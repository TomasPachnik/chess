package sk.tomas.chess.gui;

import sk.tomas.chess.base.Chess;
import sk.tomas.servant.annotation.Bean;
import sk.tomas.servant.annotation.Inject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by tomas on 5/12/17.
 * application main window
 */
@Bean("mainWindow")
public class MainWindow extends JFrame {

    @Inject
    private ImagePanel imagePanel;

    @Inject
    private Images images;

    @Inject
    private Chess chess;

    void init() {
        setTitle("Sach");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().add(imagePanel);
        createListeners();
        createMenuBar();
        //imagePanel.createLoading();
        setVisible(true);
    }

    private void createListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("Menu");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem newGame = new JMenuItem("New game");
        newGame.setMnemonic(KeyEvent.VK_E);
        newGame.setToolTipText("Start new game");
        newGame.addActionListener((ActionEvent event) -> chess.newGame());

        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setToolTipText("Exit application");
        exit.addActionListener((ActionEvent event) -> System.exit(0));

        file.add(newGame);
        file.add(exit);

        menubar.add(file);

        setJMenuBar(menubar);
    }

}
