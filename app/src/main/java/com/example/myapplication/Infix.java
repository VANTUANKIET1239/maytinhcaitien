package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Infix {
    public static String[] chuyendoi(String k){
        Stack<Character> kiet = new Stack<>();
        List<String> kiupketqua = new ArrayList<>();
        List<String> kiup = new ArrayList<>();
        String tam = "";

        for(int i = 0; i < k.length(); i++){
            if(!(k.charAt(i) == '*' || k.charAt(i) == '-' || k.charAt(i) == '+' || k.charAt(i) == '/')){
                tam += String.valueOf(k.charAt(i));
            }
            else {
                kiup.add(tam);
                tam = "";
                kiup.add(String.valueOf(k.charAt(i)));
            }
            if(i == k.length() - 1){
                kiup.add(tam);
            }
        }
        String[] doimang = kiup.toArray(new String[0]);
        for(int i = 0; i < doimang.length; i++){
            char c = doimang[i].charAt(0);
            if(Character.isLetterOrDigit(c)) kiupketqua.add(doimang[i]);
            else {
                while(!kiet.isEmpty() && doi(c) <= doi(kiet.peek())){
                    kiupketqua.add(String.valueOf(kiet.pop()));
                }
                kiet.push(c);

            }
        }
        while(!kiet.isEmpty()){
            kiupketqua.add(String.valueOf(kiet.pop()));
        }
        return kiupketqua.toArray(new String[0]);
    }
    private static int doi(char operator){
        switch (operator){
            case '+':
            case '-':
                 return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }
    public static double tinhtoan(String[] postfix){
        Stack<String> kiet = new Stack<>();
        for(int i = 0; i < postfix.length; i++){
            char c = postfix[i].charAt(0);
            if(Character.isLetterOrDigit(c)) kiet.push(String.valueOf(postfix[i]));
            else if(c == '+'|| c == '*' || c == '/' || c == '-'){
                double num1 = Double.valueOf(kiet.pop());
                double num2 = Double.valueOf(kiet.pop());
                switch (c){
                    case '+': kiet.push(String.valueOf(num2 + num1));break;
                    case '-': kiet.push(String.valueOf(num2 - num1));break;
                    case '/': kiet.push(String.valueOf(Double.valueOf(num2 / num1)));break;
                    case '*': kiet.push(String.valueOf(num2 * num1));break;
                }
            }
        }
        return Double.valueOf(kiet.peek());
    }

}
