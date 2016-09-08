package com.jecyhw.document;

import com.fasterxml.jackson.annotation.JsonValue;
import com.jecyhw.util.JsonUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Created by jecyhw on 16-8-26.
 */
@Document(collection = "diseasePest")
public class DiseasePest {
    public enum Type{
        DISEASE("disease"),
        PEST("pest");

        String type;

        Type() {
        }

        Type(String type) {
            this.type = type;
        }

        @JsonValue
        public String getType() {
            return type;
        }
    }
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String source;

    private Type type;

    private Map introduction;


    public DiseasePest() {
    }

    public DiseasePest(Map introduction, String name, String source, Type type) {
        this.introduction = introduction;
        this.name = name;
        this.source = source;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Map getIntroduction() {
        return introduction;
    }

    public void setIntroduction(Map introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return JsonUtil.valueAsString(this);
    }
}
