/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoigraph;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.Pane;

/**
 *
 * @author Ryan
 */
public class VoronoiGraph extends Application {
    

    final Canvas canvas = new Canvas(1000,1000);
    GraphPoint[][] graphArray = new GraphPoint[(int)canvas.getWidth()][(int)canvas.getHeight()];
    int numCentres = 100;
    GraphPoint[] centreArray = new GraphPoint[numCentres];
    double waterPercent= 0.25;
    @Override
    public void start(Stage primaryStage) {
        
        //Random Colour Button
        Button btn = new Button();
        btn.setText("Fill In Random Colour");
        btn.setLayoutX(1000);
        btn.setLayoutY(0);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            //Generate new graph when button is pressed
            public void handle(ActionEvent event) {
                graphArray = new GraphPoint[(int)canvas.getWidth()][(int)canvas.getHeight()];
                centreArray = new GraphPoint[numCentres];
                drawCentres(numCentres);
                fillIn();
                
            }
        });
        //End Random Colour Button
        
        //Earth Water Fill in Buton
        Button ewBtn = new Button();
        ewBtn.setText("Fill In Earth Water");
        ewBtn.setLayoutX(1000);
        ewBtn.setLayoutY(30);
        ewBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            //Generate new graph when button is pressed
            public void handle(ActionEvent event) {
                graphArray = new GraphPoint[(int)canvas.getWidth()][(int)canvas.getHeight()];
                centreArray = new GraphPoint[numCentres];
                drawCentresEW(numCentres);
                fillIn();
                
            }
        });
        //End Earth Water Fill in Buton
        
        Pane root = new Pane();
        
        //Make canvas
        root.getChildren().add(canvas);
        root.getChildren().add(ewBtn);
        root.getChildren().add(btn);
        
        drawCentres(numCentres);
        fillIn();
       
        
    
        
        Scene scene = new Scene(root, 1920, 1080);
        
        primaryStage.setTitle("VoronoiGraph");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
    }
    public void fillIn(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphPoint centre;
        for(int x=0;x<graphArray.length;x++){
            for(int y=0;y<graphArray[0].length;y++){
                if(graphArray[x][y]==null){
                    centre = findNearestCentre(x,y);
                    
                    gc.setFill(centre.color);
                    gc.fillRect(x,y,1,1);
                    
                    graphArray[x][y]=new GraphPoint(x,y,false,centre.color,centre.id);
                }
            }
        }
    }//Draw
    
    public GraphPoint findNearestCentre(int x, int y){
        GraphPoint nearest =centreArray[0];
        double distance;
        double nearestDistance=Integer.MAX_VALUE;
        for(int i =0;i<centreArray.length;i++){
            distance = getDistanceEuclidean(x,y,centreArray[i].x,centreArray[i].y);
            
            if(distance<nearestDistance){
                nearest = centreArray[i];
                nearestDistance= distance;
            }
            
        }
        
        return nearest;
    }
    
    //Get distance between two points using euclidean distance
    public double getDistanceEuclidean(int x1,int y1, int x2, int y2){
        int x = x1-x2;
        int y = y1-y2;
        double distance = Math.sqrt((x*x)+(y*y));
        return distance;
    }//getDistanceEuclidean
    
    //Get distance between two points using euclidean distance
    public double getDistanceManhattan(int x1,int y1, int x2, int y2){
        int x = x1-x2;
        int y = y1-y2;
        double distance = Math.abs(x)+Math.abs(y);
        
        return distance;
    }//getDistance
    
    public void drawCentresEW(int numCentres){
        int xPos;
        int yPos;
        Color c;
        for(int i=0;i<numCentres;i++){
            xPos =(int) (Math.random()*(canvas.getWidth()));
            yPos =(int) (Math.random()*(canvas.getHeight()));
            if(xPos<(canvas.getWidth()*waterPercent)||yPos<(canvas.getHeight()*waterPercent)||xPos>(canvas.getWidth()-(canvas.getWidth()*waterPercent))||yPos>(canvas.getHeight()-(canvas.getHeight()*waterPercent))){
                c = Color.rgb(20, 120, 249);
            }else{
                c = Color.rgb(28, 130, 5);
            }
            
            GraphicsContext point = canvas.getGraphicsContext2D();
            point.setFill(c);
            point.fillRect(xPos,yPos,1,1);
            graphArray[xPos][yPos]= new GraphPoint(xPos,yPos,true, c,i);
            centreArray[i]=graphArray[xPos][yPos];
        }
    }
    
    public void drawCentres(int numCentres){
        int xPos;
        int yPos;
        
        for(int i=0;i<numCentres;i++){
            xPos =(int) (Math.random()*(canvas.getWidth()));
            yPos =(int) (Math.random()*(canvas.getHeight()));
            Color c = Color.rgb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
            GraphicsContext point = canvas.getGraphicsContext2D();
            point.setFill(c);
            point.fillRect(xPos,yPos,1,1);
            graphArray[xPos][yPos]= new GraphPoint(xPos,yPos,true, c,i);
            centreArray[i]=graphArray[xPos][yPos];
        }
    }//drawCentres
 
    public void convertGraphToImg(){
        
    }
}
