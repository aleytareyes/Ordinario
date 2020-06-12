/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controler.Controlador;
import Interfaces.AlumnoListener;
import excepciones.AlumnoExistenteException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import objects.Alumnos;
import objects.Carrera;
import objects.Fecha;

/**
 *
 * @author Ale
 */
public class PrincipalFrame extends JFrame{
    
    private EncabezadoPanel pnlEncabezado;
    private WorkPanel pnlWork;
    private BusquedaPanel pnlBusqueda;
    
    private Controlador controlador;
    
    private AlumnoDialog dlgAlumno;
    
    public PrincipalFrame(){
        super("Control Escolar");
        super.setLayout(new BorderLayout());
        super.setSize(800, 500);
        super.setLocationRelativeTo(null);
        
        dlgAlumno = new AlumnoDialog(this);
        dlgAlumno.setListener(new AlumnoListener(){
            @Override
            public void aceptarButtonClick(Alumnos alumno){
                try {
                    controlador.addAlumnos(alumno);
                    dlgAlumno.setVisible(false);
                } catch (AlumnoExistenteException ex) {
                    JOptionPane.showMessageDialog(PrincipalFrame.this, "La matricula ya ha sido ingresada anteriormente", "Matricula invalda", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        controlador = new Controlador();
        cargaInicial();
        
        pnlEncabezado = new EncabezadoPanel();
        
        pnlWork = new WorkPanel(controlador);
        
        pnlBusqueda = new BusquedaPanel();
        
        super.setJMenuBar(createMenu());
        
        super.add(pnlEncabezado, BorderLayout.NORTH);
        super.add(pnlBusqueda, BorderLayout.SOUTH);
        super.add(pnlWork, BorderLayout.CENTER);
        
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
    
    private void cargaInicial(){
        Alumnos a = new Alumnos("09773", "Andrea", "Gomez", "Lopez", new Fecha(8,3, 1999), Carrera.ARQUITECTURA);
        Alumnos b = new Alumnos("12367", "Araminta", "Gonzalez", "Garcia", new Fecha(12,4, 1997), Carrera.DERECHO);
        Alumnos c = new Alumnos("87524", "Marco", "Ramírez", "Aragon", new Fecha(5,12, 2000), Carrera.PSICOLOGIA);
        Alumnos d = new Alumnos("08365", "Julieta", "Caballero", "Osorio", new Fecha(3,6, 1998), Carrera.SISTEMAS);
        Alumnos e = new Alumnos("13497", "Jaime", "Guijón", "Sánchez", new Fecha(14,5, 2001), Carrera.ARQUITECTURA);
        
        try{
        controlador.addAlumnos(a);
        controlador.addAlumnos(b);
        controlador.addAlumnos(c);
        controlador.addAlumnos(d);
        controlador.addAlumnos(e);
        }catch(AlumnoExistenteException ex){
            ex.printStackTrace();
        }
    }
    
    private JMenuBar createMenu(){
        JMenuBar nbMain = new JMenuBar();
        
        JMenu nmArchivo = new JMenu("Archivo");
        
        JMenuItem niNuevo = new JMenuItem("Nuevo alumno...");
        
        niNuevo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                dlgAlumno.setVisible(true);
            }  
        });
        
        JMenuItem niSalir = new JMenuItem("Salir");
        
        niSalir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        
        nmArchivo.add(niNuevo);
        nmArchivo.addSeparator();
        nmArchivo.add(niSalir);
        
        JMenu nmAyuda = new JMenu("Ayuda");
        JMenuItem niAcerca = new JMenuItem("Acerca de...");
        
        nmAyuda.add(niAcerca);
        
        nbMain.add(nmArchivo);
        nbMain.add(nmAyuda);
        
        return nbMain;
    }
}
