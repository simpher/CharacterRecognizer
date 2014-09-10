package com.cqu.feature;

import java.util.Arrays;

import com.cqu.algorithm.ImageData;
import com.cqu.util.CollectionUtil;

public class FeatureExtractor {
	
	public static double[] et1DT12(ImageData imageData, int edgeSegmentCount)
	{
		int dataRows=imageData.getHeight();
		int dataCols=imageData.getWidth();
		byte[] byteData=imageData.getData();
		
		int segmentRows = dataRows / edgeSegmentCount;
        int segmentCols = dataCols / edgeSegmentCount;
        int gapRowHalf = (dataRows % segmentRows) / 2;
        int gapColHalf = (dataCols % segmentCols) / 2;
        int rowOddOffset = 0;
        int colOddOffset = 0;
        if ((dataRows % segmentRows) % 2 == 1)
        {
            rowOddOffset = 1;
        }
        if ((dataCols % segmentCols) % 2 == 1)
        {
            colOddOffset = 1;
        }

        double[] et1Feature = new double[4 * edgeSegmentCount];
        double[] dt12Feature = new double[4 * edgeSegmentCount];
        Arrays.fill(et1Feature, 0);
        Arrays.fill(dt12Feature, 0);
        //左边界ET1和DT12外围特征
        for (int i = gapRowHalf; i < (dataRows - gapRowHalf-rowOddOffset); i++)
        {
            int segmentColIndex = (i - gapRowHalf) /segmentRows ;
            int whiteBlackChangeTimes=0;
            Boolean pixelIsWhite = true;
            for (int j = gapColHalf; j < (dataCols - gapColHalf-colOddOffset); j++)
            {
                if (byteData[i * dataCols + j] == 255)
                {
                    if (pixelIsWhite == false)
                    {
                        whiteBlackChangeTimes++;
                    }
                    pixelIsWhite = true;
                }
                else
                {
                    if (pixelIsWhite == true)
                    {
                        whiteBlackChangeTimes++;
                    }
                    pixelIsWhite = false;
                }
                if (whiteBlackChangeTimes > 2)
                {
                    break;
                }
                if (pixelIsWhite == true)
                {
                    if (whiteBlackChangeTimes == 0)
                    {
                        et1Feature[0 * edgeSegmentCount + segmentColIndex] += 1;
                    }else if(whiteBlackChangeTimes==2)
                    {
                        dt12Feature[0 * edgeSegmentCount + segmentColIndex] += 1;
                    }
                }
            }
        }
        //上边界ET1和DT12外围特征
        for (int j = gapColHalf; j < (dataCols - gapColHalf - colOddOffset); j++)
        {
            int segmentColIndex = (j - gapColHalf) / segmentCols;
            int whiteBlackChangeTimes = 0;
            Boolean pixelIsWhite = true;
            for (int i = gapRowHalf; i < (dataRows - gapRowHalf - rowOddOffset); i++)
            {
                if(byteData[i * dataCols + j] == 255)
                {
                    if(pixelIsWhite==false)
                    {
                        whiteBlackChangeTimes++;
                    }
                    pixelIsWhite=true;
                }else
                {
                    if(pixelIsWhite==true)
                    {
                        whiteBlackChangeTimes++;
                    }
                    pixelIsWhite=false;
                }
                if(whiteBlackChangeTimes>2)
                {
                    break;
                }
                if(pixelIsWhite==true)
                {
                    if(whiteBlackChangeTimes==0)
                    {
                        et1Feature[1 * edgeSegmentCount + segmentColIndex] += 1;
                    }else if(whiteBlackChangeTimes==2)
                    {
                        dt12Feature[1 * edgeSegmentCount + segmentColIndex] += 1;
                    }
                }
            }
        }
        //右边界ET1和DT12外围特征
        for (int i = gapRowHalf; i < (dataRows - gapRowHalf-rowOddOffset); i++)
        {
            int segmentColIndex = (i - gapRowHalf) /segmentRows ;
            int whiteBlackChangeTimes = 0;
            Boolean pixelIsWhite = true;
            for (int j = (dataCols - gapColHalf-colOddOffset) - 1; j > gapColHalf-1; j--)
            {
                if (byteData[i * dataCols + j] == 255)
                {
                    if (pixelIsWhite == false)
                    {
                        whiteBlackChangeTimes++;
                    }
                    pixelIsWhite = true;
                }
                else
                {
                    if (pixelIsWhite == true)
                    {
                        whiteBlackChangeTimes++;
                    }
                    pixelIsWhite = false;
                }
                if (whiteBlackChangeTimes > 2)
                {
                    break;
                }
                if(pixelIsWhite==true)
                {
                    if(whiteBlackChangeTimes==0)
                    {
                        et1Feature[2 * edgeSegmentCount + segmentColIndex] += 1;
                    }else if(whiteBlackChangeTimes==2)
                    {
                        dt12Feature[2 * edgeSegmentCount + segmentColIndex] += 1;
                    }
                }
            }
        }
        //下边界ET1和DT12外围特征
        for (int j = gapColHalf; j < (dataCols - gapColHalf - colOddOffset); j++)
        {
            int segmentColIndex = (j - gapColHalf) / segmentCols;
            int whiteBlackChangeTimes = 0;
            Boolean pixelIsWhite = true;
            for (int i = (dataRows - gapRowHalf - rowOddOffset) - 1; i > gapRowHalf - 1; i--)
            {
                if (byteData[i * dataCols + j] == 255)
                {
                    if (pixelIsWhite == false)
                    {
                        whiteBlackChangeTimes++;
                    }
                    pixelIsWhite = true;
                }
                else
                {
                    if (pixelIsWhite == true)
                    {
                        whiteBlackChangeTimes++;
                    }
                    pixelIsWhite = false;
                }
                if (whiteBlackChangeTimes > 2)
                {
                    break;
                }
                if (pixelIsWhite == true)
                {
                    if (whiteBlackChangeTimes == 0)
                    {
                        et1Feature[3 * edgeSegmentCount + segmentColIndex] += 1;
                    }
                    else if (whiteBlackChangeTimes == 2)
                    {
                        dt12Feature[3 * edgeSegmentCount + segmentColIndex] += 1;
                    }
                }
            }
        }
        return CollectionUtil.merge(et1Feature, dt12Feature);
	}
	
}
