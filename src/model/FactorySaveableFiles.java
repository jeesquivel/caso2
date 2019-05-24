package model;


public class FactorySaveableFiles extends AbstracFactory{

    @Override
    public IGuardable createSaveableFile(String type) {
        switch (type) {
            case "XML":
                break;

                default:


        }
        return null;
    }
}
