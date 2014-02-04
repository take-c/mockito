package mockito;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;//�X�b�L�������邽�߁Bstatic����Ȃ���΁AMockito.mock() �ɂȂ�B
import static org.mockito.MockitoAnnotations.*;

public class DogTest {

	@Test
	public void testBark() {
		Dog dog = new Dog();
		assertEquals(dog.Bark(), "WAN!");
	}
	
	@Test
	public void testBarkMock() {
		//mock�͋���ۂ̃��b�N�I�u�W�F�N�g�ɃX�^�u���\�b�h��ǉ�����
		//��`���ĂȂ����\�b�h�͌ĂׂȂ�
		
		Dog mock = mock(Dog.class);
//		assertEquals(mock.Bark(), "WAN!"); ��`���Ă��Ȃ��̂ŉ��L�G���[�ɂȂ�
//		java.lang.AssertionError: expected:<null> but was:<WAN!>
		
		when(mock.Bark()).thenReturn("BOW!!!"); //�X�^�u���\�b�h�̒�`
		assertEquals(mock.Bark(), "BOW!!!");
		
//		assertEquals(mock.Angry(), "GARUUU"); ��`���Ă��Ȃ��̂ŉ��L�G���[�ɂȂ�
//		java.lang.AssertionError: expected:<null> but was:<GARUUU>
	}

	@Test
	public void testBarkSpy() {
		//spy�͎��I�u�W�F�N�g�̃��\�b�h���X�^�u���\�b�h�Œu��������
		//��`���ĂȂ����\�b�h���Ăяo����
		
		Dog spy = spy(new Dog());
		assertEquals(spy.Bark(), "WAN!"); //�X�^�u���\�b�h��`�O
		
		when(spy.Bark()).thenReturn("BOW!!!");
		
		assertEquals(spy.Bark(), "BOW!!!"); //�X�^�u���\�b�h��`��
		assertEquals(spy.Angry(), "GARUUU"); //��`���Ă��Ȃ����\�b�h���Ăяo����
	}
	
	@Test
	public void testAnswer() {
		Dog mock = mock(Dog.class);
		
		// thenReturn ���ׂ����ݒ肪�ł���̂� Answer
		when(mock.Name(anyString())).thenAnswer(new Answer() {
		    public Object answer(InvocationOnMock invocation) {
		        Object[] args = invocation.getArguments();
		        return "My name is " + args[0];
		    }
		});
		assertEquals(mock.Name("pochi"), "My name is pochi");
	}
	
	//�A�m�e�[�V�����ɂ�郂�b�N�I�u�W�F�N�g�̐������@
	@Mock
	Dog anno_mock;//�t�B�[���h���` ���b�N�I�u�W�F�N�g�͂����ɃC���W�F�N�g����
	@Test
	public void testAnno() {
		
		initMocks(this);//�A�m�e�[�V�����������p  MockitoAnnotations.initMocks()
		
		when(anno_mock.Bark()).thenReturn("BOW!!!"); //�X�^�u���\�b�h�̒�`
		assertEquals(anno_mock.Bark(), "BOW!!!");
	}
	
	@Test
	public void testThrow() {
		Dog mock = mock(Dog.class);
//		when(mock.Eat("fish")).thenThrow(new Exception());//�Ԃ�l���Ȃ����\�b�h�̂��߃G���[�ɂȂ�
		
		//�߂�l���Ȃ����\�b�h�� doXXXX()���g��
		doThrow(new RuntimeException()).when(mock).Eat("fish"); //Eat()�̖߂�l��fish�ɂ���
		try {
			mock.Eat("fish");
			Assert.fail(); //�����͎��s����Ȃ�
		} catch (RuntimeException e) {
			
		}
	}
}