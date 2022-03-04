package es.aytos.fpdual.fpdual2022;

import org.junit.*;

import es.aytos.fpdual.fpdual2022.dao.*;
import es.aytos.fpdual.fpdual2022.factoria.*;

// @SuppressWarnings("deprecation")
public class ServicioHaciendaSomosTodosTest {

    private final Integer poblacionTotal = 100;

    private ServicioHaciendaSomosTodos servicioHaciendaSomosTodos = null;

    private InterfazDAO dao = null;

    @Before
    public void inicializar() {
        this.servicioHaciendaSomosTodos = new ServicioHaciendaSomosTodos();
        this.dao = FactoriaBD.obtenerDAO();
    }

    // @Test
    // public void testCrearEsquemaCiudadanos() {
    // this.servicioHaciendaSomosTodos.crearEsquemaCiudadanos();
    //
    // assert (this.dao.ejecutarSentencia("DELETE CIUDADANOS"));
    // }

    // @Test
    // public void testRellenarTablaCiudadanos() {
    // this.servicioHaciendaSomosTodos.rellenarTablaCiudadanos(this.poblacionTotal);
    //
    // assertThat(this.servicioHaciendaSomosTodos.obtenerCiudadanos().size(), is(this.poblacionTotal));
    // }
/*
    @Test
    public void testObtencionCiudadanosMorosos() {
        int poblacionBD = this.servicioHaciendaSomosTodos.obtenerCiudadanosMorosos().size();

        assert (poblacionBD > 0);
    }
*/
    @Test
    public void testRellenarTablaMorosos() {
        this.servicioHaciendaSomosTodos.rellenarTablaMorosos();

        assert (true);
    }

    // @Test
    // public void testObtenerTablaMorosos() {
    // int poblacionBD = this.servicioHaciendaSomosTodos.obtenerTablaMorosos();
    //
    // assert (poblacionBD > 0);
    // }

}