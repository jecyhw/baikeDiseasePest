package com.jecyhw.html.baike;

import com.jecyhw.html.response.filter.impl.baike.AlbumFilter;
import com.jecyhw.html.response.filter.impl.baike.PictureFilter;
import com.jecyhw.document.Picture;

import java.util.List;

/**
 * Created by jecyhw on 16-8-27.
 */
final public class BaikePicture {

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
}
