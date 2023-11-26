package ch.heig.dai.lab.smtp.config;

import ch.heig.dai.lab.smtp.Message;
import ch.heig.dai.lab.smtp.Victim;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

import java.nio.file.Path;
import java.util.List;

public class JsonReader {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String emailRegexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private <T> List<T> read(Path path, Class<T> contentClass) {
        try {
            return objectMapper.readValue(path.toFile(), objectMapper.getTypeFactory().constructParametricType(List.class, contentClass));
        } catch (IOException e){
            System.out.println("Cannot read the file");
            throw new RuntimeException(e);
        }
    }
    public List<Message> getMessage(Path path) {
        return read(path, Message.class);
    }

    private void valideEmail (Victim victim){
        if(!victim.getEmail().matches(emailRegexPattern)){
            throw new RuntimeException("Mail address is invalid: " + victim.getEmail());
        }
    };

    public List<Victim> getVictim(Path path) {
        var victimList = read(path, Victim.class);
        victimList.forEach(this::valideEmail);
        return victimList;
    }

}
