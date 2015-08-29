package com.chowchow.app.utils;

/**
 * Created by jackychan on 16/8/15.
 */
public class Constants {

    public static final String GCM_SENDER_ID = "314057307913"; // project id
    public static final String regKey = "czIcOhMgyQ0:APA91bE8klky_SSCSB_XC-2wkAP2f9q1u3w0TeioAN0GqqViNSSCMmvg9NNkHPvUtOcgWF_1usJSglurZmnsV_VtM1V1TUOAVnDREKC8xfq25fvPdB8xauR1jEtHs4fqrP5hcPpZ3kJt";
    public static final String API_KEY = "AIzaSyC4zhYlw4VYUKu4nQ779QaZFEbmEONFTwo"; // api key
    
    public enum MainFragment {
        SELECT_FRIENDS(0),
        HISTORY(1),
        SETTINGS(2);

        private int value;

        MainFragment(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

    }
} 
