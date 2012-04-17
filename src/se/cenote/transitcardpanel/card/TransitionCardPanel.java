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
package se.cenote.transitcardpanel.card;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

import se.cenote.transitcardpanel.effect.Effect;
import se.cenote.transitcardpanel.effect.EffectSlideHorizontal;
import se.cenote.transitcardpanel.effect.EffectSlideHorizontal.Direction;

/**
 * Specialized CardLayout panel supporting graphical transition effect when changing cards.
 * 
 * @author Ulf M. Johanneson
 *
 */
public class TransitionCardPanel extends JPanel{
	
	private static final long serialVersionUID = 3910932203679480788L;
	
    private static final Object TRANSITION_LOCK = new Object();
	private static final String TRANSITION_CARD = "transitionCard";
	private static final int TIMER_DELAY = 20;
    
    private CardLayout cardLayout;
    private TransitionCard transitionCard;
    private Effect transitionEffect;
    
    private TransitionTimer transitionTimer;
    
    private List<Card> cards;
    private Card currCard;
    private Card nextCard;
    
    /**
     * Default constructor.
     * 
     */
    public TransitionCardPanel(){
    	
    	initComponents();
    	layoutComponents();
    }
    
    /**
     * Make specified card visible, using predefined transition effect
     * 
     * @param card specified card.
     * @param isBackward true if direction is backward, else false.
     */
    public void setView(Card card, boolean isBackward){
        synchronized(TRANSITION_LOCK){
            transitionTimer.stopTimer();
            //viewMgr.transition(prev, next);
            
            nextCard = card;
            transitionTimer.reset(isBackward);
        }
    }
    
    protected List<Card> getCards(){
    	return cards;
    }
    
    protected Card getCard(int index){
    	return cards.get(index);
    }
    
    protected int getCardCount(){
    	return cards.size();
    }
    
    /**
     * Initialize this panel with specified views.
     * First card in list is set as current view. 
     * 
     * @param cards specified views.
     */
    protected void initCards(List<Card> cards){
    	for(Card card : cards){
            add(card.getPanel(), card.getName());
        }
    	add(transitionCard, TRANSITION_CARD);
    	
    	if(!cards.isEmpty()){
	    	currCard = cards.get(0);
	        cardLayout.show(this, currCard.getName());
	        
	        setBackground(currCard.getPanel().getBackground());
    	}
    }
    
    private void initComponents(){
    	cards = new ArrayList<Card>();
    	
        transitionEffect = new EffectSlideHorizontal(Direction.LEFT);
        transitionTimer = new TransitionTimer(TIMER_DELAY);
        
        transitionCard = new TransitionCard(transitionEffect);
    }
    
    private void layoutComponents(){
        setOpaque(true);
        
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        
        if(cards != null)
        	initCards(cards);
    }

    /**
     * Special internal view used during transition effect.
     * 
     * @author Uffe
     *
     */
	private static class TransitionCard extends JComponent{

		private static final long serialVersionUID = 1L;

		private Effect effect;
		
		/**
		 * Construct instance with specified transition effect.
		 * 
		 * @param effect specified transition effect
		 */
        public TransitionCard(Effect effect){
        	super();
            setOpaque(true);
            this.effect = effect;
        }
        
		public void paintComponent(Graphics g){
			g.drawImage(effect.getbackImg(), 0, 0, null);
        }
    }

	/**
	 * Timer instance used for driving transition effect.
	 * 
	 * @author Uffe
	 *
	 */
    private final class TransitionTimer implements ActionListener{

        private Timer timer;
        
        /**
         * Construct instance with specified delay.
         * 
         * @param delay milliseconds for the initial and between-event delay
         */
        private TransitionTimer(int delay){
            timer = new Timer(delay, this);
        }
        
        /**
         * Stop the timer.
         * 
         */
        public void stopTimer() {
        	if(timer.isRunning()){
                timer.stop();
        	}
		}

        /**
         * Reset the timer.
         * 
         * @param isBackward true if transition effect should go backwards, else false.
         */
		public void reset(boolean isBackward) {
			
			transitionEffect.reset(currCard.getPanel(), nextCard.getPanel(), getSize(), isBackward);
            
            cardLayout.show(TransitionCardPanel.this, TRANSITION_CARD);
            
            timer.start();
        }

		/**
		 * Callback for timer ticks.
		 */
        public void actionPerformed(ActionEvent ev){
        	double time = transitionEffect.getMoment();
        	
            if(time >= 1.0D){
                transitionEffect.drawComplete();
                timer.stop();
                
                currCard = nextCard;
                
                cardLayout.show(TransitionCardPanel.this, currCard.getName());
                setBackground(currCard.getPanel().getBackground());
                
                //currView.getPanel().setFocus();
            } 
            else{
            	transitionEffect.draw(time, getSize());
            }
            
            transitionCard.repaint();
        }

    }
}