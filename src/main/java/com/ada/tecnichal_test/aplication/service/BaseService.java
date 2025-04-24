package com.ada.tecnichal_test.aplication.service;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseService<T, ID> {
    private static final Logger logger = Logger.getLogger(BaseService.class.getName());

    protected abstract JpaRepository<T, ID> getRepository();

    /**
     * Finds an entity by its ID.
     *
     * @param id
     * @return T
     */
    public Optional<T> get(ID id) {
        try {
            return getRepository().findById(id);
        } catch (DataAccessException e) {
            logError("Error while retrieving entity by ID: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve entity by ID", e);
        }
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param id
     */
    public void delete(ID id) {
        try {
            logInfo("Deleting entity with ID: " + id);
            getRepository().deleteById(id);
        } catch (DataAccessException e) {
            logError("Error while deleting entity: " + e.getMessage());
            throw new RuntimeException("Failed to delete entity", e);
        }
    }

    /**
     * Logs an info message.
     *
     * @param message
     */
    protected void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    /**
     * Logs a warning message.
     *
     * @param message
     */
    protected void logError(String message) {
        logger.log(Level.SEVERE, message);
    }


}