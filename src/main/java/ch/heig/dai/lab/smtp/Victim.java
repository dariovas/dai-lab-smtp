package ch.heig.dai.lab.smtp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Victim {
    private String email;

    Victim(String email){
        this.email = email;
    }

    public Victim() {
    }
}
