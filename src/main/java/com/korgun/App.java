package com.korgun;

import java.util.Scanner;
import java.util.TreeMap;

public class App {
    public static void main( String[] args )throws Exception{

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение");
        String input = scanner.nextLine();
        System.out.println(calc(input));

    }

    public static String calc(String input) throws Exception{
        Arab arab = new Arab();
        int num1;
        int num2;
        int operandInd = -1;
        String [] operand1 = {"+","-","*","/"};
        String [] operand2 = {"\\+","-","\\*","/"};
        String [] operand3 = input.split("[+\\-*/]");
        if (operand3.length !=2) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        for(int i = 0; i< operand1.length; i++){
            if(input.contains(operand1[i])){
                operandInd = i;
                break;
            }
        }
            if(operandInd==-1) {
                System.out.println("строка не является математической операцией");
            }

        String[] razdelenie = input.split(operand2[operandInd]);

        if(arab.hasNumber(razdelenie[0]) == arab.hasNumber(razdelenie[1])) {
            int a1;
            int b1;
            boolean isArab = arab.hasNumber(razdelenie[0]);
            int result =0;

            if (isArab) {

                a1 = arab.arabToInt(razdelenie[0]);
                b1 = arab.arabToInt(razdelenie[1]);

            } else {


            a1 = Integer.parseInt(razdelenie[0]);
            b1 = Integer.parseInt(razdelenie[1]);
                if(a1>10 || b1>10) throw new Exception("Числа должны быть от 1 до 10");
            }

            if(operand1[operandInd].equals("+")){
                result = (a1 + b1);
            }
            if(operand1[operandInd].equals("-")){
                result = (a1 - b1);
            }
            if(operand1[operandInd].equals("*")){
                result = (a1 * b1);
            }
            if(operand1[operandInd].equals("/")){
                result = (a1 / b1);
            }
            if(isArab){
                if(result <= 0){
                    throw new Exception("Римское число должно быть больше 0");
                } else {
                    System.out.println(arab.intToArab(result));
                }
            } else {
                System.out.println(result);
            }
        } else {
            System.out.println("используются одновременно разные системы счисления ");
        }
        return "";
    }


}

class Arab{
    TreeMap<Character, Integer> treeMap = new TreeMap<>();

    TreeMap<Integer, String> revTreeMap = new TreeMap<>();

    public Arab(){
        treeMap.put('I',1);
        treeMap.put('V',5);
        treeMap.put('X',10);
        treeMap.put('L',50);
        treeMap.put('C',100);
        treeMap.put('D',500);
        treeMap.put('M',1000);

        revTreeMap.put(1000, "M");
        revTreeMap.put(900, "CM");
        revTreeMap.put(500, "D");
        revTreeMap.put(400, "CD");
        revTreeMap.put(100, "C");
        revTreeMap.put(90, "XC");
        revTreeMap.put(50, "L");
        revTreeMap.put(40, "XL");
        revTreeMap.put(10, "X");
        revTreeMap.put(5, "V");
        revTreeMap.put(1, "I");
    }
    boolean hasNumber(String num){
        return treeMap.containsKey(num.charAt(0));
    }

    public String intToArab(int num) {
        String resultArab = "";
        int intArab;
        do {
            intArab = revTreeMap.floorKey(num);
            resultArab += revTreeMap.get(intArab);
            num -= intArab;
        } while (num != 0);
        return resultArab;
    }

    public int arabToInt(String s) {
        int end = s.length() - 1;
        char[] array = s.toCharArray();
        int arab;
        int result = treeMap.get(array[end]);
        for (int i = end - 1; i >= 0; i--) {
            arab = treeMap.get(array[i]);

            if (arab < treeMap.get(array[i + 1])) {
                result -= arab;
            } else {
                result += arab;
            }

        }
        return result;
    }
}