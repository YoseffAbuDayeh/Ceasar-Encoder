/**
 * Program:		YA_Regular_Salad.java
 * Purpose:		Offer better encryption version of the regular salad project
 * Author:		Yoseff Abu Dayeh
 * Date:		Nov 11, 2022
 */

import java.io.FileNotFoundException;
import java.util.*;

public class YA_Spicy_Salad {

    public static void main(String[] args) {

        // Create a Scanner object for obtaining keyboard inputs
        Scanner input = new Scanner(System.in);
        int chosenOption = 3; // Declares and assigns a dummy value to an integer variable that will store the option the user wants to do.
        // Display a title/explanation
        String plainText;
        String KeyWord;
        String enText = "";
        int readText = 4;
        boolean Pass = false; // Another variable that will help on validating input.
        String fileName;
        // Assigning other values that will be used later in the program.
        
        
        System.out.println("This program will help you encipher a message or decipher a coded message.\n");

        //In here using multiple print statements for clarity.
        System.out.println("If enciphering, enter 1 and press ENTER: ");
        System.out.println("If deciphering, enter 2 and press ENTER: ");
        boolean validOption = false; // Variable to make sure the loop for error handling works as expected.
        while (!validOption)
        {       
            System.out.print("Enter 1 or 2: ");
            try 
            { // Start of try statement.
                chosenOption = input.nextInt();
                if (chosenOption < 1 || chosenOption > 2)
                {
                    throw new Exception(); // This will raise an exception if the number is not part of the options.  
                }

                validOption = true;
            } 
            catch (InputMismatchException wrongType)
            {
                System.out.println("\nPlease input an integer number. Either 1 for enciphering or 2 for deciphering.\n");
            }
            catch (Exception OutOfBounds) // It is only needed to print the error message on the catch statements since before the try 
                                          // statement I wrote the option.
            {
                System.out.println("\nPlease choose an option from the available ones. Either 1 for enciphering or 2 for deciphering.\n"); 
            }
            finally
            {
                input.nextLine(); // This will flush the buffer so that it's empty for the next iteration of the loop.
                                  // Not always needed but if they input a text then it will be needed. Used finally since it looks nice 
            } // End of try statements/catch/finally
        } // End of loop

        validOption = false; // Resets the boolean expression to be used later in the code.
        if(chosenOption== 1 )
        { // Start of encipher
            plainText = YA_Project_Methods.getText(input);
            KeyWord = YA_Project_Methods.keyword(input);
            enText = YA_Project_Methods.SCSEncipherText(plainText.toUpperCase(), KeyWord.toUpperCase()); // UpperCase to get rid of unexpected results.
            System.out.printf("\nThe plaintext and enciphered text are as follows: \n%s\n%s%n%n", enText, plainText);
            // Gets and juggles the values from the YA_Projects file and then outputs the enciphered text and plain text.            
            System.out.print("Enciphered text will now be written to a text file\nEnter a file name followed by the extension '.txt'"
            		+ "(example: secret.txt): ");
        	fileName = input.nextLine(); // Gets the name to write the encipher text.
        	char[] nameOfFile = fileName.toCharArray(); // Makes the text an array to be easier to look at.
        	for (int i = 0;i<nameOfFile.length;i++)
        	{ // Start of for loop
        		if (nameOfFile[i] == '.')
        		{
        			if (nameOfFile[i+1] == 't')
        			{
        				if (nameOfFile[i+2] == 'x')
        				{
        					if (nameOfFile[i+3] == 't')
        						Pass = true; // Makes sure that the user writes ".txt" 
        				}
        			}
        		}
        	} // End of for loop
        	if (!Pass)
        	{
        		fileName += ".txt"; // If the user didn't typed .txt, then the program will type it.
        		System.out.println("\nExtension .txt not found. Making it a .txt\n");
        	}
        	
        	YA_Project_Methods.writeFile(fileName, enText);
        	System.out.println("Ciphertext has been written to local directory as " + fileName);
        	// Nice output message
        	
        } // End of encipher
        else // We don't have to specify it's value 2 since there are only two options.
        {
        	System.out.println("We will be deciphering a message.\n"); 
        	System.out.println("If the ciphertext will be entered via keyboard, enter 1\n"
        			+ "If the ciphertext will be entered from a file, enter 2");
        	while (!validOption)
        	{
        		System.out.print("Enter 1 or 2: ");
        		try 
                { 	// Makes sure that the user inputs a valid option.
                    readText = input.nextInt(); 
                    if (readText < 1 || readText > 2)
                    {
                        throw new Exception(); // This will raise an exception if the number is not part of the options.  
                    }

                    validOption = true; // Ends the loop
                }
                catch (InputMismatchException wrongType)
                {
                    System.out.println("\nPlease input an integer number. Either 1 for typing it or 2 from file.\n");
                }
                catch (Exception OutOfBounds) // It is only needed to print the error message on the catch statements since before the try 
                                              // statement I wrote the option.
                {
                    System.out.println("\nPlease choose an option from the available ones. Either 1 for typing it or 2 from file.\n"); 
                }
                finally
                {
                    input.nextLine(); // This will flush the buffer so that it's empty for the next iteration of the loop.
                                      // Not always needed but if they input a text then it will be needed. Used finally since it looks nice 
                }
        	}
        	if (readText == 1)        	
        	{
        		enText = YA_Project_Methods.getText(input);
        	}
        	else 
        	{ // Start else statement
        		while (!Pass) // Loops for as long as needed, until the file was read without errors.
        		{
        			try 
	        		{
	            		System.out.print("Enter the name of the file containing the ciphertext (with the .txt): ");	
	            		fileName = input.nextLine(); 
	            		enText = YA_Project_Methods.readFile(fileName); // Calls the method. Will raise an exception if the file wasn't found.
	            		Pass = true; // Once the text has been read successfully it will change the variable from the loop making it stop.
	        		}
	        		catch (FileNotFoundException ex)
	        		{
	        			System.out.println("That file was not found. Let's try that again, shall we?\n");
	        		}
        		}
        	}// End else statement
        	KeyWord = YA_Project_Methods.keyword(input);
        	plainText = YA_Project_Methods.SCSDecipherText(enText.toUpperCase(), KeyWord.toUpperCase());// UpperCase to get rid of unexpected results.
            // Obtains and juggles values from the methods to later display them
        	System.out.printf("\nThe plaintext and enciphered text are as follows: \n%s\n%s%n%n", enText, plainText);
        }
        
        System.out.println("Thanks for using this program! Now quitting...");
        // Close the Scanner object
        input.close();
    }
    // end main
}
// end class