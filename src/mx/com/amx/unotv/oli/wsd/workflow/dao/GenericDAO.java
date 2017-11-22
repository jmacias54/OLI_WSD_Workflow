/**
 * 
 */
package mx.com.amx.unotv.oli.wsd.workflow.dao;

import java.util.List;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public interface GenericDAO<T> {

	List<T> findAll();

	int insert(T t);

	void delete(Object id);

	T findById(Object id);

	int update(T t);
}
