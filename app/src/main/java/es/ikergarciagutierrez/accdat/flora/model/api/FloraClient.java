package es.ikergarciagutierrez.accdat.flora.model.api;

import java.util.ArrayList;

import es.ikergarciagutierrez.accdat.flora.model.entity.CreateResponse;
import es.ikergarciagutierrez.accdat.flora.model.entity.RowsResponse;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FloraClient {

    @DELETE("api/flora/{id}")
    Call<RowsResponse> deleteFlora(@Path("id") long id);

    @GET("api/flora/{id}")
    Call<Flora> getFlora(@Path("id") long id);

    /*@GET("api/flora")
    Call<ArrayList<Flora>> getFlora();//*/

    @GET("api/flora")
    Call<ArrayList<Flora>> getFlora(@Query("correo") String correo);

    @POST("api/flora")
    Call<CreateResponse> createFlora(@Body Flora flora);

    @PUT("api/flora/{id}")
    Call<RowsResponse> editFlora(@Path("id") long id, @Body Flora flora);

    @Multipart
    @POST("api/imagen/subir")
    Call<Long> subirImagen(@Part MultipartBody.Part file, @Part("idflora") long idFlora, @Part("descripcion") String descripcion);

}
