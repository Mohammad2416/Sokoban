import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BuildWorld extends JPanel implements KeyInterFace{

    //private final int SPACE = 30;

    /**
	 * 
	 */
	private static final long serialVersionUID = -2540017970697425131L;
	private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;

    private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<Crate> baggs = new ArrayList<>();
    private ArrayList<BlankMark> blankMarks = new ArrayList<>();

    private Player player;
   // private Actor player;
    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    private String crateIcon;
    private String playerIcon;
    private String blankmarkedIcon;
    private String wallIcon;
    private  int SPACE;


    private String level;


    public  BuildWorld(String map ,String crateIcon, String playerIcon, String blankmarkedIcon, String wallIcon, int space) {
        level = map;
        this.crateIcon = crateIcon;
        this.playerIcon = playerIcon;
        this.blankmarkedIcon = blankmarkedIcon;
        this.wallIcon = wallIcon;
        SPACE = space;
        addKeyListener( new KeyPress(this));
        setFocusable(true);
        initWorld();

    }


    int getBoardWidth() {
        return this.w;
    }

     int getBoardHeight() {
        return this.h;
    }

    private  void initWorld() {

        int OFFSET = 50;
        int x = OFFSET;
        int y = OFFSET;



ActorFactory actorFactory=new ActorFactory();

        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            } else if (item == '#') {
                walls.add((Wall) actorFactory.getActor("WALL", x, y, wallIcon));
                x += SPACE;
            } else if (item == '$') {
                baggs.add((Crate) actorFactory.getActor("CRATE", x, y, crateIcon));
                x += SPACE;
            } else if (item == '.') {
                blankMarks.add((BlankMark) actorFactory.getActor("BLANK_MARK", x, y, blankmarkedIcon));
                x += SPACE;
            } else if (item == '@') {
                 player = (Player) actorFactory.getActor("PLAYER", x, y, playerIcon);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            }

            h = y;
        }
    }

    private void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList<Actor> world = new ArrayList<>();
        world.addAll(walls);
        world.addAll(blankMarks);
        world.addAll(baggs);
        world.add(player);

        for (Actor item : world) {

            if ((item instanceof Player)  || (item instanceof Crate)) {
                g.drawImage(item.getImage(), item.getX() + 2, item.getY() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.getX(), item.getY(), this);
            }

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }


    private boolean checkWallCollision(Actor actor, int type) {

        if (type == LEFT_COLLISION) {

            for (Wall wall : walls) {
                if (actor.isLeftCollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == RIGHT_COLLISION) {

            for (Wall wall : walls) {
                if (actor.isRightCollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == TOP_COLLISION) {

            for (Wall wall : walls) {
                if (actor.isTopCollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == BOTTOM_COLLISION) {

            for (Wall wall : walls) {
                if (actor.isBottomCollision(wall)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean checkBagCollision(int type) {

        if (type == LEFT_COLLISION) {

            for (int i = 0; i < baggs.size(); i++) {

                Crate bag = baggs.get(i);
                if (player.isLeftCollision(bag)) {

                    for (Crate item : baggs) {
                        if (!bag.equals(item)) {
                            if (bag.isLeftCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag, LEFT_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(-SPACE, 0);
                    isCompleted();
                }
            }
            return false;

        } else if (type == RIGHT_COLLISION) {

            for (int i = 0; i < baggs.size(); i++) {

                Crate bag = baggs.get(i);
                if (player.isRightCollision(bag)) {
                    for (Crate item : baggs) {

                        if (!bag.equals(item)) {
                            if (bag.isRightCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag, RIGHT_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(SPACE, 0);
                    isCompleted();
                }
            }
            return false;

        } else if (type == TOP_COLLISION) {

            for (int i = 0; i < baggs.size(); i++) {

                Crate bag = baggs.get(i);
                if (player.isTopCollision(bag)) {
                    for (Crate item : baggs) {

                        if (!bag.equals(item)) {
                            if (bag.isTopCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag, TOP_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(0, -SPACE);
                    isCompleted();
                }
            }

            return false;

        } else if (type == BOTTOM_COLLISION) {

            for (int i = 0; i < baggs.size(); i++) {

                Crate bag = baggs.get(i);
                if (player.isBottomCollision(bag)) {
                    for (Crate item : baggs) {

                        if (!bag.equals(item)) {
                            if (bag.isBottomCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag,
                                BOTTOM_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(0, SPACE);
                    isCompleted();
                }
            }
        }

        return false;
    }

//..........................    
    private void isCompleted() {

        int num = baggs.size();
        int compl = 0;

        for (Object bagg : baggs) {
            Crate bag = (Crate) bagg;
            for (int j = 0; j < num; j++) {
                BlankMark blankMark = blankMarks.get(j);
                if (bag.getX() == blankMark.getX()  && bag.getY() == blankMark.getY()) {
                    compl += 1;
                    java.net.URL loc = this.getClass().getResource("cratemarked.png");
                    ImageIcon iia = new ImageIcon(loc);
                    bag.setImage(iia.getImage());
//                    PlaySound bg = new PlaySound();
//                    bg.play("sound1.wav");
                }
            }
        }

        if (compl == num) {
            completed = true;
            repaint();
        }
    }
    
    
    
//...........................
    private void restartLevel() {

        blankMarks.clear();
        baggs.clear();
        walls.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }


	  
	   
	@Override
	public void key(String arrow) {
		  if (completed) {
              return;
          }

          if (arrow.equalsIgnoreCase("Left")) {
              if (checkWallCollision(player, LEFT_COLLISION)) {
                  return;
              }

              if (checkBagCollision(LEFT_COLLISION)) {
                  return;
              }

              player.move(-SPACE, 0);

          } else if (arrow.equalsIgnoreCase("Right")) {

              if (checkWallCollision(player,RIGHT_COLLISION)) {
                  return;
              }

              if (checkBagCollision(RIGHT_COLLISION)) {
                  return;
              }

              player.move(SPACE, 0);

          } else if (arrow.equalsIgnoreCase("Up")) {

              if (checkWallCollision(player,TOP_COLLISION)) {
                  return;
              }

              if (checkBagCollision(TOP_COLLISION)) {
                  return;
              }

              player.move(0, -SPACE);

          } else if (arrow.equalsIgnoreCase("Down")) {

              if (checkWallCollision(player, BOTTOM_COLLISION)) {
                  return;
              }

              if (checkBagCollision(BOTTOM_COLLISION)) {
                  return;
              }

              player.move(0, SPACE);

          } else if (arrow.equalsIgnoreCase("R")) {
              restartLevel();
          }

          repaint();
		
	}
		
		
		
	}

