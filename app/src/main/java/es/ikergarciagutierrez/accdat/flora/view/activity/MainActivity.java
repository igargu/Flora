package es.ikergarciagutierrez.accdat.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import es.ikergarciagutierrez.accdat.flora.view.adapter.Adapter;
import es.ikergarciagutierrez.accdat.flora.viewmodel.MainActivityViewModel;

/**
 * Clase que principal que contiene el RecyclerView para los objetos Flora
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Campos de la clase
     */
    private Context context;

    private RecyclerView recyclerView;
    private MainActivityViewModel mavm;
    private Adapter adapter;

    private FloatingActionButton fabAdd, fabImagen;

    /**
     * Método que infla el layout
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        context = this;
        initialize();
    }

    protected void onResume() {
        super.onResume();
        mavm.getFlora();
        initialize();
    }

    /**
     * Método que inicializa los componentes del layout y los métodos de los listener
     */
    private void initialize() {

        recyclerView = findViewById(R.id.rvFlora);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new Adapter(context);
        recyclerView.setAdapter(adapter);

        fabAdd = findViewById(R.id.fabAdd);
        fabImagen = findViewById(R.id.fabImagen);

        mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mavm.getFlora();
        mavm.getFloraLiveData().observe(this, floras -> {
            adapter.setFloraList(floras);
        });

        defineFABAddListener();
        defineFABImagenListener();
        defineFloraListener();
    }

    /**
     * Listener para los item del RecyclerView. Abre la activity para editar objetos Flora
     */
    private void defineFloraListener() {
        adapter.setOnClickListener(view -> {
            openEditFloraActivity(adapter.getItem(recyclerView.getChildAdapterPosition(view)));
        });
    }

    /**
     * Listener del fab FABAdd. Abre la activity para añadir objetos Flora
     */
    private void defineFABAddListener() {
        fabAdd.setOnClickListener(v -> openAddFloraActivity());
    }

    /**
     * Listener del fab FABImagen. Abre la activity para añadir las imagenes de los objetos Flora
     */
    private void defineFABImagenListener() {
        fabImagen.setOnClickListener(v -> openAddImagenActivity());
    }

    /**
     * Método que abre la activity para añadir las imagenes de los objetos Flora
     */
    private void openAddImagenActivity() {
        Intent intent = new Intent(this, AddImagenActivity.class);

        ArrayList<Flora> floras = new ArrayList<>();
        for (int i = 0; i < adapter.getItemCount(); i++) {
            floras.add(adapter.getItem(i));
        }
        intent.putExtra("idFloras", floras);

        startActivity(intent);
    }

    /**
     * Método que abre la activity para añadir objetos Flora
     */
    private void openAddFloraActivity() {
        Intent intent = new Intent(this, AddFloraActivity.class);

        ArrayList<Flora> floras = new ArrayList<>();
        for (int i = 0; i < adapter.getItemCount(); i++) {
            floras.add(adapter.getItem(i));
        }
        intent.putExtra("idFloras", floras);

        startActivity(intent);

    }

    /**
     * Método que abre la activity para editar el objeto Flora que seleccionemos
     * @param f Objeto Flora que queremos editar
     */
    private void openEditFloraActivity(Flora f) {
        Intent intent = new Intent(this, EditFloraActivity.class);
        intent.putExtra("idFlora", f);

        ArrayList<Flora> floras = new ArrayList<>();
        for (int i = 0; i < adapter.getItemCount(); i++) {
            floras.add(adapter.getItem(i));
        }
        intent.putExtra("idFloras", floras);

        startActivity(intent);
    }

}