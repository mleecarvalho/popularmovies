package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard;

import java.util.List;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;

public interface ListMoviewContract {
    public interface  View{
        void fillList(List<Movie> listMovie);
        void showLoading();
        void hideLoading();
        void openItem(Movie movie);
    }

    public interface Presenter{
       void loadData(ListMovieOrderBy orderBy);
        void updateList(List<Movie> listMovie);
    }

}
