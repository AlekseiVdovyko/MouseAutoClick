package gmail.avdovyko.MyFrame;
import gmail.avdovyko.MousePosition.MousePosition;
import gmail.avdovyko.MyRunner.MyRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MyFrame extends JFrame {

    private JButton startButton = new JButton("СТАРТ");
    private JButton stopButton = new JButton("СТОП");
    private JButton getFirstPosition = new JButton("Задать координаты");
    private JButton getSecondPosition = new JButton("Задать координаты");

    private JLabel firstLabelDelay = new JLabel("Первая задержка нажатия (мс)");
    private JLabel secondLabelDelay = new JLabel("Вторая задержка нажатия (мс)");
    private JLabel firstLabelPositionX = new JLabel("X");
    private JLabel firstLabelPositionY = new JLabel("Y");
    private JLabel secondLabelPositionX = new JLabel("X");
    private JLabel secondLabelPositionY = new JLabel("Y");


    private JTextField firstFieldDelay = new JTextField();
    private JTextField secondFieldDelay = new JTextField();
    private JTextField firstFieldPositionX = new JTextField();
    private JTextField firstFieldPositionY = new JTextField();
    private JTextField secondFieldPositionX = new JTextField();
    private JTextField secondFieldPositionY = new JTextField();

    private long firstDelay = 0L;
    private long secondDelay = 0L;
    private int firstPositionX = 0;
    private int firstPositionY = 0;
    private int secondPositionX = 0;
    private int secondPositionY = 0;

    MousePosition mousePosition = new MousePosition();
    Thread thread;

    public MyFrame(String name) {

        JFrame frame = new JFrame();

        frame.setTitle(name);
        frame.setBounds(1000, 60, 580,200);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        startButton.setBounds(485, 37, 75, 35);
        stopButton.setBounds(485, 110, 75, 35);

        //кнопки позиции
        getFirstPosition.setBounds(315, 37, 160, 35);
        getFirstPosition.setFont(new Font("Arial", Font.BOLD, 11));
        getSecondPosition.setBounds(315, 110, 160, 35);
        getSecondPosition.setFont(new Font("Arial", Font.BOLD, 11));

        firstLabelDelay.setBounds(10, 5, 200, 50);
        firstFieldDelay.setBounds(10, 40, 200, 30 );
        firstFieldDelay.setText(String.valueOf(3000));

        secondLabelDelay.setBounds(10, 80, 200, 50);
        secondFieldDelay.setBounds(10, 115, 200, 30 );
        secondFieldDelay.setText(String.valueOf(172500000));

        firstLabelPositionX.setBounds(235, 5, 200, 50);
        firstLabelPositionY.setBounds(285, 5, 200, 50);
        firstFieldPositionX.setBounds(220, 40, 40, 30);
        firstFieldPositionX.setText(String.valueOf(firstPositionX));
        firstFieldPositionY.setBounds(270, 40, 40, 30);
        firstFieldPositionY.setText(String.valueOf(firstPositionY));

        secondLabelPositionX.setBounds(235, 80, 200, 50);
        secondLabelPositionY.setBounds(285, 80, 200, 50);
        secondFieldPositionX.setBounds(220, 115, 40, 30);
        secondFieldPositionX.setText(String.valueOf(secondPositionX));
        secondFieldPositionY.setBounds(270, 115, 40, 30);
        secondFieldPositionY.setText(String.valueOf(secondPositionY));


        getFirstPosition.addActionListener(e -> {
            getFirstPosition.setFont(new Font("Arial", Font.BOLD, 9));
            getFirstPosition.setText("Выбрать место и нажать 1");
        });

        getSecondPosition.addActionListener(e -> {
            getSecondPosition.setFont(new Font("Arial", Font.BOLD, 9));
            getSecondPosition.setText("Выбрать место и нажать 2");
        });

        //обработчик событий нажатия кнопки "старта"
        startButton.addActionListener(e -> {
            stopButton.setEnabled(true);
            startButton.setEnabled(false);

            firstDelay = Long.parseLong(firstFieldDelay.getText());
            secondDelay = Long.parseLong(secondFieldDelay.getText());

            thread = new Thread(new MyRunner(firstDelay, secondDelay, firstPositionX, firstPositionY,
                    secondPositionX, secondPositionY));
            thread.start();
        });

        //обработка событий нажатия кнопки "стопа"
        stopButton.setEnabled(false);
        stopButton.addActionListener(e -> {
            System.out.println("Нажат стоп");
            stopButton.setEnabled(false);
            startButton.setEnabled(true);
            thread.interrupt();
            System.out.println("Поток остановлен");


        });

        frame.add(firstFieldDelay);
        frame.add(secondFieldDelay);
        frame.add(firstLabelPositionX);
        frame.add(firstLabelPositionY);
        frame.add(secondLabelPositionX);
        frame.add(secondLabelPositionY);
        frame.add(firstFieldPositionX);
        frame.add(firstFieldPositionY);
        frame.add(secondFieldPositionX);
        frame.add(secondFieldPositionY);
        frame.add(firstLabelDelay);
        frame.add(secondLabelDelay);
        frame.add(startButton);
        frame.add(stopButton);
        frame.add(getFirstPosition);
        frame.add(getSecondPosition);
        frame.setVisible(true);
        startButton.requestFocus();

        //при нажатии конки и затем клавиши 1 запоминает координаты курсора
        Action action1 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstPositionX = mousePosition.positionX();
                firstFieldPositionX.setText(String.valueOf(firstPositionX));
                firstPositionY = mousePosition.positionY();
                firstFieldPositionY.setText(String.valueOf(firstPositionY));
            }
        };
        getFirstPosition.getInputMap().put(KeyStroke.getKeyStroke("1"), "doNothing");
        getFirstPosition.getActionMap().put("doNothing", action1);

        //при нажатии конки и затем клавиши 2 запоминает координаты курсора
        Action action2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            secondPositionX = mousePosition.positionX();
            secondFieldPositionX.setText(String.valueOf(secondPositionX));
            secondPositionY = mousePosition.positionY();
            secondFieldPositionY.setText(String.valueOf(secondPositionY));
            }
        };
        getSecondPosition.getInputMap().put(KeyStroke.getKeyStroke("2"), "doNothing");
        getSecondPosition.getActionMap().put("doNothing", action2);

    }
}
