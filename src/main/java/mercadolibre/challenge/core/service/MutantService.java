package mercadolibre.challenge.core.service;

import mercadolibre.challenge.core.exception.DnaException;

import java.io.IOException;

public interface MutantService {

  boolean isMutant(String[] dna) throws DnaException, IOException;
}
