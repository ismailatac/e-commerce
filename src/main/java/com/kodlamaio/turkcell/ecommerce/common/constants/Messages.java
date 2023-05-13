package com.kodlamaio.turkcell.ecommerce.common.constants;

public class Messages {
    public static class Sale {
        public static final String NotExists = "SALE_NOT_EXISTS";
    }

    public static class Invoice {
        public static final String NotExists = "INVOICE_NOT_EXISTS";
    }

    public static class Payment {
        public static final String Incorrect = "CARD_INFORMATION_INCORRECT";
        public static final String AlreadyExists = "CARD_NUMBER_ALREADY_EXISTS";
        public static final String NotEnoughBalance = "BALANCE_NOT_ENOUGH";
        public static final String NotFound = "PAYMENT_INFORMATION_NOT_FOUND";
    }

    public static class Product {
        public static final String DescriptionLength = "PRODUCT_DESCRIPTION_CANNOT_LESS_10_OR_LARGER_50";
        public static final String LessThanZero = "CANNOT_LESS_THAN_ZERO";
    }


}
