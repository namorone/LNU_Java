import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.HTMLEditor;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StyledTextInTextArea extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Різні стилі тексту");

        HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setPrefHeight(400);

        Button saveButton = new Button("Зберегти");
        saveButton.setOnAction(event -> {
            String htmlText = htmlEditor.getHtmlText();
            System.out.println(htmlText);
        });

        VBox vBox = new VBox(htmlEditor, saveButton);

        Scene scene = new Scene(vBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
