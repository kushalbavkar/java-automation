package com.java.automation.excel;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExcelManager {
    private static final Logger log = LoggerFactory.getLogger(ExcelManager.class);

    private Connection connection = null;

    public ExcelManager(final String file) throws FilloException {
        destroyConnection();
        this.connection = getConnection(file);
    }

    @PreDestroy
    private void destroyConnection() {
        if (connection != null)
            connection.close();
    }

    private Connection getConnection(final String file) throws FilloException {
        if (Files.notExists(Paths.get(file))) {
            log.error("[{}] File does not exist", file);
            throw new FilloException(String.format("[%s] Input file missing", file));
        }

        Fillo fillo = new Fillo();
        return fillo.getConnection(file);
    }

    public Recordset Select(final String query) throws FilloException {
        final Recordset recordset = this.connection.executeQuery(query);
        log.info("Select operation successful");

        return recordset;
    }

    public void Insert(final String query) throws FilloException {
        this.connection.executeUpdate(query);
        log.info("Insert operation successful, rows affected : [1]");
    }

    public void Update(final String query) throws FilloException {
        final int rowsAffected = this.connection.executeUpdate(query);
        log.info("Update operation successful, rows affected : [{}]", rowsAffected);
    }
}
