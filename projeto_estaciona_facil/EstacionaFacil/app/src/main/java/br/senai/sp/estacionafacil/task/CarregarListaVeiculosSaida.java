package br.senai.sp.estacionafacil.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

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
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.estacionafacil.Estacionados;
import br.senai.sp.estacionafacil.Ip;
import br.senai.sp.estacionafacil.MainActivity;
import br.senai.sp.estacionafacil.SaidaEstacionamento;
import br.senai.sp.estacionafacil.model.Veiculo;


public class CarregarListaVeiculosSaida extends AsyncTask {

    private String dados="";
    private Context context;
    private ProgressDialog progressDialog;
    private List<Veiculo> veiculos;
    private ArrayAdapter<Veiculo> adapter;


    public CarregarListaVeiculosSaida(Context context){
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("...");
        progressDialog.setMessage("Carregando...");
        progressDialog.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://"+ Ip.getIp() +":8080/veiculo/saida");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream dadosStream = conexao.getInputStream();
            InputStreamReader leitorStream = new InputStreamReader(dadosStream);
            BufferedReader bufferedReader = new BufferedReader(leitorStream);
            String registro = "";

            while (registro != null){
                registro = bufferedReader.readLine();
                dados = dados + registro;
            }

            Veiculo veiculo;
            veiculos = new ArrayList<>();

            JSONArray dadosArray = new JSONArray(dados);
            for(int i=0; i<dadosArray.length(); i++ ){

                JSONObject jo = (JSONObject) dadosArray.get(i);
                veiculo = new Veiculo();
                veiculo.setId(jo.getInt("id"));
                veiculo.setHorarioEntrada(jo.getString("horarioEntrada"));
                veiculo.setHorarioSaida(jo.getString("horarioSaida"));
                veiculo.setModelo(jo.getString("modelo"));
                veiculo.setPlaca(jo.getString("placa"));
                //veiculo.setTempo(jo.getString("tempo"));
                //veiculo.setValorPago(jo.getString("valor_pago"));
                veiculos.add(veiculo);

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        adapter = new ArrayAdapter<Veiculo>(context, android.R.layout.simple_list_item_1, veiculos);
        SaidaEstacionamento.listaSaida.setAdapter(adapter);

        progressDialog.dismiss();

    }
}
