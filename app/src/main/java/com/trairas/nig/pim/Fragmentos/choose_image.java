package com.trairas.nig.pim.Fragmentos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.trairas.nig.pim.R;
import com.trairas.nig.pim.Util.Arquivo;
import com.trairas.nig.pim.Util.DCompactar;
import com.trairas.nig.pim.Util.Util;
import com.trairas.nig.pim.connetion.*;

import java.io.ByteArrayOutputStream;


public class choose_image extends Fragment {


    private static int RESULT_LOAD_IMAGE = 1;
    private String caminho_img = "";
    ProgressDialog progress;
    private static final int CAMERA_REQUEST = 1888;

    Button bt_send;
    Button bt_select_send;
    ImageView imgv_send;
    View view;

    Arquivo arq = new Arquivo();
    DCompactar zip = new DCompactar();
    Util u = new Util();
    Consumidor cu;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_choose_image, container, false);

        bt_send = (Button) view.findViewById(R.id.bt_send);
        bt_send.setText(R.string.bt_select);

        bt_select_send = (Button) view.findViewById(R.id.bt_select_send);
        bt_select_send.setText(R.string.bt_enviar);
        bt_select_send.setEnabled(false);

        imgv_send = (ImageView) view.findViewById(R.id.imgv_send);

        //cu = new Consumidor(getContext(), getContext().getCacheDir()+"");
        cu = new Consumidor(getActivity(), getContext(), Environment.getExternalStorageDirectory()+"");

        // ação do botao
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent i = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //inicia a galeria
                //startActivityForResult(i, RESULT_LOAD_IMAGE);

                String fileName = "temp.jpg";
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, fileName);
                ContentProvider mCapturedImageURI = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);

            }
        });

        bt_select_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
            }
        });


        return view;
    }

    private void enviar(){
        //convertendo a imagem em bytes

        cu.configDialog(getContext());

        u.print("Caminho em enviar = "+caminho_img);

        byte[] imagem = arq.converte_bytes(arq.ler_arquivo(caminho_img));

        String name_file_zip = getContext().getCacheDir()+"/pim_imagem.zip";

        zip.compactar("pim_imagem.png", name_file_zip, imagem);

        u.print("salvando em : "+name_file_zip);

        //converter o zip em bytes

        byte[] bytes_zip = arq.converte_bytes(arq.ler_arquivo(name_file_zip));

        // Add permission for othres threads
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // send zip with photos
        new Produtor(bytes_zip);
        u.print("imagem enviada com sucesso!");
    }




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // convert byte array to Bitmap

            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

            //Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgv_send.setImageBitmap(bitmap);



        }

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {

            Uri selectedImage = data.getData();

            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

            String picturePath = cursor.getString(columnIndex);
            Log.v(" Fonte da imagem => \" ", picturePath+" \" end path");
            cursor.close();

            imgv_send.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            Log.v("-----> ", " Enviar imagem ao sevidor");
            caminho_img = "";
            caminho_img = picturePath;

            bt_select_send.setEnabled(true);



            //-----------------------------------------------------------------------///////

        }


}



}
