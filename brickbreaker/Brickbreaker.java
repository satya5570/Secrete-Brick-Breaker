package brickbreaker;

import javax.swing.JFrame;

public class Brickbreaker {

    public static void main(String[] args) {

        JFrame f = new JFrame();
        f.setTitle("Brick breaker");
        f.setSize(700, 600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);

        // converting string to binary
        String s = "Hi";

        byte[] bytes = s.getBytes();

        StringBuilder binary = new StringBuilder();

        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            // binary.append(' ');

        }
        // System.out.println("binary of '"+s+"' is " + binary);

        String x = binary.toString();
        System.out.println(x.length());

        // converting binary string to int array
        int[] intArray = new int[x.length()]; // Initialize the integer array

        for (int i = 0; i < x.length(); i++) {
            char binaryChar = x.charAt(i);
            int intValue = Character.getNumericValue(binaryChar); // Convert char to int (0 or 1)
            intArray[i] = intValue;
        }

        System.out.println(intArray.length);

        // Print the integer array
        for (int val : intArray) {
            System.out.print(val + " ");
        }

        Gameplay gameplay = new Gameplay(intArray);
        f.add(gameplay);

    }

}
