import java.util.Arrays;
import java.util.Comparator;

public class task3 {

    public static void main(String[] args) {
        // Створення масиву об'єктів навчальних закладів
        EducationalInstitution[] institutions = new EducationalInstitution[]{
                new School("Школа 1", "Адреса 1", 1990, 123, 500),
                new University("Університет 1", "Адреса 2", 1965, "Вищий", 8),
                new School("Школа 2", "Адреса 3", 1985, 456, 300),
                new University("Університет 2", "Адреса 4", 1950, "Вищий", 6),
                new University("Університет 3", "Адреса 5", 2000, "Вищий", 10)
        };

        // Завдання 1: Сортування за роком заснування
        Arrays.sort(institutions, Comparator.comparingInt(EducationalInstitution::getFoundingYear));

        System.out.println("Завдання 1: Посортовано за роком заснування");
        for (EducationalInstitution institution : institutions) {
            System.out.println(institution);
        }

        // Завдання 2: Пошук школи з мінімальною кількістю учнів
        School minStudentsSchool = null;
        for (EducationalInstitution institution : institutions) {
            if (institution instanceof School) {
                School school = (School) institution;
                if (minStudentsSchool == null || school.getNumberOfStudents() < minStudentsSchool.getNumberOfStudents()) {
                    minStudentsSchool = school;
                }
            }
        }

        System.out.println("\nЗавдання 2: Школа з мінімальною кількістю учнів");
        System.out.println(minStudentsSchool);

        // Завдання 3: Виведення ВУЗів вказаного рівня акредитації
        String desiredAccreditationLevel = "Вищий";
        System.out.println("\nЗавдання 3: ВУЗи рівня акредитації \"" + desiredAccreditationLevel + "\"");
        for (EducationalInstitution institution : institutions) {
            if (institution instanceof University) {
                University university = (University) institution;
                if (university.getAccreditationLevel().equals(desiredAccreditationLevel)) {
                    System.out.println(university);
                }
            }
        }
    }


}
// Абстрактний базовий клас "Навчальний заклад"
abstract class EducationalInstitution {
    private String name;
    private String address;
    private int foundingYear;

    public EducationalInstitution(String name, String address, int foundingYear) {
        this.name = name;
        this.address = address;
        this.foundingYear = foundingYear;
    }

    public int getFoundingYear() {
        return foundingYear;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "Назва: " + name + ", Адреса: " + address + ", Рік заснування: " + foundingYear;
    }
}

// Похідний клас "СШ"
class School extends EducationalInstitution {
    private int schoolNumber;
    private int numberOfStudents;

    public School(String name, String address, int foundingYear, int schoolNumber, int numberOfStudents) {
        super(name, address, foundingYear);
        this.schoolNumber = schoolNumber;
        this.numberOfStudents = numberOfStudents;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    @Override
    public String getType() {
        return "СШ";
    }

    @Override
    public String toString() {
        return super.toString() + ", Номер школи: " + schoolNumber + ", Кількість учнів: " + numberOfStudents;
    }
}

// Похідний клас "ВУЗ"
class University extends EducationalInstitution {
    private String accreditationLevel;
    private int numberOfFaculties;

    public University(String name, String address, int foundingYear, String accreditationLevel, int numberOfFaculties) {
        super(name, address, foundingYear);
        this.accreditationLevel = accreditationLevel;
        this.numberOfFaculties = numberOfFaculties;
    }

    public String getAccreditationLevel() {
        return accreditationLevel;
    }

    @Override
    public String getType() {
        return "ВУЗ";
    }

    @Override
    public String toString() {
        return super.toString() + ", Рівень акредитації: " + accreditationLevel + ", Кількість факультетів: " + numberOfFaculties;
    }
}
