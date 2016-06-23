import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyPress extends KeyAdapter {

	private KeyInterFace t;
	
	public KeyPress(KeyInterFace testInterFace) {
		this.t = testInterFace;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {

			t.key("Left");
		}
		if (key == KeyEvent.VK_RIGHT) {

			t.key("Right");
		}
		if (key == KeyEvent.VK_UP) {
			t.key("Up");
		}
		if (key == KeyEvent.VK_DOWN) {
			t.key("Down");
		}
	 
	if (key == KeyEvent.VK_R) {
		t.key("R");
     }
	}

	

}