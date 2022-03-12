package es.ikergarciagutierrez.accdat.flora.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.api.FloraClient;
import es.ikergarciagutierrez.accdat.flora.model.entity.CreateResponse;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import es.ikergarciagutierrez.accdat.flora.model.entity.Imagen;
import es.ikergarciagutierrez.accdat.flora.model.entity.RowsResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    /**
     * Campos de la clase
     */
    private Context context;
    private static FloraClient floraClient;

    private MutableLiveData<ArrayList<Flora>> floraLiveData = new MutableLiveData<>();

    private MutableLiveData<Long> addFloraLiveData = new MutableLiveData<>();
    private MutableLiveData<Long> addImagenLiveData = new MutableLiveData<>();
    private MutableLiveData<Long> editFloraLiveData = new MutableLiveData<>();
    private MutableLiveData<Long> deleteFloraLiveData = new MutableLiveData<>();

    /**
     * Inicialización de las variables estáticas
     */
    static {
        floraClient = getFloraClient();
    }

    /**
     * Constructor de la clase
     *
     * @param context Context -> Contexto de la clase
     */
    public Repository(Context context) {
        this.context = context;
    }

    /**
     * Obtener objeto FloraClient
     *
     * @return retrofit.create(FloraClient.class); -> Objeto FloraClient
     */
    private static FloraClient getFloraClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://informatica.ieszaidinvergeles.org:10008/ad/felixRLDFApp/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(FloraClient.class);
    }

    /**
     * Obtener el array de floras
     *
     * @return floraLiveData
     */
    public MutableLiveData<ArrayList<Flora>> getFloraLiveData() {
        return floraLiveData;
    }

    /**
     * Obtener el array para añadir floras
     *
     * @return addFloraLiveData
     */
    public MutableLiveData<Long> getAddFloraLiveData() {
        return addFloraLiveData;
    }

    /**
     * Borrar un flora según su id
     *
     * @param id de la flora que queremos borrar
     */
    public void deleteFlora(long id) {
        Call<RowsResponse> call = floraClient.deleteFlora(id);
        call.enqueue(new Callback<RowsResponse>() {
            @Override
            public void onResponse(Call<RowsResponse> call, Response<RowsResponse> response) {
                deleteFloraLiveData.setValue(response.body().rows);
            }

            @Override
            public void onFailure(Call<RowsResponse> call, Throwable t) {

            }
        });
    }

    /**
     * Obtener todos los objetos Flora e insertarlos en floraLiveData
     */
    public void getFlora() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.prefs_file), Context.MODE_PRIVATE);
        Call<ArrayList<Flora>> call = floraClient.getFlora(sharedPreferences.getString("correo", ""));
        call.enqueue(new Callback<ArrayList<Flora>>() {
            @Override
            public void onResponse(Call<ArrayList<Flora>> call, Response<ArrayList<Flora>> response) {
                floraLiveData.setValue(response.body());
                Log.v("xyzyx", response.body().toString());
            }

            @Override
            public void onFailure(Call<ArrayList<Flora>> call, Throwable t) {
                Log.v("xyzyx", t.getLocalizedMessage());
            }
        });
    }

    /**
     * Crear un objeto flora
     *
     * @param flora Objeto flora que queremos crear
     */
    public void createFlora(Flora flora) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.prefs_file), Context.MODE_PRIVATE);
        flora.setCorreo(sharedPreferences.getString("correo", ""));
        Call<CreateResponse> call = floraClient.createFlora(flora);
        call.enqueue(new Callback<CreateResponse>() {
            @Override
            public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
                addFloraLiveData.setValue(response.body().id);
                Log.v("xyzyx", response.body().toString());
            }

            @Override
            public void onFailure(Call<CreateResponse> call, Throwable t) {
                Log.v("xyzyx", t.getLocalizedMessage());
            }
        });
    }

    /**
     * Editar un objeto flora con otro según su id
     *
     * @param id    id del objeto flora que queremos editar
     * @param flora objeto flora por el queremos sustituir el existente
     */
    public void editFlora(long id, Flora flora) {
        Call<RowsResponse> call = floraClient.editFlora(id, flora);
        call.enqueue(new Callback<RowsResponse>() {
            @Override
            public void onResponse(Call<RowsResponse> call, Response<RowsResponse> response) {
                try {
                    editFloraLiveData.setValue(response.body().rows);
                    showToast(R.string.toast_editarFlora);
                } catch (NullPointerException e) {
                    Log.v("xyzyx", response.body().toString());
                    //showToast(R.string.toast_nameExist);
                }
            }

            @Override
            public void onFailure(Call<RowsResponse> call, Throwable t) {

            }
        });
    }

    /**
     * Guardar un imagen en la bd
     *
     * @param intent
     * @param imagen
     */
    public void saveImagen(Intent intent, Imagen imagen) {
        String nombre = "xyzyx.abc";
        copyData(intent, nombre);
        File file = new File(context.getExternalFilesDir(null), nombre);
        subirImagen(file, imagen);
    }

    /**
     * Subir imagen a la bd
     *
     * @param file
     * @param imagen
     */
    private void subirImagen(File file, Imagen imagen) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("photo", imagen.nombre, requestFile);
        Call<Long> call = floraClient.subirImagen(body, imagen.idflora, imagen.descripcion);
        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                addImagenLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

            }
        });
    }

    private boolean copyData(Intent data, String name) {
        boolean result = true;
        Uri uri = data.getData();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = context.getContentResolver().openInputStream(uri);
            out = new FileOutputStream(new File(context.getExternalFilesDir(null), name));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            result = false;
            Log.v("xyzyx", e.toString());
        }
        return result;
    }

    /**
     * Método que muestra un Toast personalizado
     *
     * @param message Mensaje que queremos que aparezca en el Toast
     */
    private void showToast(int message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        View toastView = toast.getView();
        toastView.getBackground().setColorFilter(context.getResources().getColor(R.color.primary_dark_color), PorterDuff.Mode.SRC_IN);
        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
        tv.setTextColor(Color.WHITE);
        toast.show();
    }

}
