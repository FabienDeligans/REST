package controleurs;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dal.*;
import session.*;
import javax.ejb.EJB;
import outils.Utilitaire;

/**
 * @author Alain Arsane
 */
public class SrvlLogin extends HttpServlet {

    private String erreur;
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
            if (demande.equalsIgnoreCase("login.log")) {
                vueReponse = login(request);
            } else if (demande.equalsIgnoreCase("connecter.log")) {
                vueReponse = connecter(request);
            } else if (demande.equalsIgnoreCase("deconnecter.log")) {
                vueReponse = deconnecter(request);
            }
        } catch (Exception e) {
            erreur = Utilitaire.getExceptionCause(e);
        } finally {
            request.setAttribute("erreurR", erreur);
            request.setAttribute("pageR", vueReponse);
            RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp");
            if (vueReponse.contains(".log")) {
                dsp = request.getRequestDispatcher(vueReponse);
            }
            dsp.forward(request, response);
        }
    }

    /**
     * Afficher le formulaire de login
     *
     * @param request
     * @return
     * @throws Exception
     */
    private String login(HttpServletRequest request) throws Exception {
        try {
            return ("/login.jsp");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Vérifie que l'utilisateur a saisi le bon login et mot de passe
     *
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String connecter(HttpServletRequest request) throws Exception {
        String login, pwd, vueReponse;
        int userId;
        try {
            login = request.getParameter("login");
            pwd = request.getParameter("pwd");

            Utilisateur utilisateurE = utilisateurF.getUtilisateurByLogin(login, pwd);
            if (utilisateurE != null) {
                HttpSession session = request.getSession(true);
                userId = utilisateurE.getIdUtilisateur();
                session.setAttribute("userIdS", userId);
                if (utilisateurE.getRole().compareTo("Propriétaire") == 0){
                    session.setAttribute("userTypeS", "P");
                }else {
                    session.setAttribute("userTypeS", "A");
                }
                vueReponse = "/home.jsp";
            } else {
                vueReponse = "/login.jsp";
                erreur = "Login ou mot de passe inconnu(s) !";
            }

            return (vueReponse);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Déconnecter l'utilisateur en fermant sa session
     *
     * @param request
     * @return
     * @throws Exception
     */
    private String deconnecter(HttpServletRequest request) throws Exception {
        try {
            HttpSession session = request.getSession(true);
            session.setAttribute("userIdS", null);
            session.setAttribute("userTypeS", null);
            return ("/home.jsp");
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
