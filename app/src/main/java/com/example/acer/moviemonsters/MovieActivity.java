package com.example.acer.moviemonsters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.Inflater;

import static android.widget.Toast.LENGTH_LONG;

public class MovieActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyFilmAdapter filmAdapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        dialog = new ProgressDialog(MovieActivity.this);
        String s = "https://api.themoviedb.org/3/movie/popular?api_key=78bdf0295b5959b8b7fc3b150628fcef";
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            JsonAsync jsonAsync = new JsonAsync(s);
            jsonAsync.execute();
        } else {
            Toast.makeText(getApplicationContext(), "No internet", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuInflater inflatertwo = getMenuInflater();
        inflater.inflate(R.menu.refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int option = item.getItemId();
        if (option == R.id.action_sort) {
            String s1 = "https://api.themoviedb.org/3/movie/top_rated?api_key=78bdf0295b5959b8b7fc3b150628fcef";
            ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            if (isConnected) {

                JsonAsync jsonAsync = new JsonAsync(s1);
                jsonAsync.execute();
            } else {
                Toast.makeText(getApplicationContext(), "No internet", Toast.LENGTH_SHORT).show();

            }

            return true;
        }
        if (option == R.id.action_popular) {
            String s = "https://api.themoviedb.org/3/movie/popular?api_key=78bdf0295b5959b8b7fc3b150628fcef";
            ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            if (isConnected) {
                JsonAsync jsonAsync = new JsonAsync(s);
                jsonAsync.execute();
            } else {
                Toast.makeText(getApplicationContext(), "No internet", Toast.LENGTH_SHORT).show();
            }
        }
        if (option == R.id.ref) {
            finish();
            Intent intent = new Intent(MovieActivity.this, MovieActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public class JsonAsync extends AsyncTask<String, Void, String> {
        String response;
        ArrayList<JsonMovie> JsonMovies;
        String myurl = "";


        public JsonAsync(String s) {
            this.myurl = s;
        }

        public JsonAsync() {

        }

        @Override
        protected void onPreExecute() {
            JsonMovies = new ArrayList<JsonMovie>();
            super.onPreExecute();
            dialog.setMessage("Loading");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... s) {
            Http connection = new Http();
            URL url = connection.buildUrl(myurl);
            try {
                response = connection.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject movie = jsonArray.getJSONObject(i);
                    int vote_count = movie.getInt("vote_count");
                    int id = movie.getInt("id");
                    boolean video = movie.getBoolean("video");
                    long vote_average = movie.getLong("vote_average");
                    String title = movie.getString("title");
                    long popularity = movie.getLong("popularity");
                    String poster_path = "https://image.tmdb.org/t/p/w300" + "" + movie.getString("poster_path");
                    Log.i("pp", poster_path);
                    String original_language = movie.getString("original_language");
                    String original_title = movie.getString("original_title");
                    String backdrop_path = movie.getString("backdrop_path");
                    boolean adult = movie.getBoolean("adult");
                    String overview = movie.getString("overview");
                    String release_date = movie.getString("release_date");
                    JsonMovie JsonMovie = new JsonMovie(vote_count, id, video, vote_average, title, popularity, poster_path,
                            original_language, original_title, backdrop_path, adult, overview, release_date);


                    JsonMovies.add(JsonMovie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            filmAdapter = new MyFilmAdapter(MovieActivity.this, JsonMovies);
            recyclerView.setLayoutManager(new GridLayoutManager(MovieActivity.this, 2));
            recyclerView.setAdapter(filmAdapter);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

        }
    }
}

