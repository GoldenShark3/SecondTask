package com.epam.jwd.text_handling.handler;

import com.epam.jwd.text_handling.model.impl.Composite;
import java.util.regex.Matcher;

public abstract class TextHandler {
    protected Matcher matcher;

    public abstract Composite handleRequest(String line);
}
