package udacity.com.movies_stagetwo.network;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import udacity.com.movies_stagetwo.datamodel.BaseResponse;
import udacity.com.movies_stagetwo.datamodel.MoviesResponse;
import udacity.com.movies_stagetwo.datamodel.ReviewResponse;
import udacity.com.movies_stagetwo.datamodel.TrailerResponse;

/**
 * Created by techjini on 14/1/16.
 */
public class MoviesManager {
    public String API_KEY = "";
    private OnCommunicationListener mOnCommunicationListener;
    public static final int API_ID_MOVIE_LIST = 111;
    public static final int API_ID_MOVIE_TRAILOR = 112;
    public static final int API_ID_MOVIE_REVIEW = 113;


    public MoviesManager() {

    }


    public void getMoviesList(OnCommunicationListener listListener, String sortBy) {
        mOnCommunicationListener = listListener;
        MovieApiClient apiClient = MovieApiClient.getInstance();
        MoviesService service = apiClient.getService(MoviesService.class);
        final Call<MoviesResponse> model = service.getMoviesList(sortBy, API_KEY);
        try {
            model.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Response<MoviesResponse> response, Retrofit retrofit) {
                    mOnCommunicationListener.onSuccess(API_ID_MOVIE_LIST, response.body());
                }

                @Override
                public void onFailure(Throwable t) {
                    mOnCommunicationListener.onFailure(API_ID_MOVIE_LIST, t.toString());
                }
            });
        } catch (Exception e) {
            mOnCommunicationListener.onException(API_ID_MOVIE_LIST, e.toString());
        }

    }

    public void getReviewsForMovie(OnCommunicationListener listListener, int movieId) {
        mOnCommunicationListener = listListener;
        MovieApiClient apiClient = MovieApiClient.getInstance();
        MoviesService service = apiClient.getService(MoviesService.class);
        Call<ReviewResponse> model = service.getReviewsForMovie(movieId, API_KEY);
        try {
            model.enqueue(new Callback<ReviewResponse>() {
                @Override
                public void onResponse(Response<ReviewResponse> response, Retrofit retrofit) {
                    mOnCommunicationListener.onSuccess(API_ID_MOVIE_REVIEW, response.body());
                }

                @Override
                public void onFailure(Throwable t) {
                    mOnCommunicationListener.onFailure(API_ID_MOVIE_REVIEW, t.toString());
                }
            });
        } catch (Exception e) {
            mOnCommunicationListener.onException(API_ID_MOVIE_REVIEW, e.toString());
        }

    }

    public void getTrailorsForMovie(OnCommunicationListener listListener, int movieId) {
        mOnCommunicationListener = listListener;
        MovieApiClient apiClient = MovieApiClient.getInstance();
        MoviesService service = apiClient.getService(MoviesService.class);
        Call<TrailerResponse> model = service.getTrailorsForMovie(movieId, API_KEY);
        try {
            model.enqueue(new Callback<TrailerResponse>() {
                @Override
                public void onResponse(Response<TrailerResponse> response, Retrofit retrofit) {
                    mOnCommunicationListener.onSuccess(API_ID_MOVIE_TRAILOR, response.body());
                }

                @Override
                public void onFailure(Throwable t) {
                    mOnCommunicationListener.onFailure(API_ID_MOVIE_TRAILOR, t.toString());
                }
            });
        } catch (Exception e) {
            mOnCommunicationListener.onException(API_ID_MOVIE_TRAILOR, e.toString());
        }

    }


    public interface OnCommunicationListener {
        void onSuccess(int apiId, BaseResponse response);

        void onFailure(int apiId, String msg);

        void onException(int apiId, String msg);
    }


}
