package com.trairas.nig.pim.connetion;

/**
 * Created by mrv on 24/04/17.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.rabbitmq.client.*;
import com.trairas.nig.pim.Util.Arquivo;
import com.trairas.nig.pim.Util.DCompactar;

import java.io.IOException;

public class Consumidor {

    Arquivo arq = new Arquivo();
    DCompactar zip = new DCompactar();
    ProgressDialog progress;

    public void configDialog(Context contexto){
        progress = new ProgressDialog(contexto);
        progress.setTitle("Aguarde");
        progress.setMessage("Segmentando no Servidor");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCanceledOnTouchOutside(false);
        progress.show();
    }


    public boolean parar_progresso(){
        boolean retorno = false;
        try{
            progress.dismiss();
            retorno = true;
        }catch (Exception erro){
            retorno = false;
        }
        return retorno;
    }



    public Consumidor(final Activity main, final Context atividade, final String path_saida){

        final int PORT = Integer.getInteger("amqp.port", 5672);
        final String QUEUE_NAME = "server_phone";


        new Thread(new Runnable() {
            @Override
            public void run() {


                try{
                    ConnectionFactory factory = new ConnectionFactory();
                    factory.setHost("192.168.0.108");
                    factory.setPort(PORT);
                    factory.setUsername("nig");
                    factory.setPassword("nig");
                    factory.setVirtualHost("/");
                    Connection connection = factory.newConnection();
                    Channel channel = connection.createChannel();



                    channel.queueDeclare(QUEUE_NAME, false, false, false, null);

                    System.out.println(" [*] Aguardando mensagens. To exit press CTRL+C");

                    Consumer consumer = new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag,
                                                   Envelope envelope,
                                                   AMQP.BasicProperties properties,
                                                   byte[] body)
                                throws IOException {



                            //String message = new String(body, "UTF-8");
                            arq.criar_arquivo(atividade.getCacheDir()+"/tmp.zip", body);

                            zip.descompactar(atividade.getCacheDir()+"/tmp.zip", path_saida);

                            System.out.println(" [x] Received arquivo zip ");
                            if(parar_progresso()){
                                main.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(atividade, "Imagem Segmentada", Toast.LENGTH_SHORT).show();
                                        parar_progresso();
                                    }
                                });

                            }

                            System.out.println("Obtidos os dados");


                        }
                    };
                    channel.basicConsume(QUEUE_NAME, true, consumer);
                }catch (Exception erro){
                    System.out.println("NAO FOI - merda ao receber os arquivos > ");
                }

            }
        }).start();


    }


}
