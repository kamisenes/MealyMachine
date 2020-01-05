package com.vainolo.examples.jung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.DefaultEdgeLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;

public class Otomat {
	
	String data[][]= {};
	 static JTable jt;

  public static void main(String[] args) {
	  JFrame f=new JFrame("Otomat Final Projesi"); 
      	f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));


	    JPanel jpn_qverisi=new JPanel();

	    JTextField jtf_qtext=new JTextField("Veri Giriniz");
	    JLabel jlab_qtext=new JLabel("Q=");
	    jpn_qverisi.setAlignmentX(Component.LEFT_ALIGNMENT);
	    jpn_qverisi.setPreferredSize(new Dimension(100, 100));
	    jpn_qverisi.setMaximumSize(new Dimension(100, 100));
	    jpn_qverisi.add(jlab_qtext);
	    jpn_qverisi.add(new JSeparator());
	    jpn_qverisi.add(jtf_qtext);
	    
	    
		  
	    JPanel jpn_epsiloneverisi=new JPanel();
	    JTextField jtf_epsilonetext=new JTextField("Veri Giriniz");
	    JLabel jlab_epsilonetext=new JLabel("ε=");

	    jpn_epsiloneverisi.setAlignmentX(Component.LEFT_ALIGNMENT);
	    jpn_epsiloneverisi.setPreferredSize(new Dimension(100, 100));
	    jpn_epsiloneverisi.setMaximumSize(new Dimension(100, 100));
	    jpn_epsiloneverisi.add(jlab_epsilonetext);
	    jpn_epsiloneverisi.add(new JSeparator());
	    jpn_epsiloneverisi.add(jtf_epsilonetext);
	    
	    JPanel jpn_ucgenverisi=new JPanel();
	    JTextField jtf_ucgentext=new JTextField("Veri Giriniz");
	    JLabel jlab_ucgentext=new JLabel("Δ=");

	    jpn_ucgenverisi.setAlignmentX(Component.LEFT_ALIGNMENT);
	    jpn_ucgenverisi.setPreferredSize(new Dimension(100, 100));
	    jpn_ucgenverisi.setMaximumSize(new Dimension(100, 100));
	    jpn_ucgenverisi.add(jlab_ucgentext);
	    jpn_ucgenverisi.add(new JSeparator());
	    jpn_ucgenverisi.add(jtf_ucgentext);
	

	    String data[][]={ {"A","",""},    
	    				  {"B","",""},    
	    				  {"C","",""},  
	    				  {"D","",""}, 
	    				  {"E","",""}, 
                          {"F","",""}};    
			String column[]={"Q","X=0","X=1"};         
			 jt=new JTable(data,column);    
			
			JScrollPane sp=new JScrollPane(jt);    
				    
	         
			
			jtf_qtext.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void insertUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					System.out.println("TEXTINSERTUPDATE:"+jtf_qtext.getText()+jtf_qtext.getText().length());
					
				}
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					System.out.println("TEXTIREMOVEUPDATE:"+jtf_qtext.getText());

				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					System.out.println("TEXTICHANGEDUPDATE:"+jtf_qtext.getText());

				}
				
			});
			
			
			JPanel jpn_cizbutonu=new JPanel();
			JButton jbtn_ciz=new JButton("Cizim Yap");
			jpn_cizbutonu.setAlignmentX(Component.LEFT_ALIGNMENT);
			jpn_cizbutonu.setPreferredSize(new Dimension(100, 100));
			jpn_cizbutonu.setMaximumSize(new Dimension(100, 100));
			jpn_cizbutonu.add(jbtn_ciz);
			
			
			
			
			jbtn_ciz.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (jt.isEditing())
					    jt.getCellEditor().stopCellEditing();
					Vector<Object> datas = new Vector<Object>();
					ArrayList<String> list=new ArrayList<String>();
					
					for (int i =0;i<jt.getRowCount();i++) {
						for (int k=1;k<jt.getColumnCount();k++) {
							String  obj1 =  (String) GetData(jt, i, k);
							
							
							if (obj1.length()>0) {
								
								list.add(obj1);
					
							
							System.out.println(i+ " . satir "+k+" . sutun elemani ::" + obj1);
							}
							else
							{
								
								System.out.println(i+ " . satir "+k+" . sutun elemani bos");

							}
						}
					}
					
					for (String num : list) { 		      
				           System.out.println("list"+num); 		
				      }
					
					GrapCiz(list,jtf_qtext.getText());
				}
				
			
				
			});
			
			
	    f.getContentPane().add(jpn_qverisi);
	    f.getContentPane().add(jpn_epsiloneverisi);
	    f.getContentPane().add(jpn_ucgenverisi);
	    f.getContentPane().add(sp);
	    f.getContentPane().add(jpn_cizbutonu);

	    SwingUtilities.updateComponentTreeUI(f);
	    f.setSize(200,430);  
	    f.setVisible(true); 
  }
  
  public static Object GetData(JTable table, int row_index, int col_index){
	  return table.getModel().getValueAt(row_index, col_index);
	  }  

public static  void GrapCiz(ArrayList<String> list, String durumlar){
	 DirectedSparseGraph<String, String> g = new DirectedSparseGraph<String, String>();
	 String [] durum=durumlar.split(",");
	 int statedurum=0;
	 for (int i =0;i<durum.length;i++) {
		 g.addVertex(durum[i]);
	 }
	 for (int j =0;j<list.size();j++) {
		 String listitem=list.get(j);
		 
		 String [] parse=listitem.split(",");
		 
		 if (j==0 || j==1 ) {
			 statedurum=0;
		 }
		 else if (j==2 || j==3 ) {

			 statedurum=1;
		 }
		 else  if (j==4 || j==5 )
		 {
			 statedurum=2;
		 }
		 else if (j==6 || j==7 )
			 statedurum=3;
		 else if (j==8 || j==9 )
			 statedurum=4;
		 else if (j==10 || j==11 )
			 statedurum=5;
		 
         

		 if (j%2==0) {
			 g.addEdge("0/"+parse[1], durum[statedurum], parse[0]);
		 }else
		 {
			 g.addEdge("1/"+parse[1], durum[statedurum], parse[0]);

		 }
		 
		
		 System.out.println("statedurum:"+statedurum); 	
	 }
	    
	    Transformer<String,Paint> vertexPaint = new Transformer<String,Paint>() {
	    	 public Paint transform(String i) {
	    	 return Color.GREEN;
	    	 }
	    	 };

	    
	    VisualizationImageServer<String, String> vs =
	        new VisualizationImageServer<String, String>(new CircleLayout<String, String>(g), new Dimension(500, 500));

	    vs.getRenderContext().setVertexLabelRenderer(new DefaultVertexLabelRenderer(Color.cyan));
	    vs.getRenderContext().setEdgeLabelRenderer(new DefaultEdgeLabelRenderer(Color.cyan));
	    vs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
	    vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

	    vs.getRenderContext().setVertexFillPaintTransformer(vertexPaint);

	    vs.setBackground(Color.white);
	    JFrame frame = new JFrame();
	    frame.getContentPane().add(vs);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
}
public static JTable table() {
	return null;
	
}
}
