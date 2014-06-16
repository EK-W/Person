import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Person {
	double personAngle = 0;
	int rep = 0;
	double size = 1;
	public limb torso;
	public limb neck;
	public limb head;
	
	public limb left_Humerus;
	public limb left_Radius;
	public limb left_Clavicle;
	public limb left_Femur;
	public limb left_Tibia;
	
	public limb right_Humerus;
	public limb right_Radius;
	public limb right_Clavicle;
	public limb right_Femur;
	public limb right_Tibia;
	
	joint lower_neck;
	joint upper_neck;
	
	joint left_Shoulder_Joint;
	joint left_Elbow;
	joint left_Shoulder_Pivot_Joint;
	joint left_Pelvic_Joint;
	joint left_Knee;
	
	joint right_Shoulder_Joint;
	joint right_Elbow;
	joint right_Shoulder_Pivot_Joint;
	joint right_Pelvis_Joint;
	joint right_Knee;
	
	public Person(){
		torso=new limb("Torso",6,60,180);
		neck=new limb("Neck",6,22.5,0);
		head=new limb("Head",40,10,0);
		left_Humerus=new limb("Left Humerus",4,30,-150);
		right_Humerus=new limb("Right Humerus",4,30,150);
		left_Radius = new limb("Left Radius",4,30,180);
		right_Radius = new limb("Right Radius",4,30,180);
		left_Clavicle=new limb("Left Clavicle",4,15,-90);
		right_Clavicle=new limb("Right Clavicle",4,15,90);
		left_Femur=new limb("Left Femur",5,40,-150);
		right_Femur=new limb("Right Femur",5,40,150);
		left_Tibia=new limb("Left Tibia",5,40,180);
		right_Tibia=new limb("Right Tibia",5,40,180);
		System.out.println("Person creation complete.");
		System.out.println("I will call him bobertson.");
	}
	
	public void draw(Graphics2D g){
	torso.draw(g);
	neck.draw(g);
	left_Clavicle.draw(g);
	right_Clavicle.draw(g);
	left_Humerus.draw(g);
	right_Humerus.draw(g);
	right_Radius.draw(g);
	left_Radius.draw(g);
	left_Femur.draw(g);
	right_Femur.draw(g);
	left_Tibia.draw(g);
	right_Tibia.draw(g);
	head.draw(g);
	}
//+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=	
	class limb{
		public String name;
		public Color color;
		public Point2D center = new Point2D.Double();
		public boolean rounded;
		public double thickness;
		public double angle;
		public double length;
		private boolean automatic;
		public Point2D end = new Point2D.Double();
		//+=+=+=+=+=+=+=+=+=+=+=+=
		public limb(String name_){
			color=Color.black;
			center.setLocation(100,100);
			rounded = true;
			thickness = 4;
			angle = 180;
			length = 10;
			name=name_;
			automatic = true;
		}
		public limb(String name_,double t,double l, double a){
			color=Color.black;
			center.setLocation(100,100);
			rounded = true;
			thickness = t;
			angle = a;
			length = l;
			name=name_;
			automatic = true;
		}
		//+=+=+=+=+=+=+=+=+=+=+=+=
		public void setAutomatic(boolean which){
			automatic = which;
		}
		//+=+=+=+=+=+=+=+=+=+=+=+=
		public Point2D end(){
			double a = angle+personAngle+90;
			double newX=(center.getX()+(Math.cos(Math.toRadians(a)*1)*-1*length*size));
			double newY=(center.getY()+(Math.sin(Math.toRadians(a)*-1)*length*size));
			return new Point2D.Double(newX,newY);
		}
		//+=+=+=+=+=+=+=+=+=+=+=+=
		public void draw(Graphics2D g){
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g.setColor(color);
			if(rounded){
				g.setStroke(new BasicStroke((float) (thickness*size), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			}else{
				g.setStroke(new BasicStroke((float) (thickness*size)));
			}
			g.draw(new Line2D.Double(center,end()));
		}
		//+=+=+=+=+=+=+=+=+=+=+=+=
		public void quickSet(Color c, Point2D p, boolean b, double t, double a, double l){
			color = c;
			center = p;
			rounded = b;
			thickness = t;
			angle = a;
			length = l;
		}

	}
	
	class joint{}
}

