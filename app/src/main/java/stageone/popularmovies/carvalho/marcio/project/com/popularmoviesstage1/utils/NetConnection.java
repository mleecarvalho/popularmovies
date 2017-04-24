package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.R;

public class NetConnection {

    public static boolean hasInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void showConnectionError(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(R.string.no_internet_msg)
                .setTitle(R.string.connection_error_title);
        builder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
