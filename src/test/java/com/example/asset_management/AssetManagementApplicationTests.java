package com.example.asset_management;


import com.example.asset_management.controllerTest.AssetControllerTest;
import com.example.asset_management.controllerTest.CategoryControllerTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssetManagementApplicationTests {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(
				CategoryControllerTest.class,
				AssetControllerTest.class
		);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		if (result.wasSuccessful()) {
			System.out.println("All tests passed successfully ...");
		} else {
			System.out.println("Some tests failed. Check the output for details. ...");
		}
	}
}


