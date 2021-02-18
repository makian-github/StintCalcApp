package com.example.stintcalcapp;

import android.app.Application;

public class StintData extends Application {

        private String testString = "default";
        private int testInt = 0;

        @Override
        public void onCreate() {
            super.onCreate();
        }

        public String getTestString() {
            return testString;
        }

        public void setTestString(String str) {
            testString = str;
        }

        public int getTestInt() {
            return testInt;
        }

        public void setTestInt(int testInt) {
            this.testInt = testInt;
        }
    }
}
