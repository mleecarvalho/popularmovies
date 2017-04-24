package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.itemDetail;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.R;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.
        data.network.MovieDBConnection;

public class ItemDetailPresenter implements ItemDetailContract.Presenter {

    private final Context context;

    private MovieDBConnection dbConnection = MovieDBConnection.getInstance();

    public ItemDetailPresenter(Context context) {
        this.context = context;
    }

    @Override public void loadMovieImage(ImageView imageView, String imagePath) {
        Picasso.with(context)
                .load(dbConnection.getPicassoImageURL(imagePath))
                .error(R.drawable.no_image)
                .placeholder(R.drawable.no_image)
                .into(imageView);

    }

    @Override public void loadCollapseImage(ImageView imageView, String imagePath) {
        Picasso.with(context)
                .load(dbConnection.getPicassoImageURL(imagePath))
                .into(imageView, collapeCallback(imageView));

    }

    private Callback collapeCallback(final ImageView imageView) {
       return new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        ((ItemDetailActivity) context).setCollapsePallete(palette);
                    }
                });
            }

            @Override
            public void onError() {

            }
        };
    }

}
