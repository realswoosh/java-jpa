package jpa4;

import java.io.IOException;

import javax.persistence.EntityManager;

import common.EMF;
import common.model.Address;
import common.model.Grade;
import common.model.Hotel;
import common.model.Sight;
import common.model.SightDetail;

public class Jpa4Main {
    public static void main(String[] args) throws IOException {
        EMF.init();
        EntityManager em = EMF.createEntityManager();

        em.getTransaction().begin();

//        Hotel hotel = new Hotel("hotel1", "guro hotel", Grade.STAR2, new Address("zip1", "zip2", "zip3"));
//        em.persist(hotel);


        // address에 null을 넣어 입력해 보자
//        Hotel hotel = new Hotel("hotel2", "guro hotel", Grade.STAR2, null);
//        em.persist(hotel);

//        Hotel hotel = new Hotel("hotel3", "guro hotel", Grade.STAR2, new Address("zip1",  null, null));
//        em.persist(hotel);

        Sight sight = new Sight("경복궁1",
                                new Address("03045", "서울시 종로구", "세종로 1-1"),
                                new Address("03045", "jongno-gu, Seoul", "1-1, Sejong-ro"));
        sight.setDetail(new SightDetail("09~17시", "매주 화요일", "안내 설명"));

        try {
            em.persist(sight);
            em.getTransaction().commit();
        }
        catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }finally {
            em.close();
        }
    }
}
