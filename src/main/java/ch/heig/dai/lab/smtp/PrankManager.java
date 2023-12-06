package ch.heig.dai.lab.smtp;

import ch.heig.dai.lab.smtp.config.CLIArgs;
import ch.heig.dai.lab.smtp.config.JsonReader;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class PrankManager {

    private SMTPClient client;
    private List<Group> groups;
    private JsonReader reader;
    private List<Message> messageList;
    private List<Victim> victimList;
    PrankManager(String[] args){
        // Reads command line arguments
        var arguments = new CLIArgs(args);

        log.info("Application is running with these arguments : " + arguments);

        client = new SMTPClient(arguments.getServerHost(), arguments.getServerPort());
        groups = new ArrayList<Group>();
        reader = new JsonReader();

        log.info("Retrieving the messages list");
        messageList = reader.getMessage(arguments.getMessageFilePath());

        log.info("Retrieving the victims list");
        victimList = reader.getVictim(arguments.getVictimFilePath());

        log.info("Stating to create the groups and sending the prank messages");
        for(int i = 0; i < arguments.getNbGroups(); ++i){

            groups.add(new Group(victimList, messageList));

            log.info("Sending message to the group {}", i+1);
            client.send(groups.get(i).getSender(), groups.get(i).getReceivers(), groups.get(i).getMessage());
        }
    }
}
