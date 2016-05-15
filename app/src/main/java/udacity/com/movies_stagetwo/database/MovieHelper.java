/**
 *
 */
package udacity.com.movies_stagetwo.database;

import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;
import java.util.List;

import udacity.com.movies_stagetwo.datamodel.MoviesItem;

/**
 * @author techjini
 */
public class MovieHelper {


    public static boolean removeMovie(DatabaseHelper helper, int id) {

        try {
            final Dao<MoviesItem, Integer> companyDao = helper.getMoviesDao();
            companyDao.deleteById(id);
        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

        return true;
    }

    public static boolean isExists(DatabaseHelper helper, int id) {

        try {
            final Dao<MoviesItem, Integer> companyDao = helper.getMoviesDao();
            return companyDao.idExists(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }


    public static boolean addMovie(DatabaseHelper helper, MoviesItem item) {

        try {
            final Dao<MoviesItem, Integer> companyDao = helper.getMoviesDao();
            companyDao.createOrUpdate(item);
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }

        return true;
    }

    public static List<MoviesItem> getMovieList(DatabaseHelper helper) {
        List<MoviesItem> moviesItems = new ArrayList<>();
        try {
            final Dao<MoviesItem, Integer> companyDao = helper.getMoviesDao();
            moviesItems = companyDao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();


        }

        return moviesItems;
    }


}
