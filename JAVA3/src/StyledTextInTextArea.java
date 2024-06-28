

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

        public class StyledTextInTextArea extends Application {

            @Override
            public void start(Stage primaryStage) {
                primaryStage.setTitle("Colored TextArea Example");

                TextArea textArea = new TextArea();
                String hexColor = "#0000FF";
                String fontWeight = "italic"; // Жирний стиль
                String style = "-fx-text-fill: " +hexColor+"; -fx-underline: true;";
                textArea.setText("Цей текст червоного кольору.");
                //textArea.setStyle("-fx-text-fill: #0000FF;"); // Встановлення кольору тексту
                textArea.setStyle(style);
                System.out.print("-fx-fill: " +hexColor+";");
                VBox root = new VBox(textArea);

                Scene scene = new Scene(root, 300, 100);
                primaryStage.setScene(scene);
                primaryStage.show();
            }

            public static void main(String[] args) {
                launch(args);
            }
        }
