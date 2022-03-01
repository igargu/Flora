package es.ikergarciagutierrez.accdat.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.entity.Imagen;
import es.ikergarciagutierrez.accdat.flora.viewmodel.EditImagenViewModel;

public class EditImagenActivity extends AppCompatActivity {

    private Context context;
    private Imagen imagen;
    private EditImagenViewModel eivm;

    private ImageView ivSelectImagen;
    private AutoCompleteTextView actvIdFlora;
    private EditText etNombreImagen, etDescripcionImagen;
    private Button btBorrar, btEditar, btCancelarEdicion, btGuardarEdicion;

    private String adBorrarTitulo = "¿Borrar X?";
    private String adEditarTitulo = "¿Editar X?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_imagen);
        context = this;
        initialize();
    }

    private void initialize() {

        eivm = new ViewModelProvider(this).get(EditImagenViewModel.class);

        ivSelectImagen = findViewById(R.id.ivSelectImagen);
        actvIdFlora = findViewById(R.id.actvIdFlora);
        etNombreImagen = findViewById(R.id.etNombreImagen);
        etDescripcionImagen = findViewById(R.id.etDescripcionImagen);

        btBorrar = findViewById(R.id.btBorrar);
        btEditar = findViewById(R.id.btEditar);
        btCancelarEdicion = findViewById(R.id.btCancelarEdicion);
        btGuardarEdicion = findViewById(R.id.btGuardarEdicion);

        setImagen();
        deshabilitarEdicion();

        defineButtonBorrar();
        defineButtonEditar();
        defineButtonCancelarEdicion();
        defineButtonGuardarEdicion();
    }

    private void defineButtonBorrar() {
        btBorrar.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle(adBorrarTitulo.replace("X", etNombreImagen.getText()))
                    .setMessage(R.string.alertDialogBorrar_message)
                    .setPositiveButton(R.string.alertDialog_confirmar, (dialog, which) -> {
                        // Borramos de la bd
                        Imagen imagen = getImagen();
                        // eivm.deleteFlora(flora.getId());
                        Toast.makeText(context, R.string.toast_borrarFlora, Toast.LENGTH_LONG).show();
                        finish();
                    })
                    .setNegativeButton(R.string.alertDialog_cancelar, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });
    }

    private void defineButtonEditar() {
        btEditar.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle(adEditarTitulo.replace("X", etNombreImagen.getText()))
                    .setMessage(R.string.alertDialogBorrar_message)
                    .setPositiveButton(R.string.alertDialog_confirmar, (dialog, which) -> {
                        habilitarEdicion();
                    })
                    .setNegativeButton(R.string.alertDialog_cancelar, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });
    }

    private void defineButtonCancelarEdicion() {
        btCancelarEdicion.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle(R.string.alertDialogCancelarEdicion_title)
                    .setMessage(R.string.alertDialogCancelarEdicion_message)
                    .setPositiveButton(R.string.alertDialog_confirmar, (dialog, which) -> {
                        deshabilitarEdicion();
                        finish();
                    })
                    .setNegativeButton(R.string.alertDialog_cancelar, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });
    }

    private void defineButtonGuardarEdicion() {
        btGuardarEdicion.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle(R.string.alertDialogGuardarEdicion_title)
                    .setMessage(R.string.alertDialogGuardarEdicion_message)
                    .setPositiveButton(R.string.alertDialog_confirmar, (dialog, which) -> {
                        deshabilitarEdicion();
                        // Guardar cambios
                        Imagen imagen = getImagen();
                        // efvm.editFlora(flora.getId(), flora);
                        Toast.makeText(context, R.string.toast_editarFlora, Toast.LENGTH_LONG).show();
                        finish();
                    })
                    .setNegativeButton(R.string.alertDialog_cancelar, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });
    }

    private void habilitarEdicion() {

        btBorrar.setVisibility(View.GONE);
        btEditar.setVisibility(View.GONE);
        btCancelarEdicion.setVisibility(View.VISIBLE);
        btGuardarEdicion.setVisibility(View.VISIBLE);

        ivSelectImagen.setEnabled(true);
        actvIdFlora.setEnabled(true);
        etNombreImagen.setEnabled(true);
        etDescripcionImagen.setEnabled(true);
    }

    private void deshabilitarEdicion() {

        btBorrar.setVisibility(View.VISIBLE);
        btEditar.setVisibility(View.VISIBLE);
        btCancelarEdicion.setVisibility(View.GONE);
        btGuardarEdicion.setVisibility(View.GONE);

        ivSelectImagen.setEnabled(false);
        actvIdFlora.setEnabled(false);
        etNombreImagen.setEnabled(false);
        etDescripcionImagen.setEnabled(false);
    }

    private Imagen getImagen() {
        imagen = new Imagen();
        imagen.setId(actvIdFlora.getId());
        imagen.setNombre(etNombreImagen.getText().toString());
        imagen.setDescripcion(etDescripcionImagen.getText().toString());
        return imagen;
    }

    private void setImagen() {

        //ivSelectImagen.setImageResource();
        actvIdFlora.setText((int) imagen.getId());
        etNombreImagen.setText(imagen.getNombre());
        etDescripcionImagen.setText(imagen.getDescripcion());
    }
}