package com.cqu.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class DialogUtil {
	
	/**
	 * 过滤文件的类型放在fileTypes数组中，比如{".jpg", ".png", ".bmp"},
	 * 或者{"jpg", "png", "bmp"},或者{".*"}
	 * @param fileTypes
	 * @return
	 */
	public static File dialogOpenFile(final String[] fileTypes)
	{
		JFileChooser jfileChooser=new JFileChooser();
		jfileChooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean accept(File file) {
				// TODO Auto-generated method stub
				if(file.isDirectory()==true)
				{
					return true;
				}
				String fileName=file.getName();
				if(fileTypes[0].equals(".*")==true)
				{
					return true;
				}
				for(int i=0;i<fileTypes.length;i++)
				{
					if(fileName.endsWith(fileTypes[i])==true)
					{
						return true;
					}
				}
				return false;
			}
		});
		if(jfileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			return jfileChooser.getSelectedFile();
		}
		return null;
	}
}
