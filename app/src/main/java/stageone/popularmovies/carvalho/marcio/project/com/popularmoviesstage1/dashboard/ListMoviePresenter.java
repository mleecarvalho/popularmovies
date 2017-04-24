package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;

import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.
        dashboard.ListMovieAsyncTask.LISTKEY;
import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.
        dashboard.ListMovieOrderBy.POPULARITY;
import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.
        utils.NetConnection.hasInternetConnection;
import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.
        utils.NetConnection.showConnectionError;

public class ListMoviePresenter
        implements ListMoviewContract.Presenter, ListMoviewContract.AsyncTask {

    private ListMoviewContract.View view;
    private Context context;
    private ArrayList<Movie> listMove;

    public ListMoviePresenter() {
    }

    private void executeAsyncTaskData(ListMovieOrderBy orderBy) {
        if(hasInternetConnection(context)) {
            listMove = new ArrayList<>();
            ListMovieAsyncTask task = new ListMovieAsyncTask(this);
            task.execute(orderBy);
        } else {
            showConnectionError( (ListMovieActivity) view );
        }
    }

    @Override public void loadData(Bundle savedInstanceState) {
        if(savedInstanceState != null && savedInstanceState.containsKey(LISTKEY)) {
            listMove = savedInstanceState.getParcelableArrayList(LISTKEY);
            view.fillList(listMove);
        }else{
            executeAsyncTaskData(POPULARITY);
        }
    }

    @Override public void reloadData(ListMovieOrderBy orderBy) {
        executeAsyncTaskData(orderBy);
    }

    @Override public void updateList(ArrayList<Movie> listMovie) {
        this.listMove = listMovie;
    }

    @Override public ArrayList<Movie> getListMovie() {
        return this.listMove;
    }

    @Override public void processStart() {
        view.showLoading();
    }

    @Override public void processFinish(ArrayList<Movie> movies) {
        view.fillList(movies);
    }

    @Override public void attachView(ListMoviewContract.View view) {
        this.view = view;
        this.context = ((ListMovieActivity) view).getBaseContext();
    }

    @Override public void detachView() {
        this.view = null;
        this.context = null;
    }
}
