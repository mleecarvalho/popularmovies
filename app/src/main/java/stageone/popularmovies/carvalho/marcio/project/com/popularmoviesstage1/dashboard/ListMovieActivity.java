package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard;

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
import java.util.List;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.R;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;

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
        presenter.loadData(ListMovieOrderBy.POPULARITY);

    }

    private void setAdapter() {
        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        listMovieAdapter = new ListMovieAdapter();
        recyclerView.setAdapter(listMovieAdapter);
    }

    private void setPresenter() {
        presenter = new ListMoviePresenter(this);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_movie, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return (id == R.id.action_settings) ? true : super.onOptionsItemSelected(item);
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
}
