/**
 * 
 */
package mx.com.amx.unotv.oli.wsd.workflow.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import mx.com.amx.unotv.oli.wsd.workflow.controller.exception.ControllerException;
import mx.com.amx.unotv.oli.wsd.workflow.dao.HNotaDAO;
import mx.com.amx.unotv.oli.wsd.workflow.model.HNota;
import mx.com.amx.unotv.oli.wsd.workflow.response.ListResponse;


/**
 * @author Jesus A. Macias Benitez
 *
 */

@Controller
@RequestMapping("nNota")
public class HNotaController {
	private static Logger logger = Logger.getLogger(HNotaController.class);

	@Autowired
	HNotaDAO hNotaDAO;
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public ListResponse<HNota> findAll() throws ControllerException {
		logger.info("--- HNotaController-----");
		logger.info("--- findAll -----");
		ListResponse<HNota> response = null;
		List<HNota> lista = null;
		
		try {

			lista = hNotaDAO.findAll();
			response = new ListResponse<HNota>();
			response.setLista(lista);

		} catch (Exception e) {
			logger.error(" -- Error  findAll [ HNotaController ]:", e);
			throw new ControllerException(e.getMessage());
		}
		
		
		return response;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public int insert(@RequestBody HNota nota) throws ControllerException {
		logger.info("--- HNotaController-----");
		logger.info("--- insert -----");
		int res=0;
		try {

			res = hNotaDAO.insert(nota);

		} catch (Exception e) {
			logger.error(" -- Error  insert [ HNotaController ]:", e);
			throw new ControllerException(e.getMessage());
		}

		return res;
	}

	@RequestMapping(value = "/delete/{idContenido}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public void delete(@PathVariable String idContenido) throws ControllerException {
		logger.info("--- HNotaController-----");
		logger.info("--- delete -----");
		try {

			  hNotaDAO.delete(idContenido);

		} catch (Exception e) {
			logger.error(" -- Error  delete [ HNotaController ]:", e);
			throw new ControllerException(e.getMessage());
		}
		
	}

	@RequestMapping(value = "/findById/{idContenido}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public HNota findById(@PathVariable String idContenido) throws ControllerException {
		logger.info("--- HNotaController-----");
		logger.info("--- findById -----");
		HNota nota = null;
		try {

			nota = hNotaDAO.findById(idContenido);

		} catch (Exception e) {
			logger.error(" -- Error  findById [ HNotaController ]:", e);
			throw new ControllerException(e.getMessage());
		}
		
		return nota;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public int update(@RequestBody HNota nota) throws ControllerException {
		logger.info("--- HNotaController-----");
		logger.info("--- update -----");
		int res=0;
		try {

			res = hNotaDAO.update(nota);

		} catch (Exception e) {
			logger.error(" -- Error  update [ HNotaController ]:", e);
			throw new ControllerException(e.getMessage());
		}

		return res;
	}
	
}
