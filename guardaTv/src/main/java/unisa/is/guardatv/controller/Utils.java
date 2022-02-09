package unisa.is.guardatv.controller;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import static unisa.is.guardatv.controller.Constants.INVALID_INT_VALUE;

public class Utils {


    private static Utils instance;

    public static synchronized Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    /**
     * <pre>
     * Checks if a string is a valid path.
     * Null safe.
     *
     * Calling examples:
     *    isValidPath("c:/test");      //returns true
     *    isValidPath("c:/te:t");      //returns false
     *    isValidPath("c:/te?t");      //returns false
     *    isValidPath("c/te*t");       //returns false
     *    isValidPath("good.txt");     //returns true
     *    isValidPath("not|good.txt"); //returns false
     *    isValidPath("not:good.txt"); //returns false
     * </pre>
     */
    public boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }

    private String getFileExtension(String filePath) {
        return FilenameUtils.getExtension(filePath);
    }

    public boolean isValidExtension(String filePath, List<String> validExtensions) {
        return validExtensions.contains(getFileExtension(filePath));
    }

    public int getInt(String num) {
        int number;
        try {
            number = Integer.parseInt(num);
            if (number <= 0) {
                return INVALID_INT_VALUE;
            }
        } catch (Exception e) {
            return INVALID_INT_VALUE;
        }
        return number;
    }

    public boolean checkStringLength(String str, int min, int max) {
        return isValidString(str) && str.length() >= min && str.length() <= max;
    }


    public boolean checkStringLength(String str, int min, int max, String regex) {
        return isValidString(str, regex) && str.length() >= min && str.length() <= max;
    }

    public Date getDate(String date, String format) {
        Date dateRet;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            dateRet = Date.valueOf(date);
            simpleDateFormat.format(dateRet);
        } catch (Exception e) {
            return null;
        }
        return dateRet;
    }

    public boolean isValidString(String str, String regex) {
        return str != null && !str.equals("") && Pattern.compile(regex).matcher(str).find();
    }

    public boolean isValidString(String str) {
        return str != null && !str.equals("");
    }
}


