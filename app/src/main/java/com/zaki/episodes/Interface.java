package com.zaki.episodes;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by John-PC on 10/18/2017.
 */

public interface Interface {

    //This method is used for "GET"
    @GET("/")
    Call<ServerResponse> get(

    );

}
