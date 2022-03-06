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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import es.ikergarciagutierrez.accdat.flora.model.entity.Imagen;
import es.ikergarciagutierrez.accdat.flora.viewmodel.AddImagenViewModel;

/**
 * Clase que añade una imagen al objeto Flora que decidamos guardando en la base de datos como un
 * objeto Imagen
 */
public class EditImagenActivity extends AppCompatActivity {

    /**
     * Campos de la clase
     */
    private Context context;

    private ActivityResultLauncher<Intent> launcher;
    private AddImagenViewModel aivm;

    private ImageView ivSelectImagen;
    private EditText etIdFlora, etNombreImagen, etDescripcionImagen;
    private Button btAddImagen, btCancelarAdd;

    private Flora flora;

    private Intent resultadoImagen = null;

    private String adAñadirImagen = "¿Añadir esta imagen a X?";

    /**
     * Método que infla el layout
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_imagen);
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
        etIdFlora = findViewById(R.id.etIdFlora);
        etNombreImagen = findViewById(R.id.etNombreImagen);
        etDescripcionImagen = findViewById(R.id.etDescripcionImagen);
        btAddImagen = findViewById(R.id.btAñadir);
        btCancelarAdd = findViewById(R.id.btCancelarAñadir);

        defineImageViewSelectImagenListener();
        defineButtonAddImagenListener();
        defineButtonCancelarAddListener();

        Bundle bundle = getIntent().getExtras();
        flora = bundle.getParcelable("idFlora");

        etIdFlora.setText(flora.getNombre());
        etIdFlora.setEnabled(false);

    }

    /**
     * Método que guarda la imagen que hemos seleccionado
     */
    private void defineButtonAddImagenListener() {
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
    }

    /**
     * Método que cancela la activity
     */
    private void defineButtonCancelarAddListener() {
        btCancelarAdd.setOnClickListener(v -> {
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
        });
    }

    /**
     * Método que selecciona la imagen que hemos obtenido de nuestro dispositivo
     */
    private void defineImageViewSelectImagenListener() {
        ivSelectImagen.setOnClickListener(v -> {
            Intent intent = getContentIntent();
            launcher.launch(intent);
        });
    }

    /**
     * Método que guarda en la base de datos el objeto Imagen con los datos que hemos rellanado y la
     * foto que hemos seleccionado de nuestro dispositivo
     */
    private void uploadDataImage() {

        Random seed = new Random(999999999);
        Date date = Calendar.getInstance().getTime();
        String nombre = etNombreImagen.getText().toString() + "_" + seed.nextDouble() * date.getTime();

        if (!(nombre.trim().isEmpty() || resultadoImagen == null)) {

            Imagen imagen = new Imagen();
            imagen.idflora = flora.getId();
            imagen.nombre = nombre;
            imagen.descripcion = etDescripcionImagen.getText().toString();

            aivm.saveImagen(resultadoImagen, imagen);

            showToast(R.string.toast_añadirImagen);
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
    private ActivityResultLauncher<Intent> getLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
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
    private Intent getContentIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
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
