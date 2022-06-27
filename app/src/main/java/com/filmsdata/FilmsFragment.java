package com.filmsdata;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class FilmsFragment extends DialogFragment {

    @SuppressLint("DefaultLocale")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String URL1 = null;
        String nameTextEnglish1 = null;
        String nameTextRussian1 = null;
        int yearText1 = 0;
        String descriptionText1 = null;
        String ratingText1 = null;


        if (getArguments() != null) {
            URL1 = getArguments().getString("URL");
            nameTextEnglish1 = getArguments().getString("nameTextEnglish");
            nameTextRussian1 = getArguments().getString("nameTextRussian");
            yearText1 = getArguments().getInt("yearText");
            descriptionText1 = getArguments().getString("descriptionText");
            ratingText1 = getArguments().getString("ratingText");

        }

        Log.i("MOY", "Фрагмент получил данные");
        Objects.requireNonNull(getDialog()).setTitle("Title");

        @SuppressLint("InflateParams")
        View v = inflater.inflate(R.layout.fragment_films,null,false);
        TextView description = v.findViewById(R.id.textViewDescription);
        TextView year = v.findViewById(R.id.textView2Year);
        TextView  nameEnglish = v.findViewById(R.id.textViewName);
        TextView  nameRussian = v.findViewById(R.id.textViewRussian);
        TextView  raiting = v.findViewById(R.id.textViewRaiting);
        ImageView imagePic = v.findViewById(R.id.imageViewPic);
        ImageButton narrowBack = v.findViewById(R.id.imageButtonBack);

        description.setText(descriptionText1);
        year.setText(String.format("Год: %d", yearText1));
        nameEnglish.setText(nameTextEnglish1);
        nameRussian.setText(nameTextRussian1);
        Picasso.get().load(URL1).into(imagePic);
        raiting.setText(String.format("Рейтинг: %s", ratingText1));

        narrowBack.setOnClickListener(v1 -> dismiss());
        return v;

    }

    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d("MOY", "FragmentDialog: onDismiss");
    }
}