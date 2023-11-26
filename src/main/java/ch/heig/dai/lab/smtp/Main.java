package ch.heig.dai.lab.smtp;

import ch.heig.dai.lab.smtp.config.CLIArgs;
import ch.heig.dai.lab.smtp.config.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Read command line arguments
        var arguments = new CLIArgs(args);

        SMTPClient client = new SMTPClient(arguments.getServerHost(), arguments.getServerPort());
        List<Group> groups = new ArrayList<Group>();
        JsonReader reader = new JsonReader();


        for(int i = 0; i < arguments.getNbGroups(); ++i){
            var victimList = reader.getVictim(arguments.getVictimFilePath());
            var messageList = reader.getMessage(arguments.getMessageFilePath());

            groups.add(new Group(victimList, messageList));
            client.send(groups.get(i).getSender(), groups.get(i).getReceiver(), groups.get(i).getMessage());
        }
    }
}