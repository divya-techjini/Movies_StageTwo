package udacity.com.movies_stagetwo.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import udacity.com.movies_stagetwo.R;

/**
 * Created by techjini on 15/12/15.
 */
public class BaseFragment extends Fragment {

    private ProgressDialog mProgressDialog;

    public synchronized void showLoadingDialog(int resId) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage(getString(resId));
            mProgressDialog.show();
        } else {
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        }
    }

    public synchronized void hideLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
            mProgressDialog.cancel();
            mProgressDialog = null;
        }

    }

    public boolean isConnectionAvailable() {
        ConnectivityManager connectivityManager;
        NetworkInfo networkinfo;
        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkinfo = connectivityManager.getActiveNetworkInfo();

        return (networkinfo != null && networkinfo.isConnected());

    }

    public void showNoNetworkMsg() {
        Toast.makeText(getActivity(), getString(R.string.msg_no_internet), Toast.LENGTH_LONG).show();
    }


}
