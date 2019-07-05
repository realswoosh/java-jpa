package jpa7;

import java.io.IOException;
import java.lang.reflect.Member;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import common.EMF;
import common.model.MembershipCard;
import common.model.User;

public class Jpa7Main {

    public static void main(String[] args) throws IOException {
        EMF.init();

        EntityManager em = EMF.currentEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

//            User owner = em.find(User.class, "user1@user.com");
//
//            ZonedDateTime zonedDateTime = ZonedDateTime.of(2020, 1, 31, 23, 59, 59, 0 , ZoneId.systemDefault());
//            Date expiryDate = Date.from(zonedDateTime.toInstant());
//            MembershipCard membershipCard = new MembershipCard("12342", owner, expiryDate);
//            em.persist(membershipCard);

            User u1 = new User("swoosh@empal.com", "shin", new Date());

            ZonedDateTime zonedDateTime = ZonedDateTime.of(2020, 1, 31, 23, 59, 59, 0 , ZoneId.systemDefault());
            Date expiryDate = Date.from(zonedDateTime.toInstant());
            MembershipCard membershipCard = new MembershipCard("swoosh1", u1, expiryDate);
            em.persist(membershipCard);

            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        } finally {

        }
    }
}
