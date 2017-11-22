/**
 * 
 */
package mx.com.amx.unotv.oli.wsd.workflow.response;

import java.util.List;

/**
 * @author Jesus A. Macias Benitez
 * @param <T>
 *
 */
public class ListResponse<T> {
	
	List<T> lista;

	public List<T> getLista() {
		return lista;
	}

	public void setLista(List<T> lista) {
		this.lista = lista;
	}
	
	
	

}
