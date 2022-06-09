package com.filmsdata;

import java.util.List;

public class FilmsClass {

    private String titleOfFilm;
    private String image_url;
    private String description;
    private List<String> genres;

    public FilmsClass(String titleOfFilm, String image_url, String description, List<String> genres) {
        this.titleOfFilm = titleOfFilm;
        this.image_url = image_url;
        this.description = description;
        this.genres = genres;
    }

    public String getTitleOfFilm() {
        return titleOfFilm;
    }

    public void setTitleOfFilm(String titleOfFilm) {
        this.titleOfFilm = titleOfFilm;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
