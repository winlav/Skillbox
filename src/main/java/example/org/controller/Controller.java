package example.org.controller;

import example.org.service.ContactManual;
import example.org.service.DataLoader;
import example.org.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Controller {

    @Autowired(required = false)
    private DataLoader dataLoader;

    private final ContactService contactService;
    private final BufferedReader reader;

    public Controller(ContactService contactService) {
        this.contactService = contactService;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @PostConstruct
    public void handleUserInput() {
        boolean run = true;

        while (run) {
            displayMenu();

            try {
                int choice = Integer.parseInt(reader.readLine());
                System.out.println(" ");
                switch (choice) {
                    case 1:
                        contactService.showAllContacts();
                        System.out.println("1.");
                        break;
                    case 2:
                        contactService.addContact();
                        System.out.println("2.");
                        break;
                    case 3:
                        contactService.deleteContactByEmail();
                        System.out.println("3.");
                        break;
                    case 4:
                        contactService.saveContactsToFile();
                        System.out.println("4.");
                        break;
                    case 5:
                        run = false;
                        break;
                    default:
                        System.out.println("Некорректный выбор. Попробуйте снова.");
                        break;
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка ввода. Попробуйте снова.");
            }
        }

        System.out.println("Программа завершена.");
        try {
            reader.close(); // Закрываем BufferedReader
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMenu() {

        System.out.println("Выберите действие:");
        System.out.println("1. Показать все контакты");
        System.out.println("2. Добавить контакт");
        System.out.println("3. Удалить контакт по email");
        System.out.println("4. Сохранить контакты в файл");
        System.out.println("5. Выйти");
        System.out.print("Введите номер действия: ");
    }
}
