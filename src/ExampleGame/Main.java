package ExampleGame;

import org.BluminEngine6.Application;
import org.BluminEngine6.Object.BluminBehaviour;
import org.BluminEngine6.Render.DisplayMode;
import org.BluminEngine6.Render.Resolution;

public class Main {

    public static void main(String[] arg) {
        sain s = new sain();
        Application.Run(Resolution.CurentRes(), DisplayMode.WindowedLocked);
    }


    static class sain extends BluminBehaviour {


        @Override
        public void Update() {

        }

        @Override
        public void OnRender() {

        }

        @Override
        public void Init() {

        }

        @Override
        public void PreInit() {

        }

        @Override
        public void OnExit() {

        }

        @Override
        public void SceneLoad() {

        }

        @Override
        public void Destroy() {

        }
    }

}
