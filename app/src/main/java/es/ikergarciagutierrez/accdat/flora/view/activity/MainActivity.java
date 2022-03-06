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

    private FloatingActionButton fabAdd;

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

    /**
     * Método que recarga el layout al volver a esta activty
     */
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

        mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mavm.getFlora();
        mavm.getFloraLiveData().observe(this, floras -> {
            adapter.setFloraList(floras);
        });

        defineFABAddListener();
        defineFloraListener();
    }

    /**
     * Listener para los item del RecyclerView. Abre la activity para editar objetos Flora
     */
    private void defineFloraListener() {
        adapter.setOnClickListener(view -> {
            Intent intent = new Intent(this, EditFloraActivity.class);
            intent.putExtra("idFlora", adapter.getItem(recyclerView.getChildAdapterPosition(view)));
            startActivity(intent);
        });
    }

    /**
     * Listener del fab FABAdd. Abre la activity para añadir objetos Flora
     */
    private void defineFABAddListener() {
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddFloraActivity.class);
            startActivity(intent);
        });
    }

}