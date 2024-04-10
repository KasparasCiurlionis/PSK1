package lt.vu.persistence;

import lt.vu.entities.Member;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class MembersDAO {

    @Inject
    private EntityManager em;

    public void persist(Member member){
        this.em.persist(member);
    }

    public void merge(Member member){
        this.em.merge(member);
    }
    public Member findOne(Integer id){
        return em.find(Member.class, id);
    }

    public Member update(Member member){
        return em.merge(member);
    }

    public List<Member> loadAll() {
        return em.createNamedQuery("Member.findAll", Member.class).getResultList();
    }
}
