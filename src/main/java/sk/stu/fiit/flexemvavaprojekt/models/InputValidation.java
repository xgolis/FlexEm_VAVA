package sk.stu.fiit.flexemvavaprojekt.models;

import sk.stu.fiit.flexemvavaprojekt.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Trieda obsahuje funckie na validáciu všetkých vstupov v programe + sql injection validácia.
 *
 * Validácia sql injection prevzatá z:
 * https://github.com/rkpunjal/sql-injection-safe/blob/master/src/main/java/com/github/rkpunjal/sqlsafe/SqlSafeUtil.java
 *
 * @author  Ramakrishna Punjal
 * @version 1.0.0
 * @date   2016-08-26
 *
 */

public class InputValidation {

    private static final String SQL_TYPES =
            "TABLE, TABLESPACE, PROCEDURE, FUNCTION, TRIGGER, KEY, VIEW, MATERIALIZED VIEW, LIBRARY" +
                    "DATABASE LINK, DBLINK, INDEX, CONSTRAINT, TRIGGER, USER, SCHEMA, DATABASE, PLUGGABLE DATABASE, BUCKET, " +
                    "CLUSTER, COMMENT, SYNONYM, TYPE, JAVA, SESSION, ROLE, PACKAGE, PACKAGE BODY, OPERATOR" +
                    "SEQUENCE, RESTORE POINT, PFILE, CLASS, CURSOR, OBJECT, RULE, USER, DATASET, DATASTORE, " +
                    "COLUMN, FIELD, OPERATOR";

    private static final String[] SQL_REGEXPS = {
            "(?i)(.*)(\\b)+(OR|AND)(\\s)+(true|false)(\\s)*(.*)",
            "(?i)(.*)(\\b)+(OR|AND)(\\s)+(\\w)(\\s)*(\\=)(\\s)*(\\w)(\\s)*(.*)",
            "(?i)(.*)(\\b)+(OR|AND)(\\s)+(equals|not equals)(\\s)+(true|false)(\\s)*(.*)",
            "(?i)(.*)(\\b)+(OR|AND)(\\s)+([0-9A-Za-z_'][0-9A-Za-z\\d_']*)(\\s)*(\\=)(\\s)*([0-9A-Za-z_'][0-9A-Za-z\\d_']*)(\\s)*(.*)",
            "(?i)(.*)(\\b)+(OR|AND)(\\s)+([0-9A-Za-z_'][0-9A-Za-z\\d_']*)(\\s)*(\\!\\=)(\\s)*([0-9A-Za-z_'][0-9A-Za-z\\d_']*)(\\s)*(.*)",
            "(?i)(.*)(\\b)+(OR|AND)(\\s)+([0-9A-Za-z_'][0-9A-Za-z\\d_']*)(\\s)*(\\<\\>)(\\s)*([0-9A-Za-z_'][0-9A-Za-z\\d_']*)(\\s)*(.*)",
            "(?i)(.*)(\\b)+SELECT(\\b)+\\s.*(\\b)(.*)",
//            "(?i)(.*)(\\b)+SELECT(\\b)+\\s.*(\\b)+FROM(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+INSERT(\\b)+\\s.*(\\b)+INTO(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+UPDATE(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+DELETE(\\b)+\\s.*(\\b)+FROM(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+UPSERT(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+SAVEPOINT(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+CALL(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+ROLLBACK(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+KILL(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+DROP(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+CREATE(\\b)+(\\s)*(" + SQL_TYPES.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+ALTER(\\b)+(\\s)*(" + SQL_TYPES.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+TRUNCATE(\\b)+(\\s)*(" + SQL_TYPES.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+LOCK(\\b)+(\\s)*(" + SQL_TYPES.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+UNLOCK(\\b)+(\\s)*(" + SQL_TYPES.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+RELEASE(\\b)+(\\s)*(" + SQL_TYPES.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+DESC(\\b)+(\\w)*\\s.*(.*)",
            "(?i)(.*)(\\b)+DESCRIBE(\\b)+(\\w)*\\s.*(.*)",
            "(.*)(/\\*|\\*/|;){1,}(.*)",
            "(.*)(-){2,}(.*)",

    };


    // pre-build the Pattern objects for faster validation
    // private static final List<Pattern> validationPatterns = buildValidationPatterns();
    private static final List<Pattern> validationPatterns = buildPatterns(SQL_REGEXPS);

