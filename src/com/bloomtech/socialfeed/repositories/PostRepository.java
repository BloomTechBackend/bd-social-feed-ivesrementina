package com.bloomtech.socialfeed.repositories;

import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostRepository {
    private static final String POST_DATA_PATH = "src/resources/PostData.json";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public PostRepository() {
    }

    public List<Post> getAllPosts() {
        List<Post> allPosts = new ArrayList<>();
        //TODO: return all posts from the PostData.json file
        File file = new File(POST_DATA_PATH);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Post[] posts = gson.fromJson(bufferedReader,Post[].class);
            if (posts != null){
                allPosts.addAll(Arrays.asList(posts));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allPosts;
    }

    public List<Post> findByUsername(String username) {
        return getAllPosts()
                .stream()
                .filter(p -> p.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public List<Post> addPost(Post post) {
        List<Post> allPosts = new ArrayList<>();
        allPosts.add(post);

        //TODO: Write the new Post data to the PostData.json file
        String gsonList = gson.toJson(allPosts);
        try {
            FileWriter fileWriter = new FileWriter(POST_DATA_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(gsonList);

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO: Return an updated list of all posts
        return allPosts;
    }
}


