package ru.seenkit.dao;

import org.springframework.stereotype.Component;
import ru.seenkit.model.User;

import java.util.List;

@Component
public interface UserDao {

    void addUser(User user);

    List<User> getAllUsers();

    User getUserById(int id);

    void editUser(User user, int id);

    void deleteUser(int id);
}
