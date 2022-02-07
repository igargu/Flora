package es.ikergarciagutierrez.accdat.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import es.ikergarciagutierrez.accdat.flora.viewmodel.AddFloraViewModel;

public class AddFloraActivity extends AppCompatActivity {

    private AddFloraViewModel avm;
    private EditText etNombre, etFamilia, etIdentificacion, etAltitud, etHabitat, etFitosociologia,
            etBiotipo, etBioReproductiva, etFloracion, etFructificacion, etExpSexual, etPolinizacion,
            etDispersion, etNumCromosomatico, etRepAsexual, etDistribucion, etBiologia, etDemografia,
            etAmenazas, etMedPropuestas;
    private Button btCancelarAñadir, btAñadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flora);
        initialize();
    }

    private void initialize() {
        avm = new ViewModelProvider(this).get(AddFloraViewModel.class);
        avm.getAddFloraLiveData().observe(this, aLong -> {
            if (aLong > 0) {
                finish();
            } else {
                Toast.makeText(AddFloraActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });

        etNombre = findViewById(R.id.etFloraNombre);
        etFamilia = findViewById(R.id.etFloraFamilia);
        etIdentificacion = findViewById(R.id.etFloraIdentificacion);
        etAltitud = findViewById(R.id.etFloraAltitud);
        etHabitat = findViewById(R.id.etFloraHabitat);
        etFitosociologia = findViewById(R.id.etFloraFitosociologia);
        etBiotipo = findViewById(R.id.etFloraBiotipo);
        etBioReproductiva = findViewById(R.id.etFloraBiologiaReproductiva);
        etFloracion = findViewById(R.id.etFloraFloración);
        etFructificacion = findViewById(R.id.etFloraFructificacion);
        etExpSexual = findViewById(R.id.etFloraExpresionSexual);
        etPolinizacion = findViewById(R.id.etFloraPolinizacion);
        etDispersion = findViewById(R.id.etFloraDispersion);
        etNumCromosomatico = findViewById(R.id.etFloraNumCromosomatico);
        etRepAsexual = findViewById(R.id.etFloraReproduccionAsexual);
        etDistribucion = findViewById(R.id.etFloraDistribucion);
        etBiologia = findViewById(R.id.etFloraBiologia);
        etDemografia = findViewById(R.id.etFloraDemografia);
        etAmenazas = findViewById(R.id.etFloraAmenazas);
        etMedPropuestas = findViewById(R.id.etFloraMedidasPropuestas);
        btCancelarAñadir = findViewById(R.id.btCancelarAñadir);
        btAñadir = findViewById(R.id.btAñadir);

        defineButtonAñadirListener();
        defineButtonCancelarAñadirListener();
    }

    private void defineButtonAñadirListener() {
        btAñadir.setOnClickListener(v -> {
            Flora flora = new Flora();
            flora.setNombre(etNombre.getText().toString());
            flora.setFamilia(etFamilia.getText().toString());
            flora.setIdentificacion(etIdentificacion.getText().toString());
            flora.setAltitud(etAltitud.getText().toString());
            flora.setHabitat(etHabitat.getText().toString());
            flora.setFitosociologia(etFitosociologia.getText().toString());
            flora.setBiotopo(etBiotipo.getText().toString());
            flora.setBiologia_reproductiva(etBioReproductiva.getText().toString());
            flora.setFloracion(etFloracion.getText().toString());
            flora.setFructificacion(etFructificacion.getText().toString());
            flora.setExpresion_sexual(etExpSexual.getText().toString());
            flora.setPolinizacion(etPolinizacion.getText().toString());
            flora.setDispersion(etDispersion.getText().toString());
            flora.setNumero_cromosomico(etNumCromosomatico.getText().toString());
            flora.setReproduccion_asexual(etRepAsexual.getText().toString());
            flora.setDistribucion(etDistribucion.getText().toString());
            flora.setBiologia(etBiologia.getText().toString());
            flora.setDemografia(etDemografia.getText().toString());
            flora.setAmenazas(etAmenazas.getText().toString());
            flora.setMedidas_propuestas(etMedPropuestas.getText().toString());
            avm.createFlora(flora);
        });
    }

    private void defineButtonCancelarAñadirListener() {
        btCancelarAñadir.setOnClickListener(v -> {

        });
    }
}