package com.example.salome.climbingnews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<ClimbingNews>> {
    private static final int CLIMBING_LOADER_ID = 1;

    /**
     * URL to query the Guardian news source for climbing articles
     */
    private static final String GUARDIAN_REQUEST_URL = "http://content.guardianapis.com/search?show-tags=contributor&q=rock%20climbing&api-key=5d76b8e5-ac2d-4544-bed3-304f926e8d19";
    private ClimbingAdapter mAdapter;

    private TextView emptyStateView;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        emptyStateView = findViewById(R.id.empty_view);

        // Find a reference to the {@link ListView} in the layout
        ListView articleListView = findViewById(R.id.list);

        mAdapter = new ClimbingAdapter(this, new ArrayList<ClimbingNews>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        articleListView.setAdapter(mAdapter);

        emptyStateView = findViewById(R.id.empty_view);
        articleListView.setEmptyView(emptyStateView);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open link to the selected article.
        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current article that was clicked on
                ClimbingNews currentArticle = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri climbingUri = Uri.parse(currentArticle.getUrl());

                // Create a new intent to view the article
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, climbingUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });


        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting()) {

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(CLIMBING_LOADER_ID, null, this);
        } else {
            progressBar = findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);
            emptyStateView.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<ClimbingNews>> onCreateLoader(int i, Bundle bundle) {

        // Create a new loader for the given URL
        return new ClimbingLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<ClimbingNews>> loader, List<ClimbingNews> books) {
        emptyStateView.setText(R.string.no_articles);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        // Clear the adapter of previous book data
        mAdapter.clear();

        // If there is a valid list of {@link books}, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ClimbingNews>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();


    }
}

