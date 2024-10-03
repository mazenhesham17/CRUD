package com.siemens.crud.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Teacher")
public class Teacher extends WebUser {
}
