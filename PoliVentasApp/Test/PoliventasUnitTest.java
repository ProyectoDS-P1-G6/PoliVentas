/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static junit.framework.TestCase.assertNotNull;
import org.junit.Before;
import services.DBConnection;

/**
 *
 * @author carlasanchez
 */
public class PoliventasUnitTest {

    public PoliventasUnitTest() {
    }

    public void testConnectionDB() {
        DBConnection.getInstance();
        assertNotNull(DBConnection.getInstance());

    }
}
