package ch.heig.dai.lab.smtp.config;

import ch.heig.dai.lab.smtp.Message;
import ch.heig.dai.lab.smtp.Victim;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

import java.nio.file.Path;
import java.util.List;

/***
 * Reads JSON file with the Jackson library.
 */
public class JsonReader {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String emailRegexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    /***
     * Reads JSON file and stores the content in a List of contentClass.
     * @param path path to the JSON file
     * @param contentClass class corresponding to each item in the list
     * @return content of the file
     * @param <T> type of each item in the list
     */
    private <T> List<T> read(Path path, Class<T> contentClass) {
        try {
            return objectMapper.readValue(path.toFile(), objectMapper.getTypeFactory().constructParametricType(List.class, contentClass));
        } catch (IOException e){
            System.out.println("Cannot read the file");
            throw new RuntimeException(e);
        }
    }


    /**
     * Checks if the message subject or message body is not empty.
     * @param message
     */
    private void validateMessage(Message message){
        if(message.getSubject().isEmpty() || message.getBody().isEmpty()){
            throw new RuntimeException("Message subject : " + message.getSubject() + " or message body : " + message.getBody() + " is empty");
        }
    }

    /***
     * Reads a message list from a JSON file.
     * @param path path to the JSON file
     * @return List of message.
     */
    public List<Message> getMessage(Path path) {
        var messageList = read(path, Message.class);
        messageList.forEach(this::validateMessage);
        return messageList;
    }

    /***
     * Checks if the email entered is valid.
     * @param victim victim to check the email
     */
    private void validateEmail (Victim victim){
        if(!victim.getEmail().matches(emailRegexPattern)){
            throw new RuntimeException("Mail address is invalid: " + victim.getEmail());
        }
    };

    /***
     * Reads a victim list from a JSON file.
     * @param path path to the JSON file
     * @return List of victim
     */
    public List<Victim> getVictim(Path path) {
        var victimList = read(path, Victim.class);
        victimList.forEach(this::validateEmail);
        return victimList;
    }

}
