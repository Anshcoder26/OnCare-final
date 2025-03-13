package com.example.ansh.oncare.model;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Data
@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;

    private String name;

    @Indexed(unique = true)
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
