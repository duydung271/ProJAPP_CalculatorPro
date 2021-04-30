/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorproject;

/**
 *
 * @author BaoCôngIDOL
 * Calculator has functions: add, sub, equal, clear
 * Only calculate 2 object
 * have history(show all thing we calculate and memory(save all thing we did) 
 */

public class Calculator {
    String FirstNum;
    String Operator;
    String LastNum;

    public Calculator() {
        FirstNum="";
        LastNum="";
        Operator="";
    }
    
    public void delete(){
        if (!LastNum.equals("")){
            Integer b=Integer.parseInt(LastNum);
            b/=10;
            LastNum=b+"";
        }else if (!Operator.equals("")){
            Operator="";
        }else if (!FirstNum.equals("")){
            Integer a=Integer.parseInt(FirstNum);
            a/=10;
            FirstNum=a+"";
        }
    }
    
    public void addElement(String e){
        if (e.charAt(0)<='9'&&e.charAt(0)>='0'){
            if (Operator.length()==0){
                FirstNum+=e;
            }else LastNum+=e;
        }else{
            if (FirstNum.length()==0) return;
            Operator=e;
        }
    }
    
    public String calculate(){
        if (Operator.length()==0) return FirstNum;
        if (FirstNum.length()==0) return "";
        if (LastNum.length()==0) return FirstNum+Operator;
        
        Integer a=Integer.parseInt(FirstNum);
        Integer b=Integer.parseInt(LastNum);
        String res="";
        switch(Operator.charAt(0)){
            case '+':
                res+=a+" + "+b+" = "+(a+b);
                break;
            case '-':
                res+=a+" - "+b+" = "+(a-b);
                break;
        }
        FirstNum="";
        LastNum="";
        Operator="";
        return res;
    }
    public String inScreen(){
        return FirstNum+Operator+LastNum;
    }
    public static void main(String[] args) {
        Calculator c= new Calculator();
        c.addElement("1");
        System.out.println(c.inScreen());
        c.addElement("2");
        System.out.println(c.inScreen());
        c.addElement("-");
        System.out.println(c.inScreen());
        c.addElement("2");
        System.out.println(c.inScreen());
        
        System.out.println(c.calculate());
        
        c.addElement("2");
        System.out.println(c.inScreen());
        
    }
}
