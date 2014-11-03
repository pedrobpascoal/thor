// Copyright 2013 Pedro B. Pascoal
package thor.modelanalysis.common;

import thor.Model;

/**
 * This is the basic interface for all content based features.
 *
 * @author Pedro B. Pascoal
 */
public interface FeatureVector {
	
    public void extract(Model model);

    public double[] getHistogram();

    float compare(FeatureVector feature);

    java.lang.String printFeatureVector();
}