package org.BluminEngine6.Legacy.Utils.Annotations;


import java.lang.annotation.*;


/**
 * {@summary Marks a function that can only be called in the PreInit method in the Engine system}
 * @author Vrglab
 * @since 0.0.1.0_Dev
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PreInitOnly {

}
