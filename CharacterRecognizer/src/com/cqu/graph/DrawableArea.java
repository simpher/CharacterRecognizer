package com.cqu.graph;

import java.awt.Graphics;
import java.awt.Rectangle;

public class DrawableArea {
	
	private Rectangle validRect;
	
	public DrawableArea(Rectangle rawRect) {
		// TODO Auto-generated constructor stub
		int offsetLeft=11;
		int offsetTop=56;
		int offsetRight=11;
		int offsetBottom=11;
		
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
	
	/**
	 * 获得有效区域内的宽为width,高为height的对象绘制区<br/>
	 * 将对象居中显示在有效区域内，当对象高度或宽度不能容纳在区域内时，<br/>
	 * 对对象进行宽高比一定的绘画区缩放
	 * @param objectWidth
	 * @param objectHeight
	 * @return
	 */
	public Rectangle getObjectRect(int objectWidth, int objectHeight)
	{
		//有效区域的宽高比>=对象的宽高比
        if (validRect.width * objectHeight >= validRect.height * objectWidth)
        {
            //有效区域高度<对象高度，则只需要等比例缩小到对象高度=有效区域高度即可，然后使对象绘制区域在有效区域中居中
            if (validRect.height < objectHeight)
            {
                int newHeight = validRect.height;
                int newWidth = (int)(1.0 * objectWidth * newHeight / objectHeight);
                return new Rectangle(validRect.x + (validRect.width - newWidth-1) / 2, validRect.y, newWidth, newHeight);
            }
            else
            {
                //有效区域高度>=对象高度，则无需缩放对象，只需使对象绘制区域在有效区域中居中
                return new Rectangle(validRect.x + (validRect.width - objectWidth-1) / 2, validRect.y + (validRect.height - objectHeight-1) / 2, objectWidth, objectHeight);
            }
        }
        else//有效区域的宽高比<对象的宽高比
        {
            //有效区域宽度<对象宽度，则只需要等比例缩小到对象宽度=有效区域宽度即可，然后使对象绘制区域在有效区域中居中
            if (validRect.width < objectWidth)
            {
                int newWidth = validRect.width;
                int newHeight = (int)(1.0 * objectHeight * newWidth / objectWidth);
                return new Rectangle(validRect.x, validRect.y + (validRect.height - newHeight-1) / 2, newWidth, newHeight);
            }
            else
            {
                //有效区域宽度>=对象宽度，则无需缩放对象，只需使对象绘制区域在有效区域中居中
                return new Rectangle(validRect.x + (validRect.width - objectWidth-1) / 2, validRect.y + (validRect.height - objectHeight-1) / 2, objectWidth, objectHeight);
            }
        }
	}
	
	public void drawBorder(Graphics g)
	{
		g.drawRect(validRect.x-1, validRect.y-1, validRect.width+1, validRect.height+1);
	}
}
