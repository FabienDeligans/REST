package controleurs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import outils.Utilitaire;
import session.*;
import dal.*;
import javax.servlet.http.HttpSession;

/**
 * @author Alain Arsane
 */
public class SrvlReservation extends HttpServlet {

    private String erreur;
    @EJB
    OeuvreFacade oeuvreF;
    @EJB
    ReservationFacade reservationF;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String demande;
        String vueReponse = "/home.jsp";
        erreur = null;
        try {
            demande = getDemande(request);
            if (demande.equalsIgnoreCase("reserver.res")) {
                vueReponse = reserverOeuvre(request);
            } else if (demande.equalsIgnoreCase("enregistrerReservation.res")) {
                vueReponse = enregistrerReservation(request);
            }else if (demande.equalsIgnoreCase("getReservations.res")) {
                vueReponse = getReservations(request);
            } else if (demande.equalsIgnoreCase("confirmerReservation.res")) {
                vueReponse = confirmerReservation(request);
            } else if (demande.equalsIgnoreCase("supprimerReservation.res")) {
                vueReponse = supprimerReservation(request);
            }
        } catch (Exception e) {
            erreur = Utilitaire.getExceptionCause(e);
        } finally {
            request.setAttribute("erreurR", erreur);
            request.setAttribute("pageR", vueReponse);
            RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp");
            if (vueReponse.contains(".res")) {
                dsp = request.getRequestDispatcher(vueReponse);
            }
            dsp.forward(request, response);
        }
    }
    
    /**
     * Passe une réservation de l'état En attente
     * à l'état Confirmée
     * @param request
     * @return String demande de redirection
     * @throws Exception
     */
    private String confirmerReservation(HttpServletRequest request) throws Exception {
        try {
            int idOeuvre = Integer.parseInt(request.getParameter("id"));
            String dateReservation = request.getParameter("dateres").replace("'", "");
            reservationF.modifyReservation(dateReservation, idOeuvre);
            return ("getReservations.res");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Supprime une réservation
     * @param request
     * @return demande de redirection
     * @throws Exception 
     */
    private String supprimerReservation(HttpServletRequest request) throws Exception {
        try {
            int idOeuvre = Integer.parseInt(request.getParameter("id"));
            String dateReservation = request.getParameter("dateres").replace("'", "");
            reservationF.deleteReservation(dateReservation, idOeuvre);
            return ("getReservations.res");
        } catch (Exception e) {         
            throw e;
        }
    }      
    
    /**
     * Liste des réseravation
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String getReservations(HttpServletRequest request) throws Exception {
        List<Reservation> lstReservationsE;
        try {
            lstReservationsE = reservationF.getReservations();
            request.setAttribute("lstReservationsR", lstReservationsE);
            return ("/listeReservations.jsp");
        } catch (Exception e) {
            throw e;
        }
    }    

    /**
     * Ajoute une réservation avec le statut En attente
     * @param request
     * @return
     * @throws Exception
     */
    private String enregistrerReservation(HttpServletRequest request) throws Exception {
        Date dateReservation = null;
        String titre = "";
        try {
            int idOeuvre = Integer.parseInt(request.getParameter("id"));
            Oeuvre oeuvreE = oeuvreF.getOeuvreById(idOeuvre);
            titre = oeuvreE.getTitre();
            String date = request.getParameter("dateReservation");
            dateReservation = Utilitaire.StrToDate(date, "yyyy-MM-dd");
            HttpSession session = request.getSession(true);
            int idAdherent = (Integer) session.getAttribute("userIdS");
//            reservationF.addReservationV1(dateReservation, idOeuvre, idAdherent);            
//            reservationF.addReservationV2(date, idOeuvre, idAdherent);
            reservationF.addReservationV3(date, idOeuvre, idAdherent);
            return ("getReservations.res");
        } catch (Exception e) {
            erreur = Utilitaire.getExceptionCause(e);
            if (erreur.contains("PRIMARY")) {
                erreur = "L'oeuvre " + titre + " a déjà été réservée pour le : " + Utilitaire.DateToStr(dateReservation, "dd/MM/yyyy") + " !";
            }
            throw new Exception(erreur);
        }
    }

    /**
     * Lecture de l'oeuvre sélectionnée et dépôt dans le contexte Request
     * @param request
     * @return
     * @throws Exception
     */
    private String reserverOeuvre(HttpServletRequest request) throws Exception {
        try {
            int idOeuvre = Integer.parseInt(request.getParameter("id"));
            Oeuvre oeuvreE = oeuvreF.getOeuvreById(idOeuvre);
            request.setAttribute("oeuvreR", oeuvreE);
            return ("/reservation.jsp");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Extrait le texte de la demande de l'URL
     *
     * @param request
     * @return String texte de la demande
     */
    private String getDemande(HttpServletRequest request) {
        String demande = "";
        demande = request.getRequestURI();
        demande = demande.substring(demande.lastIndexOf("/") + 1);
        return demande;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
