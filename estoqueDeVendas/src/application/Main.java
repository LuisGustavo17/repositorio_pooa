package application;

import java.util.ArrayList;
import java.util.List;

import estoqueDeVendas.Estoque;
import estoqueDeVendas.ItemVenda;
import estoqueDeVendas.Produto;
import estoqueDeVendas.Venda;

public class Main {
    public static void main(String[] args) {

        // Criando produtos
        Produto p1 = new Produto("Caneta", 2.50, 0, 30);
        Produto p2 = new Produto("Caderno", 15.00, 50, 20);
        Produto p3 = new Produto("Lápis", 1.00, 200, 50);
        

        // Criando estoque e adicionando produtos
        Estoque estoque = new Estoque();
        estoque.adicionarProduto(p1);
        estoque.adicionarProduto(p2);
        estoque.adicionarProduto(p3);

        // Criando vendas
        Venda venda1 = new Venda();
        venda1.adicionarItem(new ItemVenda(p1,100)); // 40 * 2.5 = 100
        venda1.adicionarItem(new ItemVenda(p2, 10)); // 10 * 15 = 150

        Venda venda2 = new Venda();
        venda2.adicionarItem(new ItemVenda(p3, 100)); // 100 * 1 = 100
        venda2.adicionarItem(new ItemVenda(p2, 20));  // 20 * 15 = 300

        List<Venda> vendas = new ArrayList<Venda>();
        vendas.add(venda1);
        vendas.add(venda2);

        // Calculando curva ABC
        estoque.calcularCurvaABC(vendas);

        System.out.println("=== Curva ABC dos Produtos ===");
        for (Produto p : estoque.listarProdutos()) {
            System.out.println(p.getNome() + " - Classificação: " + p.getClassificacaoABC());
        }

        // Verificando reabastecimento
        System.out.println("\n=== Produtos que precisam ser reabastecidos ===");
        List<Produto> reabastecer = estoque.getProdutosParaReabastecer();
        if (reabastecer.isEmpty()) {
            System.out.println("Nenhum produto precisa de reabastecimento.");
        } else {
            for (Produto p : reabastecer) {
                System.out.println(p.getNome() + " (quantidade atual: " + p.getQuantidade() + ")");
            }
        }

        // Exibindo totais de vendas
        System.out.println("\n=== Totais de Vendas ===");
        int count = 1;
        for (Venda v : vendas) {
            System.out.println("Venda " + count + ": R$ " + v.getTotal());
            count++;
        }
    }
}