package example.org.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contacts {
    private String fullName;
    private String phoneNumber;
    private String email;

    public void add(Contacts newContact) {
    }
}