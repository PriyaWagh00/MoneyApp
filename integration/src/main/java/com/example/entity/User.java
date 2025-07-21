//package com.example.entity;
//
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//
//
//@Entity
//@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank(message = "Name is required")
//    private String name;
//
//    @Email(message = "Invalid email format")
//    @NotBlank(message = "Email is required")
//    private String email;
//    @Column(unique = true)
//    private String mobile; 
//    public String getMobile() {
//		return mobile;
//	}
//
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//
//	@NotBlank(message = "Password is required")
//    private String password;
//    private String resetToken; 
//    private LocalDateTime tokenExpiration;
//    private String role; 
//    
//    
////    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
////    private List<Cart> cartItems;
////    
////    
////    
////
////
////	public List<Cart> getCartItems() {
////		return cartItems;
////	}
////
////	public void setCartItems(List<Cart> cartItems) {
////		this.cartItems = cartItems;
////	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email!= null ? email.toLowerCase() : null;;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//
//	public String getResetToken() {
//		return resetToken;
//	}
//
//	public void setResetToken(String resetToken) {
//		this.resetToken = resetToken;
//	}
//
//	public LocalDateTime getTokenExpiration() {
//		return tokenExpiration;
//	}
//
//	public void setTokenExpiration(LocalDateTime tokenExpiration) {
//		this.tokenExpiration = tokenExpiration;
//	}
//
//	
//
//    
//}




//
//package com.example.entity;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//
//@Entity
//@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank(message = "Name is required")
//    private String name;
//
//    @Email(message = "Invalid email format")
//    @NotBlank(message = "Email is required")
//    private String email;
//
//    @Column(unique = true)
//    private String mobile;
//
//    @NotBlank(message = "Password is required")
//    private String password;
//
//    private String resetToken;
//
//    private LocalDateTime tokenExpiration;
//
//    private String role;
//
//    private Double balance = 1000.0; // Default balance for new users
//
//    // Relationships with Transactions (Optional, for querying purposes)
//    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
//    private List<Transaction> sentTransactions;
//
//    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
//    private List<Transaction> receivedTransactions;
//
//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email != null ? email.toLowerCase() : null;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public String getResetToken() {
//        return resetToken;
//    }
//
//    public void setResetToken(String resetToken) {
//        this.resetToken = resetToken;
//    }
//
//    public LocalDateTime getTokenExpiration() {
//        return tokenExpiration;
//    }
//
//    public void setTokenExpiration(LocalDateTime tokenExpiration) {
//        this.tokenExpiration = tokenExpiration;
//    }
//
//    public Double getBalance() {
//        return balance;
//    }
//
//    public void setBalance(Double balance) {
//        this.balance = balance;
//    }
//
//    public List<Transaction> getSentTransactions() {
//        return sentTransactions;
//    }
//
//    public void setSentTransactions(List<Transaction> sentTransactions) {
//        this.sentTransactions = sentTransactions;
//    }
//
//    public List<Transaction> getReceivedTransactions() {
//        return receivedTransactions;
//    }
//
//    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
//        this.receivedTransactions = receivedTransactions;
//    }
//}
//



package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Column(unique = true)
    private String mobile;

    @NotBlank(message = "Password is required")
    private String password;

    private String resetToken;

    private LocalDateTime tokenExpiration;

    private String role;

    private Double balance = 1000.0; // Default balance for new users

    // Relationships with Transactions (Optional, for querying purposes)
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> receivedTransactions;
    
   

	// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email != null ? email.toLowerCase() : null;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public LocalDateTime getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(LocalDateTime tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Transaction> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<Transaction> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }
}
