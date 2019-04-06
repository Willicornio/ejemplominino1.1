
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import static org.junit.Assert.*;

public class MyBikeImplTest {
    final static Logger log = Logger.getLogger(MyBikeImplTest.class.getName());


    MyBike mb;
    @Before
    public void setUp() throws Exception{
        mb= MyBikeImpl.getInstance();
        this.mb = new MyBikeImpl();

        this.mb.addUser("1", "Willi", "Mikoto");
        this.mb.addStation("Station1", "nada", 10, 3, 3);
        this.mb.addStation("Station2", "nada", 10, 3, 3);




    }
    @After

    public void tearDown(){

        this.mb.clear();

    }



    @Test

    public void testAddUser() {





    }
}
