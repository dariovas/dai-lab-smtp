package ch.heig.dai.lab.smtp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Read command line arguments
        if (args.length != 1) {
            System.out.println("You need to provide 1 command line arguments: number of groups.");
            System.exit(1);
        }

        int nbGroups = Integer.parseInt(args[0]);

        SMTPClient client = new SMTPClient();
        List<Group> groups = new ArrayList<Group>();
        Message message = new Message("Meeting2", "Test 122 \n teojj");
        for(int i = 0; i < nbGroups; ++i){
            groups.add(new Group(JsonReader.getVictim()));
            client.send(groups.get(i).getSender(), groups.get(i).getReceiver(), message);
        }





    }
}