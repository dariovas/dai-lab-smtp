package ch.heig.dai.lab.smtp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    public static List<Message> getMessage() throws IOException{
        File file = new File("src/main/java/ch/heig/dai/lab/smtp/data/message.json");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, new TypeReference<ArrayList<Message>>() {});
    }

    public static List<Victim> getVictim() throws IOException{
        File file = new File("src/main/java/ch/heig/dai/lab/smtp/data/victim.json");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, new TypeReference<ArrayList<Victim>>() {});
    }

}
