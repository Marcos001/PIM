package com.trairas.nig.pim.connetion;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.trairas.nig.pim.Util.Util;


/**
 * Created by mrv on 24/04/17.
 */
public class Produtor extends Util {

    private final static String QUEUE_NAME = "phone_server"; //"hello"
    static final int PORT = Integer.getInteger("amqp.port", 5672);

    static final String HOST = System.getProperty("amqp.host", "192.168.0.104");
    static final String EXCHANGE = System.getProperty("amqp.exchange", "systemExchange");
    static final String ENCODING = "UTF-8";

    Connection connection;

    public Produtor(byte[] fileData){


        try{

            print(" configuration connection");
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(HOST);
            factory.setPort(PORT);
            factory.setUsername("nig");
            factory.setPassword("nig");
            factory.setVirtualHost("/");
            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            print(" sending message > ");
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, fileData);
            System.out.println(" [x] Sent file ");

            print("closing connection > ");
            channel.close();
            connection.close();


        }catch (Exception erro){
            System.out.println("Erro ao instanciar publisher enviando ao cliente >"+HOST+" \n"+erro);
        }


    }



    public void _Produtor(String ip, String fuxico){

        Connection connection;

        System.out.println("Querendo Publicar com IP = "+ip);

       String HOST = System.getProperty("amqp.host", ip);

        try{

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(HOST);
            factory.setPort(PORT);
            factory.setUsername("nig");
            factory.setPassword("nig");
            factory.setVirtualHost("/");
            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, fuxico.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + fuxico + "'");

            channel.close();
            connection.close();


        }catch (Exception erro){
            System.out.println("Erro ao instanciar publisher enviando ao cliente >"+HOST+" \n"+erro);
        }


    }


    public void Produtor_send_message(String message){


        String HOST = System.getProperty("amqp.host", "192.168.0.106");

        try{

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(HOST);
            factory.setPort(PORT);
            factory.setUsername("nig");
            factory.setPassword("nig");
            factory.setVirtualHost("/");
            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");

            channel.close();
            connection.close();


        }catch (Exception erro){
            System.out.println("Erro ao instanciar publisher enviando ao cliente >"+HOST+" \n"+erro);
        }


    }


}
