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

/**
 * Common interface for different path implementations.
 * 
 * @see MemoryPath
 * @see StraightLinePath
 * 
 * @author Ulf M. Johanneson
 *
 */
public interface Path{

    public abstract double[] getCoords(double d);

    public abstract double[] getCoords(double d, double[] ad);
}