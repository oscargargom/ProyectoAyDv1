package es.aytos.fpdual.fpdual2022.factoria;

import es.aytos.fpdual.fpdual2022.dao.InterfazDAO;
import es.aytos.fpdual.fpdual2022.dao.SQLServerDAO;

public class FactoriaBD {

	public static InterfazDAO obtenerDAO() {
		return new SQLServerDAO();
	}

}
