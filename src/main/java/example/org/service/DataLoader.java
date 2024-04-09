package example.org.service;
import example.org.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Profile("init")
public class DataLoader implements CommandLineRunner {
    private final ContactList contactList;
    private final String contactsFilePath;

    public DataLoader(ContactList contactList,
                      @Value("${contact.file.path.load}") String contactsFilePath) {
        this.contactList = contactList;
        this.contactsFilePath = contactsFilePath;
        System.out.println("Инициализация данных из файла...");
        loadContactsFromFile();
        System.out.println("Данные загружены");
    }

    private void loadContactsFromFile() {
        Path path = Paths.get(contactsFilePath);
        if (!Files.exists(path)) {
            throw new RuntimeException("Не найден файл для загрузки контактов: " + contactsFilePath);
        }
        try (BufferedReader fileReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    Contacts contact = new Contacts();
                    contact.setFullName(parts[0].trim());
                    contact.setPhoneNumber(parts[1].trim());
                    contact.setEmail(parts[2].trim());
                    contactList.add(contact);
                }else {
                    System.err.println("Ошибка: Некорректный формат строки в файле контактов: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла контактов: " + e.getMessage());
        }
    }
    @Override
    public void run(String... args) throws Exception {
    }
}