package br.com.fiap.sub.burguerreview.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import br.com.fiap.sub.burguerreview.R;

/**
 * Created by Galego on 20/05/2017.
 */

public class Alert {

    public static void Alert(Context context, String mensagem){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle(R.string.app_name);

        alertDialog.setMessage(mensagem);

        alertDialog.setPositiveButton("OK", null);

        alertDialog.show();

    }
}
