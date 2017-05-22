package br.com.fiap.sub.burguerreview.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import br.com.fiap.sub.burguerreview.model.User;
import br.com.fiap.sub.burguerreview.utils.Database;

import static android.R.attr.password;

/**
 * Created by Galego on 20/05/2017.
 */

public class UserRepository {

    Database database;

    public UserRepository(Context context) {
        database = new Database(context);
        database.getWritableDatabase();
    }

    public void saveUser(User user){

        ContentValues contentValues =  new ContentValues();

        contentValues.put("login",      user.getLogin());
        Log.i("usuario",user.getLogin());
        contentValues.put("password",   user.getPassword());
        Log.i("pass",user.getPassword());

        database.GetConectionDatabase().insertOrThrow("tbUser", null, contentValues);
    }


    public int excludeUser(String login){

        return database.GetConectionDatabase().delete("tbUser","login = ?", new String[]{login});
    }

    public User getUser(String login){

        String[] campos = {"login","password"};

        Cursor cursor;
        cursor = database.GetConectionDatabase().query("tbUser",campos,null,null,null,null,null);
        User user =  new User();


        if(cursor.getCount() >0)

        {
            Log.i("num: ","" + cursor.getCount());
            cursor.moveToFirst();



            user.setLogin(cursor.getString(cursor.getColumnIndex("login")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
        }

        return user;
    }
}
