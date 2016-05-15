package udacity.com.movies_stagetwo.adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import udacity.com.movies_stagetwo.R;
import udacity.com.movies_stagetwo.datamodel.MoviesItem;
import udacity.com.movies_stagetwo.fragments.OverViewFragment;
import udacity.com.movies_stagetwo.fragments.ReviewFragment;
import udacity.com.movies_stagetwo.fragments.TrailerFragment;

/**
 * Created by techjini on 14/5/16.
 */
public class MovieTabAdapter extends FragmentStatePagerAdapter {

    private Activity mActivity;
    private MoviesItem mMovieItem;

    public MovieTabAdapter(FragmentManager fm, Activity activity, MoviesItem item) {
        super(fm);
        mActivity = activity;
        mMovieItem = item;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TrailerFragment.newInstance(mMovieItem.getId());
            case 1:
                return ReviewFragment.newInstance(mMovieItem.getId());
            case 2:
                return OverViewFragment.newInstance(mMovieItem.getOverview());
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = mActivity.getString(R.string.lbl_trailors);
                break;
            case 1:
                title = mActivity.getString(R.string.lbl_reviews);
                break;
            case 2:
                title = mActivity.getString(R.string.lbl_summery);
        }
        return title;
    }

    @Override
    public int getCount() {
        return 3;
    }
}


