package model;

import java.util.LinkedList;

public class TextCaretaker {
    private int maxCantMementos;
    private int currentIndex = -1; //  indice para manejar los mementos
    private final LinkedList<TextMemento> mementoLinkedList = new LinkedList<>(); // lista indezada para mementos .... lista  doble enlazada


    public void addMemento(TextMemento memento){

        // mientras la cantidad de menetos sea menor a 20 puede agregar mementos a las mementoLinkedList
        if (currentIndex <=maxCantMementos){
            mementoLinkedList.add(memento); // annado memento a  la linkeList de mementos
            currentIndex++; // sumo al indice para indicar que no hay mas mementos
        }
    }


    public TextMemento getCurrentMememnto(){
        return mementoLinkedList.get(currentIndex);
    }


    // obiene el siguiente memento
    public TextMemento getNextMemento() {
        int newIndex = currentIndex +1;
        if( newIndex >= mementoLinkedList.size()){
            return null;
        }
        currentIndex = newIndex;
        return mementoLinkedList.get(currentIndex);
    }


    //obtiene el memento anterior
    public TextMemento getPreviousMemento() {
        int newIndex = currentIndex -1;

        if(newIndex  <= -1 || newIndex >= mementoLinkedList.size()-1)
            return null;

        currentIndex= newIndex;
        return mementoLinkedList.get(currentIndex);
    }






}
