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
package se.cenote.transitcardpanel.effect.path;

import se.cenote.transitcardpanel.effect.velocity.Velocity;


/**
 * Path implementation for straight lines.
 * 
 * @author Ulf M. Johanneson
 *
 */
public class StraightLinePath implements Path{

    private Velocity velocity;
    
    private long startX;
    private long startY;
    private double incX;
    private double incY;
    
    public StraightLinePath(long startX, long startY, long incX, long incY, Velocity velocity){
        this.startX = startX;
        this.startY = startY;
        this.incX = incX - startX;
        this.incY = incY - startY;
        this.velocity = velocity;
    }

    public double[] getCoords(double d){
        return getCoords(d, new double[2]);
    }

    public double[] getCoords(double d, double[] ad){
        double d1 = velocity.getDistance(d);
        ad[0] = (double)startX + incX * d1;
        ad[1] = (double)startY + incY * d1;
        return ad;
    }

}