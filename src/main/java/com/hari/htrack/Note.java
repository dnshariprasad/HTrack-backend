package com.hari.htrack;

public class Note {
    private long id;
    private String title;
    private String info;

    public Note(long id, String title, String info) {
        this.id = id;
        this.title = title;
        this.info = info;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
