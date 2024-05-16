
package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.lang.Math.*;
import static java.lang.System.exit;
import java.util.*;

/**
 *
 * @author satya
 */
public class Gameplay extends JPanel implements ActionListener, KeyListener {

    private boolean play;
    private int totalbrick;
    private int strlenn;
    private Timer timer;
    private int delay;
    private int ballposx;
    private int ballposy;
    private int ballxdir;
    private int ballydir;
    private int playerx;
    public int k = 0;
    private int score = 0;

    private Mapgenerator map;

    int xpovalposx;
    int xpovalposy;
    int xpovalydir = -1;
    public int x[];

    public Gameplay(int[] x) {

        this.ballposy = 350;
        this.ballposx = 200;
        this.ballxdir = -1;
        this.ballydir = -2;
        this.playerx = 350;
        this.totalbrick = x.length;
        this.strlenn = x.length;
        this.delay = 8;
        this.play = false;

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer = new Timer(delay, this);
        timer.start();

        map = new Mapgenerator(2, 8, x);
        // System.out.println(x);

    }

    public void paint(Graphics g) {
        // black canvas
        g.setColor(Color.blue);
        g.fillRect(1, 1, 692, 592);

        // border
        g.setColor(Color.red);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(0, 3, 3, 592);
        g.fillRect(691, 3, 3, 592);

        // paddle
        g.setColor(Color.green);
        g.fillRect(playerx, 550, 100, 8);

        // bricks
        map.draw((Graphics2D) g);

        // ball
        g.setColor(Color.yellow);
        g.fillOval(ballposx, ballposy, 20, 20);

        // xprect
        // score
        g.setColor(Color.GREEN);
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("score: " + score, 550, 30);

        // gameover
        if (ballposy >= 570) {
            play = false;
            ballxdir = 0;
            ballydir = 0;

            g.setColor(Color.green);
            g.setFont(new Font("arial", Font.BOLD, 30));
            g.drawString("game over ! score: " + score, 200, 300);

            g.setFont(new Font("arial", Font.BOLD, 25));
            g.drawString("press enter to restart", 230, 350);

        }

        // game win
        if (totalbrick == 0) {
            play = false;
            ballxdir = 0;
            ballydir = 0;

            for (int i = 0; i < strlenn - 1; i++) {
                for (int j = 0; j < (strlenn - 1 - i); j++) {

                    if (map.rowindexnumber[j] > map.rowindexnumber[j + 1]) {
                        // swap elements

                        int temp = map.rowindexnumber[j];
                        map.rowindexnumber[j] = map.rowindexnumber[j + 1];
                        map.rowindexnumber[j + 1] = temp;

                        temp = map.unsortedarr[j];
                        map.unsortedarr[j] = map.unsortedarr[j + 1];
                        map.unsortedarr[j + 1] = temp;

                        temp = map.unsortedindex[j];
                        map.unsortedindex[j] = map.unsortedindex[j + 1];
                        map.unsortedindex[j + 1] = temp;
                    }

                }

            }

            for (int i = 0; i < 8 - 1; i++) {
                for (int j = 0; j < (8 - 1 - i); j++) {
                    if (map.unsortedindex[j] > map.unsortedindex[j + 1]) {

                        // swap elements
                        int temp = map.unsortedarr[j];
                        map.unsortedarr[j] = map.unsortedarr[j + 1];
                        map.unsortedarr[j + 1] = temp;

                        temp = map.unsortedindex[j];
                        map.unsortedindex[j] = map.unsortedindex[j + 1];
                        map.unsortedindex[j + 1] = temp;

                    }

                }
            }

            for (int i = 0; i < strlenn - 1; i++) {
                for (int j = 8; j < (strlenn - 1 - i); j++) {
                    if (map.unsortedindex[j] > map.unsortedindex[j + 1]) {

                        // swap elements
                        int temp = map.unsortedarr[j];
                        map.unsortedarr[j] = map.unsortedarr[j + 1];
                        map.unsortedarr[j + 1] = temp;

                        temp = map.unsortedindex[j];
                        map.unsortedindex[j] = map.unsortedindex[j + 1];
                        map.unsortedindex[j + 1] = temp;

                    }

                }
            }

            for (int j = 0; j < strlenn; j++) {
                System.out.print(map.unsortedarr[j] + "  " + map.unsortedindex[j] + "  " + map.rowindexnumber[j]);
                System.out.println();
                map.sortedarr[j] = map.unsortedarr[j];
            }
            System.out.println();
            for (int j = 0; j < strlenn; j++) {

                System.out.print(map.sortedarr[j] + "  " + map.unsortedindex[j] + "  " + map.rowindexnumber[j]);
                System.out.println();

            }

            for (int j = 0; j < strlenn; j++) {
                if (map.sortedarr[j] == 2) {
                    map.sortedarr[j] = 0;
                }
            }

            for (int j = 0; j < strlenn; j++) {
                map.strval = map.strval + map.sortedarr[j];
                // System.out.print(map.sortedarr[j]);
            }

            System.out.print(map.strval);
            System.out.println();

            // extract string
            String input = map.strval;
            StringBuilder sb = new StringBuilder();

            Arrays.stream(
                    input.split("(?<=\\G.{8})")).forEach(s -> sb.append((char) Integer.parseInt(s, 2)));

            String output = sb.toString();

            // System.out.println("here is hidden string : "+output);
            map.hiddenstr = output;

            System.out.println("here is hidden string  : " + map.hiddenstr);

            g.setColor(Color.green);
            g.setFont(new Font("arial", Font.BOLD, 30));
            g.drawString("you won ! score: " + score, 200, 300);
            g.setColor(Color.yellow);
            g.drawString("your hidden message is :  " + map.hiddenstr, 150, 400);
            g.setColor(Color.green);
            g.setFont(new Font("arial", Font.BOLD, 25));
            // g.drawString("press enter to restart", 230, 350);
            System.exit(0);

        }

    }

