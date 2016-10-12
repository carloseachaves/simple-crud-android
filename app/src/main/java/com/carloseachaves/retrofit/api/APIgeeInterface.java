package com.carloseachaves.retrofit.api;

import com.carloseachaves.retrofit.model.APIgeeResponseSuccess;
import com.carloseachaves.retrofit.model.Book;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIgeeInterface {

    @GET("books?limit=100")
    Call<APIgeeResponseSuccess> listBooks();

    @GET("books/{uuid}")
    Call<APIgeeResponseSuccess> getById(@Path("uuid") String uuid);


    @POST("books")
    @Headers({
            "Content-type: application/json"
    })
    Call<APIgeeResponseSuccess> addBook(@Body Book book);

    @PUT("books/{uuid}")
    @Headers({
            "Content-type: application/json"
    })
    Call<APIgeeResponseSuccess> updateBook(@Body Book book, @Path("uuid") String uuid);

    @DELETE("books/{uuid}")
    Call<APIgeeResponseSuccess> removeBook(@Path("uuid") String uuid);




}
