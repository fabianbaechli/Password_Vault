package Testing;

import Security.Hash;
import org.junit.Assert;
import org.junit.Test;

public class HashTest {
    Hash hash = new Hash();

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
    @Test
    public void TestHash3(){
        System.out.println("Check if HashSha256 is 64 Characters:");
        Assert.assertTrue(hash.getSha256("hallo").length() == 64);
    }

    @Test
    public void TestHash4(){
        System.out.println("Check that HashSha256 isnt null:");
        Assert.assertNotNull(hash.getSha256("hello"));
    }

    @Test
    public void TestHash5(){
        System.out.println("Check that HashSha256 with input \"hello\" is expected value:");
        Assert.assertTrue(hash.getSha256("hello").equals("2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824"));
    }
}
