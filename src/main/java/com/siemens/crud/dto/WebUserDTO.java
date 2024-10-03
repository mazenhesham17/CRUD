package com.siemens.crud.dto;

public class WebUserDTO {
    protected Long id;

    protected String email;

    protected String password;

    protected String firstName;

    public WebUserDTO() {}

    public WebUserDTO(WebUserDTO other) {
        if ( other == null )
            return;
        this.id = other.id;
        this.email = other.email;
        this.password = other.password;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.active = other.active;
        this.role = other.role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRole(String role) {
        this.role = role;
    }

    protected String lastName;

    protected boolean active;

    protected String role;

    public String getRole() {
        return role;
    }
}
