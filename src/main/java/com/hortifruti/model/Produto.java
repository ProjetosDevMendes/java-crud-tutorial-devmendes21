package com.hortifruti.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa um produto no sistema (frutas, verduras, legumes)
 * 
 * TODO:
 * 1. Criar enum Categoria:
 *    - FRUTA
 *    - VERDURA
 *    - LEGUME
 * 
 * 2. Adicionar atributos:
 *    - nome (String)
 *    - categoria (Categoria)
 *    - precoKg (BigDecimal)
 *    - estoque (BigDecimal)
 *    - validade (LocalDate)
 *    - fornecedorId (Long)
 * 
 * 3. Implementar validações:
 *    - Nome não pode ser vazio
 *    - Preço deve ser maior que zero
 *    - Estoque não pode ser negativo
 *    - Validade deve ser data futura
 * 
 * 4. Criar métodos:
 *    - atualizarEstoque(quantidade)
 *    - calcularValorTotal(quantidade)
 *    - verificarValidade()
 */
public class Produto extends BaseModel {
    // Implementar aqui
}
