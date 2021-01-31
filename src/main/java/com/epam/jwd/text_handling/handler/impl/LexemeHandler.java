package com.epam.jwd.text_handling.handler.impl;

import com.epam.jwd.text_handling.handler.TextHandler;
import com.epam.jwd.text_handling.interpeter.ExpressionParser;
import com.epam.jwd.text_handling.model.Component;
import com.epam.jwd.text_handling.model.impl.Composite;
import com.epam.jwd.text_handling.model.impl.LexemeLeaf;
import com.epam.jwd.text_handling.model.impl.PunctuationMarkLeaf;
import com.epam.jwd.text_handling.util.ParserRegex;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeHandler extends TextHandler {
    public static final LexemeHandler INSTANCE = new LexemeHandler();
    private final ExpressionParser EXPRESSION_PARSER = ExpressionParser.INSTANCE;
    private final Pattern LEXEME_PATTERN = Pattern.compile(ParserRegex.LEXEME_REGEX.getRegex());
    private final Pattern WORD_AND_PUNCTUATION_PATTERN = Pattern.compile(ParserRegex.WORD_AND_PUNCTUATION_REGEX.getRegex());
    private final String EXPRESSION_REGEX = ParserRegex.EXPRESSION_REGEX.getRegex();

    private LexemeHandler(){
    }

    @Override
    public Composite handleRequest(String line) {
        Composite sentence = new Composite();
        matcher = LEXEME_PATTERN.matcher(line);

        while (matcher.find()) {
            String lexeme = matcher.group().trim();
            parseToWordAndPunctuation(sentence, lexeme);
            calculateExpressionValue(sentence);
        }
        return sentence;
    }

    private void parseToWordAndPunctuation(Composite composite, String lexeme) {
        Matcher punctuationMatcher = WORD_AND_PUNCTUATION_PATTERN.matcher(lexeme);
        while(punctuationMatcher.find()) {
            String matchesLine = punctuationMatcher.group();
            if (matchesLine.matches(ParserRegex.PUNCTUATION_REGEX.getRegex())) {
                Component punctuation = new PunctuationMarkLeaf(matchesLine);
                composite.addComponent(punctuation);
            } else {
                Component word = new LexemeLeaf(matchesLine);
                composite.addComponent(word);
            }
        }
    }

    private void calculateExpressionValue(Composite composite) {
        List<Component> listOfLexemes = composite.getComponents();
        for (int i = 0; i < listOfLexemes.size(); i++) {
            if (listOfLexemes.get(i) instanceof LexemeLeaf
                    && listOfLexemes.get(i).buildStr().matches(EXPRESSION_REGEX)){

                int calculatedValue = EXPRESSION_PARSER.calculateExpression(listOfLexemes.get(i).buildStr());
                composite.changeComponentByIndex(i, new LexemeLeaf(String.valueOf(calculatedValue)));
            }
        }
    }
}
