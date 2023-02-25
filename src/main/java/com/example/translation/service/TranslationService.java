package com.example.translation.service;

import com.example.translation.entity.Language;
import com.example.translation.repository.LanguageRepository;
import com.google.cloud.translate.v3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranslationService {

    @Value("${PROJECT_ID}")
    private String projectId;

    @Autowired
    private LanguageRepository languageRepository;

    public List<String> getAllSupportedLanguages() {
        return languageRepository.findAllByOrderByLanguageNameAsc().stream().map(language -> language.getLanguageName()).collect(Collectors.toList());
    }

    // Translate text to target language.
    public List<String> translateText(String targetLanguageName, String text)
            throws IOException {

        Language targetLanguage = languageRepository.findByLanguageName(targetLanguageName);
        // Initialize client that will be used to send requests. This client only needs
        // to be created
        // once, and can be reused for multiple requests. After completing all of your
        // requests, call
        // the "close" method on the client to safely clean up any remaining background
        // resources.
        try (TranslationServiceClient client = TranslationServiceClient.create()) {
            // Supported Locations: `global`, [glossary location], or [model location]
            // Glossaries must be hosted in `us-central1`
            // Custom Models must use the same location as your model. (us-central1)
            LocationName parent = LocationName.of(projectId, "global");

            // Supported Mime Types:
            // https://cloud.google.com/translate/docs/supported-formats
            TranslateTextRequest request = TranslateTextRequest.newBuilder()
                    .setParent(parent.toString())
                    .setMimeType("text/plain")
                    .setTargetLanguageCode(targetLanguage.getLanguageCode())
                    .addContents(text)
                    .build();

            TranslateTextResponse response = client.translateText(request);

            List<String> translations = new ArrayList<>();


            // Display the translation for each input text provided
            for (Translation translation : response.getTranslationsList()) {
                translations.add(translation.getTranslatedText());
            }
            return translations;
        }
    }
}
