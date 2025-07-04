package ar.edu.unlam.pb2.sistemaDeRecompensas;

public class CazadoresUrbanos extends Cazadores {

	public CazadoresUrbanos(Integer nivelDeExperiencia) {
		super(nivelDeExperiencia);
	}

	@Override
	protected void intimidacionEspecifica(Profugo profugo) {
		if (profugo.getIntimidado().equals(true) && profugo.getEsElite().equals(false)) {
			profugo.setEsNervioso(false);
		}
	}

	@Override
	protected Boolean capturaEspecifica(Profugo profugo) {
		Boolean capturado = false;
		if (profugo.getEsNervioso() == false) {
			capturado = true;
		}
		return capturado;

	}

}
