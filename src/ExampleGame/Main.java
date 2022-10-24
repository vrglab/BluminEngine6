package ExampleGame;

import org.BluminEngine6.Application;
import org.BluminEngine6.Editor.SceneManagment.SceneMannager;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Object.BluminBehaviour;
import org.BluminEngine6.Render.DisplayMode;
import org.BluminEngine6.Render.Resolution;

public class Main {

    public static void main(String[] arg) {
        SceneMannager.ChangeScene(new ExampleScene());
        Application.Run(Resolution.CurentRes(), DisplayMode.WindowedLocked);
    }


}
