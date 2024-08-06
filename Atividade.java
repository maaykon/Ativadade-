import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Atividade{
    public static void main(String[] args) {

        GerenciarPedidos gerenciador = new GerenciarPedidos();
        gerenciador.imagensLanche();
        gerenciador.comanda();
        gerenciador.fecharPedido();
    }
}

class GerenciarPedidos {

    private List<Lanche> lanches;
    private List<Pedido> pedidos;
    private MenuView menuView;

    public GerenciarPedidos() {

        lanches = new ArrayList<>();
        pedidos = new ArrayList<>();
        menuView = new MenuView();
    }

    public void imagensLanche() {

        lanches.add(new Lanche("Hotdog", 4.00, "C:\\Users\\aluno\\Documents\\imagens JAVA\\download.jpg"));
        lanches.add(new Lanche("X Salada", 4.50, "C:\\Users\\aluno\\Documents\\imagens JAVA\\download (1).jpg"));
        lanches.add(new Lanche("X Bacon", 5.00, "C:\\Users\\aluno\\Documents\\imagens JAVA\\download (2).jpg"));
        lanches.add(new Lanche("Torrada", 2.00, "C:\\Users\\aluno\\Documents\\imagens JAVA\\images.jpg"));
        lanches.add(new Lanche("Refrigerante", 1.50, "C:\\Users\\aluno\\Documents\\imagens JAVA\\images (1).jpg"));
    }

    public void comanda() {
        while (true) {

            menuView.exibirMenu(lanches);
            int codigo = menuView.codLanche();

            if (codigo < 1 || codigo > lanches.size()) {
                System.out.println("Código inválido!");
                continue;
            }
            int quantidade = menuView.Quantidade();
            Lanche lancheEscolhido = lanches.get(codigo - 1);
            pedidos.add(new Pedido(lancheEscolhido, quantidade));

            if (!menuView.Adicionar()) {
                break;
            }
        }
    }

    public void fecharPedido() {

        double total = 0;
        System.out.println("Resumo do Pedido:");

        for (Pedido pedido : pedidos) {

            double valor = pedido.getLanche().getPreco() * pedido.getQuantidade();
            System.out.printf("%d x %s = %.2f\n", pedido.getQuantidade(), pedido.getLanche().getNome(), valor);
            total += valor;
        }
        System.out.printf("Total: %.2f\n", total);
    }
}

class Lanche {

    private String nome;
    private double preco;
    private String imagem;

    public Lanche(String nome, double preco, String imagem) {

        this.nome = nome;
        this.preco = preco;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getImagem() {
        return imagem;
    }
}

class Pedido {

    private Lanche lanche;
    private int quantidade;

    public Pedido(Lanche lanche, int quantidade) {

        this.lanche = lanche;
        this.quantidade = quantidade;
    }

    public Lanche getLanche() {
        return lanche;
    }

    public int getQuantidade() {
        return quantidade;
    }
}

class MenuView {
    private Scanner scanner;

    public MenuView() {
        scanner = new Scanner(System.in);
    }

    public void exibirMenu(List<Lanche> lanches) {

        System.out.println("Qual lanche deseja?");

        for (int i = 0; i < lanches.size(); i++) {

            Lanche lanche = lanches.get(i);
            System.out.printf("%d - %s ------- %.2f\n", i + 1, lanche.getNome(), lanche.getPreco());
            System.out.println("Imagem: " + lanche.getImagem());
        }
    }

    public int codLanche() {

        System.out.print("Código do lanche: ");

        while (!scanner.hasNextInt()) {

            System.out.println("Entrada inválida. Digite um número inteiro.");
            scanner.next(); // Limpa a entrada inválida
            System.out.print("Código do lanche: ");
        }
        return scanner.nextInt();
    }

    public int Quantidade() {

        System.out.print("Quantidade? ");

        while (!scanner.hasNextInt()) {
            
            System.out.println("Entrada inválida. Digite um número inteiro.");
            scanner.next(); // Limpa a entrada inválida
            System.out.print("Quantidade? ");
        }
        return scanner.nextInt();
    }

    public boolean Adicionar() {
        
        System.out.print("Deseja adicionar mais algum item? (sim/nao): ");
        String resposta = scanner.next();
        return resposta.equalsIgnoreCase("sim");
    }
}