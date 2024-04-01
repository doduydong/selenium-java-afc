package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class T_02_Assert {
	boolean verifyTrue = true;
	boolean verifyFalse = false;
	String framework = "TestNG";

	@Test()
	public void TC_01_Assert_True() {
		Assert.assertTrue(verifyTrue);
	}

	@Test
	public void TC_02_Assert_False() {
		Assert.assertFalse(verifyFalse);
	}

	@Test
	public void TC_03_Assert_Equal() {
		Assert.assertEquals(framework, "TestNG");
	}

}
