package com.example.salome.climbingnews;

public class ClimbingNews {

    /**
     * Title
     */
    private String mTitle;

    /**
     * Section info
     */
    private String mSection;

    /**
     * Date info
     */
    private String mDate;

    /**
     * Author info
     */
    private String mAuthor;

    /**
     * URL info
     */
    private String mUrl;


    public ClimbingNews(String Title, String Section, String Date, String Author, String Url) {
        mTitle = Title;
        mSection = Section;
        mDate = Date;
        mAuthor = Author;
        mUrl = Url;
    }

    /**
     * Get Title
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get the Section
     */
    public String getSection() {
        return mSection;
    }

    /**
     * Get the Date
     */
    public String getDate() {
        return mDate;
    }

    /**
     * Get the Author
     */
    public String getAuthor() {
        return mAuthor;
    }

    /**
     * Get the url
     */
    public String getUrl() {
        return mUrl;
    }

}
