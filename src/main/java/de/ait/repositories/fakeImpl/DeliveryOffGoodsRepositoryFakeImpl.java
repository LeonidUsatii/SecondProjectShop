package de.ait.repositories.fakeImpl;

import de.ait.models.DeliveryOffGoods;
import de.ait.repositories.DeliveryOffGoods.DeliveryOffGoodsRepository;


import java.util.List;

public class DeliveryOffGoodsRepositoryFakeImpl implements DeliveryOffGoodsRepository {
    @Override
    public List<DeliveryOffGoods> findAll() {
        return null;
    }

    @Override
    public void save(DeliveryOffGoods model) {

    }

    @Override
    public DeliveryOffGoods findByTitle(String title) {
        return null;
    }
}
