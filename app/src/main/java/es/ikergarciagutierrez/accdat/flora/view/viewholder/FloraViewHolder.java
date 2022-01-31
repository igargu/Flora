package es.ikergarciagutierrez.accdat.flora.view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.ikergarciagutierrez.accdat.flora.R;

public class FloraViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivFlora;
    public TextView tvNombre, tvFamilia, tvIdentificacion, tvAltitud, tvHabitat, tvFitosociologia,
            tvBiotipo, tvBiologiaReproductiva, tvFloracion, tvFructificacion, tvExpresionSexual,
            tvPolinizacion, tvDispersion, tvNumeroCromosomatico, tvReproduccionAsexual,
            tvDistribucion, tvBiologia, tvDemografia, tvAmenazas, tvMedidasPropuestas;

    public FloraViewHolder(@NonNull View itemView) {
        super(itemView);
        ivFlora = itemView.findViewById(R.id.ivFlora);
        tvNombre = itemView.findViewById(R.id.tvNombre);
        tvFamilia = itemView.findViewById(R.id.tvFamilia);
        tvIdentificacion = itemView.findViewById(R.id.tvIdentificacion);
        tvAltitud = itemView.findViewById(R.id.tvAltitud);
        tvHabitat = itemView.findViewById(R.id.tvHabitat);
        tvFitosociologia = itemView.findViewById(R.id.tvFitosociologia);
        tvBiotipo = itemView.findViewById(R.id.tvBiotipo);
        tvBiologiaReproductiva = itemView.findViewById(R.id.tvBiologiaReproductiva);
        tvFloracion = itemView.findViewById(R.id.tvFloracion);
        tvFructificacion = itemView.findViewById(R.id.tvFructificacion);
        tvExpresionSexual = itemView.findViewById(R.id.tvExpresionSexual);
        tvPolinizacion = itemView.findViewById(R.id.tvPolinizacion);
        tvDispersion = itemView.findViewById(R.id.tvDispersion);
        tvNumeroCromosomatico = itemView.findViewById(R.id.tvNumeroCromosomatico);
        tvReproduccionAsexual = itemView.findViewById(R.id.tvReproduccionAsexual);
        tvDistribucion = itemView.findViewById(R.id.tvDistribucion);
        tvBiologia = itemView.findViewById(R.id.tvBiologia);
        tvDemografia = itemView.findViewById(R.id.tvDemografia);
        tvAmenazas = itemView.findViewById(R.id.tvAmenazas);
        tvMedidasPropuestas = itemView.findViewById(R.id.tvMedidasPropuestas);
    }
}
