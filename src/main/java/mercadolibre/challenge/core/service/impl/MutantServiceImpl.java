package mercadolibre.challenge.core.service.impl;

import mercadolibre.challenge.core.exception.DnaException;
import mercadolibre.challenge.core.service.MutantService;
import mercadolibre.challenge.core.util.Util;

import java.io.IOException;

public class MutantServiceImpl implements MutantService {

    private static final int ARRAY_LENGTH = 6;
    private static char DNA_MATRIX[][] = new char[ARRAY_LENGTH][ARRAY_LENGTH];
    private static final int LIMIT_POSITION = ARRAY_LENGTH - 1;
    private static final int MIDDLE_POSITION = (ARRAY_LENGTH/2) - 1;
    private static int START_INDEX = 0;
    private static int SEQUENCE_COUNTER = 0;

    @Override
    public boolean isMutant(String[] dna) throws DnaException, IOException {
        validateCharacterSequences(dna);
        setMatrixDna(dna, START_INDEX, START_INDEX);

        checkHorizontals(START_INDEX, START_INDEX);
        checkVerticals(START_INDEX, START_INDEX);
        checkForwardDiagonals(START_INDEX, START_INDEX);

        System.out.println(SEQUENCE_COUNTER);

        if(SEQUENCE_COUNTER > 1){
            return true;
        }

        return false;

    }

    public void validateCharacterSequences(String[] dna) throws IOException, DnaException {
        for (String str : dna) {
            if(!Util.validateDnaRowSequenceCharacters(str)){
                throw new DnaException(Util.getProperty("app.exception.dnaexception.message"));
            }
        }
    }

    static void setMatrixDna(String[] dna, int rowIndex, int colIndex){
        if(rowIndex > LIMIT_POSITION){
            resetIndex();
        }else{
            DNA_MATRIX[rowIndex][colIndex] = dna[rowIndex].charAt(colIndex);
            if(colIndex == LIMIT_POSITION){
                setMatrixDna(dna, rowIndex + 1, 0);
            }else{
                setMatrixDna(dna, rowIndex, colIndex + 1);
            }
        }
    }

    private static void checkHorizontals(int rowIndex, int colIndex){
        if(rowIndex > LIMIT_POSITION){
            resetIndex();
        }else{
            if(DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex][colIndex + 1]
                    && DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex][colIndex + 2]
                    && DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex][colIndex + 3]){

                SEQUENCE_COUNTER = SEQUENCE_COUNTER + 1;
            }

            if(colIndex == MIDDLE_POSITION && rowIndex <= LIMIT_POSITION){
                rowIndex = rowIndex + 1;
                colIndex = 0;
            }else{
                 colIndex = colIndex + 1;
            }
            checkHorizontals(rowIndex, colIndex);
        }
    }


    private static void checkVerticals(int rowIndex, int colIndex){
        if(rowIndex > LIMIT_POSITION){
            resetIndex();
        }else{
            if(DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex][colIndex + 1]
                    && DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex][colIndex + 2]
                    && DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex][colIndex + 3]){

                SEQUENCE_COUNTER = SEQUENCE_COUNTER + 1;
            }

            if(colIndex == MIDDLE_POSITION && rowIndex <= LIMIT_POSITION){
                rowIndex = rowIndex + 1;
                colIndex = 0;
            }else{
                colIndex = colIndex + 1;
            }
            checkHorizontals(rowIndex, colIndex);
        }
    }

    private static void checkForwardDiagonals(int rowIndex, int colIndex){
        if(rowIndex > MIDDLE_POSITION){
            resetIndex();
        }else{
            if(DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex + 1][colIndex + 1]
                    && DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex + 2][colIndex + 2]
                    && DNA_MATRIX[rowIndex][colIndex] == DNA_MATRIX[rowIndex + 3][colIndex + 3]){

                SEQUENCE_COUNTER = SEQUENCE_COUNTER + 1;
            }

            if(colIndex == MIDDLE_POSITION && rowIndex <= MIDDLE_POSITION){
                rowIndex = rowIndex + 1;
                colIndex = 0;
            }else{
                colIndex = colIndex + 1;
            }
            checkForwardDiagonals(rowIndex, colIndex);
        }
    }

    private static void resetIndex(){
        START_INDEX = 0;
    }

}
