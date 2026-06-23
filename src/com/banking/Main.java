package com.banking;

import java.util.Scanner;

import com.banking.model.CorporateCustomer;
import com.banking.persistence.FileManager;
import com.banking.service.CorporateCustomerService;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FileManager fileManager = new FileManager();
        CorporateCustomerService service =
                new CorporateCustomerService(fileManager.loadCustomers());

        int option;

        do {
            System.out.println("\n=== SISTEMA BANCARIO CORPORATIVO ===");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Atualizar cliente");
            System.out.println("4 - Remover cliente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    System.out.print("CNPJ: ");
                    String cnpj = scanner.nextLine();

                    System.out.print("Razao social: ");
                    String businessName = scanner.nextLine();

                    System.out.print("Nome fantasia: ");
                    String tradeName = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Senha: ");
                    String password = scanner.nextLine();

                    CorporateCustomer customer =
                            new CorporateCustomer(cnpj, businessName, tradeName, email, password);

                    service.create(customer);
                    fileManager.saveCustomers(service.list());

                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 2:
                    if (service.list().isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        for (CorporateCustomer c : service.list()) {
                            System.out.println("-------------------------");
                            System.out.println("CNPJ: " + c.getCnpj());
                            System.out.println("Razao social: " + c.getBusinessName());
                            System.out.println("Nome fantasia: " + c.getTradeName());
                            System.out.println("Email: " + c.getEmail());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Digite o CNPJ do cliente: ");
                    String cnpjUpdate = scanner.nextLine();

                    System.out.print("Nova razao social: ");
                    String newBusinessName = scanner.nextLine();

                    System.out.print("Novo nome fantasia: ");
                    String newTradeName = scanner.nextLine();

                    boolean updated = service.update(cnpjUpdate, newBusinessName, newTradeName);

                    if (updated) {
                        fileManager.saveCustomers(service.list());
                        System.out.println("Cliente atualizado com sucesso!");
                    } else {
                        System.out.println("Cliente nao encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o CNPJ do cliente: ");
                    String cnpjDelete = scanner.nextLine();

                    boolean deleted = service.delete(cnpjDelete);

                    if (deleted) {
                        fileManager.saveCustomers(service.list());
                        System.out.println("Cliente removido com sucesso!");
                    } else {
                        System.out.println("Cliente nao encontrado.");
                    }
                    break;

                case 0:
                    fileManager.saveCustomers(service.list());
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (option != 0);

        scanner.close();
    }
}