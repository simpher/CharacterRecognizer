package com.cqu.algorithm;

import ij.ImagePlus;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;

import java.util.Arrays;

public class ImageData {

	public static final String TYPE_RGB24="RGB24";
	public static final String TYPE_GRAY8="GRAY8";
	public static final String TYPE_GRAY2="GRAY2";
	
	private byte[] data;
	private int width;
	private int height;
	private String type;
	
	public ImageData(byte[] data, int width, int height, String type) {
		super();
		this.data = data;
		this.width = width;
		this.height = height;
		this.type = type;
	}
	
	public ImageData(ImageData imageData) {
		// TODO Auto-generated constructor stub
		this.data=Arrays.copyOf(imageData.data, imageData.data.length);
		this.width=imageData.width;
		this.height=imageData.height;
		this.type=imageData.type;
	}
	
	public byte[] getData() {
		return data;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public String getType() {
		return type;
	}
	
	public static ImageData toImageData(ImageProcessor ip)
	{
		String type=null;
		int bitDepth=ip.getBitDepth();
		if(bitDepth==24)
		{
			type=ImageData.TYPE_RGB24;
		}else if(bitDepth==8)
		{
			type=ImageData.TYPE_GRAY8;
		}else if(bitDepth==2)
		{
			type=ImageData.TYPE_GRAY2;
		}
		if(type==null||!(ip.getPixels() instanceof byte[]))
		{
			return null;
		}else
		{
			return new ImageData((byte[])ip.getPixels(), ip.getWidth(), ip.getHeight(), type);
		}
	}
	
	public ImageProcessor toImageProcessor()
	{
		return new ByteProcessor(this.width, this.height, data);
	}
	
	public ImagePlus toImagePlus()
	{
		return new ImagePlus("", new ByteProcessor(this.width, this.height, data));
	}
	
}
