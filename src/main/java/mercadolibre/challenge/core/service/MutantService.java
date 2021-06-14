package mercadolibre.challenge.core.service;

import mercadolibre.challenge.core.constant.ErrorConstant;
import mercadolibre.challenge.core.exception.DnaException;
import mercadolibre.challenge.core.util.Util;

import java.util.logging.Logger;

public class MutantService {

  private static Logger logger = Logger.getLogger(MutantService.class.getName());

  private static final int ARRAY_LENGTH = 6;
  private static char DNA_MATRIX[][] = new char[ARRAY_LENGTH][ARRAY_LENGTH];
  private static final int LIMIT_POSITION = ARRAY_LENGTH - 1;
  private static final int MIDDLE_POSITION = (ARRAY_LENGTH / 2) - 1;
  private static int START_INDEX = 0;
  private static int SEQUENCE_COUNTER = 0;

  public static boolean isMutant(String[] dna) throws DnaException {
    validateCharacterSequences(dna);
    setMatrixDna(dna, START_INDEX, START_INDEX);

    checkHorizontals(START_INDEX, START_INDEX);
    checkVerticals(START_INDEX, START_INDEX);
    checkForwardDiagonals(START_INDEX, START_INDEX);

    if (SEQUENCE_COUNTER > 1) {
      SEQUENCE_COUNTER = 0;
      return true;
    }

    return false;
  }

  public static void validateCharacterSequences(String[] dna) throws DnaException {
    if (dna.length == ARRAY_LENGTH) {
      for (String str : dna) {
        if (!Util.validateDnaRowSequenceCharacters(str)) {
          throw new DnaException(ErrorConstant.ERROR_DNA_SEQUENCE);
        }
      }
    } else {
      throw new DnaException(ErrorConstant.ERROR_DNA_SEQUENCE);
    }
  }

  static void setMatrixDna(String[] dna, int rowIndex, int colIndex) {

    if (rowIndex > LIMIT_POSITION) {
      logger.info("Seteo de matriz finalizado");
    } else {
      DNA_MATRIX[rowIndex][colIndex] = dna[rowIndex].charAt(colIndex);
      if (colIndex == LIMIT_POSITION) {
        setMatrixDna(dna, rowIndex + 1, 0);
      } else {
        setMatrixDna(dna, rowIndex, colIndex + 1);
      }
    }
  }

  private static void checkHorizontals(int rowIndex, int colIndex) {

    if (rowIndex > LIMIT_POSITION) {
      logger.info("Chequeo de lineas horizontales finalizado");
    } else {
      if (DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex][colIndex + 1]
          && DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex][colIndex + 2]
          && DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex][colIndex + 3]) {

        SEQUENCE_COUNTER = SEQUENCE_COUNTER + 1;
      }

      if (colIndex == MIDDLE_POSITION && rowIndex <= LIMIT_POSITION) {
        rowIndex = rowIndex + 1;
        colIndex = 0;
      } else {
        colIndex = colIndex + 1;
      }
      checkHorizontals(rowIndex, colIndex);
    }
  }

  private static void checkVerticals(int rowIndex, int colIndex) {
    if (rowIndex > LIMIT_POSITION) {
      logger.info("Chequeo de lineas verticales finalizado");
    } else {
      if (DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex][colIndex + 1]
          && DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex][colIndex + 2]
          && DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex][colIndex + 3]) {

        SEQUENCE_COUNTER = SEQUENCE_COUNTER + 1;
      }

      if (colIndex == MIDDLE_POSITION && rowIndex <= LIMIT_POSITION) {
        rowIndex = rowIndex + 1;
        colIndex = 0;
      } else {
        colIndex = colIndex + 1;
      }
      checkHorizontals(rowIndex, colIndex);
    }
  }

  private static void checkForwardDiagonals(int rowIndex, int colIndex) {
    if (rowIndex > MIDDLE_POSITION) {
      logger.info("Chequeo de lineas oblicuas finalizado");
    } else {
      if (DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex + 1][colIndex + 1]
          && DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex + 2][colIndex + 2]
          && DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex + 3][colIndex + 3]) {

        SEQUENCE_COUNTER = SEQUENCE_COUNTER + 1;
      }

      if (colIndex == MIDDLE_POSITION && rowIndex <= MIDDLE_POSITION) {
        rowIndex = rowIndex + 1;
        colIndex = 0;
      } else {
        colIndex = colIndex + 1;
      }
      checkForwardDiagonals(rowIndex, colIndex);
    }
  }
}
