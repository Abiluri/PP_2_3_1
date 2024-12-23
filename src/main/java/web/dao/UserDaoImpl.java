package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void insertUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        entityManager.persist(user);
    }

    @Override
    public void updateUser(Long id, String name, String email, String password) {
        User user = getUserByID(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(String email) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        if (!users.isEmpty()) {
            for (User user : users) {
                entityManager.remove(user);
            }
        }
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserByID(Long id) {
        return entityManager.find(User.class, id);
    }
}