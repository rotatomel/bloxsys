/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.blox.bloxsys.search;

import org.apache.commons.lang3.StringUtils;

/**
 * Filtro de búsquda básico en base a texto
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class BasicTextSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String text;

    public BasicTextSearchFilter() {
    }

    @Override
    public boolean hasFilter() {
        return hasTextFilter();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean hasTextFilter() {
        return StringUtils.isNotBlank(text);
    }
}
