package udacity.com.movies_stagetwo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import udacity.com.movies_stagetwo.R;
import udacity.com.movies_stagetwo.datamodel.MoviesItem;
import udacity.com.movies_stagetwo.fragments.MovieDetailFragment;
import udacity.com.movies_stagetwo.fragments.MoviesListFragment;

public class MoviesListActivity extends BaseActivity implements MoviesListFragment.OnFragmentInteractionListener, MovieDetailFragment.OnFragmentInteractionListener {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    MovieDetailFragment fragment;
    MoviesListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        listFragment = MoviesListFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_list,
                listFragment).commit();
        RelativeLayout detailLayout = (RelativeLayout) findViewById(R.id.fragment_container_detail);
        if (detailLayout != null) {
            fragment = MovieDetailFragment.newInstance(new Bundle());
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_detail,
                    fragment).commit();
        }
        getSupportFragmentManager().executePendingTransactions();
    }

    @Override
    public void onMovieItemClick(MoviesItem item) {
        if (fragment != null && !fragment.isDetached()) {
            fragment.updateUI(item);
        } else {
            Intent intent = new Intent(MoviesListActivity.this, MovieDetailActivity.class);
            intent.putExtra(MovieDetailFragment
                    .EXTR_MOVIE, item);
            startActivity(intent);

        }
    }

    @Override
    public void onFavClick(MoviesItem item, boolean isFav) {
        if (listFragment != null && !listFragment.isDetached()) {
            listFragment.updateFav();
        }
    }
}
