package com.cqu.util;

import java.io.File;

public class FileUtil {
	
	/**
	 * 
	 * @param f
	 * @return {dir, fullName}
	 */
	public static String[] getDirAndFullName(File f)
	{
		String path=f.getPath();
		int index=path.lastIndexOf('/');
		if(index==-1)
		{
			index=path.lastIndexOf('\\');
		}
		return new String[]{path.substring(0, index+1), path.substring(index+1)};
	}
}
