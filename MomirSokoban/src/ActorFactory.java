
public class ActorFactory {

    public Actor getActor(String actorType, int x, int y, String icon){
        if(actorType == null){
            return null;
        }

        if(actorType.equalsIgnoreCase("WALL")){
            return new Wall(x, y, icon);

        } else if(actorType.equalsIgnoreCase("CRATE")) {
            return new Crate(x, y, icon);

        } else if(actorType.equalsIgnoreCase("BLANK_MARK")){
            return new BlankMark(x, y, icon);

        } else if(actorType.equalsIgnoreCase("PLAYER")){
            return new Player(x, y, icon);
        }

        return null;
    }


}