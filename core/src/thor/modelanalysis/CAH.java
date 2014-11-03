// Copyright 2013 Pedro B. Pascoal
package thor.modelanalysis;

import thor.Model;
import thor.modelanalysis.common.FeatureVector;

public class CAH implements FeatureVector {

	@Override
	public void extract(Model model) {
		// TODO Auto-generated method stub
		
		

		/*
		 void Histogram_Descriptor::SetHistogramDescriptor()
			{
				int cord_num=this->cordSet.size();
				DoubleVector pixel_length,pixel_angleXY,pixel_angleXZ,pixel_angleYZ;
				for (int i=0;i<cord_num;i++)
				{
					pixel_length.push_back(this->cordSet[i].length);
					pixel_angleXY.push_back(this->cordSet[i].angle_xy);
					pixel_angleXZ.push_back(this->cordSet[i].angle_xz);
					pixel_angleYZ.push_back(this->cordSet[i].angle_yz);
				}
			
				double max_pixel,min_pixel;
				this->length_histogram.GetExtremePixel(pixel_length,max_pixel,min_pixel);
				this->length_histogram.SetHistogram(pixel_length,HISTOGRAM_LEVEL,max_pixel,0);
			
				this->angleXY_histogram.SetHistogram(pixel_angleXY,HISTOGRAM_LEVEL,PI/2,-PI/2);
				this->angleXZ_histogram.SetHistogram(pixel_angleXZ,HISTOGRAM_LEVEL,PI/2,-PI/2);
				this->angleYZ_histogram.SetHistogram(pixel_angleYZ,HISTOGRAM_LEVEL,PI/2,-PI/2);
			}
		 */
		
		/*
		 Histogram_Descriptor::Histogram_Descriptor(Shape &shape)
			{
				//this->shapeName=shape.shapeName;
				//this->fileName=shape.fileName;
				this->pShape=&shape;
				for (int i=0;i<shape.tri_coordIndex.size();i++)
				{
					Cord cord(shape.normalized_baryCenter,shape.normalized_triCenter[i]);
					this->cordSet.push_back(cord);
				}
				SetHistogramDescriptor();
			}
		 */
		/*
		this->Get3DShapeDescriptor(shape_list);//information of shape histograms is in cord_angle_hist
		
		//fv_folder.append("feature-vector\\");
		char name[16];
		for (int i=0;i<shape_list.size();i++)
		{
			
			sprintf(name,"%s",shape_list[i].shapeName.c_str());
			string file_name;
			file_name.append(fv_folder).append(name);
			
			this->PrintFeatureVector(file_name,i);		
		} 
	 */
	
	 /* 
	this->model_count=shape_list.size();
	for (int i=0;i<this->model_count;i++)
	{
		printf("%s ...\n",shape_list[i].shapeName.c_str());
		Histogram_Descriptor *pDescriptor=new Histogram_Descriptor(shape_list[i]);
		this->descriptor_list.push_back(pDescriptor);		
	}
	*/
		
	}

	@Override
	public double[] getHistogram() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float compare(FeatureVector feature) {
		// TODO Auto-generated method stub
		
		/*
		//calculate the dissimilarity of lengths between two shapes (value:[0,2])
		double length_dis=this->CompareHistogram(
			this->descriptor_list[index1]->length_histogram,
			this->descriptor_list[index2]->length_histogram);
		//calculate the dissimilarity of angles between two shapes (value:[0,2])
		double angleXY_dis=this->CompareHistogram(
			this->descriptor_list[index1]->angleXY_histogram,
			this->descriptor_list[index2]->angleXY_histogram);
		double angleXZ_dis=this->CompareHistogram(
			this->descriptor_list[index1]->angleXZ_histogram,
			this->descriptor_list[index2]->angleXZ_histogram);
		double angleYZ_dis=this->CompareHistogram(
			this->descriptor_list[index1]->angleYZ_histogram,
			this->descriptor_list[index2]->angleYZ_histogram);
		double angle_dis=(angleXY_dis+angleXZ_dis+angleYZ_dis)/3;
		//normalize value to [0,1]
		double similarity = 1-(length_dis+angle_dis)/4;
		return similarity;
		 */
		return 0;
	}

	@Override
	public String printFeatureVector() {
		// TODO Auto-generated method stub
		return null;
	}
	
}