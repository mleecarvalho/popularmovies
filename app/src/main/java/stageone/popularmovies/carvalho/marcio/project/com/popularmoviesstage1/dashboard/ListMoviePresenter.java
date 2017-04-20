package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;

import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard.ListMovieOrderBy.POPULARITY;
import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.utils.NetConnection.hasInternetConnection;
import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.utils.NetConnection.showConnectionError;

public class ListMoviePresenter
        implements ListMoviewContract.Presenter {

    private final ListMoviewContract.View view;
    private final Context context;
    private List<Movie> listMove;
    private static ListMovieOrderBy orderBy;

    public ListMoviePresenter(ListMoviewContract.View view) {
        this.view = view;
        this.context =((ListMovieActivity) view).getBaseContext();
    }

    @Override public void loadData() {
        listMove = new ArrayList<>();

        if(hasInternetConnection(context)) {
            ListMovieAsyncTask task = new ListMovieAsyncTask(view);
            task.execute(getOrderBy());
        } else {
            showConnectionError(context);
        }

    }

    @Override public void updateList(List<Movie> listMovie) {
        this.listMove = listMovie;
    }

    @Override
    public ListMovieOrderBy getOrderBy() {
        return (orderBy == null)? POPULARITY : orderBy;
    }

    @Override
    public void setOrderBy(ListMovieOrderBy orderBy) {
        this.orderBy = orderBy;
    }

}
