package br.com.fiap.sub.burguerreview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.fiap.sub.burguerreview.model.HamburgerHouse;
import br.com.fiap.sub.burguerreview.model.User;
import br.com.fiap.sub.burguerreview.repository.HamburgerHouseRepository;
import br.com.fiap.sub.burguerreview.repository.UserRepository;
import br.com.fiap.sub.burguerreview.utils.Database;
import io.realm.exceptions.RealmException;

import static com.android.volley.Response.*;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar progressBar;

    private String url = "http://www.mocky.io/v2/58b9b1740f0000b614f09d2f";
    private RequestQueue requestQueue;

    private User user;
    private HamburgerHouse hamburgerHouse;

    private UserRepository userRepository;
    private HamburgerHouseRepository hamburgerHouseRepository;
    private int progress;

    private Intent intent;
    private boolean boolsharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initializeValues();

        setProgressValue(progress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeValues();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initializeValues()  {


        progressBar = (ProgressBar) findViewById(R.id.pb_splash);

        Cache cache = new DiskBasedCache(getCacheDir(), 1024*1024);
        Network network  = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache,network);
        requestQueue.start();

        hamburgerHouse = new HamburgerHouse();
        user = new User();
        hamburgerHouseRepository = new HamburgerHouseRepository(this);
        userRepository = new UserRepository(SplashScreen.this);

        progress =0;


        try{
            saveHamburgerHouses();
        } catch (SQLiteConstraintException e){
            Log.i("Error: ",String.valueOf(e));
        } catch(SQLiteException e){
            Log.i("Error: ",String.valueOf(e));
        }


        sharePreferenceVerification();
    }

    private void jsonSync(){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url, null,
                new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            user = userRepository.getUser(response.getString("usuario"));
                            if(user != null){

                                user.setLogin(response.getString("usuario"));
                                user.setPassword(response.getString("senha"));

                                userRepository.saveUser(user);
                            }
                        } catch (JSONException e) {
                            Log.i("Error: ",String.valueOf(e));
                        }catch (SQLiteException e){
                            Log.i("Error: ",String.valueOf(e));
                        }
                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error: ", String.valueOf(error));

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void setProgressValue(final int progress) {

        if(progress > 100){
            if(boolsharedPreference == true){
                startActivity(intent);
            }else{
                jsonSync();
                startActivity(intent);
            }
        }else{
            progressBar.setProgress(progress);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setProgressValue(progress + 15);
                }
            });
            thread.start();
        }
    }

    private void sharePreferenceVerification(){
        SharedPreferences sharedPref = getSharedPreferences("burguerReview", Context.MODE_PRIVATE);
        String username = sharedPref.getString("USERNAME","");
        String password = sharedPref.getString("PASSWORD","");

        if (username.equals("") && password.equals(""))
        {
            intent = new Intent(this,Login.class);
            boolsharedPreference = false;
        }else{
            intent = new Intent(this,Navigation.class);
            boolsharedPreference = true;
        }
    }


    private void saveHamburgerHouses(){
        hamburgerHouse.setName("Stone House Hamburgueria");
        hamburgerHouse.setAdress("Avenida Emilio ribas,665");
        hamburgerHouse.setStrongPoint("Batata apimentada");
        hamburgerHouse.setWeakPoint("Demora");
        hamburgerHouse.setNoteSnack((float)8/2);
        hamburgerHouse.setNoteSider((float)7/2);
        hamburgerHouse.setNoteAmbient((float)6/2);
        hamburgerHouse.setPriceRange((float)7/2);
        hamburgerHouse.setNotes("Melhorar demora no atendimento");

        hamburgerHouseRepository.saveHamburgerHouse(hamburgerHouse);

        hamburgerHouse.setName("Holy Burger");
        hamburgerHouse.setAdress("Dr Cesario Mota Junior, 527");
        hamburgerHouse.setStrongPoint("Tudo neste lugar é bom");
        hamburgerHouse.setWeakPoint("Espaço fisico pequeno");
        hamburgerHouse.setNoteSnack((float)9/2);
        hamburgerHouse.setNoteSider((float)8/2);
        hamburgerHouse.setNoteAmbient((float)6/2);
        hamburgerHouse.setPriceRange((float)6/2);
        hamburgerHouse.setNotes("Recomendo este lugar");

        hamburgerHouseRepository.saveHamburgerHouse(hamburgerHouse);

        hamburgerHouse.setName("Burger de Garagem");
        hamburgerHouse.setAdress("Rua Conego Valadao,885");
        hamburgerHouse.setStrongPoint("Otimo hamburger");
        hamburgerHouse.setWeakPoint("Ausencia de picles");
        hamburgerHouse.setNoteSnack((float)9/2);
        hamburgerHouse.setNoteSider((float)8/2);
        hamburgerHouse.setNoteAmbient((float)6/2);
        hamburgerHouse.setPriceRange((float)6/2);
        hamburgerHouse.setNotes("Bom custo beneficio");

        hamburgerHouseRepository.saveHamburgerHouse(hamburgerHouse);
    }
}
