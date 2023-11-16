package com.example.mycrud.adaptadors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycrud.FormActivity;
import com.example.mycrud.R;
import com.example.mycrud.api.api_inter;
import com.example.mycrud.api.retro;
import com.example.mycrud.models.clase_empleados;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdaClientes extends RecyclerView.Adapter<AdaClientes.Vista>{
    private Context C;
    private List<clase_empleados> Lista;
    api_inter api;

    public AdaClientes(Context C, List<clase_empleados> Lista) {
        this.C = C;
        this.Lista = Lista;
        api = retro.getClient().create(api_inter.class);

    }
//    private OnClientesClickListener onClientesClickListener;
//    public interface OnClientesClickListener { void onClientesClick(ClsClientes Cli);}
//
//    public void setOnPetClickListener(OnClientesClickListener onClientesClickListener) {
//        this.onClientesClickListener = onClientesClickListener;
//    }
//    -----------------------------------------------------------------------------------------
    @NonNull
    @Override
    public Vista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(C).inflate(R.layout.item, parent, false);
        return new Vista(V);
    }

    @Override
    public void onBindViewHolder(@NonNull Vista holder, int position) {
        int p = position;
        final clase_empleados item = Lista.get(position);
        holder.titulo.setText(item.getNombre());
        holder.subtitulo.setText(item.getPuesto()+ " [ $"+String.valueOf(item.getSalario())+"]");

        holder.btn_borrar.setOnClickListener(v -> {
            Call<String> call = api.BORRAR(item.getId_empleado());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(C, "Se borro a: " + item.getNombre().toString()+" "+item.getApellidoP(), Toast.LENGTH_SHORT).show();
                    holder.itemView.setVisibility(View.GONE);
                    Lista.remove(p);
                    notifyItemRemoved(p);
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {}
            });

        });
        holder.btn_editar.setOnClickListener(v -> {
            Intent intent = new Intent(C, FormActivity.class);
            intent.putExtra(FormActivity.CLI_KEY, item);
            C.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {return Lista.size();}
//    -----------------------------------------------------------------------------------------

    public class Vista extends RecyclerView.ViewHolder{
        TextView titulo, subtitulo;
        Button btn_editar, btn_borrar;
        public Vista(@NonNull View itemView) { super(itemView);
            titulo = itemView.findViewById(R.id.item_titulo);
            subtitulo = itemView.findViewById(R.id.item_subtitulo);

            btn_editar = itemView.findViewById(R.id.btn_editar);
            btn_borrar = itemView.findViewById(R.id.btn_borrar);
        }
    }
}
