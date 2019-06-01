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
import br.senai.sp.estacionafacil.model.Mensalista;

public class GravarMensalista extends AsyncTask {

    private Mensalista mensalista;

    public GravarMensalista(Mensalista mensalista){
        this.mensalista = mensalista;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        JSONStringer jsMensalista = new JSONStringer();

        try {

            jsMensalista.object();
            jsMensalista.key("nome").value(mensalista.getNome());
            jsMensalista.key("celular").value(mensalista.getCelular());
            jsMensalista.key("cpf").value(mensalista.getCpf());
            jsMensalista.key("qtd_vagas").value(mensalista.getQuantidadeVagas());
            jsMensalista.endObject();

            URL url = new URL("http://" + Ip.getIp()+ ":8080/mensalista");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(jsMensalista);

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
