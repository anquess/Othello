package othello.client;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class XYCoordinateTest {

	@Test
	public void XYCoordinateコンストラクタ引数int値5_6のgetxCoordinateで5が戻ってくるtest() {
		XYCoordinate sut = new XYCoordinate(5, 6);
		int expected	= 5;
		int actual		= sut.getxCoordinate();
		assertThat(actual,is(expected));
	}
	@Test
	public void XYCoordinateコンストラクタ引数int値0_6のgetyCoordinateで6が戻ってくるtest() {
		XYCoordinate sut = new XYCoordinate(0, 6);
		int expected	= 6;
		int actual		= sut.getyCoordinate();
		assertThat(actual,is(expected));
	}
	@Test(expected = IllegalArgumentException.class)
	public void XYCoordinateコンストラクタ引数int値マイナス4_1でIllegalArgumentExceptionを送出する(){
		new XYCoordinate(-4, 1);
	}
	@Test(expected = IllegalArgumentException.class)
	public void XYCoordinateコンストラクタ引数int値4_マイナス1でIllegalArgumentExceptionを送出する(){
		new XYCoordinate(4, -1);
	}
	@Test(expected = IllegalArgumentException.class)
	public void XYCoordinateコンストラクタ引数int値8_0でIllegalArgumentExceptionを送出する(){
		new XYCoordinate(8,0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void XYCoordinateコンストラクタ引数int値0_8でIllegalArgumentExceptionを送出する(){
		new XYCoordinate(0, 8);
	}

	@Test
	public void XYCoordinateコンストラクタ引数JButtonのgetActionCommandが14でgetyCoordinateで1が戻ってくるtest() {
		XYCoordinate sut = new XYCoordinate(new JButton(){
			@Override
			public String getActionCommand(){
				return "14";
			}
		});
		int expected	= 1;
		int actual		= sut.getyCoordinate();
		assertThat(actual,is(expected));
	}
	@Test
	public void XYCoordinateコンストラクタ引数JButtonのgetActionCommandが14でgetxCoordinateで6が戻ってくるtest() {
		XYCoordinate sut = new XYCoordinate(new JButton(){
			@Override
			public String getActionCommand(){
				return "14";
			}
		});
		int expected	= 6;
		int actual		= sut.getxCoordinate();
		assertThat(actual,is(expected));
	}


}
