package ch.heig.dai.lab.smtp;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class SMTPClient {
    final String SERVER_ADDRESS;
    final int SERVER_PORT;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    SMTPClient(String serverAddress, int serverPort){
        this.SERVER_ADDRESS = serverAddress;
        this.SERVER_PORT = serverPort;
    }
    public void send(Victim sender, List<Victim> receivers, Message message) {
        // Establish TCP Connection
        log.info("Establishing connection with {}:{}", SERVER_ADDRESS, SERVER_PORT);

        try (Socket clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream(),
                        StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(
                     new OutputStreamWriter(clientSocket.getOutputStream(),
                             StandardCharsets.UTF_8));) {


            log.info("Connection established");
            readInfo(in);

            sendInfo(out, "HELO heig-vd.ch");
            readInfo(in);

            sendInfo(out, "MAIL FROM: <" + sender.getEmail() + ">");
            readInfo(in);

            for (Victim receiver : receivers) {
                sendInfo(out, "RCPT TO: <" + receiver.getEmail() + ">");
                readInfo(in);
            }
            sendInfo(out, "DATA");
            readInfo(in);

            var data = new StringBuilder();
            data.append("Content-Type: text/plain; charset=UTF-8\n");
            data.append("From: <").append(sender.getEmail()).append(">\n");
            data.append("To: <").append(Group.getReceiversEmail(receivers)).append(">\n");
            data.append("Date: ").append(LocalDateTime.now().format(dateFormat)).append("\n");
            data.append("Subject: ").append(message.getSubject()).append("\n\n");
            data.append(message.getBody()).append("\n");
            data.append("\r\n.\r");

            sendInfo(out, data.toString());
            readInfo(in);

            sendInfo(out, "QUIT");
            readInfo(in);
        }
        catch(IOException e){
            log.error("Client: exc.:" + e);
        }
    }

    private void sendInfo(BufferedWriter out, String info) throws IOException {
        log.info(info);
        out.write(info + "\n");
        out.flush();
    }

    private void readInfo(BufferedReader in) throws IOException {
        String response;
        while ((response = in.readLine()) != null){
            log.info(response);

            // According to the SMTP protocol documentation, the error code are upper or equal to 400.
            if(Integer.parseInt(response.substring(0, 3)) >= 400){
                throw new RuntimeException(response);
            }

            if(response.charAt(3) == ' '){
                break;
            }
        }
    }
}
