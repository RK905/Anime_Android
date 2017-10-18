package com.zaki.episodes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "ep";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvEpisodes = (ListView) findViewById(R.id.lvEpisodes);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        lvEpisodes.setAdapter(adapter);

        Retrofit retrofit = APIClient.getClient();
        Interface service = retrofit.create(Interface.class);

        Call<ServerResponse> call = service.post("https://www2.animetv.to/anime/07-ghost.html");

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse res = response.body();

                adapter.add(res.desc.get(0).getDesc());

                for(int i = 0; i < res.episodes.size(); i ++) {
                    ServerResponse.Episode ep = res.episodes.get(i);
                    adapter.add(ep.getTitle());
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(TAG, "error: " + t.getMessage());
            }
        });
    }
}
