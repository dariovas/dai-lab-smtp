package ch.heig.dai.lab.smtp;

public class Message {
    private String subject;
    private String body;

    Message(String subject, String body){
        this.subject = subject;
        this.body = body;
    }
}
