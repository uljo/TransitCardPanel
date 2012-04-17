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

import java.util.ArrayList;
import java.util.List;

/**
 * Velocity-implementation över sequence.
 * 
 * @author Ulf M. Johanneson
 *
 */
public class SequencedVelocity implements Velocity{

    private List<Velocity> velocities;
    
	public SequencedVelocity(){ }

	public SequencedVelocity(Velocity[] velocities){
        for(Velocity velocity : velocities){
            add(velocity, 1.0D);
        }
    }

    public double getDistance(double d){
        d -= 0.0001D;
        int i = velocities.size();
        double d2 = d * (double)i;
        int j = (int)d2;
        double d3 = d2 % 1.0D;
        double d4 = ((Velocity)velocities.get(j)).getDistance(d3);
        return ((double)j + d4) / (double)i;
    }

	public void add(Velocity velocity, double d){
		if(velocities == null ){
			velocities = new ArrayList<Velocity>();
		}
        velocities.add(velocity);
    }

}