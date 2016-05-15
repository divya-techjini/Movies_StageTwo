package udacity.com.movies_stagetwo.network;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by techjini on 31/12/15.
 */
public class MovieHttpClient extends OkHttpClient {

    public MovieHttpClient() {
        super();
        interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder builder = original.newBuilder();


                Request request = builder.method(original.method(), original.body())
                        .build();
                Log.e("sdfgf",request.urlString());
                return chain.proceed(request);
            }
        });
    }


}
