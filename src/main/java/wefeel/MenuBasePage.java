package wefeel;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class MenuBasePage implements BasePage{
    //menu pages must implement showPage on their own
    public abstract void showPage(Scanner sc);

    //allows us to request ints on any menu page.
    int getIntValue(Scanner sc, int bottomLimit, int topLimit) {
        int returnValue = 0;
        //force them to eventually enter a valid int
        while (true)
            try {
                returnValue = sc.nextInt();
                if(returnValue >= bottomLimit && returnValue <= topLimit) {
                    sc.nextLine();
                    return returnValue;
                }
                else{
                    sc.nextLine();
                    System.out.println("The value needs to be between " + bottomLimit + "-" + topLimit + ", please try again:");
                }

            }
            catch (InputMismatchException ex) {
                System.out.println("Please enter an integer " + bottomLimit + "-" + topLimit + ", please try again:");
                sc.nextLine();
            }
    }

    /**
     * Get's a Yes/No response and converts to a boolean
     * @param sc Scanner to pass around
     * @return boolean from Yes or No (Y/N)
     */
    boolean requestYesNo(Scanner sc) {
        while (true) {
            String yesNo = sc.nextLine().toLowerCase();
            if (yesNo.startsWith("y") || yesNo.startsWith("n"))
                return yesNo.startsWith("y");
            else
                System.out.println("Please enter either 'yes' or 'no'");

        }

    }

}
