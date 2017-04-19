package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.itemDetail;

import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;

public interface ItemDetailContract {
    interface View{
        void setCollapsePallete(Palette palette);
    }
    interface Presenter{
        void loadMovieImage(ImageView imageView, Movie movie);
        void loadCollapseImage(ImageView imageView, Movie movie);
    }
}
