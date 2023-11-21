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
import com.example.mycrud.RecetasFormActivity;
import com.example.mycrud.api.api_rec;
import com.example.mycrud.api.retro;
import com.example.mycrud.models.clase_recetas;
import com.example.mycrud.models.clase_recetas;

import java.util.List;

public class AdaRecetas extends RecyclerView.Adapter<AdaRecetas.Vista>{
    private Context C;
    private List<clase_recetas> Lista;
    api_rec api_rec;

    public AdaRecetas(Context C, List<clase_recetas> Lista) {
        this.C = C;
        this.Lista = Lista;
        api_rec = retro.getClient().create(api_rec.class);
    }
    public void actualizarDatos(List<clase_recetas> nuevosDatos) {
        Lista = nuevosDatos;
        notifyDataSetChanged();
    }
//    -----------------------------------------------------------------------------------------
    @NonNull
    @Override
    public AdaRecetas.Vista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(C).inflate(R.layout.item, parent, false);
        return new Vista(V);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaRecetas.Vista holder, int position) {
        int p = position;
        final clase_recetas item = Lista.get(position);
        holder.titulo.setText(item.nombre +" -  $ "+item.costoAprox + ".00");
        holder.subtitulo.setText("País: "+item.paisOrigen+", "+item.descripcion);

        holder.btn_editar.setOnClickListener(v -> {
            Intent intent = new Intent(C, RecetasFormActivity.class);
            intent.putExtra(RecetasFormActivity.KEY, item);
            C.startActivity(intent);
        });
        holder.btn_borrar.setOnClickListener(v -> {
            Toast.makeText(C, "Editando", Toast.LENGTH_SHORT).show();
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
