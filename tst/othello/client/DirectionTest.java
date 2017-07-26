package othello.client;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static othello.client.Direction.*;

import org.junit.Before;
import org.junit.Test;

public class DirectionTest {

	private XYPointer xyPointer;

	@Before
	public void setUp(){
		this.xyPointer = new XYPointer(1,1);
	}

	@Test
	public void UPをnext1_1して0_1test() {
		xyPointer = UP.next(xyPointer);

		int actual 	= xyPointer.getxPointer();
		int expected	= 0;
		assertThat("X:", actual,is(expected));

		actual 			= xyPointer.getyPointer();
		expected		= 1;
		assertThat("Y:", actual,is(expected));
	}
	@Test
	public void DOWNをnext1_1して2_1test() {
		xyPointer = DOWN.next(xyPointer);

		int actual 	= xyPointer.getxPointer();
		int expected	= 2;
		assertThat("X:", actual,is(expected));

		actual 			= xyPointer.getyPointer();
		expected		= 1;
		assertThat("Y:", actual,is(expected));
	}
	@Test
	public void RIGHTをnext1_1して1_2test() {
		xyPointer = RIGHT.next(xyPointer);

		int actual 	= xyPointer.getxPointer();
		int expected	= 1;
		assertThat("X:", actual,is(expected));

		actual 			= xyPointer.getyPointer();
		expected		= 2;
		assertThat("Y:", actual,is(expected));
	}
	@Test
	public void LEFTをnext1_1して1_0test() {
		xyPointer = LEFT.next(xyPointer);

		int actual 	= xyPointer.getxPointer();
		int expected	= 1;
		assertThat("X:", actual,is(expected));

		actual 			= xyPointer.getyPointer();
		expected		= 0;
		assertThat("Y:", actual,is(expected));
	}
	@Test
	public void UPRIGHTをnext1_1して0_2test() {
		xyPointer = UPRIGHT.next(xyPointer);

		int actual 	= xyPointer.getxPointer();
		int expected	= 0;
		assertThat("X:", actual,is(expected));

		actual 			= xyPointer.getyPointer();
		expected		= 2;
		assertThat("Y:", actual,is(expected));
	}
	@Test
	public void UPLEFTをnext1_1して0_0test() {
		xyPointer = UPLEFT.next(xyPointer);

		int actual 	= xyPointer.getxPointer();
		int expected	= 0;
		assertThat("X:", actual,is(expected));

		actual 			= xyPointer.getyPointer();
		expected		= 0;
		assertThat("Y:", actual,is(expected));
	}
	@Test
	public void DOWNRIGHTをnext1_1して2_2test() {
		xyPointer = DOWNRIGHT.next(xyPointer);

		int actual 	= xyPointer.getxPointer();
		int expected	= 2;
		assertThat("X:", actual,is(expected));

		actual 			= xyPointer.getyPointer();
		expected		= 2;
		assertThat("Y:", actual,is(expected));
	}
	@Test
	public void DOWNLEFTをnext1_1して2_0test() {
		xyPointer = DOWNLEFT.next(xyPointer);

		int actual 	= xyPointer.getxPointer();
		int expected	= 2;
		assertThat("X:", actual,is(expected));

		actual 			= xyPointer.getyPointer();
		expected		= 0;
		assertThat("Y:", actual,is(expected));
	}

}
