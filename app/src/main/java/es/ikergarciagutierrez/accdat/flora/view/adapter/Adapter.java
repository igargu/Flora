package es.ikergarciagutierrez.accdat.flora.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import es.ikergarciagutierrez.accdat.flora.model.entity.Imagen;
import es.ikergarciagutierrez.accdat.flora.view.adapter.viewholder.FloraViewHolder;
import es.ikergarciagutierrez.accdat.flora.viewmodel.AddFloraViewModel;
import es.ikergarciagutierrez.accdat.flora.viewmodel.MainActivityViewModel;

/**
 * Adapter para el RecyclerView
 */
public class Adapter extends RecyclerView.Adapter<FloraViewHolder> implements View.OnClickListener {

    /**
     * Campos de la clase
     */
    private Context context;

    private List<Flora> floraList;
    private String ivFloraURL = "https://informatica.ieszaidinvergeles.org:10008/ad/felixRLDFApp/public/api/imagen/";

    private View.OnClickListener listener;

    /**
     * Constructor para la clase
     *
     * @param context Contexto de la clase
     */
    public Adapter(Context context) {
        this.context = context;
    }

    /**
     * Método que infla el layout con los componentes del item
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public FloraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flora, parent, false);

        view.setOnClickListener(listener);

        return new FloraViewHolder(view);
    }

    /**
     * Método que llena los campos de cada item del RecyclerView
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull FloraViewHolder holder, int position) {
        Flora flora = floraList.get(position);
        holder.etFloraNombre.setText(flora.getNombre());
        holder.etFloraFamilia.setText(flora.getFamilia());
        Picasso.get().load(ivFloraURL + flora.getId() + "/flora")
                .memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).into(holder.ivFlora);
    }

    /**
     * Método que devuelve la cantidad de items en el RecyclerView
     *
     * @return Cantidad de items en el RecyclerView
     */
    @Override
    public int getItemCount() {
        if (floraList == null) {
            return 0;
        }
        return floraList.size();
    }

    /**
     * Método que llena el ArrayList para el RecyclerView
     *
     * @param floraList ArrayList para el RecyclerView
     */
    public void setFloraList(List<Flora> floraList) {
        this.floraList = floraList;
        notifyDataSetChanged();
    }

    /**
     * Método que devuelve un item del RecyclerView según su posición
     *
     * @param poisition Posición del item que vamos a devolver
     * @return Item del RecyclerView que se encuentra en la posición que hemos especificado
     */
    public Flora getItem(int poisition) {
        return floraList.get(poisition);
    }

    /**
     * Método que estable el listenr para el RecyclerView
     * @param listener Objeto Listener
     */
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    /**
     * Método que realiza la acción del listener
     * @param view Objeto View
     */
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

}
