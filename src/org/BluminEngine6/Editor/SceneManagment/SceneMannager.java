package org.BluminEngine6.Editor.SceneManagment;

public class SceneMannager {

    private static Scene current;

    public static <t extends Scene> void ChangeScene(t s) {
        if(current != null) {
            current.logicsData.OnDestroy.Run();
            current = s;
            current.logicsData.OnSceneLoad.Run();
        }else{
            s.logicsData.OnSceneLoad.Run();
            current = s;
        }
    }

    public static Scene getCurrentScene() {
        return current;
    }
}
