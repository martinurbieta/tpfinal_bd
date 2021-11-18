/**
 * Este paquete contiene todas las implementaciones de los repositorios.
 */
package com.bd.tpfinal.repositories;

/**
 * Esta interface define el comportamiento extra que debe cumplir un repositorio
 * de usuarios.
 * 
 * @author Javier Bazzocco
 *
 */
public interface CustomUserRepository {

	/**
	 * Obtiene la cantidad de usuarios que tienen la misma clave.
	 * 
	 * @return el número de usuarios.
	 */
	public int getNumberOfUsersThatHasTheSamePassword();
}
