package jpa1.reserve.application;

import javax.persistence.EntityManager;

import common.EMF;
import common.model.User;

public class ChangeNameService {
    public void changeName(String email, String newName) {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, email);
            if (user == null) new UserNotFoundException();
            user.changeNames(newName);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
