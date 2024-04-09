package example.org.service;

import example.org.dto.Contacts;
import org.springframework.stereotype.Service;

@Service
public class ContactManual {

    public void ContactAdd(Contacts contacts) {
        // Выполнение расчетов
        System.out.println("Добавление контакта");
    }
}
