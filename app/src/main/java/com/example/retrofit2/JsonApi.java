package com.example.retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface JsonApi {

    @GET("posts/{id}/comments")
    Call<List<Comments>> comments(@Path("id") int postId);

    @GET("posts")
    Call<List<Posts>> posts();

    @GET
    Call<List<Comments>> comment(@Url String url);

    @GET("posts")
    Call<List<Posts>> query(@Query("userId") int userId,
                            @Query("_sort") String sort,
                            @Query("_order") String order);

    @POST("posts")
    Call<Posts> createPost(@Body Posts posts);

}
