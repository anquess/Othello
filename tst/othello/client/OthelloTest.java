package othello.client;

import java.awt.Component;
import java.awt.event.MouseEvent;

import org.junit.Test;

public class OthelloTest {

	@Test
	public void test() {
		Othello sut = new Othello("Test");
		Component component = sut.getComponent(0);
		MouseEvent e = new MouseEvent(component, 0, 0, 0, 0, 0, 0, false);

	}

}
