package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import static sun.security.krb5.Confounder.bytes;
import java.util.Arrays;
import java.util.Comparator;

public class Mapgenerator {

    public int map[][];
    public int mapoval[][];
    public int brickwidth;
    public int brickheight;
    public int xpovalwidth = 15;
    public int xpovalheight = 15;

    public int xpovalydir = 2;
    public int xpovalposy;
    public int unsortedarr[] = new int[200];
    public int unsortedindex[] = new int[200];
    public int sortedarr[] = new int[200];
    public int rowindexnumber[] = new int[200];

    public String strval = "";
    public String hiddenstr = "";
    public int k = 0;
    public int v;

    public Mapgenerator(int row, int col, int[] x) {

        System.out.println();

        // for printing array x bit by bit
        // for (int i = 0; i < x.length; i++) {
        // System.out.println(x[i]);
        // }
        map = new int[row][col];
        v = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                map[i][j] = x[v];

                if (map[i][j] == 0) {
                    map[i][j] = x[v] + 2;
                } else {
                    map[i][j] = x[v];
                }
                System.out.print("[" + i + "," + j + "]");
                System.out.print(map[i][j] + " ");
                v++;
            }

            System.out.println();

        }

        // string value of brick
        //
        mapoval = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mapoval[i][j] = 0;

            }
        }

        brickwidth = 600 / col;
        brickheight = 300 / row;

    }

    public void setBrick(int value, int r, int c, int totalbrick) {

        if (totalbrick != 0) {
            if (map[r][c] == 1) {
                unsortedarr[k] = map[r][c];
                unsortedindex[k] = c;

                rowindexnumber[k] = r;
                k++;
                map[r][c] = value;
            }
            if (map[r][c] == 2) {
                unsortedarr[k] = map[r][c];
                unsortedindex[k] = c;

                rowindexnumber[k] = r;
                k++;
                map[r][c] = value;
            }

        }
        // for(int i=0; i<16;i++){
        // System.out.print(unsortedarr[i]);
        // }
    }

    public void setoval(int value, int r, int c) {
        mapoval[r][c] = value;

    }

    public void draw(Graphics2D g) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(Color.pink);
                    g.fillRect(j * brickwidth + 50, i * brickheight + 50, brickwidth, brickheight);
                    g.setColor(Color.blue);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j * brickwidth + 50, i * brickheight + 50, brickwidth, brickheight);
                    g.drawString(i + " " + j, j * brickwidth + 70, i * brickheight + 70);

                    g.drawString(map[i][j] + " ", j * brickwidth + 60, i * brickheight + 80);

                    // System.out.println(i + " " + j);
                }

            }
        }

        /////////////////////////
        // for xp oval
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {

                if (mapoval[i][j] > 0) {
                    g.setColor(Color.orange);
                    g.fillOval(j * brickwidth + 70, i * brickheight + 60, xpovalwidth, xpovalheight);

                    xpovalposy += xpovalydir;

                }

            }
        }

    }

}
