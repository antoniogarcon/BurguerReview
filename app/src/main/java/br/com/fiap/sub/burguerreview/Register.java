package br.com.fiap.sub.burguerreview;


import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.fiap.sub.burguerreview.model.HamburgerHouse;
import br.com.fiap.sub.burguerreview.repository.HamburgerHouseRepository;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmException;
import io.realm.exceptions.RealmIOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {


    EditText ed_name, ed_adress, ed_strongPoint, ed_weakPoint;
    EditText ed_snackNote, ed_siderNote, ed_ambientNote, ed_priceRange;
    EditText ed_note;
    TextView tv_title_fragment;
    Button bt_register, bt_cancel;

    View view;

    HamburgerHouse hamburgerHouse;
    HamburgerHouseRepository hamburgerHouseRepository;

    String action;
    String nameHamburgerHouse;

    public Register() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);

        initialize();

        action = getArguments().getString("Action");
        nameHamburgerHouse = getArguments().getString("hamburgerHouseName");

//        if(action.equals("register")){
//
//        }
//        else{
//            bt_register.setText("update");
//
//            carregaHamburgerHouseInfo(nameHamburgerHouse);
//        }


        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    hamburgerHouse.setName(ed_name.getText().toString());
                    hamburgerHouse.setAdress(ed_adress.getText().toString());
                    hamburgerHouse.setStrongPoint(ed_strongPoint.getText().toString());
                    hamburgerHouse.setWeakPoint(ed_weakPoint.getText().toString());
                    hamburgerHouse.setNoteSnack(Float.parseFloat(ed_snackNote.getText().toString())/2);
                    hamburgerHouse.setNoteSider(Float.parseFloat(ed_siderNote.getText().toString())/2);
                    hamburgerHouse.setNoteAmbient(Float.parseFloat(ed_ambientNote.getText().toString())/2);
                    hamburgerHouse.setPriceRange(Float.parseFloat(ed_priceRange.getText().toString())/2);
                    hamburgerHouse.setNotes(ed_note.getText().toString());

                    hamburgerHouseRepository.saveHamburgerHouse(hamburgerHouse);

                    view.setVisibility(View.GONE);
                }catch (SQLiteException e){
                    Log.i("Error: ",String.valueOf(e));
                }
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void initialize(){

        ed_name = (EditText) view.findViewById(R.id.ed_register_name) ;
        ed_adress = (EditText) view.findViewById(R.id.ed_register_adress);
        ed_strongPoint = (EditText) view.findViewById(R.id.ed_register_strong_point);
        ed_weakPoint = (EditText) view.findViewById(R.id.ed_register_weak_point);
        ed_snackNote = (EditText) view.findViewById(R.id.ed_register_snack_note);
        ed_siderNote = (EditText) view.findViewById(R.id.ed_register_sider_note);
        ed_ambientNote = (EditText)view.findViewById(R.id.ed_register_ambient_note);
        ed_priceRange = (EditText)view.findViewById(R.id.ed_register_price_range);
        ed_note = (EditText) view.findViewById(R.id.ed_register_note);

        tv_title_fragment = (TextView) view.findViewById(R.id.tv_fragment_title);

        bt_register = (Button) view.findViewById(R.id.bt_register_add);
        bt_cancel = (Button) view.findViewById(R.id.bt_register_cancel);

        hamburgerHouse = new HamburgerHouse();
        hamburgerHouseRepository = new HamburgerHouseRepository(view.getContext());
    }

    private boolean validationData(){

        return true;
    }


    public void register(View view){


    }

}
