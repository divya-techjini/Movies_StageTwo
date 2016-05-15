package udacity.com.movies_stagetwo.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import udacity.com.movies_stagetwo.R;
import udacity.com.movies_stagetwo.adapters.ReviewAdapter;
import udacity.com.movies_stagetwo.databinding.LayoutListBinding;
import udacity.com.movies_stagetwo.datamodel.BaseResponse;
import udacity.com.movies_stagetwo.datamodel.ReviewItem;
import udacity.com.movies_stagetwo.datamodel.ReviewResponse;
import udacity.com.movies_stagetwo.network.MoviesManager;


public class ReviewFragment extends BaseFragment implements MoviesManager.OnCommunicationListener {

    private static final String EXTRA_MOVIE_ID = "movie_id_video";
    private int mMovieId = 0;
    private MoviesManager mManager = new MoviesManager();
    private LayoutListBinding mBinding;
    private ReviewAdapter mAdapter;


    public ReviewFragment() {
        // Required empty public constructor
    }

    public static ReviewFragment newInstance(int id) {
        ReviewFragment fragment = new ReviewFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_MOVIE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovieId = getArguments().getInt(EXTRA_MOVIE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_list, container, false);
        mBinding.setSize(1);
        mBinding.setMessage(R.string.msg_no_reviews);
        mAdapter = new ReviewAdapter(new ArrayList<ReviewItem>());
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        getData();
        return mBinding.getRoot();
    }

    private void getData() {
        if (isConnectionAvailable()) {
            showLoadingDialog(R.string.msg_loading_data);
            mManager.getReviewsForMovie(this, mMovieId);
        } else {
            showNoNetworkMsg();
        }
    }

    @Override
    public void onSuccess(int apiId, BaseResponse response) {
        if (isDetached()) return;
        hideLoadingDialog();
        ReviewResponse response1 = (ReviewResponse) response;
        if (response1 != null) {
            mAdapter = new ReviewAdapter(response1.getResults());
            mBinding.recyclerView.setAdapter(mAdapter);
        }
        mBinding.setSize(mAdapter.getItemCount());
    }

    @Override
    public void onFailure(int apiId, String msg) {
        if (isDetached()) return;
        hideLoadingDialog();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
        mBinding.setSize(mAdapter.getItemCount());
    }

    @Override
    public void onException(int apiId, String msg) {
        if (isDetached()) return;
        hideLoadingDialog();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
        mBinding.setSize(mAdapter.getItemCount());
    }

}
