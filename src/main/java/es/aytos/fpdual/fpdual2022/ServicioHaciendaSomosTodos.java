package es.aytos.fpdual.fpdual2022;

import java.util.*;

import es.aytos.fpdual.fpdual2022.dao.*;
import es.aytos.fpdual.fpdual2022.factoria.*;
import es.aytos.fpdual.fpdual2022.modelo.*;
import es.aytos.fpdual.fpdual2022.util.*;

public class ServicioHaciendaSomosTodos {

    private InterfazDAO dao = null;

    private final String CIERRE_PARENTESIS = ")";

    private final int ACTUACION_KIKI_CODIGO = 1;

    private final String ACTUACION_KIKI_DESCRIPCION = "MUERTE POR KIKI";

    private final int ACTUACION_ROTURA_PIERNAS_CODIGO = 2;

    private final String ACTUACION_ROTURA_PIERNAS_DESCRIPCION = "ROTURA PIERNAS";

    private final int ACTUACION_CORBATA_CODIGO = 3;

    private final String ACTUACION_CORBATA_DESCRIPCION = "CORBATA COLOMBIANA";

    private final int ACTUACION_MUTILACION_CODIGO = 4;

    private final String ACTUACION_MUTILACION_DESCRIPCION = "CORTADA DE MANOS GUILLOTINA STYLE";

    private final int ACTUACION_VENDIDO_CODIGO = 5;

    private final String ACTUACION_VENDIDO_DESCRIPCION = "VENDIDO COMO ESCLAVO";

    private final int ACTUACION_MEDIEVO_CODIGO = 6;

    private final String ACTUACION_MEDIEVO_DESCRIPCION = "REALIZAR MEDIEVO";

    private final int ACTUACION_MULTACA_CODIGO = 7;

    private final String ACTUACION_MULTACA_DESCRIPCION = "MULTACA INFERNAL";

    private final int ACTUACION_LIBRE_CODIGO = 8;

    private final String ACTUACION_LIBRE_DESCRIPCION = "SE LIBRA POR ERROR JUDICIAL";

    public static void main(String[] args) {
        // NADA
    }

    private void borrarEsquemaCiudadano() {
        this.getDao().ejecutarSentencia("DROP TABLE CIUDADANOS");
    }

    public void crearEsquemaCiudadanos() {
        this.borrarEsquemaCiudadano();

        String sql = "CREATE TABLE CIUDADANOS(id NUMERIC NOT NULL PRIMARY KEY, "
                + "nombre VARCHAR(100) NOT NULL, direccion VARCHAR(150), "
                + "facturacionAnual NUMERIC,paisResidencia VARCHAR(50),diasPermanencia NUMERIC NOT NULL)";

        this.getDao().ejecutarSentencia(sql);

        sql = "CREATE TABLE MOROSOS(id NUMERIC NOT NULL PRIMARY KEY, "
                + "nombre VARCHAR(100) NOT NULL, actuacion VARCHAR(150))";

        this.getDao().ejecutarSentencia(sql);
    }

    public void rellenarTablaCiudadanos(int cantidad) {
        String sqlInsert = "INSERT INTO CIUDADANOS(id,nombre,direccion,facturacionAnual,paisResidencia,diasPermanencia) VALUES(";

        List<Ciudadano> poblacion = new ArrayList<Ciudadano>();
        Ciudadano ciudadano = null;
        for (int i = 0; i < cantidad; i++) {
            ciudadano = new Ciudadano();
            ciudadano.setId(String.valueOf(i + 1));
            ciudadano.setNombre("Nombre " + (i + 1));
            ciudadano.setDireccion("Direccion " + (i + 1));
            ciudadano.setFacturacionAnual(Utilidades.tirar1d100());
            ciudadano.setPaisResidencia(Utilidades.obtenerPaisResidencia());
            ciudadano.setDiasPermanenciaSpain(Utilidades.obtenerDiasResidencia());

            poblacion.add(ciudadano);
        }

        for (Ciudadano c : poblacion) {
            this.getDao().ejecutarSentencia(sqlInsert + c.traducirASqlValues() + this.CIERRE_PARENTESIS);
        }
    }

