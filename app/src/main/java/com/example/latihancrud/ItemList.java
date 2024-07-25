package com.example.latihancrud;

public class ItemList {

    private String id;
    private String judul;
    private String subJudul;
    private String imageUrl;

    public ItemList(String judul, String subJudul, String imageUrl) {
        this.judul = judul;
        this.subJudul = subJudul;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public String getSubJudul() {
        return subJudul;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
