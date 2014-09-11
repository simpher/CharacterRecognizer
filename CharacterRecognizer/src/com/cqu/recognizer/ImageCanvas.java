package com.cqu.recognizer;

import ij.ImagePlus;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.cqu.graph.DrawableArea;

public class ImageCanvas extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = -621032799763184951L;
	
	private ImagePlus iplus;
	
	public void setImageplus(ImagePlus iplus) {
		this.iplus = iplus;
	}
	
	public void refreshImage()
	{
		this.repaint();
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		if(iplus!=null)
		{
			DrawableArea da=new DrawableArea(new Rectangle(0, 0, this.getWidth(), this.getHeight()), 2, 2, 2, 2);
			Rectangle drawingRect=da.getObjectRect(iplus.getWidth(), iplus.getHeight());
			//da.drawBorder(g);
			g.drawImage(iplus.getImage(), drawingRect.x, drawingRect.y, drawingRect.width, drawingRect.height, null);
		}
	}

}
