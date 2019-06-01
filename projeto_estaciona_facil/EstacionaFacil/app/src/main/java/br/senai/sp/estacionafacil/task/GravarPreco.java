package br.senai.sp.estacionafacil.task;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.estacionafacil.Ip;
import br.senai.sp.estacionafacil.model.Preco;

public class GravarPreco extends AsyncTask {

    private Preco preco;


    public  GravarPreco(Preco preco){
        this.preco = preco;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        JSONStringer jsPreco = new JSONStringer();

        try {

            jsPreco.object();
            jsPreco.key("valorPrimeiraHora").value(preco.getValorPrimeiraHora());
            jsPreco.key("valorDemaisHoras").value(preco.getValorDemaisHoras());
            jsPreco.key("tolerancia").value(preco.getTolerancia());
            jsPreco.endObject();

            URL url = new URL("http://" + Ip.getIp() + ":8080/preco");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(jsPreco);

            conexao.connect();
            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
