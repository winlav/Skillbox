package com.example.contacts5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
public class ContactController {
    private final ContactService contactService;
    private final BufferedReader reader;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void handleUserInput() {
        boolean running = true;

        while (running) {
            displayMenu();

            try {
                int choice = Integer.parseInt(reader.readLine());
                System.out.println(" ");
                switch (choice) {
                    case 1:
                        showAllContacts();
                        break;
                    case 2:
                        addContact();
                        break;
                    case 3:
                        deleteContact();
                        break;
                    case 4:
                        saveContactsToFile();
                        break;
                    case 5:
                        running = false;
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

    private void showAllContacts() {
        contactService.loadContactsFromFile();
        for (Contact contact : contactService.getAllContacts()) {
            System.out.println(contact.getFullName() + " | " + contact.getPhoneNumber() + " | " + contact.getEmail());
        }
    }

    private void addContact() {
        try {
            System.out.println("Введите данные нового контакта в формате 'Ф. И. О.;номер телефона;адрес электронной почты':");
            String contactInfo = reader.readLine();
            contactService.addContact(contactInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteContact() {
        try {
            System.out.println("Введите email контакта для удаления:");
            String email = reader.readLine();
            contactService.deleteContactByEmail(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveContactsToFile() {
        contactService.saveContactsToFile();
    }
}
