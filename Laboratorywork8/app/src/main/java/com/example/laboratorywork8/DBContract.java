package com.example.laboratorywork8;

import android.provider.BaseColumns;

public class DBContract {
    private DBContract() {
    }

    public static class DBEntry implements BaseColumns {
        public static final String TABLE_NAME = "people";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_BIRTHDAY = "birthday";
    }
}
