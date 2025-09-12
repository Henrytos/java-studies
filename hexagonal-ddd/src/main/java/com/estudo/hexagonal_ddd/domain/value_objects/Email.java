package com.estudo.hexagonal_ddd.domain.value_objects;

import com.estudo.hexagonal_ddd.domain.exceptions.WrongCredentials;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {
    private String value;
    private final String EMAIL_REGEX =
            "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

    private final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Email(String value) {
        if(!isValidEmail(value)){
            throw  new WrongCredentials(value);
        }

        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
