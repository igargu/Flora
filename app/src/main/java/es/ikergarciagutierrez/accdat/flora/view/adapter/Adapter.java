package es.ikergarciagutierrez.accdat.flora.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import es.ikergarciagutierrez.accdat.flora.view.adapter.viewholder.FloraViewHolder;

public class Adapter extends RecyclerView.Adapter<FloraViewHolder> {

    private List<Flora> floraList;
    private Context context;

    public Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FloraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flora, parent, false);
        return new FloraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FloraViewHolder holder, int position) {
        Flora flora = floraList.get(position);
        holder.tvNombre.setText(flora.getNombre());
        holder.tvFamilia.setText(flora.getFamilia());
        holder.tvIdentificacion.setText(flora.getIdentificacion());
        holder.tvAltitud.setText(flora.getAltitud());
        holder.tvHabitat.setText(flora.getHabitat());
        holder.tvFitosociologia.setText(flora.getFitosociologia());
        holder.tvBiotipo.setText(flora.getBiotopo());
        holder.tvBiologiaReproductiva.setText(flora.getBiologia_reproductiva());
        holder.tvFloracion.setText(flora.getFloracion());
        holder.tvFructificacion.setText(flora.getFructificacion());
        holder.tvExpresionSexual.setText(flora.getExpresion_sexual());
        holder.tvPolinizacion.setText(flora.getPolinizacion());
        holder.tvDispersion.setText(flora.getDispersion());
        holder.tvNumeroCromosomatico.setText(flora.getNumero_cromosomico());
        holder.tvReproduccionAsexual.setText(flora.getReproduccion_asexual());
        holder.tvDistribucion.setText(flora.getDistribucion());
        holder.tvBiologia.setText(flora.getBiologia());
        holder.tvDemografia.setText(flora.getDemografia());
        holder.tvAmenazas.setText(flora.getAmenazas());
        holder.tvMedidasPropuestas.setText(flora.getMedidas_propuestas());
        // holder.ivFlora.setImageResource();
    }

    @Override
    public int getItemCount() {
        if(floraList == null) {
            return 0;
        }
        return floraList.size();
    }

    public void setFloraList(List<Flora> floraList) {
        this.floraList = floraList;
        notifyDataSetChanged();
    }
}
