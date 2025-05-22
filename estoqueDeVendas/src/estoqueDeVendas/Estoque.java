package estoqueDeVendas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estoque {
    private List<Produto> produtos;

    public Estoque() {
        this.produtos = new ArrayList<Produto>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    public List<Produto> listarProdutos() {
        List<Produto> copia = new ArrayList<Produto>();
        for (int i = 0; i < produtos.size(); i++) {
            copia.add(produtos.get(i));
        }
        return copia;
    }

    public List<Produto> getProdutosParaReabastecer() {
        List<Produto> reabastecer = new ArrayList<Produto>();
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            if (p.precisaReabastecer()) {
                reabastecer.add(p);
            }
        }
        return reabastecer;
    }

    public void calcularCurvaABC(List<Venda> vendas) {
        Map<Produto, Double> faturamentoPorProduto = new HashMap<Produto, Double>();

        for (int i = 0; i < vendas.size(); i++) {
            Venda venda = vendas.get(i);
            List<ItemVenda> itens = venda.getItens();
            for (int j = 0; j < itens.size(); j++) {
                ItemVenda item = itens.get(j);
                Produto produto = item.getProduto();
                double subtotal = item.getSubtotal();

                if (faturamentoPorProduto.containsKey(produto)) {
                    double atual = faturamentoPorProduto.get(produto);
                    faturamentoPorProduto.put(produto, atual + subtotal);
                } else {
                    faturamentoPorProduto.put(produto, subtotal);
                }
            }
        }

        List<Map.Entry<Produto, Double>> listaOrdenada = new ArrayList<Map.Entry<Produto, Double>>(faturamentoPorProduto.entrySet());

        for (int i = 0; i < listaOrdenada.size() - 1; i++) {
            for (int j = i + 1; j < listaOrdenada.size(); j++) {
                if (listaOrdenada.get(i).getValue() < listaOrdenada.get(j).getValue()) {
                    Map.Entry<Produto, Double> temp = listaOrdenada.get(i);
                    listaOrdenada.set(i, listaOrdenada.get(j));
                    listaOrdenada.set(j, temp);
                }
            }
        }

        double total = 0.0;
        for (int i = 0; i < listaOrdenada.size(); i++) {
            total += listaOrdenada.get(i).getValue();
        }

        double acumulado = 0.0;
        for (int i = 0; i < listaOrdenada.size(); i++) {
            Map.Entry<Produto, Double> entry = listaOrdenada.get(i);
            acumulado += entry.getValue();
            double percentual = acumulado / total;

            if (percentual <= 0.8) {
                entry.getKey().setClassificacaoABC('A');
            } else if (percentual <= 0.95) {
                entry.getKey().setClassificacaoABC('B');
            } else {
                entry.getKey().setClassificacaoABC('C');
            }
        }
    }
}

