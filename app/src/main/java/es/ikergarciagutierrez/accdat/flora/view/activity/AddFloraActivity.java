package es.ikergarciagutierrez.accdat.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import es.ikergarciagutierrez.accdat.flora.viewmodel.AddFloraViewModel;

public class AddFloraActivity extends AppCompatActivity {

    private Context context;
    private Flora flora;
    private AddFloraViewModel afvm;
    private EditText etNombre, etFamilia, etIdentificacion, etAltitud, etHabitat, etFitosociologia,
            etBiotipo, etBioReproductiva, etFloracion, etFructificacion, etExpSexual, etPolinizacion,
            etDispersion, etNumCromosomatico, etRepAsexual, etDistribucion, etBiologia, etDemografia,
            etAmenazas, etMedPropuestas;
    private Button btCancelarAñadir, btAñadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flora);
        context = this;
        initialize();
    }

    private void initialize() {
        afvm = new ViewModelProvider(this).get(AddFloraViewModel.class);
        afvm.getAddFloraLiveData().observe(this, aLong -> {
            if (aLong > 0) {
                finish();
            } else {
                Toast.makeText(AddFloraActivity.this, R.string.toast_error, Toast.LENGTH_LONG).show();
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
            new AlertDialog.Builder(context)
                    .setTitle(R.string.alertDialogAñadir_title)
                    .setMessage(R.string.alertDialogAñadir_message)
                    .setPositiveButton(R.string.alertDialog_confirmar, (dialog, which) -> {
                        if (areFieldsEmpty()) {
                            afvm.createFlora(getFlora());
                            Toast.makeText(context, R.string.toast_añadirFlora, Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(context, R.string.toast_fieldsEmpty, Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton(R.string.alertDialog_cancelar, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });
    }

    private void defineButtonCancelarAñadirListener() {
        btCancelarAñadir.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle(R.string.alertDialogCancelarAñadir_title)
                    .setMessage(R.string.alertDialogCancelarAñadir_message)
                    .setPositiveButton(R.string.alertDialog_confirmar, (dialog, which) -> {
                        finish();
                    })
                    .setNegativeButton(R.string.alertDialog_cancelar, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });
    }

    private boolean areFieldsEmpty() {
        if (etNombre.getText().toString().isEmpty() || etFamilia.getText().toString().isEmpty() ||
                etIdentificacion.getText().toString().isEmpty() || etAltitud.getText().toString().isEmpty() ||
                etHabitat.getText().toString().isEmpty() || etFitosociologia.getText().toString().isEmpty() ||
                etBiotipo.getText().toString().isEmpty() || etBioReproductiva.getText().toString().isEmpty() ||
                etFloracion.getText().toString().isEmpty() || etFructificacion.getText().toString().isEmpty() ||
                etExpSexual.getText().toString().isEmpty() || etPolinizacion.getText().toString().isEmpty() ||
                etDispersion.getText().toString().isEmpty() || etNumCromosomatico.getText().toString().isEmpty() ||
                etRepAsexual.getText().toString().isEmpty() || etDistribucion.getText().toString().isEmpty() ||
                etBiologia.getText().toString().isEmpty() || etDemografia.getText().toString().isEmpty() ||
                etAmenazas.getText().toString().isEmpty() || etMedPropuestas.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    private Flora getFlora() {
        flora = new Flora();
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
        return flora;
    }
}