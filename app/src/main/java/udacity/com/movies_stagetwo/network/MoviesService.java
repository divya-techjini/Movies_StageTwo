package udacity.com.movies_stagetwo.network;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;
import udacity.com.movies_stagetwo.datamodel.MoviesResponse;
import udacity.com.movies_stagetwo.datamodel.ReviewResponse;
import udacity.com.movies_stagetwo.datamodel.TrailerResponse;

/**
 * Created by techjini on 14/1/16.
 */
public interface MoviesService {

    @GET("/3/discover/movie")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<MoviesResponse> getMoviesList(@Query("sort_by") String sortBy, @Query("api_key") String api_key);

    @GET("/3/movie/{id}/videos")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<TrailerResponse> getTrailorsForMovie(@Path("id") int id, @Query("api_key") String api_key);

    @GET("/3/movie/{id}/reviews")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ReviewResponse> getReviewsForMovie(@Path("id") int id, @Query("api_key") String api_key);


}
