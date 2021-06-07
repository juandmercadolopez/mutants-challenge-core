package mercadolibre.challenge.core.controller;

import mercadolibre.challenge.core.exception.DnaException;
import mercadolibre.challenge.core.service.MutantService;
import mercadolibre.challenge.core.service.impl.MutantServiceImpl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MutantController {

    private MutantService service;

    private Logger logger = Logger.getLogger(MutantController.class.getName());

    public boolean isMutant(String[] dna){

        boolean output = false;

        try {
            service = new MutantServiceImpl();
            output = service.isMutant(dna);
        } catch (DnaException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

    return output;

    }
}
