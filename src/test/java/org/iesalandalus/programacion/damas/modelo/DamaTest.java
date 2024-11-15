package org.iesalandalus.programacion.damas.modelo;

import javax.naming.OperationNotSupportedException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DamaTest {
	private static final String COLOR_NO_ESPERADO = "El color no es el esperado.";
	private static final String POSICION_NO_ESPERADA = "La posicion no es la esperada.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String EXCEPCION_NO_VALIDA = "El tipo de excepción no es valida o no ha saltado.";
	private static final String MENSAJE_ERROR_COLOR_NULO = "ERROR: El color no puede ser nulo.";
	private static final String MENSAJE_ERROR_DIRECCION_NULA = "ERROR: La direccion no puede ser nula.";
	private static final String MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO = "ERROR: Movimiento no permitido.";
	private static final String MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO_DAMA_ESPECIAL = "ERROR: Las damas normales solo se pueden mover 1 casilla.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";

	private static Dama damaBlanca;
	private static Dama damaNegra;

	@BeforeEach
	void asignarValoresDama()
	{
		damaBlanca = new Dama(Color.BLANCO);
		damaNegra = new Dama(Color.NEGRO);
	}
	@Test
	void constructores()
	{
		// Test de constructor por defecto
		damaBlanca = new Dama();
		assertEquals(Color.BLANCO, damaBlanca.getColor(), COLOR_NO_ESPERADO);
		assertTrue(damaBlanca.getPosicion().getFila()==1||damaBlanca.getPosicion().getFila()==2||damaBlanca.getPosicion().getFila()==3, POSICION_NO_ESPERADA);

		assertEquals(Color.NEGRO, damaNegra.getColor(), COLOR_NO_ESPERADO);
		assertTrue(damaNegra.getPosicion().getFila()==6||damaNegra.getPosicion().getFila()==7||damaNegra.getPosicion().getFila()==8, POSICION_NO_ESPERADA);

		NullPointerException excepcion = assertThrows(NullPointerException.class, () -> { new Dama(null);}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_COLOR_NULO, excepcion.getMessage(), MENSAJE_NO_CORRECTO);
	}
	@Test
	void moverIncorrectamente() throws OperationNotSupportedException
	{
		NullPointerException excepcionNula;
		OperationNotSupportedException excepcionMovimiento;

		damaBlanca = new Dama(Color.BLANCO);
		damaNegra = new Dama(Color.NEGRO);

		excepcionNula = assertThrows(NullPointerException.class, () -> { damaBlanca.mover(null, 1);}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_DIRECCION_NULA, excepcionNula.getMessage(), MENSAJE_NO_CORRECTO);
		excepcionNula = assertThrows(NullPointerException.class, () -> { damaNegra.mover(null, 1);}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_DIRECCION_NULA, excepcionNula.getMessage(), MENSAJE_NO_CORRECTO);

		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { damaBlanca.mover(Direccion.SURESTE,1); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { damaBlanca.mover(Direccion.SUROESTE, 1); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);

		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { damaBlanca.mover(Direccion.NORESTE, 2); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO_DAMA_ESPECIAL, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { damaBlanca.mover(Direccion.NOROESTE, 2); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO_DAMA_ESPECIAL, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);

		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { damaNegra.mover(Direccion.NORESTE, 1); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { damaNegra.mover(Direccion.NOROESTE, 1); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);

		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { damaNegra.mover(Direccion.SURESTE,2); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO_DAMA_ESPECIAL, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { damaNegra.mover(Direccion.SUROESTE, 2); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO_DAMA_ESPECIAL, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
	}
	@Test
	void moverCorrectamente() throws OperationNotSupportedException {

		// Test de movimiento correcto del rey blanco

		damaBlanca = new Dama(Color.BLANCO);

		damaBlanca.setPosicion(new Posicion(7,'c'));
		damaBlanca.mover(Direccion.NORESTE, 1);
		assertEquals(new Posicion(8, 'd'), damaBlanca.getPosicion(), POSICION_NO_ESPERADA);
		damaBlanca.mover(Direccion.SURESTE, 3);
		assertEquals(new Posicion(5, 'g'), damaBlanca.getPosicion(), POSICION_NO_ESPERADA);
		damaBlanca.mover(Direccion.SUROESTE, 4);
		assertEquals(new Posicion(1, 'c'), damaBlanca.getPosicion(), POSICION_NO_ESPERADA);

		damaNegra = new Dama(Color.NEGRO);

		damaNegra.setPosicion(new Posicion(2,'b'));
		damaNegra.mover(Direccion.SURESTE, 1);
		assertEquals(new Posicion(1, 'c'), damaNegra.getPosicion(), POSICION_NO_ESPERADA);
		damaNegra.mover(Direccion.NORESTE, 3);
		assertEquals(new Posicion(4, 'f'), damaNegra.getPosicion(), POSICION_NO_ESPERADA);
		damaNegra.mover(Direccion.NOROESTE, 4);
		assertEquals(new Posicion(8, 'b'), damaNegra.getPosicion(), POSICION_NO_ESPERADA);
	}

	@Test
	void toStringImprimeCorrectamente()
	{
		damaBlanca = new Dama(Color.BLANCO);
		assertEquals("color=Blanco, posicion=(fila="+damaBlanca.getPosicion().getFila()+", columna="+damaBlanca.getPosicion().getColumna()+")", damaBlanca.toString(), CADENA_NO_ESPERADA);
	}
}
