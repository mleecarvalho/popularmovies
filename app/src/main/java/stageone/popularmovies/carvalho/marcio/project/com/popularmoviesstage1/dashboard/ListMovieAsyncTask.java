package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard;

import android.os.AsyncTask;
import java.util.ArrayList;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.network.MovieDBConnection;

public class ListMovieAsyncTask extends AsyncTask<ListMovieOrderBy,Void,ArrayList<Movie>>{

    private ListMoviewContract.View view;
    private MovieDBConnection dbConnection = MovieDBConnection.getInstance();

    public ListMovieAsyncTask(ListMoviewContract.View view) {
        this.view = view;
    }

    @Override protected void onPreExecute() {
        super.onPreExecute();
        view.showLoading();
    }

    @Override protected ArrayList<Movie> doInBackground(ListMovieOrderBy... listMovieOrderBies) {
        ListMovieOrderBy type = listMovieOrderBies[0];
        ArrayList<Movie> movieList = null;
        movieList = dbConnection.requestMovies(type);
        return movieList;
    }

    @Override protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        view.fillList(movies);
    }

}
