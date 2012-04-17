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

import java.awt.AlphaComposite;
import java.awt.Dimension;

/**
 * Transitionseffekt längs horisontel axel.
 * 
 * @author Ulf M. Johanneson
 * 
 */
public class EffectSlideHorizontal extends Effect{
	
	private static final long serialVersionUID = 1L;

	public enum Direction {LEFT, RIGHT}
	
	private Direction dir;
	
	public EffectSlideHorizontal(Direction dir){
		this.dir = dir;
	}
	
	public void draw(double time, Dimension dim){
		java.awt.Composite comp = backImgG.getComposite();
        
        double coords[] = path.getCoords(time);
        int x = (int)coords[0];

        int x2 = 0;
        if(Direction.LEFT.equals(dir)){
        	x2 = backwards ? x - dim.width : dim.width - x;
        }
        else {
        	x2 = backwards ? dim.width - x : x - dim.width;
        }
        
        AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)time);
        backImgG.setComposite(alpha);
        
        backImgG.drawImage(incomingImg, x2, 0, null);
        
        backImgG.setComposite(comp);
	}
}