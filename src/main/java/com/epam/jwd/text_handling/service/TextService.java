package com.epam.jwd.text_handling.service;

import com.epam.jwd.text_handling.model.Component;
import com.epam.jwd.text_handling.model.impl.Composite;
import com.epam.jwd.text_handling.model.impl.LexemeLeaf;

import java.util.Comparator;
import java.util.List;

public class TextService {
    private static TextService instance;

    private TextService() {
    }

    public static TextService getInstance() {
        if (instance == null) {
            instance = new TextService();
        }
        return instance;
    }

    public Composite sortParagraphsByAmountOfSentence(Composite composite) {
        List<Component> listOfParagraphs = composite.getComponents();
        listOfParagraphs.sort(Comparator.comparingInt(Component::receiveAmountOfComponents));
        return new Composite(listOfParagraphs);
    }

    public Composite removeSentencesWithLessSize(Composite composite, int sentenceSize) {
        int count = 0;
        List<Component> paragraphList = composite.getComponents();
        for (Component paragraph : paragraphList) {
            for (Component sentence : paragraph.getComponents()) {
                for (Component lexeme : sentence.getComponents()) {
                    if (lexeme instanceof LexemeLeaf) {
                        count++;
                    }
                }
                if (count < sentenceSize) {
                    paragraph.removeComponent(sentence);
                }
                count = 0;
            }
        }
        return new Composite(paragraphList);
    }

    public String findSentenceWithLongestWord(Composite composite) {
        int maxLength = -1;
        Component longestSentence = null;

        for (Component paragraph : composite.getComponents()){
            for (Component sentence : paragraph.getComponents()) {
                for (Component word : sentence.getComponents()) {
                    if (word.buildStr().length() > maxLength) {
                        maxLength = word.buildStr().length();
                        longestSentence = sentence;
                    }
                }
            }
        }
        return longestSentence.buildStr();
    }

    public void printText(Composite text) {
        List<Component> textComponents = text.getComponents();
        for (Component component : textComponents) {
            System.out.println("    " + component.buildStr());
        }
    }

}
