package jpa5;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import common.EMF;
import common.model.User;

public class Jpa5Main {
    public static void main(String[] args) throws IOException {
        EMF.init();
//        EntityManager em = EMF.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//
//        User user = null;
//        try {
//            tx.begin();
//            user = em.find(User.class, "realdm99@linecorm.com");
//            System.out.println("name = " + user.getName());
//            tx.commit();
//        }
//        catch (Exception ex) {
//            tx.rollback();
//            throw ex;
//        }
//        finally {
//            em.close();
//        }
//
//        System.out.println("---------------------");
//
//        EntityManager em2 = EMF.createEntityManager();
//        em2.getTransaction().begin();
//        try {
//            user.changeNames("1");
//            em2.merge(user);
//            em2.getTransaction().commit();;
//        }
//        catch (Exception ex) {
//            em2.getTransaction().rollback();
//            throw ex;
//        }
//        finally {
//            em2.close();
//        }

        findAndMerge();
    }


    public static void findAndMerge()
    {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        User user = null;
        try {
            tx.begin();
            user = em.find(User.class, "realdm99@linecorm.com");
            System.out.println("user = " + user.toString());
            em.clear();
            User user2 = em.merge(user);
            if (user != user2)
            {
                System.out.println("user != user2");
            }
            tx.commit();
        }
        catch (Exception ex) {
            tx.rollback();
            throw ex;
        }
        finally {
            em.close();
        }
    }
}
