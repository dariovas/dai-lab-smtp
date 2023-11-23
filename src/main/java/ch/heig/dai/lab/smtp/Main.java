package ch.heig.dai.lab.smtp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Group> groups = new ArrayList<Group>();

        for(int i = 0; i < 4; ++i){
            groups.add(new Group(JsonReader.getVictim()));
        }

    }
}