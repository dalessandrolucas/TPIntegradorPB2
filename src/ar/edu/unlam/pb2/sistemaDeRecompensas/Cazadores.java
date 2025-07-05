package ar.edu.unlam.pb2.sistemaDeRecompensas;

import java.util.ArrayList;
import java.util.List;

public abstract class Cazadores implements ComportamientoCazador {

	private Integer nivelDeExperiencia;
	private Integer capturasRealizadas;

	protected List<Profugo> cazados = new ArrayList<>();

	public Cazadores(Integer nivelDeExperiencia) {
		this.nivelDeExperiencia = nivelDeExperiencia;
		this.capturasRealizadas = 0;
	}

	@Override
	public void intimidar(Profugo profugo) {
		profugo.setIntimidado(true);
		profugo.reducirInocencia(2);
		intimidacionEspecifica(profugo);
	}

	@Override
	public void capturar(Profugo profugo) {
		if (this.nivelDeExperiencia > profugo.getNivelDeInocencia() && this.capturaEspecifica(profugo)) {
			profugo.setCapturado(true);
		} else {
			intimidar(profugo);
		}
	}

	public void agregarCazado(Profugo profugo) {
		this.cazados.add(profugo);
	}

	public void capturarEnZona(Zonas zona) {
        int minHabilidad = Integer.MAX_VALUE;
        int cantidadCapturados = 0;

        if (zona.getProfugos().size() > 0) {
            for (Profugo profugo : zona.getProfugos()) {
                capturar(profugo);

                if (profugo.isCapturado()) {
                    cantidadCapturados++;
                    agregarCazado(profugo);
                } else {
                    if (profugo.getNivelDeHabilidad() < minHabilidad) {
                        minHabilidad = profugo.getNivelDeHabilidad();
                    }
                }
            }
        }

        zona.removerCapturados();

        if (minHabilidad == Integer.MAX_VALUE) {
            minHabilidad = 0;
        }

        this.capturasRealizadas += cantidadCapturados;
        this.nivelDeExperiencia += minHabilidad + (2 * cantidadCapturados);
    }

	public List<Profugo> getCazados() {
		return cazados;
	}

	protected abstract void intimidacionEspecifica(Profugo profugo);

	protected abstract Boolean capturaEspecifica(Profugo profugo);

	public Integer getCapturasRealizadas() {
		return capturasRealizadas;
	}

	public Integer getNivelDeExperiencia() {
		return nivelDeExperiencia;
	}

}