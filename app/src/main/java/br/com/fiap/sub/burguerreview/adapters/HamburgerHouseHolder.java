package br.com.fiap.sub.burguerreview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.sub.burguerreview.R;
import br.com.fiap.sub.burguerreview.model.HamburgerHouse;
import br.com.fiap.sub.burguerreview.repository.HamburgerHouseRepository;
import br.com.fiap.sub.burguerreview.utils.Database;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import io.realm.exceptions.RealmIOException;

/**
 * Created by lsitec91.garcon on 16/05/2017.
 */

public class HamburgerHouseHolder extends RecyclerView.Adapter<HamburgerHouseHolder.MyViewHolder> {

    View view;

    Database database;

    HamburgerHouse tmp, hamburgerHouse;
    HamburgerHouseRepository hamburgerHouseRepository;

    ArrayList<HamburgerHouse> hamburgerHouseArrayList;


    @Override
    public HamburgerHouseHolder.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item, parent, false);

        database = new Database(view.getContext());
        hamburgerHouse = new HamburgerHouse();
        hamburgerHouseArrayList = new ArrayList<>();

        saveHamburgerHouses();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HamburgerHouseHolder.MyViewHolder holder, int position) {

        try{
            holder.img.setImageResource(R.drawable.hamburger_gray);
            holder.tv_name.setText(hamburgerHouseArrayList.get(position).getName());
            holder.rb_snack.setRating( hamburgerHouseArrayList.get(position).getNoteSnack());
            holder.rb_ambient.setRating(hamburgerHouseArrayList.get(position).getNoteAmbient());
            holder.rb_price.setRating(hamburgerHouseArrayList.get(position).getPriceRange());
        }catch (NullPointerException e){
            e.printStackTrace();
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {

        return hamburgerHouseArrayList.size();

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView tv_name;
        RatingBar rb_snack, rb_price, rb_ambient;

        public MyViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.item_img);
            tv_name = (TextView) itemView.findViewById(R.id.item_name);
            rb_snack = (RatingBar) itemView.findViewById(R.id.rb_snack_note);
            rb_ambient = (RatingBar) itemView.findViewById(R.id.rb_ambient_note);
            rb_price = (RatingBar) itemView.findViewById(R.id.rb_price_range);
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

        hamburgerHouseArrayList.add(hamburgerHouse);

        hamburgerHouse =new HamburgerHouse();

        hamburgerHouse.setName("Holy Burger");
        hamburgerHouse.setAdress("Dr Cesario Mota Junior, 527");
        hamburgerHouse.setStrongPoint("Tudo neste lugar é bom");
        hamburgerHouse.setWeakPoint("Espaço fisico pequeno");
        hamburgerHouse.setNoteSnack((float)9/2);
        hamburgerHouse.setNoteSider((float)8/2);
        hamburgerHouse.setNoteAmbient((float)6/2);
        hamburgerHouse.setPriceRange((float)6/2);
        hamburgerHouse.setNotes("Recomendo este lugar");

        hamburgerHouseArrayList.add(hamburgerHouse);

        hamburgerHouse =new HamburgerHouse();
        hamburgerHouse.setName("Burger de Garagem");
        hamburgerHouse.setAdress("Rua Conego Valadao,885");
        hamburgerHouse.setStrongPoint("Otimo hamburger");
        hamburgerHouse.setWeakPoint("Ausencia de picles");
        hamburgerHouse.setNoteSnack((float)9/2);
        hamburgerHouse.setNoteSider((float)8/2);
        hamburgerHouse.setNoteAmbient((float)6/2);
        hamburgerHouse.setPriceRange((float)6/2);
        hamburgerHouse.setNotes("Bom custo beneficio");

        hamburgerHouseArrayList.add(hamburgerHouse);

        Log.i("tamanho: ",String.valueOf(hamburgerHouseArrayList.size()));
    }

}