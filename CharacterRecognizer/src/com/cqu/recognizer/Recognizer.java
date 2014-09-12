package com.cqu.recognizer;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageConverter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.cqu.algorithm.ImageData;
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
	
	private ImagePlus iplusRaw;
	private ImagePlus iplus;
	private ImageCanvas imageCanvas;
	private JLabel labelInfo;
	private JLabel labelOnProcessing;

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
		this.setContentPane(this.createContentPanel());
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("文件");
		menuBar.add(menuFile);
		
		JMenuItem menuItemOpen = new JMenuItem("打开");
		menuItemOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String defaultDir=XmlUtil.readSetting(SETTING_FILE_PATH, SETTING_NAME_DEFAULT_OPEN_DIR);
				
				File f=DialogUtil.dialogOpenFile(new String[]{".jpg"}, "打开文字图片", defaultDir);
				if(f!=null)
				{
					XmlUtil.writeSetting(SETTING_FILE_PATH, SETTING_NAME_DEFAULT_OPEN_DIR, FileUtil.getDirAndFullName(f)[0]);
					
					iplus=IJ.openImage(f.getPath());
					iplusRaw=iplus.duplicate();
					int bitDepth=iplus.getBitDepth();
					if(bitDepth!=24)
					{
						DialogUtil.dialogShowMessage("Warning", "图像不是24位RGB图像");
					}else
					{
						imageCanvas.setImageplus(iplus);
						imageCanvas.refreshImage();
						
						setInfo(ImageData.easyTypeByBitDepth(iplus.getBitDepth()));
					}
				}
			}
		});
		menuFile.add(menuItemOpen);
		
		JMenuItem menuItemOriginalPicture=new JMenuItem("原图");
		menuItemOriginalPicture.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(iplusRaw!=null)
				{
					iplus=iplusRaw;
					imageCanvas.setImageplus(iplus);
					imageCanvas.refreshImage();
					
					setInfo(ImageData.easyTypeByBitDepth(iplus.getBitDepth()));
				}
			}
		});
		menuFile.add(menuItemOriginalPicture);
		
		JMenu menuEdit=new JMenu("编辑");
		menuBar.add(menuEdit);
		
		JMenuItem menuItemGray8=new JMenuItem("8位灰度");
		menuItemGray8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(iplus==null)
				{
					return;
				}
				if(iplus.getBitDepth()!=24)
				{
					DialogUtil.dialogShowMessage("Warning", "图像不是24位RGB图像");
					return;
				}
				ImageConverter ic=new ImageConverter(iplus);
				ic.convertToGray8();
				imageCanvas.refreshImage();
				
				setInfo(ImageData.easyTypeByBitDepth(iplus.getBitDepth()));
			}
		});
		menuEdit.add(menuItemGray8);
		
		JMenuItem menuItemGray2=new JMenuItem("二值灰度");
		menuItemGray2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(iplus==null)
				{
					return;
				}
				if(iplus.getBitDepth()!=8)
				{
					DialogUtil.dialogShowMessage("Warning", "图像不是8位灰度图像");
					return;
				}
				String th=JOptionPane.showInputDialog("Threshold=", "128");
				iplus.getProcessor().threshold(Integer.parseInt(th));
				iplus.updateImage();
				imageCanvas.refreshImage();
				
				setInfo("二值灰度图像");
			}
		});
		menuEdit.add(menuItemGray2);
		
		this.processingOn(false);
	}
	
	private JPanel createContentPanel()
	{
		JPanel contentPanel=new JPanel(new BorderLayout());
		imageCanvas=new ImageCanvas();
		contentPanel.add(imageCanvas, BorderLayout.CENTER);
		
		JToolBar statusBar=new JToolBar(JToolBar.HORIZONTAL);
		statusBar.setFloatable(false);
		statusBar.addSeparator(new Dimension(5, statusBar.getHeight()));
		labelInfo=new JLabel("就绪");
		statusBar.add(labelInfo);
		labelOnProcessing=new JLabel(new ImageIcon("resources/loading.gif"));
		statusBar.addSeparator(new Dimension(5, statusBar.getHeight()));
		statusBar.add(labelOnProcessing);
		
		contentPanel.add(statusBar, BorderLayout.SOUTH);
		
		return contentPanel;
	}
	
	private void setInfo(String info)
	{
		this.labelInfo.setText(info);
	}
	
	private void processingOn(boolean on)
	{
		this.labelOnProcessing.setVisible(on);
	}
}
