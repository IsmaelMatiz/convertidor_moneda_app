package com.example.convertidores.logic.binary;


import java.util.*;

public class BiCalculation {
    public static void CalculateConversion(String numToConvert, String wayToConvert){

        //First determine if im waiting a decimal or a binary num
        if (wayToConvert.split(" ")[1].equals("Binario")){//-------------------convert from binary to decimal-------------------
            //Divide the number into an array
            //help vars
            Integer result;
            Integer [] numToBeSum = new Integer[numToConvert.length()];
            int y = 0;//auxVar
            for (int i = numToConvert.length()-1; i >= 0; i--){
                numToBeSum[i] = (int) Math.pow(2,(double) y) * Integer.parseInt(String.valueOf( numToConvert.charAt(i)));
                y++;
            }
            //sum the nums to get the final result int
            Optional<Integer> prevResult =  (Arrays.stream(numToBeSum).reduce((acumulador, numero) -> acumulador+numero));
            result = Integer.valueOf(prevResult.get());
            System.out.println(result);
            //finally show the result
            //TODO invocar la Activity que muestra el resultado
        }else {//----------------convert from decimal to binary---------------------
            //help vars
            Integer numAsInt = Integer.parseInt(numToConvert);
            int reduction = numAsInt;
            String prevResult = "";
            //do the convertion and save it in prevResult
            do{
                prevResult += reduction%2;
                reduction /= 2;
            }while (reduction>0);

            //the previous formula requires to show the result in a reverse way
            StringBuilder rev = new StringBuilder(prevResult);

            //finally show the result
            //TODO invocar la Activity que muestra el resultado
        }
    }
}
