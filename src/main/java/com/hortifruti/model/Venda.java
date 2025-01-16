package com.hortifruti.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma venda no sistema
 * 
 * TODO:
 * 1. Criar enum FormaPagamento:
 *    - DINHEIRO
 *    - CARTAO
 *    - PIX
 * 
 * 2. Adicionar atributos:
 *    - data (LocalDateTime)
 *    - itens (List<ItemVenda>)
 *    - valorTotal (BigDecimal)
 *    - formaPagamento (FormaPagamento)
 * 
 * 3. Implementar métodos:
 *    - adicionarItem(Produto, quantidade)
 *    - removerItem(ItemVenda)
 *    - calcularTotal()
 *    - finalizarVenda()
 * 
 * 4. Validações:
 *    - Lista de itens não vazia
 *    - Quantidades positivas
 *    - Estoque suficiente
 *    - Produtos válidos
 */
public class Venda extends BaseModel {
    // Implementar aqui
}
