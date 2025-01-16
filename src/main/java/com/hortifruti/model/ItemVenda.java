package com.hortifruti.model;

import java.math.BigDecimal;

/**
 * Representa um item de venda no sistema
 * 
 * TODO:
 * 1. Adicionar atributos:
 *    - produto (Produto)
 *    - quantidade (BigDecimal)
 *    - precoUnitario (BigDecimal)
 *    - subtotal (BigDecimal)
 * 
 * 2. Implementar métodos:
 *    - calcularSubtotal()
 *    - atualizarQuantidade(BigDecimal)
 *    - validarQuantidade()
 * 
 * 3. Adicionar validações:
 *    - Quantidade maior que zero
 *    - Produto não nulo
 *    - Preço válido
 * 
 * 4. Regras de negócio:
 *    - Atualizar subtotal ao mudar quantidade
 *    - Verificar disponibilidade no estoque
 *    - Usar preço do produto como base
 */
public class ItemVenda extends BaseModel {
    // Implementar aqui
}
