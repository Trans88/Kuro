package com.trans.kuro_core.util.Toast;

import android.widget.Toast;

import com.trans.kuro_core.app.Kuro;

public class ToastUtil {

    public static void shortShow(String Content){
        Toast.makeText(Kuro.getApplicationContext(),Content,Toast.LENGTH_SHORT).show();
    }

    public static void longShow(String Content){
        Toast.makeText(Kuro.getApplicationContext(),Content,Toast.LENGTH_LONG).show();
    }
}
