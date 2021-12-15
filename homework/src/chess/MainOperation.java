package chess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class MainOperation extends JFrame implements MouseMotionListener, MouseListener {
    JPanel contentPane = new JPanel();
    JButton beginGame, endGame, stopGame, reStart;
    int valueX = 0, valueY = 0;
    int chessX = 0, chessY = 0;
    int state = 1;//表示该是黑子落子还是白子落子---1代表黑子---2代表白子
    int gameState = 0;//1表示游戏开始,2表示游戏暂停,3表示游戏结束,4表示游戏重新开始
    boolean whiteWin = false;
    boolean blackWin = false;
    boolean Pressed = false;
    boolean showChessBoard = false;
    boolean[][] blackChesses = new boolean[15][15];
    boolean[][] whiteChesses = new boolean[15][15];
    boolean[][] locatedOrNot = new boolean[15][15];

    MainOperation() {
        super("五子棋");
        setVisible(true);
        setSize(1000, 800);
        addMouseMotionListener(this);
        addMouseListener(this);
        this.setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setSize(1000, 200);
        contentPane.setVisible(true);
        contentPane.setOpaque(true);
        initialize();
        beginGame = new JButton(new ImageIcon("./resources/begin.png"));
        endGame = new JButton(new ImageIcon("./resources/end.png"));
        stopGame = new JButton(new ImageIcon("./resources/stop.png"));
        reStart = new JButton(new ImageIcon("./resources/reStart.png"));
        beginGame.setBounds(60, 615, 240, 60);
        endGame.setBounds(350, 615, 240, 60);
        stopGame.setBounds(60, 690, 240, 60);
        reStart.setBounds(350, 690, 240, 60);
        contentPane.add(beginGame);
        contentPane.add(endGame);
        contentPane.add(reStart);
        contentPane.add(stopGame);
        ActionListener listenerBeginGame = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameState = 1;
                showChessBoard = true;
            }
        };
        ActionListener listenerStopGame = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameState = 2;
            }
        };
        ActionListener listenerEndGame = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameState = 3;
            }
        };
        ActionListener listenerRestartGame = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameState = 4;
                initialize();
            }
        };
        beginGame.addActionListener(listenerBeginGame);
        endGame.addActionListener(listenerEndGame);
        reStart.addActionListener(listenerRestartGame);
        stopGame.addActionListener(listenerStopGame);
    }


    public void paint(Graphics g) {
        super.paint(g);
        judge();
        if (whiteWin) {

            return;
        }
        if (blackWin) {

            return;
        }
        if (showChessBoard == true) {
            try {
                Image chessBoard = ImageIO.read(new File("./resources/1.png"));
                g.drawImage(chessBoard, 60, 60, chessBoard.getWidth(null), chessBoard.getHeight(null), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Image white = ImageIO.read(new File("./resources/whitechess(1).png"));
            Image black = ImageIO.read(new File("./resources/blackchess(1).png"));
            if (Pressed) {
                for (int i = 0; i < 15; i++)
                    for (int j = 0; j < 15; j++) {
                        if (blackChesses[i][j]) {
//                            g.setColor(Color.BLACK);
//                            g.fillOval(i * 40 + 40, j * 40 + 40, 40, 40);
                            g.drawImage(black, i * 40 + 40, j * 40 + 40, 38, 38, null);
                        }
                        if (whiteChesses[i][j]) {
//                            g.setColor(Color.WHITE);
//                            g.fillOval(i * 40 + 40, j * 40 + 40, 40, 40);
//                            g.setColor(Color.BLACK);
//                            g.drawOval(i * 40 + 40, j * 40 + 40, 40, 40);
                            g.drawImage(white, i * 40 + 40, j * 40 + 40, 38, 38, null);
                        }
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getX() > 60 && e.getX() < 620 && e.getY() > 60 && e.getY() < 620 && gameState == 1) {
            valueX = e.getX() - 60;
            valueY = e.getY() - 60;
            int nowX = valueX / 40;
            int nowY = valueY / 40;
            int restX = valueX % 40;
            int restY = valueY % 40;
            if (restX < 20) {
                chessX = nowX;
                valueX = chessX * 40 + 60;
            } else if (restX >= 20) {
                chessX = nowX + 1;
                valueX = chessX * 40 + 60;
            }
            if (restY < 20) {
                chessY = nowY;
                valueY = chessY * 40 + 60;
            } else if (restY >= 20) {
                chessY = nowY + 1;
                valueY = chessY * 40 + 60;
            }
            if (state == 1 && locatedOrNot[chessX][chessY] == false) {
                blackChesses[chessX][chessY] = true;
                locatedOrNot[chessX][chessY] = true;
                state = 0;
            } else if (state == 0 && locatedOrNot[chessX][chessY] == false) {
                whiteChesses[chessX][chessY] = true;
                locatedOrNot[chessX][chessY] = true;
                state = 1;
            }
            Pressed = true;
            repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {
        Pressed = false;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void judge() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 15; j++)
                if (whiteChesses[i][j] && whiteChesses[i + 1][j] && whiteChesses[i + 2][j] && whiteChesses[i + 3][j] && whiteChesses[i + 4][j]) {
                    whiteWin = true;
                    return;
                }
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 10; j++)
                if (whiteChesses[i][j] && whiteChesses[i][j + 1] && whiteChesses[i][j + 2] && whiteChesses[i][j + 3] && whiteChesses[i][j + 4]) {
                    whiteWin = true;
                    return;
                }
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (whiteChesses[i][j] && whiteChesses[i + 1][j + 1] && whiteChesses[i + 2][j + 2] && whiteChesses[i + 3][j + 3] && whiteChesses[i + 4][j + 4]) {
                    whiteWin = true;
                    return;
                }
        for (int i = 0; i < 10; i++)
            for (int j = 4; j < 15; j++)
                if (whiteChesses[i][j] && whiteChesses[i + 1][j - 1] && whiteChesses[i + 2][j - 2] && whiteChesses[i + 3][j - 3] && whiteChesses[i + 4][j - 4]) {
                    whiteWin = true;
                    return;
                }
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 15; j++)
                if (blackChesses[i][j] && blackChesses[i + 1][j] && blackChesses[i + 2][j] && blackChesses[i + 3][j] && blackChesses[i + 4][j]) {
                    blackWin = true;
                    return;
                }
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 10; j++)
                if (blackChesses[i][j] && blackChesses[i][j + 1] && blackChesses[i][j + 2] && blackChesses[i][j + 3] && blackChesses[i][j + 4]) {
                    blackWin = true;
                    return;
                }
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (blackChesses[i][j] && blackChesses[i + 1][j + 1] && blackChesses[i + 2][j + 2] && blackChesses[i + 3][j + 3] && blackChesses[i + 4][j + 4]) {
                    blackWin = true;
                    return;
                }
        for (int i = 0; i < 10; i++)
            for (int j = 4; j < 15; j++)
                if (blackChesses[i][j] && blackChesses[i + 1][j - 1] && blackChesses[i + 2][j - 2] && blackChesses[i + 3][j - 3] && blackChesses[i + 4][j - 4]) {
                    blackWin = true;
                    return;
                }
    }

    public void initialize() {
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++) {
                blackChesses[i][j] = false;
                whiteChesses[i][j] = false;
                locatedOrNot[i][j] = false;
            }
        whiteWin = false;
        blackWin = false;
        gameState = 1;
    }


}


