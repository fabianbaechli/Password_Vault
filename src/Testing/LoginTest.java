package Testing;

import DataManagement.Manager;
import Security.Hash;
import Security.Salt;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest {
    Manager manager = new Manager();
    Hash hash = new Hash();
    Salt salt = new Salt();

    @Test //User is already in JSON File
    public void TestLogin1() {
        System.out.println("Check if Method isnt null:");
        Assert.assertNotNull(manager.login("Fabrice", "TestPassword"));
    }

    @Test
    public void TestLogin2() {
        System.out.println("Check if old User example is still in JSON:");
        Assert.assertTrue(manager.login("Fabrice", "TestPasswort"));
    }

    @Test
    public void TestLogin3() {
        System.out.println("Check if given User is created and is in JSON:");
        String unique = salt.getUniqueString();
        manager.addUser("Bambuga", hash.getSha512("PasswortSafe", unique), unique);
        Assert.assertTrue(manager.login("Bambuga", "PasswortSafe"));
    }
}
 