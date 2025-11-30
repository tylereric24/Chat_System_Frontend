package com.espinozameridaal.modernui.services;

public class AuthService {

    // TODO: wire this to your real backend / database if desired
    public boolean login(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return false;
        }
        // Accept any non-empty username/password for now
        return true;
    }
}
