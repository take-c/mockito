package mockito;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.*;

import static org.mockito.Mockito.*;

public class KonbiniTest {
	
	@InjectMocks
	private Konbini konbini; //buysakeをインジェクトする
	
	@Mock
	private BuySake buysake; //モックオブジェクト
	
	@Test
    public void testInject() {
		initMocks(this);//アノテーション初期化用
		
		//konbiniオブジェクトの中で、buysakeオブジェクトを作成している
		when(buysake.checkAge(anyInt())).thenReturn(false);

		String result = konbini.buyCannedBeer();
		assertEquals(result, "NG");
	}
}
