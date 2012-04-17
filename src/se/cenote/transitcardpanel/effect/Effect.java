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
package se.cenote.transitcardpanel.effect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.Serializable;

import javax.swing.JPanel;

import se.cenote.transitcardpanel.effect.path.Path;
import se.cenote.transitcardpanel.effect.path.StraightLinePath;
import se.cenote.transitcardpanel.effect.velocity.SequencedVelocity;
import se.cenote.transitcardpanel.effect.velocity.SoftAccelerationVelocity;
import se.cenote.transitcardpanel.effect.velocity.SoftDecelerationVelocity;
import se.cenote.transitcardpanel.effect.velocity.Velocity;

/**
 * Basklass för transitionseffekter.
 * 
 * @see EffectAlpha
 * @see EffectSlideHorizontal
 * @see EffectSlideVertical
 * 
 * @author Ulf M. Johanneson
 *
 */
public abstract class Effect implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static final int ANIM_DURATION = 500;
	
	public static final Velocity SOFT_VELOCITY = new SequencedVelocity(new Velocity[] {
	        new SoftAccelerationVelocity(), new SoftDecelerationVelocity()
	    });
	
	public static final GraphicsConfiguration GCON = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
	
	
	private AnimationController anim;
	
	protected boolean backwards;
	protected Color bg;
	protected Path path;
	
	protected BufferedImage outgoingImg;
	protected BufferedImage incomingImg;
	protected VolatileImage backImg;
	protected Graphics2D backImgG;
	
	abstract public void draw(double time, Dimension dim);
	
	public void drawComplete(){
		backImgG.drawImage(incomingImg, 0, 0, null);
	}
	
	public double getMoment(){
		return anim.getMoment();
	}
	
	public Image getbackImg() {
		return backImg;
	}
	
	public void reset(JPanel prevView, JPanel newView, Dimension d, boolean backwards){
		this.backwards = backwards;
		
        int w = (int)d.getWidth();
        int h = (int)d.getHeight();
        
        path = new StraightLinePath(0L, 0L, w, h, SOFT_VELOCITY);
        anim = new AnimationController(ANIM_DURATION);
        
        outgoingImg = GCON.createCompatibleImage(w, h);
        prevView.paint(outgoingImg.getGraphics());
        this.bg = prevView.getBackground();
        
        incomingImg = GCON.createCompatibleImage(w, h);
        newView.paint(incomingImg.getGraphics());
        
        backImg = GCON.createCompatibleVolatileImage(w, h);
        backImgG = (Graphics2D)backImg.getGraphics();
        backImgG.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        backImgG.drawImage(outgoingImg, 0, 0, null);
        
        anim.start();
	}

}
