package com.jstarcraft.core.common.selection.xpath;

import org.jaxen.JaxenException;

import com.jstarcraft.core.common.selection.XpathSelector;
import com.jstarcraft.core.common.selection.xpath.swing.SwingNode;
import com.jstarcraft.core.common.selection.xpath.swing.SwingXPath;

public class SwingXpathSelector extends XpathSelector<SwingNode> {

    public SwingXpathSelector(String query) {
        super(query);
        try {
            this.xpath = new SwingXPath(query);
        } catch (JaxenException exception) {
            throw new RuntimeException(exception);
        }
    }

}
