package com.example.abdul.myapplication;

import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;



public class ShuntingYard {

    private LinkedList<String> outputQue = new LinkedList<String>();
    private LinkedList<String> inputQue = new LinkedList<String>();
    private Stack<String> opStack = new Stack<String>();

    // Public functions
    public ShuntingYard(String str)
    {
        StringTokenizer strTok = new StringTokenizer(str, "+-*/", true);
        while(strTok.hasMoreTokens())
        {
            this.inputQue.addLast(strTok.nextToken().trim());
        }
    }

    public String runner()
    {
        String currSymbol;
        while(this.inputQue.size() > 0)
        {
            currSymbol = this.inputQue.removeFirst();
            if(this.checkOp(currSymbol))
            {
                while(!this.opStack.empty()  && this.checkPriority(this.opStack.peek()) >= this.checkPriority(currSymbol))
                {
                    this.outputQue.addLast(this.opStack.pop());
                }
                this.opStack.push(currSymbol);

            }
            else
            {
                //we must be dealing with a number
                this.outputQue.addLast(currSymbol);
            }
        }

        //clear the opStack to the outputQue
        while(!this.opStack.empty())
        {
            this.outputQue.addLast(this.opStack.pop());
        }

        return this.calculate();
    }



    ///Private function for internal use
    private String calculate()
    {
        double num1;
        double num2;
        Stack<String> mathStack = new Stack<String>();
        for(String str : this.outputQue)
        {
            if(this.checkOp(str))
            {
                num2 = Double.parseDouble(mathStack.pop());
                num1 = Double.parseDouble(mathStack.pop());
                mathStack.push("" + this.operation(num1, num2, str));
            }
            else
            {
                mathStack.push(str);
            }
        }
        return mathStack.pop();
    }

    private int checkPriority(String opr)
    {
        if(opr.equals("+") || opr.equals("-"))
        {
            return 2;
        }
        else
        {
            //return 3 for other operators /*
            return 3;
        }

    }

    private boolean checkOp(String symbol)
    {
        return "+-*/".indexOf(symbol) >= 0;
    }

    private double operation(double num1, double num2, String opr)
    {
        if(opr.equals("+"))
        {
            return num1 + num2;
        }
        else if(opr.equals("-"))
        {
            return num1 - num2;
        }
        else if(opr.equals("*"))
        {
            return num1 * num2;
        }
        else
        {
            return num1 / num2;
        }

    }




}
