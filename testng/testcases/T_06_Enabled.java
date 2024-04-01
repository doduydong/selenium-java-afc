package testcases;

import org.testng.annotations.Test;

public class T_06_Enabled {
	@Test()
	public void Create() {
		System.out.println("Create");
	}

	@Test
	public void Read() {
		System.out.println("Read");
	}

	@Test
	public void Update() {
		System.out.println("Update");
	}

	@Test(enabled = false)
	public void Delete() {
		System.out.println("Delete");
	}

}
