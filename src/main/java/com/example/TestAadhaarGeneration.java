package com.example;

import com.example.utils.RandomDataGenerator;

/**
 * Quick test to verify Aadhaar number generation
 */
public class TestAadhaarGeneration {
    public static void main(String[] args) {
        System.out.println("Testing Aadhaar Number Generation");
        System.out.println("==================================");
        System.out.println();
        
        System.out.println("Requirement: 12-digit number starting from 2-9");
        System.out.println();
        
        System.out.println("Generating 10 sample Aadhaar numbers:");
        System.out.println("--------------------------------------");
        
        for (int i = 1; i <= 10; i++) {
            String aadhaar = RandomDataGenerator.generateAadhaarNumber();
            char firstDigit = aadhaar.charAt(0);
            boolean isValid = aadhaar.length() == 12 && firstDigit >= '2' && firstDigit <= '9';
            
            System.out.println(i + ". " + aadhaar + 
                " | Length: " + aadhaar.length() + 
                " | First digit: " + firstDigit + 
                " | Valid: " + (isValid ? "✓" : "✗"));
        }
        
        System.out.println();
        System.out.println("==================================");
        System.out.println("All Aadhaar numbers should:");
        System.out.println("  - Be exactly 12 digits long");
        System.out.println("  - Start with digits 2-9 (not 0 or 1)");
        System.out.println("==================================");
    }
}
