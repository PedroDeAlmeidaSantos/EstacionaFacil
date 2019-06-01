package br.senai.sp.estacionafacil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import android.widget.BaseAdapter;
import br.senai.sp.estacionafacil.R;
import br.senai.sp.estacionafacil.model.Veiculo;

public class VeiculoAdapter extends BaseAdapter{

    private List<Veiculo> veiculos;
    private Context context;

    public VeiculoAdapter(Context context, List<Veiculo> veiculos){
        this.veiculos = veiculos;
        this.context = context;

    }

    @Override
    public int getCount() {
        return veiculos.size();
    }

    @Override
    public Object getItem(int position) {
        return veiculos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return veiculos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Veiculo veiculo = veiculos.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_lista, null);

        TextView txtPlaca = view.findViewById(R.id.txt_placa_list);
        txtPlaca.setText(veiculo.getPlaca());

        TextView txtModelo = view.findViewById(R.id.txt_modelo_list);
        txtModelo.setText(veiculo.getModelo());

        ImageView foto = view.findViewById(R.id.imagem_veiculo);
        return view;
    }
}
