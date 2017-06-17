package com.trairas.nig.pim.connetion;

/**
 * Created by mrv on 24/04/17.
 */

import com.rabbitmq.client.*;
import java.io.IOException;

public class Consumidor {

    private final static String QUEUE_NAME = "hello";

    public Consumidor(){

        new Thread(new Runnable() {
            @Override
            public void run() {


                try{
                    ConnectionFactory factory = new ConnectionFactory();
                    factory.setHost("localhost");
                    Connection connection = factory.newConnection();
                    Channel channel = connection.createChannel();

                    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                    System.out.println(" [*] Aguardando mensagens. To exit press CTRL+C");

                    Consumer consumer = new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                                throws IOException {
                            String message = new String(body, "UTF-8");

                            String ip = "";
                            String msg = "";
                            String tmp = "";

                            for(int i = 0; i < message.length(); i++){

                                if(message.charAt(i)==':'){
                                    ip = tmp;
                                    tmp = "";
                                }

                                else if(message.charAt(i)=='§'){
                                    msg = tmp;
                                    tmp = "";
                                }

                                else if(message.charAt(i)!=':'){
                                    tmp += message.charAt(i);
                                }
                            }

                            System.out.println("IP = "+ip+"\n"+"Nome = "+msg);
                            System.out.println(" [x] Received '" + message + "'");

                            System.out.println("Obtidos os dados");
                            final String finalMsg = msg;
                            final String finalIp = ip;

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    new Produtor(finalIp, finalMsg);
                                    new Produtor(finalMsg);
                                }
                            }).start();
                            System.out.println("mensagem Enviada "+msg+" a "+ip);

                        }
                    };
                    channel.basicConsume(QUEUE_NAME, true, consumer);
                }catch (Exception erro){
                    System.out.println("NAO FOI");
                }

            }
        }).start();

    }

    public Consumidor(byte[] fileData){

        new Thread(new Runnable() {
            @Override
            public void run() {


                try{
                    ConnectionFactory factory = new ConnectionFactory();
                    factory.setHost("localhost");
                    Connection connection = factory.newConnection();
                    Channel channel = connection.createChannel();

                    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                    System.out.println(" [*] Aguardando mensagens. To exit press CTRL+C");

                    Consumer consumer = new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                                throws IOException {

                            String message = new String(body, "UTF-8");


                            System.out.println(" [x] Received '" + message + "'");

                            System.out.println("Obtidos os dados");


                        }
                    };

                }catch (Exception erro){
                    System.out.println("NAO FOI");
                }

            }
        }).start();

    }




}
