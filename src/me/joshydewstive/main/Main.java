package me.joshydewstive.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.axet.vget.VGet;
import com.github.axet.vget.info.VGetParser;
import com.github.axet.vget.info.VideoInfo;
import com.github.axet.vget.vhs.YouTubeMPGParser;
import com.github.axet.vget.vhs.YouTubeQParser;
import com.github.axet.wget.info.ex.DownloadInterruptedError;
import com.github.axet.vget.vhs.YouTubeInfo.YoutubeQuality;

public class Main {

	private JFrame frmYoutubeDownloader;
	private JTextField txtYTLink;
	
	private JRadioButton rb360;
	private JRadioButton rb480;
	private JRadioButton rb720;
	private JRadioButton rb1080;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmYoutubeDownloader.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmYoutubeDownloader = new JFrame();
		frmYoutubeDownloader.setResizable(false);
		frmYoutubeDownloader.setTitle("Youtube Downloader");
		frmYoutubeDownloader.getContentPane().setBackground(Color.WHITE);
		frmYoutubeDownloader.getContentPane().setLayout(null);
		
		JLabel lblYoutubeDown = new JLabel("Youtube Downloader");
		lblYoutubeDown.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 32));
		lblYoutubeDown.setHorizontalAlignment(SwingConstants.CENTER);
		lblYoutubeDown.setBounds(10, 11, 664, 47);
		frmYoutubeDownloader.getContentPane().add(lblYoutubeDown);
		
		txtYTLink = new JTextField();
		txtYTLink.setText("Put the Youtube link here");
		txtYTLink.setToolTipText("Youtube link here (Full link only)");
		txtYTLink.setBounds(10, 86, 664, 20);
		frmYoutubeDownloader.getContentPane().add(txtYTLink);
		txtYTLink.setColumns(10);
		
		JLabel lblYoutubeLink = new JLabel("Youtube Link:");
		lblYoutubeLink.setBounds(10, 69, 259, 14);
		frmYoutubeDownloader.getContentPane().add(lblYoutubeLink);
		
		rb360 = new JRadioButton("360p");
		rb360.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rb480.setSelected(false);
				rb720.setSelected(false);
				rb1080.setSelected(false);
			}
		});
		rb360.setBounds(10, 113, 109, 23);
		frmYoutubeDownloader.getContentPane().add(rb360);
		
		rb480 = new JRadioButton("480p");
		rb480.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rb360.setSelected(false);
				rb720.setSelected(false);
				rb1080.setSelected(false);
			}
		});
		rb480.setBounds(140, 113, 109, 23);
		frmYoutubeDownloader.getContentPane().add(rb480);
		
		rb720 = new JRadioButton("720p");
		rb720.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rb360.setSelected(false);
				rb480.setSelected(false);
				rb1080.setSelected(false);
			}
		});
		rb720.setBounds(269, 113, 109, 23);
		frmYoutubeDownloader.getContentPane().add(rb720);
		
		rb1080 = new JRadioButton("1080p");
		rb1080.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rb360.setSelected(false);
				rb480.setSelected(false);
				rb720.setSelected(false);
			}
		});
		rb1080.setBounds(405, 113, 109, 23);
		frmYoutubeDownloader.getContentPane().add(rb1080);
		
		JButton btnDownload = new JButton("DOWNLOAD");
		btnDownload.setForeground(Color.ORANGE);
		btnDownload.setBackground(new Color(0, 128, 0));
		btnDownload.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 32));
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	        	btnDownload.setText("DOWNLOADING...");		
	        	infoBox("Press okay to start the download \n Be advised some videos may have separate video and audio tracks, as this is the problem of the orignal author, nothing can be done to fix that as of Version "+References.VERSION,"",JOptionPane.INFORMATION_MESSAGE);
				try {
					
					
		        	System.out.println("Downloading....");
		        	final String dir = System.getProperty("user.dir");
		        	System.out.println("Downloaded to: "+dir);
		            String url = txtYTLink.getText();
		            String path = dir;
		            
		            URL web = new URL(url);
		            VGetParser user = VGet.parser(web);
		            if(rb360.isSelected()) {
			            user = new YouTubeQParser(YoutubeQuality.p360);
		            } else if(rb480.isSelected()) {
			            user = new YouTubeQParser(YoutubeQuality.p480);
		            } else if(rb720.isSelected()) {
			            user = new YouTubeQParser(YoutubeQuality.p720);
		            } else if(rb1080.isSelected()) {
			            user = new YouTubeQParser(YoutubeQuality.p1080);
		            }
		            user = new YouTubeMPGParser();
		            
		            VideoInfo videoinfo = user.info(web);
		            VGet v = new VGet(videoinfo,new File(path));
		            v.download();
		            infoBox("Done! File located where this program is! ("+dir+")","Success!",JOptionPane.INFORMATION_MESSAGE);
		        } catch (DownloadInterruptedError e) {
		        	infoBox("The download failed. Download Interrupted.","Oh no!",JOptionPane.ERROR_MESSAGE);
		        } catch (RuntimeException e) {
		        	infoBox("The download failed. Runtime Error ("+e.getMessage()+").","Oh no!",JOptionPane.ERROR_MESSAGE);
		        } catch (Exception e) {
		        	infoBox("The download failed. Unknown Exception ("+e.getMessage()+").","Oh no!",JOptionPane.ERROR_MESSAGE);
		        }
				btnDownload.setText("DOWNLOAD");
			}
		});
		btnDownload.setBounds(10, 143, 664, 107);
		frmYoutubeDownloader.getContentPane().add(btnDownload);
		frmYoutubeDownloader.setBounds(100, 100, 700, 300);
		frmYoutubeDownloader.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rb360.setSelected(true);
	}
	
	public static void infoBox(String infoMessage, String titleBar,int pane)
    {
        JOptionPane.showMessageDialog(null, infoMessage,titleBar, pane);
    }
	
}
