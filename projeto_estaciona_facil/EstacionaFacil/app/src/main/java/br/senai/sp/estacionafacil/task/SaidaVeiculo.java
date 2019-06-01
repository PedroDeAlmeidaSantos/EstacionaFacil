package br.senai.sp.estacionafacil.task;

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
import br.senai.sp.estacionafacil.model.Veiculo;

public class SaidaVeiculo extends AsyncTask {
    private Veiculo veiculo;

    public SaidaVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        JSONStringer jsVeiculo = new JSONStringer();

        try {
            jsVeiculo.object();
            jsVeiculo.key("modelo").value(veiculo.getModelo());
            jsVeiculo.key("placa").value(veiculo.getPlaca());
            jsVeiculo.key("horarioEntrada").value(veiculo.getHorarioEntrada());
            jsVeiculo.key("horarioSaida").value(veiculo.getHorarioSaida());
            jsVeiculo.key("valor").value(veiculo.getValorPago());
            jsVeiculo.key("id").value(veiculo.getId());
            jsVeiculo.endObject();

            URL url = new URL("http://"+ Ip.getIp() +":8080/veiculo/saida/" + veiculo.getId());


            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("PUT");

            conexao.setDoInput(true);

            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(jsVeiculo);

            conexao.connect();
            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
