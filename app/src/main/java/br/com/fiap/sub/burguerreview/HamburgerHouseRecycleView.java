package br.com.fiap.sub.burguerreview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.fiap.sub.burguerreview.adapters.HamburgerHouseHolder;
import br.com.fiap.sub.burguerreview.model.HamburgerHouse;


/**
 * A simple {@link Fragment} subclass.
 */
public class HamburgerHouseRecycleView extends Fragment {


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private HamburgerHouseHolder adapter;

    View view;

    List<HamburgerHouse> hamburgerHouseList;

    public HamburgerHouseRecycleView() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_hamburger_houser_recycle_view, container, false);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView = (RecyclerView) view.findViewById(R.id.hh_recycle_view);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new HamburgerHouseHolder();
        recyclerView.setAdapter(adapter);

        return view;
    }
}
