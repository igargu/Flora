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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.entity.Imagen;
import es.ikergarciagutierrez.accdat.flora.viewmodel.AddImagenViewModel;

public class AddImagenActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;
    private AddImagenViewModel aivm;

    private ImageView ivSelectImagen;
    private AutoCompleteTextView actvIdFlora;
    private EditText etNombreImagen, etDescripcionImagen;
    private Button btAddImagen;

    private Intent resultadoImagen = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imagen);
        initialize();
    }

    private void initialize() {

        launcher = getLauncher();
        aivm = new ViewModelProvider(this).get(AddImagenViewModel.class);

        ivSelectImagen = findViewById(R.id.ivSelectImagen);
        actvIdFlora = findViewById(R.id.actvIdFlora);
        etNombreImagen = findViewById(R.id.etNombreImagen);
        etDescripcionImagen = findViewById(R.id.etDescripcionImagen);
        btAddImagen = findViewById(R.id.btAñadir);

        ivSelectImagen.setOnClickListener(v -> {
            selectImage();
        });

        btAddImagen.setOnClickListener(v -> {
            uploadDataImage();
        });

        String[] type = new String[]{"1"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_flora, R.id.tvIdFlora, type);
        actvIdFlora.setAdapter(adapter);

    }

    private void uploadDataImage() {
        String idFlora = actvIdFlora.getText().toString();
        String nombre = etNombreImagen.getText().toString();
        String descripcion = etDescripcionImagen.getText().toString();

        if (!(nombre.trim().isEmpty() || idFlora.trim().isEmpty() || resultadoImagen == null)) {
            Imagen imagen = new Imagen();
            imagen.nombre = nombre;
            imagen.descripcion = descripcion;
            imagen.idflora = Long.parseLong(idFlora);
            aivm.saveImagen(resultadoImagen, imagen);
            Toast.makeText(this, R.string.toast_añadirImagen, Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, R.string.toast_fieldsEmpty, Toast.LENGTH_LONG).show();
        }
    }

    ActivityResultLauncher<Intent> getLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    //respuesta al resultado de haber seleccionado una imagen
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        //copyData(result.getData());
                        resultadoImagen = result.getData();
                    }
                }
        );
    }

    Intent getContentIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
    }

    void selectImage() {
        Intent intent = getContentIntent();
        launcher.launch(intent);
    }
}
