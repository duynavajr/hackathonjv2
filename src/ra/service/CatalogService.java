package ra.service;

import ra.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements IService<Catalog, Integer> {
    private List<Catalog> catalogList;

    public CatalogService() {
        catalogList = new ArrayList<>();
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
    public Catalog findById(Integer catalogId) {
        for (Catalog catalog : catalogList) {
            if (catalog.getCatalogId() == catalogId) {
                return catalog;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer catalogId) {
        Catalog catalogToRemove = null;
        for (Catalog catalog : catalogList) {
            if (catalog.getCatalogId() == catalogId) {
                catalogToRemove = catalog;
                break;
            }
        }
        if (catalogToRemove != null) {
            catalogList.remove(catalogToRemove);
        }
    }
}

