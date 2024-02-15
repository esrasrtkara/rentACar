package com.rentACar.rentACar.services.constants;


import jakarta.servlet.http.PushBuilder;

public class Messages {
    public static String SAME_BRAND_EXISTS ="The same name brand cannot be added!";
    public static String SAME_MODEL_EXISTS ="Model with the same name cannot be added!";
    public static String BRAND_ID_NOT_FOUND ="Brand ID not found";
    public static String SAME_COLOR_EXISTS = "The same name color cannot be added!";
    public static String SAME_COLOR_CODE_EXISTS = "The same code color cannot be added";
    public static String CHECK_IF_PLATE_FORMAT = "Plate number should match Turkish plate format";
    public static String SAME_CAR_PLATE_EXISTS = "The same license plate cannot be registered";
    public static String CHECK_IF_MODEL_ID_ZERO = "Model ID cannot be less than zero.";
    public static String CHECK_IF_COLOR_ID_ZERO = "Color ID cannot be less than zero.";
    public static String CHECK_IF_MODEL_ID = "Model ID not found in database";
    public static String CHECK_IF_COLOR_ID = "Color ID not found in database";
    public static String CHECK_IF_USER_ID = "User ID not found in database";
    public static String CHECK_IF_RENTAL_ID ="Rental ID not found in database";
    public static String CHECK_IF_CAR_ID ="Car ID not found in database";
    public static String CHECK_IF_25_DAY ="A vehicle can be rented for a maximum of 25 days.";
    public static String CHECK_IF_END_DATE_FOR_STARTDATE ="The end date cannot be later than the start date.";



    public static String ADDED_BRAND = "Brand added.";
    public static String UPDATED_BRAND = "Brand updated.";
    public static String DELETED_BRAND = "Brand deleted";

    public static String ADDED_COLOR ="Color added.";
    public static String UPDATED_COLOR ="Color updated";
    public static String DELETED_COLOR ="Color deleted";

}
