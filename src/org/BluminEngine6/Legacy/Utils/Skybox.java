package org.BluminEngine6.Legacy.Utils;

import BluminEngine5.Application;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Componant.Rendering.CubeMapRenderer;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.Utils.Annotations.HelperClass;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchivedFile;


/**
 * Pre-made GameObject used for rendering a SkyBox
 * @author Vrglab
 */
@HelperClass
public class Skybox extends BluminBehaviour {

    CubeMapRenderer cmr;

    ArchivedFile singelImage;

    int[] file,archive;

    @Override
    public void Update() {
    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        Mesh m = new Mesh(Utils.CubeAsVertex3D(500));
        Shader shader = Application.getResourceManager().GetShader("Res/Shaders/Default/Cubemap/DefaultGameShader.json");



        try {
            cmr = new CubeMapRenderer(m,
                    Utils.CreateCubeMap(4),
                    shader);
            if(singelImage != null) {
                cmr = new CubeMapRenderer(m,
                        Utils.CreateCubeMap(singelImage.ID,singelImage.ArchiveId),
                        shader);
            } else if(archive != null) {
                cmr = new CubeMapRenderer(m,
                        Utils.CreateCubeMap(file,archive),
                        shader);
            }
        }catch(Exception e) {

        }
        RegisterComponant(cmr);
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
