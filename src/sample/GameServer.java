package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {
    static ArrayList<String> userName = new ArrayList<String>();
    static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();      // Send msg to all client

    public static void main(String[] args) throws Exception{
        System.out.println("Waiting for clients...");

        ServerSocket ss = new ServerSocket(8888);
        // Rx incoming conn
        while (true) {
            Socket soc = ss.accept();
            System.out.println("Connection Established");
            ConversationHandler handler = new ConversationHandler(soc);
            handler.start();
        }
    }
}

class ConversationHandler extends Thread {
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    String name;

    public ConversationHandler(Socket socket) throws IOException {
        this.socket = socket;
    }
    public void run() {

        try {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            int count = 0;
            while (true) {

                if(count > 0)
                    out.println("NAME_ALREADY_EXIST");
                else
                    out.println("NAME_REQUIRED");

                name = in.readLine();
                if (name == null)
                    return;
                if (GameServer.userName.size() >= 4) {
                    out.println("MAX_CLIENT");
                    socket.close();
                    return;
                }
                if (!GameServer.userName.contains(name)) {
                    GameServer.userName.add(name);
                    System.out.println(GameServer.userName.size());
                    break;
                }
                ++count;
            }

            out.println("NAME_ACCEPTED"+name);
            GameServer.printWriters.add(out);

            for(String name : GameServer.userName) {
                System.out.println(name);
            }

            while (true) {
                String message = in.readLine();

                if (message == null)
                    return;
                for (PrintWriter writer : GameServer.printWriters)              // For all writer in printWriter
                    writer.println(name + ": " + message);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

