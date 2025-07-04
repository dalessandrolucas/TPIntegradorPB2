package ar.edu.unlam.pb2.sistemaDeRecompensas;

public class Profugo implements EntrenamientoProfugo {

	private Integer nivelDeInocencia, nivelDeHabilidad;
	private Boolean esNervioso, capturado, intimidado, esElite, protegidoLegal;

	public Profugo(Integer nivelDeInocencia, Integer nivelDeHabilidad, Boolean esNervioso, Boolean capturado,
			Boolean intimidado) {

		this.nivelDeInocencia = nivelDeInocencia;
		this.nivelDeHabilidad = nivelDeHabilidad;
		this.esNervioso = esNervioso;
		this.capturado = capturado;
		this.intimidado = intimidado;
		this.esElite = false;
		this.protegidoLegal = false;
	}

	public void setIntimidado(Boolean intimidado) {
		this.intimidado = intimidado;
	}

	public void reducirInocencia(Integer cantidad) {
		if (this.intimidado) {
			int nuevaInocencia = this.nivelDeInocencia - cantidad;

			if (this.protegidoLegal) {
				this.nivelDeInocencia = Math.max(nuevaInocencia, 40);
			}

			else {
				this.nivelDeInocencia = nuevaInocencia;
			}
		}
	}

	public void reducirHabilidad(int cantidad) {
		if (intimidado) {
			if (this.nivelDeHabilidad >= cantidad) {
				this.nivelDeHabilidad -= cantidad;
			} else {
				this.nivelDeHabilidad = 0;
			}
		}
	}

	@Override
	public void entrenamientoElite() {

		this.esNervioso = false;
		this.esElite = true;

	}

	@Override
	public void protecLegal() {

		this.protegidoLegal = true;
		this.nivelDeInocencia = Math.max(this.nivelDeInocencia, 40);
	}

	@Override
	public void entrenarArtMarciales() {

		this.nivelDeHabilidad = Math.min((this.nivelDeHabilidad * 2), 100);

	}

	public Integer getNivelDeInocencia() {
		return nivelDeInocencia;
	}

	public void setNivelDeInocencia(Integer nivelDeInocencia) {
		this.nivelDeInocencia = nivelDeInocencia;
	}

	public Integer getNivelDeHabilidad() {
		return nivelDeHabilidad;
	}

	public void setNivelDeHabilidad(Integer nivelDeHabilidad) {
		this.nivelDeHabilidad = nivelDeHabilidad;
	}

	public Boolean getEsNervioso() {
		return esNervioso;
	}

	public void setEsNervioso(Boolean esNervioso) {
		this.esNervioso = esNervioso;
	}

	public boolean isCapturado() {
		return capturado != null && capturado;
	}

	public void setCapturado(Boolean capturado) {
		this.capturado = capturado;
	}

	public Boolean getIntimidado() {
		return intimidado;
	}

	public Boolean getEsElite() {
		return esElite;
	}

	public void setEsElite(Boolean esElite) {
		this.esElite = esElite;
	}

	public Boolean getProtegidoLegal() {
		return protegidoLegal;
	}

}
