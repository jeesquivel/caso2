package model;


public class FactorySaveableFiles extends AbstracFactory{

    @Override
    public IArchivo createSaveableFile(String type) {
        switch (type) {
            case "XML":
                break;

                default:


        }
        return null;
    }
}
