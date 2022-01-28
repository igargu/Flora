package es.ikergarciagutierrez.accdat.flora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.ikergarciagutierrez.accdat.flora.model.entity.CreateResponse;
import es.ikergarciagutierrez.accdat.flora.model.entity.RowsResponse;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import es.ikergarciagutierrez.accdat.flora.model.api.FloraClient;
import es.ikergarciagutierrez.accdat.flora.viewmodel.MainActivityViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    FloraClient floraClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {

        MainActivityViewModel mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        MutableLiveData<ArrayList<Flora>> floraList = mavm.getFloraLiveData();
        floraList.observe(this, floraPlural -> {
            Log.v("xyzyx", floraPlural.toString());
        });
        mavm.getFlora();

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://informatica.ieszaidinvergeles.org:10008/ad/felixRLDFApp/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        floraClient = retrofit.create(FloraClient.class);

        Call<ArrayList<Flora>> call = floraClient.getFlora();
        call.enqueue(new Callback<ArrayList<Flora>>() {
            @Override
            public void onResponse(Call<ArrayList<Flora>> call, Response<ArrayList<Flora>> response) {
                Log.v("xyzyx", response.body().toString());
            }

            @Override
            public void onFailure(Call<ArrayList<Flora>> call, Throwable t) {
                Log.v("xyzyx", t.getLocalizedMessage());
            }
        });

        Call<Flora> call2 = floraClient.getFlora(1);
        call2.enqueue(new Callback<Flora>() {
            @Override
            public void onResponse(Call<Flora> call, Response<Flora> response) {
                Log.v("xyzyx", response.body().toString());
            }

            @Override
            public void onFailure(Call<Flora> call, Throwable t) {
                Log.v("xyzyx", t.getLocalizedMessage());
            }
        });

        Flora flora = new Flora();
        flora.setNombre("La montañaaaaaaa");
        Call<CreateResponse> call3 = floraClient.createFlora(flora);
        call3.enqueue(new Callback<CreateResponse>() {
            @Override
            public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
                Log.v("xyzyx", response.body().toString());
            }

            @Override
            public void onFailure(Call<CreateResponse> call, Throwable t) {
                Log.v("xyzyx", t.getLocalizedMessage());
            }
        });

        flora.setNombre("Paco");
        flora.setAltitud("AHHHHHHHHHH");
        Call<RowsResponse> call4 = floraClient.editFlora(1, flora);
        call4.enqueue(new Callback<RowsResponse>() {
            @Override
            public void onResponse(Call<RowsResponse> call, Response<RowsResponse> response) {
                Log.v("xyzyx", response.body().toString());
            }

            @Override
            public void onFailure(Call<RowsResponse> call, Throwable t) {
                Log.v("xyzyx", t.getLocalizedMessage());
            }
        });

        Call<RowsResponse> call5 = floraClient.deleteFlora(1);
        call5.enqueue(new Callback<RowsResponse>() {
            @Override
            public void onResponse(Call<RowsResponse> call, Response<RowsResponse> response) {
                Log.v("xyzyx", response.body().toString());
            }

            @Override
            public void onFailure(Call<RowsResponse> call, Throwable t) {
                Log.v("xyzyx", t.getLocalizedMessage());
            }
        });*/
    }
}

/*

    Autentizicación desde Laravel y desde Retrofit

 */