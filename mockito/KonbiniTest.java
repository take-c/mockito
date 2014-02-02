package mockito;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.*;

import static org.mockito.Mockito.*;

public class KonbiniTest {
	
	@InjectMocks
	private Konbini konbini;
	
	@Mock
	private BuySake buysake;

	@InjectMocks
	private Law law;
	
	@Test
    public void testInject() {
		initMocks(this);
		when(buysake.checkAge(anyInt())).thenReturn(false);
		
		String result = konbini.buyCannedBeer();
		assertEquals(result, "NG");
	}
}
