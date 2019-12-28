package pl.zielinska.trashAlert.dao;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zielinska.trashAlert.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDAOHibernateImpl implements UserDAO{

    @NonNull
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager
                .unwrap(Session.class)
                .createQuery("FROM User", User.class)
                .getResultList();
    }

    @Override
    public User findByUsername(String username) {
        return entityManager
                .unwrap(Session.class)
                .createQuery("FROM User WHERE username=:theUsername", User.class)
                .setParameter("theUsername", username)
                .getSingleResult();
    }

    @Override
    public void save(User theUser) {
        entityManager
                .unwrap(Session.class)
                .saveOrUpdate(theUser);
    }

    @Override
    public void deleteByUsername(String username) {
        entityManager
                .unwrap(Session.class)
                .createQuery("DELETE FROM User WHERE username=:theUsername")
                .setParameter("theUsername", username)
                .executeUpdate();
    }
}
