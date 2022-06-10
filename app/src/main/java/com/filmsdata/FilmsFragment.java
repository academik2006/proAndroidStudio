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





        String URL = null;
        String nameTextEnglish = null;
        String nameTextRussian = null;
        int yearText = 0;
        String descriptionText = null;
        String ratingText = null;


        if (getArguments() != null) {
            URL = getArguments().getString("URL");
            nameTextEnglish = getArguments().getString("nameTextEnglish");
            nameTextRussian = getArguments().getString("nameTextRussian");
            yearText = getArguments().getInt("yearText");
            descriptionText = getArguments().getString("descriptionText");
            ratingText = getArguments().getString("ratingText");

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

        description.setText(descriptionText);
        year.setText(String.format("Год: %d", yearText));
        nameEnglish.setText(nameTextEnglish);
        nameRussian.setText(nameTextRussian);
        Picasso.get().load(URL).into(imagePic);
        raiting.setText(String.format("Рейтинг: %s", ratingText));

        narrowBack.setOnClickListener(v1 -> dismiss());
        return v;

    }

    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d("MOY", "FragmentDialog: onDismiss");
    }
}