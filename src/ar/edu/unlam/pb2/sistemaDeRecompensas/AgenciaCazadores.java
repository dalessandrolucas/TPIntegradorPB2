package ar.edu.unlam.pb2.sistemaDeRecompensas;

import java.util.ArrayList;
import java.util.List;

public class AgenciaCazadores {

	private String nombre;
	private List<Cazadores> cazadores;

	public AgenciaCazadores(String nombre) {

		this.nombre = nombre;
		this.cazadores = new ArrayList<>();

	}

	public boolean contratarCazador(Cazadores cazador) {
		if (cazador != null) {
			return this.cazadores.add(cazador);
		}
		return false;
	}

	public List<Profugo> obtenerTodosLosProfugosCazados() {
		List<Profugo> cazados = new ArrayList<>();

		for (Cazadores cazador : this.cazadores) {
			cazados.addAll(cazador.getCazados());
		}

		return cazados;
	}

	public Profugo obtenerProfugoMasHabilCazado() {
		List<Profugo> cazados = obtenerTodosLosProfugosCazados();
		Profugo masHabil = null;
		int minHabilidad = -1;

		for (Profugo profugo : cazados) {
			if (profugo.getNivelDeHabilidad() > minHabilidad) {
				minHabilidad = profugo.getNivelDeHabilidad();
				masHabil = profugo;
			}
		}

		return masHabil;
	}

	public Cazadores obtenerCazadorConMasCapturas() {
		Cazadores cazadorConMasCapturas = null;
		int minCapturas = -1;

		for (Cazadores cazador : cazadores) {
			int cantidad = cazador.getCazados().size();
			if (cantidad > minCapturas) {
				minCapturas = cantidad;
				cazadorConMasCapturas = cazador;
			}
		}

		return cazadorConMasCapturas;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Cazadores> getCazadores() {
		return cazadores;
	}

}
