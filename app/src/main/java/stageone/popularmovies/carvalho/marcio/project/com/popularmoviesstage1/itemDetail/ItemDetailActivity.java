package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.itemDetail;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.R;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;

import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.utils.ConvertUtils.formatToGregorianDate;
import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.utils.ConvertUtils.getYearAmericanDate;

public class ItemDetailActivity extends AppCompatActivity
        implements ItemDetailContract.View{

    @BindView(R.id.collapse_toolbar)
    protected CollapsingToolbarLayout collapseToolbarLayout;
    @BindView(R.id.collapse_image)
    protected ImageView collapseImage;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.original_title_text)
    protected TextView originalTitle;
    @BindView(R.id.movie_item_image)
    protected ImageView movieImage;
    @BindView(R.id.rating_bar)
    protected RatingBar rating_bar;
    @BindView(R.id.release_date_text)
    protected TextView releaseDate;
    @BindView(R.id.synopse_text)
    protected TextView synopse;
    private Movie movie;
    private ItemDetailContract.Presenter presenter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);

        this.movie =  getIntent().getParcelableExtra("movie");
        setupPresenter();
        setupFields();
    }

    private void setupPresenter() {
        this.presenter = new ItemDetailPresenter(this);
    }

    private void setupFields() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.movie_details_title);
        collapseToolbarLayout.setTitle(movie.getTitle());
        originalTitle.setText(movie.getOriginalTitle());
        rating_bar.setRating(averageRate(movie.getVoteAverage())) ;
        releaseDate.setText(getYearAmericanDate(movie.getReleaseDate()));
        synopse.setText(movie.getOverview());
        presenter.loadMovieImage(movieImage,movie.getPosterPath());
        presenter.loadCollapseImage(collapseImage, movie.getBackdropPath());
    }

    private Float averageRate(Double voteAverage) {
        return   voteAverage.floatValue() / 2;
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
            collapseToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
            collapseToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
    }
}
