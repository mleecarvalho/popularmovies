package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.network;

import android.net.Uri;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard.ListMovieOrderBy;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.Movie;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.data.model.MovieProcessor;

public class MovieDBConnection {
    private final static String API_DBKEY = "...";
    private final static String MOVIEDB_ENDPOINT = "https://api.themoviedb.org/";
    private final static String PICASSO_ENDPOINT = "http://image.tmdb.org/t/p/w185";
    private final static String POPULARITY_PATH = "3/movie/popular";
    private final static String TOP_RATED_PATH = "3/movie/top_rated";
    private static MovieDBConnection dbConnection;

    public String getPicassoImageURL(String picture) {
        StringBuilder imgUrl = new StringBuilder();
        if(!picture.isEmpty()) {
            imgUrl.append(PICASSO_ENDPOINT);
            imgUrl.append(picture);
        }
        return imgUrl.toString();
    }


    public static MovieDBConnection getInstance() {
        if(dbConnection == null) {
            dbConnection = new MovieDBConnection();
        }
        return dbConnection;
    }

    public ArrayList<Movie> requestMovies(ListMovieOrderBy type) {
        URL requestUrl = buildRequestURL(getRequestPath(type));
        return requestMovieList(requestUrl);
    }

    private String getRequestPath(ListMovieOrderBy type) {
        return (type == ListMovieOrderBy.POPULARITY) ? POPULARITY_PATH : TOP_RATED_PATH;
    }

    private ArrayList<Movie> requestMovieList(URL movieURL) {
        ArrayList<Movie> request = null;
        try {
            String stringRequest = getResponseFromHttpUrl(movieURL);
            request = parseJsonListMovie(stringRequest);
        } catch (IOException | JSONException e) {
        }
        return request;
    }

    private static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            return (hasInput) ? scanner.next(): null;
        } finally {
            urlConnection.disconnect();
        }
    }

    private URL buildRequestURL(String path) {
        Uri builtUri = Uri.parse(MOVIEDB_ENDPOINT)
                .buildUpon()
                .path(path)
                .appendQueryParameter("api_key", API_DBKEY)
                .build();

        URL requestURL = null;

        try {
            requestURL = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
        }

        return requestURL;
    }

    private ArrayList<Movie> parseJsonListMovie(String json) throws JSONException {
        JSONObject jsonResponse = new JSONObject(json);
        JSONArray results = jsonResponse.getJSONArray("results");

        return MovieProcessor.process(results);
    }

}
