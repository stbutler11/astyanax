package com.netflix.astyanax.entitystore;

import java.util.Collection;

import javax.persistence.PersistenceException;

import com.google.common.base.Function;

/**
 * @param <T> entity type 
 * @param <K> rowKey type
 */
public interface EntityManager<T, K> {

	/**
	 * write entity to cassandra with mapped rowId and columns
	 * @param entity entity object
	 */
	public void put(T entity) throws PersistenceException;
	
	/**
	 * fetch whole row and construct entity object mapping from columns
	 * @param id row key
	 * @return entity object
	 */
	public T get(K id) throws PersistenceException;
	
	/**
	 * delete the whole row
	 * @param id row key
	 */
	public void delete(K id) throws PersistenceException;
	
	/**
	 * Return all entities.  
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public Collection<T> getAll() throws PersistenceException;
	
	/**
	 * Get a set of entities
	 * @param ids
	 * @return
	 * @throws PersistenceException
	 */
	public Collection<T> get(Collection<K> ids) throws PersistenceException;
	
	/**
	 * Delete a set of entities
	 * @param ids
	 * @throws PersistenceException
	 */
	public void delete(Collection<K> ids) throws PersistenceException;
	
	/**
	 * Store a set of entities.
	 * @param entites
	 * @throws PersistenceException
	 */
	public void put(Collection<T> entities) throws PersistenceException;
	
	/**
	 * Visit all entities.
	 * 
	 * @param callback Callback when an entity is read.  Note that the callback 
	 *                 may be called from multiple threads.
	 * @throws PersistenceException
	 */
	public void visitAll(Function<T, Boolean> callback) throws PersistenceException;
}
