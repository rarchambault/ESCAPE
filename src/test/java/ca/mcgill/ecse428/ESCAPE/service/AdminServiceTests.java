package ca.mcgill.ecse428.ESCAPE.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse428.ESCAPE.dto.UserProfileRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import ca.mcgill.ecse428.ESCAPE.model.Admin;
import ca.mcgill.ecse428.ESCAPE.repository.AdminRepository;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTests {

	// Replace the repository with a "mock" that exposes the same interface
	@Mock
	AdminRepository adminRepo;

	// Get a service that uses the mock repository
	@InjectMocks
	AdminService adminService;

	@Test
	public void testGetAdminByEmail() {
		// Tell the mocked repository how to behave
		final Admin party = new Admin();
		String email = party.getEmail();
		when(adminRepo.findAdminByEmail(email)).thenAnswer((InvocationOnMock invocation) -> party);

		// Test that the service behaves properly
		Admin admin = adminService.getAdminByEmail(email);

		assertNotNull(admin);
		assertEquals(email, admin.getEmail());
	}

	@Test
	public void testGetAdminByUnrecognizedEmail() {
		final String UnrecognizedEmail = "anUnrecognizedEmail";

		// Mock: if asking for a admin with invalid ID, return null
		when(adminRepo.findAdminByEmail(UnrecognizedEmail)).thenAnswer((InvocationOnMock invocation) -> null);

		// call method, and obtain resulting exception
		EscapeException ex = assertThrows(EscapeException.class,
				() -> adminService.getAdminByEmail(UnrecognizedEmail));

		// check results
		assertEquals("Admin not found.", ex.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}

	@Test
	public void testCreateAdmin() {
		// Mock: just return the admin with no modification
		when(adminRepo.save(any(Admin.class))).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0));

		// test set up - create an admin request
		String name = "aName";
		String email = "anEmail";
		String password = "aPassword";
		UserProfileRequestDto adminRequest = new UserProfileRequestDto(name, email, password);
		adminRequest.setName(name);

		// call method
		UserProfileResponseDto returnedAdmin = adminService.createAdmin(adminRequest);

		// check results
		assertNotNull(returnedAdmin);
		assertEquals(name, returnedAdmin.getName());
		// Check that the service actually saved the admin
		// verify(adminRepo, times(1)).save();
	}
	
	@Test
	public void testGetAllAdmins() {
		
	}

	// test delete admin
	@Test
	public void testDeleteAdmin() {
	}

	// test invaleud delete admin - not found
	@Test
	public void testInvalidDeleteAdmin() {
		// Mock: if searching using an invalid admin email return null
		when(adminRepo.findAdminByEmail(any(String.class))).thenAnswer(x -> null);

		// call method, expecting exception
		EscapeException ex = assertThrows(EscapeException.class,
				() -> adminService.deleteAdmin("anUnrecognizedEmail"));

		// check results
		assertEquals(ex.getMessage(), "Admin not found.");
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}

}
