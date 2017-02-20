package Testing;


import Security.Salt;
import org.junit.Assert;
import org.junit.Test;

public class SaltTest {
    private Salt salt = new Salt();

    @Test
    public void TestSalt1() {
        System.out.println("Check if Salt is 26 Characters long:");
        Assert.assertTrue(salt.getUniqueString().length() == 26);
    }

    @Test
    public void TestSalt2() {
        System.out.println("Check that salt isn't null:");
        Assert.assertNotNull(salt.getUniqueString());
    }
}
