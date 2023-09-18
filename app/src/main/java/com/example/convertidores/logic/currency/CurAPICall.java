package com.example.convertidores.logic.currency;

import com.google.gson.Gson;
import okhttp3.*;
import java.io.IOException;

/**
 * This class use the singleton pattern to call  the API https://currencyapi.com/docs
 * in order to get the conversion rate that is updated daily, avoiding entering
 * the value manually
 */
public final class CurAPICall {

    private static CurAPICall instance;
    private Float dolarRate;
    private Float euroRate;
    private Float poundRate;
    private Float yenRate;
    private Float koreanWonRate;

    public Float getDolarRate() {
        return dolarRate;
    }

    public Float getYenRate() {
        return yenRate;
    }

    public Float getEuroRate() {
        return euroRate;
    }

    public Float getPoundRate() {
        return poundRate;
    }

    public Float getKoreanWonRate() {
        return koreanWonRate;
    }

    /**
     * This function handle all the api call process  and  set conversion rates
     * @Return set the Currencies Conversion Rates
     */
    public void setRates(){
        //here i set the url for the call
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("api.currencyapi.com")
                .addPathSegment("v3")
                .addPathSegment("latest")
                .addQueryParameter("apikey","cur_live_skzpOTimDeosr1RlFsb1nXrus9el6qF8zBCwLoaH")
                .addQueryParameter("base_currency","COP")
                .build();

        //here the http client and how will be the call
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(httpUrl).build();

        //set the var to catch the response
        Response response = null;
        String jsonData = "";

        try {
            //send the http request and save the response
            response = client.newCall(request).execute();
        }catch (Exception e){
            System.out.println("Error al llamar la API:  "+e);
        }

        System.out.println("El codigo es: "+response.code());

        try {
            //try to convert the response to a String
            jsonData = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Serialize the Json response
        Gson gson = new Gson();
        Rates rates =  gson.fromJson(jsonData, Rates.class);
        /*
         * set the values
         * "USD"
         * "EUR"
         * "GBP"
         * "JPY"
         * "KRW"
         * */
        this.dolarRate = Float.parseFloat(rates.data.get("USD").value);
        this.euroRate = Float.parseFloat(rates.data.get("EUR").value);
        this.poundRate = Float.parseFloat(rates.data.get("GBP").value);
        this.yenRate =  Float.parseFloat(rates.data.get("JPY").value);
        this.koreanWonRate = Float.parseFloat(rates.data.get("KRW").value);
    }
    private CurAPICall() {
    }

    public static CurAPICall getInstance() {
        if (instance == null) {
            instance = new CurAPICall();
        }
        return instance;
    }

    //DecimalFormat df = new DecimalFormat("#.#####");
    //df.setRoundingMode(RoundingMode.FLOOR);

}
