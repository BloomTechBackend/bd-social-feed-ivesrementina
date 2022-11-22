package com.bloomtech.socialfeed.observerpattern;

import com.bloomtech.socialfeed.App;
import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;

import java.util.ArrayList;
import java.util.List;

//TODO: Implement Observer Pattern
public class OUserFeed implements Observer {
    private User user;
    private List<Post> feeds;
    private final SourceFeed sourceFeed;

    public OUserFeed(User user) {
        this.user = user;
        this.feeds = new ArrayList<>();
        //TODO: update OUserFeed in constructor after implementing observer pattern
        sourceFeed = App.sourceFeed;
        //sourceFeed.attach(user.getUserFeed());
    }

    public User getUser() {
        return user;
    }

    public List<Post> getFeed() {
        return feeds;
    }

    @Override
    public void update() {
        if (user != null) {
            feeds = sourceFeed.getPosts();
        }
    }
}

