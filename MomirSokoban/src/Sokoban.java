import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Sokoban extends JFrame {

    private final int OFFSET = 50;
    private String level =
                      "    ######\n"
                    + "    ##   ##\n"
                    + "    ##$  ##\n"
                    + "  ####  $ #\n"
                    + "  ##  $ $ ##########\n"
                    + "#### # ##       ####\n"
                    + "#### #    ##  # ####\n"
                    + "##   # ##  ## #   .#\n"
                    + "## $  $           .#\n"
                    + "###    ##   @ #  ..#\n"
                    + " ## . #  .  ######## \n"
                    + " ###########\n";


    public Sokoban() {

        InitUI();
    }

    public void InitUI() {
        BuildWorld world = new BuildWorld(level, "crate.png" ,"player.png", "blankmarked.png", "wall.png" , 30);
        add(world);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(world.getBoardWidth() + OFFSET,
                world.getBoardHeight() + 2*OFFSET);
        setLocationRelativeTo(null);
        setTitle("Sokoban");
//        
//        java.net.URL loc = this.getClass().getResource("cratemarked.png");    
//        setContentPane(new JLabel(new ImageIcon(loc)));
//        
        PlaySound bg = new PlaySound();
        bg.play("sound1.wav");
        
    }


    public static void main(String[] args) {
        Sokoban sokoban = new Sokoban();
        sokoban.setVisible(true);
    }
}