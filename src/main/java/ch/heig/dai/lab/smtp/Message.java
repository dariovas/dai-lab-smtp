package ch.heig.dai.lab.smtp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String subject;
    private String body;

    public Message(String subject, String body){
        this.subject = subject;
        this.body = body;
    }

    public Message(){}
}
