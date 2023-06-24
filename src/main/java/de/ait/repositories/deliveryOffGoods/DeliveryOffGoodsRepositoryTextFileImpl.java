package de.ait.repositories.DeliveryOffGoods;

import de.ait.models.DeliveryOffGoods;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryOffGoodsRepositoryTextFileImpl implements DeliveryOffGoodsRepository {

    private final String fileName;
    public DeliveryOffGoodsRepositoryTextFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<DeliveryOffGoods> findAll() {

        List<DeliveryOffGoods> delivery = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line = bufferedReader.readLine();

            while (line != null) {
                String[] parsed = line.split("\\|");
                String id  = parsed[0];
                String address = parsed[1];
                String productId  = parsed[2];
                String userId  = parsed[3];
                String deliveryDate  = parsed[4];

                delivery.add(new DeliveryOffGoods(id, address, productId, userId, deliveryDate));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Произошла ошибка");
        }
        return delivery;
    }

    @Override
    public DeliveryOffGoods findByTitle(String title) {
        return null;
    }

    @Override
    public void save(DeliveryOffGoods delivery) {
        try (BufferedWriter bufferedWriter = new BufferedWriter
                (new FileWriter(fileName, true));) {

            String deliveryObject = "";

            deliveryObject = delivery.getId() + "|" + delivery.getAddress() + "|" + delivery.getProductId()
                    + "|" + delivery.getUserId() + "|" + delivery.getDeliveryDate();

            bufferedWriter.write(deliveryObject);
            bufferedWriter.newLine();

        } catch (IOException e) {
            throw new IllegalArgumentException("Проблемы с файлом");
        }
    }

}
