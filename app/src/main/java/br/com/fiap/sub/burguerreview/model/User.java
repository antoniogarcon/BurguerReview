package br.com.fiap.sub.burguerreview.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lsitec91.garcon on 08/05/2017.
 */

public class User{
    @PrimaryKey
    private String login;
    private String password;


    public User(){
        login = "";
        password = "";
    }

    public User(String login, String password){
        this.setLogin(login);
        this.setPassword(password);



    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
