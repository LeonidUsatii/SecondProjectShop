package de.ait.services.DeliveryOffGoods;

import de.ait.models.*;
import de.ait.repositories.DeliveryOffGoods.DeliveryOffGoodsRepository;


import java.util.List;

public class DeliveryOffGoodsServiceImpl implements DeliveryOffGoodsService {

    private DeliveryOffGoodsRepository deliveryOffGoodsRepository;


    public DeliveryOffGoodsServiceImpl(DeliveryOffGoodsRepository deliveryOffGoodsRepository) {
        this.deliveryOffGoodsRepository = deliveryOffGoodsRepository;
    }

    @Override
    public List<DeliveryOffGoods> getOrder() {

        return deliveryOffGoodsRepository.findAll();
    }

    @Override
    public String toString() {
        return "DeliveryOffGoodsServiceImpl{" +
                "deliveryOffGoodsRepository=" + deliveryOffGoodsRepository +
                '}';
    }
}
