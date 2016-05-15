package udacity.com.movies_stagetwo.datamodel;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by techjini on 14/12/15.
 */
public class MoviePreference {
    private static MoviePreference sInstance;
    private static SharedPreferences sPref;
    private static SharedPreferences.Editor sEditor;
    public static final String MOVIE_FAVOURITE = "centerr_status";

    private MoviePreference(Context context) {
        sPref = context.getSharedPreferences("com.movies.android", Context.MODE_PRIVATE);
        sEditor = sPref.edit();
    }

    public static MoviePreference getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MoviePreference(context);
        }
        return sInstance;
    }


    public boolean getBoolean(String key) {
        return sPref.getBoolean(key, false);

    }

    public void setBoolean(String key, boolean value) {
        sEditor.putBoolean(key, value).commit();
    }

    public int getInt(String key) {
        return sPref.getInt(key, 0);
    }

    public long getLong(String key) {
        return sPref.getLong(key, 0);
    }

    public void setInt(String key, int value) {
        sEditor.putInt(key, value).commit();
    }

    public void setLong(String key, long value) {
        sEditor.putLong(key, value).commit();

    }

    public void setString(String key, String value) {
        sEditor.putString(key, value).commit();
    }

    public void setStringSet(String key, Set<String> value) {
        sEditor.putStringSet(key, value).commit();
    }

    public Set<String> getStringSet(String key) {
        return sPref.getStringSet(key, new HashSet<String>());
    }


    public void remove(String key) {
        sEditor.remove(key).commit();
    }

    public String getString(String key) {
        return sPref.getString(key, "");
    }
}
