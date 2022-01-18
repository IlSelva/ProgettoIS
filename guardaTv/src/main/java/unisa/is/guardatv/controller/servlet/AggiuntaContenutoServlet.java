package unisa.is.guardatv.controller.servlet;

import unisa.is.guardatv.StorageLayer.*;
import unisa.is.guardatv.controller.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static unisa.is.guardatv.controller.Constants.*;


/**
 * Servlet implementation class AggiuntaContenuto
 */
@WebServlet(name = "AggiuntaContenuto", urlPatterns = "/aggiunto-contenuto")
public class AggiuntaContenutoServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiuntaContenutoServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        // controllo l'id in input che rispetti la regex definita in precedenza
        if (!Pattern.compile(ID_REGEX).matcher(id).find()) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        String titolo = request.getParameter("titolo");
        // controllo il titolo in input che rispetti lunghezza prefissata
        if (!Utils.getInstance().checkStringLength(titolo, MIN_TITLE_LENGTH, MAX_TITLE_LENGTH)) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }


        String descrizione = request.getParameter("descrizione");
        // controllo il descrizione in input che rispetti lunghezza prefissata
        if (!Utils.getInstance().checkStringLength(descrizione, MIN_DESCRIPTION_LENGTH, MAX_DESCRIPTION_LENGTH)) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }


        String genere = request.getParameter("genere");
        // controllo il genere in input che rispetti lunghezza prefissata
        if (!Utils.getInstance().checkStringLength(genere, MIN_GENRE_LENGTH, MAX_GENRE_LENGTH)) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        // Divido la stringa genere per la virgola es. azione, commedia -> ["azione", " commedia"]
        // Successivamente elimino gli spazi bianchi e creo un oggetto di classe Genere per ogni elemento
        // della lista
        List<Genere> generi = Arrays.stream(genere.split(",")).map(gen -> {
            Genere newGen = new Genere();
            newGen.setNome(gen.replaceAll(" ", ""));
            return newGen;
        }).collect(Collectors.toList());

        String regista = request.getParameter("regista");
        // controllo il regista in input che rispetti lunghezza prefissata
        if (!Utils.getInstance().checkStringLength(regista, MIN_DIRECTOR_LENGTH, MAX_DIRECTOR_LENGTH)) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        int durata = Utils.getInstance().getInt(request.getParameter("durata"));
        // Controllo che la durata abbia un valore corretto
        if (durata == INVALID_INT_VALUE) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        Date dataDiUscita = Utils.getInstance().getDate(request.getParameter("dataDiUscita"), DATE_FORMAT);
        // Controllo che la data di uscita si in un formato corretto
        if (dataDiUscita == INVALID_DATE_VALUE) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        String pathImmagine = request.getParameter("immagine");
        // Controllo che il path dell'immagine sia valido
        if (!Utils.getInstance().isValidPath(pathImmagine) || !Utils.getInstance().isValidExtension(pathImmagine, IMAGE_EXTENSIONS)) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        String pathTrailer = request.getParameter("trailer");
        // Se il path del trailer è presente (!= null) controllo che sia un path valido
        if (Utils.getInstance().isValidString(pathTrailer) && (!Utils.getInstance().isValidPath(pathTrailer) || !Utils.getInstance().isValidExtension(pathImmagine, VIDEO_EXTENSIONS))) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }


        // Recupero il parametro film
        boolean film = Boolean.parseBoolean(request.getParameter("puntate"));

        int puntate = Utils.getInstance().getInt(request.getParameter("puntate"));
        // Controllo che il parametro puntate abbia un valore corretto
        if (puntate == INVALID_INT_VALUE) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        int stagioni = Utils.getInstance().getInt(request.getParameter("stagioni"));
        // Controllo che il parametro puntate abbia un valore corretto
        if (stagioni == INVALID_INT_VALUE) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }



        // Creo il contenuto se non esistente
        ContenutoDAO contenutoDAO = new ContenutoDAO();
        Contenuto contenuto = new Contenuto();
        contenuto.setId(id);
        contenuto.setTitolo(titolo);
        contenuto.setDescrizione(descrizione);
        contenuto.setDurata(durata);
        contenuto.setRegista(regista);
        contenuto.setDataDiUscita(dataDiUscita);
        contenuto.setImmagineDelContenuto(pathImmagine);
        contenuto.setFilm(film);
        contenuto.setPuntate(puntate);
        contenuto.setStagioni(stagioni);

        if (Utils.getInstance().isValidString(pathTrailer)) {
            contenuto.setVideoTrailer(pathTrailer);
        }

        try {
            contenutoDAO.doSave(contenuto);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        // Creo la tipologia ovvero contenuto -> genere

        // Creo i generi se non esistenti
        GenereDAO genereDAO = new GenereDAO();
        TipologiaDAO tipologiaDAO = new TipologiaDAO();

        for (Genere gen : generi) {
            try {
                genereDAO.doSave(gen);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("POSSIBILE CHE IL GENERE SIA GIA PRESENTE");
            }

            Tipologia tipologia = new Tipologia();

            tipologia.setContenuto(contenuto.getId());
            tipologia.setGenere(gen.getNome());

            try {
                tipologiaDAO.doSave(tipologia);
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write(BAD_REQUEST_MESS);
                return;
            }
        }


        response.getWriter().write("ok");
    }
}



