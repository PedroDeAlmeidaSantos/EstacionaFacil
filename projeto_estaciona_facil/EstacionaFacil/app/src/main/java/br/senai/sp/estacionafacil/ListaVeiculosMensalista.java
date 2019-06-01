package br.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import br.senai.sp.estacionafacil.model.Mensalista;
import br.senai.sp.estacionafacil.model.VeiculoMensalista;
import br.senai.sp.estacionafacil.task.CarregarListaVeiculoMensalista;


public class ListaVeiculosMensalista extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static ListView listaVeiculoMensalistas;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ImageButton btnNovoVeiculoMensalista;
    Mensalista mensalista = new Mensalista();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_veiculos_mensalista);
        listaVeiculoMensalistas = findViewById(R.id.lista_veiculos_mensalista);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);

        /*SINCRONIZA O ESTADO DO MENU (ABERTO OU FECHADO)*/
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnNovoVeiculoMensalista = findViewById(R.id.btn_add_veiculo_mensalista);
        btnNovoVeiculoMensalista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                if(intent.getSerializableExtra("mensalista") != null) {

                    mensalista = (Mensalista) intent.getSerializableExtra("mensalista");
                    Intent intentCadastro = new Intent(ListaVeiculosMensalista.this, CadastrarVeiculoMensalistaActivity.class);
                    intentCadastro.putExtra("veiculo_mensalista", mensalista);
                    startActivity(intentCadastro);

                }


            }
        });


        listaVeiculoMensalistas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                VeiculoMensalista veiculoMensalista = (VeiculoMensalista) listaVeiculoMensalistas.getItemAtPosition(position);

                Intent intent = new Intent(ListaVeiculosMensalista.this, CadastrarVeiculoMensalistaActivity.class);
                intent.putExtra("editar_veiculo_mensalista", veiculoMensalista);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();

        if(intent.getSerializableExtra("mensalista") != null) {

            mensalista = (Mensalista) intent.getSerializableExtra("mensalista");
            CarregarListaVeiculoMensalista carregarListaVeiculoMensalista = new CarregarListaVeiculoMensalista(this, mensalista.getId());
            carregarListaVeiculoMensalista.execute();
        }



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.menu_entrada:{
                startActivity (new Intent (this, CadastrarMovimentoActivity.class));
                break;
            }
            case R.id.menu_estacionados:{
                startActivity (new Intent (this, Estacionados.class));
                break;
            }
            case R.id.menu_saida:{
                startActivity(new Intent(this, SaidaEstacionamento.class));
                break;
            }
            case R.id.menu_cadastrar_preco:{
                startActivity(new Intent(this, ListaPreco.class));
                break;
            }
            case R.id.menu_cadastrar_mensalista:{
                startActivity(new Intent(this, ListaMensalista.class));
                break;
            }

        }
        drawerLayout.closeDrawer(Gravity.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(Gravity.START)){
            drawerLayout.closeDrawer(Gravity.START);
        }else {
            super.onBackPressed();
        }
    }
}
