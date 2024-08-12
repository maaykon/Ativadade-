package com.lanchenlayer;

import java.util.ArrayList;
import java.util.Scanner;

import com.lanchenlayer.applications.ProdutoApplication;
import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.facade.ProdutoFacade;
import com.lanchenlayer.repositories.ProdutoRepository;
import com.lanchenlayer.services.ProdutoService;

public class Console {
    public static void main(String[] args) {
        ProdutoRepository produtoRepository = new ProdutoRepository();
        ProdutoService produtoService = new ProdutoService(produtoRepository);
        ProdutoApplication produtoApplication = new ProdutoApplication(produtoRepository, produtoService);
        ProdutoFacade produtoFacade = new ProdutoFacade(produtoApplication);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            menu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    
                    System.out.println("Digite a descrição do produto:");
                    String descricao = scanner.nextLine();
                    System.out.println("Digite o preço do produto:");
                    float preco = scanner.nextFloat();
                    scanner.nextLine(); 
                    System.out.println("Digite o caminho da imagem do produto:");
                    String imagem = scanner.nextLine();
                    int id = produtoFacade.buscarTodos().size() + 1;
                    Produto novoProduto = new Produto(id, descricao, preco, imagem);
                    produtoFacade.adicionar(novoProduto);
                    System.out.println("Produto adicionado com sucesso!");
                    break;

                case 2:
                  
                    System.out.println("Digite o ID do produto para vender:");
                    int idVender = scanner.nextInt();
                    scanner.nextLine();
                    boolean vendido = produtoFacade.vender(idVender);
                    if (vendido) {
                        System.out.println("Produto vendido com sucesso!");
                    } else {
                        System.out.println("Produto não encontrado!");
                    }
                    break;

                case 3:
                
                    ArrayList<Produto> produtos = produtoFacade.buscarTodos();
                    System.out.println("Produtos disponíveis:");
                    produtos.forEach(p -> {
                        System.out.printf("ID: %d, Descrição: %s, Preço: %.2f, Imagem: %s%n",
                                p.getId(), p.getDescricao(), p.getPreco(), p.getImagem());
                    });
                    break;

                case 0:
                    
                    running = false;
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }

    private static void menu() {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Adicionar produto");
        System.out.println("2. Vender produto");
        System.out.println("3. Listar produtos");
        System.out.println("0. Sair");
        
    }
}
