package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Player;
import lt.vu.entities.Room;
import lt.vu.entities.Team;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PlayersDAO;
import lt.vu.persistence.RoomsDAO;
import lt.vu.persistence.TeamsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class PlayersForRoom implements Serializable {

    @Inject
    private RoomsDAO roomsDAO;

    @Inject
    private PlayersDAO playersDAO;

    @Getter @Setter
    private Room room;

    @Getter
    private List<Player> allPlayers;

    @Getter @Setter
    private Player playerToCreate = new Player();

    @PostConstruct
    public void init() {
        loadAllPlayers();
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer roomId = Integer.parseInt(requestParameters.get("roomId"));
        this.room= roomsDAO.findOne(roomId);
    }

    @Transactional
    @LoggedInvocation
    public void createPlayer() {
        //playerToCreate.setManyToMany(this.room);
        //playersDAO.persist(playerToCreate);
    }

    private void loadAllPlayers(){
        this.allPlayers = playersDAO.loadAll();
    }
}
