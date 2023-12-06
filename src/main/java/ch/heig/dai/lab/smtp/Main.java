package ch.heig.dai.lab.smtp;

import ch.heig.dai.lab.smtp.config.CLIArgs;
import ch.heig.dai.lab.smtp.config.JsonReader;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        // Reads command line arguments
        PrankManager prankManager = new PrankManager(args);
    }
}