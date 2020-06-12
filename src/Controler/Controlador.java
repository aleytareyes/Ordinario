/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import excepciones.AlumnoExistenteException;
import excepciones.AlumnoInexistenteException;
import java.util.ArrayList;
import objects.Alumnos;

/**
 *
 * @author Ale
 */
public class Controlador {
    
    private ArrayList<Alumnos> alum;
    
    public Controlador(){
        alum = new ArrayList<>(); 
    }
    
    public void addAlumnos(Alumnos alumno) throws AlumnoExistenteException{
        if(alum.contains(alumno)){
            throw new AlumnoExistenteException();
        }
        alum.add(alumno);
    }
    
    public ArrayList<Alumnos> getAlum(){
        return alum;
    }
    
    public Alumnos getAlumnos(String matricula) throws AlumnoInexistenteException{
        int index = alum.indexOf(new Alumnos(matricula));
        if(index < 0){
            throw new AlumnoInexistenteException();
        }
        return alum.get(index);
    }
    
    public Alumnos getAlumnos(int index){
        return alum.get(index);
    }
    
    public int getAlumnosCount(){
        return alum.size();
    }
    
    /*public static void main(String [] args){
        Controlador controlador = new Controlador();
        Alumnos a = new Alumnos("a");
        Alumnos b = new Alumnos("b");
        Alumnos c = new Alumnos("c");
        
        try{
        controlador.addAlumnos(a);
        controlador.addAlumnos(b);
        controlador.addAlumnos(c);
        System.out.println("Fine");
        Alumnos tmp = controlador.getAlumnos("c");
        System.out.println(tmp.getMatricula());
        }catch(AlumnoExistenteException ex){
            ex.printStackTrace();
        }catch(AlumnoInexistenteException ex){
            ex.printStackTrace();
        }
    }*/

 
}


