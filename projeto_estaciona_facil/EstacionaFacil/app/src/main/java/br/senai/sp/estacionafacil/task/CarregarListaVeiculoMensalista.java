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

import br.senai.sp.estacionafacil.Ip;
import br.senai.sp.estacionafacil.ListaMensalista;
import br.senai.sp.estacionafacil.ListaVeiculosMensalista;
import br.senai.sp.estacionafacil.model.Mensalista;
import br.senai.sp.estacionafacil.model.Veiculo;
import br.senai.sp.estacionafacil.model.VeiculoMensalista;

public class CarregarListaVeiculoMensalista extends AsyncTask {

    private String dados="";
    private Context context;
    private int id;
    private ProgressDialog progressDialog;
    private List<VeiculoMensalista> veiculoMensalistas;
    private Mensalista mensalista;
   // private VeiculoMensalista veiculoMensalistas;
    private ArrayAdapter<VeiculoMensalista> adapter;


    public CarregarListaVeiculoMensalista(Context context, int id){
        this.context = context;
        this.id = id;
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
            URL url = new URL("http://"+ Ip.getIp() +":8080/veiculomensalista/mensalista/" + id);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream dadosStream = conexao.getInputStream();
            InputStreamReader leitorStream = new InputStreamReader(dadosStream);
            BufferedReader bufferedReader = new BufferedReader(leitorStream);
            String registro = "";

            while (registro != null){
                registro = bufferedReader.readLine();
                dados = dados + registro;
            }

            VeiculoMensalista veiculoMensalista;
            veiculoMensalistas = new ArrayList<>();

            JSONArray dadosArray = new JSONArray(dados);
            for(int i=0; i<dadosArray.length(); i++ ){

                JSONObject jo = (JSONObject) dadosArray.get(i);
                veiculoMensalista = new VeiculoMensalista();
                veiculoMensalista.setId(jo.getInt("id"));
                veiculoMensalista.setPlaca(jo.getString("placa"));
                veiculoMensalista.setModelo(jo.getString("modelo"));
                veiculoMensalista.setIdMensalista(jo.getString("idMensalista"));
                veiculoMensalistas.add(veiculoMensalista);


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

        adapter = new ArrayAdapter<VeiculoMensalista>(context, android.R.layout.simple_list_item_1, veiculoMensalistas);
        ListaVeiculosMensalista.listaVeiculoMensalistas.setAdapter(adapter);

        progressDialog.dismiss();

    }
}
