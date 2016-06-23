import java.net.URL;

import javax.swing.ImageIcon;

public class Player extends Actor {

	public final int SPACE = 30;
//	public int x;
//	public int y;
//	public Image image;

	public Player(int x, int y, String playerIcon) {

		this.x = x;
		this.y = y;
		URL loc = this.getClass().getResource(playerIcon);// area
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
	public void move(int x, int y) {
		int nx = this.x + x;
		int ny = this.y + y;
		this.setX(nx);
		this.setY(ny);
	}
	
	


}
