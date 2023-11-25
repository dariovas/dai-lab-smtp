package ch.heig.dai.lab.smtp;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

import java.nio.file.Path;
import java.util.List;

public class JsonReader {
    private final ObjectMapper objectMapper = new ObjectMapper();

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

    public List<Victim> getVictim(Path path) {
        return read(path, Victim.class);
    }

}
