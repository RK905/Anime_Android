package geeksdobyte.com.animefreakz;

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
    Call<EpisodesResponse> get(

    );

    @FormUrlEncoded
    @POST("/php/kissanime_ios.php")
    Call<EpisodesResponse> post_episodes(
        @Field("Episodes") String query
    );


    @FormUrlEncoded
    @POST("/php/kissanime_ios.php")
    Call<LinkResponse> post_link(
            @Field("LinkIos") String query
    );
}
