package estoqueDeVendas;

public class Produto {
    private String nome;
    private double preco;
    private int quantidade;
    private int pontoDeReabastecimento;
    private char classificacaoABC;

    public Produto(String nome, double preco, int quantidade, int pontoDeReabastecimento) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.pontoDeReabastecimento = pontoDeReabastecimento;
    }

    public String getNome() {
        return nome;
    }
    
    public int getQuantidade() {
    	return quantidade;
    }

    public void adicionarEstoque(int quantidade) {
        this.quantidade += quantidade;
    }

    public void removerEstoque(int quantidade) {
        if (quantidade <= this.quantidade) {
            this.quantidade -= quantidade;
        } else {
            throw new IllegalArgumentException("Estoque insuficiente");
        }
    }

    public int getPontoDeReabastecimento() {
        return pontoDeReabastecimento;
    }

    public boolean precisaReabastecer() {
        return this.quantidade <= pontoDeReabastecimento;
    }

    public void setClassificacaoABC(char classificacao) {
        this.classificacaoABC = classificacao;
    }

    public char getClassificacaoABC() {
        return classificacaoABC;
    }

	public double getPreco() {
		return preco;
	}

	

	
}
