package com.epam.jwd.text_handling.handler.impl;

import com.epam.jwd.text_handling.model.Component;
import com.epam.jwd.text_handling.model.impl.Composite;
import com.epam.jwd.text_handling.handler.TextHandler;
import com.epam.jwd.text_handling.util.ParserRegex;
import java.util.regex.Pattern;

public class SentenceHandler extends TextHandler {
    public final static SentenceHandler INSTANCE = new SentenceHandler();
    private final Pattern SENTENCE_PATTERN = Pattern.compile(ParserRegex.SENTENCE_REGEX.getRegex());
    private final TextHandler NEXT_HANDLER = LexemeHandler.INSTANCE;

    private SentenceHandler(){
    }

    @Override
    public Composite handleRequest(String line) {
        Composite composite = new Composite();
        matcher = SENTENCE_PATTERN.matcher(line);

        while(matcher.find()) {
            Component sentence = NEXT_HANDLER.handleRequest(matcher.group());
            composite.addComponent(sentence);
        }

        return composite;
    }
}
