package com.trairas.nig.pim.Fragmentos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.trairas.nig.pim.R;
import com.trairas.nig.pim.Util.Util;
import com.trairas.nig.pim.connetion.Consumidor;

import java.io.File;


public class dg_md3 extends Fragment {


    Util u = new Util();

    public dg_md3() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dg_md3, container, false);

        ImageView img = (ImageView) view.findViewById(R.id.imgv_kmeans);


        File imgFile = new  File(getContext().getCacheDir()+"/kmeans.png");


        try{
            if(imgFile.exists()){

                u.print("Arquivo existe");

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                img.setImageBitmap(myBitmap);
            }
            else{
                u.print("imagem kmeans.png não existe");
            }
        }
        catch (Exception erro){
            u.print("Erro ao adicionar a imagem no imageView "+ erro);
        }


        return view;
    }


}
