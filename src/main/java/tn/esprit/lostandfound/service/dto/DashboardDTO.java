package tn.esprit.lostandfound.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardDTO {


    private long totalUser;

    private int newUser;

    private int totalObjectP;

    private int totalObjectT;

    private int totalObjectRet;

    public long getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(long totalUser) {
        this.totalUser = totalUser;
    }

    public int getNewUser() {
        return newUser;
    }

    public void setNewUser(int newUser) {
        this.newUser = newUser;
    }

    public int getTotalObjectP() {
        return totalObjectP;
    }

    public void setTotalObjectP(int totalObjectP) {
        this.totalObjectP = totalObjectP;
    }

    public int getTotalObjectT() {
        return totalObjectT;
    }

    public void setTotalObjectT(int totalObjectT) {
        this.totalObjectT = totalObjectT;
    }

    public int getTotalObjectRet() {
        return totalObjectRet;
    }

    public void setTotalObjectRet(int totalObjectRet) {
        this.totalObjectRet = totalObjectRet;
    }

    public DashboardDTO(long totalUser, int newUser, int totalObjectP, int totalObjectT, int totalObjectRet) {
        this.totalUser = totalUser;
        this.newUser = newUser;
        this.totalObjectP = totalObjectP;
        this.totalObjectT = totalObjectT;
        this.totalObjectRet = totalObjectRet;
    }
}
