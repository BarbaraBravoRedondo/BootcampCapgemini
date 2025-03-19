package com.example.domains.entities.dtos;

import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

public class LanguageShort {

    @JsonProperty("id")
    @JsonView(Language.Partial.class)
    private int languageId;

    @JsonProperty("name")
    @JsonView(Language.Partial.class)
    private String name;

    public LanguageShort() {
    }

    public LanguageShort(int languageId, String name) {
        this.languageId = languageId;
        this.name = name;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LanguageShortDTO [languageId=" + languageId + ", name=" + name + "]";
    }
}
