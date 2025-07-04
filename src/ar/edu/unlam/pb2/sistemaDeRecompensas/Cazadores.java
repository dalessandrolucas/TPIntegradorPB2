package ar.edu.unlam.pb2.sistemaDeRecompensas;

import java.util.ArrayList;

public abstract class Cazadores {
	private Integer nivelDeExperiencia;

	public Cazadores(Integer nivelDeExperiencia) {
		super();
		this.nivelDeExperiencia = nivelDeExperiencia;
	}

	public void intimidar(Profugo profugo) {
		profugo.setIntimidado(true);
		profugo.reducirInocencia(2);
		intimidacionEspecifica(profugo);
	}

	public void capturar(Profugo profugo) {
		if (nivelDeExperiencia > profugo.getNivelDeInocencia() && capturaEspecifica(profugo)) {
			profugo.setCapturado(true);
		} else {
			intimidar(profugo);
		}
	}

	public void capturarEnZona(Zonas zona) {
		int minHabilidad = Integer.MAX_VALUE;
		int cantidadCapturados = 0;

		for (Profugo profugo : zona.getProfugos()) {
			capturar(profugo);
			
			if (profugo.isCapturado()) {
				cantidadCapturados++;
			} else {
				if (profugo.getNivelDeHabilidad() < minHabilidad) {
					minHabilidad = profugo.getNivelDeHabilidad();
				}
			}
		}
		
		zona.removerCapturados();

		if (minHabilidad == Integer.MAX_VALUE) {
			minHabilidad = 0;
		}

		nivelDeExperiencia += minHabilidad + (2 * cantidadCapturados);
	}

	protected abstract void intimidacionEspecifica(Profugo profugo);

	protected abstract Boolean capturaEspecifica(Profugo profugo);

}