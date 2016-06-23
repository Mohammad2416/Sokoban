import java.net.URL;

import javax.swing.ImageIcon;

public class Wall extends Actor {

	private final int SPACE = 30;
//	private int x;
//	private int y;
//	private Image image;

	public Wall(int x, int y, String wallIcon) {

		this.x = x;
		this.y = y;
		URL loc = this.getClass().getResource(wallIcon);// area
		ImageIcon iia = new ImageIcon(loc);
		image = iia.getImage();
		this.setImage(image);
	}

	@Override
	public boolean isLeftCollision(Actor actor) {
		  return ((this.x - SPACE) == actor.getX()) && (this.y == actor.getY());
	}

	@Override
	public boolean isRightCollision(Actor actor) {
		  return ((this.x + SPACE) == actor.getX()) && (this.y == actor.getY());
	}

	@Override
	public boolean isTopCollision(Actor actor) {
		 return ((this.y - SPACE) == actor.getY()) && (this.x == actor.getX());
	}

	@Override
	public boolean isBottomCollision(Actor actor) {
		return ((this.y + SPACE) == actor.getY()) && (this.x == actor.getX());
	}

	@Override
	public void move(int x, int y) {}
	
	


}
