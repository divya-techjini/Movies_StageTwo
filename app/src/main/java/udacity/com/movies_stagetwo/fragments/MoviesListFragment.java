package udacity.com.movies_stagetwo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import udacity.com.movies_stagetwo.MovieApplication;
import udacity.com.movies_stagetwo.R;
import udacity.com.movies_stagetwo.adapters.MoviesListAdapter;
import udacity.com.movies_stagetwo.database.MovieHelper;
import udacity.com.movies_stagetwo.datamodel.BaseResponse;
import udacity.com.movies_stagetwo.datamodel.MoviesItem;
import udacity.com.movies_stagetwo.datamodel.MoviesResponse;
import udacity.com.movies_stagetwo.network.MoviesApi;
import udacity.com.movies_stagetwo.network.MoviesManager;

public class MoviesListFragment extends BaseFragment implements MoviesManager.OnCommunicationListener,
        MoviesListAdapter.OnMovieClickListener {

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.txtEmpty)
    TextView txtEmpty;
    private MoviesManager mMoviesManager;
    private MoviesListAdapter adapter;
    private int selectedMenu = -1;
    OnFragmentInteractionListener mListener;
    private final int MOVIE_POPULAR = 1;
    private final int MOVIE_RATED = 2;
    private final int MOVIE_FAV = 3;


    public MoviesListFragment() {
        // Required empty public constructor
    }

    public static MoviesListFragment newInstance() {
        return new MoviesListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_movies_list, container, false);
        mMoviesManager = new MoviesManager();
        ButterKnife.bind(this, rootView);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        showLoadingDialog(R.string.msg_loading);
        mMoviesManager.getMoviesList(MoviesListFragment.this, MoviesApi.SORT_BY_POPULAR);
        selectedMenu = MOVIE_POPULAR;
        getActivity().setTitle(R.string.title_movie_list_pop);
        setHasOptionsMenu(true);
        return rootView;
    }


    private void setListView(List<MoviesItem> items) {
        txtEmpty.setVisibility(View.GONE);
        adapter = new MoviesListAdapter(items, MoviesListFragment.this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (adapter.getItemCount() == 0) {
            txtEmpty.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movies_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_popular) {
            selectedMenu = MOVIE_POPULAR;
            showLoadingDialog(R.string.msg_loading);
            getActivity().setTitle(R.string.title_movie_list_pop);
            mMoviesManager.getMoviesList(MoviesListFragment.this, MoviesApi.SORT_BY_POPULAR);
            return true;
        } else if (id == R.id.action_rating) {
            selectedMenu = MOVIE_RATED;
            showLoadingDialog(R.string.msg_loading);
            getActivity().setTitle(R.string.title_movie_list_rate);
            mMoviesManager.getMoviesList(MoviesListFragment.this, MoviesApi.SORT_BY_RATING);
            return true;
        } else if (id == R.id.action_fav_list) {
            selectedMenu = MOVIE_FAV;
            getActivity().setTitle(R.string.title_movie_fav);
            List<MoviesItem> items = MovieHelper.getMovieList(MovieApplication.getInstance().getDBHepler());
            setListView(items);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateFav() {
        if (selectedMenu == MOVIE_FAV) {
            getActivity().setTitle(R.string.title_movie_fav);
            List<MoviesItem> items = MovieHelper.getMovieList(MovieApplication.getInstance().getDBHepler());
            setListView(items);
        }

    }

    @Override
    public void onSuccess(int apiId, BaseResponse response) {
        hideLoadingDialog();
        MoviesResponse res = (MoviesResponse) response;
        if (res == null || res.getResults() == null || res.getResults().size() == 0) {
            txtEmpty.setVisibility(View.VISIBLE);
        } else {
            setListView(res.getResults());
        }

    }

    @Override
    public void onFailure(int apiId, String url) {

    }

    @Override
    public void onException(int apiId, String url) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onMovieSelected(MoviesItem movieItem) {

        mListener.onMovieItemClick(movieItem);
    }

    public interface OnFragmentInteractionListener {
        void onMovieItemClick(MoviesItem item);
    }

}
