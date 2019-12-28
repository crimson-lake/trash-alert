package pl.zielinska.trashAlert.dao;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.zielinska.trashAlert.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDAOHibernateImpl implements UserDAO{

    @NonNull
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> findAll() {
        return entityManager
                .unwrap(Session.class)
                .createQuery("FROM User", User.class)
                .getResultList();
    }
}
