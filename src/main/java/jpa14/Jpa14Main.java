package jpa14;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import common.EMF;
import common.model.User;
import common.model.User_;

public class Jpa14Main {
    public static void main(String[] args) throws IOException {
        EMF.init();

        EntityManager em = EMF.currentEntityManager();
        EntityTransaction tx = em.getTransaction();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        try {
            tx.begin();

            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);

            Predicate namePredicate = cb.equal(root.get("name"), "고길동");

            cq.where(namePredicate);

            TypedQuery<User> query = em.createQuery(cq);

            List<User> userList = query.getResultList();
            for (User u : userList)
            {
                System.out.println("u = " + u.toString());
            }

            System.out.println("------------------------------");

            Predicate metaPredicate = cb.equal(root.get(User_.email), "realdm99@linecorp.com");
            cq.where(metaPredicate);

            query = em.createQuery(cq);

            userList = query.getResultList();
            for (User u : userList)
            {
                System.out.println("u = " + u.toString());
            }

            tx.commit();
        }
        catch (Exception e) {

            tx.rollback();
        }
        finally {
            em.close();
        }
    }
}
