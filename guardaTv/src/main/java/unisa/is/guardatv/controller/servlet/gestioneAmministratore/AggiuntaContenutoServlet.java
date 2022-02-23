package unisa.is.guardatv.controller.servlet.gestioneAmministratore;

import unisa.is.guardatv.StorageLayer.*;
import unisa.is.guardatv.controller.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.SyncFailedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static unisa.is.guardatv.controller.Constants.*;


/**
 * Servlet implementation class AggiuntaContenuto
 */
@WebServlet(name = "AggiuntaContenuto", urlPatterns = "/aggiunto-contenuto")
@MultipartConfig
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //controllo utente amministratore
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Utente non loggato.");
        }

        if (!utente.getAdministrator()) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Non hai i permessi necessari");
        }

        String id = request.getParameter("contenutoId");
        // controllo l'id in input che rispetti la regex definita in precedenza
        if (!Pattern.compile(ID_REGEX).matcher(id).find()) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("ID non valido.");
        }

        String titolo = request.getParameter("titolo");
        // controllo il titolo in input che rispetti lunghezza prefissata
        if (!Utils.getInstance().checkStringLength(titolo, MIN_TITLE_LENGTH, MAX_TITLE_LENGTH)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Titolo non valido.");
        }


        String descrizione = request.getParameter("descrizione");
        // controllo il descrizione in input che rispetti lunghezza prefissata
        if (!Utils.getInstance().checkStringLength(descrizione, MIN_DESCRIPTION_LENGTH, MAX_DESCRIPTION_LENGTH)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Descrizione non valida.");
        }


        String genere = request.getParameter("genere");
        // controllo il genere in input che rispetti lunghezza prefissata
        if (!Utils.getInstance().checkStringLength(genere, MIN_GENRE_LENGTH, MAX_GENRE_LENGTH, REGEX_AZazCOMMA)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Genere non valido.");
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
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Regista non valido.");
        }

        int durata = Utils.getInstance().getInt(request.getParameter("durata"));
        // Controllo che la durata abbia un valore corretto
        if (durata == INVALID_INT_VALUE) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Durata non valida.");
        }

        Date dataDiUscita = Utils.getInstance().getDate(request.getParameter("dataDiUscita"), DATE_FORMAT);
        // Controllo che la data di uscita si in un formato corretto
        if (dataDiUscita == INVALID_DATE_VALUE) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Data di uscita non valida.");
        }

        Part filePart = request.getPart("immagine");
        String pathImmagine=null;
        if(filePart != null){
            pathImmagine = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if((pathImmagine.compareTo("")==0) || (pathImmagine==null))
	        pathImmagine = id+".jpg";
            // Controllo che il path dell'immagine sia valido
            if (!Utils.getInstance().isValidPath(pathImmagine) || !Utils.getInstance().isValidExtension(pathImmagine, IMAGE_EXTENSIONS)) {
                throw new unisa.is.guardatv.controller.servlet.MyServletException("Immagine non valida.");
            }

        }


        String pathTrailer = request.getParameter("trailer");

        // Recupero il parametro film
        boolean film = request.getParameter("categoria").compareTo("film") == 0;

        int puntate = Utils.getInstance().getInt(request.getParameter("puntate"));
        // Controllo che il parametro puntate abbia un valore corretto
        if ((!film) && (puntate == INVALID_INT_VALUE)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Numero puntate non valido.");
        }

        int stagioni = Utils.getInstance().getInt(request.getParameter("stagioni"));
        // Controllo che il parametro puntate abbia un valore corretto
        if ((!film) && (stagioni == INVALID_INT_VALUE)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Numero stagioni non valido.");
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
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Errore nel salvataggio del contenuto.");
        }

        try{
            String destinazione = CARTELLA_UPLOAD + File.separator + pathImmagine;
            Path pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));

            InputStream fileInputStream = filePart.getInputStream();
            // crea CARTELLA_UPLOAD, se non esiste
            Files.createDirectories(pathDestinazione.getParent());
            // scrive il file
            Files.copy(fileInputStream, pathDestinazione);
        }
        catch (Exception e){
            System.err.println("ERRORE CARICAMENTO IMMAGINE");
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
                throw new unisa.is.guardatv.controller.servlet.MyServletException("Errore nel salataggio della tipologia.");
            }
        }


        request.setAttribute("notifica", "Contenuto aggiunto con successo");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/profilo.jsp");
        requestDispatcher.forward(request, response);
    }
}



