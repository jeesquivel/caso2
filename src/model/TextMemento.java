package model;

public class TextMemento {
    String text;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextMemento(String text) {
        this.text = text;
    }
}
