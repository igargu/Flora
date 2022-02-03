package es.ikergarciagutierrez.accdat.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.viewmodel.AddImagenViewModel;

public class AddImagenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imagen);
        initialize();
    }

    private void initialize() {
        AddImagenViewModel aivm = new ViewModelProvider(this).get(AddImagenViewModel.class);

    }
}