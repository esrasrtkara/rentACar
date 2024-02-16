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
    public static String DELETED_BRAND = "Brand deleted.";

    public static String ADDED_COLOR ="Color added.";
    public static String UPDATED_COLOR ="Color updated.";
    public static String DELETED_COLOR ="Color deleted.";

    public static String ADDED_MODEL ="Model added.";
    public static String UPDATED_MODEL ="Model updated.";
    public static String DELETED_MODEL ="Model deleted.";

    public static String ADDED_CUSTOMER ="Customer added.";
    public static String ADDED_CORPORATE_CUSTOMER ="Corporate customer added.";
    public static String LOGIN="Login successful";
    public static String LOGIN_ERROR="Information is Incorrect.";

    public static String ADDED_CAR="Car added.";
    public static String UPDATED_CAR="Car updated.";
    public static String DELETED_CAR="Car deleted";

    public static String ADDED_COMMENT="Comment added.";
    public static String UPDATED_COMMENT="Comment updated";
    public static String DELETED_COMMENT="Comment deleted";

    public static String DELETED_CORPORATE_CUSTOMER="Corporate customer deleted";
    public static String UPDATED_CORPORATE_CUSTOMER="Corporate customer updated";

    public static String UPDATED_CUSTOMER="Customer updated";
    public static String DELETED_CUSTOMER="Customer deleted";

    public static String ADDED_DISCOUNT="Discount added";
    public static String UPDATED_DISCOUNT="Discount updated";
    public static String DELETED_DISCOUNT="Discount deleted";

    public static String ADDED_INVOICE ="Invoice added.";
    public static String UPDATED_INVOICE= "Invoice updated.";
    public static String DELETED_INVOICE="Invoice deleted.";

    public static String UPDATED_RENTAL="Rental updated.";
    public static String DELETED_RENTAL="Rental deleted.";

    public static String ADDED_TAX_RATE="TaxRate added.";
    public static String UPDATED_TAX_RATE ="TaxRate updated.";
    public static String DELETED_TAX_RATE="TaxRate deleted.";

}
