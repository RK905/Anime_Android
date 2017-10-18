package com.zaki.episodes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvEpisodes = (ListView) findViewById(R.id.lvEpisodes);
        final TextView txtDesc = (TextView) findViewById(R.id.txtDesc);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        lvEpisodes.setAdapter(adapter);

        Retrofit retrofit = APIClient.getClient();

        Interface service = retrofit.create(Interface.class);
        Call<ServerResponse> call = service.get();

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse res = response.body();

                txtDesc.setText(res.desc.get(0).getDesc());

                for(int i = 0; i < res.episodes.size(); i ++) {
                    ServerResponse.Episode ep = res.episodes.get(i);
                    adapter.add(ep.getTitle());

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d("ep", t.getMessage());
            }
        });

    }
}
