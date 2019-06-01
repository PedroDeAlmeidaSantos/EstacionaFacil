package br.senai.sp.estacionafacil;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.transition.Slide;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import br.senai.sp.estacionafacil.model.Veiculo;
import br.senai.sp.estacionafacil.task.CarregarListaVeiculos;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //public static ListView listViewVeiculos;
    private ImageButton btnNovo;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "BEM VINDO À ESTACIONA FÁCIL  " + ("\ud83d\ude98"), Toast.LENGTH_LONG).show();

        //listViewVeiculos = findViewById(R.id.lista_veiculos);
        btnNovo = findViewById(R.id.btn_add_veiculo);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);

        /*SINCRONIZA O ESTADO DO MENU (ABERTO OU FECHADO)*/
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);



        //registerForContextMenu(listViewVeiculos);

        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*INTENÇÃO DE ABRIR A TELA DE CADASTRO*/
                Intent cadastroVeiculo = new Intent(MainActivity.this, CadastrarMovimentoActivity.class);
                /*PASSAR CONTATO DE UMA ACTIVITY PARA OUTRA*/
                startActivity(cadastroVeiculo);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onResume() {
        super.onResume();

       // CarregarListaVeiculos carregarListaVeiculos = new CarregarListaVeiculos(this);
        //carregarListaVeiculos.execute();

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
