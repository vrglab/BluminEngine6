package org.BluminEngine6.Legacy.Utils.Annotations;


import java.lang.annotation.*;

/**
 * {@summary Marks a class that needs its Create method called before use}
 * @author Vrglab
 * @since 0.0.1.0_Dev
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MustCreate {
}
