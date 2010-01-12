/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2009, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package org.carrot2.examples.core;

import java.io.InputStream;
import java.util.Map;

import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.core.ProcessingResult;
import org.carrot2.core.SimpleController;
import org.carrot2.core.attribute.AttributeNames;
import org.carrot2.examples.ConsoleFormatter;
import org.carrot2.source.google.GoogleDocumentSource;
import org.carrot2.util.CloseableUtils;
import org.carrot2.util.attribute.AttributeValueSets;

import com.google.common.collect.Maps;

/**
 * This example shows how load a set of attribute values from an XML stream.
 * 
 * @see SavingAttributeValuesToXml
 * @see <a href="http://download.carrot2.org/head/manual/#section.customizing.component-suites-and-attributes.saving-with-workbench">Saving attributes to XML using Carrot2 Document Clustering Workbench</a>
 */
public class LoadingAttributeValuesFromXml
{
    public static void main(String [] args) throws Exception
    {
        InputStream xmlStream = null;
        try
        {
            xmlStream = LoadingAttributeValuesFromXml.class
                .getResourceAsStream("/org/carrot2/examples/core/algorithm-lingo-attributes.xml");

            // Load attribute value sets from the XML stream
            final AttributeValueSets attributeValueSets = AttributeValueSets
                .deserialize(xmlStream);

            // Get the desired set of attribute values for use with further processing
            final Map<String, Object> defaultAttributes = attributeValueSets
                .getDefaultAttributeValueSet().getAttributeValues();

            final Map<String, Object> fasterClusteringAttributes = attributeValueSets
                .getAttributeValueSet("faster-clustering").getAttributeValues();

            // Perform processing using the attribute values
            final SimpleController controller = new SimpleController();

            // Initialize the controller with one attribute set
            controller.init(fasterClusteringAttributes);

            // Perform clustering using the attribute set provided at initialization time
            Map<String, Object> requestAttributes = Maps.newHashMap();
            requestAttributes.put(AttributeNames.QUERY, "clustering");
            ProcessingResult results = controller.process(requestAttributes,
                GoogleDocumentSource.class, LingoClusteringAlgorithm.class);
            ConsoleFormatter.displayClusters(results.getClusters());

            // Perform clustering using some other attribute set, in this case the
            // one that is the default in the XML file.
            requestAttributes = Maps.newHashMap(defaultAttributes);
            requestAttributes.put(AttributeNames.QUERY, "clustering");
            results = controller.process(requestAttributes, GoogleDocumentSource.class,
                LingoClusteringAlgorithm.class);
            ConsoleFormatter.displayClusters(results.getClusters());
        }
        finally
        {
            CloseableUtils.close(xmlStream);
        }
    }
}
