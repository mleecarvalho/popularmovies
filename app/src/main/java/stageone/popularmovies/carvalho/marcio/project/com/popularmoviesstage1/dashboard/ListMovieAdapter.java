package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.R;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieViewHolder> {

    private List<Movie> listMovie = new ArrayList<>();
    private ListMoviewContract.View view;

    public ListMovieAdapter(ListMoviewContract.View view) {
        this.view = view;
    }

    @Override public ListMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View inflateView = inflater.inflate(R.layout.adapter_list_movie_item, parent, false);
        return new ListMovieViewHolder(inflateView, this.view);
    }

    @Override public void onBindViewHolder(ListMovieViewHolder holder, int position) {
        holder.bind(listMovie.get(position));

    }

    @Override
    public int getItemCount() {
        if(listMovie != null) {
            return listMovie.size();
        }
        return 0;
    }

    public void setMovieList(List<Movie> listMovie) {
        this.listMovie = listMovie;
        notifyDataSetChanged();
    }
}
