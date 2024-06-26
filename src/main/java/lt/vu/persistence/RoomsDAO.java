package lt.vu.persistence;

import lt.vu.entities.Room;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class RoomsDAO {

    @Inject
    private EntityManager em;

    public void persist(Room room){
        this.em.persist(room);
    }
    public void merge(Room room){
        this.em.merge(room);
    }

    public Room findOne(Integer id){
        return em.find(Room.class, id);
    }

    public Room update(Room room){
        return em.merge(room);
    }

    public List<Room> loadAll() {
        return em.createNamedQuery("Room.findAll", Room.class).getResultList();
    }
}
