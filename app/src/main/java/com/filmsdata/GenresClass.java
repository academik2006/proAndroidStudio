package com.filmsdata;

import java.util.List;

public class GenresClass {

    private List<String> genre;
    private MainActivity.TypeOfViewHolder typeOfViewHolder;

    public GenresClass(List<String> genre, MainActivity.TypeOfViewHolder typeOfViewHolder) {
        this.genre = genre;
        this.typeOfViewHolder = typeOfViewHolder;
    }

    public MainActivity.TypeOfViewHolder getTypeOfViewHolder() {
        return typeOfViewHolder;
    }

    public void setTypeOfViewHolder(MainActivity.TypeOfViewHolder typeOfViewHolder) {
        this.typeOfViewHolder = typeOfViewHolder;
    }

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
