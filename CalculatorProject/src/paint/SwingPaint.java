/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;
/**
 *
 * @author BaoCôngIDOL
 */
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;

import javax.swing.JPanel;

public class SwingPaint extends JPanel {

    JButton clearBtn;
    DrawArea drawArea;
    ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {
                try {
                    predict();
                }catch(Exception x){
                    System.err.println(x);
                }
                drawArea.clear(); 
            } 
        }
    };
    public DrawArea getDrawArea(){
        return drawArea;
    }
    public void clear(){
        drawArea.clear(); 
    }
    public void Init() {
        // create controls to apply colors and call clear feature
        JPanel controls = new JPanel();

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(actionListener);
       

        // add to panel
        controls.add(clearBtn);
        controls.setLayout(new GridLayout(1, 1));

        // create draw area
        drawArea = new DrawArea();

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        // add to content pane
        add(drawArea, gbc);
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        // add to content pane
//        add(controls, gbc);

//        setLayout(new GridLayout(2,1));
    }

    public SwingPaint() {
        Init();
    }
    
    public String predict()throws IOException, InterruptedException{
        String s="";
        double [][] x=drawArea.getMatrix();
        for (int i=0;i<x.length;i++){
            for (int j=0;j<x[i].length;j++){
                if (x[i][j]>0){
                    x[i][j]=Double.min(x[i][j]+0.5,1.0);
                }
                
                s+=x[i][j];
                if (j!=x[i].length-1) s+=" ";
            }
            if (i!=x.length-1) s+="\n";
        }
        
        //nem input cho python
        FileWriter writer = new FileWriter("Java.txt");
        BufferedWriter buffer = new BufferedWriter(writer);
        buffer.write(s);
        buffer.close();
        writer.close();
        
        
        //Doc output tu file python
        FileReader fr = new FileReader("Python.txt");
        BufferedReader br = new BufferedReader(fr);
 
        String charPre="";
        while ((charPre = br.readLine()) == null) {
            System.out.print("");
        }
        br.close();
        fr.close();
        
        //nem clear file input cho python
        FileWriter writer2 = new FileWriter("Python.txt");
        BufferedWriter buffer2 = new BufferedWriter(writer2);
        buffer2.write("");
        buffer2.close();
        writer2.close();
        
        
        return charPre;
    }
    public static void main(String[] args) throws IOException, InterruptedException{
      
    }
    
    
}
