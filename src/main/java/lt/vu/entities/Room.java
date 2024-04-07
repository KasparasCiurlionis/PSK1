package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Room.findAll", query = "select t from Room as t")
})
@Table(name = "ROOM")
@Getter @Setter
public class Room {
    @Id
    @GeneratedValue
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @ManyToMany
    @JoinTable(
            name = "Reservation",
            joinColumns = @JoinColumn(name = "roomId"),
            inverseJoinColumns = @JoinColumn(name = "playerId"))
    private List<Player> manyToMany;

    public List<Player> getManyToMany() {
        return manyToMany;
    }

    public void setManyToMany(List<Player> manyToMany) {
        this.manyToMany = manyToMany;
    }

    @Basic
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
