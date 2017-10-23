package com.trairas.nig.pim;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

/**
 * Created by nig on 23/10/17.
 */

public class Test_About  extends ActivityInstrumentationTestCase2<about> {

    private about activity;
    private TextView texto_descricao;

    public Test_About() {
            super(about.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activity = getActivity();
        texto_descricao = (TextView)activity.findViewById(R.id.tv_about_description);

    }

    public void testDevePossuirUmTextViewDescription() throws Exception {

        assertNotNull(activity);
        assertNotNull(texto_descricao);
    }

}
