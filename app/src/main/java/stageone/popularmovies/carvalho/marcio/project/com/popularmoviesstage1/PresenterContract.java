package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1;

public interface PresenterContract<V> {
    void attachView(V view);
    void detachView();

}
