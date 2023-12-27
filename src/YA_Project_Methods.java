/**
 * Program:		YA_Project_Methods.java
 * Purpose:		This is a .java file with the functions needed for
 *              the final project.
 * Author:		Yoseff Abu Dayeh
 * Date:		Nov 4, 2022
 */

import java.util.*;
import java.io.*;

public class YA_Project_Methods 
{    
    public static String getText(Scanner input) 
    {
        /**
         * Name:        getText
         * Description: This will get the text from the user, uppercase it and then return it.
         * Parameters:  input: This is a scanner object which will get the input from the keyboard.
         * Return:      This Method will return the UpperCased String.
         */
        System.out.print("Enter the ciphertext message you wish to encipher: ");
        return input.nextLine().toUpperCase();
    } // end getText
    
    public static String keyword(Scanner input)
    {
        /**
         * Name:        keyword
         * Description: This method will get a keyword from the user, make sure it's valid and then return it
         * Parameters:  input: This is a scanner object which will get the input from the keyboard.
         * Return:      This Method will return the keyword as a string.
         */

        boolean invalidInput = true;
        String KeyWord = ""; // Declares and assigns a dummy value to the string Keyword
        while (invalidInput)
        {
            System.out.print("Enter a keyword of only letters, with no digits, spaces, or punctuation marks: ");
            KeyWord = input.nextLine().toUpperCase();
            char[] lettersKeyword = KeyWord.toCharArray();
            boolean wrongCharacter = false; // This boolean value will take note whether the user inputted a wrong character in the string.
                        // I opted to use a boolean value and then comparing algorithm since something similar was used in class
            for (char Letter : lettersKeyword)
            {
                if (Letter == ' ')
                {
                    System.out.println("\nPlease input a keyword that does NOT have a space.\n");
                    wrongCharacter = true; // This will tell the program that there was a value we don't want on what the user inputted.
                    break; // Breaking the loop because we only need to find 1 issue with the keyword at a time.
                }
                else if (Character.isDigit(Letter))
                {
                    System.out.println("\nPlease input a keyword that does NOT contain a number.\n");
                    wrongCharacter = true; // This will tell the program that there was a value we don't want on what the user inputted.
                    break; // Breaking the loop because we only need to find 1 issue with the keyword at a time.
                }
                else if (!(Character.isLetter(Letter)))
                {
                    System.out.println("\nPlease input a keyword that does NOT have any type of special characters.\n");
                    wrongCharacter = true; // This will tell the program that there was a value we don't want on what the user inputted.
                    break; // Breaking the loop because we only need to find 1 issue with the keyword at a time.
                }
                
                else 
                {
                    continue; // This continue statement can just be ommited but it makes the CPU skip some lines of text so it's good in that regard.
                }
            }
            if (KeyWord.length() == 0) // ERROR Handling to make sure that the user didn't inputted an empty string.
            {
                System.out.println("\nPlease input an answer and not just press enter.\n");
                wrongCharacter = true; // This will tell the program that there was a value we don't want on what the user inputted.
                continue; // Goes to the next interation instead of ending the program.
            }
            if (wrongCharacter == false) // This conditional statement will run only if the for loop detected on invalid character.
            {
                invalidInput = false; // Changes the value in the loop to make it stop doing iterations.
            } 
        }
        return KeyWord;
    } // end keyword

    public static int shiftGenerator(String keyword) 
    {
        /**
         * Name:        shiftGenerator
         * Description: This method will generate a shift value to shift the letters of a word.
         * Parameters:  keyword: This is the keyword that will determine the value to shift.
         * Return:      This method will return the amount of values to shift
         */
        char[] ASCIICode = keyword.toCharArray(); // Makes the keyword a character array
        int shiftValue = 0; // Declaring and assigning the shiftValue to be used later in the code. 
        for (int n:ASCIICode)
        {
            shiftValue+= n; // Since the char values can be thought of as integers then I can just add them
        }
        
        shiftValue %=26; // Gets the modulus which will determine the shift value
        if (shiftValue == 0)
        {
            shiftValue++;
        } // This makes it so that the shift value becomes 1 if after the modulus operator it is 0.
        return shiftValue;
    } // end of shiftGenerator
    
    public static String SCSEncipherText(String plainText, String KeyWord)
    {
    	/**
         * Name:        SCSEncipherText (Super Caesar Salad Encipher Text)
         * Description: This method will encipher the plain text and make it enciphered text
         * Parameters:  plainText: this is a string variable that contains the uncrypted message
         *              KeyWord: This is a string variable that has the keyword.             
         * Return:      This method will return the already enciphered text 
         */
    	char[] Text = plainText.toCharArray();
    	char[] KW = KeyWord.toCharArray();
    	String enText = "" ;
    	int Counter = 0;
    	char c = ' ';
    	// Declaring and initializing some variables.
    	for (int i = 0;i < Text.length;i++)
    	{
    		if (Text[i] >64 && Text[i] <91) // Checks if the character is a letter (only upper case, but they can only input upper case so it doesn't matter)
            {
    			
    			int SpicyShift = KW[Counter] - 65; // Gets the shift value from the Keyword 
    			int Letter = Text[i];  // Gets the ASCII value of the word, which will be useful when shifting the value
    			c = (char)(Letter + SpicyShift); // Adds the shift value to the ASCII Value to get the shifted value.
    			
    			Counter++; // Will increase and grab the next value from the keyword
    			if (Counter >KW.length-1)
    			{
    				Counter = 0;
    			} // Makes sure that the index of the keyword doesn't goes out of bounds
    			
                if (c > 90)
                {
                    c -= 26; // Makes it so that if the shift value goes past Z then it will go back to A and start again
                }
            }
    		else 
    		{
    			c = Text[i]; // It just passes down any non-letter character
    		}
    		enText += c; // Adds the shifted value to the new enciphered word.
    	}
    	return enText;
    }
    
