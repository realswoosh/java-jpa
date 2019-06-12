package jpa1.reserve.application;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import common.EMF;
import common.model.User;

public class GetUserListService {
    public List<User> getAllList() {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<User> query =
                    em.createQuery("select u from User u order by u.name", User.class);
            List<User> result = query.getResultList();
            em.getTransaction().commit();
            return result;
        }
        catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
        finally {
            em.close();
        }
    }
}
