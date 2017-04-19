package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.itemDetail;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.R;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;

import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.utils.ConvertUtils.convertDoubleToDecimal;
import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.utils.ConvertUtils.formatDate;

public class ItemDetailActivity extends AppCompatActivity
        implements ItemDetailContract.View{

    @BindView(R.id.collapsing_toolbar)
    protected CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.app_bar_layout)
    protected AppBarLayout appBarLayout;
    @BindView(R.id.movieBackdropImage)
    protected ImageView movieBackdropImage;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.iv_movie_poster)
    protected ImageView moviePoster;
    @BindView(R.id.tv_movie_rating)
    protected TextView movieRating;
    @BindView(R.id.tv_movie_release_date)
    protected TextView movieReleaseDate;
    @BindView(R.id.tv_movie_synopsis)
    protected TextView movieSynopsis;
    private Movie movie;
    private ItemDetailContract.Presenter presenter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);

        this.movie =  getIntent().getParcelableExtra("movie");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupPresenter();
        setupFields();
    }

    private void setupPresenter() {
        this.presenter = new ItemDetailPresenter(this);
    }

    private void setupFields() {
        getSupportActionBar().setTitle(movie.getTitle());
        collapsingToolbarLayout.setTitle(movie.getTitle());
        movieRating.setText(convertDoubleToDecimal(movie.getVoteAverage()));
        movieReleaseDate.setText(formatDate(movie.getReleaseDate()));
        movieSynopsis.setText(movie.getOverview());
        presenter.loadMovieImage(moviePoster,movie.getPosterPath());
        presenter.loadCollapseImage(movieBackdropImage, movie.getBackdropPath());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int opcao = item.getItemId();
        switch (opcao) {
            case (android.R.id.home):
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void setCollapsePallete(Palette palette) {
            int primaryDark = getResources().getColor(R.color.colorPrimaryDark);
            int primary = getResources().getColor(R.color.colorPrimary);
            collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
            collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
    }
}
