/**
  * This file is part of application TransitCardPanel.
  * Copyright (C) 2012, Ulf M. Johanneson.
  *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */
package se.cenote.example;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;

/**
 * 
 * @author Ulf M. Johanneson
 *
 */
public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private String title = "Transition Example";
	private int width = 250;
	private int height = 330;
	
	private static String NIMBUS_LOOK = "Nimbus";
	
	public static void main(String[] args){
		displayFrame();
	}
	
	public MainFrame(){
		setTitle(title);
		setSize(width, height);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		MainPanel mainPanel = new MainPanel();
		getContentPane().add(mainPanel);
	}
	
	public static void displayFrame(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		
	    SwingUtilities.invokeLater(new Runnable() {
	    	public void run() {
	    		try{
		    		for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    			System.out.println("L&F " + info.getName());
	    		        if(NIMBUS_LOOK.equals(info.getName())) {
	    		            UIManager.setLookAndFeel(info.getClassName());
	    		            break;
	    		        }
	    		    }
	    		}
	    		catch(Exception e) {
	    			e.printStackTrace();
	    		}
	    		
	    		MainFrame frame = new MainFrame();
	    	  	frame.setVisible(true);
	    	}
	    });
	}

}
