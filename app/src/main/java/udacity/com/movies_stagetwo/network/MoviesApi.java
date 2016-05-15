package udacity.com.movies_stagetwo.network;

/**
 * Created by techjini on 31/12/15.
 */
public class MoviesApi {

    private final String baseHostUrl;
    public static final String SORT_BY_POPULAR = "popularity.desc";
    public static final String SORT_BY_RATING = "vote_average.desc";
    public static final String IMG_URL = "http://image.tmdb.org/t/p/w185/";

    public MoviesApi() {
        this("http://api.themoviedb.org");
    }

    public MoviesApi(String baseHostUrl) {
        this.baseHostUrl = baseHostUrl;
    }

    public String getBaseHostUrl() {
        return this.baseHostUrl;
    }
}
