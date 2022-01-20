package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            /*
            // TypeQuery, Query 학습
            TypedQuery<Member> result1 = em.createQuery("select m from Member as m", Member.class);
            TypedQuery<String> result2 = em.createQuery("select m.username from Member as m", String.class);
            // 위의 두개의 명령어와 같이 쿼리의 반환 타입이 동일한 경우 TypedQuery를 사용할 수 있다.
            // 하지만 아래의 코드와 같이 쿼리의 반환 타입이 여러개인 경우 Query타입으로 return 받아야 한다.
            Query result3 = em.createQuery("select m.username, m.age from Member as m");
            */

            /*
            // 쿼리 API사용 예시
            TypedQuery<Member> query = em.createQuery("select m from Member m where m.username =:username", Member.class); // username으로 쿼리 바인딩 기준 설정
            query.setParameter("username", "member1"); // username이 member1인 것을 where조건으로 설정하겠다~
            Member singleResult = query.getSingleResult(); // 결과를 return
            System.out.println("SingleResult = " + singleResult.getUsername());

            // 위와 아래는 같은 코드로 위의 코드들을 method chain으로 변경한 것이다.

            Member singleResult1 = em.createQuery("select m from Member m where m.username =:username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
             */


            tx.commit();
        } catch (Exception e) {
            System.out.println("\n" + e.getMessage() + "\n");
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
