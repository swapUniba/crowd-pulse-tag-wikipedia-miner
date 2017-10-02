package com.github.frapontillo.pulse.crowd.tag.wikipediaminer;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author Francesco Pontillo
 */
public interface WikipediaMinerService {
    @GET("/services/wikify?responseFormat=json")
    WikifyResponse wikify(@Query("source") String text, @Query("wikipedia") String language);
}
