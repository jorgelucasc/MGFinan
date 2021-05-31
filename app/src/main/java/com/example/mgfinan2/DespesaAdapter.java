package com.example.mgfinan2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mgfinan2.dominio.entidades.Despesa;

import java.util.List;

public class DespesaAdapter extends RecyclerView.Adapter<DespesaAdapter.ViewHolderDespesa> {
    private List<Despesa> dados;

    public DespesaAdapter(List<Despesa> dados) {
        this.dados = dados;
    }

    @Override
    public DespesaAdapter.ViewHolderDespesa onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha_despesa,parent,false);

        ViewHolderDespesa holderDespesa = new ViewHolderDespesa(view, parent.getContext());

        return holderDespesa;
    }

    @Override
    public void onBindViewHolder(DespesaAdapter.ViewHolderDespesa holder, int position) {

        if ((dados!=null)&(dados.size()>0)){
            Despesa despesa = dados.get(position);

            holder.txtValor.setText(despesa.vlrDespesa);
            holder.txtObs.setText(despesa.obsDespesa);
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
    public class ViewHolderDespesa extends RecyclerView.ViewHolder{

        public TextView txtObs;
        public TextView txtValor;
        public Spinner tpDespesa;

        public ViewHolderDespesa(View itemView, final Context context) {
            super(itemView);

            txtObs = (TextView) itemView.findViewById(R.id.txtObs);
            txtValor = itemView.findViewById(R.id.txtValor);
            tpDespesa = (Spinner) itemView.findViewById(R.id.spinner_tpDespesa);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dados.size()>0){
                        Despesa despesa = dados.get(getLayoutPosition());

                        Intent it = new Intent(context, ActCadDespesa.class);
                        it.putExtra("DESPESAS", despesa);
                        ((AppCompatActivity)context).startActivityForResult(it,0);
                    }

                }
            });
        }
    }
}
