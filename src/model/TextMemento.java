package model;

import org.fxmisc.richtext.model.StyledDocument;

public class TextMemento implements IPrototypeMemento {
    String  text;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextMemento(String text) {
        this.text = text;
    }

    @Override
    public IPrototypeMemento clone() {
        return this;
    }

    @Override
    public IPrototypeMemento deepClone() {
        return  new TextMemento(text);
    }
}
