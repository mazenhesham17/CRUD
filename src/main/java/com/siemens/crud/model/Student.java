package com.siemens.crud.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Student")
public class Student extends WebUser {
}
