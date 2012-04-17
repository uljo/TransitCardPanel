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
package se.cenote.transitcardpanel.effect.velocity;

/**
 * Velocity-implementation med mjuk deceleration.
 * 
 * @author Ulf M. Johanneson
 *
 */
public class SoftDecelerationVelocity implements Velocity{
	
	private static final double ARC = 1.5707963267948966D;
	
    public SoftDecelerationVelocity(){ }

    public double getDistance(double d){
        return Math.sin(d * ARC);
    }

    
}