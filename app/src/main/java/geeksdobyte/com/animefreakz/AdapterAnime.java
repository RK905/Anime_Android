package geeksdobyte.com.animefreakz;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import com.bumptech.glide.Glide;
import java.util.Collections;
import java.util.List;

/**
 * Created by rkwork on 10/11/17.
 */

public class AdapterAnime extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<AnimeData> data= Collections.emptyList();
    AnimeData current;
    int currentPos=0;


    public AdapterAnime(Context context, List<AnimeData> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_anime, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        final AnimeData current=data.get(position);
        myHolder.textAnime.setText(current.animeTitle);

        // load image into imageview using glide
        Glide.with(context).load(current.animeThumb)
                .placeholder(R.mipmap.ic_img_error)
                .error(R.mipmap.ic_img_error)
                .into(myHolder.ivAnime);


        myHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view ){
            Intent intent = new Intent(context.getApplicationContext(), Detail.class);
            intent.putExtra("AnimeImage", current.animeThumb);
            intent.putExtra("AnimeLink", current.animeLink);
            context.startActivity(intent);

        } });
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView textAnime;
        ImageView ivAnime;
        RelativeLayout relativeLayout;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textAnime = (TextView) itemView.findViewById(R.id.textAnime);
            ivAnime = (ImageView) itemView.findViewById(R.id.ivAnime);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relLayout);
        }

    }

}
