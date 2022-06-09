package com.filmsdata;

import java.util.List;

public class GenresClass {

    private List<String> genre;

    GenresClass(List<String> genre){
        this.genre = genre;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }
}
