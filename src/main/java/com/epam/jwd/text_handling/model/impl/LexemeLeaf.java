package com.epam.jwd.text_handling.model.impl;

import com.epam.jwd.text_handling.model.Component;

import java.util.List;
import java.util.Objects;

public class LexemeLeaf implements Component {

    private final String word;

    public LexemeLeaf(String word) {
        this.word = word;
    }

    @Override
    public String buildStr() {
        return word;
    }

    @Override
    public void addComponent(Component component) {
    }

    @Override
    public void removeComponent(Component component) {
    }

    @Override
    public int receiveAmountOfComponents() {
       throw new UnsupportedOperationException("Unsupported operation with leaf");
    }

    @Override
    public List<Component> getComponents() {
        throw new UnsupportedOperationException("Unsupported operation with leaf");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LexemeLeaf word1 = (LexemeLeaf) o;
        return Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

}
