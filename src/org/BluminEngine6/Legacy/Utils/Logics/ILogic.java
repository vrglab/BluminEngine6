package org.BluminEngine6.Legacy.Utils.Logics;

/**
 * Basic interface used to give Logic methods to class's
 * @author Vrglab
 */
public interface ILogic {
    void Update();
    void OnRender();

    /**The Init function is called after the windows is made*/
    void Init();

    /**The PreInit function is called before the windows is made*/
    void Awake();
    /**The OnExit function is called before the game closes*/
    void OnExit();
}
