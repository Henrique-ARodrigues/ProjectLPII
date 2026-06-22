package com.banking.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.banking.model.CorporateCustomer;

public class FileManager {

    private static final String FILE_NAME = "customers.txt";

    public void saveCustomers(List<CorporateCustomer> customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (CorporateCustomer customer : customers) {
                writer.write(
                    customer.getCnpj() + ";" +
                    customer.getBusinessName() + ";" +
                    customer.getTradeName() + ";" +
                    customer.getEmail() + ";" +
                    customer.getPassword()
                );
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public List<CorporateCustomer> loadCustomers() {
        List<CorporateCustomer> customers = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return customers;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");

                if (data.length == 5) {
                    CorporateCustomer customer = new CorporateCustomer(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4]
                    );

                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }

        return customers;
    }
}