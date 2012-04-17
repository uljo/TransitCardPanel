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

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import se.cenote.transitcardpanel.card.Card;

/**
 * 
 * @author Ulf M. Johanneson
 *
 */
public class CardAlfa extends JPanel implements Card {
	
	private static final long serialVersionUID = 1L;
	
	private JButton btn;
	
	public CardAlfa(ActionListener listener){
		btn = new JButton("Alfa");
		btn.addActionListener(listener);
		add(btn);
		
		setBackground(Color.RED);
	}

	@Override
	public String getName() {
		return "alfa";
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

}
