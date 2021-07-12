package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String address;
    private final String email;
    private final String lastName;
    private final String phoneNumber;

    public ContactData(String firstName, String address, String email, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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
}
