package br.com.fiap.sub.burguerreview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.fiap.sub.burguerreview.model.User;
import br.com.fiap.sub.burguerreview.repository.UserRepository;
import br.com.fiap.sub.burguerreview.utils.Alert;
import br.com.fiap.sub.burguerreview.utils.Database;


public class Login extends AppCompatActivity {

    private User tmp;

    EditText ed_login;
    EditText ed_password;
    CheckBox ck_remember;

    Database database;

    User user;
    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
    }


    private void initialize(){

        ed_login = (EditText) findViewById(R.id.ed_login_user);
        ed_password = (EditText) findViewById(R.id.ed_login_password);
        ck_remember = (CheckBox) findViewById(R.id.ck_login);

        tmp = new User();
        database = new Database(this);

        userRepository = new UserRepository(this);
        user = new User();
    }

    public void login(View view){

       try {

            user = userRepository.getUser(ed_login.getText().toString());

           if (user  == null) {
               Alert.Alert(this, "User not found!");

           }else {
                   if (ed_login.getText().toString().trim().equals(user.getLogin()) && ed_password.getText().toString().trim().equals(user.getPassword())) {

                       if (ck_remember.isChecked() == true) {
                           sharePreference(ed_login.getText().toString(), ed_password.getText().toString());
                       }
                       Intent i = new Intent(this, Navigation.class);
                       startActivity(i);
                   } else {
                       Alert.Alert(this, "Wrong user or password!");

                   }
           }
       }catch (NullPointerException error){
            Log.i("Realm Error: ", String.valueOf(error));
        }
    }

    private void sharePreference(String login, String password){
        SharedPreferences sharedPref = getSharedPreferences("burguerReview",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("LOGIN",login);
        editor.putString("PASSWORD",password);
        editor.apply();
    }
}
