package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;

import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.utils.NetConnection.hasInternetConnection;
import static stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.utils.NetConnection.showConnectionError;

public class ListMoviePresenter
        implements ListMoviewContract.Presenter {

    private final ListMoviewContract.View view;
    private final Context context;
    private List<Movie> listMove;

    public ListMoviePresenter(ListMoviewContract.View view) {
        this.view = view;
        this.context =((ListMovieActivity) view).getBaseContext();
    }

    @Override public void loadData(ListMovieOrderBy orderBy) {
        listMove = new ArrayList<>();

        if(hasInternetConnection(context)) {
 //           view.fillList(listMove);
            ListMovieAsyncTask task = new ListMovieAsyncTask(view);
            task.execute(orderBy);
        } else {
            showConnectionError(context);
        }

    }

    @Override public void updateList(List<Movie> listMovie) {
        this.listMove = listMovie;
    }
}
