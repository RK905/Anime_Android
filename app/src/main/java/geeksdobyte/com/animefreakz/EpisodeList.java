package geeksdobyte.com.animefreakz;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John-PC on 10/20/2017.
 */

public class EpisodeList extends ArrayAdapter<String> {

    private class Item{
        public String title;
        public String href;

        public Item(String title, String href) {
            this.title = title;
            this.href = href;
        }
    }

    List<Item> episodes;

    public EpisodeList(Context context, int resID) {
        super(context, resID);

        episodes = new ArrayList<Item>();
    }

    public void addItem(String title, String href) {
        episodes.add(new Item(title, href));
        add(title);
    }

    public String getHref(int i) {
        return episodes.get(i).href;
    }


}
