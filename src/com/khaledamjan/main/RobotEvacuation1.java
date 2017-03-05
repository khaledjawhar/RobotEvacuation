package com.khaledamjan.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class RobotEvacuation1 {
    static int flagFindExit=0;
    static int flagStopLinearMotion1=0;
    static int flagStopLinearMotion2=0;
    static int flagStopCircularMotion1=0;
    static int flagStopCircularMotion2=0;
    static int randomInt=0;
    static int flagTimer=1;
    static int flagReachExit1=0,flagReachExit2=0;
    static int flagMoveLinear1=0;
    static int flagMoveLinear2=0;
    JTextField timerText1 = new JTextField(10);
    JTextField timerText2 = new JTextField(10);
    long endTime;
    long startTime;
    public static void main(String[] args) {
    	flagFindExit=1;
        new RobotEvacuation1();
    }

    public RobotEvacuation1() {
        EventQueue.invokeLater(new Runnable() {
            @SuppressWarnings("unchecked")
			@Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                JFrame mainFrame = new JFrame("Main Frame");
                @SuppressWarnings("rawtypes")
				JComboBox comboBox = new JComboBox();
                comboBox.setPreferredSize(new Dimension(300, 20));
                JButton evacuate = new JButton("Evacuate");
                comboBox.addItem("Worst Case Evacuation");
                comboBox.addItem("Average Case Evacuatio");
                evacuate.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                    	 @SuppressWarnings("rawtypes")
                         Object selected = comboBox.getSelectedItem();
                         if(selected.toString().equals("Worst Case Evacuation")){
	                    	 JFrame frame = new JFrame("Robot Evacuation1");
	                         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	                         int height = screenSize.height;
	                         int width = screenSize.width;
	                         frame.setSize(height, width); 
	                         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                         frame.add(new TestPaneWorstCase());
	                         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	                         frame.setVisible(true);
                         }
                         else if(selected.toString().equals("Average Case Evacuatio")){
                        	 JFrame frame = new JFrame("Robot Evacuation1");
	                         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	                         int height = screenSize.height;
	                         int width = screenSize.width;
	                         frame.setSize(height, width);
	                         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                         frame.add(new TestPaneAverageCase());
	                         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	                         frame.setVisible(true);
                         }
                    }
                });   
                mainFrame.setLayout(new FlowLayout());
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int height = screenSize.height;
                int width = screenSize.width;
                mainFrame.setSize(height, width);
                mainFrame.add(evacuate);
                mainFrame.add(comboBox);
                mainFrame.setVisible(true);
            }
        });
    }

    @SuppressWarnings("serial")
	public class TestPaneWorstCase extends JPanel {
        private float degree1 = 90;
        private float degree2 = 90;
        private float distance=0;
        private float distance1=0;
        TestPaneWorstCase(){
        	this.setBackground(Color.RED);
        	this.add(timerText1);
            startTime = System.currentTimeMillis();
        	animate(20);
        }
        public void animate(int v) {
            Timer timer = new Timer(v, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(flagStopLinearMotion1==1){
                    	 degree1 += 0.5f;
                    }
                    if(flagStopLinearMotion2==1){
                   	 degree2 -= 0.5f;
                   }
                    if(flagStopCircularMotion1==1){
                    	 distance1+=0.5f;
                    }
                    distance+=0.5f;
                    repaint();
                }
            });
            timer.start();
        }
                
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Circle c=new Circle();
            Graphics2D g2d = (Graphics2D) g.create();
            c.initiateCircle(getWidth()/2,getHeight()/2,Math.min(getWidth()/4, getHeight())/4);
            g2d.setColor(Color.GREEN);
            g2d.drawOval(c.x-c.radius, c.y-c.radius, 2*c.radius, 2*c.radius);
            float innerDiameter = 20;
            float innerDiameter1 = 20;
            float innerDiameter2 = 20;
            Robot r=new Robot();
            if(flagFindExit==1)
            {
            	@SuppressWarnings("unused")
				Random random = new Random();
            	randomInt=210;
            }
            flagFindExit=0;
            Point r1=new Point();
            Point r2=new Point();
            Point exit = r.moveCircular(c.x- (int) (innerDiameter / 2),c.y- (int) (innerDiameter / 2),randomInt,c.getRadius());
        	g2d.drawOval(exit.x, exit.y, (int) innerDiameter, (int) innerDiameter);
            if(distance<c.getRadius()){
            	r1=r.moveLinear(c.x-(int) (innerDiameter1 / 2),c.y- (int) (innerDiameter1 / 2),90,distance);
                degree1=90;
            }
            else if(flagStopCircularMotion1==0){
            	flagStopLinearMotion1=1;
            	r1=r.moveCircular(c.x- (int) (innerDiameter1 / 2),c.y- (int) (innerDiameter1 / 2),degree1,c.getRadius());
            	if(degree1==randomInt){
            		flagStopCircularMotion1=1;
            	}
            }
            else{
            	 r1 = r.moveCircular(c.x- (int) (innerDiameter / 2),c.y- (int) (innerDiameter / 2),randomInt,c.getRadius());
             	 g2d.drawOval(r1.x, r1.y, (int) innerDiameter, (int) innerDiameter);
            }
            if(distance<c.getRadius()){
            	r2=r.moveLinear(c.x-(int) (innerDiameter2 / 2),c.y- (int) (innerDiameter2 / 2),90,distance);
                degree2=90;
            }
            else if(flagStopCircularMotion1==0){
            	flagStopLinearMotion2=1;
            	r2=r.moveCircular(c.x- (int) (innerDiameter / 2),c.y- (int) (innerDiameter / 2),degree2,c.getRadius());
            }
            else if(flagStopCircularMotion1==1&&flagStopCircularMotion2==0){
            	double rads = Math.toRadians(30);
        		int x = Math.round((float) (Math.cos(rads) * c.getRadius())) ;
        		int y = Math.round((float) (Math.sin(rads) * c.getRadius())) ;
            	r2=r.moveLinear(c.x-(int) (innerDiameter2 / 2)+x,c.y- (int) (innerDiameter2 / 2)-y,180,distance1);
            	if(r2.getX()==r1.getX()){
            		flagStopCircularMotion2=1;
            	}
            }
            else{
            	r2 = r.moveCircular(c.x- (int) (innerDiameter / 2),c.y- (int) (innerDiameter / 2),randomInt,c.getRadius());
            	g2d.drawOval(r2.x, r2.y, (int) innerDiameter, (int) innerDiameter);
            	endTime   = System.currentTimeMillis();
            	if(flagTimer==1){
            		timerText1.setText(Long.toString((endTime - startTime))+" milli seconds");
            		flagTimer=0;
            	}
            }
            g2d.setColor(Color.BLUE);
            g2d.drawOval(r1.x, r1.y, (int) innerDiameter1, (int) innerDiameter1);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(r2.x, r2.y, (int) innerDiameter2, (int) innerDiameter2);
            g2d.dispose();
        }
        
        
     

    }
    
    @SuppressWarnings("serial")
	public class TestPaneAverageCase extends JPanel {
        private float degree1 = 90;
        private float degree2 = 90;
        private float otherdegree=0;
        private float distance=0;
        private float distance1=0;
        private float distance2=0;
        TestPaneAverageCase(){
        	this.setBackground(Color.RED);
        	this.add(timerText2);
            startTime = System.currentTimeMillis();
        	animate(20);
        }
        public void animate(int v) {
            Timer timer = new Timer(v, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(flagStopLinearMotion1==1){
                    	 degree1 += 0.5f;
                    }
                    if(flagStopLinearMotion2==1){	
                   	 degree2 -= 0.5f;
                   }
                    
                   if(flagMoveLinear1==1){
                      	 distance1-=0.5f;
                   }
                   if(flagMoveLinear2==1){
                    	 distance2+=0.5f;
                 }
                    distance+=0.5f;
                    repaint();
                }
            });
            timer.start();
        }
                
        @Override
        protected void paintComponent(Graphics g) {
            
        	super.paintComponent(g);
            Circle c=new Circle();
            Graphics2D g2d = (Graphics2D) g.create();
            c.initiateCircle(getWidth()/2,getHeight()/2,Math.min(getWidth()/4, getHeight())/4);
            g2d.setColor(Color.GREEN);
            g2d.drawOval(c.x-c.radius, c.y-c.radius, 2*c.radius, 2*c.radius);
            float innerDiameter = 20;
            float innerDiameter1 = 20;
            float innerDiameter2 = 20;
            Robot r=new Robot();
            
            if(flagFindExit==1)
            {
            	@SuppressWarnings("unused")
				Random random = new Random();
            	randomInt=random.nextInt((180 - 0) + 1) + 90;
            }
            flagFindExit=0;
            Point r1=new Point();
            Point r2=new Point();
            Point exit = r.moveCircular(c.x- (int) (innerDiameter / 2),c.y- (int) (innerDiameter / 2),randomInt,c.getRadius());
        	g2d.drawOval(exit.x, exit.y, (int) innerDiameter, (int) innerDiameter);
            if(distance<c.getRadius()){
            	r1=r.moveLinear(c.x-(int) (innerDiameter1 / 2),c.y- (int) (innerDiameter1 / 2),90,distance);
                degree1=90;
            }
            else if(flagStopCircularMotion1==0){
            	flagStopLinearMotion1=1;
            	r1=r.moveCircular(c.x- (int) (innerDiameter1 / 2),c.y- (int) (innerDiameter1 / 2),degree1,c.getRadius());
            	if(r1.getY()==exit.getY()&&r1.getX()==exit.getX()){
            		flagStopCircularMotion1=1;
            		flagStopCircularMotion2=1;
            		flagReachExit1=1;
            		if(0<randomInt&&randomInt<180){
            			otherdegree=180-randomInt;
            		}
            		else if(180<randomInt&&randomInt<360){
            			otherdegree=540-randomInt;
            		}
            	}
            	
            }
            else if(flagReachExit1==1){
            	 r1 = r.moveCircular(c.x- (int) (innerDiameter / 2),c.y- (int) (innerDiameter / 2),randomInt,c.getRadius());
             	 g2d.drawOval(r1.x, r1.y, (int) innerDiameter, (int) innerDiameter);
             	 flagMoveLinear2=1;
            }
            else if(flagMoveLinear1==1) {
            	if(otherdegree>270&&otherdegree<360){
            		double rads = Math.toRadians(otherdegree-270);
            		int x = Math.round((float) (Math.sin(rads) * c.getRadius())) ;
            		int y= Math.round((float) (Math.cos(rads) * c.getRadius()));
                	r1=r.moveLinear(c.x-(int) (innerDiameter2 / 2)+x,c.y- (int) (innerDiameter2 / 2)-y,180,distance1);
            	}
            	if(otherdegree>180&&otherdegree<270){
            		double rads = Math.toRadians(otherdegree-180);
            		int x = Math.round((float) (Math.cos(rads) * c.getRadius())) ;
            		int y= Math.round((float) (Math.sin(rads) * c.getRadius()));
                	r1=r.moveLinear(c.x-(int) (innerDiameter2 / 2)-x,c.y- (int) (innerDiameter2 / 2)-y,180,distance1);
            	}
            	if(otherdegree>90&&otherdegree<180){
            		double rads = Math.toRadians(otherdegree-90);
            		int x = Math.round((float) (Math.sin(rads) * c.getRadius())) ;
            		int y= Math.round((float) (Math.cos(rads) * c.getRadius()));
                	r1=r.moveLinear(c.x-(int) (innerDiameter2 / 2)-x,c.y- (int) (innerDiameter2 / 2)+y,180,distance1);
            	}
            	if(otherdegree>0&&otherdegree<90){
            		double rads = Math.toRadians(otherdegree);
            		int x = Math.round((float) (Math.cos(rads) * c.getRadius())) ;
            		int y=  Math.round((float) (Math.sin(rads) * c.getRadius())) ;
                	r1=r.moveLinear(c.x-(int) (innerDiameter2 / 2)+x,c.y- (int) (innerDiameter2 / 2)+y,180,distance1);
            	}
            	if(r1.getX()==exit.getX()&&r1.getY()==exit.getY()){
            		flagMoveLinear1=0;
            		endTime   = System.currentTimeMillis();
      	            if(flagTimer==1){
      	           		timerText2.setText(Long.toString((endTime - startTime))+" milli seconds");
      	           		flagTimer=0;
      	           	}
            	}
            }
            else{
            	r1 = r.moveCircular(c.x- (int) (innerDiameter / 2),c.y- (int) (innerDiameter / 2),randomInt,c.getRadius());
            	g2d.drawOval(r1.x, r1.y, (int) innerDiameter, (int) innerDiameter);	
            	
            }
            
            
            if(distance<c.getRadius()){
            	r2=r.moveLinear(c.x-(int) (innerDiameter2 / 2),c.y- (int) (innerDiameter2 / 2),90,distance);
                degree2=90;
            }
            else if(flagStopCircularMotion2==0){
            	flagStopLinearMotion2=1;
            	r2=r.moveCircular(c.x- (int) (innerDiameter / 2),c.y- (int) (innerDiameter / 2),degree2,c.getRadius());
            	if(r2.getY()==exit.getY()&&r2.getX()==exit.getX()){
            		flagStopCircularMotion1=1;
            		flagStopCircularMotion2=1;
            		flagReachExit2=1;
            		if(0<randomInt&&randomInt<180){
            			otherdegree=180-randomInt;
            		}
            		else if(180<randomInt&&randomInt<360){
            			otherdegree=540-randomInt;
            		}
            	}
            }
            else if(flagReachExit2==1){
              	 r2 = r.moveCircular(c.x- (int) (innerDiameter / 2),c.y- (int) (innerDiameter / 2),randomInt,c.getRadius());
                 g2d.drawOval(r2.x, r2.y, (int) innerDiameter, (int) innerDiameter);
                 flagMoveLinear1=1;
              }
            else if(flagMoveLinear2==1){
            	if(otherdegree>270&&otherdegree<360){
            		double rads = Math.toRadians(otherdegree-270);
            		int x = Math.round((float) (Math.sin(rads) * c.getRadius())) ;
            		int y= Math.round((float) (Math.cos(rads) * c.getRadius()));
                	r2=r.moveLinear(c.x-(int) (innerDiameter2 / 2)+x,c.y- (int) (innerDiameter2 / 2)-y,180,distance2);
            	}
            	if(otherdegree>180&&otherdegree<270){
            		double rads = Math.toRadians(otherdegree-180);
            		int x = Math.round((float) (Math.cos(rads) * c.getRadius())) ;
            		int y= Math.round((float) (Math.sin(rads) * c.getRadius()));
                	r2=r.moveLinear(c.x-(int) (innerDiameter2 / 2)-x,c.y- (int) (innerDiameter2 / 2)-y,180,distance2);
            	}
            	if(otherdegree>90&&otherdegree<180){
            		double rads = Math.toRadians(otherdegree-90);
            		int x = Math.round((float) (Math.sin(rads) * c.getRadius())) ;
            		int y= Math.round((float) (Math.cos(rads) * c.getRadius()));
                	r2=r.moveLinear(c.x-(int) (innerDiameter2 / 2)-x,c.y- (int) (innerDiameter2 / 2)+y,180,distance2);
            	}
            	if(otherdegree>0&&otherdegree<90){
            		double rads = Math.toRadians(otherdegree);
            		int x = Math.round((float) (Math.cos(rads) * c.getRadius())) ;
            		int y=  Math.round((float) (Math.sin(rads) * c.getRadius())) ;
                	r2=r.moveLinear(c.x-(int) (innerDiameter2 / 2)+x,c.y- (int) (innerDiameter2 / 2)+y,180,distance2);
            	}
            	if(r2.getX()==exit.getX()&&r2.getY()==exit.getY()){
            		flagMoveLinear2=0;
            		endTime   = System.currentTimeMillis();
      	            if(flagTimer==1){
      	           		timerText2.setText(Long.toString((endTime - startTime))+" milli seconds");
      	           		flagTimer=0;
      	           	} 
            	}
            }
            
            else{
              	r2 = r.moveCircular(c.x- (int) (innerDiameter / 2),c.y- (int) (innerDiameter / 2),randomInt,c.getRadius());
                g2d.drawOval(r2.x, r2.y, (int) innerDiameter, (int) innerDiameter);
                
              }
            
            g2d.setColor(Color.BLUE);
            g2d.drawOval(r1.x, r1.y, (int) innerDiameter1, (int) innerDiameter1);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(r2.x, r2.y, (int) innerDiameter2, (int) innerDiameter2);
            g2d.dispose();
        }
        
        
     

    }

}