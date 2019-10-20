package Spring;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CustomerAccountTestCase {


	CustomerAccount objToTest;
	
	@BeforeEach
	void setUp() throws Exception {
		objToTest = new CustomerAccount();
	}


	@Test
	void test() throws SQLException, NoAccountCreatedException {
		
		CustomerAccountDAO mockCad = Mockito.mock(CustomerAccountDAO.class);
		
		
		Mockito.doThrow(new SQLException()).when(mockCad).newAcctNumber("Me", "652");
		
		objToTest.setCad(mockCad);
		
		
		assertThrows(NoAccountCreatedException.class, () -> {
			objToTest.createNewAccount("Me", "652");
	    });
	}
 
	
	@Test
	void test2() throws SQLException, NoAccountCreatedException {
		
		CustomerAccountDAO mockCad = Mockito.mock(CustomerAccountDAO.class);
		
		Mockito.doReturn("987654321").when(mockCad).newAcctNumber("Me", "652");

		Mockito.doThrow(new SQLException()).when(mockCad).saveAccount(objToTest);
		
		objToTest.setCad(mockCad);
		
		assertThrows(NoAccountCreatedException.class, () -> {
			objToTest.createNewAccount("Me", "652");
	    });
		
		
	}
	
	@Test
	void test3() throws SQLException, NoAccountCreatedException, NoSuchCustomerAccountException {
		
		CustomerAccountDAO mockCad = Mockito.mock(CustomerAccountDAO.class);
		
		Mockito.doReturn("987654321").when(mockCad).newAcctNumber("Me", "652");
		Mockito.doThrow(new SQLException()).when(mockCad).updateAccount(objToTest);

		
		
		objToTest.setCad(mockCad);
		
		assertThrows(NoSuchCustomerAccountException.class, () -> {
			objToTest.updateCustomerName("6532", "Me");
	    });
		
	}
}

	

