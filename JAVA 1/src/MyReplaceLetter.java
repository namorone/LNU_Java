import java.io.Console;
import java.util.Scanner;

public class MyReplaceLetter {

    public static void main(String[] args) {
        System.out.println("---------TASK 2 ------ ");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть string розілений комами або enter для продовження залаштунками ");
        String S = scanner.nextLine();

        scanner.close();
        String input = "";
        if (S=="") {
             input = "word1,goodbye,programming";
        };


        // Розділяємо вхідний рядок на окремі слова за допомогою коми як роздільника
        String[] words = input.split(",");

        // Цикл для перетворення кожного слова
        for (String word : words) {
            String transformedWord = transformWord(word);
            System.out.print(transformedWord + ", "); // Друк перетвореного слова з комою
        }
    }

    // Метод для перетворення слова


    public static String transformWord(String word) {
        // Заміна кожної 'g' на 'th'
        String transformed = word.replace("g", "th");
        return transformed;
    }
}
