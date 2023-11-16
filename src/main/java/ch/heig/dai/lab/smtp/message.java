package ch.heig.dai.lab.smtp;

public class message {
    private String subject;
    private String body;

    message(String subject, String body){
        this.subject = subject;
        this.body = body;
    }
}
