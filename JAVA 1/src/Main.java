import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("---------TASK 1 ------ ");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть к-сть рядків: ");
        int m = scanner.nextInt();
        System.out.println("Введіть к-сть стопців: ");
        int n = scanner.nextInt();

        scanner.close();
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) (Math.random() * 100); // Генеруємо випадкові значення від 0 до 99
            }
        }


        int[] maxSumDigitsVector = new int[m];

        for (int i = 0; i < m; i++) {
            int maxSumDigits = 0;
            for (int j = 0; j < n; j++) {
                int num = matrix[i][j];
                int sumDigits = 0;
                while (num > 0) {
                    sumDigits += num % 10; // Додаємо останню цифру числа до суми
                    num /= 10; // Видаляємо останню цифру числа
                }
                if (sumDigits > maxSumDigits) {
                    maxSumDigits = sumDigits;
                }
            }
            maxSumDigitsVector[i] = maxSumDigits;
        }

        System.out.println("Виводимо матрицю: ");
        for (int i = 0; i < m; i++) {
             for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }


        System.out.println("Вектор максимумів суми цифр:");
        for (int i = 0; i < m; i++) {
            System.out.print(maxSumDigitsVector[i] + " ");
        }

    }
};


