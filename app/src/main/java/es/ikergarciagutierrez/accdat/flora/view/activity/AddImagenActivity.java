package es.ikergarciagutierrez.accdat.flora.view.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.entity.Imagen;
import es.ikergarciagutierrez.accdat.flora.viewmodel.AddImagenViewModel;

public class AddImagenActivity extends AppCompatActivity {

    private Button btAddImagen;
    private ImageView ivSelectImagen;
    private EditText etIdImagen, etNombreImagen, etDescripcionImagen;
    private String idImagen, nombre, descripcion;
    private ActivityResultLauncher<Intent> launcher;
    private Intent resultadoImagen;
    private AddImagenViewModel aivm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imagen);
        initialize();
    }

    private void initialize() {
        launcher = getLauncher();

        ivSelectImagen = findViewById(R.id.ivSelectImagen);
        ivSelectImagen.setOnClickListener(v -> {
            selectImage();
        });

        btAddImagen = findViewById(R.id.btAñadir);
        btAddImagen.setOnClickListener(v -> {
            uploadDataImage();
        });

        etIdImagen = findViewById(R.id.etIdImagen);
        etNombreImagen = findViewById(R.id.etNombreImagen);
        etDescripcionImagen = findViewById(R.id.etDescripcionImagen);

        aivm = new ViewModelProvider(this).get(AddImagenViewModel.class);

    }

    Intent getContentIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
    }

    ActivityResultLauncher<Intent> getLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    // Respuesta al resultado de haber seleccionado una imagen
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // copyData(result.getData());
                        resultadoImagen = result.getData();
                        Imagen imagen = new Imagen();
                        imagen.nombre = nombre;
                        imagen.descripcion = descripcion;
                        imagen.idflora = Long.parseLong(idImagen);
                        aivm.saveImagen(resultadoImagen, imagen);
                    }
                }
        );
    }

    void selectImage() {
        Intent intent = getContentIntent();
        launcher.launch(intent);
    }

    private void uploadDataImage() {
        idImagen = etIdImagen.getText().toString();
        nombre = etNombreImagen.getText().toString();
        descripcion = etDescripcionImagen.getText().toString();

        if (!(idImagen.trim().isEmpty() || descripcion.trim().isEmpty() || resultadoImagen == null)) {

        }
    }
}