package sk.tomas.chess.gui;

import sk.tomas.chess.base.Chess;
import sk.tomas.servant.annotation.Inject;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by tomas on 5/12/17.
 * application main window
 */
public class MainWindow extends JFrame {

    @Inject
    private ImagePanel imagePanel;

    void init() {
        setTitle("Sach");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().add(imagePanel);
        listeners();
        setVisible(true);
    }

    private void listeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
