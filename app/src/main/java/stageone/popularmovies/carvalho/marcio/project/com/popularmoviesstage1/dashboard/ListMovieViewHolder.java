package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.squareup.picasso.Picasso;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.R;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.
        network.MovieDBConnection;

public class ListMovieViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.movie_image)
    protected ImageView movieImage;
    @BindView(R.id.movie_title)
    protected TextView movieTitle;
    protected MovieDBConnection movieConnection = MovieDBConnection.getInstance();
    protected ListMoviewContract.View view;

    private Movie movie;

    public ListMovieViewHolder(View itemView, ListMoviewContract.View mView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.view = mView;
    }

    public void bind(Movie movie){
        this.movie = movie;
        this.movieTitle.setText(movie.getTitle());
        getPicture();

    }

    private void getPicture() {
        Picasso.with(itemView.getContext())
                .load(movieConnection.getPicassoImageURL(movie.getPosterPath()))
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(movieImage);
    }

    @OnClick(R.id.movie_image)
    public void onClick(View view){
        this.view.openItem(movie);
    }

}
