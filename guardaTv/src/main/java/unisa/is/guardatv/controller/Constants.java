package unisa.is.guardatv.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Constants {
    public static final List<String> IMAGE_EXTENSIONS = Arrays.asList("png", "jpeg", "jpg");
    public static final List<String> VIDEO_EXTENSIONS = Arrays.asList("mp4", "mkv");
    /**
     * messaggio di errore inviato in caso di bad request.
     **/
    public static final String BAD_REQUEST_MESS = "L'operazione richiesta non e' valida.";

    /**
     * messaggio restituito in caso di successo dell'operazione.
     **/
    public static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";

    public static final String ID_REGEX = "^[A-Z0-9]{4}-[A-Z0-9]{4}-[A-Z0-9]{4}$|^[A-Z0-9]{4}-[A-Z0-9]{4}-[A-Z0-9]{4}-[A-Z0-9]{4}-[A-Z0-9]{1}-[A-Z0-9]{4}-[A-Z0-9]{4}-[A-Z0-9]{1}$";

    public static final String REGEX_AZaz09 = "^[A-Za-z0-9\\s]+$";
    public static final String REGEX_AZazCOMMA = "^[A-Za-z,\\s]+$";
    public static final String REGEX_DEFAULT = "";

    public static final int MIN_TITLE_LENGTH = 1;
    public static final int MAX_TITLE_LENGTH = 50;
    public static final int MIN_DESCRIPTION_LENGTH = 1;
    public static final int MAX_DESCRIPTION_LENGTH = 255;
    public static final int MIN_GENRE_LENGTH = 1;
    public static final int MAX_GENRE_LENGTH = 50;
    public static final int MIN_DIRECTOR_LENGTH = 1;
    public static final int MAX_DIRECTOR_LENGTH = 50;
    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 50;
    public static final int MIN_SEARCH_LENGTH = 1;
    public static final int MAX_SEARCH_LENGTH = 50;

    public static final int INVALID_INT_VALUE = -1;
    public static final Date INVALID_DATE_VALUE = null;
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String CARTELLA_UPLOAD = "src/main/webapp/img/contenuti";
}
