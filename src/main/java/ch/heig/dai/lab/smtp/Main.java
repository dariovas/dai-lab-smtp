package ch.heig.dai.lab.smtp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        SMTPClient client = new SMTPClient();
        List<Group> groups = new ArrayList<Group>();
        Message message = new Message("Meeting2", "Test 122 \n teojj");
        for(int i = 0; i < 4; ++i){
            groups.add(new Group(JsonReader.getVictim()));
            client.send(groups.get(i).getSender(), groups.get(i).getReceiver(), message);
        }





    }
}