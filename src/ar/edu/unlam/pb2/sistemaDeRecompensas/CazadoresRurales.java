package ar.edu.unlam.pb2.sistemaDeRecompensas;

public class CazadoresRurales extends Cazadores {

	
	public CazadoresRurales(Integer nivelDeExperiencia) {
		super(nivelDeExperiencia);
	}

	@Override
	protected void intimidacionEspecifica(Profugo profugo) {

		if (profugo.getIntimidado() == true) {
			profugo.setEsNervioso(true);
		}
	}
	
	@Override
	protected Boolean capturaEspecifica(Profugo profugo) {
		Boolean capturado = false;
		if(profugo.getEsNervioso() == true) {
			capturado = true;
		}
		return capturado;	
	}

}
