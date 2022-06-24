package com.example.indi_pro3;

public class TAGS {

    public static ItemMed MEDS = null;

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_DESCRIPTION_2 = "description2";
    public static final String TAG_PRICE = "price";
    public static final String TAG_RESOURCE_ID = "res_id";
    public static final String TAG_CATEGORY_NAME = "category";
    public static final String TAG_SIZE = "size";
    public static final String TAG_POWER = "power";
    public static final String TAG_AGE = "age";
    public static final String TAG_QTY = "qty";


    public static boolean isFromOrders = false;


    public static String getMultiplyPrice(String price, String numOfMultiply) {

        int varPrice = Integer.parseInt(price);
        int varNum = Integer.parseInt(numOfMultiply);

        int result = varPrice * varNum;

        return String.valueOf(result);
    }
}
