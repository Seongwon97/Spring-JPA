package hellojpa;

import hellojpa.InheritenceMapping.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Movie movie = new Movie();
            movie.setActor("오성원");
            movie.setDirector("오성원 감독");
            movie.setName("JPA로 떠나는 여행");
            movie.setPrice(15000);
            em.persist(movie);

            tx.commit();
        } catch (Exception e) {
            System.out.println("\n"+ e.getMessage()+"\n");
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
