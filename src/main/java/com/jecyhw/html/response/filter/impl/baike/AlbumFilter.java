package com.jecyhw.html.response.filter.impl.baike;

import com.jecyhw.html.baike.BaikeConstant;
import com.jecyhw.html.response.filter.Filter;
import com.jecyhw.html.util.RequestUtil;
import com.jecyhw.document.Picture;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jecyhw on 16-9-8.
 */
final public class AlbumFilter implements Filter<List<Picture>> {

    final String url;

    public AlbumFilter(String url) {
        this.url = url;
    }

    @Override
    public List<Picture> filter() {
        List<Picture> pictures = new AlbumLinkFilter().filter();
        if (pictures.size() == 0) {
            Picture picture = new Picture();
            picture.setReference(this.url);
            pictures.add(picture);//抓取异常,保留元抓取链接
        } else {
            for (Picture picture : pictures) {
                try {
                    Document doc = RequestUtil.getDocument(picture.getReference());
                    Element element = doc.select("a.tool-button.origin").first();
                    if (element != null) {
                        picture.setReference(element.attr("href"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return pictures;
    }

    class AlbumLinkFilter implements Filter<List<Picture>> {
        @Override
        public List<Picture> filter() {
            List<Picture> pictures = new ArrayList<>();
            try {
                Document doc = RequestUtil.getDocument(url);
                Elements items = doc.select("a.pic-item");
                for (Element item : items) {
                    Picture picture = new Picture();
                    picture.setTitle(item.attr("title"));
                    picture.setReference(BaikeConstant.ROOT_URL + item.attr("href"));//获取每张图片所在的网页
                    pictures.add(picture);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return pictures;
        }
    }
}
