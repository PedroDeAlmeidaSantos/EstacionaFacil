package br.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.estacionafacil.model.Veiculo;
import br.senai.sp.estacionafacil.task.AtualizarVeiculo;
import br.senai.sp.estacionafacil.task.CarregarListaEstacionados;
import br.senai.sp.estacionafacil.task.CarregarListaVeiculos;
import br.senai.sp.estacionafacil.task.CarregarSaida;
import br.senai.sp.estacionafacil.task.GravarVeiculo;
import br.senai.sp.estacionafacil.task.SaidaVeiculo;


public class CadastrarMovimentoActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener  {

    private ImageButton btnSair;
    private ImageButton btnSalvar;
    private EditText txtPlaca;
    private EditText txtModelo;
    private Toolbar toolbar;
    private TextView txtEntrada;
    private TextView txtSaida;
    private TextView txtValorPago;
    Veiculo veiculo = new Veiculo();
    private Spinner spinner;
    private TextInputLayout layoutModelo;
    private TextInputLayout layoutPlaca;
   // private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        final Spinner spinner = (Spinner) findViewById(R.id.tipo_veiculo_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_veiculo_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       /* spinner.setAdapter(adapter); */


        /*List<String> linguagens = new ArrayList<>(Arrays.asList("Java","Python","PHP","Ruby"));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, linguagens );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/

        //spinner.setAdapter(dataAdapter);

        setContentView(R.layout.activity_cadastrar_movimento);

        btnSair = findViewById(R.id.btn_sair);
        btnSalvar = findViewById(R.id.btn_salvar);
        txtPlaca = findViewById(R.id.txt_placa);
        txtModelo = findViewById(R.id.txt_modelo);
        layoutModelo = findViewById(R.id.layout_txt_modelo);
        layoutPlaca = findViewById(R.id.layout_txt_placa);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        if(intent.getSerializableExtra("veiculo") != null) {

            veiculo = (Veiculo) intent.getSerializableExtra("veiculo");
            txtPlaca.setText(veiculo.getPlaca());
            txtModelo.setText(veiculo.getModelo());
           // txtEntrada.setText(veiculo.getHorarioEntrada());
           // txtSaida.setText(veiculo.getHorarioSaida());


        }


        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //veiculo.setPlaca(txtPlaca.getText().toString());
                //veiculo.setModelo(txtModelo.getText().toString());
                //veiculo.setHorarioEntrada(txtEntrada.getText().toString());
                //veiculo.setHorarioSaida(txtSaida.getText().toString());

                SaidaVeiculo saida = new SaidaVeiculo(veiculo);
                saida.execute();

                CarregarSaida carregarSaida = new CarregarSaida(veiculo);
                carregarSaida.execute();

                try {
                    veiculo = (Veiculo) carregarSaida.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent saidaVeiculo = new Intent(CadastrarMovimentoActivity.this, Saida.class);
                saidaVeiculo.putExtra("veiculo", veiculo);
                startActivity(saidaVeiculo);


            }
        });


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                veiculo.setPlaca(txtPlaca.getText().toString());
                veiculo.setModelo(txtModelo.getText().toString());
          //      veiculo.setHorarioEntrada(txtEntrada.getText().toString());


                if(veiculo.getId() == 0){

                    if(validar() == true){
                        GravarVeiculo gravarVeiculo = new GravarVeiculo(veiculo);
                        gravarVeiculo.execute();
                        Toast.makeText(CadastrarMovimentoActivity.this, "Veículo adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                }else{
                    if(validar() == true){
                        AtualizarVeiculo atualizarVeiculo = new AtualizarVeiculo(veiculo);
                        atualizarVeiculo.execute();
                        Toast.makeText(CadastrarMovimentoActivity.this, "Veículo atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                }


            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) findViewById(R.id.tipo_veiculo_spinner);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean validar(){
        boolean validado = true;
        if(txtModelo.getText().toString().isEmpty()){
            layoutModelo.setErrorEnabled(true);
            layoutModelo.setError("Por favor digite um modelo");
            validado = false;
        }else{
            layoutModelo.setErrorEnabled(false);
        }
        if(txtPlaca.getText().toString().isEmpty()){
            layoutPlaca.setErrorEnabled(true);
            layoutPlaca.setError("Por favor digite uma placa");
            validado = false;
        }else{
            layoutPlaca.setErrorEnabled(false);
        }
        return validado;
    }


}
