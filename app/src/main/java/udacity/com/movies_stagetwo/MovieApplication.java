package udacity.com.movies_stagetwo;

import android.app.Application;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import udacity.com.movies_stagetwo.database.DatabaseHelper;

/**
 * Created by techjini on 23/12/15.
 */
public class MovieApplication extends Application {

    private static MovieApplication sIntance;

    private DatabaseHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        sIntance = this;
    }

    public static MovieApplication getInstance() {
        return sIntance;
    }


    public DatabaseHelper getDBHepler() {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return dbHelper;
    }


    public void closeDB() {
        if (dbHelper != null) {
            dbHelper.close();
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }

}
