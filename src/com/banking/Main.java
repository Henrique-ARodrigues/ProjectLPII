package com.banking;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import com.banking.model.CorporateCustomer;
import com.banking.persistence.FileManager;
import com.banking.service.CorporateCustomerService;
import com.banking.account.BankAccount;
import com.banking.account.CheckingAccount;
import com.banking.card.VirtualCard;
import com.banking.transaction.PixTransaction;
import com.banking.transaction.BoletoTransaction;
import com.banking.transaction.TedTransaction;
import com.banking.transaction.CardTransaction;

public class Main {

    // Lista simples em memória para guardar as contas criadas junto com os clientes
    private static List<BankAccount> contas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FileManager fileManager = new FileManager();
        CorporateCustomerService service =
                new CorporateCustomerService(fileManager.loadCustomers());

        // Cria uma conta destino padrão em memória para receber as transferências simuladas
        CorporateCustomer bancoDestino = new CorporateCustomer("00.000.000/0001-00", "Banco Destino", "DEST", "adm@dest.com", "123");
        CheckingAccount contaDestino = new CheckingAccount(1000.0, bancoDestino, 5000.0, "0001", "9999-9");
        contas.add(contaDestino);

        int option;

        do {
            System.out.println("\n=== SISTEMA BANCARIO CORPORATIVO ===");
            System.out.println("1 - Cadastrar cliente (com abertura de conta)");
            System.out.println("2 - Listar clientes e saldos");
            System.out.println("3 - Atualizar cliente");
            System.out.println("4 - Remover cliente");
            System.out.println("5 - Simular Transação Financeira");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            // Evita o fechamento do programa caso digitem alguma letra no menu
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                option = -1;
            }

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

                    System.out.print("Saldo Inicial da Conta Corrente: R$ ");
                    double saldoInicial = Double.parseDouble(scanner.nextLine());

                    CorporateCustomer customer =
                            new CorporateCustomer(cnpj, businessName, tradeName, email, password);

                    service.create(customer);
                    fileManager.saveCustomers(service.list());

                    // Integração: Abre automaticamente uma conta corrente com R$1000 de cheque especial
                    CheckingAccount novaConta = new CheckingAccount(1000.0, customer, saldoInicial, "0001", "1234-5");
                    contas.add(novaConta);

                    System.out.println("Cliente cadastrado e conta aberta com sucesso!");
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
                            
                            // Busca e exibe o saldo e taxas da conta vinculada a este cliente
                            BankAccount contaDoCliente = buscarContaPorCnpj(c.getCnpj());
                            if (contaDoCliente != null) {
                                System.out.println("Saldo da Conta: R$ " + contaDoCliente.getBalance());
                                System.out.println("Taxas Adm (U3): R$ " + contaDoCliente.calculateFees());
                            }
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

                case 5:
                    System.out.print("Digite o CNPJ do cliente de origem: ");
                    String cnpjOrigem = scanner.nextLine();
                    BankAccount minhaConta = buscarContaPorCnpj(cnpjOrigem);

                    if (minhaConta == null) {
                        System.out.println("Nenhuma conta encontrada para este CNPJ.");
                        break;
                    }

                    System.out.println("Escolha o tipo: 1-Pix | 2-Boleto | 3-TED | 4-Cartão");
                    System.out.print("Opção: ");
                    int tipo = Integer.parseInt(scanner.nextLine());
                    System.out.print("Valor: R$ ");
                    double valor = Double.parseDouble(scanner.nextLine());

                    if (tipo == 1) {
                        System.out.print("Chave Pix: ");
                        String chave = scanner.nextLine();
                        new PixTransaction(minhaConta, valor, LocalDateTime.now(), contaDestino, chave).process();
                    } else if (tipo == 2) {
                        System.out.print("Código de Barras: ");
                        String barras = scanner.nextLine();
                        new BoletoTransaction(minhaConta, valor, LocalDateTime.now(), null, barras).process();
                    } else if (tipo == 3) {
                        System.out.print("Código do Banco: ");
                        String banco = scanner.nextLine();
                        new TedTransaction(minhaConta, valor, LocalDateTime.now(), contaDestino, banco).process();
                    } else if (tipo == 4) {
                        // Injeta um cartão ativo atrelado à empresa para rodar a sua CardTransaction
                        VirtualCard cartao = new VirtualCard("4444-5555-6666-7777", minhaConta.getOwner().getTradeName(), "123", "12/30", "ACTIVE", 5000.0, null, false);
                        new CardTransaction(minhaConta, valor, LocalDateTime.now(), null, cartao).process();
                    } else {
                        System.out.println("Opção inválida.");
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

    // Método local auxiliar básico para achar a conta do cliente
    private static BankAccount buscarContaPorCnpj(String cnpj) {
        for (BankAccount conta : contas) {
            if (conta.getOwner().getCnpj().equals(cnpj)) {
                return conta;
            }
        }
        return null;
    }
}