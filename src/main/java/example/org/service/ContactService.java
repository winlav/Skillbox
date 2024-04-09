package example.org.service;


import example.org.dto.ContactList;
import example.org.dto.Contacts;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;

@Service
public class ContactService {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private final ContactList contactList;
    private final String contactFilePath;

    public ContactService(ContactList contactList,
                          @Value("${contact.file.path.save}") String contactFilePath) {
        this.contactList = contactList;
        this.contactFilePath = contactFilePath;
    }

    @SneakyThrows
    public void addContact() {
        System.out.println("Введите данные нового контакта в формате 'Ф. И. О.; номер телефона; адрес электронной почты':");
        String userInput = reader.readLine();
        String[] parts = userInput.split(";");
        if (parts.length == 3) {
            Contacts newContact = new Contacts();
            newContact.setFullName(parts[0].trim());
            newContact.setPhoneNumber(parts[1].trim());
            newContact.setEmail(parts[2].trim());
            contactList.add(newContact);
            System.out.println("Контакт успешно добавлен.");
        } else {
            System.out.println("Некорректный формат ввода. Попробуйте снова.");
        }
    }

    public void showAllContacts() {
        for (Contacts contact : contactList.getContacts()) {
            System.out.println(formatContact(contact));
        }
    }

    private String formatContact(Contacts contact) {
        return String.format("%s | %s | %s", contact.getFullName(), contact.getPhoneNumber(), contact.getEmail());
    }

    @SneakyThrows
    public void saveContactsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(contactFilePath))) {
            for (Contacts contact : contactList.getContacts()) {
                writer.println(formatContact(contact));
            }
        }
    }

    @SneakyThrows
    public void deleteContactByEmail() {
        System.out.println("Введите email контакта для удаления");
        String userInput = reader.readLine();
        Iterator<Contacts> iterator = contactList.getContacts().iterator();
        while (iterator.hasNext()) {
            Contacts contact = iterator.next();
            if (contact.getEmail().equals(userInput)) {
                iterator.remove();
                System.out.println("Контакт с email " + userInput + " удален успешно.");
                return;
            }
        }
        System.out.println("Контакт с email " + userInput + " не найден.");
    }
}
