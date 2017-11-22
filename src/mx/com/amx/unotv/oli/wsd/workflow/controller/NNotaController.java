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
import mx.com.amx.unotv.oli.wsd.workflow.dao.NNotaDAO;
import mx.com.amx.unotv.oli.wsd.workflow.model.NNota;
import mx.com.amx.unotv.oli.wsd.workflow.response.ListResponse;


/**
 * @author Jesus A. Macias Benitez
 *
 */


@Controller
@RequestMapping("nNota")
public class NNotaController {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(NNotaController.class);
	
	
	@Autowired
	NNotaDAO nNotaDAO;
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public ListResponse<NNota> findAll() throws ControllerException {
		logger.info("--- NNotaController-----");
		logger.info("--- findAll -----");
		ListResponse<NNota> response = null;
		List<NNota> lista = null;
		
		try {

			lista = nNotaDAO.findAll();
			response = new ListResponse<NNota>();
			response.setLista(lista);

		} catch (Exception e) {
			logger.error(" -- Error  findAll [ NNotaController ]:", e);
			throw new ControllerException(e.getMessage());
		}
		
		
		return response;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public int insert(@RequestBody NNota nota) throws ControllerException {
		logger.info("--- NNotaController-----");
		logger.info("--- insert -----");
		int res=0;
		try {

			res = nNotaDAO.insert(nota);

		} catch (Exception e) {
			logger.error(" -- Error  insert [ NNotaController ]:", e);
			throw new ControllerException(e.getMessage());
		}

		return res;
	}

	@RequestMapping(value = "/delete/{idContenido}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public void delete(@PathVariable String idContenido) throws ControllerException {
		logger.info("--- NNotaController-----");
		logger.info("--- delete -----");
		try {

			nNotaDAO.delete(idContenido);

		} catch (Exception e) {
			logger.error(" -- Error  delete [ NNotaController ]:", e);
			throw new ControllerException(e.getMessage());
		}
		
	}

	@RequestMapping(value = "/{idContenido}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public NNota findById(@PathVariable String idContenido) throws ControllerException {
		logger.info("--- NNotaController-----");
		logger.info("--- findById -----");
		
		NNota nota = null;
		try {

			nota = nNotaDAO.findById(idContenido);

		} catch (Exception e) {
			logger.error(" -- Error  findById [ NNotaController ]:", e);
			throw new ControllerException(e.getMessage());
		}
		
		return nota;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public int update(@RequestBody NNota nota) throws ControllerException {
		logger.info("--- NNotaController-----");
		logger.info("--- update -----");
		
		int res=0;
		try {

			res = nNotaDAO.update(nota);

		} catch (Exception e) {
			logger.error(" -- Error  update [ NNotaController ]:", e);
			throw new ControllerException(e.getMessage());
		}

		return res;
	}

	
	@RequestMapping(value = "/nota", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public NNota nota() throws ControllerException {
		
		return new NNota();
	}
}
