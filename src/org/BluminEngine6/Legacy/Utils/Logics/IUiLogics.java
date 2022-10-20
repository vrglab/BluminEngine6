package org.BluminEngine6.Legacy.Utils.Logics;

public interface IUiLogics{
    void OnUiRender();
    void Update();

    /**The Init function is called after the windows is made*/
    void Init();

    /**The PreInit function is called before the windows is made*/
    void PreInit();
    /**The OnExit function is called before the game closes*/
    void OnExit();
    void SceneLoad();
    void Destroy();
}
