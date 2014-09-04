package com.cqu.graph;

import java.awt.Rectangle;

public class DrawableArea {
	
	private Rectangle validRect;
	
	public DrawableArea(Rectangle rawRect) {
		// TODO Auto-generated constructor stub
		int offsetLeft=5;
		int offsetTop=5;
		int offsetRight=5;
		int offsetBottom=5;
		
		validRect=new Rectangle(rawRect.x+offsetLeft, rawRect.y+offsetTop, rawRect.width-
				offsetLeft-offsetRight, rawRect.height-offsetTop-offsetBottom);
	}
	
	public DrawableArea(Rectangle rawRect, int spaceTop, int offsetLeft, int offsetTop, int offsetRight, int offsetBottom)
	{
		validRect=new Rectangle(rawRect.x+offsetLeft, rawRect.y+offsetTop, rawRect.width-
				offsetLeft-offsetRight, rawRect.height-offsetTop-offsetBottom);
	}
	
	public Rectangle getValidRect()
	{
		return this.validRect;
	}
}
