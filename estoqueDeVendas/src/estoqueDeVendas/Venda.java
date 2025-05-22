package estoqueDeVendas;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private List<ItemVenda> itens;

    public Venda() {
        this.itens = new ArrayList<ItemVenda>();
    }

    public void adicionarItem(ItemVenda item) {
        itens.add(item);
    }

    public double getTotal() {
        double total = 0.0;
        for (int i = 0; i < itens.size(); i++) {
            total += itens.get(i).getSubtotal();
        }
        return total;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }
}
