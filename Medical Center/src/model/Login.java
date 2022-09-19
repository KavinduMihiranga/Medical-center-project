package model;

public class Login {
    private String loginId;
    private String d_Id;
    private String userId;
    private String loginStatus;

    public Login() {
    }

    public Login(String loginId, String d_Id, String userId, String loginStatus) {
        this.loginId = loginId;
        this.d_Id = d_Id;
        this.userId = userId;
        this.loginStatus = loginStatus;
    }


    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getD_Id() {
        return d_Id;
    }

    public void setD_Id(String d_Id) {
        this.d_Id = d_Id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Override
    public String toString() {
        return "Login{" +
                "loginId='" + loginId + '\'' +
                ", d_Id='" + d_Id + '\'' +
                ", userId='" + userId + '\'' +
                ", loginStatus='" + loginStatus + '\'' +
                '}';
    }
}
