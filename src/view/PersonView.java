package view;

import model.Person;

import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class PersonView<E extends Person>{
    protected Scanner sc = new Scanner(System.in);
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{9,11}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    public E inputPerson() {
        String name;
        String phoneNumber;
        String emailAddress;
        int indexProject = 0;

        while (true) {
            try {
                System.out.print("Tên: ");
                name = sc.nextLine().trim();
                if (name.isEmpty()) {
                    throw new Exception("Tên không được để trống!");
                }
                break;
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("SDT: ");
                phoneNumber = sc.nextLine().trim();
                if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
                    throw new Exception("Số điện thoại không hợp lệ! Phải là 9-11 chữ số.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Email: ");
                emailAddress = sc.nextLine().trim();
                if (!EMAIL_PATTERN.matcher(emailAddress).matches()) {
                    throw new Exception("Email không hợp lệ!");
                }
                break;
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }

        return createPerson(name, phoneNumber, emailAddress, indexProject);
    }

    protected E createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        return null;
    }

}
