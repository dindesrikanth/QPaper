package com.example.questionpaper.Model;

import java.util.List;

public class Leaderboardmodel {
    private List<String> usernames;
    private int registeredUserCount;

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    public int getRegisteredUserCount() {
        return registeredUserCount;
    }

    public void setRegisteredUserCount(int registeredUserCount) {
        this.registeredUserCount = registeredUserCount;
    }
}
