package sk.tomas.chess;

import sk.tomas.chess.gui.Gui;
import sk.tomas.servant.annotation.Config;
import sk.tomas.servant.annotation.PackageScan;
import sk.tomas.servant.core.Servant;
import sk.tomas.servant.exception.ServantException;

/**
 * Created by Tomas Pachnik on 12-May-17.
 */

@Config
@PackageScan("sk.tomas.chess")
public class Main {

    public static void main(String[] args) throws ServantException {
        Servant.addConfiguration(Main.class);
        ((Gui) Servant.getByName("gui")).init();
    }

}
