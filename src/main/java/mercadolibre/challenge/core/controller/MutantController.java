package mercadolibre.challenge.core.controller;

import mercadolibre.challenge.core.exception.DnaException;
import mercadolibre.challenge.core.service.MutantService;

import javax.inject.Inject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MutantController {

    @Inject
    private MutantService service;

    private Logger logger;

    public boolean isMutant(String[] dna){

        boolean output = false;

        logger.info("Inicia proceso de validacion de ADN");

        try {
            output = service.isMutant(dna);
        } catch (DnaException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    return output;
    }

}
