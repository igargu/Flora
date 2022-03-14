package es.ikergarciagutierrez.accdat.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import es.ikergarciagutierrez.accdat.flora.viewmodel.EditFloraViewModel;

/**
 * Clase que edita y borra los objetos Flora
 */
public class EditFloraActivity extends AppCompatActivity {

    /**
     * Campos de la clase
     */
    private Context context;
    private Flora flora;
    private EditFloraViewModel efvm;

    private ImageView ivFlora;
    private EditText etNombre, etFamilia, etIdentificacion, etAltitud, etHabitat, etFitosociologia,
            etBiotipo, etBioReproductiva, etFloracion, etFructificacion, etExpSexual, etPolinizacion,
            etDispersion, etNumCromosomatico, etRepAsexual, etDistribucion, etBiologia, etDemografia,
            etAmenazas, etMedPropuestas;
    private TextView tvMoreInfo;
    private Button btBorrar, btEditar, btCancelarEdicion, btGuardarEdicion;

    private String ivFloraURL = "https://informatica.ieszaidinvergeles.org:10008/ad/felixRLDFApp/public/api/imagen/";

    private String adBorrarTitulo = "¿Borrar X?";
    private String adEditarTitulo = "¿Editar X?";

    /**
     * Método que infla el layout
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flora);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        context = this;
        initialize();
    }

    protected void onResume() {
        super.onResume();
        initialize();
    }

    /**
     * Método que inicializa los componentes del layout y los métodos de los listener
     */
    private void initialize() {

        efvm = new ViewModelProvider(this).get(EditFloraViewModel.class);

        ivFlora = findViewById(R.id.ivFlora);
        etNombre = findViewById(R.id.etFloraNombre);
        etFamilia = findViewById(R.id.etFloraFamilia);
        etIdentificacion = findViewById(R.id.etFloraIdentificacion);
        etAltitud = findViewById(R.id.etFloraAltitud);
        etHabitat = findViewById(R.id.etFloraHabitat);
        etFitosociologia = findViewById(R.id.etFloraFitosociologia);
        etBiotipo = findViewById(R.id.etFloraBiotipo);
        etBioReproductiva = findViewById(R.id.etFloraBiologiaReproductiva);
        etFloracion = findViewById(R.id.etFloraFloracion);
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

        tvMoreInfo = findViewById(R.id.tvMoreInfo);

        btBorrar = findViewById(R.id.btBorrar);
        btEditar = findViewById(R.id.btEditar);
        btCancelarEdicion = findViewById(R.id.btCancelarEdicion);
        btGuardarEdicion = findViewById(R.id.btGuardarEdicion);

        setFlora();
        deshabilitarEdicion();

        defineTextViewMoreInfoListener();
        defineButtonBorrarListener();
        defineButtonEditarListener();
        defineButtonCancelarEdicionListener();
        defineButtonGuardarEdicionListener();
    }

