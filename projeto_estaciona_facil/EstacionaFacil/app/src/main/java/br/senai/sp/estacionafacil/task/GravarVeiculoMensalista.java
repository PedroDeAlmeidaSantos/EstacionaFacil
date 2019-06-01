package br.senai.sp.estacionafacil.task;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import br.senai.sp.estacionafacil.Ip;
import br.senai.sp.estacionafacil.model.Mensalista;
import br.senai.sp.estacionafacil.model.Veiculo;
import br.senai.sp.estacionafacil.model.VeiculoMensalista;

public class GravarVeiculoMensalista extends AsyncTask {

    private VeiculoMensalista veiculoMensalista;
    private int id;

    public GravarVeiculoMensalista(VeiculoMensalista veiculoMensalista, int id){
        this.veiculoMensalista = veiculoMensalista;
        this.id = id;

    }

    @Override
    protected Object doInBackground(Object[] objects) {

        JSONStringer jsVeiculoMensalista = new JSONStringer();

        try {
            jsVeiculoMensalista.object();
            jsVeiculoMensalista.key("modelo").value(veiculoMensalista.getModelo());
            jsVeiculoMensalista.key("placa").value(veiculoMensalista.getPlaca());
            jsVeiculoMensalista.key("idMensalista").value(id);
            jsVeiculoMensalista.endObject();

            URL url = new URL("http://" + Ip.getIp() +":8080/veiculomensalista");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(jsVeiculoMensalista);

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
