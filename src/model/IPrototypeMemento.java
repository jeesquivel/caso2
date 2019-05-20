package model;

public interface IPrototypeMemento<T extends IPrototypeMemento> extends Cloneable{
    public T clone();
    public T deepClone();
}