    /**
     * Determines if the provided string value is SQL-Injection-safe.
     * <p>
     * @param  dataString  string na validáciu
     * @return   'true' ak je bezpečný 'false' ak je zamietnutý
     */
    public static boolean isSqlInjectionSafe(String dataString){
        if(isEmpty(dataString)){
            return true;
        }

        for(Pattern pattern : validationPatterns){
            if(matches(pattern, dataString)){
                Main.logger.log(Level.WARNING, "Validation if string"+dataString+" is not sql injection safe");
                return false;
            }
        }
        return true;
    }

    private static boolean matches(Pattern pattern, String dataString){
        Matcher matcher = pattern.matcher(dataString);
        return matcher.matches();
    }

    private static List<Pattern> buildPatterns(String[] expressionStrings){
        List<Pattern> patterns = new ArrayList<Pattern>();
        for(String expression : expressionStrings){
            patterns.add(getPattern(expression));
        }
        return patterns;
    }


    private static Pattern getPattern(String regEx){
        return Pattern.compile(regEx, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    }

    private static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * Statické premenné obsahujúce regexy na validáciu jednotlivých vstupov
     */

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_USERNAME_REGEX =
            Pattern.compile("^[a-zA-Z0-9._-]{6,}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_NAME_REGEX =
            Pattern.compile("(?i)(^[a-z\\u00C0-\\u024F])((?![ .,'-]$)[a-z\\u00C0-\\u024F .,'-]){1,24}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_PHONE_REGEX =
            Pattern.compile("\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$", Pattern.CASE_INSENSITIVE);


    private static final Pattern VALID_REVIEW_REGEX =
            Pattern.compile(".{1,60}", Pattern.DOTALL);

    private static final Pattern VALID_EXERCISE_REGEX =
            Pattern.compile(".{2,20}", Pattern.DOTALL);

    private static final Pattern VALID_DATE_REGEX =
            Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    private static final Pattern VALID_NAME_FILTER_REGEX =
            Pattern.compile("^[a-zA-Z\\u00C0-\\u024F]*$");

    private static final Pattern VALID_REGID_FILTER_REGEX =
            Pattern.compile("^[0-9]*$");
    //^(\+0?1\s)?\(?\d{4}\)?[\s.-]\d{3}[\s.-]\d{3}$

//    \+(9[976]\d|8[987530]\d|6[987]\d|5[90]\d|42\d|3[875]\d|
//    2[98654321]\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|
//    4[987654310]|3[9643210]|2[70]|7|1)\d{1,14}$

    //\d{4}[\s]\d{3}[\s ]\d{3}$

    /**
     * Vzorová funkcia validácie vstupu
     * Kontroluje sa pomocou Patternu uvedeného vyššie.
     * Ak matcher nájde zhodu medzi patternom a emailom string je validovaný.
     * @param emailStr Email ktorý chceme validovať
     * @return 'true' - validný email 'false' - invalidný email
     */

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validateUsername(String usernameStr) {
        Matcher matcher = VALID_USERNAME_REGEX.matcher(usernameStr);
        return matcher.find();
    }

    public static boolean validatePassword(String passwordStr) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passwordStr);
        return matcher.find();
    }

    public static boolean validateExercise(String exercise){
        Matcher matcher = VALID_EXERCISE_REGEX.matcher(exercise);
        return matcher.find();
    }

    public static boolean validateName(String firstnameStr) {
        Matcher matcher = VALID_NAME_REGEX.matcher(firstnameStr);
        return matcher.find();
    }

    public static boolean validatePhone(String phoneNumberStr) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(phoneNumberStr);
        return matcher.find();
    }

    public static boolean validateStars(String stars) {
        try {
            int number = Integer.parseInt(stars);
            return number > 0 && number <= 10;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean validateReview(String review) {
        Matcher matcher = VALID_REVIEW_REGEX.matcher(review);
        return matcher.find();
    }

    public static boolean validateDate(String date) {
        Matcher matcher = VALID_DATE_REGEX.matcher(date);
        return matcher.find();
    }

    public static boolean validateNameFilter(String name) {
        Matcher matcher = VALID_NAME_FILTER_REGEX.matcher(name);
        return matcher.find();
    }

    public static boolean validateREGIDFilter(String regid) {
            Matcher matcher = VALID_REGID_FILTER_REGEX.matcher(regid);
        return matcher.find();
    }

    public static boolean validateDigit(String digit) {
        try {
            int number = Integer.parseInt(digit);
            return true;
        }catch (Exception e){
            return false;
        }
    }






}
