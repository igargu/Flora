package es.ikergarciagutierrez.accdat.flora.view.adapter.viewholder;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.ikergarciagutierrez.accdat.flora.R;

/**
 * Clase que controla el item para el RecyclerView
 */
public class FloraViewHolder extends RecyclerView.ViewHolder {

    /**
     * Campos de la clase
     */
    public ImageView ivFlora;
    public EditText etFloraNombre, etFloraFamilia;

    /**
     * Método que inicializa los componentes del item
     *
     * @param itemView Vista del item
     */
    public FloraViewHolder(@NonNull View itemView) {
        super(itemView);
        ivFlora = itemView.findViewById(R.id.ivFlora);
        etFloraNombre = itemView.findViewById(R.id.etFloraNombre);
        etFloraFamilia = itemView.findViewById(R.id.etFloraFamilia);
    }
}
