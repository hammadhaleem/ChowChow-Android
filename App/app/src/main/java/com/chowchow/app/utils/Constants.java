package com.chowchow.app.utils;

/**
 * Created by jackychan on 16/8/15.
 */
public class Constants {
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
