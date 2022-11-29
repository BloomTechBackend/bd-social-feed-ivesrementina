package com.bloomtech.socialfeed.validators;

import com.bloomtech.socialfeed.helpers.exceptions.UserValidationException;
import com.bloomtech.socialfeed.models.Role;
import com.bloomtech.socialfeed.models.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfoValidator implements Validator {

    private boolean isValidUsername(String username) {
        //TODO: validate username begins with an uppercase letter, is at least 4 characters long, and only contains
        //letters, numbers, and underscores
        String userRegex = "^[A-Z]\\w{3,}$";
        Pattern userPattern = Pattern.compile(userRegex);
        Matcher userMatcher = userPattern.matcher(username);
        return userMatcher.find();
    }

    private boolean isValidPassword(String password) {
        //TODO: validate password contains at least 8 characters, an uppercase, and a lowercase letter.
        String passRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*[a-zA-Z0-9!@#$%^&*]{8,}$";
        Pattern passPattern = Pattern.compile(passRegex);
        Matcher passMatcher = passPattern.matcher(password);
        return passMatcher.find();
    }

    @Override
    public void validate(Object userData) {

        User user = (User) userData;

        if (!isValidUsername(user.getUsername())) {
            throw new UserValidationException("Invalid Username: Username must be at least 4 characters long, " +
                    "must begin with an uppercase letter, and may not contain special characters or spaces!");
        }
        if (!isValidPassword(user.getPassword())) {
            throw new UserValidationException("Invalid Password: Password must be at least 8 characters long, " +
                    "contain at least one uppercase letter, one lowercase letter, and one special character!");
        }
        if (user.getRole() == null) { user.setRole(Role.USER); }
    }
}
