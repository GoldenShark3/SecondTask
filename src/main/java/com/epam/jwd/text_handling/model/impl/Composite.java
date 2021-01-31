package com.epam.jwd.text_handling.model.impl;

import com.epam.jwd.text_handling.model.Component;
import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    private List<Component> componentList = new ArrayList<>();

    public Composite() {
    }

    public Composite(List<Component> componentList) {
        this.componentList = componentList;
    }

    @Override
    public String buildStr() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < componentList.size(); i++) {
            if (i != componentList.size() - 1
                    && componentList.get(i + 1) instanceof PunctuationMarkLeaf){
                str.append(componentList.get(i).buildStr());
            } else {
                str.append(componentList.get(i).buildStr()).append(" ");
            }
        }
        return str.toString();
    }

    @Override
    public void addComponent(Component component) {
        componentList.add(component);
    }

    @Override
    public void removeComponent(Component component) {
        componentList.remove(component);
    }

    @Override
    public int receiveAmountOfComponents() {
        return componentList.size();
    }

    @Override
    public List<Component> getComponents() {
        return new ArrayList<>(componentList);
    }

    public void changeComponentByIndex(int index, Component component){
        componentList.set(index, component);
    }
}
