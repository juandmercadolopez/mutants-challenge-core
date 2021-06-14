package mercadolibre.challenge.core.util;

import mercadolibre.challenge.core.constant.AppConstant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

  public static boolean validateDnaRowSequenceCharacters(String dnaRowSequence) {
    Pattern p = Pattern.compile(AppConstant.REGEX_CHARACTERS_ALLOWED);
    Matcher m = p.matcher(dnaRowSequence);
    return m.matches();
  }
}
