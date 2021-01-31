package com.epam.jwd.text_handling.model;

import java.util.List;

public interface Component {
    String buildStr();

    void addComponent(Component component);

    void removeComponent(Component component);

    int receiveAmountOfComponents();

    List<Component> getComponents();
}

