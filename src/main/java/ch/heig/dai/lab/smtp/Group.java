package ch.heig.dai.lab.smtp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Group {
    private final int MIN_GROUP_MEMBERS = 2;
    private final int MAX_GROUP_MEMBERS = 5;
    private List<Victim> members = new ArrayList<Victim>();
    private List<Victim> receivers = new ArrayList<>();
    private Victim sender = new Victim();
    private Message message = new Message();

    Group(List<Victim> victims, List<Message> messages){
        Random rand = new Random();

        int numberOfElements = rand.nextInt(MIN_GROUP_MEMBERS,MAX_GROUP_MEMBERS);

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(victims.size());
            members.add(victims.get(randomIndex));
            victims.remove(randomIndex);
        }

        sender = members.get(0);
        receivers = members;
        receivers.remove(0);

        message = messages.get(rand.nextInt(messages.size()));
    }

    public List<Victim> getMembers() {
        return members;
    }

    public Victim getSender() {
        return sender;
    }

    public Message getMessage(){
        return message;
    }

    public List<Victim> getReceiver() {
        return receivers;
    }

    public static String getReceiversEmail(List<Victim> receivers){
        String output = "";

        for(Victim receiver : receivers){
            output += receiver.getEmail() + ", ";
        }

        return output;
    }
}
