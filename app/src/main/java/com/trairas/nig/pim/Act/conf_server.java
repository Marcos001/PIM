package com.trairas.nig.pim.Act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.trairas.nig.pim.R;
import com.trairas.nig.pim.Util.OperArquivos;

public class conf_server extends AppCompatActivity {

    EditText ed_ip;
    EditText ed_id;
    Button bt_1;

    private static String FILE_IP = "file_ip.txt";
    private static String FILE_ID = "file_id.txt";

    OperArquivos opr = new OperArquivos();


    private boolean val_char(char letra, String val){
        for (int i=0;i<val.length();i++){
            if(letra == val.charAt(i)){
                return true;
            }
        }
        return false;
    }

    private String getIP(){

        String ip;

        try{
            ip = opr.ler(conf_server.this, FILE_IP);
        }catch (Exception erro){
            Log.v("<----:)","Erro ao obter dados do arquivo "+FILE_IP);
            opr.salvar("192.168.0.109", getApplicationContext(), FILE_IP);
            ip = "192.168.0.109";
        }

        return ip;}

    private boolean val_string(String src, String val){
        for(int i=0;i<src.length();i++){
            if(!val_char(src.charAt(i), val)){
                return false;
            }
        }
        return true;
    }

    private void change_IP(String ip){
        if(ip.length() > 0){
            if (val_string(ip, "1234567890.")){
                Toast.makeText(conf_server.this, "Dados Salvos Com sucesso", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(conf_server.this, "Dados inválidos", Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_conf_server);

        ed_ip = (EditText) findViewById(R.id.ed_ip);
        ed_ip.setText(getIP());
        ed_id = (EditText) findViewById(R.id.ed_id);
        bt_1 = (Button) findViewById(R.id.bt_altear);

        bt_1.setText("Alterar");

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_IP(ed_ip.getText().toString());
            }
        });


    }
}
