package es.ikergarciagutierrez.accdat.flora.view.adapter.viewholder;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.ikergarciagutierrez.accdat.flora.R;

public class FloraViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivFlora;
    public EditText etFloraNombre, etFloraFamilia;

    public FloraViewHolder(@NonNull View itemView) {
        super(itemView);
        ivFlora = itemView.findViewById(R.id.ivFlora);
        etFloraNombre = itemView.findViewById(R.id.etFloraNombre);
        etFloraFamilia = itemView.findViewById(R.id.etFloraFamilia);
    }
}
