package de.ait.repositories.CashWarrant;

import de.ait.models.CashWarrant;
import de.ait.models.Category;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CashWarrantRepositoryTextFileImpl implements CashWarrantRepository {

    private String fileName;
    public CashWarrantRepositoryTextFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<CashWarrant> findAll() {

        List<CashWarrant> cashWarrants = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line = bufferedReader.readLine();

            while (line != null) {
                String[] parsed = line.split("\\|");
                String id  = parsed[0];
                LocalDateTime dateTime = LocalDateTime.parse(parsed[1]);
                String userId  = parsed[2];
                double price  = Double.parseDouble(parsed[3]);

                cashWarrants.add(new CashWarrant(id, dateTime, userId, price));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Произошла ошибка");
        }

        return cashWarrants;
    }

    @Override
    public void save(CashWarrant cashWarrant) {
        try (BufferedWriter bufferedWriter = new BufferedWriter
                (new FileWriter(fileName, true));) {

            String cashWarrantObject = "";

            cashWarrantObject = cashWarrant.getId() + "|" + cashWarrant.getDateTime()
                    + "|" + cashWarrant.getUserId()
                   + "|" + cashWarrant.getSum();

            bufferedWriter.write(cashWarrantObject);
            bufferedWriter.newLine();

        } catch (IOException e) {
            throw new IllegalArgumentException("Проблемы с файлом");
        }
    }

    @Override
    public CashWarrant findByTitle(String title) {
        return null;
    }

}
