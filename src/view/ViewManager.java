package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;

    private AnchorPane mainPane = new AnchorPane();
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTON_START_X = 100;
    private final static int MENU_BUTTON_START_Y = 150;

    private SpaceRunnerSubScene creditsSubScene;
    private SpaceRunnerSubScene helpSubScene;
    private SpaceRunnerSubScene scoreSubScene;
    private SpaceRunnerSubScene shipChooserScene;

    private SpaceRunnerSubScene sceneToHide;

    List<SpaceRunnerButton> menuButtons;
    List<ShipPicker> shipList;
    private SHIP choosenShip;

    static BufferedReader in;
    static PrintWriter out;
    String name;

    public ViewManager(BufferedReader in, PrintWriter out, String s) {

        this.in = in;
        this.out = out;
        name = s;

        menuButtons = new ArrayList<>();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        createSubScene();
        createButtons();
        createBackground();
        createLogo();
    }
    public Stage getMainStage() {
        return mainStage;
    }

    private void showSubScene(SpaceRunnerSubScene subScene) {
        if (sceneToHide != null)
            sceneToHide.moveSubScene();

        subScene.moveSubScene();
        sceneToHide = subScene;
    }
    private void createSubScene() {
        creditsSubScene = new SpaceRunnerSubScene();
        mainPane.getChildren().add(creditsSubScene);

        helpSubScene = new SpaceRunnerSubScene();
        mainPane.getChildren().add(helpSubScene);

        scoreSubScene = new SpaceRunnerSubScene();
        mainPane.getChildren().add(scoreSubScene);

        createShipChooserSubScene();
    }
    private void createShipChooserSubScene() {
        shipChooserScene = new SpaceRunnerSubScene();
        mainPane.getChildren().add(shipChooserScene);

        InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(25);

        shipChooserScene.getPane().getChildren().add(chooseShipLabel);
        shipChooserScene.getPane().getChildren().add(createShipToChoose());
        shipChooserScene.getPane().getChildren().add(createButtonToStart());
    }
    private HBox createShipToChoose() {
        HBox box = new HBox();
        box.setSpacing(20);

        shipList = new ArrayList<>();

        for (SHIP ship : SHIP.values()) {
            ShipPicker shipToPick = new ShipPicker(ship);
            shipList.add(shipToPick);
            box.getChildren().add(shipToPick);
            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (ShipPicker ship : shipList) {
                        ship.setIsCircleChosen(false);
                    }
                    shipToPick.setIsCircleChosen(true);
                    choosenShip = shipToPick.getShip();
                }
            });
        }

        box.setLayoutX(300 - (118*2));
        box.setLayoutY(100);

        return box;
    }
    private SpaceRunnerButton createButtonToStart() {
        SpaceRunnerButton startButton = new SpaceRunnerButton("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (choosenShip != null) {
                    WaitingRoomManager waitingRoomManager = new WaitingRoomManager(in, out);
                    waitingRoomManager.createWaitingRoom(mainStage);
                    waitingRoomManager.waitForGameBegin();
                }
            }
        });
        // Set to go to waiting room

        return startButton;
    }
    private void addMenuButton(SpaceRunnerButton button) {
        button.setLayoutX(MENU_BUTTON_START_X);
        button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }
    private void createButtons() {
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
        createLogo();
    }
    private void createStartButton() {
        SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
        addMenuButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(shipChooserScene);
            }
        });
    }
    private void createScoresButton() {
        SpaceRunnerButton scoresButton = new SpaceRunnerButton("SCORES");
        addMenuButton(scoresButton);

        scoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(scoreSubScene);
            }
        });
    }
    private void createHelpButton() {
        SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(helpSubScene);
            }
        });
    }
    private void createCreditsButton() {
        SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(creditsSubScene);
            }
        });
    }
    private void createExitButton() {
        SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });
    }
    private void createBackground() {
        Image backgroundImage = new Image("view/resources/purple.png",
                                    256, 256, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                                                        BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo() {
        ImageView logo = new ImageView("view/resources/logo.png");
        logo.setFitHeight(110);
        logo.setFitWidth(590);
        //logo.setPreserveRatio(true);
        logo.setLayoutX(349);
        logo.setLayoutY(25);

        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());
            }
        });
        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });

        mainPane.getChildren().add(logo);
    }
}
