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

public class RobotEvacuation3 {
	static int flagFindExit=0;
	static int flagExit=0;
    static int flagStopLinearMotion1=0;
    static int flagStopLinearMotion2=0;
    static int flagStopCircularMotion1=0;
    static int flagStopCircularMotion2=0;
    static int randomInt=0;
    static int randomY=0;
    static int flagTimer=1;
    static int flagReachExit1=0,flagReachExit2=0;
    static int flagMoveLinear1=0;
    static int flagMoveLinear2=0;
    static int flagAngleBetweenRobots=0;
    static int distanceBetweenRobots=0;
	JTextField timerText1 = new JTextField(10);
    JTextField timerText2 = new JTextField(10);
    long endTime;
    long startTime;
    private float distance = 0;
	
	public static void main(String[] args) {
    	flagFindExit=1;
        new RobotEvacuation3();
    }
	
	public RobotEvacuation3() {
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
                comboBox.addItem("Average Case Evacuation");
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
                         else if(selected.toString().equals("Average Case Evacuation")){
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
                  	 degree2 += 0.5f;
                  }
                    distance+=0.5f;
                    repaint();
                }
            });
            timer.start();
        }
		
	}
	
	@SuppressWarnings("serial")
	public class TestPaneAverageCase extends JPanel {
		private float degree1 = 90;
        private float degree2 = 90;
        private float distance1=0;
        private float distance2=0;
        private float distance3=0;
        private float AngleBetweenRobots=0;
		
		int stage1 = 1;
		int stage2 = 1;
		
		boolean initialization = true;
		
		//exit placed randomly 
		
		double eAngle;
		int randomInt;
		
		
		
		//both robots placed randomly
		
		TestPaneAverageCase(){
        	this.setBackground(Color.RED);
        	this.add(timerText1);
            startTime = System.currentTimeMillis();
        	animate(20);
        }
		
		public void animate(int v) {
			
			Timer timer = new Timer(v, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					repaint();
				}
        	});
        	timer.start();
        	
    	}
		
		@Override
        protected void paintComponent(Graphics g) {
        	 super.paintComponent(g);
        	 
        	 //draw room
        	 Circle c=new Circle();
             Graphics2D g2d = (Graphics2D) g.create();
             c.initiateCircle(getWidth()/2,getHeight()/2,Math.min(getWidth()/4, getHeight())/4);
             g2d.setColor(Color.GREEN);
             g2d.drawOval(c.x-c.radius, c.y-c.radius, 2*c.radius, 2*c.radius);
             Robot r = new Robot();
             
             float diameter = 20;
             
             if(initialization)
             {
             	@SuppressWarnings("unused")
 				Random random = new Random();
             	randomInt=random.nextInt((360 - 0) + 1) + 90;
             	initialization = false;
             }
             
             Point r1=new Point();
             Point r2=new Point();
             Point exit = r.moveCircular(c.x- (int) (diameter / 2),c.y- (int) (diameter / 2),randomInt,c.getRadius());
             g2d.drawOval(exit.x, exit.y, (int) diameter, (int) diameter);
             
             
             //both robots move linearly to the edge of the circle (line through center), keep the co-ordinates 
             //of these intersections
             if (stage1 == 1) { //first robot in first stage
            	 
            	 //r1 = r.moveLinear(x, y, degrees, radius);
            	 
             }
        	 
		}
		
		
		
		//both robots move in the direction towards the center
		
		//if one of the robots reaches the exit it calls the other to move linearly towards it
		
		//otherwise both robots meet at a point, travel to the previous intersection points linearly 
		
		//they move circularly the direction opposite the center
		
		//if one of the robots reaches the exit, the other goes linearly towards it
		
		
	}
	
	

}
