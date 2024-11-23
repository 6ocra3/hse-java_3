import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ФИО: ");
        String fullName = scanner.nextLine().trim();
        System.out.print("Введите дату рождения (дд.мм.гггг): ");
        String birthDate = scanner.nextLine().trim();

        String[] nameParts = fullName.split("\\s+");
        if (nameParts.length != 3) {
            System.out.println("Ошибка: ФИО должно содержать три слова (Фамилия Имя Отчество).");
            return;
        }

        String lastName = nameParts[0];
        String firstName = nameParts[1];
        String patronymic = nameParts[2];

        String initials = lastName + " " + firstName.charAt(0) + "." + patronymic.charAt(0) + ".";

        String gender = determineGender(patronymic);

        int age = calculateAge(birthDate);
        if (age == -1) {
            System.out.println("Ошибка: неверный формат даты.");
            return;
        }

        String ageEnding = getAgeEnding(age);

        System.out.println("Инициалы: " + initials);
        System.out.println("Пол: " + gender);
        System.out.println("Возраст: " + age + " " + ageEnding);
    }

    private static String determineGender(String patronymic) {
        if (patronymic.endsWith("ич")) {
            return "М";
        } else if (patronymic.endsWith("на")) {
            return "Ж";
        } else {
            return "ОПРЕДЕЛИТЬ_НЕ_УДАЛОСЬ";
        }
    }

    private static int calculateAge(String birthDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate birth = LocalDate.parse(birthDate, formatter);
            LocalDate now = LocalDate.now();
            return Period.between(birth, now).getYears();
        } catch (Exception e) {
            return -1;
        }
    }

    private static String getAgeEnding(int age) {
        if (age % 10 == 1 && age % 100 != 11) {
            return "год";
        } else if ((age % 10 >= 2 && age % 10 <= 4) && !(age % 100 >= 12 && age % 100 <= 14)) {
            return "года";
        } else {
            return "лет";
        }
    }
}