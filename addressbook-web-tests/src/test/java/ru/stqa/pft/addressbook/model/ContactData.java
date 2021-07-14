package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String address;
    private final String email;
    private final String lastName;
    private final String phoneNumber;
    private String group;

    public ContactData(String firstName, String address, String email, String lastName, String phoneNumber, String group) {
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGroup() {
        return group;
    }
}
