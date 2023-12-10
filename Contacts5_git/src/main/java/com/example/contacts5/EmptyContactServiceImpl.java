package com.example.contacts5;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmptyContactServiceImpl implements ContactService {

    @Override
    public List<Contact> getAllContacts() {
        return Collections.emptyList(); // Возвращает пустой список контактов
    }

    @Override
    public void addContact(String contactInfo) {
        // Ничего не делает, так как это заглушка
    }

    @Override
    public void deleteContactByEmail(String email) {
        // Ничего не делает, так как это заглушка
    }

    @Override
    public void saveContactsToFile() {
        // Ничего не делает, так как это заглушка
    }

    @Override
    public void loadContactsFromFile() {

    }
}
