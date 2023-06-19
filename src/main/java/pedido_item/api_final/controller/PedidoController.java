package pedido_item.api_final.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pedido_item.api_final.domain.ItemPedido;
import pedido_item.api_final.domain.Pedido;


@RestController
@RequestMapping("/api/v1/pedido")
public class PedidoController {
    private final Map<String, Pedido> pedidos = new HashMap<>();
    private final Map<String, List<ItemPedido>> itensPedido = new HashMap<>();

    @PostMapping
    public ResponseEntity<String> criarPedido(@RequestBody Pedido pedido) {
        if (pedidos.containsKey(pedido.getNumero())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um pedido com o número fornecido.");
        }
        pedidos.put(pedido.getNumero(), pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pedido criado com sucesso.");
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Pedido> obterPedidoPorNumero(@PathVariable String numero) {
        if (pedidos.containsKey(numero)) {
            return ResponseEntity.ok(pedidos.get(numero));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> obterTodosPedidos() {
        return ResponseEntity.ok(new ArrayList<>(pedidos.values()));
    }

    @PostMapping("/{numero}/item")
    public ResponseEntity<String> adicionarItemPedido(@PathVariable String numero, @RequestBody ItemPedido itemPedido) {
        if (!pedidos.containsKey(numero)) {
            return ResponseEntity.notFound().build();
        }

        List<ItemPedido> itens = itensPedido.computeIfAbsent(numero, k -> new ArrayList<>());
    
    int indice = itens.size() + 1; // Obter o próximo índice com base na quantidade atual de itens
    itemPedido.setIndice(indice); // Definir o índice no itemPedido
    
    itens.add(itemPedido);
    return ResponseEntity.status(HttpStatus.CREATED).body("Item do pedido adicionado com sucesso.");
        //List<ItemPedido> itens = itensPedido.computeIfAbsent(numero, k -> new ArrayList<>());
        //itens.add(itemPedido);
       // return ResponseEntity.status(HttpStatus.CREATED).body("Item do pedido adicionado com sucesso.");
    }

    @GetMapping("/{numero}/item/{indice}")
    public ResponseEntity<ItemPedido> obterItemPedidoPorIndice(@PathVariable String numero, @PathVariable int indice) {
        if (itensPedido.containsKey(numero) && indice >= 1 && indice <= itensPedido.get(numero).size()) {
            return ResponseEntity.ok(itensPedido.get(numero).get(indice - 1));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{numero}/item")
    public ResponseEntity<List<ItemPedido>> obterTodosItensPedido(@PathVariable String numero) {
        if (itensPedido.containsKey(numero)) {
            return ResponseEntity.ok(itensPedido.get(numero));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/item")
    public ResponseEntity<List<ItemPedido>> obterItensPorProduto(@RequestParam("produto") String produto) {
        List<ItemPedido> itens = new ArrayList<>();
        for (List<ItemPedido> itemPedidos : itensPedido.values()) {
            for (ItemPedido itemPedido : itemPedidos) {
                if (itemPedido.getProduto().equals(produto)) {
                    itens.add(itemPedido);
                }
            }
        }
        return ResponseEntity.ok(itens);
    }


    }