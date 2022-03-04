package es.aytos.fpdual.fpdual2022.dao;

import java.util.List;
import java.util.Map;

public interface InterfazDAO {

	public boolean conectar();
	
	public boolean isConexionAbierta();

	public boolean desconectar();

	public boolean ejecutarSentencia(String sql);

	public List<Map<String, String>> ejecutarConsulta(String sql);

}
