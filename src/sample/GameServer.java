package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class GameServer {
    static ArrayList<String> userName = new ArrayList<String>();
    static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();      // Send msg to all client
//    static ArrayList<ObjectOutputStream> objectOutputStreams = new ArrayList<ObjectOutputStream>();
//    static ArrayList<Integer> isReady = new ArrayList<Integer>(Collections.nCopies(4, 0));

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
//    ObjectOutputStream objectOutputStream;
//    ObjectInputStream objectInputStream;

    String name;

    public ConversationHandler(Socket socket) throws IOException {
        this.socket = socket;
    }
    public void run() {

        try {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
//            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//            objectInputStream = new ObjectInputStream(socket.getInputStream());

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
                    //System.out.println(GameServer.userName.size());
                    break;
                }
                ++count;
            }

            out.println("NAME_ACCEPTED"+name);
            GameServer.printWriters.add(out);
            //GameServer.objectOutputStreams.add(objectOutputStream);

//            for(String name : GameServer.userName) {
//                System.out.println(name);
//            }

            while (true) {
                String message = in.readLine();

                if (message == null)
                    return;
                if (message.startsWith("READY")) {
                    for (PrintWriter writer : GameServer.printWriters)
                        writer.println(GameServer.userName.size());
//                    for (ObjectOutputStream objectOutputStream : GameServer.objectOutputStreams)
//                        objectOutputStream.writeObject(GameServer.isReady);
                    System.out.println(GameServer.userName.size() + " " + name);
                }
//                for (PrintWriter writer : GameServer.printWriters)              // For all writer in printWriter
//                    writer.println(name + ": " + message);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

