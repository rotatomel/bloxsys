package ar.com.blox.bloxsys.utils;

import org.apache.commons.codec.CharEncoding;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.Map;

/**
 * Clase de soporte para utilizar Apache Velocity.
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public abstract class VelocityHelper {

    /**
     * Retorna un <code>String</code> con el <code>template</code> mezclado con el <code>Map</code> pasado por
     * parámetro. Utiliza por defecto la codificación UTF-8.
     *
     * @param template
     * @param objects
     * @return un <code>String</code> con el template aplicado
     */
    public static String merge(String template, Map<String, Object> objects) {
        return merge(template, CharEncoding.UTF_8, objects);
    }

    /**
     * Retorna un <code>String</code> con el <code>template</code> mezclado con el <code>Map</code> pasado por
     * parámetro. Utiliza la codificación de caracteres pasada por parámetro.
     *
     * @param template
     * @param encoding
     * @param objects
     * @return un <code>String</code> con el template aplicado.
     */
    public static String merge(String template, String encoding, Map<String, Object> objects) {
        if (objects == null) {
            throw new IllegalArgumentException("Objects parameter cannot be null!");
        }
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();

        VelocityContext context = new VelocityContext();
        objects.forEach((x, y) -> context.put(x, y));

        Template t = ve.getTemplate(template, encoding);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }
}

