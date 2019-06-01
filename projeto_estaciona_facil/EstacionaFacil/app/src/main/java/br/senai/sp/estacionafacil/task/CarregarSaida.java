package br.senai.sp.estacionafacil.task;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.senai.sp.estacionafacil.Ip;
import br.senai.sp.estacionafacil.model.Veiculo;

public class CarregarSaida extends AsyncTask{

    private Veiculo veiculo;
    private String dados = "";


    public CarregarSaida (Veiculo veiculo){
        this.veiculo = veiculo;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://"+ Ip.getIp()+ ":8080/veiculo/" + veiculo.getId());

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream dadosStream = conexao.getInputStream();
            InputStreamReader leitorStream = new InputStreamReader(dadosStream);
            BufferedReader bufferedReader = new BufferedReader(leitorStream);
            String registro = "";


            registro = bufferedReader.readLine();

            JSONObject jo = new JSONObject(registro);

            veiculo = new Veiculo();
            veiculo.setId(jo.getInt("id"));
            veiculo.setHorarioEntrada(jo.getString("horarioEntrada"));
            veiculo.setHorarioSaida(jo.getString("horarioSaida"));
            veiculo.setModelo(jo.getString("modelo"));
            veiculo.setPlaca(jo.getString("placa"));
            //veiculo.setTempo(jo.getString("tempo"));
            veiculo.setValorPago(jo.getDouble("valor"));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return veiculo;
    }

}
