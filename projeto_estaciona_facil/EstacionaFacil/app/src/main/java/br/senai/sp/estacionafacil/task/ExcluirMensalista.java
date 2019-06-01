package br.senai.sp.estacionafacil.task;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.senai.sp.estacionafacil.Ip;

public class ExcluirMensalista extends AsyncTask {


    private int id;

    public ExcluirMensalista (int id) {
        this.id = id;
    }

    @Override
    protected Object doInBackground(Object[] objects) {


        try {
            URL url = new URL("http://" + Ip.getIp() + ":8080/mensalista/" + id);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("DELETE");
            conexao.connect();
            conexao.getInputStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
