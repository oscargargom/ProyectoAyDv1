package es.aytos.fpdual.fpdual2022.modelo;

public class Ciudadano {
	private String id;
	private String nombre;
	private String direccion;
	private String facturacionAnual;
	private String paisResidencia;
	private String diasPermanenciaSpain;

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getFacturacionAnual() {
		return facturacionAnual;
	}

	public String getPaisResidencia() {
		return paisResidencia;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setFacturacionAnual(String facturacionAnual) {
		this.facturacionAnual = facturacionAnual;
	}

	public void setPaisResidencia(String paisResidencia) {
		this.paisResidencia = paisResidencia;
	}

	public String getDiasPermanenciaSpain() {
		return diasPermanenciaSpain;
	}

	public void setDiasPermanenciaSpain(String diasPermanenciaSpain) {
		this.diasPermanenciaSpain = diasPermanenciaSpain;
	}	
	
	
}