    public static String encipherText(String plainText, int shift)
    {
        /**
         * Name:        encipherText
         * Description: This method will encipher the plain text and make it enciphered text
         * Parameters:  plainText: this is a string variable that contains the uncrypted message
         *              shift: this is an integer that contains the amount of character that the plainText will shift in values.             
         * Return:      This method will return the already enciphered text 
         */
        
        char[] lettersText = plainText.toCharArray(); // Makes the string a character array
        String encipheredText = "";
        char letter; // Declares the variable letter which will be used assigned shortly
        for (char c:lettersText)
        {
            if (c >64 && c <91) // Checks if the character is a letter (only upper case, but they can only input upper case so it doesn't matter)
            {
                letter = (char)(((int)c) + shift); // Shifts the letter to the next character. There are a lot of brackets because without 
                                                   // type-casting to int then back to char it will crash since it it won't be able to add.
                if (letter > 90)
                {
                    letter -= 26; // Makes it so that if the shift value goes past Z then it will go back to A and start again
                }
            }
            else
            {
                letter = c; // Will pass down the special characters like ' ' or '!' instead of shifting their values
            }
            encipheredText += letter; // It will add the already shifted character to the text to the variable that stores the enciphered text.
        }
        
        return encipheredText;
    } // end of encipherText

    public static String SCSDecipherText(String cText, String KeyWord)
    {
    	/**
         * Name:        SCSDecipherText (Super Caesar Salad Encipher Text)
         * Description: This method will decipher the plain text and make it plain text
         * Parameters:  cText: this is a string variable that contains the crypted message
         *              KeyWord: This is a string variable that has the keyword.             
         * Return:      This method will return the plaintext 
         */
    	char[] Text = cText.toCharArray();
    	char[] KW = KeyWord.toCharArray();
    	String plainText = "" ;
    	int Counter = 0;
    	char c = ' ';
    	// Declaring and initializing some variables.
    	for (int i = 0;i < Text.length;i++)
    	{
    		if (Text[i] >64 && Text[i] <91) // Checks if the character is a letter (only upper case, but they can only input upper case so it doesn't matter)
            {
    			
    			int SpicyShift = KW[Counter] - 65; // Gets the shift value from the Keyword 
    			int Letter = Text[i];  // Gets the ASCII value of the word, which will be useful when shifting the value
    			c = (char)(Letter - SpicyShift); // Adds the shift value to the ASCII Value to get the shifted value.
    			
    			Counter++; // Will increase and grab the next value from the keyword
    			if (Counter >KW.length-1)
    			{
    				Counter = 0;
    			} // Makes sure that the index of the keyword doesn't goes out of bounds
    			
    			if (c < 65)
                {
                    c += 26; // Makes it so that if the shift value goes past Z then it will go back to A and start again
                }
            }
    		else 
    		{
    			c = Text[i]; // It just passes down any non-letter character
    		}
    		plainText += c; // Adds the shifted value to the new enciphered word.
    	}
    	return plainText;
    }
    
    public static String decipherText(String Text, int Shift)
    {
        /**
         * Name:        decipherText
         * Description: This method will dechipher some enciphered text into plain text
         * Parameters:  Text: This string is the enciphered text
         *              Shift: This integer is the value of the keyword; it will tell us how much to shift each letter.
         * Return:      String: This string will be the alredy deciphered text.
         */

        String plainText = "";
        char[] letterArray = Text.toCharArray();
        char c;
        for(char eachLetter:letterArray)
        {
            if (eachLetter >64 && eachLetter <91) // Checks if the character is a letter (only upper case, but they can only input upper case so it doesn't matter)
            {
                c = (char)(((int)eachLetter) - Shift); // Shifts the letter to the next character. There are a lot of brackets because without 
                                                       // type-casting to int then back to char it will crash since it it won't be able to add.
                if (c < 65)
                {
                    c += 26; // Makes it so that if the shift value goes past A then it will go back to Z and start again
                }
            }
            else
            {
                c = eachLetter; // Will pass down the special characters like ' ' or '!' instead of shifting their values
            }
            plainText += c; // It will add the already shifted character to the text to the variable that stores the enciphered text.
        }
        return plainText;
    }

    public static void writeFile(String name, String Text)
    {
        /**
         * Name:        writeFile
         * Description: This method will write to a file/make the file and then write to that file.
         * Parameters:  Name: This will be the name of the file it will write to.
         *              Text: This is the text that will be written into the file.
         * Return:      Null
         */
        try{
            PrintWriter txtFile = new PrintWriter(name); // Selects the file, if the file doesn't exists it makes it
            txtFile.print(Text); // It writes the text in the file.
            System.out.println("The file was written successfully!"); // Outputs a nice message
            txtFile.close(); // Closes the file
        }
        catch (IOException e)
        {
            System.out.println("\nERROR: " + e.getMessage()); // This will detect the error and give
                                                              // an appropriate message
        }
    }

    public static String readFile(String name) throws FileNotFoundException
    {
        /***
         * Name:        readFile
         * Description: This method will grab the text from the file and return it to the main method.
         * Parameters:  name: This will be the name of the file to be read from
         * Return:      
         */
        File nameFile = new File(name); // Makes a new file from the address given 
        Scanner text = new Scanner(nameFile); // Warning sign is there because we don't close the scanner here. It is 
        									  // not an issue because we close it on the main method.
        return text.nextLine().toUpperCase(); // No need to close it  here since we are closing it in the main method.
    }
}
// end class