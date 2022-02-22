package es.ikergarciagutierrez.accdat.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.api.FloraClient;
import es.ikergarciagutierrez.accdat.flora.view.activity.AddFloraActivity;
import es.ikergarciagutierrez.accdat.flora.view.adapter.Adapter;
import es.ikergarciagutierrez.accdat.flora.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    private FloatingActionButton fabAdd;
    private FloatingActionButton fabImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initialize();
    }

    private void initialize() {

        recyclerView = findViewById(R.id.rvFlora);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        Adapter adapter = new Adapter(context);
        recyclerView.setAdapter(adapter);

        fabAdd = findViewById(R.id.fabAdd);
        fabImagen = findViewById(R.id.fabImagen);

        defineFABAddListener();
        defineFABImagenListener();
    }

    private void defineFABAddListener() {
        fabAdd.setOnClickListener(v -> openAddActivity());
    }

    private void defineFABImagenListener() {
        fabImagen.setOnClickListener(v -> openAddImagenActivity());
    }

    private void openAddImagenActivity() {
        Intent intent = new Intent(this, AddImagenActivity.class);
        startActivity(intent);
    }

    private void openAddActivity() {
        Intent intent = new Intent(this, AddFloraActivity.class);
        startActivity(intent);
    }
}

/*
    Autentizicación desde Laravel y desde Retrofit
 */