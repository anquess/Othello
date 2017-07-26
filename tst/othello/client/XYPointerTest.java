package othello.client;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class XYPointerTest {

	@Test
	public void XYPointerコンストラクタ引数int値5_6のgetxPointerで5が戻ってくるtest() {
		XYPointer sut = new XYPointer(5, 6);
		int expected	= 5;
		int actual		= sut.getxPointer();
		assertThat(actual,is(expected));
	}
	@Test
	public void XYPointerコンストラクタ引数int値0_6のgetyPointerで6が戻ってくるtest() {
		XYPointer sut = new XYPointer(0, 6);
		int expected	= 6;
		int actual		= sut.getyPointer();
		assertThat(actual,is(expected));
	}
	@Test(expected = IllegalArgumentException.class)
	public void XYPointerコンストラクタ引数int値マイナス4_1でIllegalArgumentExceptionを送出する(){
		new XYPointer(-4, 1);
	}
	@Test(expected = IllegalArgumentException.class)
	public void XYPointerコンストラクタ引数int値4_マイナス1でIllegalArgumentExceptionを送出する(){
		new XYPointer(4, -1);
	}
	@Test(expected = IllegalArgumentException.class)
	public void XYPointerコンストラクタ引数int値8_0でIllegalArgumentExceptionを送出する(){
		new XYPointer(8,0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void XYPointerコンストラクタ引数int値0_8でIllegalArgumentExceptionを送出する(){
		new XYPointer(0, 8);
	}

	@Test
	public void XYPointerコンストラクタ引数JButtonのgetActionCommandが14でgetyPointerで1が戻ってくるtest() {
		XYPointer sut = new XYPointer(new JButton(){
			@Override
			public String getActionCommand(){
				return "14";
			}
		});
		int expected	= 1;
		int actual		= sut.getyPointer();
		assertThat(actual,is(expected));
	}
	@Test
	public void XYPointerコンストラクタ引数JButtonのgetActionCommandが14でgetxPointerで6が戻ってくるtest() {
		XYPointer sut = new XYPointer(new JButton(){
			@Override
			public String getActionCommand(){
				return "14";
			}
		});
		int expected	= 6;
		int actual		= sut.getxPointer();
		assertThat(actual,is(expected));
	}


}
