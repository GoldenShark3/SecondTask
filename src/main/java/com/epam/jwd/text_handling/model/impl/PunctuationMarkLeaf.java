package com.epam.jwd.text_handling.model.impl;

import com.epam.jwd.text_handling.model.Component;

import java.util.List;
import java.util.Objects;

public class PunctuationMarkLeaf implements Component {
    private final String punctuationMark;

    public PunctuationMarkLeaf(String punctuationMark) {
        this.punctuationMark = punctuationMark;
    }

    @Override
    public String buildStr() {
        return punctuationMark;
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
        PunctuationMarkLeaf that = (PunctuationMarkLeaf) o;
        return punctuationMark.equals(that.punctuationMark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(punctuationMark);
    }
}
