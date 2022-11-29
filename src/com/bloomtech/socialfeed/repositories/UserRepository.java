package com.bloomtech.socialfeed.repositories;

import com.bloomtech.socialfeed.helpers.LocalDateTimeAdapter;
import com.bloomtech.socialfeed.models.User;
import com.bloomtech.socialfeed.validators.UserInfoValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepository {
    private static final String USER_DATA_PATH = "src/resources/UserData.json";

    private static final UserInfoValidator userInfoValidator = new UserInfoValidator();
    Gson gson = new GsonBuilder().setPrettyPrinting()
            .create();
    public UserRepository() {
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        //TODO: return parsed list of Users from UserData.json
        File file = new File(USER_DATA_PATH);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            User[] users = gson.fromJson(bufferedReader,User[].class);
            if (users != null){
                allUsers.addAll(Arrays.asList(users));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public Optional<User> findByUsername(String username) {
        return getAllUsers()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    public void save(User user) {
        List<User> allUsers = getAllUsers();

        Optional<User> existingUser = allUsers.stream()
                .filter(u -> u.getUsername().equals(user.getUsername()))
                .findFirst();

        if (!existingUser.isEmpty()) {
            throw new RuntimeException("User with name: " + user.getUsername() + " already exists!");
        }
        allUsers.add(user);
        //TODO: Write allUsers to UserData.json
        String gsonList = gson.toJson(allUsers);
        try {
            FileWriter fileWriter = new FileWriter(USER_DATA_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(gsonList);

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

