package demoGAME;

import java.awt.Component;

import javax.swing.JFrame;

public class MainClass {

	public static void main(String[] args) {
      JFrame obj=new JFrame();
        GamePlay gameplay =new GamePlay();
        obj.add(gameplay);
       obj.setSize(700,600);
       obj.setLocationRelativeTo(null);
       obj.setTitle("BrickBreaker");
       obj.setResizable(false);
       obj.setVisible(true);
       obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
 }
}
	

	


