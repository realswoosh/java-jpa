package jpa1.reserve.application;

import javax.persistence.EntityManager;

import common.EMF;
import common.model.User;
import common.repository.UserRepository;

public class JoinService {

    private UserRepository userRepository = new UserRepository();

    public void join(User user) {
        EntityManager em = EMF.currentEntityManager();
        em.getTransaction().begin();
        try {
            User found = userRepository.find(user.getEmail());
            if (found != null) {
                throw new DuplicateEmailException();
            }
            userRepository.save(user);
            em.getTransaction().commit();
        }
        catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
        finally {
            EMF.closeCurrentEntityManager();
        }
    }
}
