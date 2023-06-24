package de.ait.services.CashWarrant;

import de.ait.models.CashWarrant;
import de.ait.repositories.CashWarrant.CashWarrantRepository;


import java.util.List;


public class CashWarrantServiceImpl implements CashWarrantService {

    private final CashWarrantRepository cashWarrantRepository;

    public CashWarrantServiceImpl(CashWarrantRepository cashWarrantRepository) {
        this.cashWarrantRepository = cashWarrantRepository;
    }

    @Override
    public List<CashWarrant> getOrder() {

        return cashWarrantRepository.findAll();
    }

    @Override
    public String toString() {
        return "CashWarrantServiceImpl{" +
                "cashWarrantRepository=" + cashWarrantRepository +
                '}';
    }

    @Override
    public double getSumOfPrice() {

        return cashWarrantRepository.findAll()
                .stream()
                .map(CashWarrant::getSum)
                .reduce(0.0, (Double::sum));

    }
}
