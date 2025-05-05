import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    static final int MAX_RECORDS = 50;
    static LocalDateTime[] dates = new LocalDateTime[MAX_RECORDS];
    static String[] texts = new String[MAX_RECORDS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("Щоденник котика .");

        while (running) {
            System.out.println("\nОберіть дію яку зроблять котики :");
            System.out.println("1 - Котик додасть запис");
            System.out.println("2 - Котик видалить запис");
            System.out.println("3 - Переглянути всі записи котиків");
            System.out.println("0 - Вийти");
            System.out.print("Оберіть дію котика: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addEntry(scanner);
                    break;
                case "2":
                    deleteEntry(scanner);
                    break;
                case "3":
                    showEntries();
                    break;
                case "0":
                    running = false;
                    System.out.println("До побачення! Нехай ваші спогади залишаються теплими, як пухнастий котик на колінах.");
                    break;
                default:
                    System.out.println("Котик не знає що робити, таких команд ми ще не знаємо(.");
            }
        }

        scanner.close();
    }

    static void addEntry(Scanner scanner) {
        System.out.print("Введіть дату та час запису аби котик зрозумів що робити (у форматі РРРР-ММ-ДД[T]гг:хх[:сс]): ");
        String dateTimeString = scanner.nextLine();

        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dateTimeString);
        } catch (Exception e) {
            System.out.println("Некоректний формат дати та часу. Котик збентежений. Спробуйте ще раз у форматі РРРР-ММ-ДД[T]гг:хх[:сс].");
            return;
        }

        System.out.println("Введіть текст запису аби котик записав ващі думки (для завершення натисніть Enter на порожньому рядку):");
        String text = "";
        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break;
            text += (text.isEmpty() ? "" : "\n") + line;
        }

        for (int i = 0; i < MAX_RECORDS; i++) {
            if (dates[i] == null) {
                dates[i] = dateTime;
                texts[i] = text;
                System.out.println("Запис успішно додано. Це як залишити теплий слід лапки в пам’яті.");
                return;
            }
        }

        System.out.println("Котик заповнив щоденник на максимум. Видаліть запис, щоб додати новий, котик прийняв забагато інформації.");
    }

    static void deleteEntry(Scanner scanner) {
        System.out.print("Введіть дату та час запису, аби котив знав що потрібно видалити (у форматі РРРР-ММ-ДД[T]гг:хх[:сс]): ");
        String dateTimeString = scanner.nextLine();

        try {
            LocalDateTime dateTimeToDelete = LocalDateTime.parse(dateTimeString);
            for (int i = 0; i < MAX_RECORDS; i++) {
                if (dateTimeToDelete.equals(dates[i])) {
                    dates[i] = null;
                    texts[i] = null;
                    System.out.println("Запис видалено. Іноді потрібно залишити місце для нових муркотінь.");
                    return;
                }
            }
            System.out.println("Котик не знайшов запис за вказаною датою та часом.");
        } catch (Exception e) {
            System.out.println("Некоректний формат дати та часу. Спробуйте ще раз у форматі РРРР-ММ-ДД[T]гг:хх[:сс].");
        }
    }

    static void showEntries() {
        boolean empty = true;
        System.out.println("Ваші записи:");

        for (int i = 0; i < MAX_RECORDS; i++) {
            if (dates[i] != null) {
                System.out.println("Дата та час: " + dates[i]);
                System.out.println(texts[i]);
                empty = false;
            }
        }

        if (empty) {
            System.out.println("Записів поки що немає. Але котики вірять у вас.");
        }
    }
}
