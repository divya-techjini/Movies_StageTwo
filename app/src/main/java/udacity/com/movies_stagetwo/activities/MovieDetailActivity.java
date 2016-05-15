package udacity.com.movies_stagetwo.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import udacity.com.movies_stagetwo.R;
import udacity.com.movies_stagetwo.fragments.MovieDetailFragment;

public class MovieDetailActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        setTitle(R.string.title_details);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                MovieDetailFragment.newInstance(getIntent().getExtras())).commit();
        getSupportFragmentManager().executePendingTransactions();
    }


}
