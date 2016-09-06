package com.jecyhw.html.baidu;

import com.jecyhw.model.document.Picture;
import com.jecyhw.html.response.filter.Filter;
import com.jecyhw.html.util.RequestUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jecyhw on 16-8-27.
 */
public class BaikePicture {

    final String url;

    public BaikePicture(String url) {
        this.url = url;
    }

    public List<Picture> pictures() {
        return new AlbumFilter(this.url).filter();
    }

    public Picture picture() {
        return new PictureFilter(this.url).filter();
    }

    static final class AlbumFilter implements Filter<List<Picture>> {

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

    static final class PictureFilter implements Filter<Picture> {

        final String url;

        public PictureFilter(String url) {
            this.url = url;
        }

        @Override
        public Picture filter() {
            Picture picture = new Picture();
            try {
                Document doc = RequestUtil.getDocument(this.url);
                picture.setTitle(doc.select("a.pic-item.selected").first().attr("title"));
                picture.setReference(doc.select("#imgPicture").first().attr("src"));
            } catch (IOException e) {
                picture.setReference(this.url);
            }
            return picture;
        }
    }
}
