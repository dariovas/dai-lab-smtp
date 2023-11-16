package ch.heig.dai.lab.smtp;

public class Victim {
    private String email;

    Victim(String email){
        this.email = email;
    }

    public Victim() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
