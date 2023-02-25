package com.example.translation.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "LANGUAGE_CODE")
    private String languageCode;

    @Column(name = "LANGUAGE_NAME")
    private String languageName;
}
