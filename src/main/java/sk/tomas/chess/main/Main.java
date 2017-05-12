package sk.tomas.chess.main;

import sk.tomas.chess.configuration.Configuration;
import sk.tomas.servant.core.Core;
import sk.tomas.servant.core.impl.CoreImpl;
import sk.tomas.servant.exception.ServantException;

/**
 * Created by Tomas Pachnik on 12-May-17.
 */
public class Main {

    public static void main(String[] args) throws ServantException {
        Core core = new CoreImpl(Configuration.class);
    }

}
