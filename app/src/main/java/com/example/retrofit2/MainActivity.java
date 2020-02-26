package com.example.retrofit2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private JsonApi jsonApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create())
                            .build();

         jsonApi = retrofit.create(JsonApi.class);

         callPosts();
//         callComments();
//        createPosts();
    }

    private void createPosts() {

        final Posts posts = new Posts(1, "Title", "Text");

        Call<Posts> call = jsonApi.createPost(posts);

        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {

                if(!response.isSuccessful()){

                    textView.setText(response.code());
                    return;
                }

                Posts posts1 = response.body();

                String contents = "";
                contents += "Code : " + response.code() + "\n";
                contents += "Id : " + posts1.getId() + "\n";
                contents += "UserId : " + posts1.getUserId() + "\n";
                contents += "Title : " + posts1.getTitle() + "\n";
                contents += "Body : " + posts1.getText() + "\n\n";

                textView.append(contents);

            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                textView.setText(t.getMessage());
                return;
            }
        });
    }

    private void callComments() {

        Call<List<Comments>> call = jsonApi.comment("https://jsonplaceholder.typicode.com/posts/1/comments");

        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {

                if(!response.isSuccessful()){

                    textView.setText(response.code());
                }

                List<Comments> commentsList = response.body();

                for(Comments comment : commentsList){
                    String contents = "";
                    contents += "PostId : " + comment.getPostId() + "\n";
                    contents += "Id : " + comment.getId() + "\n";
                    contents += "Email : " + comment.getEmail() + "\n";
                    contents += "Title : " + comment.getTitle() + "\n";
                    contents += "Body : " + comment.getText() + "\n\n";

                    textView.append(contents);
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {

                textView.setText(t.getMessage());
            }
        });

    }

    private void callPosts() {

        Call<List<Posts>> call = jsonApi.posts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if (!response.isSuccessful()) {
                    textView.setText(response.code());
                }

                List<Posts> postsList = response.body();

                for (Posts post : postsList) {
                    String content = "";
                    content += "Id : " + post.getId() + "\n";
                    content += "UserId : " + post.getUserId() + "\n";
                    content += "Title : " + post.getTitle() + "\n";
                    content += "Body : " + post.getText() + "\n\n";

                    textView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}
