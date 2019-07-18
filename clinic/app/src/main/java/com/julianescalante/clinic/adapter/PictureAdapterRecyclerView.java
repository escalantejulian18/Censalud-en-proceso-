package com.julianescalante.clinic.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.julianescalante.clinic.R;
import com.julianescalante.clinic.model.Picture;
import com.julianescalante.clinic.view.Mapa;
import com.julianescalante.clinic.view.PictureDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

// El adapter recibe una coleccion de objetos
public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder>{

    // Estas tres variables manejan los elementos de objetos pictures
    private ArrayList<Picture> pictures;
    //aqui pasamos como paramentro el obejto activity
    private  int resource;
    private Activity activity;


    // Generamos el constructor de estos tres elementos
    public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, FragmentActivity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }

    /// - > Se implementan todos los metodos, estos son obligatorios
    @Override
    public  PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inicializamos la clase viewHolder paa que encuentre todos las vistas
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder( PictureViewHolder holder, int position) {

        // Aqui trabajamos con la lista de objetos, va ir cacheando las cards que se van creando
        final Picture picture = pictures.get(position);
        // a travez del objeto holder, obtengo los datos
        holder.centernameCard.setText(picture.getCenterName());
        holder.addressCard.setText(picture.getAddress());
        //holder.infoCard.setText(picture.getInfo());


        //Modificamos el contexto, y llamamos a picture para insertar laas imagenes desde nuestra card
        Picasso.with(activity).load(picture.getPicture()).into(holder.pictureCard);

        holder.pictureCard.setOnClickListener(new View.OnClickListener() { //Implementamos el metodo onClick
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PictureDetailActivity.class); //Le pasomos el contexto de la actividad
                intent.putExtra("nombre", picture.getCenterName().toString());
                intent.putExtra("latitude", picture.getLatitud());
                intent.putExtra("longitude", picture.getLongitud());
                activity.startActivity(intent);

            }
        });
    }

    @Override
    // Devuelve el conteo de cuantas cards tengo
    public int getItemCount() {
        return pictures.size();
    }

    // Esta clase  PictureViewHolder trabaja con todos los view de las cardView que la componen
    public class PictureViewHolder extends RecyclerView.ViewHolder{


        // Encapsulamos los objetos
        private ImageView pictureCard;
        private TextView centernameCard;
        private TextView addressCard;
        //private TextView infoCard;


        public PictureViewHolder(View itemView) {
            super(itemView);

            // Instanciamos sobre las clases inners

            pictureCard    =   itemView.findViewById(R.id.pictureCard);
            centernameCard = itemView.findViewById(R.id.centerNameCard);
            addressCard =  itemView.findViewById(R.id.addressCard);
            //infoCard =  itemView.findViewById(R.id.infoCard);


        }
    }
}

