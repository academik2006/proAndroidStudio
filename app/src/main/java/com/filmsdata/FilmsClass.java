package com.filmsdata;

import java.util.List;

public class FilmsClass {

    private String name;
    private String image_url;
    private String description;
    private List<String> genres;
    private MainActivity.TypeOfViewHolder typeOfViewHolder;
    private String localizename;
    private int year;
    private Object rating;

    public FilmsClass(String name, String image_url, String description, List<String> genres, MainActivity.TypeOfViewHolder typeOfViewHolder, String localizename, int year, Object rating) {
        this.name = name;
        this.image_url = image_url;
        this.description = description;
        this.genres = genres;
        this.typeOfViewHolder = typeOfViewHolder;
        this.localizename = localizename;
        this.year = year;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }


    public String getDescription() {
        return description;
    }


    public MainActivity.TypeOfViewHolder getTypeOfViewHolder() {
        return typeOfViewHolder;
    }


    public String getLocalizename() {
        return localizename;
    }


    public int getYear() {
        return year;
    }

    public Object getRating() {
        return rating;
    }

}
