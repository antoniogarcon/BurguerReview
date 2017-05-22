package br.com.fiap.sub.burguerreview.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Galego on 20/05/2017.
 */

public class Database extends SQLiteOpenHelper {

    private static final String dataBaseName   = "HamburgerHouse";
    private static final int    dataBaseVersion = 1;

    private StringBuilder stringBuilderCreateTable;

    public Database(Context context){
        super(context, dataBaseName,null,dataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        stringBuilderCreateTable = new StringBuilder();

        stringBuilderCreateTable.append(" CREATE TABLE tbUser (");
        stringBuilderCreateTable.append(" login      TEXT, ");
        stringBuilderCreateTable.append(" password   TEXT) ");


        db.execSQL(stringBuilderCreateTable.toString());



        stringBuilderCreateTable = new StringBuilder();

        stringBuilderCreateTable.append(" CREATE TABLE tbHamburgerHouse (");
        stringBuilderCreateTable.append("name          TEXT , ");
        stringBuilderCreateTable.append("adress        TEXT ,            ");
        stringBuilderCreateTable.append("strong_point  TEXT ,            ");
        stringBuilderCreateTable.append("weak_point    TEXT ,            ");
        stringBuilderCreateTable.append("snack_note    TEXT ,            ");
        stringBuilderCreateTable.append("sider_note    TEXT ,            ");
        stringBuilderCreateTable.append("ambient_note  TEXT ,            ");
        stringBuilderCreateTable.append("price_range   TEXT ,            ");
        stringBuilderCreateTable.append("notes         TEXT )           ");


        db.execSQL(stringBuilderCreateTable.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS tbUser");
        db.execSQL("DROP TABLE IF EXISTS tbHamburgerHouse");
        onCreate(db);

    }

    public SQLiteDatabase GetConectionDatabase(){

        return this.getWritableDatabase();
    }
}
