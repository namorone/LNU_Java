import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

import java.util.Random;

public class EpicycloidGraphApp extends Application {

    private Canvas canvas;
    private GraphicsContext gc;
    private double lineWidth = 1.0; // Товщина ліній по замовчуванню
    private Color lineColor = Color.BLUE; // Колір ліній по замовчуванню
    private double A = 3.0; // Значення A
    private double a = 2.0; // Значення a
    private Random random = new Random();
    private double scale = 20.0; // Масштаб графіка по x
    private double scale2 = 20.0; // Масштаб графіка по y
    private StrokeLineCap[] lineCaps = {StrokeLineCap.BUTT, StrokeLineCap.ROUND, StrokeLineCap.SQUARE};
    private int currentLineCapIndex = 0;

    private int currentDashedPatternIndex = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Епіциклоїда");

        BorderPane root = new BorderPane();
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        root.setCenter(canvas);

        // Малюємо епіциклоїду, осі та виводимо інформацію про автора
        draw(); // Викликаємо метод малювання при старті програми


        // Додаємо обробник подій миші для зміни параметрів ліній
        canvas.setOnMouseClicked(this::changeLineProperties);

        Scene scene = new Scene(root, 800, 600);

        // Додаємо слухач події зміни розміру вікна для масштабування графіка
        primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> {
            canvas.setWidth(newValue.doubleValue());
            draw();

        });

        primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> {
            canvas.setHeight(newValue.doubleValue());
            draw();

        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void draw() {
        // Очистити Canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Збільшена ширина і висота Canvas
        double centerX = canvas.getWidth() / 2;
        double centerY = canvas.getHeight() / 2;

        scale = canvas.getWidth() / 20; // Масштаб відповідно до розміру вікна x
        scale2 =  canvas.getHeight() / 20; // Масштаб відповідно до розміру вікна y


        gc.setStroke(lineColor);
        gc.setLineWidth(lineWidth);
        gc.setLineCap(lineCaps[currentLineCapIndex]);
        // Встановлюємо пунктирний стиль лінії
        gc.setLineDashes(50,50);
//        gc.setLineDashOffset(0); // Починаємо з початку паттерну


        double t = 0.0;
        double x0, y0, x1, y1;

        x0 = centerX + scale * ((A + a) * Math.cos(t) - a * Math.cos((A / a + 1) * t));
        y0 = centerY + scale2 * ((A + a) * Math.sin(t) - a * Math.sin((A / a + 1) * t));

        while (t < 5 * Math.PI) {
            t += 0.1;
            x1 = centerX + scale * ((A + a) * Math.cos(t) - a * Math.cos((A / a + 1) * t));
            y1 = centerY + scale2 * ((A + a) * Math.sin(t) - a * Math.sin((A / a + 1) * t));
            gc.strokeLine(x0, y0, x1, y1);
            x0 = x1;
            y0 = y1;
        }
        drawAxes();
        drawAuthorInfo();
    }

    private void drawAxes() {
        // Опишіть код для малювання осей координат на canvas
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
        gc.setLineDashes(0,0);
        gc.strokeLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2);
        gc.strokeLine(canvas.getWidth() / 2, 0, canvas.getWidth() / 2, canvas.getHeight());
    }

    private void drawAuthorInfo() {
        // Опишіть код для виведення вашого прізвища і номера варіанта на canvas
        gc.setFill(Color.BLACK);
        gc.fillText("Автор: Боровець Роман", 10, 20);
        gc.fillText("№ варіанта:  1", 10, 40);
    }

    private void changeLineProperties(MouseEvent event) {
        // Обробник події кліку миші для зміни параметрів ліній
        lineColor = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        lineWidth = random.nextDouble() * 20 + 1; // Товщина від 1 до 101
        currentLineCapIndex = random.nextInt(lineCaps.length); // Випадковий стиль ліній
        currentDashedPatternIndex = random.nextInt(); // Випадковий паттерн


        draw(); // Перемалювати графік з новими параметрами
    }
}