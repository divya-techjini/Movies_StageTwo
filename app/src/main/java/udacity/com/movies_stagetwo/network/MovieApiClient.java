package udacity.com.movies_stagetwo.network;

import java.util.concurrent.ConcurrentHashMap;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by techjini on 31/12/15.
 */
public class MovieApiClient {
    final ConcurrentHashMap<Class, Object> services;
    Retrofit retrofit;
    private static MovieApiClient sInstance;


    public static MovieApiClient getInstance() {
        if(sInstance == null) {
            sInstance = new MovieApiClient();
        }
        return sInstance;
    }

    private MovieApiClient() {
        services = new ConcurrentHashMap<>();
        retrofit = new Retrofit.Builder().client(new MovieHttpClient()).
                baseUrl(new MoviesApi().getBaseHostUrl()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public  <T> T getService(Class<T> cls) {
        return this.getRetrofitService(this.retrofit, cls);
    }

    public  <T> T getRetrofitService(Retrofit retrofit, Class<T> cls) {
        if(!this.services.contains(cls)) {
            this.services.putIfAbsent(cls, retrofit.create(cls));
        }
        return (T) this.services.get(cls);
    }

}
