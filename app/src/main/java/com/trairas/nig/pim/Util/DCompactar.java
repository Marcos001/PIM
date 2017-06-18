package com.trairas.nig.pim.Util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by nig on 01/06/17.
 */
public class DCompactar extends Util{



    public DCompactar(){

    }

    public void compactar(String path_arquivo, String nome_arquivo_zip, byte[] conteudo_file){

        try{
            print("0%");
            FileOutputStream file_output = new FileOutputStream(nome_arquivo_zip);
            print("25%");
            ZipOutputStream inst_zip = new ZipOutputStream(file_output);
            print("50%");
            inst_zip.putNextEntry(new ZipEntry(path_arquivo));
            print("75%");
            inst_zip.write(conteudo_file);

            inst_zip.closeEntry();
            inst_zip.close();

            print("100% \nzip criado com sucesso!");

        }catch (Exception erro){
            print("erro ao compactar o arquivo");
        }
    }


    /***---------------------------------------------------------------------------**/

    public void zip_pasta(String pasta_origem, String destino_zip){

        try{
            print("p0");
            FileOutputStream file_writer = new FileOutputStream(destino_zip); //cria o zip
            print("p1");
            ZipOutputStream zip = new ZipOutputStream(file_writer);
            print("p2");
            adicionar_pasta_para_zip("", pasta_origem, zip);
            print("p3");
            zip.flush();
            zip.close();
            print("p4");
            print("pasta zipada com sucesso");

        }catch (Exception erro){
            print("Erro ao zipar a pasta");
        }

    }

    public void adicionar_arquivos_para_zipar(String path, String origem_arquivo, ZipOutputStream zip){

        try{

            File folder = new File(origem_arquivo);

            if (folder.isDirectory()){
                adicionar_pasta_para_zip(path, origem_arquivo, zip);
            }
            else{
                byte[] buf = new byte[1024];
                int len;

                FileInputStream entrada = new FileInputStream(origem_arquivo);

                zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));

                while ((len = entrada.read(buf)) > 0){
                    zip.write(buf, 0, len);
                }

            }

        }catch (Exception erro){
            print("erro ao adicionar arquivos para zipar");
        }

    }


    public void adicionar_pasta_para_zip(String path, String pasta_origem, ZipOutputStream zip){

        try{

            File pasta = new File(pasta_origem);

            for (String file_name : pasta.list() ){
                if(path.equals("")){
                    adicionar_arquivos_para_zipar(pasta.getName(), pasta_origem+"/"+file_name, zip );
                }
                else{
                    adicionar_arquivos_para_zipar(path+"/"+pasta.getName(), pasta_origem+"/", zip);
                }
            }

        }catch (Exception erro){
            print("Erro ao adicionar os arquivos ao zip > "+erro);
        }

    }


    public void descompactar(String path_file_zip, String path_saida){

        byte[] buffer = new byte[1024];

        try{

            ZipInputStream zinstream = new ZipInputStream(new FileInputStream(path_file_zip));

            ZipEntry entry = zinstream.getNextEntry();

            while(entry != null){
                //pega o nome da entrada
                String name = entry.getName();

                //cria o otput do arquivo
                print("Descompactando arquivo = "+ name);
                FileOutputStream saida = new FileOutputStream(path_saida+"/"+name);

                int n;

                //escreve no arquivo

                while ((n = zinstream.read(buffer))> -1){
                    saida.write(buffer, 0, n);
                }

                //fecha o arquivo

                saida.close();

                //fecha a entrada e tenta pegar a proxima

                zinstream.closeEntry();
                entry = zinstream.getNextEntry();

            }

        }catch (Exception erro){
            print("Erro ao descompactar os arquivos > "+erro);
        }


    }

}
