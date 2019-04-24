package pkgGui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pkgGui.Background.ImagePanel;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class TitleScreen {

	private JFrame frmKanagotchi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TitleScreen window = new TitleScreen();
					window.frmKanagotchi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TitleScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Initialize
		frmKanagotchi = new JFrame();
		frmKanagotchi.getContentPane().setLayout(null);
		
		//Names
		frmKanagotchi.setTitle("KanaGotchi");
		frmKanagotchi.setName("KanaGotchi");
		
		//Resolutions
		frmKanagotchi.setResizable(false);
		frmKanagotchi.setBounds(100, 100, 800, 600);
		
		//Operations
		frmKanagotchi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Center the frame
		frmKanagotchi.setLocationRelativeTo(null);
		frmKanagotchi.setVisible(true);
		
		//UI Images
		//ImageIcon newGame = new ImageIcon("resources/Images/UI/NuevaPartida.png");
		
		
		//Background Image
		Background.ImagePanel background = new Background.ImagePanel(
		        new ImageIcon("resources/Images/Backgrounds/TitleScreen.png").getImage());
		frmKanagotchi.getContentPane().add(background);
		
		JLabel lblNuevaPartida = new JLabel("Nueva partida");
		lblNuevaPartida.setForeground(Color.WHITE);
		lblNuevaPartida.setFont(new Font("Curse Casual", Font.BOLD, 35));
		lblNuevaPartida.setBounds(316, 360, 236, 101);
		frmKanagotchi.getContentPane().add(lblNuevaPartida);
	
		
	}
	
}
