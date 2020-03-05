package controleurs;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import outils.Utilitaire;
import dal.*;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import session.*;

/**
 * @author Alain Arsane
 */
public class SrvlOeuvre extends HttpServlet {

    private String erreur;
    @EJB
    OeuvreFacade oeuvreF;
    @EJB
    UtilisateurFacade utilisateurF;

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
            if (demande.equalsIgnoreCase("catalogue.oe")) {
                vueReponse = getOeuvres(request);
            } else if (demande.equalsIgnoreCase("myCatalogue.oe")) {
                vueReponse = getMyOeuvres(request);
            } else if (demande.equalsIgnoreCase("ajouter.oe")) {
                vueReponse = creerOeuvre(request);
            } else if (demande.equalsIgnoreCase("enregistrer.oe")) {
                vueReponse = enregistrerOeuvre(request);
            } else if (demande.equalsIgnoreCase("modifier.oe")) {
                vueReponse = modifierOeuvre(request);
            } else if (demande.equalsIgnoreCase("supprimer.oe")) {
                vueReponse = supprimerOeuvre(request);
            }
        } catch (Exception e) {
            erreur = Utilitaire.getExceptionCause(e);
        } finally {
            request.setAttribute("erreurR", erreur);
            request.setAttribute("pageR", vueReponse);
            RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp");
            if (vueReponse.contains(".oe")) {
                dsp = request.getRequestDispatcher(vueReponse);
            }
            dsp.forward(request, response);
        }
    }
    
    /**
     * Supprimer une oeuvre
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String supprimerOeuvre(HttpServletRequest request) throws Exception {
        Oeuvre oeuvreE = null;
        String titre = "";
        try {
            int idOeuvre = Integer.parseInt(request.getParameter("id"));
            oeuvreE = oeuvreF.getOeuvreById(idOeuvre);
            titre = oeuvreE.getTitre();
            oeuvreF.deleteOeuvre(idOeuvre);
            String vueReponse = "catalogue.oe";
            return (vueReponse);
        } catch (Exception e) {
            erreur = Utilitaire.getExceptionCause(e);
            if (erreur.contains("FK_RESERVATION_OEUVRE")) {
                erreur = "Il n'est pas possible de supprimer l'oeuvre : " + titre + " car elle a été réservée !";
            }
            throw new Exception(erreur);
        }
    }    

    /**
     * Lit et affiche une oeuvre pour pouvoir la modifier
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String modifierOeuvre(HttpServletRequest request) throws Exception {
        try {
            int idOeuvre = Integer.parseInt(request.getParameter("id"));
            Oeuvre oeuvreE = oeuvreF.getOeuvreById(idOeuvre);
            request.setAttribute("oeuvreR", oeuvreE);
            request.setAttribute("titre", "Modifier une oeuvre");
            String vueReponse = "/oeuvre.jsp";
            return (vueReponse);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Affiche le formulaire vide d'une oeuvre Initialise le titre de la page
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String creerOeuvre(HttpServletRequest request) throws Exception {

        String vueReponse;
        try {
            Oeuvre oeuvreE = new Oeuvre();
            oeuvreE.setIdOeuvre(0);
            request.setAttribute("oeuvreR", oeuvreE);
            request.setAttribute("titre", "Créer une oeuvre");
            vueReponse = "/oeuvre.jsp";
            return (vueReponse);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Enregistre une oeuvre qui a été soit créée (id_oeuvre = 0) soit modifiée
     * (id_oeuvre > 0)
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String enregistrerOeuvre(HttpServletRequest request) throws Exception {
        try {
            int idOeuvre = Integer.parseInt(request.getParameter("id"));
            String titre = request.getParameter("titre");
            BigDecimal prix = new BigDecimal(request.getParameter("prix"));
            HttpSession session = request.getSession(true);
            int idUtilisateur = (Integer) session.getAttribute("userIdS");
            if (idOeuvre > 0) {
                oeuvreF.modifyOeuvre(idOeuvre, titre, prix, idUtilisateur);
            } else {
                oeuvreF.addOeuvre(titre, prix, idUtilisateur);
            }
            String vueReponse = "catalogue.oe";
            return (vueReponse);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Liste des oeuvres d'un propriétaire
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String getMyOeuvres(HttpServletRequest request) throws Exception {
        try {
            HttpSession session = request.getSession(true);
            int userId = (Integer) session.getAttribute("userIdS");
            List<Oeuvre> lstOeuvresE = oeuvreF.getOeuvresByUtilisateur( userId);
            request.setAttribute("lstOeuvresR", lstOeuvresE);
            return ("/myCatalogue.jsp");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Liste de toutes les oeuvres
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String getOeuvres(HttpServletRequest request) throws Exception {
        List<Oeuvre> lstOeuvresE;
        try {
            lstOeuvresE = oeuvreF.getOeuvres();
            request.setAttribute("lstOeuvresR", lstOeuvresE);
            return ("/catalogue.jsp");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Extrait le texte de la demande de l'URL
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
