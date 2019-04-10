package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import ficheros.Leer;
import negocio.ID3;
import negocio.Nodo;

public class Main {

	public static void main(String[] args) {
		Leer l = new Leer();
		try {
			l.leerAtributos("AtributosJuego.txt");
			l.leerValores("Juego.txt");
			ID3 id3 = new ID3();
			id3.algoritmo(l.getTabla(), null);
			ArrayList<Nodo> arbol = id3.getArbol();
			for(int i = 0; i < arbol.size(); i++) {
				String padre = "";
				if(arbol.get(i).getPadre() != null)
					padre = arbol.get(i).getPadre().getAtributo() + "->" + arbol.get(i).getPadre().getCamino();
				System.out.println(arbol.get(i).getCamino() + " " + arbol.get(i).getAtributo() + " padre: " + padre);
			}
			
			
			Graph<String, String> g = new DelegateForest<String, String>();
			HashMap<Nodo, String> aux = new HashMap<>();
			for(int i = 0; i < arbol.size(); i++) {
				if(!aux.containsKey(arbol.get(i).getPadre())){
					g.addVertex((String) arbol.get(i).getAtributo());
					aux.put(arbol.get(i), arbol.get(i).getAtributo());
				}
				else {
					if(arbol.get(i).getAtributo().equalsIgnoreCase("SI") || 
							arbol.get(i).getAtributo().equalsIgnoreCase("NO")) {
						g.addVertex((String) arbol.get(i).getAtributo() + i);
						g.addEdge(arbol.get(i).getPadre().getCamino() + i,
								aux.get(arbol.get(i).getPadre()),
								arbol.get(i).getAtributo() + i);
						aux.put(arbol.get(i), arbol.get(i).getAtributo() + i);
					}
					else {
						g.addVertex((String) arbol.get(i).getAtributo());
						g.addEdge(arbol.get(i).getPadre().getCamino() + i,
								aux.get(arbol.get(i).getPadre()),
								arbol.get(i).getAtributo());
						aux.put(arbol.get(i), arbol.get(i).getAtributo());
					}
					
					
				}
			}
			//Vista vista = new Vista(arbol);
			Layout<String, String> layout = new TreeLayout<>((Forest<String, String>) g);
			VisualizationViewer<String, String> vv = new VisualizationViewer<String, String>(layout);
			vv.setPreferredSize(new Dimension(800, 800));
			
			// Show vertex and edge labels
			vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
			vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
			// Create a graph mouse and add it to the visualization component
			DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
			gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
			vv.setGraphMouse(gm);
			// Add the mouses mode key listener to work it needs to be added to the
			// visualization component
			vv.addKeyListener(gm.getModeKeyListener());
			vv.setSize(1000, 1000);
			JFrame frame = new JFrame("Interactive Graph View 2");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400, 400);
			frame.getContentPane().add(vv);
			//frame.pack();
			frame.setLayout(new BorderLayout());
			JFrame datos = new JFrame();
			
			JPanel panel = new JPanel();
			JButton boton = new JButton();
			panel.add(boton);
		
			datos.add(panel);
			//tabla
			String data[][]={ {"","","", ""}};
		    String column[]={"TIEMPO EXTERIOR","TEMPERATURA","HUMEDAD", "VIENTO"};         
		    JTable jt=new JTable(data,column);
		    JScrollPane sp=new JScrollPane(jt);    
		    panel.add(sp);
		    
		    //ACTION LISTENER DEL BOTON
		    boton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("boton presionado");
	            }
	        });
			
		    
		    
		    
			datos.setSize(700, 700);
			datos.setVisible(true);
			frame.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
