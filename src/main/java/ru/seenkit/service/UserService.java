package ru.seenkit.service;

import ru.seenkit.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> getAllUsers();

    User getUserById(int id);

    void editUser(User user, int id);

    void deleteUser(int id);
}
