package com.jecyhw.service.impl;

import com.google.common.collect.ImmutableList;
import com.jecyhw.html.baidu.BaikeConstant;
import com.jecyhw.html.baidu.BaikePicture;
import com.jecyhw.html.baidu.BaikeSerach;
import com.jecyhw.html.response.filter.Filter;
import com.jecyhw.model.document.DiseasePest;
import com.jecyhw.model.document.Paragraph;
import com.jecyhw.model.document.Picture;
import com.jecyhw.repository.DiseasePestRepository;
import com.jecyhw.service.DiseasePestService;
import com.jecyhw.util.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jecyhw on 16-8-26.
 */
@Service
final public class DiseasePestServiceImpl implements DiseasePestService {

    @Autowired
    DiseasePestRepository repository;

    @Override
    public DiseasePest search(String word, DiseasePest.Type type) throws IOException {
        DiseasePest diseasePest = repository.findByName(word);
        if (diseasePest == null) {
            BaikeSerach.Params params = new BaikeSerach.Params(word);
            BaikeSerach serach = new BaikeSerach(params);
            PestFilter filter = new PestFilter(serach.html());
            diseasePest = new DiseasePest(filter.filter(), word, serach.getResponseUrl(), type);
            repository.save(diseasePest);
        }
        return diseasePest;
    }

    @Override
    public List<DiseasePest> all() {
        return ImmutableList.copyOf(repository.findAll());
    }

    static final class PestFilter implements Filter<Map<String, Object>> {

        final Document doc;
        Element current;
        public PestFilter(String html) {
            doc = Jsoup.parse(html);
        }

        @Override
        public Map<String, Object> filter() {
            Map<String, Object> intro = new LinkedHashMap<>();

            //
            intro.put("简介", doc.select(".lemma-summary").first().text());

            //基本信息
            intro.putAll(basicInfo());

            //目录
            current = doc.select(".para-title.level-2").first();
            if (current != null) {
                intro.putAll(nest());
                //词条,词条比较特殊,需要从script标签中提取src,再进行拼接
                if (current != null && current.hasClass("album-list")) {
                    intro.put(StringUtil.removeBlank(current.select("span.title").first().text()), originWordAlbum());
                }
            }

            return intro;
        }

        final static Pattern ALBUM_LIST_PATTERN = Pattern.compile("AlbumList\\(\\{[\\s\\S]+?\\}\\);");
        final static Pattern COVERPIC_JSON_PATTERN = Pattern.compile("[^:,]+:coverpic:[^:,]+") ;
        final static Pattern COVERPIC_ARRAY_PATTERN = Pattern.compile("coverpic:[^:,]+") ;

        private List<List<Picture>> originWordAlbum() {
            List<List<Picture>> pictures = new ArrayList<>();
            List<String> links = new ArrayList<>();
            String linkPrefix = BaikeConstant.ROOT_URL + current.select(".more-link").first().attr("href").replaceAll("\\?[\\s\\S]+$", "");
            Matcher matcher = ALBUM_LIST_PATTERN.matcher(doc.body().html());
            if (matcher.find()) {
                String data = StringUtil.removeBlank(matcher.group()).replaceAll("[\\{\\}\\[\\]\"]", "");
                if (data.contains("data:coverpic")) {//为数组格式
                    matcher = COVERPIC_ARRAY_PATTERN.matcher(data);
                    int index = 0;
                    while (matcher.find()) {
                        String[] temp = matcher.group().split(":");
                        links.add(linkPrefix + "/" + index++ + "/" + temp[1] + "?fr=lemma");
                    }
                } else {//json格式
                    matcher = COVERPIC_JSON_PATTERN.matcher(data);
                    while (matcher.find()) {
                        String[] temp = matcher.group().split(":");
                        links.add(linkPrefix + "/" + temp[0] + "/" + temp[2] + "?fr=lemma");
                    }
                }

            }
            for (String link : links) {
                pictures.add(new BaikePicture(link).pictures());
            }
            return pictures;
        }

        private Map<String, String> basicInfo() {
            Map<String, String> map = new LinkedHashMap<>();
            Elements basicInfo = doc.select(".basic-info > .basicInfo-block");
            for (Element basicInfoItems : basicInfo) {
                Iterator<Element> iterator = basicInfoItems.children().iterator();
                while (iterator.hasNext()) {
                    map.put(StringUtil.removeBlank(iterator.next().text()), iterator.next().text());
                }
            }
            return map;
        }

        private Object next() {
            current = current.nextElementSibling();//从para-title转向下一个para或者anchor-list的div
            if (current.hasClass("anchor-list")) {//还要嵌套的子级分类
                current = current.nextElementSibling();//移动到para-title的div
                return nest();
            }
            return paragraph();//返回内容
        }

        private List<Paragraph> paragraph() {//还有图片
            List<Paragraph> paras = new ArrayList<>();
            do {
                Paragraph para = new Paragraph();
                para.setPictures(findParagraphPicture());//查找段落图片
                removeInvalidTagsInParagraph();//移除不相关的标签
                para.setContent(current.text());//查找段落内容
                paras.add(para);
                current = current.nextElementSibling();
            } while (current.hasClass("para"));
            current = current.nextElementSibling();//让current指向下一个para-title
            return paras;
        }

        private Map<String, Object> nest() {//current为para-title的div
            int level = level(current);//获取当前的级别
            Map<String, Object> map = new LinkedHashMap<>();
            do {
                map.put(title(current), next());
            } while (current != null && current.hasClass("para-title") && level == level(current));
            return map;
        }

        private List<Object> findParagraphPicture() {
            List<Object> pictures = new ArrayList<>();
            pictures.addAll(originPicture());
            pictures.addAll(originAlbum());
            return pictures;
        }

        private List<Picture> originPicture() {//picture的列表
            List<Picture> pictures = new ArrayList<>();
            Elements lemmaPictures = current.select(".lemma-picture");
            for (Element lemmaPicture : lemmaPictures) {
                pictures.add(new BaikePicture(BaikeConstant.ROOT_URL + lemmaPicture.select(".image-link").first().attr("href")).picture());
            }
           return pictures;
        }

        private List<List<Picture>> originAlbum() {//List<Picture>列表
            List<List<Picture>> pictures = new ArrayList<>();
            Elements lemmaAlbums = current.select(".lemma-album");
            for (Element lemmaAlbum : lemmaAlbums) {
                pictures.add(new BaikePicture(BaikeConstant.ROOT_URL + lemmaAlbum.attr("href")).pictures());
            }
            return pictures;
        }

        private void removeInvalidTagsInParagraph() {
            current.select("sup").remove();//删除引用
            current.select(".lemma-picture").remove();
            current.select(".lemma-album").remove();
        }

        private int level(Element ele) {
            return Integer.parseInt(ele.attr("class").replaceAll("[^0-9]", "") );
        }

        private String title(Element ele) {
            return StringUtil.removeBlank(ele.select(".title-text").first().ownText());
        }
    }
}
