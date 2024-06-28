import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.io.*;

public class TextEditor extends Application {

    private String textstyle = "";
    private String colorstyle = "";
    public static void main(String[] args) {
        launch(args);
    }

    private final TextArea textArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Text Editor");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        // Меню
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Файл");
        MenuItem newMenuItem = new MenuItem("Створити");
        MenuItem openMenuItem = new MenuItem("Відкрити");
        MenuItem saveMenuItem = new MenuItem("Зберегти");
        MenuItem exitMenuItem = new MenuItem("Вихід");
        fileMenu.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem, new SeparatorMenuItem(), exitMenuItem);
        menuBar.getMenus().add(fileMenu);

        // Панель інструментів
        ToolBar toolBar = new ToolBar();
        Button boldButton = new Button("Жирний");
        Button italicButton = new Button("Курсив");
        Button underlineButton = new Button("Підкреслений");
        Button textColorButton = new Button("Змінити колір тексту");

        toolBar.getItems().addAll(boldButton, italicButton, underlineButton, textColorButton);

        // Редагування тексту
        textArea.setEditable(true);
        root.setCenter(textArea);

        // Рядок стану
        Label statusLabel = new Label("Готовий для роботи");
        root.setBottom(statusLabel);

        // Додавання обробників подій для кнопок форматування
        boldButton.setOnAction(e -> {
            textstyle = "-fx-font-weight: bold;";
            applyStyleToSelectedText(textstyle+colorstyle);

        });
        italicButton.setOnAction(e -> {
            textstyle = "-fx-font-style: italic;";
            applyStyleToSelectedText(textstyle+colorstyle);

        });
        underlineButton.setOnAction(e -> {
            textstyle = "-fx-underline: underline;";
            applyStyleToSelectedText(textstyle+colorstyle);

        });
        textColorButton.setOnAction(e -> {
            ColorPicker colorPicker = new ColorPicker(); // Створення вибору кольору
            colorPicker.setValue(Color.BLACK); // Задамо початковий колір

            // Створення діалогового вікна
            Dialog<Color> dialog = new Dialog<>();
            dialog.setTitle("Виберіть колір тексту");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            dialog.getDialogPane().setContent(colorPicker);

            dialog.setResultConverter(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    return colorPicker.getValue();
                }
                return null;
            });

            // Показ діалогового вікна і оновлення кольору тексту
            Color selectedColor = dialog.showAndWait().orElse(null);
            if (selectedColor != null) {
                String hexColor = String.format("#%02X%02X%02X",
                        (int)(selectedColor.getRed() * 255),
                        (int)(selectedColor.getGreen() * 255),
                        (int)(selectedColor.getBlue() * 255));
                colorstyle = "-fx-text-fill: " + hexColor + ";";
                applyStyleToSelectedText(textstyle+colorstyle); // Застосування кольору

            }
        });


        // Додавання обробників подій для меню
        newMenuItem.setOnAction(e -> textArea.clear());
        openMenuItem.setOnAction(e -> openFile(primaryStage));
        saveMenuItem.setOnAction(e -> saveFile(primaryStage,statusLabel));
        exitMenuItem.setOnAction(e -> primaryStage.close());

        // Додавання обробників подій для клавіш
        scene.setOnKeyPressed(e -> {
            if (e.isControlDown()) {
                if (e.getCode() == KeyCode.B) {
                    applyStyleToSelectedText("-fx-font-weight: bold;");
                } else if (e.getCode() == KeyCode.I) {
                    applyStyleToSelectedText("-fx-font-style: italic;");
                } else if (e.getCode() == KeyCode.U) {
                    applyStyleToSelectedText("-fx-underline: true;");

                }

            }
        });
        // Обробник подій для відстежування змін у тексті
        textArea.textProperty().addListener((obs, oldValue, newValue) -> {
            if (statusLabel.getText().equals("Готовий для роботи") || statusLabel.getText().equals("Файл успішно збережено")) {
                statusLabel.setText("Редагування, зміни не збережено");
            }
        });

        // Відображення елементів
        VBox vbox = new VBox(menuBar, toolBar, root);
        primaryStage.setScene(new Scene(vbox));
        primaryStage.show();
    }

    private void applyStyleToSelectedText(String style) {
        int startIndex = textArea.getSelection().getStart();
        int endIndex = textArea.getSelection().getEnd();
        String selectedText = textArea.getText(startIndex, endIndex);
        String textBefore = textArea.getText(0, startIndex);
        String textAfter = textArea.getText(endIndex, textArea.getLength());
        textArea.setText(textBefore + selectedText + textAfter);
        textArea.positionCaret(startIndex);
        textArea.selectRange(startIndex, startIndex + selectedText.length());
        textArea.setStyle(style);
    }

    private void openFile(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Відкрити файл");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстові файли", "*.docx"));
        File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder text = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    text.append(line).append("\n");
                }
                textArea.setText(text.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile(Stage primaryStage,Label statusLabel) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Зберегти файл");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстові файли", "*.txt"));
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(textArea.getText());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        statusLabel.setText("Файл успішно збережено");

    }

}
