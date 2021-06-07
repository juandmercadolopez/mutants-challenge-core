package mercadolibre.challenge.core.util;

import mercadolibre.challenge.core.constant.AppConstant;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static String getProperty(String property) throws IOException {

        ResourceBundle bundle = ResourceBundle.getBundle("Application");

        return bundle.getString(property);

    }

    public static boolean validateDnaRowSequenceCharacters(String dnaRowSequence){
        Pattern p = Pattern.compile(AppConstant.REGEX_CHARACTERS_ALLOWED);
        Matcher m = p.matcher(dnaRowSequence);
        return m.matches();
    }
}
