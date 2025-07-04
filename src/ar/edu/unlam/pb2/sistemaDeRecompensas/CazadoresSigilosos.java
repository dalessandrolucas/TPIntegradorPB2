package ar.edu.unlam.pb2.sistemaDeRecompensas;

public class CazadoresSigilosos extends Cazadores{

	public CazadoresSigilosos(Integer nivelDeExperiencia) {
		super(nivelDeExperiencia);
	}

	@Override
	protected void intimidacionEspecifica(Profugo profugo) {
		if(profugo.getIntimidado().equals(true)) {
			profugo.reducirHabilidad(5);
		}	
		
	}

	@Override
	protected Boolean capturaEspecifica(Profugo profugo) {
		Boolean capturado = false;
		if(profugo.getNivelDeHabilidad() < 50) {
			capturado = true;
		}
		return capturado;
		
	}

}
