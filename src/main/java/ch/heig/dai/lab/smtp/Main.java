package ch.heig.dai.lab.smtp;

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

        for(int i = 0; i < arguments.getNbGroups() && arguments.getMessageFilePath() != null && arguments.getVictimFilePath() != null; ++i){
            groups.add(new Group(reader.getVictim(arguments.getVictimFilePath()), reader.getMessage(arguments.getMessageFilePath())));
            client.send(groups.get(i).getSender(), groups.get(i).getReceiver(), groups.get(i).getMessage());
        }
    }
}