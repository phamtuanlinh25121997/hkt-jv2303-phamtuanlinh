package ra.service;

import ra.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements IService<Catalog,Integer>{
    private List<Catalog> catalogList;
    public CatalogService(){
        this.catalogList = new ArrayList<>();
    }

    @Override
    public List<Catalog> getAll() {
        return catalogList;
    }

    @Override
    public void save(Catalog catalog) {
        catalogList.add(catalog);
    }

    @Override
    public void findById(Integer catalogId) {
        for (Catalog catalog ; catalogList){
            if (catalog.getCatalogId()==catalogId){
                System.out.println(catalog);
                return;
            }
        }
        System.out.println("Catalog not found.");
    }

    @Override
    public void delete(Integer catalogId) {
        for (Catalog catalog : catalogList) {
            if (catalog.getCatalogId()== catalogId){
                catalogList.remove(catalog);
                System.out.println("Catalog delete");
                return;
            }
        }
        System.out.println("Catalog not found");
    }
}
