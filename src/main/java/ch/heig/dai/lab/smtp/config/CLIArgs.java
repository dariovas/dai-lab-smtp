package ch.heig.dai.lab.smtp.config;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.Getter;
import lombok.ToString;

import java.nio.file.Path;

@ToString
@Getter

/***
 * Manage the cli arguments with the JCommander library.
 */
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
    private Path victimFilePath;

    @Parameter(
            names = {"-m", "--message-list"},
            description = "Path to the file containing the list of prank messages",
            required = true
    )
    private Path messageFilePath;

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

    public CLIArgs(String ... args){
        JCommander.newBuilder()
                .addObject(this)
                .build()
                .parse(args);
    }
}
