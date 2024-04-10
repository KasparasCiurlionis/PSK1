package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Member;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.MembersDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdatePlayerDetails implements Serializable {

    private Member member;

    @Inject
    private MembersDAO membersDAO;

    @PostConstruct
    private void init() {
        System.out.println("UpdatePlayerDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer playerId = Integer.parseInt(requestParameters.get("playerId"));
        this.member = membersDAO.findOne(playerId);
    }

    @Transactional
    @LoggedInvocation
    public String updatePlayerJerseyNumber() {
        try{
            membersDAO.update(this.member);
        } catch (OptimisticLockException e) {
            return "/playerDetails.xhtml?faces-redirect=true&playerId=" + this.member.getId() + "&error=optimistic-lock-exception";
        }
        return "members.xhtml?teamId=" + this.member.getTeam().getId() + "&faces-redirect=true";
    }
}
