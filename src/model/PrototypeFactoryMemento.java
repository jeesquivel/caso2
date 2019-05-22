package model;

import java.util.HashMap;

public class PrototypeFactoryMemento {
    // hash de prototypes por nombre
    private static HashMap<Integer ,IPrototypeMemento> prototypes = new HashMap<>(); // es necesario tener un hash?


    // getPrototype del hash por nombre, pero CLONADO
    public static IPrototypeMemento getPrototype(int prototypeID){
        return prototypes.get(prototypeID).deepClone();
    }

    // add prototype al hash
    public static void addPrototype(IPrototypeMemento iprototype){
        prototypes.put(1, iprototype);
    }

}
