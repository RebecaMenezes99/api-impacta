package pedido_item.api_final.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class Pedido {
    private static final AtomicInteger idGenerator = new AtomicInteger();
    
    private int id;
    private String numero;
    private String nomeCliente;

    public Pedido(String numero, String nomeCliente) {
        this.numero = numero;
        this.nomeCliente = nomeCliente;
    }

    public Pedido(int id, String numero, String nomeCliente) {
        this.id = idGenerator.incrementAndGet();
        this.numero = numero;
        this.nomeCliente = nomeCliente;
    }

    public Pedido() {
        this.id = idGenerator.incrementAndGet();
        this.numero = "";
        this.nomeCliente = "";
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

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    
}
