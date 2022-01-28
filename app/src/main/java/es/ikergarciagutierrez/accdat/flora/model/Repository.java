package es.ikergarciagutierrez.accdat.flora.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import es.ikergarciagutierrez.accdat.flora.model.api.FloraClient;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
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

    public MutableLiveData<ArrayList<Flora>> getFloraLiveData() {
        return floraLiveData;
    }

    public void deleteFlora(long id) {

    }


    public void getFlora(long id) {

    }

    /**
     * Obtener todos los objetos Flora e insertarlos en floraLiveData
     */
    public void getFlora() {
        Call<ArrayList<Flora>> call = floraClient.getFlora();
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

    public void createFlora(Flora flora) {

    }

    public void editFlora(long id, Flora flora) {

    }

}