    public List<Ciudadano> obtenerCiudadanos() {
        List<Map<String, String>> ciudadanosSQL = this.getDao().ejecutarConsulta(
                "SELECT id,nombre,direccion,facturacionAnual,paisResidencia,diasPermanencia FROM CIUDADANOS");

        List<Ciudadano> poblacion = new ArrayList<Ciudadano>();
        Ciudadano citizen = null;
        for (Map<String, String> ciudadanoSQL : ciudadanosSQL) {
            citizen = new Ciudadano();
            citizen.setId(ciudadanoSQL.get("id"));
            citizen.setNombre(ciudadanoSQL.get("nombre"));
            citizen.setDireccion(ciudadanoSQL.get("direccion"));
            citizen.setFacturacionAnual(ciudadanoSQL.get("facturacionAnual"));
            citizen.setPaisResidencia(ciudadanoSQL.get("paisResidencia"));
            citizen.setDiasPermanenciaSpain(ciudadanoSQL.get("diasPermanencia"));

            poblacion.add(citizen);
        }

        return poblacion;
    }

    private InterfazDAO getDao() {
        if (Objects.isNull(this.dao)) {
            this.dao = FactoriaBD.obtenerDAO();
        }

        return this.dao;
    }

    private boolean declaraResidirEnElExtranjero(Ciudadano c) {
        return !c.getPaisResidencia().equals("ESPAÑA");
    }

    private boolean haEstadoViviendoEnSpain(Ciudadano c) {
        return Integer.valueOf(c.getDiasPermanenciaSpain()) > Utilidades.TOPE_DIAS_MAXIMO_EN_SPAIN_PARA_NO_DECLARAR;
    }

    public List<Ciudadano> obtenerCiudadanosMorosos() {

        List<Ciudadano> ciudadanos = this.obtenerCiudadanos();
        List<Ciudadano> morosos = new ArrayList<Ciudadano>();

        for (Ciudadano c : ciudadanos) {
            if (this.declaraResidirEnElExtranjero(c) && this.haEstadoViviendoEnSpain(c)) {
                morosos.add(c);
            }
        }

        return morosos;
    }

    public void rellenarTablaMorosos() {
        List<Ciudadano> morosos = this.obtenerCiudadanosMorosos();

        String sqlInsert = "INSERT INTO MOROSOS(id,nombre,actuacion) VALUES(";
        for (Ciudadano m : morosos) {
            this.getDao().ejecutarSentencia(sqlInsert + m.getId() + "," + Utilidades.sqlTratarTexto(m.getNombre()) + ","
                    + Utilidades.sqlTratarTexto(this.decidirActuacion()) + this.CIERRE_PARENTESIS);
        }
    }

    private String decidirActuacion() {
        int actuacion = Integer.parseInt(Utilidades.tirar1d8());
        String actuacionDesc = "";
        switch (actuacion) {
        case ACTUACION_KIKI_CODIGO:
            actuacionDesc = this.ACTUACION_KIKI_DESCRIPCION;
            break;
        case ACTUACION_ROTURA_PIERNAS_CODIGO:
            actuacionDesc = this.ACTUACION_ROTURA_PIERNAS_DESCRIPCION;
            break;
        case ACTUACION_CORBATA_CODIGO:
            actuacionDesc = this.ACTUACION_CORBATA_DESCRIPCION;
            break;
        case ACTUACION_MUTILACION_CODIGO:
            actuacionDesc = this.ACTUACION_MUTILACION_DESCRIPCION;
            break;
        case ACTUACION_VENDIDO_CODIGO:
            actuacionDesc = this.ACTUACION_VENDIDO_DESCRIPCION;
            break;
        case ACTUACION_MEDIEVO_CODIGO:
            actuacionDesc = this.ACTUACION_MEDIEVO_DESCRIPCION;
            break;
        case ACTUACION_MULTACA_CODIGO:
            actuacionDesc = this.ACTUACION_MULTACA_DESCRIPCION;
            break;
        case ACTUACION_LIBRE_CODIGO:
            actuacionDesc = this.ACTUACION_LIBRE_DESCRIPCION;
            break;
        }

        return actuacionDesc;
    }
}