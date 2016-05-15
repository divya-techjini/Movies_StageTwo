package udacity.com.movies_stagetwo.utils;

import java.util.Comparator;

import udacity.com.movies_stagetwo.datamodel.MoviesItem;

/**
 * Created by techjini on 21/3/16.
 */

public class RatingComparator implements Comparator<MoviesItem> {


    @Override
    public int compare(MoviesItem lhs, MoviesItem rhs) {
        if (lhs.getVote_average() == rhs.getVote_average())
            return 0;
        else if (lhs.getVote_average() > rhs.getVote_average())
            return 1;
        else
            return -1;
    }
}
