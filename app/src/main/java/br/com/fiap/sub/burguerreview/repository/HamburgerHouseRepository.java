package br.com.fiap.sub.burguerreview.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import br.com.fiap.sub.burguerreview.model.HamburgerHouse;
import br.com.fiap.sub.burguerreview.utils.Database;

/**
 * Created by Galego on 20/05/2017.
 */

public class HamburgerHouseRepository {

    Database database;
    private final String tabela = "tbHamburgerHouse";

    private static final String[] allColumns = {"name","adress","strong_point","weak_point","snack_note","sider_note","ambient_note","price_range","notes"};
    public HamburgerHouseRepository(Context context){
        database = new Database(context);
        database.getWritableDatabase();
    }


    public void saveHamburgerHouse( HamburgerHouse hamburgerHouse){

        ContentValues contentValues =  new ContentValues();

        contentValues.put("name",         hamburgerHouse.getName());
        contentValues.put("adress",       hamburgerHouse.getAdress());
        contentValues.put("strong_point",  hamburgerHouse.getStrongPoint());
        contentValues.put("weak_point",    hamburgerHouse.getWeakPoint());
        contentValues.put("snack_note",    hamburgerHouse.getNoteSnack());
        contentValues.put("sider_note",    hamburgerHouse.getNoteSider());
        contentValues.put("ambient_note",  hamburgerHouse.getNoteAmbient());
        contentValues.put("price_range",   hamburgerHouse.getPriceRange());
        contentValues.put("notes",         hamburgerHouse.getNotes());

        database.GetConectionDatabase().insertOrThrow(tabela,null,contentValues);

    }

    public void updateHamburgerHouse(HamburgerHouse hamburgerHouse){

        ContentValues contentValues =  new ContentValues();

        contentValues.put("name",         hamburgerHouse.getName());
        contentValues.put("adress",       hamburgerHouse.getAdress());
        contentValues.put("strong_point",  hamburgerHouse.getStrongPoint());
        contentValues.put("weak_point",    hamburgerHouse.getWeakPoint());
        contentValues.put("snack_note",    hamburgerHouse.getNoteSnack());
        contentValues.put("sider_note",    hamburgerHouse.getNoteSider());
        contentValues.put("ambient_note",  hamburgerHouse.getNoteAmbient());
        contentValues.put("price_range",   hamburgerHouse.getPriceRange());
        contentValues.put("notes",        hamburgerHouse.getNotes());

        database.GetConectionDatabase().update(tabela,contentValues,"name = ?", new String[]{hamburgerHouse.getName()});
    }

    public int excluedeHamburgerHouse(HamburgerHouse hamburgerHouse){

        return database.GetConectionDatabase().delete("tbHamburgerHouse","name = ?", new String[]{hamburgerHouse.getName()});
    }

    public HamburgerHouse getHamburgerHouse(String name){

        Cursor cursor = database.GetConectionDatabase().rawQuery("SELECT * FROM tbHamburgerHouse WHERE name= "+ name,null);

        cursor.moveToFirst();

        HamburgerHouse hamburgerHouse = new HamburgerHouse();


        hamburgerHouse.setName(cursor.getString(cursor.getColumnIndex("name")));
        hamburgerHouse.setAdress(cursor.getString(cursor.getColumnIndex("adress")));
        hamburgerHouse.setStrongPoint(String.valueOf(cursor.getColumnIndex("strong_point")));
        hamburgerHouse.setWeakPoint(cursor.getString(cursor.getColumnIndex("weak_point")));
        hamburgerHouse.setNoteSnack(cursor.getFloat(cursor.getColumnIndex("snack_note")));
        hamburgerHouse.setNoteSider(cursor.getFloat(cursor.getColumnIndex("sider_note")));
        hamburgerHouse.setNoteAmbient(cursor.getFloat(cursor.getColumnIndex("ambient_note")));
        hamburgerHouse.setPriceRange(cursor.getFloat(cursor.getColumnIndex("price_range")));
        hamburgerHouse.setNotes(cursor.getString(cursor.getColumnIndex("notes")));

        return hamburgerHouse;
    }

    public int getAll(ArrayList<HamburgerHouse> hamburgerHouses){

//        StringBuilder stringBuilderQuery = new StringBuilder();
//        stringBuilderQuery.append(" SELECT *      ");
//        stringBuilderQuery.append("  FROM  tbHamburgerHouse");
//        stringBuilderQuery.append(" ORDER BY name");


        Cursor cursor = database.GetConectionDatabase().query(tabela,allColumns,null,null,null,null,null);
        Log.i("Array size", String.valueOf(cursor.getCount()));

//        try{
//            if(cursor.getCount() >0){
//
//                cursor.moveToFirst();
//
//                while(!cursor.isAfterLast()){
//
//                    HamburgerHouse tmp = new HamburgerHouse();
//
//                    tmp.setName(cursor.getString(cursor.getColumnIndex("name")));
//                    tmp.setAdress(cursor.getString(cursor.getColumnIndex("adress")));
//                    tmp.setStrongPoint(String.valueOf(cursor.getColumnIndex("strong_point")));
//                    tmp.setWeakPoint(cursor.getString(cursor.getColumnIndex("weak_point")));
//                    tmp.setNoteSnack(cursor.getFloat(cursor.getColumnIndex("snack_note")));
//                    tmp.setNoteSider(cursor.getFloat(cursor.getColumnIndex("sider_note")));
//                    tmp.setNoteAmbient(cursor.getFloat(cursor.getColumnIndex("ambient_note")));
//                    tmp.setPriceRange(cursor.getFloat(cursor.getColumnIndex("price_range")));
//                    tmp.setNotes(cursor.getString(cursor.getColumnIndex("notes")));
//
//                    hamburgerHouses.add(tmp);
//
//                    Log.i("name",tmp.getName());
//                    Log.i("notes",tmp.getNotes());
//
//                    cursor.moveToNext();
//                }
//                return hamburgerHouses.size();
//            }
//            return 0;
//        }catch (NullPointerException e){
//            Log.i("Error: ", String.valueOf(e));
//        }

        return 0;
    }

}
