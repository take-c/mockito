package mockito;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.*;

import static org.mockito.Mockito.*;

public class KonbiniTest {
	
	@InjectMocks
	private Konbini konbini; //buysake���C���W�F�N�g����
	
	@Mock
	private BuySake buysake; //���b�N�I�u�W�F�N�g
	
	@Test
    public void testInject() {
		initMocks(this);//�A�m�e�[�V�����������p
		
		//konbini�I�u�W�F�N�g�̒��ŁAbuysake�I�u�W�F�N�g���쐬���Ă���
		when(buysake.checkAge(anyInt())).thenReturn(false);

		String result = konbini.buyCannedBeer();
		assertEquals(result, "NG");
	}
}
