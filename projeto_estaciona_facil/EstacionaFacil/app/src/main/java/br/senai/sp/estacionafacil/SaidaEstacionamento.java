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
import android.widget.ListView;

import br.senai.sp.estacionafacil.task.CarregarListaEstacionados;
import br.senai.sp.estacionafacil.task.CarregarListaVeiculosSaida;

public class SaidaEstacionamento extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    public static ListView listaSaida;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saida_estacionamento);
        listaSaida = findViewById(R.id.lista_saida);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);

        /*SINCRONIZA O ESTADO DO MENU (ABERTO OU FECHADO)*/
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    protected void onResume() {
        super.onResume();

        CarregarListaVeiculosSaida carregarListaVeiculosSaida = new CarregarListaVeiculosSaida(this);
        carregarListaVeiculosSaida.execute();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.menu_entrada:{
                startActivity (new Intent(this, CadastrarMovimentoActivity.class));
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
