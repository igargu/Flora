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

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import es.ikergarciagutierrez.accdat.flora.model.entity.Imagen;
import es.ikergarciagutierrez.accdat.flora.viewmodel.AddImagenViewModel;
import es.ikergarciagutierrez.accdat.flora.viewmodel.MainActivityViewModel;

/**
 * Clase que añade una imagen al objeto Flora que decidamos guardando en la base de datos como un
 * objeto Imagen
 */
public class AddImagenActivity extends AppCompatActivity {

    /**
     * Campos de la clase
     */
    private ActivityResultLauncher<Intent> launcher;
    private AddImagenViewModel aivm;

    private ImageView ivSelectImagen;
    private AutoCompleteTextView actvIdFlora;
    private EditText etNombreImagen, etDescripcionImagen;
    private Button btAddImagen;

    private Intent resultadoImagen = null;

    private ArrayList<Flora> floras = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    /**
     * Método que infla el layout
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imagen);
        initialize();
    }

    /**
     * Método que inicializa los componentes del layout y los métodos de los listener
     */
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

        floras = (ArrayList<Flora>) getIntent().getSerializableExtra("idFloras");

        String[] type = new String[floras.size()];
        for (int i = 0; i < floras.size(); i++) {
            type[i] = floras.get(i).getNombre();
        }

        adapter = new ArrayAdapter<>(this, R.layout.list_flora, R.id.tvIdFlora, type);
        actvIdFlora.setAdapter(adapter);

    }

    /**
     * Método que guarda en la base de datos el objeto Imagen con los datos que hemos rellanado y la
     * foto que hemos seleccionado de nuestro dispositivo
     */
    private void uploadDataImage() {

        long id = 0;
        String nombre = "";
        for (int i = 0; i < floras.size(); i++) {
            if (actvIdFlora.getText().toString().equals(floras.get(i).getNombre())) {
                id = floras.get(i).getId();
            }
        }

        Random seed = new Random(30);
        String idFlora = String.valueOf(id);
        nombre = etNombreImagen.getText().toString() + "_" + seed.nextInt();
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
            Toast.makeText(this, R.string.toast_fieldsEmptyAddImage, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Método que devuelve el resultado de seleccionar una imagen de nuestro dispositivo
     *
     * @return Resultado de seleccionar una imagen de nuestro dispositivo
     */
    ActivityResultLauncher<Intent> getLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    //respuesta al resultado de haber seleccionado una imagen
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        resultadoImagen = result.getData();
                    }
                }
        );
    }

    /**
     * Método que devuelve un objeto intent que abre el explorador de archivos de nuestro dispositivo
     *
     * @return intent que abre el explorador de archivos de nuestro dispositivo
     */
    Intent getContentIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
    }

    /**
     * Método que selecciona la imagen que hemos obtenido de nuestro dispositivo
     */
    void selectImage() {
        Intent intent = getContentIntent();
        launcher.launch(intent);
    }
}
