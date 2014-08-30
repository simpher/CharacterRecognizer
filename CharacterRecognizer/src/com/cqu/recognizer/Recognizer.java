package com.cqu.recognizer;

import java.awt.EventQueue;

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

public class Recognizer {
	
	public static final String SETTING_FILE_PATH="setting/settings.xml";
	public static final String SETTING_NAME_DEFAULT_OPEN_DIR="default_open_dir";

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Recognizer window = new Recognizer();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
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
				}
			}
		});
		menu.add(menuItem);
	}

}
