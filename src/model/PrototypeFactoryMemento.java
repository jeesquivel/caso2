package model;

import java.util.HashMap;

public class PrototypeFactoryMemento {
    // hash de prototypes por nombre
    private static HashMap<String,IPrototypeMemento> prototypes = new HashMap<>(); // es necesario tener un hash?


    // getPrototype del hash por nombre, pero CLONADO
    public static IPrototypeMemento getPrototype(String prototypeName){
        return prototypes.get(prototypeName).deepClone();
    }

    // add prototype al hash
    public static void addPrototype(String prototypeName,IPrototypeMemento iprototype){
        prototypes.put(prototypeName, iprototype);
    }

}
