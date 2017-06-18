package com.trairas.nig.pim.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.trairas.nig.pim.R;


public class fg_md2 extends Fragment {


    public fg_md2() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dg_md3, container, false);

        ImageView img = (ImageView) view.findViewById(R.id.imgv_otsu);

        return view;
    }


}
