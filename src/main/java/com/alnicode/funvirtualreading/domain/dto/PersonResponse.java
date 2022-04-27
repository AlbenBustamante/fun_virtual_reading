package com.alnicode.funvirtualreading.domain.dto;

import java.util.HashSet;
import java.util.Set;

import com.alnicode.funvirtualreading.persistence.entity.Book;

import lombok.Data;

@Data
public class PersonResponse {
    private long id;
    private String name;
    private String lastname;
    private String email;
    private String birthday;
    private String nationality;
    private String date;
    private Set<Book> likes = new HashSet<>();
}
