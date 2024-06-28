import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TextEditor extends Application {

    public static void main(String[] args) {
        launch(args);
    }

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
        toolBar.getItems().addAll(boldButton, italicButton, underlineButton);

        // Редагування тексту
        TextArea textArea = new TextArea();
        root.setCenter(textArea);

        // Рядок стану
        Label statusLabel = new Label("Готово");
        root.setBottom(statusLabel);

        // Додавання обробників подій для елементів (наприклад, для відкриття та збереження файлів)

        newMenuItem.setOnAction(e -> {
            textArea.clear();
        });

        openMenuItem.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            fileChooser.setTitle("Відкрити файл");
            textArea.setText("Реалізуйте код для відкриття файлу з діалоговим вікном тут.");
        });

        saveMenuItem.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            fileChooser.setTitle("Зберегти файл");
            textArea.setText("Реалізуйте код для збереження файлу з діалоговим вікном тут.");
        });

        exitMenuItem.setOnAction(e -> {
            primaryStage.close();
        });

        // Обробники подій для панелі інструментів (додайте функціональність для форматування тексту)

        boldButton.setOnAction(e -> {
            // Реалізуйте код для застосування жирного стилю до виділеного тексту
        });

        italicButton.setOnAction(e -> {
            // Реалізуйте код для застосування курсивного стилю до виділеного тексту
        });

        underlineButton.setOnAction(e -> {
            // Реалізуйте код для застосування підкреслення до виділеного тексту
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
