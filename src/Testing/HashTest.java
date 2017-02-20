package Testing;

import Security.Hash;
import org.junit.Assert;
import org.junit.Test;

public class HashTest {
    private Hash hash = new Hash();

    @Test
    public void TestHash1(){
        System.out.println("Check if HashSha512 is 128 Characters:");
        Assert.assertTrue(hash.getSha512("Test","12345678912345678912345678").length() == 128);
    }

    @Test
    public void TestHash2(){
        System.out.println("Check that HashSha512 isnt null:");
        Assert.assertNotNull(hash.getSha512("Test","12345678912345678912345678"));
    }
}
