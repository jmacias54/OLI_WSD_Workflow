/**
 * 
 */
package mx.com.amx.unotv.oli.wsd.workflow.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.com.amx.unotv.oli.wsd.workflow.dao.exception.HNotaDAOException;
import mx.com.amx.unotv.oli.wsd.workflow.model.HNota;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class HNotaDAO  {
	private static Logger logger = Logger.getLogger(HNotaDAO.class);
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat dateFormat_2 = new SimpleDateFormat("yyyy-MM-dd");

	
	public List<HNota> findAll() throws HNotaDAOException {
		List<HNota> lista = null;

		StringBuilder query = new StringBuilder();
		query.append(" SELECT * FROM OLI_MX_H_NOTA  ");

		try {

			lista = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<HNota>(HNota.class));

		} catch (Exception e) {

			logger.error(" Error findAll  [ HNotaDAO ] ", e);

			throw new HNotaDAOException(e.getMessage());

		}

		return lista;
	}
	
	public void delete(String friendlyURL) throws HNotaDAOException {
		logger.info("--- Delete  [HNotaDAO] ---- ");

		StringBuilder query = new StringBuilder();
		query.append(" DELETE FROM OLI_MX_H_NOTA WHERE FC_FRIENDLY_URL ='" + friendlyURL + "'");
		try {

			jdbcTemplate.execute(query.toString());

		} catch (Exception e) {

			logger.error(" Error Delete  [ HNotaDAO ] ", e);

			throw new HNotaDAOException(e.getMessage());

		}
		
	}

	
	public HNota findByFriendlyURL(String friendlyURL) throws HNotaDAOException {
		logger.info("--- findById  [HNotaDAO] ---- ");

		List<HNota> lista = null;

		StringBuilder query = new StringBuilder();
		query.append(" SELECT * FROM OLI_MX_H_NOTA WHERE FC_FRIENDLY_URL ='" + friendlyURL + "'");

		try {

			lista = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<HNota>(HNota.class));

		} catch (Exception e) {

			logger.error(" Error findById [ HNotaDAO ] ", e);

			throw new HNotaDAOException(e.getMessage());

		}

		if (lista.isEmpty() || lista == null) {
			return null;
		}

		return lista.get(0);
	}


	public int update(HNota nota) throws HNotaDAOException {
	logger.info("--- update  [HNotaDAO] ---- ");
		
		int rows = 0;

		try {
			rows = jdbcTemplate.update(" UPDATE OLI_MX_H_NOTA SET  "+
					" FC_ID_CATEGORIA = ? ,  "+
					" FC_ID_TIPO_NOTA = ? ,  "+
					" FC_ID_CLASS_VIDEO = ? ,  "+
					" FC_TITULO = ? ,  "+
					" FC_DESCRIPCION = ? ,  "+
					" FC_AUTOR = ? ,  "+
					" FC_URL_AUTOR = ? ,  "+
					" FC_IMAGEN = ? ,  "+
					" FC_PIE_IMAGEN = ? ,  "+
					" CL_GALERIA = ? ,  "+
					" CL_RTF_CONTENIDO = ? ,  "+
					" FC_ID_YOUTUBE = ? ,  "+
					" FC_ID_CONTENT_OOYALA = ? ,  "+
					" FC_ID_PLAYER_OOYALA = ? ,  "+
					" FC_KEYWORDS = ? ,  "+
					" FD_FECHA_MODIFICACION = ? ,  "+
					" FI_BAN_OTROS = ?  "
					+ " WHERE FC_FRIENDLY_URL = ?   "
					,nota.getFcIdCategoria(),nota.getFcIdTipoNota(),nota.getFcIdClassVideo()
					,nota.getFcTitulo(),nota.getFcDescripcion()
					,nota.getFcAutor(),nota.getFcUrlAutor(),nota.getFcImagen(),nota.getFcPieImagen()
					,nota.getClGaleria(),nota.getClRtfContenido(),nota.getFcIdYoutube()
					,nota.getFcIdContentOoyala(),nota.getFcIdPlayerOoyala(),nota.getFcKeywords()
					,dateFormat.format(new Date()),nota.getFiBanOtros(),nota.getFcFriendlyUrl());

		} catch (Exception e) {

			logger.error(" Error update [ HNotaDAO ] ", e);

			throw new HNotaDAOException(e.getMessage());

		}

		return rows;
	}


	public int insert(HNota nota) throws HNotaDAOException {
	logger.info("--- insert  [HNotaDAO] ---- ");
		
		int rows = 0;

		try {
			rows = jdbcTemplate.update(" INSERT INTO OLI_MX_H_NOTA (FC_ID_CONTENIDO , "+
					" FC_ID_CATEGORIA , "+
					" FC_ID_TIPO_NOTA , "+
					" FC_ID_CLASS_VIDEO , "+
					" FC_TITULO , "+
					" FC_DESCRIPCION , "+
					" FC_FRIENDLY_URL , "+
					" FC_AUTOR , "+
					" FC_URL_AUTOR , "+
					" FC_IMAGEN , "+
					" FC_PIE_IMAGEN , "+
					" CL_GALERIA , "+
					" CL_RTF_CONTENIDO , "+
					" FC_ID_YOUTUBE , "+
					" FC_ID_CONTENT_OOYALA , "+
					" FC_ID_PLAYER_OOYALA , "+
					" FC_KEYWORDS , "+
					" FD_FECHA_PUBLICACION , "+
					" FD_FECHA_MODIFICACION , "+
					" FI_BAN_OTROS ) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
					,nota.getFcIdContenido(),nota.getFcIdCategoria(),nota.getFcIdTipoNota(),nota.getFcIdClassVideo()
					,nota.getFcTitulo(),nota.getFcDescripcion(),nota.getFcFriendlyUrl(),nota.getFcAutor(),nota.getFcUrlAutor()
					,nota.getFcImagen(),nota.getFcPieImagen(),nota.getClGaleria(),nota.getClRtfContenido(),nota.getFcIdYoutube()
					,nota.getFcIdContentOoyala(),nota.getFcIdPlayerOoyala(),nota.getFcKeywords(), dateFormat.format(new Date()),
					dateFormat.format(new Date()), nota.getFiBanOtros());

		} catch (Exception e) {

			logger.error(" Error al insertar [ HNotaDAO ] ", e);

			throw new HNotaDAOException(e.getMessage());

		}

		return rows;
	}

}
