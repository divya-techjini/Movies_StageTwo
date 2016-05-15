package udacity.com.movies_stagetwo.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import udacity.com.movies_stagetwo.datamodel.MoviesItem;

/**
 * Created by techjini on 18/12/15.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<MoviesItem, Integer> moviesDao = null;
    private RuntimeExceptionDao<MoviesItem, Integer> simpleRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, MoviesItem.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Dao<MoviesItem, Integer> getMoviesDao() throws SQLException {
        if (moviesDao == null) {
            moviesDao = getDao(MoviesItem.class);
        }
        return moviesDao;
    }


    @Override
    public void close() {
        super.close();
        moviesDao = null;
    }
}

