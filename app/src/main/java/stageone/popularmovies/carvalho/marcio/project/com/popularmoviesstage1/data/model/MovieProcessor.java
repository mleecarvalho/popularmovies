package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieProcessor{

    public static ArrayList<Movie> process(JSONArray results){
        ArrayList<Movie> movies =  new ArrayList<>();
        try {
            if(results.length() > 0){
                for(int i = 0; i < results.length(); i++) {
                    JSONObject jsonObject = results.getJSONObject(i);
                    Movie movie = new Movie(jsonObject);
                    movies.add(movie);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
