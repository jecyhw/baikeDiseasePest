package com.jecyhw.model.document;

import com.jecyhw.util.JsonUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jecyhw on 16-8-29.
 */
@Document(collection = "error")
public class SearchError {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String message;

    public SearchError() {
    }

    public SearchError(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return JsonUtil.valueAsString(this);
    }
}
