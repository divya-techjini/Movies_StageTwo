package udacity.com.movies_stagetwo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import udacity.com.movies_stagetwo.R;
import udacity.com.movies_stagetwo.datamodel.ReviewItem;

/**
 * Created by techjini on 8/3/16.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ImageViewHolder> {
    private List<ReviewItem> list = Collections.emptyList();


    public ReviewAdapter(List<ReviewItem> list) {
        if (list == null) return;
        this.list = list;
    }


    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_review, parent, false);
        return new ImageViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        ReviewItem ReviewItem = list.get(position);
        holder.txtAuthor.setText(ReviewItem.getAuthor());
        holder.txtContent.setText(ReviewItem.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_author)
        TextView txtAuthor;
        @Bind(R.id.txt_content)
        TextView txtContent;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }



    }

}


