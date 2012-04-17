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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import se.cenote.transitcardpanel.card.Card;
import se.cenote.transitcardpanel.card.TransitionCardPanel;

/**
 * 
 * @author Ulf M. Johanneson
 *
 */
public class MainPanel extends TransitionCardPanel {

	private static final long serialVersionUID = 1L;

	private boolean isBackward;
	private int curr = 0;
	
	public MainPanel(){
		ActionListener listener = new MyListener();
		
		List<Card> cards = getCards();
		cards.add(new CardAlfa(listener));
		cards.add(new CardBeta(listener));
		
		initCards(cards);
	}
	
	private void navigate(){
		if(isBackward && curr == 0){
			isBackward = false;
		}
		else if(!isBackward && curr == getCardCount()-1){
			isBackward = true;
		}
		curr += isBackward ? -1 : 1;
		
		setView(getCard(curr), isBackward);
	}
	
	private class MyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			navigate();
		}
	}
}
