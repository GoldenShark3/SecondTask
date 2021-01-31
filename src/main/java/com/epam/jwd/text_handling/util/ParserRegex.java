package com.epam.jwd.text_handling.util;

public enum ParserRegex {
    PARAGRAPH_REGEX("[ ]{4}.*"),
    SENTENCE_REGEX("[^.!?\n]+([.]{3}|[.]|[!?])"),
    LEXEME_REGEX("([^\\s]+)(\\s)*"),
    WORD_AND_PUNCTUATION_REGEX("([.]{3}|[.,!?:;])|([^.,!?:;]+)"),
    EXPRESSION_REGEX("[^a-zA-Zа-яА-Я,;]{2,}"),
    PUNCTUATION_REGEX("[.]{3}|[.,?!:;]");

    private final String regex;

    ParserRegex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
