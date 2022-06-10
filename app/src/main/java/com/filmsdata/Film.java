package com.filmsdata;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Film {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("localized_name")
    @Expose
    private String localizedName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("rating")
    @Expose
    private Object rating;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public Object getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getGenres() {
        return genres;
    }

}