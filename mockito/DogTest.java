package mockito;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;//スッキリさせるため。staticじゃなければ、Mockito.mock() になる。
import static org.mockito.MockitoAnnotations.*;

public class DogTest {

	@Test
	public void testBark() {
		Dog dog = new Dog();
		assertEquals(dog.Bark(), "WAN!");
	}
	
	@Test
	public void testBarkMock() {
		//mockは空っぽのモックオブジェクトにスタブメソッドを追加する
		//定義してないメソッドは呼べない
		
		Dog mock = mock(Dog.class);
//		assertEquals(mock.Bark(), "WAN!"); 定義していないので下記エラーになる
//		java.lang.AssertionError: expected:<null> but was:<WAN!>
		
		when(mock.Bark()).thenReturn("BOW!!!"); //スタブメソッドの定義
		assertEquals(mock.Bark(), "BOW!!!");
		
//		assertEquals(mock.Angry(), "GARUUU"); 定義していないので下記エラーになる
//		java.lang.AssertionError: expected:<null> but was:<GARUUU>
	}

	@Test
	public void testBarkSpy() {
		//spyは実オブジェクトのメソッドをスタブメソッドで置き換える
		//定義してないメソッドも呼び出せる
		
		Dog spy = spy(new Dog());
		assertEquals(spy.Bark(), "WAN!"); //スタブメソッド定義前
		
		when(spy.Bark()).thenReturn("BOW!!!");
		
		assertEquals(spy.Bark(), "BOW!!!"); //スタブメソッド定義後
		assertEquals(spy.Angry(), "GARUUU"); //定義していないメソッドも呼び出せる
	}
	
	@Test
	public void testAnswer() {
		Dog mock = mock(Dog.class);
		
		// thenReturn より細かい設定ができるのが Answer
		when(mock.Name(anyString())).thenAnswer(new Answer() {
		    public Object answer(InvocationOnMock invocation) {
		        Object[] args = invocation.getArguments();
		        return "My name is " + args[0];
		    }
		});
		assertEquals(mock.Name("pochi"), "My name is pochi");
	}
	
	//アノテーションによるモックオブジェクトの生成方法
	@Mock
	Dog anno_mock;//フィールドを定義 モックオブジェクトはここにインジェクトする
	@Test
	public void testAnno() {
		
		initMocks(this);//アノテーション初期化用  MockitoAnnotations.initMocks()
		
		when(anno_mock.Bark()).thenReturn("BOW!!!"); //スタブメソッドの定義
		assertEquals(anno_mock.Bark(), "BOW!!!");
	}
	
	@Test
	public void testThrow() {
		Dog mock = mock(Dog.class);
//		when(mock.Eat("fish")).thenThrow(new Exception());//返り値がないメソッドのためエラーになる
		
		//戻り値がないメソッドは doXXXX()を使う
		doThrow(new RuntimeException()).when(mock).Eat("fish"); //Eat()の戻り値をfishにする
		try {
			mock.Eat("fish");
			Assert.fail(); //ここは実行されない
		} catch (RuntimeException e) {
			
		}
	}
}