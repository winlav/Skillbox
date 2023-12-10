package com.example.contacts5;

import java.util.List;

public interface ContactService {
    List<Contact> getAllContacts();
    void addContact(String contactInfo);
    void deleteContactByEmail(String email);
    void saveContactsToFile();
    void loadContactsFromFile();


}
