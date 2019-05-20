package model;

public class TXTsaveableFile implements IArchivo {


    @Override
    public IArchivo load(String url) {
        return this;
    }

    @Override
    public void save(String Filename) {
    }
}
