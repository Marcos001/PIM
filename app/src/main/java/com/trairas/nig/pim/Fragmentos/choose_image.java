package com.trairas.nig.pim.Fragmentos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.trairas.nig.pim.R;


public class choose_image extends Fragment {


    private static int RESULT_LOAD_IMAGE = 1;
    Context atividade;

    Button bt_send;
    ImageView imgv_send;
    View view;

    public choose_image() {}

    public void _setContext(Context c) {

        this.atividade = c;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_choose_image, container, false);

        bt_send = (Button) view.findViewById(R.id.bt_send);

        imgv_send = (ImageView) view.findViewById(R.id.imgv_send);


        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        });
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = atividade.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

            String picturePath = cursor.getString(columnIndex);
            Log.v(" Fonte da imagem => \" ", picturePath+" \" end path");
            cursor.close();

            imgv_send.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            Log.v("-----> ", " Enviar imagem ao sevidor");

        }


}



}
