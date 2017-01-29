package com.kersuzananthony.popularmovies.adapters;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kersuzananthony.popularmovies.R;
import com.kersuzananthony.popularmovies.models.Movie;
import com.kersuzananthony.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    public interface MoviesAdapterOnClickHandler {
        public void onClick(Movie movie);
    }

    private static final String TAG = MoviesAdapter.class.getSimpleName();

    private final MoviesAdapterOnClickHandler mOnClickHandler;

    private ArrayList<Movie> mMovieData;

    private Context mContext;

    public MoviesAdapter(MoviesAdapterOnClickHandler onClickHandler) {
        this.mOnClickHandler = onClickHandler;
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new MoviesAdapterViewHolder that holds the View for each list item
     */
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int layoutForListItem = R.layout.movie_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(layoutForListItem, parent, shouldAttachToParentImmediately);

        MoviesAdapterViewHolder viewHolder = new MoviesAdapterViewHolder(view);

        return viewHolder;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the movie
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(MoviesAdapterViewHolder holder, int position) {
        Movie movie = mMovieData.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        if (mMovieData == null) return 0;

        return mMovieData.size();
    }

    /**
     * This method is used to set the mMovieData list on a MoviesAdapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new MoviesAdapter to display it.
     *
     * @param movieData The new weather data to be displayed.
     */
    public void setMovieData(ArrayList<Movie> movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }

    class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* Properties */
        public ImageView mImageView;

        /* Movies AdapterViewHolder constructor */
        public MoviesAdapterViewHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.movie_list_item_iv_movie_image_view);

            // Add onClickListener
            itemView.setOnClickListener(this);
        }

        /* Helpers for binding a movie to the ViewHolder */
        public void bind(Movie movie) {
            Uri uri = NetworkUtils.buildUri(mContext, NetworkUtils.URL_BASE_IMAGE, movie.getPosterPath());

            Picasso.with(this.mImageView.getContext())
                    .load(uri)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder_error)
                    .fit()
                    .centerCrop()
                    .into(mImageView);

            mImageView.setContentDescription(movie.getTitle());
        }

        @Override
        public void onClick(View v) {
            Movie selectedMovie = mMovieData.get(getAdapterPosition());
            mOnClickHandler.onClick(selectedMovie);
        }
    }

}
