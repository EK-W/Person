import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

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
		
		upper_neck=new joint(neck,head,false,true);
		lower_neck=new joint(torso,neck,true,true);
		left_Shoulder_Joint = new joint(left_Clavicle,left_Humerus,false,true);
		right_Shoulder_Joint = new joint(right_Clavicle,right_Humerus,false,true);
		left_Elbow = new joint(left_Humerus,left_Radius,false,true);
		right_Elbow = new joint(right_Humerus,right_Radius,false,true);
		left_Shoulder_Pivot_Joint = new joint(torso,left_Clavicle,true,true);
		right_Shoulder_Pivot_Joint = new joint(torso,right_Clavicle,true,true);
		left_Pelvic_Joint = new joint(torso,left_Femur,false,true);
		right_Pelvis_Joint = new joint(torso,right_Femur,false,true);
		left_Knee=new joint(left_Femur,left_Tibia,false,true);
		right_Knee=new joint(right_Femur,right_Tibia,false,true);
		System.out.println("Person creation complete.");
		System.out.println("I will call him bobertson.");
	}
	
	public void draw(Graphics2D g){
	left_Shoulder_Pivot_Joint.connect();
	right_Shoulder_Pivot_Joint.connect();
	left_Shoulder_Joint.connect();
	right_Shoulder_Joint.connect();
	right_Elbow.connect();
	left_Elbow.connect();
	left_Pelvic_Joint.connect();
	right_Pelvis_Joint.connect();
	left_Knee.connect();
	right_Knee.connect();
	lower_neck.connect();
	upper_neck.connect();
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
		private String name;
		public Color color;
		public Point2D center = new Point2D.Double();
		public boolean rounded;
		public double thickness;
		public double angle;
		public double length;
		//+=+=+=+=+=+=+=+=+=+=+=+=
		public limb(String name_){
			color=Color.black;
			center.setLocation(100,100);
			rounded = true;
			thickness = 4;
			angle = 180;
			length = 10;
			name=name_;
		}
		public limb(String name_,double t,double l, double a){
			color=Color.black;
			center.setLocation(100,100);
			rounded = true;
			thickness = t;
			angle = a;
			length = l;
			name=name_;
		}
		//+=+=+=+=+=+=+=+=+=+=+=+=
		public void pointAt(Point2D at){
			angle = Math.toDegrees(Math.atan2((center.getY() - at.getY()), (center.getX() - at.getX())))-90;
			}
		//+=+=+=+=+=+=+=+=+=+=+=+=
		public String getName(){
			return name;	
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
	
	class joint{
		public limb dominantLimb;
		public limb agreeingLimb;
		public boolean dominantCenter;
		public boolean agreeingCenter;
		public boolean connected;
		
		public joint(limb dominant, limb agreeing){
			create(dominant,agreeing,false,true);
		}
		public joint(limb dominant, boolean dc, limb agreeing, boolean ac){
			create(dominant,agreeing,dc,ac);	
		}
		public joint(limb dominant,limb agreeing, boolean dc, boolean ac){
			create(dominant,agreeing,dc,ac);	
		}
		
		public void Dominant_Center(){dominantCenter=true;	}
		public void Dominant_End(){dominantCenter=false;	}
		public void Agreeing_Center(){agreeingCenter=true;	}
		public void Agreeing_End(){agreeingCenter=false;	}
		
		private void create(limb dominant, limb agreeing, boolean dc, boolean ac){
		connected=true;
		dominantLimb = dominant;
		agreeingLimb = agreeing;
		dominantCenter=dc;
		agreeingCenter=ac;
		}
		
		public void connect(){
		if(connected){
			Point2D dominantPoint;
			if(dominantCenter)dominantPoint = dominantLimb.center;
			else dominantPoint = dominantLimb.end();
		if(agreeingCenter){
			agreeingLimb.center.setLocation(dominantPoint);
		}else{
			double tempAngle = degreesTo(agreeingLimb.end(),agreeingLimb.center);
			agreeingLimb.center.setLocation(findCenter(tempAngle,dominantPoint));
		}
		}
		}
		private Point2D findCenter(double angle_,Point2D thing){
			double a = angle_+personAngle+90;
			double newX=(thing.getX()+(Math.cos(Math.toRadians(a)*-1)*agreeingLimb.length*size));
			double newY=(thing.getY()+(Math.sin(Math.toRadians(a)*-1)*agreeingLimb.length*size));
			return new Point2D.Double(newX,newY);
		}
		private  double degreesTo(Point2D from,Point2D at){
			return (Math.toDegrees(Math.atan2((from.getY() - at.getY()), -(from.getX() - at.getX()))))-90;
		}
		public void breakConnection(){
			connected=false;
		}
		public void fixConnection(){
			connected=true;
		}
	}
	class tool{
		
	}
}

