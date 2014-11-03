// Copyright 2013 Pedro B. Pascoal
package thor.modelanalysis.common;

import thor.Model;

public interface FeatureDescriptor {
	
    public void extract(Model model);

    public double[] getHistogram();

    float compare(FeatureVector feature);

    java.lang.String printFeatureVector();
}
