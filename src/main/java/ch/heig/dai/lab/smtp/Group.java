package ch.heig.dai.lab.smtp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Group {
    private final int MIN_GROUP_MEMBERS = 2;
    private final int MAX_GROUP_MEMBERS = 5;
    private List<Victim> members = new ArrayList<Victim>();
    private Victim sender = new Victim();

    Group(List<Victim> victims){
        Random rand = new Random();

        int numberOfElements = rand.nextInt(MIN_GROUP_MEMBERS,MAX_GROUP_MEMBERS);

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(victims.size());
            members.add(victims.get(randomIndex));
            victims.remove(randomIndex);
        }

        sender = members.get(0);
    }


}
