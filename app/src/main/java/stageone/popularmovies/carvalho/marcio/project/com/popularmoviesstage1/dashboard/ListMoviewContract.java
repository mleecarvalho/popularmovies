package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;

public interface ListMoviewContract {
    public interface  View{
        void fillList(ArrayList<Movie> listMovie);
        void showLoading();
        void openItem(Movie movie);
    }

    public interface Presenter{
        void loadData(Bundle savedInstanceState);
        void reloadData(ListMovieOrderBy orderBy);
        void updateList(ArrayList<Movie> listMovie);
        ArrayList<Movie> getListMovie();
    }

    public interface AsyncTask{
        void processStart();
        void processFinish(ArrayList<Movie> movies);
    }

}
