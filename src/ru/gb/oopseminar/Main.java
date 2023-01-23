package ru.gb.oopseminar;


import java.lang.reflect.Field;
import java.security.AccessControlException;
import java.util.Scanner;


public class Main extends SecurityManager{
    public static void main(String[] args) throws Exception {
        Main m = new Main();
        Protecteddata data1 = m.getProtectedData();
        System.out.println(ANSIConstants.ANSI_GREEN + "ORIGINAL DATA: \n"
                + ANSIConstants.ANSI_RESET + data1);
        m.tryToCrackData(data1);
//        System.out.println(ANSIConstants.ANSI_GREEN + "OUTPUT DATA: \n"
//                + ANSIConstants.ANSI_RESET + data1);
    }
    public Protecteddata getProtectedData() {
        Protecteddata d = new Protecteddata(new String[]{"Ivan Ivanov"}, 123, 12345);
        return d;
    }

    public Protecteddata tryToCrackData(Protecteddata d) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("\n" + "=".repeat(37)+ "\n"
                + ANSIConstants.ANSI_WHITE_BACKGROUND +
                ANSIConstants.ANSI_BLACK + "DATA CRACK MODE"
                + ANSIConstants.ANSI_RESET);
        System.out.println("=".repeat(37)
                + "\nChoose your tool to break-in: " + "\n" + "-".repeat(37));
        System.out.println("1.Pincode replacement through SetMethods ");
        System.out.println("2.Password replacement through SetMethods ");
        System.out.println("3.Data replacement through Reflection features ");
        System.out.println("4.Exit");
        System.out.println("-".repeat(37));
        System.out.print("Put your choice here: ");
        int choice = in.nextInt();
        in.nextLine();
        System.out.println("-".repeat(37));
        switch (choice) {
            case 1:
                System.out.print("Put your pin here: ");
                Integer choice1 = in.nextInt();
                testForAccess(d);
                d.setPincode(choice1);
                break;
            case 2:
                System.out.print("Put your password here: ");
                Integer choice2 = in.nextInt();
                testForAccess(d);
                d.setPassword(choice2);
                break;
            case 3:
                System.out.print("Set your pin here: ");
                Integer pin = in.nextInt();
                testForAccess(d);
                breakWithReflection(d, pin);
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println(ANSIConstants.ANSI_RED
                        + "Wrong menu point choice! Please try again!"
                        + ANSIConstants.ANSI_RESET);
        }
        return d;
    }
    public Protecteddata breakWithReflection(Protecteddata data, Integer bugPin)
            throws Exception {
        Field f = Integer.class.getDeclaredField("value");
        disableWarning();
        ((Field) f).setAccessible(true);
        f.set(data.getPincode(), bugPin);
        return data;
    }
    public void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }
    public void testForAccess(Protecteddata d) throws AccessControlException {
        try {
            System.setSecurityManager(new SecurityManager());
        } catch (AccessControlException e) {
            System.out.println("-".repeat(71) + ANSIConstants.ANSI_RED +
                    "\nWarning! Unauthorized data change method using has just been prevented!\n"
                    + ANSIConstants.ANSI_RESET + "-".repeat(71));
        }finally {
            System.out.println(ANSIConstants.ANSI_GREEN + "OUTPUT DATA: \n"
                    + ANSIConstants.ANSI_RESET + d);
            System.exit(0);
        }
    }
}