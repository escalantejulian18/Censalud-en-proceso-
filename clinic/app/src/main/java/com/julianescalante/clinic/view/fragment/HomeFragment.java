package com.julianescalante.clinic.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.julianescalante.clinic.R;
import com.julianescalante.clinic.adapter.PictureAdapterRecyclerView;
import com.julianescalante.clinic.model.Picture;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar("Home", false, view);

        // Instancimos la clase de RecylcerView
        RecyclerView picturesRecycler =  view.findViewById(R.id.pictureRecycler);

        // pasamos a la zona verde -- pasamos el contexto
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        // ADAPTER // pasamos el adapter
        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buidPictures(),R.layout.cardview_picture, getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        return view;
    }

    // creamos un metodo constructor para el arraylist de pictures
    public ArrayList<Picture>buidPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://i0.wp.com/genesis24.net/wp-content/uploads/2017/05/Tematica_HospitalUrquizaCDU.jpg?zoom=1.25&resize=696%2C392&ssl=1","Hospital J.J. de Urquiza","Lorenzo P. Sartorio 2130", -32.4773196,-58.248289399999976));
        pictures.add(new Picture("http://infoner.com.ar/wp-content/uploads/2018/10/Cooperativa-M%C3%A9dica-Concepci%C3%B3n-del-Uruguay-1.jpg","Cooperativa America","14 de Julio 377",-32.4848294,-58.2369554));
        pictures.add(new Picture("https://lh5.googleusercontent.com/p/AF1QipNZbmyifwGtuPI18SV1WDcNOpt4Drcd719CC5nQ=w408-h544-k-no","Centro Médico","14 de Julio 378",-32.480575, -58.23392560000002));
        pictures.add(new Picture("https://media.ahora.com.ar/adjuntos/226/imagenes/002/064/0002064211.jpg","Centro de Salud Dr. Giacomotti","Viejo Hospital",-32.477276,-58.22163899999998));
        pictures.add(new Picture("https://lh5.googleusercontent.com/p/AF1QipPblSK_XDALo2bFJ85lZbQAsIxAXS49A4lgxgrt=w408-h725-k-no","Cedyt SRL","Eva Duarte de Perón 181",-32.4799794,-58.23535679999998));
        return pictures;
    }

    // Este toolbar esta en soporte y a su vez es distinto porque  estamos en contexto de un Fragment
    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
