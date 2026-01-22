package com.example.utils;

import java.security.SecureRandom;
import java.util.Random;

import com.github.javafaker.Faker;

/**
 * Utility class for generating random test data
 */
public class RandomDataGenerator {
    
    private static final Faker faker = new Faker();
    private static final Random random = new SecureRandom();
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String NUMERIC = "0123456789";
    
    /**
     * Generate random alphanumeric string
     * @param length desired length
     * @return random alphanumeric string
     */
    public static String generateAlphanumeric(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }
    
    /**
     * Generate random customer number (8-10 characters)
     * @return random customer number
     */
    public static String generateCustomerNumber() {
        return "CUST" + generateAlphanumeric(6);
    }
    
    /**
     * Generate random person name
     * @return random name
     */
    public static String generateName() {
        return faker.name().fullName();
    }
    
    /**
     * Generate random first name
     * @return random first name
     */
    public static String generateFirstName() {
        return faker.name().firstName();
    }
    
    /**
     * Generate random last name
     * @return random last name
     */
    public static String generateLastName() {
        return faker.name().lastName();
    }
    
    /**
     * Generate random 10-digit phone number
     * @return phone number
     */
    public static String generatePhoneNumber() {
        // Generate 10-digit number starting with 6-9
        String firstDigit = String.valueOf(6 + random.nextInt(4)); // 6, 7, 8, or 9
        StringBuilder sb = new StringBuilder(firstDigit);
        for (int i = 0; i < 9; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    
    /**
     * Generate random 10-digit mobile number
     * @return mobile number
     */
    public static String generateMobileNumber() {
        return generatePhoneNumber();
    }
    
    /**
     * Generate random email address
     * @return email address
     */
    public static String generateEmail() {
        return faker.internet().emailAddress();
    }
    
    /**
     * Generate random 12-digit Aadhaar number starting from 2-9
     * @return Aadhaar number (12 digits starting with 2-9)
     */
    public static String generateAadhaarNumber() {
        StringBuilder sb = new StringBuilder();
        // First digit must be between 2-9 (not 0 or 1)
        sb.append(random.nextInt(8) + 2); // generates 2-9
        // Remaining 11 digits can be any digit 0-9
        for (int i = 1; i < 12; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    
    /**
     * Generate random 16-digit number (as per requirement)
     * @return 16-digit number
     */
    public static String generate16DigitNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    
    /**
     * Generate random address
     * @return address string
     */
    public static String generateAddress() {
        return faker.address().streetAddress();
    }
    
    /**
     * Generate random full address
     * @return full address
     */
    public static String generateFullAddress() {
        return faker.address().fullAddress();
    }
    
    /**
     * Generate random city
     * @return city name
     */
    public static String generateCity() {
        return faker.address().city();
    }
    
    /**
     * Generate random state
     * @return state name
     */
    public static String generateState() {
        return faker.address().state();
    }
    
    /**
     * Generate random remarks/comments
     * @return random remarks
     */
    public static String generateRemarks() {
        return "Automated test - " + faker.lorem().sentence();
    }
    
    /**
     * Generate random number within range
     * @param min minimum value
     * @param max maximum value
     * @return random number
     */
    public static int generateNumberInRange(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
    
    /**
     * Generate random number with specific length
     * @param length number of digits
     * @return random number as string
     */
    public static int generateRandomNumber(int min, int max) {
        return generateNumberInRange(min, max);
    }
    
    /**
     * Generate random numeric string
     * @param length number of digits
     * @return random numeric string
     */
    public static String generateNumeric(int length) {
        StringBuilder sb = new StringBuilder();
        // First digit should not be 0
        sb.append(random.nextInt(9) + 1);
        for (int i = 1; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    
    /**
     * Generate random decimal number
     * @param integerDigits number of digits before decimal
     * @param decimalDigits number of digits after decimal
     * @return random decimal as string (e.g., "12.45")
     */
    public static String generateDecimal(int integerDigits, int decimalDigits) {
        StringBuilder sb = new StringBuilder();
        // Generate integer part
        sb.append(random.nextInt(9) + 1); // First digit 1-9
        for (int i = 1; i < integerDigits; i++) {
            sb.append(random.nextInt(10));
        }
        sb.append(".");
        // Generate decimal part
        for (int i = 0; i < decimalDigits; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    
    /**
     * Generate random date in DD-MMM-YYYY format (e.g., 01-Jan-2026)
     * @return random date string
     */
    public static String generateDate() {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", 
                          "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int month = random.nextInt(12);
        int day = random.nextInt(28) + 1; // Safe day for all months
        int year = 2020 + random.nextInt(7); // 2020-2026
        return String.format("%02d-%s-%04d", day, months[month], year);
    }
}
