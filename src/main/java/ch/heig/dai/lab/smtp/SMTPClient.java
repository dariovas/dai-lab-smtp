package ch.heig.dai.lab.smtp;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import java.time.LocalTime;
import java.util.List;

@Slf4j
public class SMTPClient {
    final String SERVER_ADDRESS;
    final int SERVER_PORT;

    SMTPClient(String serverAddress, int serverPort){
        this.SERVER_ADDRESS = serverAddress;
        this.SERVER_PORT = serverPort;
    }
    public void send(Victim sender, List<Victim> receivers, Message message) {
        // Establish TCP Connection
        log.debug("Establishing connection with {}:{}", SERVER_ADDRESS, SERVER_PORT);

        try (Socket clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream(),
                            StandardCharsets.UTF_8));
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream(),
                            StandardCharsets.UTF_8));

            log.debug("Connection established");

            do {
                System.out.println(in.readLine());

                sendInfo(out, "HELO heig-vd.ch");
                //out.write("HELO heig-vd.ch\n");
                //out.flush();
                System.out.println(in.readLine());

                sendInfo(out,"MAIL FROM: <"+ sender.getEmail() + ">" );
                //out.write("MAIL FROM: <"+ sender.getEmail() + ">\n");
                //out.flush();
                System.out.println(in.readLine());


                for(Victim receiver : receivers) {
                    sendInfo(out, "RCPT TO: <" + receiver.getEmail() + ">");
                    //out.write("RCPT TO: <" + receiver.getEmail() + ">\n");
                    //out.flush();
                    System.out.println(in.readLine());
                }


                sendInfo(out, "DATA");
                //out.write("DATA\n");
                //out.flush();
                System.out.println(in.readLine());

                var data = new StringBuilder();
                data.append("Content-Type: text/plain; charset=UTF-8\n");
                data.append("From: <").append(sender.getEmail()).append(">\n");
                data.append("To: <").append(Group.getReceiversEmail(receivers)).append(">\n");
                data.append("Data: ").append(LocalTime.now()).append("\n");
                data.append("Subject: ").append(message.getSubject()).append("\n\n");
                data.append(message.getBody()).append("\n");
                data.append("\r\n.\r");

                sendInfo(out, data.toString());

                /*
                out.write("Date: Thu, 23 November 2023 20:25\n" +
                        "From: " + sender.getEmail() + "\n" +
                        "Subject: " + message.getSubject() + "\n" +
                        "To: " + Group.getReceiversEmail(receivers)  + "\n" +
                        "\n" +
                        message.getBody() + "\r\n" +
                        ".\r\n");
                out.flush();
                */
                System.out.println(in.readLine());

                sendInfo(out, "QUIT");
                //out.write("QUIT\n");
                //out.flush();
                System.out.println(in.readLine());


            }while(clientSocket.isClosed());
        }
        catch(IOException e){
            System.out.println("Client: exc.: " + e);
        }
    }

    private void sendInfo(BufferedWriter out, String info) throws IOException {
        log.info(info);
        out.write(info + "\n");
        out.flush();
    }
}
