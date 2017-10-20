package geeksdobyte.com.animefreakz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.R.id.input;


public class Detail extends AppCompatActivity {
    public final String TAG = "ep";
    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i = getIntent();
        String val = i.getStringExtra("AnimeImage");
        String valLink = i.getStringExtra("AnimeLink");
        ImageView animeImg = (ImageView)findViewById(R.id.ivAnime);
        Glide.with(this).load(val).into(animeImg);

        ListView lvEpisodes = (ListView) findViewById(R.id.lvEpisodes);

        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        final EpisodeList adapter = new EpisodeList(this, android.R.layout.simple_list_item_1);
        lvEpisodes.setAdapter(adapter);

        Retrofit retrofit = APIClient.getClient();
        final Interface service = retrofit.create(Interface.class);

        Call<EpisodesResponse> call = service.post_episodes(valLink);

        call.enqueue(new Callback<EpisodesResponse>() {
            @Override
            public void onResponse(Call<EpisodesResponse> call, Response<EpisodesResponse> response) {

                EpisodesResponse res = response.body();

                String desc = res.desc.get(0).getDesc();
                adapter.addItem( desc, "" );

                for(int i = 0; i < res.episodes.size(); i ++) {
                    EpisodesResponse.Episode ep = res.episodes.get(i);
                    adapter.addItem( ep.getTitle(), ep.getHref() );
                }
            }

            @Override
            public void onFailure(Call<EpisodesResponse> call, Throwable t) {
                Log.d(TAG, "error: " + t.getMessage());
            }
        });

        lvEpisodes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i <= 0)
                    return;

                String href = adapter.getHref(i);
                Call<LinkResponse> call = service.post_link(href);

                call.enqueue(new Callback<LinkResponse>() {
                    @Override
                    public void onResponse(Call<LinkResponse> call, Response<LinkResponse> response) {

                        LinkResponse res = response.body();

                        Intent intent = new Intent(getApplicationContext(), Viewer.class);
                        intent.putExtra("href", res.streamango);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<LinkResponse> call, Throwable t) {
                        Log.d(TAG, "error: " + t.getMessage());
                    }
                });

            }
        });

    }

}