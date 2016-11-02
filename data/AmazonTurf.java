import java.lang.Math;
import java.util.*;

class PlaceHolder{
	
	// LoopsFor Variables ArithmeticOperations ; Methods ; LOOPSDOWHILE ; LoopsFor PrimitiveDataTypes Variables ArithmeticOperations ; ArithmeticOperations ; ArithmeticOperations LoopsFor PrimitiveDataTypes Variables
	public void placeHolderMethod0(){
		for(int i = 0; i < 5; i++)
		{
			int sum = 0;
			sum = sum + i;
			System.out.println(sum);
		}	
	}
	
	// Constants ; ArithmeticOperations Objects
    public void placeHolderMethod1(){
        Double d = 4.5;
        Integer i = 2;

        double sum = d + i;

        System.out.println(sum);            
    }
    
    // PrimitiveDataTypes Variables ; Classes ; Objects BooleanExpressions ARRAYS SWITCH
    public void placeHolderMethod2(){
        int anInt = 7;
        String aStr = "7";
        System.out.println( (anInt + anInt) + aStr );            
    }
    
    // ArithmeticOperations PrimitiveDataTypes ; Methods ; NESTEDLOOPS; Variables  Methods  ACCESSCONTROL  Objects 
    public double max(double x, double y, double z)
    {
        return Math.max(Math.max(x,y), z);
    }
    
    // LOOPSDOWHILE ; LoopsFor LoopsWhile PrimitiveDataTypes ; LoopsFor LoopsWhile ; LoopsWhile LoopsFor ; ArithmeticOperations PrimitiveDataTypes TWODIMENSIONALARRAYS
    public void placeHolderMethod3(){
        for(int i = 0; i <= 10; i++)
        {
            System.out.println(i);
        }
        
        int i = 0;

        while(i <= 10)
        {
            System.out.println(i);
            i++;
        }
    }
    
    // Exceptions ; Variables IfElse ; LoopsWhile ; Variables BooleanExpressions IfElse ; BooleanExpressions ; Variables BooleanExpressions
    public void placeHolderMethod4(){
        int x = 1;
        int y = 2;

        if(x != y)
            System.out.println("x != y");

        if(x == y)
            System.out.println("x = y");
    }
    
    // LoopsFor ; PrimitiveDataTypes ; LoopsFor ; LoopsFor ; LoopsFor ; Objects LoopsFor ArithmeticOperations
    public void printFactorial(int aNumber){
        int factorial = 1;

        for(int i = 1; i <= aNumber; i++)
        {
            factorial = factorial * i;
        } 
        System.out.println(factorial);
    }
    
    // LoopsFor ; LoopsFor Arrays ; Interfaces ;for (double e : Arrays); Variables ; ArithmeticOperations ; ArrayList; ArithmeticOperations ; ArrayList ; ArrayList ; ArrayList ; ArrayList
    public void printArrayListSum(){
        
        ArrayList<Double> aList = new ArrayList<Double>();
        aList.add(15.5);
        aList.add(21.7);
        aList.add(23.5);
        double sum = 0;
        for(Double e : aList)
        {
            sum = sum + e;
        }
        System.out.println(sum);        
    }
    
    // Arrays ArrayList ; LoopsFor ArrayList ; ArrayList ; LoopsFor
    public void iterateArrayList(){
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(1.1);
        list.add(2.2);
        list.add(3.3);
        list.remove(0);
        for(Double d : list)
            System.out.println(d);
    }
    
    // IfElse ; ArithmeticOperations IfElse NestedLoops ; Switch ; Strings ArithmeticOperations IfElse Variables ;10; Strings ; Strings  ; AccessControl  ArithmeticOperations  Classes  Variables  Constants  BooleanExpressions  IfElse 
    public void printTextLength(){
    
        int myYear = 2015;
        String myText = new String("Sun Devil!");
        int result = 0;

        if (myText.length() > 20)
        {
        result = 1;
        if (myText.length() < 30 && myYear >= 2008)
          result += 5;
        }
        else{
        if (myYear >= 2000)
          result += 10;
        else
          result += 100;
        }
        System.out.println(result);
    }
    
    // Objects Arrays ;a; Objects ; ArrayList
    public void printLetterStack(){
        Stack letterStack = new Stack();

        letterStack.push("X");
        letterStack.push("Y");
        letterStack.push("Z");

        letterStack.pop();

        System.out.println(letterStack);
    }
    
    // Arrays ; ArithmeticOperations Constants Variables ; BooleanExpressions Thread ;Variables;ArithmeticOperations;ArithmeticOperations
    public static void main(String[] args) {
        int i = 14;
        int j = 20;
        int k;
        k = j / i * 7 % 4;
        System.out.println(k);
    }
}



