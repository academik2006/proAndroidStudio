package com.filmsdata.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import androidx.annotation.NonNull;


// Проверка наличия интернет соединения

public class InternetConnection {

    public static Boolean chekConnection (@NonNull Context context) {

        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;


    }
}