    private void moveleft() {
        play = true;
        playerx -= 20;
    }

    private void moveright() {
        play = true;
        playerx += 20;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change
        // body of generated methods, choose Tools | Templates.

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerx <= 0) {
                playerx = 0;
            } else {
                moveleft();
            }

        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerx >= 600) {
                playerx = 600;
            } else {
                moveright();
            }

        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                score = 0;
                totalbrick = x.length;
                ballposx = 120;
                ballposy = 350;
                ballxdir = -1;
                ballydir = -2;
                playerx = 320;

                // map = new Mapgenerator(5, 8);
            }

        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerx <= 0) {
                playerx = 0;
            } else {
                moveleft();
            }

        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerx >= 600) {
                playerx = 600;
            } else {
                moveright();
            }

        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                score = 0;
                totalbrick = x.length;
                ballposx = 200;
                ballposy = 350;
                ballxdir = -1;
                ballydir = -2;
                playerx = 320;

                // map = new Mapgenerator(5, 8);
            }

        }

        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (play) {
            if (ballposx <= 0) {
                ballxdir = -ballxdir;
            }
            if (ballposx >= 670) {
                ballxdir = -ballxdir;
            }
            if (ballposy <= 0) {
                ballydir = -ballydir;
            }

            Rectangle ballRect = new Rectangle(ballposx, ballposy, 20, 20);
            Rectangle paddleRect = new Rectangle(playerx, 550, 100, 8);

            if (ballRect.intersects(paddleRect)) {
                ballydir = -ballydir;
            }

            A: for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {

                    if (map.map[i][j] > 0 && totalbrick > 0) {

                        int width = map.brickwidth;
                        int height = map.brickheight;
                        int brickxpos = 50 + j * width;
                        int brickypos = 50 + i * height;

                        Rectangle brickRect = new Rectangle(brickxpos, brickypos, width, height);

                        if (ballRect.intersects(brickRect)) {
                            System.out.println(map.map.length);
                            System.out.println(map.map[0].length);
                            System.out.println("[" + i + "][" + j + "]" + map.map[i][j]);

                            //
                            // other outside this
                            map.setBrick(0, i, j, totalbrick); // invisibling brick

                            // int xp = (int) Math.round(Math.random() * 10);
                            // if (xp >= 5) {
                            // map.setoval(1, i, j);
                            // System.out.println(xp + " [" + i + "][" + j + "]");
                            // score += xp;
                            //
                            // }
                            totalbrick--;
                            System.out.println("totalbrick:  " + totalbrick);
                            score += 10;
                            xpovalposy += xpovalydir;

                            if (ballposx + 19 <= brickxpos || ballposx + 1 >= brickxpos + width) {
                                ballxdir = -ballxdir;
                            } else {
                                ballydir = -ballydir;
                            }

                            //
                            break A;

                        } // end of iff intersect

                    } // end of if
                    if (totalbrick == 0) {
                        // play = false;
                        break A;

                    }

                } // end of for j
                //
            } // end of for i

            ballposx += ballxdir;
            ballposy += ballydir;
        } // end of if play

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change
        // body of generated methods, choose Tools | Templates.
    }

}
