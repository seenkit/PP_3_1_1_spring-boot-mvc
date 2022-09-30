package ru.seenkit.dao;

import org.springframework.stereotype.Repository;
import ru.seenkit.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select e from User e", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void editUser(User editUser, int id) {
        User user = getUserById(id);
        user.setFirstName(editUser.getFirstName());
        user.setLastName(editUser.getLastName());
        user.setAge(editUser.getAge());
        user.setEmail(editUser.getEmail());
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUserById(id));
    }
}
