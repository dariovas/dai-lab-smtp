package ch.heig.dai.lab.smtp;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
public class CLIArgs {
    @Parameter(
            names = {"-g", "--group-count"},
            description = "Number of group of 2-5 victim to form",
            required = true
    )
    private int nbGroups;

    @Parameter(
            names = {"-v", "--victim-list"},
            description = "Path to the file containing the list of victims",
            required = true
    )
    private String victimFilePath;

    @Parameter(
            names = {"-m", "--message-list"},
            description = "Path to the file containing the list of prank messages",
            required = true
    )
    private String messageFilePath;

    @Parameter(
            names = {"--server-host"},
            description = "Host of the SMTP server mail are sent to, default to localhost",
            required = false
    )
    private String serverHost = "localhost";

    @Parameter(
            names = {"--server-port"},
            description = "Port of the SMTP server mail are sent to, default to 25",
            required = false
    )
    private int serverPort = 25;

    CLIArgs(String ... args){
        JCommander.newBuilder()
                .addObject(this)
                .build()
                .parse(args);
    }
}
