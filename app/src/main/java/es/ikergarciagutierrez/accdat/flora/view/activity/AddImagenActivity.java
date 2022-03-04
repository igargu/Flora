package es.ikergarciagutierrez.accdat.flora.view.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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
    private Context context;

    private ActivityResultLauncher<Intent> launcher;
    private AddImagenViewModel aivm;

    private ImageView ivSelectImagen;
    private AutoCompleteTextView actvIdFlora;
    private EditText etNombreImagen, etDescripcionImagen;
    private Button btAddImagen, btCancelarAdd;

    private Intent resultadoImagen = null;

    private ArrayList<Flora> floras = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    private String adAñadirImagen = "¿Añadir esta imagen a X?";

    /**
     * Método que infla el layout
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imagen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        context = this;
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
        btCancelarAdd = findViewById(R.id.btCancelarAñadir);

        ivSelectImagen.setOnClickListener(v -> {
            selectImage();
        });

        btAddImagen.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle(adAñadirImagen.replace("X", etNombreImagen.getText()))
                    .setMessage(R.string.alertDialogAñadirImagen_message)
                    .setPositiveButton(R.string.alertDialog_confirmar, (dialog, which) -> {
                        uploadDataImage();
                    })
                    .setNegativeButton(R.string.alertDialog_cancelar, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });

        btCancelarAdd.setOnClickListener(v -> {
            cancelAddImagen();
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
            showToast(R.string.toast_añadirImagen);
            Log.v("xyzyx", imagen.toString());
            finish();
        } else {
            showToast(R.string.toast_fieldsEmptyAddImage);
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
                        Picasso.get().load(resultadoImagen.getData()).memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).into(ivSelectImagen);
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

    /**
     * Método que cancela la activity
     */
    private void cancelAddImagen() {
        new AlertDialog.Builder(context)
                .setTitle(R.string.alertDialogCancelarAñadirImagen_title)
                .setMessage(R.string.alertDialogCancelarAñadir_message)
                .setPositiveButton(R.string.alertDialog_confirmar, (dialog, which) -> {
                    finish();
                })
                .setNegativeButton(R.string.alertDialog_cancelar, (dialog, which) -> {
                    dialog.cancel();
                })
                .show();
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
