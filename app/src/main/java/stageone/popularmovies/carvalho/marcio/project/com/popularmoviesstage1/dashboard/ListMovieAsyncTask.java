package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard;

import android.os.AsyncTask;
import java.util.ArrayList;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data
        .network.MovieDBConnection;

public class ListMovieAsyncTask extends AsyncTask<ListMovieOrderBy,Void,ArrayList<Movie>>{

    public static final String LISTKEY = "Movies";
    private ListMoviewContract.AsyncTask asyncTask;
    private MovieDBConnection dbConnection = MovieDBConnection.getInstance();

    public ListMovieAsyncTask(ListMoviewContract.AsyncTask asyncTask) {
        this.asyncTask = asyncTask;
    }

    @Override protected void onPreExecute() {
        super.onPreExecute();
        asyncTask.processStart();
    }

    @Override protected ArrayList<Movie> doInBackground(ListMovieOrderBy... listMovieOrderBies) {
        ListMovieOrderBy type = listMovieOrderBies[0];
        ArrayList<Movie> movieList = null;
        movieList = dbConnection.requestMovies(type);
        return movieList;
    }

    @Override protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        asyncTask.processFinish(movies);
    }

}
