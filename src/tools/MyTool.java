package tools;

import java.util.Scanner;


public class MyTool {
    static Scanner sc = new Scanner(System.in);
    public static String _line;
    
    public static void header(String message) {
        Menu.clearScreen();
        System.out.println(message);
        System.out.println("=".repeat(message.length()) + "\n");
    }

    public static String epochDateToString(long epoch, String pattern) {
        return new java.text.SimpleDateFormat(pattern)
            .format(new java.util.Date(epoch));
    }

    public static String colsHeader(String[] headers, int[] widths) {
        _line = DataTableView.getHorizontalLine(widths);
        String s = _line + "\n";
        for (int i = 0; i < headers.length; i++) {
            s += "|" + Menu.align(headers[i], widths[i], Menu.CENTER);
        }
        return s + "|\n" + _line + "\n";
    }

    public static int[] initWiths(String[] headers) {
        int[] widths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            widths[i] = headers[i].length();
        }
        return widths;
    }

    public static int readId(String message) {
        header("Search the " + message);
        return Menu.readId("Type the " + message + " ID: ");
    }

    public static String readName(String message, String field) {
        header("Search the " + message);
        System.out.print("Type the " + field + ": ");
        return sc.nextLine();
    }

    public static String readString(String message, String field) {
        System.out.print("Type the " + field + ": ");
        return sc.nextLine();
    }

    public static String readName() {
        System.out.print("Type the Name: ");
        return sc.nextLine();
    }

    public static int readInt(String message, int lower) {
        System.out.print("Type the " + message + ": ");
        int number;
        try {
            number = Integer.parseInt(sc.nextLine());
            if (number > lower) return number;
        } catch (Exception e) {
            Menu.showError("Invalid " + message + "!");
        }
        return lower - 1;
    }
}
