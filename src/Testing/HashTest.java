package Testing;

import Security.Hash;
import org.junit.Assert;
import org.junit.Test;

public class HashTest {
    Hash hash = new Hash();

    @Test
    public void TestHash1(){
        System.out.println("Check if Hash is 128 Characters:");
        Assert.assertTrue(hash.getSha512("Test","12345678912345678912345678").length() == 128);
    }

    @Test
    public void TestHash2(){
        System.out.println("Check that Hash isnt null:");
        Assert.assertNotNull(hash.getSha512("Test","12345678912345678912345678"));
    }
}