package testcases;

import org.testng.annotations.Test;

public class T_05_DependsOnMethods {
	@Test
	public void Create() {
		System.out.println("Create");
	}

	@Test(dependsOnMethods = "D_01_Create")
	public void Read() {
		System.out.println("Read");
	}

	@Test(dependsOnMethods = "D_02_Read")
	public void Update() {
		System.out.println("Update");
	}

	@Test(dependsOnMethods = "D_03_Update")
	public void Delete() {
		System.out.println("Delete");
	}

}
