package ar.edu.unlam.pb2.sistemaDeRecompensas;

import static org.junit.Assert.*;

import java.util.ArrayList;


import org.junit.Test;

public class SistemaTest {

	@Test
	public void queCazadorUrbanoCaptureEIntimideCorrectamenteYSeSumeLaCapturaRealizada() {
		// Se crean correctamente los profugos
		Profugo p1 = new Profugo(10, 40, false, false, false);
		Profugo p2 = new Profugo(60, 60, true, false, false);

		// Se crea correctamente la zona
		Zonas zona = new Zonas("Morón", new ArrayList<Profugo>());
		// Se agregan correctamente a la zona
		zona.agregarProfugo(p1);
		zona.agregarProfugo(p2);

		// Se crea correctamente el cazador
		CazadoresUrbanos cazador = new CazadoresUrbanos(50);
		// Procede a capturarEnZona correctamente
		cazador.capturarEnZona(zona);

		// Captura o NO captura correctamente
		assertTrue(p1.isCapturado());
		assertFalse(p2.isCapturado());
		// Intimida Correctamente
		assertEquals(Integer.valueOf(58), p2.getNivelDeInocencia());
		assertFalse(p2.getEsNervioso());
		// Se suma la captura correctamente
		assertEquals(Integer.valueOf(1), cazador.getCapturasRealizadas());
	}

	@Test
	public void queCazadorRuralCaptureEIntimideCorrectamenteYSeSumeLaExperiencia() {
		Profugo p1 = new Profugo(10, 30, true, false, false);
		Profugo p2 = new Profugo(50, 50, false, false, false);

		Zonas zona = new Zonas("Castillo", new ArrayList<Profugo>());

		zona.agregarProfugo(p1);
		zona.agregarProfugo(p2);

		CazadoresRurales cazador = new CazadoresRurales(50);
		cazador.capturarEnZona(zona);

		// Captura o NO captura correctamente
		assertTrue(p1.isCapturado());
		assertFalse(p2.isCapturado());
		// Intimida Correctamente
		assertEquals(Integer.valueOf(48), p2.getNivelDeInocencia()); // -2
		assertTrue(p2.getEsNervioso());
		// Se suma la experiencia
		assertEquals(Integer.valueOf(102), cazador.getNivelDeExperiencia());

	}

	@Test
	public void queCazadorSigilosoCaptureEIntimideCorrectamente() {
		Profugo p1 = new Profugo(10, 40, false, false, false);
		Profugo p2 = new Profugo(30, 60, false, false, false);

		Zonas zona = new Zonas("Haedo", new ArrayList<Profugo>());
		zona.agregarProfugo(p1);
		zona.agregarProfugo(p2);

		CazadoresSigilosos cazador = new CazadoresSigilosos(50);
		cazador.capturarEnZona(zona);

		assertTrue(p1.isCapturado());
		assertFalse(p2.isCapturado());
		assertEquals(Integer.valueOf(28), p2.getNivelDeInocencia());
		assertEquals(Integer.valueOf(55), p2.getNivelDeHabilidad());
	}

	@Test
	public void queNoSePuedePonerNerviosoAUnElite() {
		Profugo p2 = new Profugo(30, 60, false, false, false);

		Zonas zona = new Zonas("Haedo", new ArrayList<Profugo>());
		zona.agregarProfugo(p2);

		p2.entrenamientoElite();

		CazadoresRurales cazador = new CazadoresRurales(25);
		cazador.capturarEnZona(zona);

		// NO captura
		assertFalse(p2.isCapturado());
		// SI intimida
		assertTrue(p2.getIntimidado());
		// A pesar de intimidar correctamente, no lo vuelve nervioso ya que es un ELITE
		assertFalse(p2.getEsNervioso());
		assertEquals(Integer.valueOf(28), p2.getNivelDeInocencia());
	}

	@Test
	public void queSeEntrenanArtesMarcialesCorrectamenteYNoSuperaElLimite() {
		Profugo p1 = new Profugo(10, 40, false, false, false);
		Profugo p2 = new Profugo(30, 60, false, false, false);

		Zonas zona = new Zonas("Haedo", new ArrayList<Profugo>());
		zona.agregarProfugo(p2);
		zona.agregarProfugo(p1);

		p2.entrenarArtMarciales();
		// Duplica el nivel de habilidad, supera los 100 pero al estar limitado a 100 no
		// se pasa
		assertEquals(Integer.valueOf(100), p2.getNivelDeHabilidad());

		p1.entrenarArtMarciales();
		// Duplica el nivel de habilidad
		assertEquals(Integer.valueOf(80), p1.getNivelDeHabilidad());
	}

	@Test
	public void queSeAgregaProteccionLegalYElNivelDeInocenciaNoBajaDelMinimo() {
		Profugo p1 = new Profugo(10, 40, false, false, false);
		Profugo p2 = new Profugo(41, 60, false, false, false);

		Zonas zona = new Zonas("Haedo", new ArrayList<Profugo>());
		zona.agregarProfugo(p2);
		zona.agregarProfugo(p1);

		CazadoresSigilosos cazador = new CazadoresSigilosos(50);

		// Se agrega proteccion legal correctamente por lo que su nivel de inocencia no
		// puede ser menor a 40, asi que al ser menor, lo setea en 40
		p1.protecLegal();
		assertEquals(Integer.valueOf(40), p1.getNivelDeInocencia());

		// Al ser mayor a 40 lo deja igual
		p2.protecLegal();
		assertEquals(Integer.valueOf(41), p2.getNivelDeInocencia());

		cazador.capturarEnZona(zona);
		// NO logra capturarlo, lo intimida y baja la inocencia a 39
		assertFalse(p2.isCapturado());
		// NO permite correctamente que la inocencia baje de 40
		assertEquals(Integer.valueOf(40), p2.getNivelDeInocencia());
	}
}
