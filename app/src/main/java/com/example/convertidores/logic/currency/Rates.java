package com.example.convertidores.logic.currency;

import java.util.HashMap;

/**
 * This Class help me to convert the Json to an object easy to handle
 */
public class Rates {
    HashMap<String,InfoRates> data;

    @Override
    public String toString() {
        return "Rates{" +
                "data=" + data +"\n"+
                "}";
    }

    public  class InfoRates{
        String code;
        String value;

        @Override
        public String toString() {
            return this.code+" : {" +
                    "\ncode: "+this.code+
                    "\nvalue: "+this.value+
                    "\n}";
        }
    }
}
