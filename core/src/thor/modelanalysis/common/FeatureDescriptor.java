// Copyright 2013 Pedro B. Pascoal
package thor.modelanalysis.common;

import thor.Model;

/**
 * This is the basic interface for all content based features.
 *
 * @author Pedro B. Pascoal
 */
public abstract class FeatureDescriptor {
	protected final double MAX_PIXEL = 1000;	// default
	protected final double MIN_PIXEL = -1000;	// default
	
    public abstract void extract(Model model);

    public abstract double[] getHistogram();

    public abstract double compare(FeatureDescriptor feature);

	//static double compare(FeatureDescriptor f1, FeatureDescriptor f2);

    public abstract java.lang.String printFeatureVector();
}
