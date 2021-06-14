package mercadolibre.challenge.core.controller;

import mercadolibre.challenge.core.exception.DnaException;
import mercadolibre.challenge.core.service.MutantService;
import mercadolibre.challenge.core.service.impl.MutantServiceImpl;

import java.io.IOException;

public class Mutant {

  private MutantService service;

  public boolean isMutant(String[] dna) throws DnaException, IOException {

    service = new MutantServiceImpl();
    return service.isMutant(dna);
  }
}
