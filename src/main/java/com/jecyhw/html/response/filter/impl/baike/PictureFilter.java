package com.jecyhw.html.response.filter.impl.baike;

import com.jecyhw.html.response.filter.Filter;
import com.jecyhw.html.util.RequestUtil;
import com.jecyhw.document.Picture;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by jecyhw on 16-9-8.
 */
final public class PictureFilter implements Filter<Picture> {

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
