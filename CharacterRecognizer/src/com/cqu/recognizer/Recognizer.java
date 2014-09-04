package com.cqu.recognizer;

import ij.IJ;
import ij.ImagePlus;

import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import com.cqu.util.DialogUtil;
import com.cqu.util.FileUtil;
import com.cqu.util.XmlUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class Recognizer extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 450043483540861678L;
	public static final String SETTING_FILE_PATH="setting/settings.xml";
	public static final String SETTING_NAME_DEFAULT_OPEN_DIR="default_open_dir";
	
	private ImagePlus iplus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Recognizer window = new Recognizer();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Recognizer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("文件");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("打开");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String defaultDir=XmlUtil.readSetting(SETTING_FILE_PATH, SETTING_NAME_DEFAULT_OPEN_DIR);
				
				File f=DialogUtil.dialogOpenFile(new String[]{".jpg"}, "打开文字图片", defaultDir);
				if(f!=null)
				{
					XmlUtil.writeSetting(SETTING_FILE_PATH, SETTING_NAME_DEFAULT_OPEN_DIR, FileUtil.getDirAndFullName(f)[0]);
					
					iplus=IJ.openImage(f.getPath());
					int bitDepth=iplus.getBitDepth();
					if(bitDepth!=24)
					{
						DialogUtil.dialogShowMessage("Warning", "图像不是24位RGB图像");
					}else
					{
						repaint();
					}
				}
			}
		});
		menu.add(menuItem);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		if(iplus!=null)
		{
			g.drawImage(iplus.getImage(), 0, 0, this);
		}
	}
	
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		super.update(g);
	}

}
