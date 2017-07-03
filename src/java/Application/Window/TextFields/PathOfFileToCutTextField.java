package Application.Window.TextFields;

public class PathOfFileToCutTextField extends javafx.scene.control.TextField{
    private static PathOfFileToCutTextField INSTANCE = null;
    private PathOfFileToCutTextField(){
        super.setMinWidth(550);
        super.setLayoutX(40);
        super.setLayoutY(20);
        super.setPromptText("Path to the file you want to trimm.");
    }
    public static PathOfFileToCutTextField getInstance(){
        if( INSTANCE == null)
            INSTANCE = new PathOfFileToCutTextField();
        return INSTANCE;
    }
}
