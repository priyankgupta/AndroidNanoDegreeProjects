package com.example.p14gupta.popularmoviedb.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p14gupta on 04-05-2016.
 *
 */
public class MovieTrailersLink {
    @SerializedName("id")
    private Integer id;
    @SerializedName("results")
    private List<MovieTrailerLinkResults> results = new ArrayList<MovieTrailerLinkResults>();

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The results
     */
    public List<MovieTrailerLinkResults> getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(List<MovieTrailerLinkResults> results) {
        this.results = results;
    }

}
