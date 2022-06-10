package com.filmsdata.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.filmsdata.GenresClass;
import com.filmsdata.MainActivity;
import com.filmsdata.R;

import java.util.Arrays;
import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenresViewHolder> {

    private final List<String> genresListFil;
    private final LayoutInflater inflater;
    private final MainActivity.TypeOfViewHolder typeOfViewHolder;


    public GenresAdapter(Context context, List<String> genresListFil, MainActivity.TypeOfViewHolder typeOfViewHolder){
        this.genresListFil = genresListFil;
        this.inflater = LayoutInflater.from(context);
        this.typeOfViewHolder = typeOfViewHolder;
    }

    @NonNull
    @Override
    public GenresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.geres_layout,parent,false);
        return new GenresViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GenresAdapter.GenresViewHolder holder, int position) {


        /*
        GenresClass genre = genresClassList.get(position);
        holder.changeHolder(genre.getGenre());

         */
        String genre = genresListFil.get(position);
        holder.genre_text.setText(genre);

    }

    @Override
    public int getItemCount() {
        return genresListFil.size();
    }

    class GenresViewHolder extends RecyclerView.ViewHolder {

        TextView genre_text;

        public GenresViewHolder(@NonNull View itemView) {
            super(itemView);
            genre_text = itemView.findViewById(R.id.textViewGenre);
        }

        void changeHolder (List<String> genre) {

            //String [] result_of_parsing = new String[MAX_ELEMENTS_IN_GENRES];

            String result_of_parsing = null;

            for (int I = 0; I<genre.size();I++)
            {
                //result_of_parsing [I] = genre.get(I);
                result_of_parsing = genre.get(I);

            }
            Log.i("MOY", "Список жанров " + result_of_parsing);

            //genre_text.setText(result_of_parsing[0]);
            genre_text.setText(result_of_parsing);
        }
    }





}
