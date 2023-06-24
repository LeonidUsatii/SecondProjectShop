package de.ait.repositories.CashWarrant;

import de.ait.models.CashWarrant;
import de.ait.repositories.CrudRepository;

import java.util.List;

public interface CashWarrantRepository extends CrudRepository<CashWarrant> {
    @Override
    List<CashWarrant> findAll();

    @Override
    void save(CashWarrant model);

    @Override
    CashWarrant findByTitle(String title);
}
