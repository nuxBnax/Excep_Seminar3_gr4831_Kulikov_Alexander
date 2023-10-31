import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            writer(validation(inputData()));
        } catch (NullPointerException | UserArraySizeException | IncorrectFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static String[] inputData() throws NullPointerException, UserArraySizeException {
        Scanner cs = new Scanner(System.in);

        System.out.println("Введите данные пользователя, используя пробел, согласно формату: \n" +
                "Фамилия Имя Отчество датаРождения номерТелефона пол \n" +
                "Например: \n" +
                "Иванов Иван Иванович 15.12.2000 48464 m");

        String userString = cs.nextLine();
        String[] userData = userString.strip().split(" ");

        if (userData[0].equals(""))
            throw new NullPointerException("Вы не ввели никаких данных");
        if (userData.length != 6)
            throw new UserArraySizeException("Количество элементов, указанных через пробел, больше или меньше требуемых");

        return userData;
    }
    static User validation(String[] userData) throws IncorrectFormatException {
        if (isNumeric(userData[0]) || isNumeric(userData[1]) || isNumeric(userData[2])) {
            throw new IncorrectFormatException("Неверный формат Фамилии/Имени/Отчества, он должен состоять из букв, а вы ввели цифры");
        }
        String surName = userData[0];

        String name = userData[1];

        String middleName = userData[2];


        try {
            String[] userBirthDate = userData[3].split("\\.");
            if(userBirthDate.length != 3){
                throw new IncorrectFormatException("Неверный формат даты");
            }
            if (!isNumeric(userBirthDate[0]) || !isNumeric(userBirthDate[1]) || !isNumeric(userBirthDate[2])) {
                throw new IncorrectFormatException("Неверный формат даты, введены не цифры");
            }
            if (userBirthDate[0].length()!=2 || userBirthDate[1].length() != 2 || userBirthDate[2].length() != 4){
                throw new IncorrectFormatException("Неверный формат даты, формат должен соответствовать виду dd.mm.yyyy");
            }
        } catch (RuntimeException e) {
            throw new IncorrectFormatException("Неверный формат даты, в качестве разделителя должна быть точка");
        }

        String birthDate = userData[3];

        if (!isNumeric(userData[4])) {
            throw new IncorrectFormatException("Неверный формат номера телефона, он должен состоять из цифр,а вы ввели " + userData[4]);
        }
        Integer phoneNumber = Integer.valueOf(userData[4]);

        if (!(userData[5].equalsIgnoreCase("m")) && !(userData[5].equalsIgnoreCase("f"))) {
            throw new IncorrectFormatException("Неверный формат пола, должен быть m или f, а вы ввели " + userData[5]);
        }
        String sex = userData[5];

        User user = new User(surName, name, middleName, birthDate, phoneNumber, sex);

        return user;
    }
    static void writer(User user) {

        String fileName = user.getSurName() + ".txt";
        fileName = fileName.toLowerCase();

        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(user + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static boolean isNumeric(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}