package view;

import model.Person;

import java.util.Scanner;

public abstract class PersonView<E extends Person>{
    protected Scanner sc = new Scanner(System.in);

    public E input(){
        System.out.println("Tên: ");
        String name = sc.nextLine();
        System.out.println("SDT: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Email: ");
        String emailAddress = sc.nextLine();
        System.out.println("STT của dự án góp mặt: ");
        int indexProject = sc.nextInt();
        sc.nextLine();
        return createPerson(name, phoneNumber, emailAddress, indexProject);
    }

    protected E createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        return null;
    }

}
