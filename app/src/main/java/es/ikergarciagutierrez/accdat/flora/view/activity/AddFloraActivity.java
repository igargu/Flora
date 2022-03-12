package es.ikergarciagutierrez.accdat.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import es.ikergarciagutierrez.accdat.flora.viewmodel.AddFloraViewModel;

/**
 * Método que añade objetos Flora a la base de datos
 */
public class AddFloraActivity extends AppCompatActivity {

    /**
     * Campos de la clase
     */
    private Context context;
    private Flora flora;
    private AddFloraViewModel afvm;
    private EditText etNombre, etFamilia, etIdentificacion, etAltitud, etHabitat, etFitosociologia,
            etBiotipo, etBioReproductiva, etFloracion, etFructificacion, etExpSexual, etPolinizacion,
            etDispersion, etNumCromosomatico, etRepAsexual, etDistribucion, etBiologia, etDemografia,
            etAmenazas, etMedPropuestas;
    private Button btCancelarAñadir, btAñadir;

    /**
     * Método que infla el layout
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flora);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        context = this;
        initialize();
    }

    /**
     * Método que inicializa los componentes del layout y los métodos de los listener
     */
    private void initialize() {
        afvm = new ViewModelProvider(this).get(AddFloraViewModel.class);
        afvm.getAddFloraLiveData().observe(this, aLong -> {
            if (aLong > 0) {
                showToast(R.string.toast_añadirFlora);
                finish();
            } else {
                // showToast(R.string.toast_nameExist);
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

    /**
     * Listener del button btAñadir. Añade un objeto Flora a la base de datos con los datos que
     * hemos rellenado. Sólo se añade si no existe ya un objeto con el mismo nombre
     */
    private void defineButtonAñadirListener() {
        btAñadir.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle(R.string.alertDialogAñadir_title)
                    .setMessage(R.string.alertDialogAñadir_message)
                    .setPositiveButton(R.string.alertDialog_confirmar, (dialog, which) -> {
                        if (areFieldsEmpty()) {
                            afvm.createFlora(getFlora());
                        } else {
                            showToast(R.string.toast_fieldsEmpty);
                        }
                    })
                    .setNegativeButton(R.string.alertDialog_cancelar, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });
    }

    /**
     * Listener del button btCancelar. Cancela la pantalla para añadir un nuevo objeto Flora
     */
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

    /**
     * Método que comprueba si el campo nombre está vacío
     *
     * @return true si el campo nombre está vacío, false en caso contario
     */
    private boolean areFieldsEmpty() {
        if (etNombre.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve un objeto Flora con los valores introducidos en los campos
     *
     * @return objeto Flora con los valores introducidos en los campos
     */
    private Flora getFlora() {
        flora = new Flora();
        flora.setNombre(etNombre.getText().toString());
        flora.setFamilia(etFamilia.getText().toString());
        flora.setIdentificacion(etIdentificacion.getText().toString());
        flora.setAltitud(etAltitud.getText().toString());
        flora.setHabitat(etHabitat.getText().toString());
        flora.setFitosociologia(etFitosociologia.getText().toString());
        flora.setBiotipo(etBiotipo.getText().toString());
        flora.setBiologia_reproductiva(etBioReproductiva.getText().toString());
        flora.setFloracion(etFloracion.getText().toString());
        flora.setFructificacion(etFructificacion.getText().toString());
        flora.setExpresion_sexual(etExpSexual.getText().toString());
        flora.setPolinizacion(etPolinizacion.getText().toString());
        flora.setDispersion(etDispersion.getText().toString());
        flora.setNumero_cromosomatico(etNumCromosomatico.getText().toString());
        flora.setReproduccion_asexual(etRepAsexual.getText().toString());
        flora.setDistribucion(etDistribucion.getText().toString());
        flora.setBiologia(etBiologia.getText().toString());
        flora.setDemografia(etDemografia.getText().toString());
        flora.setAmenazas(etAmenazas.getText().toString());
        flora.setMedidas_propuestas(etMedPropuestas.getText().toString());
        return flora;
    }

    /**
     * Método que muestra un Toast personalizado
     *
     * @param message Mensaje que queremos que aparezca en el Toast
     */
    private void showToast(int message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        View toastView = toast.getView();
        toastView.getBackground().setColorFilter(getResources().getColor(R.color.primary_dark_color), PorterDuff.Mode.SRC_IN);
        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
        tv.setTextColor(Color.WHITE);
        toast.show();
    }
}