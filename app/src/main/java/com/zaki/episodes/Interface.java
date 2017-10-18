package com.zaki.episodes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by John-PC on 10/18/2017.
 */

public interface Interface {

    @GET("/")
    Call<ServerResponse> get(

    );

    @FormUrlEncoded
    @POST("/php/kissanime_ios.php")
    Call<ServerResponse> post(
        @Field("Episodes") String query
    );

}
