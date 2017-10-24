package com.trairas.nig.pim;

import com.trairas.nig.pim.Util.Arquivo;

import junit.framework.Test;
import junit.framework.TestCase;

import java.io.File;

/**
 * Created by nig on 24/10/17.
 */

public class Teste_Arquivo extends TestCase {

    Arquivo arquivo = new Arquivo();

    public void test_convert_bytes(){

        File file = null;

        assertNotNull(arquivo.converte_bytes(file));

    }

}
