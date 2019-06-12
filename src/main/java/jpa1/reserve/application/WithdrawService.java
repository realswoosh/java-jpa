package jpa1.reserve.application;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import common.EMF;
import common.model.User;
import common.repository.UserRepository;

public class WithdrawService {
    private UserRepository userRepository = new UserRepository();

    public void withdraw(String email) {

        EntityManager em = EMF.currentEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            User user = userRepository.find(email);
            if (user == null) {
                throw new UserNotFoundException();
            }
            userRepository.remove(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            EMF.closeCurrentEntityManager();
        }
    }
}
