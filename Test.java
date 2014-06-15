import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Test extends Applet implements ActionListener{
Person player = new Person();
Timer animate = new Timer(10,this);
public void init(){
animate.start();
setSize(1280,700);
}
public void paint(Graphics g2){
	Graphics2D g = (Graphics2D) g2;
	player.draw(g);
}
@Override
public void actionPerformed(ActionEvent arg0) {
repaint();
	
}
}
