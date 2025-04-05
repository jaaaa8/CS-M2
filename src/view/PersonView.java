package view;

import model.Person;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class PersonView<E extends Person> {
    protected Scanner sc = new Scanner(System.in);
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{9,11}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    public E inputPerson() {
        String name = inputName();
        String phone = inputPhoneNumber();
        String email = inputEmailAddress();
        int projectIndex = 0;
        return createPerson(name, phone, email, projectIndex);
    }

    private String inputName() {
        while (true) {
            System.out.print("Tên: ");
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Lỗi: Tên không được để trống!");
        }
    }

    private String inputPhoneNumber() {
        while (true) {
            System.out.print("SDT: ");
            String input = sc.nextLine().trim();
            if (PHONE_PATTERN.matcher(input).matches()) return input;
            System.out.println("Lỗi: Số điện thoại không hợp lệ! Phải là 9-11 chữ số.");
        }
    }

    private String inputEmailAddress() {
        while (true) {
            System.out.print("Email: ");
            String input = sc.nextLine().trim();
            if (EMAIL_PATTERN.matcher(input).matches()) return input;
            System.out.println("Lỗi: Email không hợp lệ!");
        }
    }

    protected abstract E createPerson(String name, String phoneNumber, String emailAddress, int indexProject);
}
