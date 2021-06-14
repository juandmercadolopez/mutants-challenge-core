package mercadolibre.challenge.core.controller;

import mercadolibre.challenge.core.exception.DnaException;
import mercadolibre.challenge.core.service.MutantService;

public class Mutant {

  public static boolean isMutant(String[] dna) throws DnaException {

    return MutantService.isMutant(dna);

  }
}
