package com.example.convertidores.logic.currency;

import javax.swing.*;
import java.text.DecimalFormat;

/**
 * This class will take care of doing the conversion based on what the user chosen
 */
public class Calculation {
    /**
     * This function takes the value to convert and the way to do it, also invokes the conversion rate
     * from the Class CurApiCall, then does the calculation and use the showConversionResult from MainMenu
     * to show the conversion result.
     *
     * @param numToConvert this is the value to be converted like $80 dolars for  example
     * @param wayToConvert which especifies how to convert the value for instance "De Dolar a Pesos"
     */
    public  static void CalculateConversion(Float numToConvert, String wayToConvert){
        Float rate;
        String isoRate;
        Float result;
        //get the conversion rate
        if(wayToConvert.toLowerCase().contains("dolar")){
            rate = CurAPICall.getInstance().getDolarRate();
            isoRate = "Dolares";
        } else if (wayToConvert.toLowerCase().contains("euro")) {
            rate = CurAPICall.getInstance().getEuroRate();
            isoRate = "Euros";
        }else if (wayToConvert.toLowerCase().contains("libras")) {
            rate = CurAPICall.getInstance().getPoundRate();
            isoRate = "Libras";
        } else if (wayToConvert.toLowerCase().contains("yen")) {
            rate = CurAPICall.getInstance().getYenRate();
            isoRate = "Yenes";
        }else if (wayToConvert.toLowerCase().contains("won")) {
            rate = CurAPICall.getInstance().getKoreanWonRate();
            isoRate = "Wones";
        }else{
           //TODO mostrar un sanckbar del error
            return;
        }
        //Now determine the way to convert | should I multiply or divide

        DecimalFormat df = new DecimalFormat("#.##");
        DecimalFormat df2 = new DecimalFormat("#.#####");

        if (wayToConvert.split(" ")[1].equals("Pesos")){
            result = Float.valueOf(df.format(numToConvert * Float.parseFloat(df2.format(rate))));
        }else{
            result = Float.parseFloat(df.format(numToConvert / Float.parseFloat(df2.format(rate))));
        }

        //TODO invocar la Activity que muestra el resultado
    }
}
