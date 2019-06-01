package br.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import br.senai.sp.estacionafacil.model.Mensalista;
import br.senai.sp.estacionafacil.model.VeiculoMensalista;
import br.senai.sp.estacionafacil.task.CarregarListaVeiculoMensalista;
import br.senai.sp.estacionafacil.task.GravarVeiculoMensalista;

public class CadastroVeiculoMensalistaActivity extends AppCompatActivity {

    private ImageButton btnSalvar;
    private EditText txtPlaca;
    private EditText txtModelo;
    Mensalista mensalista = new Mensalista();
    VeiculoMensalista veiculoMensalista = new VeiculoMensalista();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculo_mensalista);

        btnSalvar = findViewById(R.id.btn_salvar_veiculo_mensalista);
        txtModelo = findViewById(R.id.txt_modelo_mensalista);
        txtPlaca = findViewById(R.id.txt_placa_mensalista);


        Intent intent = getIntent();
        if(intent.getSerializableExtra("veiculo_mensalista") != null) {

           mensalista = (Mensalista) intent.getSerializableExtra("veiculo_mensalista");
             int id = mensalista.getId();

        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //preco.setValorDemaisHoras(Double.valueOf(txtDemaisHoras.getText().toString()));
                veiculoMensalista.setModelo(txtModelo.getText().toString());
                veiculoMensalista.setPlaca(txtPlaca.getText().toString());

                GravarVeiculoMensalista gravarVeiculoMensalista = new GravarVeiculoMensalista(veiculoMensalista, mensalista.getId());
                gravarVeiculoMensalista.execute();
                finish();

            }
        });

    }
}
