package geeksdobyte.com.animefreakz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by John-PC on 10/18/2017.
 */

public class EpisodesResponse {
    @SerializedName("episodes")
    public List<Episode> episodes;
    @SerializedName("desc")
    public List<Desc> desc;

    public class Episode {
        @SerializedName("title")
        public String title;
        @SerializedName("href")
        public String href;

        public String getHref() { return href; }
        public String getTitle() {
            return title;
        }
    }

    public class Desc {
        @SerializedName("content")
        public String desc;

        public String getDesc() {
            return desc;
        }
    }
}
