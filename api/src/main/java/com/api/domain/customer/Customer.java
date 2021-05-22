package com.api.domain.customer;

import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String encryptedPassword;


   public Customer(Long id, String firstName, String lastName, String username, String encryptedPassword) {
	   this.id = id;
       this.firstName = firstName;
       this.lastName = lastName;
       this.username = username;
       this.encryptedPassword = encryptedPassword;
   }

   public Customer() {

   }

	public Long getId() {
	    return id;
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
	
	public String getEncryptedPassword() {
	    return encryptedPassword;
	}
}
