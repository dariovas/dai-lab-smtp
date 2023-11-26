package ch.heig.dai.lab.smtp;

import ch.heig.dai.lab.smtp.config.CLIArgs;
import ch.heig.dai.lab.smtp.config.JsonReader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        // Read command line arguments
        var arguments = new CLIArgs(args);

        log.info("Application is running with these arguments : " + arguments);

        SMTPClient client = new SMTPClient(arguments.getServerHost(), arguments.getServerPort());
        List<Group> groups = new ArrayList<Group>();
        JsonReader reader = new JsonReader();

        var messageList = reader.getMessage(arguments.getMessageFilePath());

        log.info("Stating to create the groups and sending the prank messages");
        for(int i = 0; i < arguments.getNbGroups(); ++i){
            var victimList = reader.getVictim(arguments.getVictimFilePath());

            groups.add(new Group(victimList, messageList));

            log.info("Sending message to the group {}", i);
            client.send(groups.get(i).getSender(), groups.get(i).getReceiver(), groups.get(i).getMessage());
        }
    }
}