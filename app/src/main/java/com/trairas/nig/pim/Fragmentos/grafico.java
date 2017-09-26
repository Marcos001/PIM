package com.trairas.nig.pim.Fragmentos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.trairas.nig.pim.R;
import com.trairas.nig.pim.Util.Util;

import java.io.File;


public class grafico extends Fragment {

    Util u = new Util();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_grafico, container, false);

        ImageView img;

        String path_img = Environment.getExternalStorageDirectory()+"/grafico.png";

        File imgFile = new  File(path_img);

        try{
            if(imgFile.exists()){
                u.print("arquivo existe");
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                img = (ImageView) view.findViewById(R.id.imgv_grafico
                );
                img.setImageBitmap(myBitmap);
            }
            else{
                u.print("imagem grafico.png não existe");
            }
        }
        catch (Exception erro){
            u.print("Erro ao adicionar a imagem no imageView "+ erro);
        }


        return view;
    }

}
