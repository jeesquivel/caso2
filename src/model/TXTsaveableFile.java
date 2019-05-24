package model;

public class TXTsaveableFile implements IGuardable {


    @Override
    public IGuardable load(String url) {
        return this;
    }

    @Override
    public void save(String Filename) {
    }
}
