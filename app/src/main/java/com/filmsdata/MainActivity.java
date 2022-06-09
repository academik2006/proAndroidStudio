package com.filmsdata;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.filmsdata.adapter.FilmsAdapter;
import com.filmsdata.adapter.GenresAdapter;
import com.filmsdata.adapter.HeadersAdapter;
import com.filmsdata.retrofit.ApiService;
import com.filmsdata.retrofit.RetrofitController;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    ArrayList<HeadersClass> headers = new ArrayList<>();
    ArrayList<GenresClass> genres = new ArrayList<>();
    ArrayList<FilmsClass> films = new ArrayList<>();

    String first_header = "Жанры";
    String second_header = "Фильмы";
    ArrayList<Film> dataListAll;
    ArrayList<String> genres_filter = new ArrayList<>();

    RecyclerView filmsList;
    Button button_for_debug;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_for_debug = findViewById(R.id.button2);

        //получение данных с сервера

        ApiService api = RetrofitController.getApi();
        Call<Data> call = api.getMyJSON();
        call.enqueue(new Callback<Data>() {

            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull retrofit2.Response<Data> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    dataListAll = response.body().getFilms();
                    //заполнение списка данных
                   // Log.i("MOY", "Полученные объекты класса Film" + dataListAll.size());
                    setInitialData();
                    // инициализация списка
                    filmsList = findViewById(R.id.recyclerViewFilms);
                    //установка LayoutManager для списка

                    /*
                    GridLayoutManager layoutManagerFilmsList = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);
                    filmsList.setLayoutManager(layoutManagerFilmsList);

                     */

                    LinearLayoutManager layoutManagerFilmsList = new LinearLayoutManager(MainActivity.this);
                    filmsList.setLayoutManager(layoutManagerFilmsList);

                    // Инициализация и установка адаптера
                    HeadersAdapter headerAdapter1 = new HeadersAdapter(MainActivity.this, first_header);
                    HeadersAdapter headerAdapter2 = new HeadersAdapter(MainActivity.this, second_header);
                    //GenresAdapter genresAdapter = new GenresAdapter(MainActivity.this, genres_filter_2);
                    GenresAdapter genresAdapter = new GenresAdapter(MainActivity.this, genres_filter.stream().distinct().collect(Collectors.toList()));
                    FilmsAdapter filmsAdapter = new FilmsAdapter(MainActivity.this, films);
                    // Используем адаптер заголовка дважды
                    ConcatAdapter concatAdapter = new ConcatAdapter(headerAdapter1, genresAdapter, headerAdapter2, filmsAdapter);
                    filmsList.setAdapter(concatAdapter);

                } else
                    Toast.makeText(MainActivity.this, "Ошибка ответа сервера", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Ошибка соединения с сервером", Toast.LENGTH_SHORT).show();

            }
        });

        button_for_debug.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Кнопка нажата", Toast.LENGTH_SHORT).show());
    }


        private void setInitialData() {

            headers.add(new HeadersClass(first_header));
            headers.add(new HeadersClass(second_header));

            for (int I = 0; I < dataListAll.size(); I++) {

                films.add(new FilmsClass(dataListAll.get(I).getLocalizedName(), dataListAll.get(I).getImageUrl(),
                        dataListAll.get(I).getDescription(), dataListAll.get(I).getGenres()));
                //Log.i("MOY", "Полученные объекты списка films " + films.get(I).getImage_url());

                genres.add(new GenresClass(dataListAll.get(I).getGenres()));
                //Log.i("MOY", "Полученные объекты списка genres" + "" + genres.get(I).getGenre().toString());

            }
            // фильтрация списка жанров, удаление дублей
            IntStream.range(0, genres.size()).forEach(K -> genres_filter.addAll(genres.get(K).getGenre()));

        }
    }
