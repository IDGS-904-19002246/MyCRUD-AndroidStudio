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

import com.example.mycrud.EmpleadosFormActivity;
import com.example.mycrud.R;
import com.example.mycrud.TianguisFormActivity;
import com.example.mycrud.api.api_tia;
import com.example.mycrud.api.retro;
import com.example.mycrud.models.clase_empleados;
import com.example.mycrud.models.clase_tianguis;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdaTianguis extends RecyclerView.Adapter<AdaTianguis.Vista>{
    private Context C;
    private List<clase_tianguis> Lista;
    api_tia api_tia;

    public AdaTianguis(Context C, List<clase_tianguis> Lista) {
        this.C = C;
        this.Lista = Lista;
        api_tia = retro.getClient().create(api_tia.class);
    }
    public void actualizarDatos(List<clase_tianguis> nuevosDatos) {
        Lista = nuevosDatos;
        notifyDataSetChanged();
    }
    //    -----------------------------------------------------------------------------------------
    @NonNull
    @Override
    public AdaTianguis.Vista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(C).inflate(R.layout.item, parent, false);
        return new Vista(V);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaTianguis.Vista holder, int position) {
        int p = position;
        final clase_tianguis item = Lista.get(position);
        holder.titulo.setText(item.nombre+ " - "+item.estado);
        holder.subtitulo.setText(item.ubicacion+" de "+item.horaInicio.substring(0,5)+" - "+item.horaFinal.substring(0,5));

        holder.btn_editar.setOnClickListener(v -> {
            Intent intent = new Intent(C, TianguisFormActivity.class);
            intent.putExtra(TianguisFormActivity.KEY, item);
            C.startActivity(intent);
        });
        holder.btn_borrar.setOnClickListener(v -> {
            Call<String> call = api_tia.BORRAR(item.id_tianguis);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(C, "Se borro a: " + item.nombre, Toast.LENGTH_SHORT).show();
                    holder.itemView.setVisibility(View.GONE);
                    Lista.remove(p);
                    notifyItemRemoved(p);
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {}
            });
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
