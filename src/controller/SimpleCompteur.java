package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class SimpleCompteur
 */
public class SimpleCompteur extends HttpServlet {
 private static final long serialVersionUID = 1L;
       
 Integer compteur =0 ;
 String fichierSource = "/home/rfrimat/Documents/JAVA/Eclipse_JEE_ServletJSP_Cycle-Vie-Servlet_CompteurParSession/src/persistance/compteur.txt";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleCompteur() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    @Override
    	public void init() throws ServletException {
    		try {
				this.deserialisation();
			} catch (ClassNotFoundException | IOException e) {
				
				e.printStackTrace();
			}
    	}
    
    @Override
    	public void destroy() {
    		this.serialisation();
    	}
    
    

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
  response.setContentType("text/plain");
  PrintWriter out = response.getWriter();
  this.ajoutCompte();
  out.println("De puis le chargement de cette servlet, celle-ci a été accédée " + compteur + " fois.");
  
 }
 
 private synchronized void ajoutCompte(){
	 compteur++;
 }
 
 public void deserialisation() throws IOException, ClassNotFoundException{
	 try { 
         FileInputStream f = new FileInputStream(this.fichierSource); 
         ObjectInputStream o = new ObjectInputStream(f); 
         this.compteur = (Integer)o.readObject(); 
         o.close(); 
   } 
   catch (IOException e) { 
         System.out.println(e); 
   } 
 }
 
 private void serialisation(){
	 try { 
         FileOutputStream f = new FileOutputStream(this.fichierSource); 
         ObjectOutputStream o = new ObjectOutputStream(f); 
         o.writeObject(this.compteur); o.close(); 
     } 
     catch (IOException e) { 
         System.out.println(e); 
     } 
     
    
 }

}