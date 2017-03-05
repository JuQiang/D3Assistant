package iamfqq.d3assistant;

/**
 * Created by JuQiang on 3/4/2017.
 */

public class Friend {
    public Friend(String profileID, String nickName){
        this.profileID = profileID;
        this.nickName = nickName;
    }
    public String getProfileID() {
        return profileID;
    }
    public String profileID;

    public String getNickName() {
        return nickName;
    }
    public String nickName;
}
