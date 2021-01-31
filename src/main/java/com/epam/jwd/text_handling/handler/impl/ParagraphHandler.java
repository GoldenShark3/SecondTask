package com.epam.jwd.text_handling.handler.impl;

import com.epam.jwd.text_handling.model.Component;
import com.epam.jwd.text_handling.model.impl.Composite;
import com.epam.jwd.text_handling.handler.TextHandler;
import com.epam.jwd.text_handling.util.ParserRegex;
import java.util.regex.Pattern;

public class ParagraphHandler extends TextHandler {
    public static final ParagraphHandler INSTANCE = new ParagraphHandler();
    private final Pattern PARAGRAPH_PATTERN = Pattern.compile(ParserRegex.PARAGRAPH_REGEX.getRegex());
    private final TextHandler NEXT_HANDLER = SentenceHandler.INSTANCE;

    private ParagraphHandler(){
    }

    @Override
    public Composite handleRequest(String line) {
        Composite text = new Composite();
        matcher = PARAGRAPH_PATTERN.matcher(line);

        while (matcher.find()) {
            Component paragraph = NEXT_HANDLER.handleRequest(matcher.group());
            text.addComponent(paragraph);
        }

        return text;
    }
}
