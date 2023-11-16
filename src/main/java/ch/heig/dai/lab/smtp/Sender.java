package ch.heig.dai.lab.smtp;

public class Sender extends Group{

    Sender(String name) {
        super(name);
    }

    public void sendMessage(Message message){

    }

    public Message choiceMessage(){
        Message message = new Message("","");
        return message;
    }
}
