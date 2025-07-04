package ar.edu.unlam.pb2.sistemaDeRecompensas;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SistemaTest {

	@Test
	public void queCazadorUrbanoCaptureYIntimideCorrectamente() {
		Profugo p1 = new Profugo(10, 40, false, false, false);
		Profugo p2 = new Profugo(60, 60, true, false, false);

		Zonas zona = new Zonas("Morón", new ArrayList<Profugo>());
		zona.agregarProfugo(p1);
		zona.agregarProfugo(p2);

		CazadoresUrbanos cazador = new CazadoresUrbanos(50);
		cazador.capturarEnZona(zona);

		assertTrue(p1.isCapturado());
		assertFalse(p2.isCapturado());
		assertEquals(Integer.valueOf(58), p2.getNivelDeInocencia());
		assertFalse(p2.getEsNervioso());
	}

	
	@Test
	public void queCazadorRuralCaptureYIntimideCorrectamente() {
	    Profugo p1 = new Profugo(10, 30, true, false, false);
	    Profugo p2 = new Profugo(50, 50, false, false, false);

		Zonas zona = new Zonas("Castillo", new ArrayList<Profugo>());

	    zona.agregarProfugo(p1);
	    zona.agregarProfugo(p2);

	    CazadoresRurales cazador = new CazadoresRurales(50);
	    cazador.capturarEnZona(zona);

	    assertTrue(p1.isCapturado());
	    assertFalse(p2.isCapturado());
	    assertEquals(Integer.valueOf(48), p2.getNivelDeInocencia()); // -2
	    assertTrue(p2.getEsNervioso());
	}
	
	@Test
	public void queCazadorSigilosoCaptureYIntimideCorrectamente() {
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
}
