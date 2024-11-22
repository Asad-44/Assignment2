package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PersonFormWithBanner extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label bannerLabel = new Label("DATA COLLECTOR");
        bannerLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center; -fx-background-color: lightblue;");
        bannerLabel.setMaxWidth(Double.MAX_VALUE);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label fatherNameLabel = new Label("Father Name:");
        TextField fatherNameField = new TextField();

        Label cnicLabel = new Label("CNIC:");
        TextField cnicField = new TextField();

        cnicField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d{0,5}-?\\d{0,7}-?\\d?")) {
                cnicField.setText(oldValue);
            } else {
                // Automatically add dashes
                String plainCNIC = newValue.replace("-", "");
                if (plainCNIC.length() > 5 && plainCNIC.length() <= 12) {
                    cnicField.setText(plainCNIC.substring(0, 5) + "-" + plainCNIC.substring(5));
                }
                if (plainCNIC.length() > 12) {
                    cnicField.setText(plainCNIC.substring(0, 5) + "-" + plainCNIC.substring(5, 12) + "-" + plainCNIC.substring(12, 13));
                }
            }
        }
        );

        Label dateLabel = new Label("Date (Picker):");
        DatePicker datePicker = new DatePicker();

        Label genderLabel = new Label("Gender:");
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female", "Other");

        Label cityLabel = new Label("City:");
        TextField cityField = new TextField();

        Label imageLabel = new Label("Image:");
        Button uploadButton = new Button("Upload Image");
        ImageView imageView = new ImageView();
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-border-color: gray; -fx-border-width: 1px;");

        uploadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose an Image");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
            }
        });

        Button saveButton = new Button("Save");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.addRow(0, nameLabel, nameField);
        gridPane.addRow(1, fatherNameLabel, fatherNameField);
        gridPane.addRow(2, cnicLabel, cnicField);
        gridPane.addRow(3, dateLabel, datePicker);
        gridPane.addRow(4, genderLabel, genderComboBox);
        gridPane.addRow(5, cityLabel, cityField);
        gridPane.addRow(6, imageLabel, uploadButton);
        gridPane.addRow(7, new Label("Preview:"), imageView);

        VBox vbox = new VBox(10, bannerLabel, gridPane, saveButton);
        vbox.setStyle("-fx-padding: 20px;");
        Scene scene = new Scene(vbox, 400, 550);
        primaryStage.setTitle("Person Form with Banner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
