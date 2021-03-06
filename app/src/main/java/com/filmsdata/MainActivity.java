package com.filmsdata;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MainActivity extends AppCompatActivity implements FilmsAdapter.OnFilmClickListener {

    ArrayList<HeadersClass> headers = new ArrayList<>();
    ArrayList<GenresClass> genres = new ArrayList<>();
    ArrayList<FilmsClass> films = new ArrayList<>();
    ArrayList<FilmsClassDouble> filmsDouble = new ArrayList<>();

    String first_header = "Жанры";
    String second_header = "Фильмы";
    ArrayList<Film> dataListAll;
    ArrayList<String> genres_filter = new ArrayList<>();

    public RecyclerView filmsList;
    DialogFragment dlg;

    @Override
    public void onFilmClick(FilmsClassDouble filmsClassDouble, View itemView, int position) {

    }


    public enum TypeOfViewHolder {
        HOLDER,
        GENRE,
        FILM                 }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //полноэкранный режим диалога
        dlg = new FilmsFragment();
        dlg.setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_Black_NoTitleBar_Fullscreen);

        //получение данных с сервера

        ApiService api = RetrofitController.getApi();
        Call<Data> call = api.getMyJSON();
        call.enqueue(new Callback<Data>() {

            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull retrofit2.Response<Data> response) {

                if (response.isSuccessful()||chekConnection(MainActivity.this)) {
                    assert response.body() != null;
                    dataListAll = response.body().getFilms();
                    //заполнение списка данных
                    Log.i("MOY", "Получен ответ сервера" + dataListAll.size());
                    setInitialData();
                    // инициализация списка
                    filmsList = findViewById(R.id.recyclerViewFilms);

                    // определяем слушателя нажатия элемента в списке фильмов
                    FilmsAdapter.OnFilmClickListener onFilmClickListener = (filmsClassDouble, itemView, position) -> {

                        Log.i("MOY", "Сработал слушатель");


                        Bundle bundle = new Bundle();

                        bundle.putString ("URL", filmsClassDouble.getFilm1().getImage_url());
                        bundle.putString ("nameTextEnglish", filmsClassDouble.getFilm1().getName());
                        bundle.putString ("nameTextRussian", filmsClassDouble.getFilm1().getLocalizename());
                        bundle.putInt ("yearText", filmsClassDouble.getFilm1().getYear());
                        bundle.putString ("descriptionText", filmsClassDouble.getFilm1().getDescription());
                        bundle.putString ("ratingText", String.valueOf(filmsClassDouble.getFilm1().getRating()));

                        bundle.putString ("URL", filmsClassDouble.getFilm2().getImage_url());
                        bundle.putString ("nameTextEnglish", filmsClassDouble.getFilm2().getName());
                        bundle.putString ("nameTextRussian", filmsClassDouble.getFilm2().getLocalizename());
                        bundle.putInt ("yearText", filmsClassDouble.getFilm2().getYear());
                        bundle.putString ("descriptionText", filmsClassDouble.getFilm2().getDescription());
                        bundle.putString ("ratingText", String.valueOf(filmsClassDouble.getFilm2().getRating()));


                        dlg.setArguments(bundle);
                        Log.i("MOY", "Создан объект Bundle");
                        dlg.show(getSupportFragmentManager(), "dlg");
                        Log.i("MOY", "Стартовал фрагмент");

                    };


                    //установка LayoutManager для списка

                    LinearLayoutManager layoutManagerFilmsList = new LinearLayoutManager(MainActivity.this);
                    filmsList.setLayoutManager(layoutManagerFilmsList);

                    // Инициализация и установка адаптера

                    HeadersAdapter headerAdapter1 = new HeadersAdapter(MainActivity.this, first_header, TypeOfViewHolder.HOLDER);
                    HeadersAdapter headerAdapter2 = new HeadersAdapter(MainActivity.this, second_header, TypeOfViewHolder.HOLDER);

                    GenresAdapter genresAdapter = new GenresAdapter(MainActivity.this, genres_filter.stream().distinct().collect(Collectors.toList()),TypeOfViewHolder.GENRE);
                    //FilmsAdapter filmsAdapter = new FilmsAdapter(MainActivity.this, films,TypeOfViewHolder.FILM, onFilmClickListener);
                    FilmsAdapter filmsAdapter = new FilmsAdapter(MainActivity.this, films, filmsDouble, TypeOfViewHolder.FILM, onFilmClickListener);

                    // Используем адаптер заголовка дважды
                    ConcatAdapter concatAdapter = new ConcatAdapter(headerAdapter1, genresAdapter, headerAdapter2, filmsAdapter);
                    filmsList.setAdapter(concatAdapter);



                    Log.i("MOY", "Установлен адаптер");

                } else
                    Toast.makeText(MainActivity.this, "Ошибка ответа сервера", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Ошибка соединения с сервером", Toast.LENGTH_SHORT).show();

            }
        });

    }

        private void setInitialData() {

            headers.add(new HeadersClass(first_header,TypeOfViewHolder.HOLDER));
            headers.add(new HeadersClass(second_header, TypeOfViewHolder.HOLDER));

            for (int I = 0; I < dataListAll.size(); I++) {

                films.add(new FilmsClass(dataListAll.get(I).getName(), dataListAll.get(I).getImageUrl(),
                        dataListAll.get(I).getDescription(), dataListAll.get(I).getGenres(), TypeOfViewHolder.FILM,
                        dataListAll.get(I).getLocalizedName(), dataListAll.get(I).getYear(), dataListAll.get(I).getRating()));
               // Log.i("MOY", "Полученные объекты списка films " + films.get(I).getImage_url());


                genres.add(new GenresClass(dataListAll.get(I).getGenres(), TypeOfViewHolder.GENRE));
                // Log.i("MOY", "Полученные объекты списка genres" + "" + genres.get(I).getGenre().toString());

            }

            if (films.size()%2!=0)

            {
                films.add(new FilmsClass(dataListAll.get(0).getName(), dataListAll.get(0).getImageUrl(),
                        dataListAll.get(0).getDescription(), dataListAll.get(0).getGenres(), TypeOfViewHolder.FILM,
                        dataListAll.get(0).getLocalizedName(), dataListAll.get(0).getYear(), dataListAll.get(0).getRating()));

            }



            for (int I = 0; I < films.size(); I=I+2) {

                filmsDouble.add(new FilmsClassDouble(films.get(I), films.get(I+1)));
                //Log.i("MOY", "Шаг цикла " + I);
                //Log.i("MOY", "Полученные объекты списка filmsDoble " + filmsDouble.get(I).toString());
            }


            // фильтрация списка жанров, удаление дублей
            IntStream.range(0, genres.size()).forEach(K -> genres_filter.addAll(genres.get(K).getGenre()));

        }


        //Проверка наличия интернет - соединения

        public Boolean chekConnection (@NonNull Context context) {

        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;


        }



    }
