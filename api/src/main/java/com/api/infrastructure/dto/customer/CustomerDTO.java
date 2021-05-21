package com.api.infrastructure.dto.customer;

public class CustomerDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String plainPassword;

    public CustomerDTO(String firstName, String lastName, String username, String plainPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.plainPassword = plainPassword;
    }

    public CustomerDTO() {

    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPlainPassword() {
        return plainPassword;
    }
}
