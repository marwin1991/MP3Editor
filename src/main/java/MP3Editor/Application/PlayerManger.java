package MP3Editor.Application;

import MP3Editor.Player.Player;

public class PlayerManger {
    private static PlayerManger INSTANCE = null;
    private Player player;

    private PlayerManger(){
        player = new Player();
    }

    public Player getPlayer(){
        return this.player;
    }

    public static PlayerManger getInstance(){
        if (INSTANCE == null)
            INSTANCE = new PlayerManger();
        return INSTANCE;
    }
}
