package com.hortifruti.dao;

import com.hortifruti.model.BaseModel;
import java.util.List;

/**
 * Interface base para todas as operações de DAO no sistema.
 * Define o contrato básico que todos os DAOs devem implementar.
 * 
 * Principais responsabilidades:
 * - Definir operações CRUD padrão
 * - Garantir consistência entre DAOs
 * - Forçar tipagem segura com genéricos
 * 
 * @param <T> Tipo da entidade, deve estender BaseModel
 */
public interface BaseDAO<T extends BaseModel> {
    
    /**
     * Cria uma nova entidade no banco de dados.
     * 
     * Implementação necessária:
     * 1. Validar dados da entidade
     * 2. Preparar query INSERT
     * 3. Executar e obter ID gerado
     * 4. Retornar entidade com ID
     * 
     * @param entity Entidade a ser criada
     * @return Entidade criada com ID preenchido
     */
    T criar(T entity);
    
    /**
     * Busca uma entidade pelo seu ID.
     * 
     * Implementação necessária:
     * 1. Validar ID
     * 2. Preparar query SELECT
     * 3. Mapear resultado para objeto
     * 
     * @param id ID da entidade
     * @return Entidade encontrada ou null
     */
    T buscarPorId(Long id);
    
    /**
     * Lista todas as entidades do tipo T.
     * 
     * Implementação necessária:
     * 1. Preparar query SELECT
     * 2. Mapear resultados para lista
     * 3. Ordenar adequadamente
     * 
     * @return Lista de entidades
     */
    List<T> buscarTodos();
    
    /**
     * Atualiza uma entidade existente.
     * 
     * Implementação necessária:
     * 1. Validar dados e ID
     * 2. Preparar query UPDATE
     * 3. Verificar linhas afetadas
     * 
     * @param entity Entidade com dados atualizados
     * @return Entidade atualizada
     */
    T atualizar(T entity);
    
    /**
     * Remove uma entidade pelo seu ID.
     * 
     * Implementação necessária:
     * 1. Validar ID
     * 2. Verificar restrições
     * 3. Executar DELETE
     * 
     * @param id ID da entidade
     * @return true se removido com sucesso
     */
    boolean deletar(Long id);
}
