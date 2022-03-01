package es.ikergarciagutierrez.accdat.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.api.FloraClient;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import es.ikergarciagutierrez.accdat.flora.view.activity.AddFloraActivity;
import es.ikergarciagutierrez.accdat.flora.view.adapter.Adapter;
import es.ikergarciagutierrez.accdat.flora.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private RecyclerView recyclerView;
    private MainActivityViewModel mavm;
    private Adapter adapter;

    private FloatingActionButton fabAdd, fabImagen, fabReload;

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
        adapter = new Adapter(context);
        recyclerView.setAdapter(adapter);

        fabAdd = findViewById(R.id.fabAdd);
        fabImagen = findViewById(R.id.fabImagen);
        fabReload = findViewById(R.id.fabReload);

        mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mavm.getFlora();
        mavm.getFloraLiveData().observe(this, floras -> {
            adapter.setFloraList(floras);
        });

        defineFABAddListener();
        defineFABImagenListener();
        defineFABReloadListener();
        defineFloraListener();
    }

    private void defineFloraListener() {
        adapter.setOnClickListener(view -> {
            new MaterialAlertDialogBuilder(context)
                    .setTitle(R.string.alertDialogMain_title)
                    .setMessage(R.string.alertDialogMain_message)
                    .setNeutralButton(R.string.alertDialog_cancelar, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .setPositiveButton(R.string.alertDialog_imagen, (dialog, which) -> {
                        openEditImagenActivity();
                        dialog.cancel();
                    })
                    .setNegativeButton(R.string.alertDialog_flora, (dialog, which) -> {
                        openEditFloraActivity(adapter.getItem(recyclerView.getChildAdapterPosition(view)));
                        dialog.cancel();
                    })
                    .show();
        });
    }

    private void defineFABAddListener() {
        fabAdd.setOnClickListener(v -> openAddFloraActivity());
    }

    private void defineFABImagenListener() {
        fabImagen.setOnClickListener(v -> openAddImagenActivity());
    }

    private void defineFABReloadListener() {
        fabReload.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            finish();
            overridePendingTransition(0, 0);
            startActivity(i);
            overridePendingTransition(0, 0);
            Toast.makeText(context, R.string.toast_reload, Toast.LENGTH_SHORT).show();
        });
    }

    private void openAddImagenActivity() {
        Intent intent = new Intent(this, AddImagenActivity.class);
        startActivity(intent);
    }

    private void openAddFloraActivity() {
        Intent intent = new Intent(this, AddFloraActivity.class);
        startActivity(intent);
    }

    private void openEditImagenActivity() {
        Intent intent = new Intent(this, EditImagenActivity.class);
        startActivity(intent);
    }

    private void openEditFloraActivity(Flora f) {
        Intent intent = new Intent(this, EditFloraActivity.class);
        intent.putExtra("idFlora", f);
        startActivity(intent);
    }
}

/*
    Autentizicación desde Laravel y desde Retrofit
 */