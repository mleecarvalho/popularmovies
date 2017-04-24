package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.itemDetail;

import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.PresenterContract;

public interface ItemDetailContract {
    interface View{
        void setCollapsePallete(Palette palette);
    }
    interface Presenter extends PresenterContract<View> {
        void loadMovieImage(ImageView imageView, String imagePath);
        void loadCollapseImage(ImageView imageView, String imagePath);
    }
}
