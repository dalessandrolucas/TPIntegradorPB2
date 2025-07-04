package ar.edu.unlam.pb2.sistemaDeRecompensas;

import java.util.ArrayList;

public class Zonas {
	private String nombre;
	private ArrayList<Profugo> profugos;

	public Zonas(String nombre, ArrayList<Profugo> profugos) {
		this.nombre = nombre;
		this.profugos = new ArrayList<Profugo>();
	}

	public void agregarProfugo(Profugo profugo) {
		profugos.add(profugo);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Profugo> getProfugos() {
		return profugos;
	}

	public void setProfugos(ArrayList<Profugo> profugos) {
		this.profugos = profugos;
	}

	public void removerCapturados() {
		profugos.removeIf(Profugo::isCapturado);
	}
}
