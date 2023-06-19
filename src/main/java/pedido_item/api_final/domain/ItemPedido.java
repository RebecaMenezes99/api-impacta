package pedido_item.api_final.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ItemPedido {
    private static final AtomicInteger idGenerator = new AtomicInteger();
    private static final Map<String, Integer> indiceMap = new HashMap<>();

    private int id;
    private String numero;
    private int indice;
    private String sku;
    private String produto;
    private double preco;
    private int quantidade;

    public ItemPedido(String numero, int indice, String sku, String produto, double preco, int quantidade) {
        this.id = idGenerator.incrementAndGet();
        this.numero = numero;
        this.indice =  geraIndice(numero);
        this.sku = sku;
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public ItemPedido(int id, String numero, int indice, String sku, String produto, double preco, int quantidade) {
        this.id = id;
        this.numero = numero;
        this.indice =  geraIndice(numero);
        this.sku = sku;
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public ItemPedido() {
        this.id = idGenerator.incrementAndGet();
       // AtomicInteger indiceCounter = indiceGenerator.computeIfAbsent(numero, k -> new AtomicInteger());
        this.indice = geraIndice(numero);
        this.numero = "";
        this.sku = "";
        this.produto = "";
        this.preco = 0.0;
        this.quantidade = 0;
    }

    private int geraIndice(String numero) {
        int nextIndice = indiceMap.getOrDefault(numero, 0) + 1;
        indiceMap.put(numero, nextIndice);
        return nextIndice;
    }

    public static AtomicInteger getIdgenerator() {
        return idGenerator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    
}
