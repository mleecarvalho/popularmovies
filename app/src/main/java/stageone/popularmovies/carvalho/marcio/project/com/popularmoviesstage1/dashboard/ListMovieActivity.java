package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard.
        ListMovieAsyncTask.LISTKEY;
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

    @BindView(R.id.list_move_progressbar)
    protected ProgressBar progressBar;
    @BindView(R.id.list_movie_recycleview)
    protected RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private ListMovieAdapter listMovieAdapter;
    private ListMoviewContract.Presenter presenter;
    private ListMovieOrderBy orderBy;
    private final String ORDERBY_KEY = "orderBy";


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);
        ButterKnife.bind(this);
        setPresenter();
        setAdapter();
        setupOrderBy();
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.loadData(orderBy);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void setAdapter() {
        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        listMovieAdapter = new ListMovieAdapter(this);
        recyclerView.setAdapter(listMovieAdapter);
    }

    private void setPresenter() {
        presenter = new ListMoviePresenter();
        presenter.attachView(this);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        ArrayList<Movie> movies = presenter.getListMovie();
        outState.putInt(ORDERBY_KEY,orderBy.getOrder());
        super.onSaveInstanceState(outState);
    }

    @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null && savedInstanceState.containsKey(ORDERBY_KEY))
            this.orderBy = ListMovieOrderBy.getOrderby(savedInstanceState.getInt(ORDERBY_KEY));
    }

    private void setupOrderBy() {
        if(orderBy == null)
            orderBy = POPULARITY;
    }
    private boolean setOrderBy(ListMovieOrderBy order) {
        boolean mustSet = (order == this.orderBy) ? false : true;
        if(mustSet)
            this.orderBy = order;
        return mustSet;
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_movie, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ordey_by_popularity :
                if(setOrderBy(POPULARITY))
                    reloadList();
                return true;
            case R.id.order_by_rating :
                if(setOrderBy(RATING))
                    reloadList();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reloadList() {
        List<Movie> movieList = new ArrayList<>();
        if(hasInternetConnection(this)) {
            setAdapter();
            presenter.reloadData(orderBy);
        } else {
            showConnectionError(this);
        }
    }

    @Override
    public void fillList(ArrayList<Movie> listMovie) {
        hideLoading();
        presenter.updateList(listMovie);
        listMovieAdapter.setMovieList(listMovie);
    }

    @Override public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override public void openItem(Movie movie) {
        Intent intent = new Intent(ListMovieActivity.this, ItemDetailActivity.class);
        intent.putExtra(LISTKEY, movie);
        startActivity(intent);
    }
}
