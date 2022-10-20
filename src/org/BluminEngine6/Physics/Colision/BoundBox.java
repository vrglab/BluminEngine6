package org.BluminEngine6.Physics.Colision;


import org.BluminEngine6.Legacy.Utils.Math.Vector3;

import java.io.Serializable;

public class BoundBox  implements Serializable {
    //public Transform transform = Transform.DefaultZero;
    public Vector3 TopLeftFront= Vector3.Zero, TopLeftBack= Vector3.Zero,
               TopRightFront= Vector3.Zero, TopRightBack= Vector3.Zero,
               BottomLeftFront= Vector3.Zero,  BottomLeftBack= Vector3.Zero,
               BottomRightFront= Vector3.Zero,  BottomRightBack = Vector3.Zero;


    public BoundBox(Vector3 topLeftFront, Vector3 topLeftBack, Vector3 topRightFront, Vector3 topRightBack, Vector3 bottomLeftFront, Vector3 bottomLeftBack, Vector3 bottomRightFront, Vector3 bottomRightBack) {
        TopLeftFront = topLeftFront;
        TopLeftBack = topLeftBack;
        TopRightFront = topRightFront;
        TopRightBack = topRightBack;
        BottomLeftFront = bottomLeftFront;
        BottomLeftBack = bottomLeftBack;
        BottomRightFront = bottomRightFront;
        BottomRightBack = bottomRightBack;
    }

    public BoundBox() {

    }
}
