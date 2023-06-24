package de.ait.repositories.DeliveryOffGoods;

import de.ait.models.DeliveryOffGoods;
import de.ait.models.Product;
import de.ait.repositories.CrudRepository;

import java.util.List;

public interface DeliveryOffGoodsRepository extends CrudRepository<DeliveryOffGoods> {
    @Override
    List<DeliveryOffGoods> findAll();

    @Override
    void save(DeliveryOffGoods model);

    @Override
    DeliveryOffGoods findByTitle(String title);
}
