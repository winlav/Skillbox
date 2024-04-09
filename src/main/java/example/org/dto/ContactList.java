package example.org.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class ContactList {
    private List<Contacts> contacts;

    public ContactList() {
        this.contacts = new ArrayList<>();
    }
    public void add(Contacts contact) {
        contacts.add(contact);
    }
}
