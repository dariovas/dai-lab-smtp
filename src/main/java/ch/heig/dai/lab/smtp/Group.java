package ch.heig.dai.lab.smtp;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Group {
    private final int MIN_GROUP_MEMBERS = 2;
    private final int MAX_GROUP_MEMBERS = 5;

    @Getter
    private List<Victim> receivers = new ArrayList<>();
    @Getter
    private Victim sender = new Victim();
    @Getter
    private Message message = new Message();

    Group(List<Victim> victims, List<Message> messages){
        if(victims.isEmpty() || messages.isEmpty()){
            throw new RuntimeException("Victim or Message files are empty.");
        }

        Random rand = new Random();

        // Selects randomly the number of member in the group.
        int numberOfElements = rand.nextInt(MIN_GROUP_MEMBERS,MAX_GROUP_MEMBERS + 1);

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(victims.size());

            // The first victim of the group is the sender.
            if(i < 1){
                sender = victims.get(randomIndex);

            }else{
                // Otherwise, it is a receiver.
                receivers.add(victims.get(randomIndex));
            }

            victims.remove(randomIndex);
        }
        message = messages.get(rand.nextInt(messages.size()));
    }


    /***
     * Gets all receiver emails.
     * @param receivers : List of receivers whose email address we want
     * @return : string containing all email addresses of receivers
     */
    public static String getReceiversEmail(List<Victim> receivers){
        StringBuilder output = new StringBuilder();

        for(int i = 0; i < receivers.size(); ++i){
            if(i > 0){
                output.append(", ");
            }

            output.append(receivers.get(i).getEmail());
        }

        return output.toString();
    }
}