    /**
     * Listener del textview tvMoreInfo. Hace una búsqueda en Wikipedia según el nombre del objeto
     * Flora
     */
    private void defineTextViewMoreInfoListener() {
        tvMoreInfo.setOnClickListener(view -> {
            if (etNombre.getText().toString().isEmpty()) {
                Toast.makeText(context, R.string.toast_errorWiki, Toast.LENGTH_LONG).show();
            } else {
                String urlWikipedia = "https://es.wikipedia.org/wiki/" + etNombre.getText().toString().trim();
                Uri uri = Uri.parse(urlWikipedia);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    /**
     * Listener del button btBorrar. Borra el objeto Flora de la base de datos
     */
    private void defineButtonBorrarListener() {
        btBorrar.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle(adBorrarTitulo.replace("X", etNombre.getText()))
                    .setMessage(R.string.alertDialogBorrar_message)
                    .setPositiveButton(R.string.alertDialog_confirmar, (dialog, which) -> {
                        efvm.deleteFlora(flora.getId());
                        showToast(R.string.toast_borrarFlora);
                        finish();
                    })
                    .setNegativeButton(R.string.alertDialog_cancelar, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });
    }

    /**
     * Listener del button btEditar. Habilita la edición de campos
     */
    private void defineButtonEditarListener() {
        btEditar.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle(adEditarTitulo.replace("X", etNombre.getText()))
                    .setMessage(R.string.alertDialogBorrar_message)
                    .setNeutralButton(R.string.alertDialog_cancelar, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .setPositiveButton(R.string.alertDialog_editFlora, (dialog, which) -> {
                        habilitarEdicion();
                    })
                    .setNegativeButton(R.string.alertDialog_editImagen, (dialog, which) -> {
                        editarImagen();
                    })
                    .show();
        });
    }

    /**
     * Listener del button btCancelarEdicion. Cancela la edición del objeto Flora, reestableciendo
     * los datos a sus valores originales
     */
    private void defineButtonCancelarEdicionListener() {
        btCancelarEdicion.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle(R.string.alertDialogCancelarEdicion_title)
                    .setMessage(R.string.alertDialogCancelarEdicion_message)
                    .setPositiveButton(R.string.alertDialog_confirmar, (dialog, which) -> {
                        deshabilitarEdicion();
                        setFlora();
                    })
                    .setNegativeButton(R.string.alertDialog_cancelar, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });
    }

    /**
     * Listener el button btGuardarEdicion. Guarda en la base de datos el objeto Flora con los
     * nuevos valores introducidos
     */
    private void defineButtonGuardarEdicionListener() {
        btGuardarEdicion.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle(R.string.alertDialogGuardarEdicion_title)
                    .setMessage(R.string.alertDialogGuardarEdicion_message)
                    .setPositiveButton(R.string.alertDialog_confirmar, (dialog, which) -> {
                        deshabilitarEdicion();
                        if (!areFieldsEmpty()) {
                            efvm.editFlora(flora.getId(), getFlora());
                            finish();
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
     * Método que habilita la edición de campos y muestra los botones para cancelar y guardar la
     * edición. Los botones de borrar y editar son ocultados
     */
    private void habilitarEdicion() {

        ivFlora.setVisibility(View.GONE);
        tvMoreInfo.setVisibility(View.GONE);

        btBorrar.setVisibility(View.GONE);
        btEditar.setVisibility(View.GONE);
        btCancelarEdicion.setVisibility(View.VISIBLE);
        btGuardarEdicion.setVisibility(View.VISIBLE);

        etNombre.setEnabled(true);
        etFamilia.setEnabled(true);
        etIdentificacion.setEnabled(true);
        etAltitud.setEnabled(true);
        etHabitat.setEnabled(true);
        etFitosociologia.setEnabled(true);
        etBiotipo.setEnabled(true);
        etBioReproductiva.setEnabled(true);
        etFloracion.setEnabled(true);
        etFructificacion.setEnabled(true);
        etExpSexual.setEnabled(true);
        etPolinizacion.setEnabled(true);
        etDispersion.setEnabled(true);
        etNumCromosomatico.setEnabled(true);
        etRepAsexual.setEnabled(true);
        etDistribucion.setEnabled(true);
        etBiologia.setEnabled(true);
        etDemografia.setEnabled(true);
        etAmenazas.setEnabled(true);
        etMedPropuestas.setEnabled(true);
    }

    /**
     * Método que deshabilita la edición de campos y muestra los botones borrar y editar.
     * Los botones para cancelar y guardar la edición son ocultados
     */
    private void deshabilitarEdicion() {

        ivFlora.setVisibility(View.VISIBLE);
        tvMoreInfo.setVisibility(View.VISIBLE);

        btBorrar.setVisibility(View.VISIBLE);
        btEditar.setVisibility(View.VISIBLE);
        btCancelarEdicion.setVisibility(View.GONE);
        btGuardarEdicion.setVisibility(View.GONE);

        etNombre.setEnabled(false);
        etFamilia.setEnabled(false);
        etIdentificacion.setEnabled(false);
        etAltitud.setEnabled(false);
        etHabitat.setEnabled(false);
        etFitosociologia.setEnabled(false);
        etBiotipo.setEnabled(false);
        etBioReproductiva.setEnabled(false);
        etFloracion.setEnabled(false);
        etFructificacion.setEnabled(false);
        etExpSexual.setEnabled(false);
        etPolinizacion.setEnabled(false);
        etDispersion.setEnabled(false);
        etNumCromosomatico.setEnabled(false);
        etRepAsexual.setEnabled(false);
        etDistribucion.setEnabled(false);
        etBiologia.setEnabled(false);
        etDemografia.setEnabled(false);
        etAmenazas.setEnabled(false);
        etMedPropuestas.setEnabled(false);
    }

    /**
     * Método que comprueba si el campo nombre está vacío
     * @return true si el campo nombre está vacío, false en caso contario
     */
    private boolean areFieldsEmpty() {
        if (etNombre.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }

    private void editarImagen() {
        Intent intent = new Intent(this, EditImagenActivity.class);
        Bundle bundle = getIntent().getExtras();
        flora = bundle.getParcelable("idFlora");
        intent.putExtra("idFlora", flora);
        startActivity(intent);
    }

    /**
     * Método que devuelve un objeto Flora con los valores introducidos en los campos
     * @return objeto Flora con los valores introducidos en los campos
     */
    private Flora getFlora() {

        flora = new Flora();

        Bundle bundle = getIntent().getExtras();
        Flora oldFlora = bundle.getParcelable("idFlora");
        flora.setId(oldFlora.getId());

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
     * Método que llena los campos con los valores del objeto Flora actual
     */
    private void setFlora() {

        Bundle bundle = getIntent().getExtras();
        flora = bundle.getParcelable("idFlora");

        Picasso.get().load(ivFloraURL + flora.getId() + "/flora").memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).into(ivFlora);

        etNombre.setText(flora.getNombre());
        etFamilia.setText(flora.getFamilia());
        etIdentificacion.setText(flora.getIdentificacion());
        etAltitud.setText(flora.getAltitud());
        etHabitat.setText(flora.getHabitat());
        etFitosociologia.setText(flora.getFitosociologia());
        etBiotipo.setText(flora.getBiotipo());
        etBioReproductiva.setText(flora.getBiologia_reproductiva());
        etFloracion.setText(flora.getFloracion());
        etFructificacion.setText(flora.getFructificacion());
        etExpSexual.setText(flora.getExpresion_sexual());
        etPolinizacion.setText(flora.getPolinizacion());
        etDispersion.setText(flora.getDispersion());
        etNumCromosomatico.setText(flora.getNumero_cromosomatico());
        etRepAsexual.setText(flora.getReproduccion_asexual());
        etDistribucion.setText(flora.getDistribucion());
        etBiologia.setText(flora.getBiologia());
        etDemografia.setText(flora.getDemografia());
        etAmenazas.setText(flora.getAmenazas());
        etMedPropuestas.setText(flora.getMedidas_propuestas());
    }

    /**
     * Método que muestra un Toast personalizado
     *
     * @param message Mensaje que queremos que aparezca en el Toast
     */
    private void showToast(int message) {
        Toast toast = new Toast(context);
        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView tvToast = view.findViewById(R.id.tvMessage);
        tvToast.setText(message);
        toast.setView(view);
        toast.show();
    }

}
