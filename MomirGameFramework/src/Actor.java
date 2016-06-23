import java.awt.Image;

abstract class Actor {
	public int x;
	public int y;
	public Image image;

	public abstract boolean isLeftCollision(Actor actor);

	public abstract boolean isRightCollision(Actor actor);

	public abstract boolean isTopCollision(Actor actor);

	public abstract boolean isBottomCollision(Actor actor);

	public abstract void move(int x, int y);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
