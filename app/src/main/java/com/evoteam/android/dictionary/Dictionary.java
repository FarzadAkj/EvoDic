package com.evoteam.android.dictionary;

public class Dictionary {

    private String word;
    private String translate;
    private String picture;

    public Dictionary(String word, String translate, String picture){
        this.word = word;
        this.translate = translate;
        this.picture = picture;
    }

    public Dictionary(){
        this(null, null, null);
    }

    //getters and setters

    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getTranslate() {
        return translate;
    }
    public void setTranslate(String translate) {
        this.translate = translate;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String toString(){
        return word;
    }
}
