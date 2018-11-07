package accounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class AccountTest {

    private Account testAccount;

    @Before
    public void setUp() {
        testAccount = new Account("John Doe", LocalDate.of(1975,5,20));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testToString() {
        String expectedResult = "GivenName= " + testAccount.getGivenName() +"\n" +
                "Birthday= " + testAccount.getBirthDate().toString() +"\n" +
                "Account ID= " + testAccount.getAccountID() +"\n" +"ADHD= false\n" +
                "Depression= false\n" +
                "Anxiety= false\n" +
                "Mood= false";

        String result = testAccount.toString();
        System.out.println(result);

        Assert.assertEquals(expectedResult,result);

    }
}