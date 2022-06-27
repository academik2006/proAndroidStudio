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
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;
import com.filmsdata.FilmsClass;
import com.filmsdata.FilmsClassDouble;
import com.filmsdata.MainActivity;
import com.filmsdata.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder> {

    private final List<FilmsClass> filmsClassList;
    private final List<FilmsClassDouble> filmsClassDoublesList;
    private final LayoutInflater inflater;
    private final MainActivity.TypeOfViewHolder typeOfViewHolder;


    public FilmsAdapter(Context context, List<FilmsClass> filmsClassList, List<FilmsClassDouble> filmsClassDoublesList, MainActivity.TypeOfViewHolder typeOfViewHolder, OnFilmClickListener onFilmClickListener){
        this.filmsClassList = filmsClassList;
        this.filmsClassDoublesList = filmsClassDoublesList;
        this.inflater = LayoutInflater.from(context);
        this.typeOfViewHolder = typeOfViewHolder;
        this.onFilmClickListener = onFilmClickListener;
    }

    public interface OnFilmClickListener{
        void onFilmClick(FilmsClassDouble filmsClassDouble, View itemView, int position);
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


        //FilmsClass film = filmsClassList.get(position);
        //FilmsClassDouble filmsClassDouble = new FilmsClassDouble ());

        FilmsClassDouble filmsClassDouble = new FilmsClassDouble(filmsClassDoublesList.get(position).getFilm1(), filmsClassDoublesList.get(position).getFilm2());

        //holder.changeHolder(film);
        holder.changeHolder(filmsClassDouble);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onFilmClickListener.onFilmClick(filmsClassDouble, holder.itemView, position);
                Log.i("MOY", "Передан по клику объект filmClassDouble" + holder.itemView);

            }
        });

    }

    @Override
    public int getItemCount() {

        return filmsClassDoublesList.size();

    }

    class FilmsViewHolder extends RecyclerView.ViewHolder {

        TextView films_text1;
        ImageView imageViewFilm1;

        TextView films_text2;
        ImageView imageViewFilm2;

        public FilmsViewHolder(@NonNull View itemView) {
            super(itemView);

            films_text1 = itemView.findViewById(R.id.textViewFilms1);
            imageViewFilm1 = itemView.findViewById(R.id.imageViewFilm1);

            films_text2 = itemView.findViewById(R.id.textViewFilms2);
            imageViewFilm2 = itemView.findViewById(R.id.imageViewFilm2);

        }

        void changeHolder (FilmsClassDouble filmsClassDouble) {

                films_text1.setText(filmsClassDouble.getFilm1().getLocalizename());
                Picasso.get().load(filmsClassDouble.getFilm1().getImage_url()).into(imageViewFilm1);

                films_text2.setText(filmsClassDouble.getFilm2().getLocalizename());
                Picasso.get().load(filmsClassDouble.getFilm2().getImage_url()).into(imageViewFilm2);

        }

        }
    }


