package com.jecyhw.document;

/**
 * Created by jecyhw on 16-8-27.
 */
public class Picture {
    String title;
    String reference;

    public Picture() {
    }

    public Picture(String reference) {
        this.reference = reference;
    }

    public Picture(String title, String reference) {
        this.title = title;
        this.reference = reference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
