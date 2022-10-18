package io.anastasiou;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FTC");

        TextArea txt = new TextArea();
        txt.setFont(new Font("Courier New", 16));

        Label lbl = new Label();
        lbl.setText("WELCOME TO FTC");
        lbl.setFont(new Font("Arial Black", 24));

        Button btn = new Button();
        btn.setText("FETCH");
        btn.setMaxWidth(600);
        btn.setFont(new Font("Arial Black", 16));
        btn.setOnAction((e) -> {
                    try {
                        txt.setText("");

                        for(String s : new Connection().getData()) {
                            txt.appendText(s);
                        }
                    } catch (IOException | InterruptedException exc) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error...");
                        alert.setHeaderText("An error occurred...");
                        alert.setContentText("A connection to the server is not possible.");
                        alert.showAndWait().ifPresent(rs -> {
                            if (rs == ButtonType.OK) {
                                System.out.println("Pressed OK.");
                            }
                        });
                    }
                });

        BorderPane root = new BorderPane();
        root.setTop(lbl);
        root.setBottom(btn);
        root.setCenter(txt);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}