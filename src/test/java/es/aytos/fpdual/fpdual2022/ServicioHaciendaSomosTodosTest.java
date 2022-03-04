package es.aytos.fpdual.fpdual2022;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import es.aytos.fpdual.fpdual2022.dao.InterfazDAO;
import es.aytos.fpdual.fpdual2022.factoria.FactoriaBD;

public class ServicioHaciendaSomosTodosTest {

	private ServicioHaciendaSomosTodos servicioHaciendaSomosTodos = null;
	private InterfazDAO dao = null;

	@Before
	public void inicializar() {
		this.servicioHaciendaSomosTodos = new ServicioHaciendaSomosTodos();
		this.dao = FactoriaBD.obtenerDAO();
	}

	@Test
	public void testCrearEsquemaCiudadanos() {
		this.servicioHaciendaSomosTodos.crearEsquemaCiudadanos();
		
		assert (this.dao.ejecutarSentencia("DELETE CIUDADANOS"));
	}
	
	@Test
	public void testRellenarTablaCiudadanos() {
		int poblacionTotal = 100;
		this.servicioHaciendaSomosTodos.rellenarTablaCiudadanos(poblacionTotal);
		
		assertThat(this.dao.ejecutarConsulta("SELECT * FROM CIUDADANOS").size(), is(poblacionTotal));
	}

}
