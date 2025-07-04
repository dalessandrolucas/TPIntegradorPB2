package ar.edu.unlam.pb2.sistemaDeRecompensas;

public class Profugo {

	private Integer nivelDeInocencia, nivelDeHabilidad;
	private Boolean esNervioso, capturado, intimidado;

	public Profugo(Integer nivelDeInocencia, Integer nivelDeHabilidad, Boolean esNervioso, Boolean capturado,
			Boolean intimidado) {

		this.nivelDeInocencia = nivelDeInocencia;
		this.nivelDeHabilidad = nivelDeHabilidad;
		this.esNervioso = esNervioso;
		this.capturado = capturado;
		this.intimidado = intimidado;
	}

	public void setIntimidado(Boolean intimidado) {
		this.intimidado = intimidado;
	}

	public void reducirInocencia(Integer cantidad) {
		if (intimidado) {
			this.nivelDeInocencia -= cantidad;
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

}
