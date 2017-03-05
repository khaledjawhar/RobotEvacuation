package com.khaledamjan.main;

public class Circle {
	int x=0;
	int y=0;
	int radius;
	
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

	void setRadius(int radius){
		this.radius=radius;
	}

	public void initiateCircle(int x,int y,int radius){
	    this.x = x;
	    this.y = y;
	    this.radius=radius;
	}
}
