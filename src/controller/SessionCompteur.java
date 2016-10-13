package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionCompteur
 */

public class SessionCompteur extends HttpServlet {
 private static final long serialVersionUID = 1L;

 /**
  * @see HttpServlet#HttpServlet()
  */
 public SessionCompteur() {
  super();
  // TODO Auto-generated constructor stub
 }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
  *      response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
  response.setContentType("text/html");
  PrintWriter out = response.getWriter();

  // obtenir l'objet de session courante, en créer un si nécessaire
  HttpSession session = request.getSession(true);

  System.out.println("session id=" + session.getId());

  // incrémenter le compteur par session
  Integer compteur = (Integer) session.getAttribute("compteur");

  if (compteur == null) {
   compteur = new Integer(1);
  } else {
   compteur = new Integer(compteur.intValue() + 1);
  }

  session.setAttribute("compteur", compteur);

  out.println("<HTML><HEAD><TITLE>Compteur par Client</TITLE></HEAD>");
  out.println("<BODY>");

  // afficher le compteur par session
  out.println("<P>La servlet a été accédée par vous " + compteur + " fois.</P>");

  out.println("</BODY></HTML>");

 }
}