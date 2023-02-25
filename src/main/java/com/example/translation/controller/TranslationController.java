package com.example.translation.controller;

import com.example.translation.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @GetMapping("/")
    public List<String> getAllLanguageNames() {
        return translationService.getAllSupportedLanguages();
    }

    @GetMapping("/translate")
    public List<String> translate(@RequestParam String targetLanguageName, @RequestParam String text) throws IOException {
        return translationService.translateText(targetLanguageName, text);
    }
}
