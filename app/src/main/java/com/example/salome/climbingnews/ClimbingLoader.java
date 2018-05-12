package com.example.salome.climbingnews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class ClimbingLoader extends AsyncTaskLoader<List<ClimbingNews>> {

    /**
     * Query URL
     */
    private String mUrl;


    public ClimbingLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<ClimbingNews> loadInBackground() {
        if (mUrl == null)

        {
            return null;
        }

        List<ClimbingNews> articles = QueryUtils.fetchClimbingNews(mUrl);
        return articles;
    }
}