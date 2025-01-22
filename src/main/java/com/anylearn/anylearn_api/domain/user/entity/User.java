package com.anylearn.anylearn_api.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity(name = "users")
@Data
@Builder
@AllArgsConstructor
public class User {
    User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String phone;

    private String name;

    private String role;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
