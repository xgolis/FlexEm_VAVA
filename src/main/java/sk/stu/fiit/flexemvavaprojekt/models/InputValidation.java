package sk.stu.fiit.flexemvavaprojekt.models;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * Empty value is considered safe.
     * This is used in by the SqlInjectionSafe annotation.
     *
     * @param  dataString  string value that is to verified
     * @return   'true' for safe and 'false' for unsafe
     */
    public static boolean isSqlInjectionSafe(String dataString){
        if(isEmpty(dataString)){
            return true;
        }

        for(Pattern pattern : validationPatterns){
            if(matches(pattern, dataString)){
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

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_USERNAME_REGEX =
            Pattern.compile("^[a-zA-Z0-9._-]{6,}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_NAME_REGEX =
            Pattern.compile("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){1,24}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_PHONE_REGEX =
            Pattern.compile("\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_STAR_REGEX =
            Pattern.compile("[1-9]|10", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_DIGIT_REGEX =
            Pattern.compile("\\\\d+");

    private static final Pattern VALID_REVIEW_REGEX =
            Pattern.compile(".{1,60}", Pattern.DOTALL);

    private static final Pattern VALID_EXERCISE_REGEX =
            Pattern.compile(".{2,20}", Pattern.DOTALL);

    private static final Pattern VALID_DATE_REGEX =
            Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    //^(\+0?1\s)?\(?\d{4}\)?[\s.-]\d{3}[\s.-]\d{3}$

//    \+(9[976]\d|8[987530]\d|6[987]\d|5[90]\d|42\d|3[875]\d|
//    2[98654321]\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|
//    4[987654310]|3[9643210]|2[70]|7|1)\d{1,14}$

    //\d{4}[\s]\d{3}[\s ]\d{3}$


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

    public static boolean validateDigit(String digit) {
        try {
            int number = Integer.parseInt(digit);
            return true;
        }catch (Exception e){
            return false;
        }
    }






}
