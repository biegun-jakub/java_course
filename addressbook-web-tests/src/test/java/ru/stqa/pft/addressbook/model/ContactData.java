package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String firstName;
    private final String address;
    private final String email;
    private final String lastName;
    private final String phoneNumber;
    private String group;

    public ContactData(int id, String firstName, String address, String email, String lastName, String phoneNumber, String group) {
        this.id = id;
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.group = group;
    }

    public ContactData(String firstName, String address, String email, String lastName, String phoneNumber, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.group = group;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
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
