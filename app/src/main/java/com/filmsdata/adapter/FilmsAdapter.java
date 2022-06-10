package com.filmsdata.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.filmsdata.FilmsClass;
import com.filmsdata.MainActivity;
import com.filmsdata.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder> {

    private final List<FilmsClass> filmsClassList;
    private final LayoutInflater inflater;
    private final MainActivity.TypeOfViewHolder typeOfViewHolder;

    public FilmsAdapter(Context context, List<FilmsClass> filmsClassList, MainActivity.TypeOfViewHolder typeOfViewHolder, OnFilmClickListener onFilmClickListener){
        this.filmsClassList = filmsClassList;
        this.inflater = LayoutInflater.from(context);
        this.typeOfViewHolder = typeOfViewHolder;
        this.onFilmClickListener = onFilmClickListener;
    }

    public interface OnFilmClickListener{
        void onFilmClick(FilmsClass film, int position);
    }

    private final OnFilmClickListener onFilmClickListener;

    @NonNull
    @Override
    public FilmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.films_layout, parent, false);
        return new FilmsViewHolder (view);

    }

    @Override
    public void onBindViewHolder(@NonNull FilmsViewHolder holder, @SuppressLint("RecyclerView") int position) {

        FilmsClass film = filmsClassList.get(position);
        holder.changeHolder(film);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onFilmClickListener.onFilmClick(film,position);
                Log.i("MOY", "Передан по клику объект film" + film.getName());

            }
        });

    }

    @Override
    public int getItemCount() {
        return filmsClassList.size();
    }



    class FilmsViewHolder extends RecyclerView.ViewHolder {

        TextView films_text;
        ImageView imageViewFilm;


        public FilmsViewHolder(@NonNull View itemView) {
            super(itemView);
            films_text = itemView.findViewById(R.id.textViewFilms);
            imageViewFilm = itemView.findViewById(R.id.imageViewFilm);


        }

        void changeHolder (FilmsClass film) {
            films_text.setText(film.getLocalizename());
            Picasso.get().load(film.getImage_url()).into(imageViewFilm);

        }



        }
    }


