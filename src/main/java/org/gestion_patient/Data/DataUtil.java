package org.gestion_patient.Data;


import org.gestion_patient.crypto.Crypto;

public class DataUtil {

    public static final String KEY= "q#4puta9!am4$fcl";
    public static final String IV = "1zp6@y#ect4?5krx";


    public static Boolean displayBoolean (Boolean elem){
        return elem!=null?elem:null;
    }
    public static String displayString (String elem){return elem!=null?elem:null;}

    public static String displayStringEncrypt (String elem) throws Exception {return elem!=null? Crypto.cryptService(elem):null;}
    public static String displayStringDecrypt (String elem) throws Exception {return elem!=null? Crypto.decryptService(elem):null;}
    public static Integer displayInt (Integer elem){
        return elem!=null?elem:null;
    }
    public static Float displayFloat (Float elem){return elem!=null?elem:null;}

}
