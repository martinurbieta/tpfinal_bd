package com.bd.tpfinal.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.zinbig.mongodemo.model.User;

/**
 * Clase que representa el repositorio de usuarios.
 *
 * @author Javier Bazzocco
 *
 */
@Repository
public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {

    /**
     * Recupera un usuario por su nombre.
     *
     * @param aName es el nombre del usuario.
     * @return el usuario hallado o null.
     */
    public User findByName(String aName);

    /**
     * Recupera un usuario por su nombre y cuenta de usuario.
     *
     * @param anUsername es la cuenta de usuario a buscar.
     * @param aName      es el nombre del usuario.
     * @return el usuario hallado o null.
     */
    @Query("{name: ?1, username:?0}")
    public User findUserX(String anUsername, String aName);

}