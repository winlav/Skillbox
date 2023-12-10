package com.example.contacts5;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
@Primary
public class ContactServiceImpl implements ContactService {

    @Value("${fpath}") private String filePath;
    private Map<String, Contact> contacts = new HashMap<>();
    @Override
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts.values());
    }
    @Override
    public void addContact(String contactInfo) {
        String[] contactData = contactInfo.split(";"); // Разделение введенных данных
        if (contactData.length == 3) {
            Contact contact = new Contact(contactData[0], contactData[1], contactData[2]);
            contacts.put(contact.getEmail(), contact);
            System.out.println("Контакт успешно добавлен.");
        } else {
            System.out.println("Ошибка: Некорректный формат ввода контакта.");
        }
    }

    @Override
    public void deleteContactByEmail(String email) {
        if (contacts.containsKey(email)) {
            contacts.remove(email);
            System.out.println("Контакт с email " + email + " успешно удален.");
        } else {
            System.out.println("Ошибка: Контакт с указанным email не найден.");
        }
    }

    @Override
    public void saveContactsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Contact contact : contacts.values()) {
                writer.println(contact.getFullName() + ";" + contact.getPhoneNumber() + ";" + contact.getEmail());
            }
            writer.close();
            System.out.println("Контакты успешно сохранены в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении контактов в файл.");
        }
    }
    @Override
    public void loadContactsFromFile() {
//        System.out.println("Файл с контактами должен быть тут: " + filePath);
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] contactData = scanner.nextLine().split(";");
                if (contactData.length == 3) {
                    Contact contact = new Contact(contactData[0], contactData[1], contactData[2]);
                    contacts.put(contact.getEmail(), contact);
                }
            }
            System.out.println("Контакты успешно загружены из файла.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл с контактами не найден. Создан новый файл.");
        }
    }
}