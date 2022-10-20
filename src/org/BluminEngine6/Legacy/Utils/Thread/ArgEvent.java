package org.BluminEngine6.Legacy.Utils.Thread;

import BluminEngine5.Utils.Annotations.Obsolete;


@Obsolete
public interface ArgEvent<t>{
    t run();

    t getValue();
}
