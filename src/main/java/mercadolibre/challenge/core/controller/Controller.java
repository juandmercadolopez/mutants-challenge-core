package mercadolibre.challenge.core.controller;

import mercadolibre.challenge.core.service.Service;

import javax.inject.Inject;
import java.util.logging.Logger;

public class Controller {

    @Inject
    private Service service;

    private Logger logger;

    public boolean isMutant(String[] dna){

        logger.info("Inicia proceso de validacion de ADN");

        return service.isMutant(dna);

    }

}
