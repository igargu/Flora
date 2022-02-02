package es.ikergarciagutierrez.accdat.flora.model.api;

import java.util.ArrayList;

import es.ikergarciagutierrez.accdat.flora.model.entity.CreateResponse;
import es.ikergarciagutierrez.accdat.flora.model.entity.RowsResponse;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FloraClient {

    @DELETE("api/flora/{id}")
    Call<RowsResponse> deleteFlora(@Path("id") long id);

    @GET("api/flora/{id}")
    Call<Flora> getFlora(@Path("id") long id);

    @GET("api/flora")
    Call<ArrayList<Flora>> getFlora();

    @POST("api/flora")
    Call<CreateResponse> createFlora(@Body Flora flora);

    @PUT("api/flora/{id}")
    Call<RowsResponse> editFlora(@Path("id") long id, @Body Flora flora);



}