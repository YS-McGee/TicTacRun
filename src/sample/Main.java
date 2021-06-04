package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ViewManager;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main extends Application {

    static JFrame chatWindow = new JFrame("Chat Application");

    static BufferedReader in;
    static PrintWriter out;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            // Get ip addr and username
            String ipAddress = (String) JOptionPane.showInputDialog(chatWindow,
                                                            "Enter IP Address:",
                                                                "IP Address Required!!",
                                                                    JOptionPane.PLAIN_MESSAGE,
                                                                null, null,
                                                    "localhost");
            // Create socket and I/O
            Socket soc = new Socket(ipAddress, 8888);
            in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            out = new PrintWriter(soc.getOutputStream(), true);

            // Username Validation
            while (true) {
                String str = in.readLine();
                if (str.equals("NAME_REQUIRED")) {
                    String name = JOptionPane.showInputDialog(chatWindow,
                            "Enter a unique name:",
                            "Name Required!",
                            JOptionPane.PLAIN_MESSAGE);
                    out.println(name);
                } else if (str.equals("NAME_ALREADY_EXIST")) {
                    String name = JOptionPane.showInputDialog(chatWindow,
                            "Try another name:",
                            "Name Already Exists!!",
                            JOptionPane.WARNING_MESSAGE);
                    out.println(name);
                } else if (str.startsWith("NAME_ACCEPTED"))
                    break;
            }

            ViewManager manager = new ViewManager(in, out);
            primaryStage = manager.getMainStage();
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
