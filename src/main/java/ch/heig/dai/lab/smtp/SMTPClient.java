package ch.heig.dai.lab.smtp;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import java.util.List;

public class SMTPClient {
    final String SERVER_ADDRESS;
    final int SERVER_PORT;

    SMTPClient(String serverAddress, int serverPort){
        this.SERVER_ADDRESS = serverAddress;
        this.SERVER_PORT = serverPort;
    }
    public void send(Victim sender, List<Victim> receivers, Message message) {
        try (Socket clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream(),
                            StandardCharsets.UTF_8));
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream(),
                            StandardCharsets.UTF_8));


            do {
                System.out.println(in.readLine());
                out.write("HELO heig-vd.ch\n");
                out.flush();
                System.out.println(in.readLine());

                out.write("MAIL FROM: <"+ sender.getEmail() + ">\n");
                out.flush();
                System.out.println(in.readLine());
                for(Victim receiver : receivers) {
                    out.write("RCPT TO: <" + receiver.getEmail() + ">\n");
                    out.flush();
                    System.out.println(in.readLine());
                }


                out.write("DATA\n");
                out.flush();
                System.out.println(in.readLine());

                out.write("Date: Thu, 23 November 2023 20:25\n" +
                        "From: " + sender.getEmail() + "\n" +
                        "Subject: " + message.getSubject() + "\n" +
                        "To: " + Group.getReceiversEmail(receivers)  + "\n" +
                        "\n" +
                        message.getBody() + "\r\n" +
                        ".\r\n");
                out.flush();
                System.out.println(in.readLine());

                out.write("QUIT\n");
                out.flush();
                System.out.println(in.readLine());


            }while(clientSocket.isClosed());



        }
        catch(IOException e){
            System.out.println("Client: exc.: " + e);
        }
    }
}
