/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoigraph;

import javafx.scene.paint.*;



/**
 *
 * @author Ryan
 */
public class GraphPoint {
    int x;
    int y;
    boolean centre;
    Color color;
    int id;
        
        GraphPoint(int xPos,int yPos,boolean centreTF,Color colorThing,int idOfCentre){
            x=xPos;
            y=yPos;
            centre=centreTF;
            color=colorThing;
            id=idOfCentre;
        }
}
