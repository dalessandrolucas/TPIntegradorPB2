package ar.edu.unlam.pb2.sistemaDeRecompensas;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SistemaTest {

	@Test
	public void queSeCreaConÉxitoUnaAgenciaPuedeContratarCazadoresYSeGuardanEnElArray() {
		AgenciaCazadores agencia = new AgenciaCazadores("Cazadores de Recompensas");
		Cazadores cazador = new CazadoresSigilosos(90);
		Cazadores cazador2 = new CazadoresRurales(80);

		assertTrue(agencia.contratarCazador(cazador));
		assertTrue(agencia.contratarCazador(cazador2));
		assertEquals(2, agencia.getCazadores().size());
	}

	@Test
	public void queNoSePuedeContratarUnCazadorInexistente() {
		AgenciaCazadores agencia = new AgenciaCazadores("Agencia C");
		try {
			assertFalse(agencia.contratarCazador(null));
		} catch (IllegalArgumentException excepcion) {
			assertEquals("El Cazador no puede ser Null", excepcion.getMessage());
		}
	}

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
    public void queNoSeSumanCapturasEnUnaZonaVacia() {
        Zonas zona = new Zonas("Haedo", new ArrayList<Profugo>());

        CazadoresSigilosos cazador = new CazadoresSigilosos(50);
        cazador.capturarEnZona(zona);

        assertEquals(Integer.valueOf(0), cazador.getCapturasRealizadas());

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

	@Test
	public void queSeGuardanLosProfugosCazadosYSeSumanLasCapturasRealizadas() {
		Profugo p1 = new Profugo(10, 40, false, false, false);
		Profugo p2 = new Profugo(41, 40, false, false, false);

		Cazadores cazador = new CazadoresSigilosos(90);
		Zonas zona = new Zonas("Haedo", new ArrayList<Profugo>());
		zona.agregarProfugo(p2);
		zona.agregarProfugo(p1);

		cazador.capturarEnZona(zona);
		assertTrue(p1.isCapturado());
		assertTrue(p2.isCapturado());
		assertEquals(2, cazador.getCazados().size());
		assertEquals(Integer.valueOf(2), cazador.getCapturasRealizadas());
	}

	@Test
	public void queSeObtieneElReporteDeTodosLosProfugosCazados() {
		Profugo p1 = new Profugo(10, 40, false, false, false);
		Profugo p2 = new Profugo(41, 40, false, false, false);
		Profugo p3 = new Profugo(40, 40, true, false, false);
		Profugo p4 = new Profugo(40, 40, true, false, false);

		AgenciaCazadores agencia = new AgenciaCazadores("Cazadores de Recompensas");
		Cazadores cazador = new CazadoresSigilosos(90);
		Cazadores cazador2 = new CazadoresRurales(80);

		agencia.contratarCazador(cazador);
		agencia.contratarCazador(cazador2);

		Zonas zona = new Zonas("Haedo", new ArrayList<Profugo>());
		zona.agregarProfugo(p2);
		zona.agregarProfugo(p1);

		Zonas castillo = new Zonas("Castillo", new ArrayList<Profugo>());

		castillo.agregarProfugo(p4);
		castillo.agregarProfugo(p3);

		cazador.capturarEnZona(zona);
		cazador2.capturarEnZona(castillo);
		// Se confirma que se capturaron los prófugos
		assertTrue(p1.isCapturado());
		assertTrue(p2.isCapturado());
		assertTrue(p3.isCapturado());
		assertTrue(p4.isCapturado());
		// Se confirma que se agregaron al array y se podrían ver por un menú
		assertEquals(agencia.obtenerTodosLosProfugosCazados().size(), 4);
	}

	@Test
	public void queSeObtieneElCazadorConMasCapturas() {
		AgenciaCazadores agencia = new AgenciaCazadores("Cazadores de Recompensas");

		Cazadores cazador = new CazadoresSigilosos(90);
		Cazadores cazador2 = new CazadoresRurales(80);

		agencia.contratarCazador(cazador);
		agencia.contratarCazador(cazador2);

		Profugo p1 = new Profugo(10, 40, false, false, false);
		Profugo p2 = new Profugo(41, 40, false, false, false);
		Profugo p3 = new Profugo(40, 40, true, false, false);

		Zonas haedo = new Zonas("Haedo", new ArrayList<Profugo>());
		haedo.agregarProfugo(p2);
		haedo.agregarProfugo(p1);

		Zonas castillo = new Zonas("Castillo", new ArrayList<Profugo>());

		castillo.agregarProfugo(p3);

		cazador.capturarEnZona(haedo);
		cazador2.capturarEnZona(castillo);

		// Se obtiene el cazador con mas capturas
		assertTrue(agencia.obtenerCazadorConMasCapturas().equals(cazador));
		// Se confirma que no es el cazador con mas capturas
		assertFalse(agencia.obtenerCazadorConMasCapturas().equals(cazador2));
	}
	
	@Test
	public void queSeObtieneElProfugoMasHabilCazado() {
		AgenciaCazadores agencia = new AgenciaCazadores("Cazadores de Recompensas");

		Cazadores cazador = new CazadoresSigilosos(90);
		Cazadores cazador2 = new CazadoresRurales(80);

		agencia.contratarCazador(cazador);
		agencia.contratarCazador(cazador2);

		Profugo p1 = new Profugo(10, 40, false, false, false);
		Profugo p2 = new Profugo(41, 49, false, false, false);
		Profugo p3 = new Profugo(40, 34, true, false, false);

		Zonas haedo = new Zonas("Haedo", new ArrayList<Profugo>());
		haedo.agregarProfugo(p2);
		haedo.agregarProfugo(p1);

		Zonas castillo = new Zonas("Castillo", new ArrayList<Profugo>());

		castillo.agregarProfugo(p3);

		cazador.capturarEnZona(haedo);
		cazador2.capturarEnZona(castillo);

		// Compara y devuelve el profugo mas habil correctamente
		assertTrue(agencia.obtenerProfugoMasHabilCazado().equals(p2));
		assertFalse(agencia.obtenerProfugoMasHabilCazado().equals(p1));
		assertFalse(agencia.obtenerProfugoMasHabilCazado().equals(p3));

	}
	

	@Test
	public void queDosCazadoresCapturenEnLaMismaZona() {
		AgenciaCazadores agencia = new AgenciaCazadores("Cazadores de Recompensas");

		Cazadores cazador = new CazadoresSigilosos(90);
		Cazadores cazador2 = new CazadoresRurales(80);

		agencia.contratarCazador(cazador);
		agencia.contratarCazador(cazador2);

		Profugo p1 = new Profugo(10, 40, false, false, false);
		Profugo p2 = new Profugo(41, 49, false, false, false);
		Profugo p3 = new Profugo(40, 50, true, false, false);

		Zonas haedo = new Zonas("Haedo", new ArrayList<Profugo>());
		haedo.agregarProfugo(p1);
		haedo.agregarProfugo(p2);
		haedo.agregarProfugo(p3);
		cazador.capturarEnZona(haedo);
		assertEquals(Integer.valueOf(2), cazador.getCapturasRealizadas());
		assertEquals(1, haedo.getProfugos().size());
		cazador2.capturarEnZona(haedo);
		assertEquals(Integer.valueOf(1), cazador2.getCapturasRealizadas());
		assertEquals(0, haedo.getProfugos().size());
	}
	
	
	@Test
	public void queLuegoDeCapturarNoQuedenProfugosCapturadosEnLaZona(){
		Profugo p1 = new Profugo(10, 40, false, false, false); 
	    Profugo p2 = new Profugo(30, 60, false, false, false); 
	    Profugo p3 = new Profugo(100, 40, false, false, false); 
	    Profugo p4 = new Profugo(300, 60, false, false, false); 

		Zonas zona = new Zonas("Haedo", new ArrayList<Profugo>());
	    zona.agregarProfugo(p1);
	    zona.agregarProfugo(p2);
	    zona.agregarProfugo(p3);
	    zona.agregarProfugo(p4);

	    CazadoresSigilosos cazador = new CazadoresSigilosos(50);
	    cazador.capturarEnZona(zona);

	    assertTrue(p1.isCapturado());
	    assertFalse(p2.isCapturado());
	    assertFalse(p3.isCapturado());
	    assertFalse(p4.isCapturado());
	    assertEquals(3 , zona.getProfugos().size());   
	}

	
	
	

	
	
	
	
}
