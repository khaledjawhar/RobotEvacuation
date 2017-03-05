package com.khaledamjan.main;

import java.awt.Point;

public class Robot {
int x=0;
int y=0;
int radius=0;
int getX(){
	return this.x;
}

void setX(int x){
	this.x=x;
}

int getY(){
	return this.y;
}

void setY(int y){
	this.y=y;
}

int getRadius(){
	return this.radius;
}

void serRadius(int radius){
	this.radius=radius;
}

public void initiateRobot(int x,int y,int radius){
	this.x=x;
	this.y=y;
	this.radius=radius;
}

public int distanceToPoint(Point p){
//	int distance=0;
	
	return  (int) Math.sqrt(
            (p.getX() - this.getX()) *  (p.getX() - this.getX()) + 
            (p.getY() - this.getY()) *  (p.getY() - this.getY())
        );
}
//pass the degree between robot and x-axis and radius is distance from point(x,y)  
public Point moveLinear(int x,int y,float degrees,float radius){
	 double rads = Math.toRadians(degrees); 
	if(rads<2*Math.PI && rads>3*Math.PI/2){
		 this.x = Math.round((float) (x + Math.cos(rads) * radius));
	     this.y = Math.round((float) (y + Math.sin(rads) * radius));
	}
	else if(3*Math.PI/2>rads && rads>Math.PI){
		 this.x = Math.round((float) (x + Math.cos(rads) * radius));
	     this.y = Math.round((float) (y + Math.sin(rads) * radius));
	}
	
	else if(Math.PI>rads && rads>Math.PI/2){
		 this.x = Math.round((float) (x + Math.cos(rads) * radius));
	     this.y = Math.round((float) (y + Math.sin(rads) * radius));
	}
    else if(0<rads && rads<Math.PI/2){
    	 this.x = Math.round((float) (x + Math.cos(rads) * radius));
	     this.y = Math.round((float) (y + Math.sin(rads) * radius));
	}
    else if(rads==0){
    	 this.x = Math.round((float) (x + radius));
	     this.y = y;
    }
    else if(rads==3*Math.PI/2){
    	 this.x = x;
	     this.y = Math.round((float) (y -radius));
    }
    else if(rads==Math.PI){
    	this.x = Math.round((float) (x - radius));
	    this.y =y;
    }
    else if(rads==Math.PI/2){
    	this.x = x;
	    this.y = Math.round((float) (y + radius));
    }
	return new Point(this.x, this.y);
}


//takes coordinate of point to set the position with respect to and returns the coordinate of point of robot
public Point moveCircular(int x,int y,float degrees,float radius){
     double rads = Math.toRadians(degrees); 
     this.x = Math.round((float) (x + Math.cos(rads) * radius));
     this.y = Math.round((float) (y + Math.sin(rads) * radius));
     return new Point(this.x, this.y);
}

}
