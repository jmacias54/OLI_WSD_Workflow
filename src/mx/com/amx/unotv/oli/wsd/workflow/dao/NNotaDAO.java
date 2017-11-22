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
import mx.com.amx.unotv.oli.wsd.workflow.dao.exception.NNotaDAOException;
import mx.com.amx.unotv.oli.wsd.workflow.model.NNota;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class NNotaDAO {
	private static Logger logger = Logger.getLogger(NNotaDAO.class);
	
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat dateFormat_2 = new SimpleDateFormat("yyyy-MM-dd");

	
	public List<NNota> findAll() throws NNotaDAOException {
		List<NNota> lista = null;

		StringBuilder query = new StringBuilder();
		query.append(" SELECT * FROM OLI_MX_N_NOTA  ");

		try {

			lista = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<NNota>(NNota.class));

		} catch (Exception e) {

			logger.error(" Error findAll  [ NNotaDAO ] ", e);

			throw new NNotaDAOException(e.getMessage());

		}

		return lista;
	}

	
	public void delete(String id) throws NNotaDAOException {
		logger.info("--- Delete  [HNotaDAO] ---- ");

		StringBuilder query = new StringBuilder();
		query.append(" DELETE FROM OLI_MX_N_NOTA WHERE FC_ID_CONTENIDO ='" + id + "'");
		try {

			jdbcTemplate.execute(query.toString());

		} catch (Exception e) {

			logger.error(" Error Delete  [ NNotaDAO ] ", e);

			throw new NNotaDAOException(e.getMessage());

		}
		
	}

	
	public NNota findById(String id) throws NNotaDAOException {
		logger.info("--- findById  [NNotaDAO] ---- ");

		List<NNota> lista = null;

		StringBuilder query = new StringBuilder();
		query.append(" SELECT * FROM OLI_MX_N_NOTA WHERE FC_ID_CONTENIDO ='" + id + "'");

		try {

			lista = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<NNota>(NNota.class));

		} catch (Exception e) {

			logger.error(" Error findById [ NNotaDAO ] ", e);

			throw new NNotaDAOException(e.getMessage());

		}

		if (lista.isEmpty() || lista == null) {
			return null;
		}

		return lista.get(0);
	}


	public int update(NNota nota) throws NNotaDAOException {
		int rows = 0;

		try {
			rows = jdbcTemplate.update(" UPDATE oli_mx_n_nota SET  "+
					" FC_ID_CATEGORIA  = ? , "+
					" FC_TIPO_NOTA  = ? , "+
					" FC_ID_CLASS_VIDEO  = ? , "+
					" FC_TITULO  = ? , "+
					" FC_DESCRIPCION  = ? , "+
					" FC_FRIENDLY_URL  = ? , "+
					" FC_AUTOR  = ? , "+
					" FC_URL_AUTOR  = ? , "+
					" FC_IMAGEN  = ? , "+
					" FC_PIE_IMAGEN  = ? , "+
					" CL_GALERIA  = ? , "+
					" CL_RTF_CONTENIDO  = ? , "+
					" FC_ID_YOUTUBE  = ? , "+
					" FC_ID_CONTENT_OOYALA  = ? , "+
					" FC_ID_PLAYER_OOYALA  = ? , "+
					" FC_KEYWORDS  = ? , "+
					" FD_FECHA_MODIFICACION  = ? , "+
					" FI_BAN_OTROS  = ?  WHERE FC_ID_CONTENIDO = ? "
					,nota.getFcIdCategoria(),nota.getFcTipoNota(),nota.getFcIdClassVideo()
					,nota.getFcTitulo(),nota.getFcDescripcion(),nota.getFcFriendlyUrl()
					,nota.getFcAutor(),nota.getFcUrlAutor(),nota.getFcImagen()
					,nota.getFcPieImagen(),nota.getClGaleria(),nota.getClRtfContenido()
					,nota.getFcIdYoutube(),nota.getFcIdContentOoyala(),nota.getFcIdPlayerOoyala()
					,nota.getFcKeywords(),dateFormat.format(new Date()) ,nota.getFiBanOtros(),nota.getFcIdContenido());

		} catch (Exception e) {

			logger.error(" Error update [ NNotaDAO ] ", e);

			throw new NNotaDAOException(e.getMessage());

		}

		return rows;
	}

	public int insert(NNota nota) throws NNotaDAOException {
		int rows = 0;

		try {
			rows = jdbcTemplate.update(" INSERT INTO oli_mx_n_nota ( FC_ID_CONTENIDO, "+
					" FC_ID_CATEGORIA, "+
					" FC_TIPO_NOTA, "+
					" FC_ID_CLASS_VIDEO, "+
					" FC_TITULO, "+
					" FC_DESCRIPCION, "+
					" FC_FRIENDLY_URL, "+
					" FC_AUTOR, "+
					" FC_URL_AUTOR, "+
					" FC_IMAGEN, "+
					" FC_PIE_IMAGEN, "+
					" CL_GALERIA, "+
					" CL_RTF_CONTENIDO, "+
					" FC_ID_YOUTUBE, "+
					" FC_ID_CONTENT_OOYALA, "+
					" FC_ID_PLAYER_OOYALA, "+
					" FC_KEYWORDS, "+
					" FD_FECHA_PUBLICACION, "+
					" FD_FECHA_MODIFICACION, "+
					" FI_BAN_OTROS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
					, nota.getFcIdContenido(),nota.getFcIdCategoria(),nota.getFcTipoNota()
					, nota.getFcIdClassVideo(),nota.getFcTitulo() , nota.getFcDescripcion()
					, nota.getFcFriendlyUrl(),nota.getFcAutor(),nota.getFcUrlAutor()
					, nota.getFcImagen(), nota.getFcPieImagen(),nota.getClGaleria()
					, nota.getClRtfContenido(), nota.getFcIdYoutube(), nota.getFcIdContentOoyala()
					, nota.getFcIdPlayerOoyala(),nota.getFcKeywords(),dateFormat.format(new Date()),
					dateFormat.format(new Date()),nota.getFiBanOtros());

		} catch (Exception e) {

			logger.error(" Error insert [ NNotaDAO ] ", e);

			throw new NNotaDAOException(e.getMessage());

		}

		return rows;
	}
}
