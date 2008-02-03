/**
 * 
 */
package org.carrot2.core.attribute;

import java.util.Map;

import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

/**
 *
 */
@Root(name = "component-metadata")
public class BindableMetadata
{
    @ElementMap(name = "attributes", entry = "attribute", key = "field-name", inline = false, attribute = true)
    private Map<String, AttributeMetadata> attributeMetadata;

    BindableMetadata()
    {
    }

    public BindableMetadata(Map<String, AttributeMetadata> attributeMetadata)
    {
        this.attributeMetadata = attributeMetadata;
    }
    
    public Map<String, AttributeMetadata> getAttributeMetadata()
    {
        return attributeMetadata;
    }

    void setAttributeMetadata(Map<String, AttributeMetadata> attributeMetadata)
    {
        this.attributeMetadata = attributeMetadata;
    }
}
