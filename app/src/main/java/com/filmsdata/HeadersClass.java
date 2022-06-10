package com.filmsdata;

public class HeadersClass {

    private String title;
    private MainActivity.TypeOfViewHolder typeOfViewHolder;

    public HeadersClass(String title, MainActivity.TypeOfViewHolder typeOfViewHolder) {
        this.title = title;
        this.typeOfViewHolder = typeOfViewHolder;
    }

    public MainActivity.TypeOfViewHolder getTypeOfViewHolder() {
        return typeOfViewHolder;
    }

    public void setTypeOfViewHolder(MainActivity.TypeOfViewHolder typeOfViewHolder) {
        this.typeOfViewHolder = typeOfViewHolder;
    }

    public HeadersClass(String title){

        this.title = title;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }
}
