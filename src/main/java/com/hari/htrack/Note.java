package com.hari.htrack;

public class Note {
    private int id;
    private String title;
    private String info;
    private String link;
    private String type;
    private String tags;

    public Note() {
    }

    public Note(int id, String title, String info, String link, String type, String tags) {
        this.id = id;
        this.title = title;
        this.info = info;
        this.link = link;
        this.type = type;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Note && id == ((Note) o).id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
