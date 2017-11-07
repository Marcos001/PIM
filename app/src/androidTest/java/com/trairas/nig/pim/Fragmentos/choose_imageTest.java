package com.trairas.nig.pim.Fragmentos;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nig on 25/10/17.
 */
public class choose_imageTest extends TestCase {

    public void testinstancia(){
        choose_image esolher_imagem = new choose_image();
        assertNotNull(esolher_imagem.zip);
        assertNotNull(esolher_imagem.u);
        assertNotNull(esolher_imagem.opr);
    }

    @Before
    public void setUp() throws Exception {

    }


}