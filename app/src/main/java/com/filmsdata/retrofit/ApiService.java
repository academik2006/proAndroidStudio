package com.filmsdata.retrofit;

import com.filmsdata.Data;
import com.filmsdata.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET ("/sequeniatesttask/films.json")
    Call<Data> getMyJSON ();

}
