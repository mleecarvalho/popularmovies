package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.R;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.itemDetail.
        ItemDetailActivity;

import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.
        dashboard.ListMovieOrderBy.POPULARITY;
import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard.
        ListMovieOrderBy.RATING;
import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.utils.
        NetConnection.hasInternetConnection;
import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.utils.
        NetConnection.showConnectionError;

public class ListMovieActivity extends AppCompatActivity
        implements ListMoviewContract.View{

    @BindView(R.id.list_movie_appbar)
    protected AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.list_move_progressbar)
    protected ProgressBar progressBar;
    @BindView(R.id.list_movie_recycleview)
    protected RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private ListMovieAdapter listMovieAdapter;
    private ListMoviewContract.Presenter presenter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setPresenter();
        setAdapter();
        presenter.loadData();

    }

    private void setAdapter() {
        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        listMovieAdapter = new ListMovieAdapter(this);
        recyclerView.setAdapter(listMovieAdapter);
    }

    private void setPresenter() {
        presenter = new ListMoviePresenter(this);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_movie, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ordey_by_popularity :
                presenter.setOrderBy(POPULARITY);
                reloadList();
                return true;
            case R.id.order_by_rating :
                presenter.setOrderBy(RATING);
                reloadList();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reloadList() {
        List<Movie> movieList = new ArrayList<>();
        if(hasInternetConnection(this)) {
            setAdapter();
            ListMovieAsyncTask task =  new ListMovieAsyncTask(this);
            task.execute(presenter.getOrderBy());
        } else {
            showConnectionError(this);
        }
    }

    @Override
    public void fillList(List<Movie> listMovie) {
        hideLoading();
        presenter.updateList(listMovie);
        listMovieAdapter.setMovieList(listMovie);
    }

    @Override public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override public void openItem(Movie movie) {
        Intent intent = new Intent(ListMovieActivity.this, ItemDetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}
