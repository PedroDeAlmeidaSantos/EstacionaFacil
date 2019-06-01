package br.senai.sp.estacionafacil;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import br.senai.sp.estacionafacil.R;

import br.senai.sp.estacionafacil.model.Mensalista;
import br.senai.sp.estacionafacil.model.Preco;
import br.senai.sp.estacionafacil.task.CarregarListaMensalistas;
import br.senai.sp.estacionafacil.task.CarregarListaPrecos;
import br.senai.sp.estacionafacil.task.DesativarPreco;
import br.senai.sp.estacionafacil.task.ExcluirMensalista;

public class ListaMensalista extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static ListView listaMensalistas;
    private Toolbar toolbar;
    private ImageButton botaoCadastrarMensalista;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    
    Mensalista mensalista = new Mensalista();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_mensalista);
        listaMensalistas = findViewById(R.id.lista_mensalistas);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);

        /*SINCRONIZA O ESTADO DO MENU (ABERTO OU FECHADO)*/
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        botaoCadastrarMensalista = findViewById(R.id.btn_add_mensalista);

        registerForContextMenu(listaMensalistas);

        botaoCadastrarMensalista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ListaMensalista.this, CadastrarMensalistaActivity.class);
                startActivity(intent);

            }
        });


        listaMensalistas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                Mensalista mensalista = (Mensalista) listaMensalistas.getItemAtPosition(position);

                Intent cadastroMensalista = new Intent(ListaMensalista.this, ListaVeiculosMensalista.class);
                cadastroMensalista.putExtra("mensalista", mensalista);
                startActivity(cadastroMensalista);


            }
        });

        registerForContextMenu(listaMensalistas);



    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context_lista_mensalista, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        mensalista = (Mensalista) listaMensalistas.getItemAtPosition(info.position);
        switch (item.getItemId()){
            case R.id.menu_editar_mensalista:{

                Intent intent = new Intent(this, CadastrarMensalistaActivity.class);
                intent.putExtra("mensalista", mensalista);
                startActivity(intent);
                break;
            }
            case R.id.menu_excluir_mensalista:{
                ExcluirMensalista excluirMensalista = new ExcluirMensalista(mensalista.getId());
                excluirMensalista.execute();

                CarregarListaMensalistas carregarListaMensalistas = new CarregarListaMensalistas(this);
                carregarListaMensalistas.execute();
            }
        }


        return super.onContextItemSelected(item);

    }
    
    @Override
    protected void onResume() {
        super.onResume();

        CarregarListaMensalistas carregarListaMensalistas = new CarregarListaMensalistas(this);
        carregarListaMensalistas.execute();

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
