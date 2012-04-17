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
 * Path-implementation som utökar en given path med 'minnes-funktion'
 * Path implementation for straight lines.
 * 
 * @author Ulf M. Johanneson
 *
 */
public class MemoryPath implements Path{
	

    private Path path;
    
    private double coords[][];
    private int index;
    private int bufferSize;
    
    private int gapCnt;
    private int gap;

    public MemoryPath(Path path, int rows){
        this(path, rows, 1);
    }

    public MemoryPath(Path path, int rows, int gap){
        this.path = path;
        coords = new double[rows][2];
        index = 0;
        bufferSize = 0;
        
        this.gap = gap;
        gapCnt = 0;
        
    }

    public double[] getCoords(double d){
        return getCoords(d, new double[2]);
    }

    public double[] getCoords(double d, double[] ad){
        ad = path.getCoords(d, ad);
        gapCnt++;
        if(gapCnt % gap == 0) {
            coords[index][0] = ad[0];
            coords[index][1] = ad[1];
            index = (index + 1) % coords.length;
            bufferSize++;
            if(bufferSize > coords.length){
                bufferSize = coords.length;
            }
        }
        return ad;
    }

    public double[] getPreviousCoords(int i){
        i = (((i + index) - 1) + coords.length) % coords.length;
        return coords[i % coords.length];
    }

    public int getMemorySize(){
        return coords.length;
    }

    public int getBufferSize(){
        return bufferSize;
    }

    public void reset(){
        bufferSize = 0;
        gapCnt = 0;
    }
}