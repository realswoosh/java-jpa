package jpa13;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import common.EMF;
import common.model.Player;

public class Jpa13Main {

    public static void main(String[] args) throws IOException {
        EMF.init();

        EntityManager em = EMF.currentEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Player p = em.find(Player.class, "1");

            /*
            Hibernate: select player0_.player_id as player_i1_2_0_, player0_.name as name2_2_0_, player0_.salary as salary3_2_0_, player0_.team_id as team_id4_2_0_ from player player0_ where player0_.player_id=?
            */

            System.out.println("----------------");

            // 여기서 조인이 안일어 나는 이유는
            // p.team.id는 테이블 안에 있는 team_id 컬럼 이기 때문에 조인이 일어 나지 않는다.
            // 중요한것은 연관이 된 오브젝트의 식별자(연결관 컬럼)이 아닌 속성(attribute)일 경우에만 조인이 일어 난다.
            Query query1 = em.createQuery("select p from Player p order by p.team.id, p.name");
            query1.getResultList();

            /*
            Hibernate: select player0_.player_id as player_i1_2_, player0_.name as name2_2_, player0_.salary as salary3_2_, player0_.team_id as team_id4_2_ from player player0_ order by player0_.team_id, player0_.name
            */

            System.out.println("----------------");

            Query query2 = em.createQuery("select p from Player p order by p.team.name, p.name");
            query2.getResultList();

            /*
            Hibernate: select player0_.player_id as player_i1_2_, player0_.name as name2_2_, player0_.salary as salary3_2_, player0_.team_id as team_id4_2_ from player player0_ cross join team team1_ where player0_.team_id=team1_.id order by team1_.name, player0_.name
            */

            tx.commit();
        }
        catch(Exception e) {
            tx.rollback();
            throw e;
        }
        finally {
            em.close();
        }

        System.out.println("----------------- testSingleResult");
        testSingleResult();
    }


    public static void testSingleResult() {
        EntityManager em = EMF.currentEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            TypedQuery<Long> query =
                    em.createQuery("select count(p) from Player p", Long.class);
            Long count = query.getSingleResult();

            System.out.println("count = " + count);

            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
            throw e;
        }
        finally {
            em.close();;
        }
    }
}
