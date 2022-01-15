package hellojpa;

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
//            데이터 추가
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

//            데이터 조회
//            Member member = em.find(Member.class, 1L);
//            System.out.println(member.getId() + ", "+ member.getName());

//            데이터 삭제
//            Member member = em.find(Member.class, 1L);
//            em.remove(member);

//            데이터 수정
//            Member member = em.find(Member.class, 1L);
//            member.setName("HelloJPA");

//            JPQL을 통한 쿼리문을 통한 데이터 조회 (특정 조건을 걸 때 사용)
//            (JPQL은 from절에서 테이블 명을 적는 것이 아닌 Entity  대상으로 검색을 한다.)
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                            .getResultList();

            for (Member member : result) {
                System.out.println(member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
