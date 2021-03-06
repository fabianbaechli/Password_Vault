package Testing;

import DataManagement.Manager;
import Security.Hash;
import Security.Salt;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest {
    private Manager manager = new Manager();
    private Hash hash = new Hash();
    private Salt salt = new Salt();

    @Test
    public void TestLogin1() {
        System.out.println("Check if Method isnt null:");
        Assert.assertNotNull(manager.login("", ""));
    }

    @Test //User is already in JSON File
    public void TestLogin2() {
        System.out.println("Check if old User example is still in JSON:");
        Assert.assertTrue(manager.login("", ""));
    }

    @Test
    public void TestLogin3() {
        System.out.println("Check if given User is created and is in JSON:");
        String unique = salt.getUniqueString();
        manager.addUser("Bambuga", hash.getSha512("PasswortSafe", unique), unique, "PasswortSafe");
        Assert.assertTrue(manager.login("Bambuga", "PasswortSafe"));
    }
}
 