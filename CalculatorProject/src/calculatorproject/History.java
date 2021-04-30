/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

/**
 *
 * @author BaoCôngIDOL
 */
public class History {
    Vector<String> list;

    public History() {
        list= new Vector<String>();
    }
    public Vector<String> getList(){
        return list;
    }
    public void add(String s){
        if (s==null||s.equals("")) return;
        list.add(s);
    }
    
    public void clear(){
        list.clear();
    }
    public void load(){
        try{
            //Doc output tu file python
            FileReader fr = new FileReader("History.txt");
            BufferedReader br = new BufferedReader(fr);

            String math="";
            do{
                math=br.readLine();
                if (math==null) break;
                if (math.equals("")) break;
                list.add(math);
         
            }while(math!=null);
            br.close();
            fr.close();
        }catch(Exception e){
            System.err.println(e);
        }
        
    }
    public void save(){
        try{
            FileWriter writer = new FileWriter("History.txt");
            BufferedWriter buffer = new BufferedWriter(writer);
            for (String s: list){
                buffer.write(s+"\n");
            }
            buffer.close();
            writer.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    
    
    
}
