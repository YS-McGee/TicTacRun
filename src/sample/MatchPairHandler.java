package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class MatchPairHandler extends Thread{

    BufferedReader in;
    PrintWriter out;

    String name, numString;
    int currentNum = 1, rxNum;

    private AnchorPane gamePane;
    private Stage gameStage;

    private final static String BZ0 = "view/resources/animation/big_zombie_0.png";
    private final static String BZ1 = "view/resources/animation/big_zombie_1.png";
    private final static String BZ2 = "view/resources/animation/big_zombie_2.png";
    private final static String BZ3 = "view/resources/animation/big_zombie_3.png";
    private final static String BZ4 = "view/resources/animation/big_zombie_4.png";
    private final static String BZ5 = "view/resources/animation/big_zombie_5.png";
    private final static String BZ6 = "view/resources/animation/big_zombie_6.png";
    private final static String BZ7 = "view/resources/animation/big_zombie_7.png";

    final static Image BIG_ZOMBIE_0 = new Image(BZ0);
    final static Image BIG_ZOMBIE_1 = new Image(BZ1);
    final static Image BIG_ZOMBIE_2 = new Image(BZ2);
    final static Image BIG_ZOMBIE_3 = new Image(BZ3);
    final static Image BIG_ZOMBIE_4 = new Image(BZ4);
    final static Image BIG_ZOMBIE_5 = new Image(BZ5);
    final static Image BIG_ZOMBIE_6 = new Image(BZ6);
    final static Image BIG_ZOMBIE_7 = new Image(BZ7);

    final ImageView big_zombie0 = new ImageView(BIG_ZOMBIE_0);
    final ImageView big_zombie1 = new ImageView(BIG_ZOMBIE_1);
    final ImageView big_zombie2 = new ImageView(BIG_ZOMBIE_2);
    final ImageView big_zombie3 = new ImageView(BIG_ZOMBIE_3);
    final ImageView big_zombie4 = new ImageView(BIG_ZOMBIE_4);
    final ImageView big_zombie5 = new ImageView(BIG_ZOMBIE_5);
    final ImageView big_zombie6 = new ImageView(BIG_ZOMBIE_6);
    final ImageView big_zombie7 = new ImageView(BIG_ZOMBIE_7);

    private Group bigZombieGroup;


    public MatchPairHandler(BufferedReader in, PrintWriter out, String name, Stage gameStage, AnchorPane gamePane) {
        this.in = in;
        this.out = out;
        this.name = name;
        this.gameStage = gameStage;
        this.gamePane = gamePane;

        // Placement 2
        bigZombieGroup = new Group(big_zombie0);
        bigZombieGroup.setTranslateX(240);
        bigZombieGroup.setTranslateY(120);
        Timeline zT = new Timeline();
        zT.setCycleCount(Timeline.INDEFINITE);
        zT.getKeyFrames().add(new KeyFrame(Duration.millis(200), (ActionEvent event) -> {bigZombieGroup.getChildren().setAll(big_zombie1);}));
        zT.getKeyFrames().add(new KeyFrame(Duration.millis(300), (ActionEvent event) -> {bigZombieGroup.getChildren().setAll(big_zombie2);}));
        zT.getKeyFrames().add(new KeyFrame(Duration.millis(400), (ActionEvent event) -> {bigZombieGroup.getChildren().setAll(big_zombie3);}));
        zT.getKeyFrames().add(new KeyFrame(Duration.millis(500), (ActionEvent event) -> {bigZombieGroup.getChildren().setAll(big_zombie4);}));
        zT.getKeyFrames().add(new KeyFrame(Duration.millis(600), (ActionEvent event) -> {bigZombieGroup.getChildren().setAll(big_zombie5);}));
        zT.getKeyFrames().add(new KeyFrame(Duration.millis(700), (ActionEvent event) -> {bigZombieGroup.getChildren().setAll(big_zombie6);}));
        zT.getKeyFrames().add(new KeyFrame(Duration.millis(800), (ActionEvent event) -> {bigZombieGroup.getChildren().setAll(big_zombie7);}));
        zT.play();
    }

    @Override
    public void run() {
        out.println("Waiting"+name);

        try {
            while (true) {
                numString = in.readLine();

                if (numString == null)
                    return;
                rxNum = Integer.parseInt(numString);
                //System.out.println(rxNum);
//                for (int i = currentNum; i <= rxNum; ++i) {
//
//                }
                gamePane.getChildren().add(bigZombieGroup);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
