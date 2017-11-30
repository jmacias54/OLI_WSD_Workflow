/**
 * 
 */
package mx.com.amx.unotv.oli.wsd.workflow.controller;

import java.util.Collections;
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
import mx.com.amx.unotv.oli.wsd.workflow.response.NNotaList;


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
	public NNotaList findAll() throws ControllerException {
		logger.info("--- NNotaController-----");
		logger.info("--- findAll -----");
		NNotaList response = null;
		List<NNota> lista = null;
		
		try {

			lista = nNotaDAO.findAll();
			response = new NNotaList();
			if(lista != null && !lista.isEmpty()) {
				response.setLista(lista);
			}else {
				response.setLista(Collections.<NNota>emptyList());
			}

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
		logger.debug("--- DTO :"+nota.toString());
		int res=0;
		try {

			res = nNotaDAO.insert(nota);

		} catch (Exception e) {
			logger.error(" -- Error  insert [ NNotaController ]:", e);
			throw new ControllerException(e.getMessage());
		}

		return res;
	}

	@RequestMapping(value = "/delete/{friendlyURL}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public void delete(@PathVariable String friendlyURL) throws ControllerException {
		logger.info("--- NNotaController-----");
		logger.info("--- delete -----");
		try {

			nNotaDAO.delete(friendlyURL);

		} catch (Exception e) {
			logger.error(" -- Error  delete [ NNotaController ]:", e);
			throw new ControllerException(e.getMessage());
		}
		
	}

	@RequestMapping(value = "/{friendlyURL}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public NNota findByFriendlyURL(@PathVariable String friendlyURL) throws ControllerException {
		logger.info("--- NNotaController-----");
		logger.info("--- findByFriendlyURL -----");
		
		NNota nota = null;
		try {

			nota = nNotaDAO.findByFriendlyURL(friendlyURL);

		} catch (Exception e) {
			logger.error(" -- Error  findByFriendlyURL [ NNotaController ]:", e);
			throw new ControllerException(e.getMessage());
		}
		
		return nota;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public int update(@RequestBody NNota nota) throws ControllerException {
		logger.info("--- NNotaController-----");
		logger.info("--- update -----");
		logger.debug("--- DTO :"+nota.toString());
		
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
