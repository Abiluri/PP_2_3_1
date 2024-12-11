package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void insertUser(String name, String email, String password) {
        userDao.insertUser(name, email, password);
    }

    @Transactional
    @Override
    public void updateUser(Long id, String name, String email, String password) {
        userDao.updateUser(id, name, email, password);
    }

    @Transactional
    @Override
    public void deleteUser(String email) {
        userDao.deleteUser(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUserByID(Long id) {
        return userDao.getUserByID(id);
    }
}