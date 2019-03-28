/*
 * Student Number: 2410516
 */
package music;

import music.MusicServiceStub.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;

public class Client extends JFrame implements ActionListener {
	// Final variables
	private final static int contentInset = 5;
	private final static int trackColumns = 130;
	private final static int trackRows = 20;
	private final static int gridLeft = GridBagConstraints.WEST;
	private final static String programTitle = "Music Album";

	// UI variables
	private GridBagConstraints contentConstraints = new GridBagConstraints();
	private GridBagLayout contentLayout = new GridBagLayout();
	private Container contentPane = getContentPane();
	private JButton discButton = new JButton("Check");
	private JLabel discLabel = new JLabel("Disc Number:");
	private JTextField discText = new JTextField(5);
	private JButton nameButton = new JButton("Check");
	private JLabel nameLabel = new JLabel("Composer/Artiste Name:");
	private JTextField nameText = new JTextField(16);
	private Font trackFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);
	private JLabel trackLabel = new JLabel("Tracks:");
	private JTextArea trackArea = new JTextArea(trackRows, trackColumns);
	private JScrollPane trackScroller = new JScrollPane(trackArea);

	// Tracks returned
	private Tracks tracks;
	// MusicServiceStub
	private MusicServiceStub musicServiceStub;

	public Client() throws Exception {
		// Setup UI
		contentPane.setLayout(contentLayout);
		addComponent(0, 0, gridLeft, nameLabel);
		addComponent(1, 0, gridLeft, nameText);
		addComponent(2, 0, gridLeft, nameButton);
		addComponent(0, 1, gridLeft, discLabel);
		addComponent(1, 1, gridLeft, discText);
		addComponent(2, 1, gridLeft, discButton);
		addComponent(0, 2, gridLeft, trackLabel);
		addComponent(0, 3, gridLeft, trackScroller);
		// Add action listeners
		nameButton.addActionListener(this);
		discButton.addActionListener(this);
		trackArea.setFont(trackFont);
		trackArea.setEditable(false);
		// Initialise stub
		musicServiceStub = new MusicServiceStub();
	}

	public static void main(String[] args) throws Exception {
		// Get screen dimensions
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		// Initialise client
		Client window = new Client();
		// Window setup
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle(programTitle);
		window.setResizable(false);
		window.pack();
		int windowWidth = window.getWidth();
		int windowHeight = window.getHeight();
		int windowX = (screenWidth - windowWidth) / 2;
		int windowY = (screenHeight - windowHeight) / 2;
		window.setLocation(windowX, windowY);
		window.setVisible(true);
	}

	/**
	 * Add component to contentPane of window
	 * 
	 * @param x
	 *            coordinate to position component
	 * @param y
	 *            coordinate to position component
	 * @param position
	 *            of component
	 * @param component
	 *            that needs adding
	 */
	private void addComponent(int x, int y, int position, JComponent component) {
		Insets contentInsets = new Insets(contentInset, contentInset, contentInset, contentInset);
		contentConstraints.gridx = x;
		contentConstraints.gridy = y;
		contentConstraints.anchor = position;
		contentConstraints.insets = contentInsets;
		if (component == trackArea || component == trackLabel)
			contentConstraints.gridwidth = GridBagConstraints.REMAINDER;
		contentLayout.setConstraints(component, contentConstraints);
		contentPane.add(component);
	}

	/**
	 * Action performed.
	 * 
	 * @param event
	 *            that has occurred.
	 */
	public void actionPerformed(ActionEvent event) {
		String trackRows = "";
		TrackDetail[] tracks;
		try {
			if (event.getSource() == nameButton)
				tracks = getField("composer", nameText.getText());
			else if (event.getSource() == discButton)
				tracks = getField("disc", discText.getText());
			else
				return;
			// Add to display on screen the table row column names
			trackRows += String.format("%4s %5s %-32s %-40s %-40s\n", "Disc", "Track", "Composer/Artist", "Work",
					"Title");
			for (int i = 0; i < tracks.length; i++) {
				TrackDetail trackDetail = tracks[i];

				// Add to display on screen in the table
				trackRows += String.format("%4s %5s %-32s %-40s %-40s\n", trackDetail.getDiscNumber(),
						trackDetail.getTrackNumber(), trackDetail.getComposerName(), trackDetail.getWorkName(),
						trackDetail.getTitleName());
			}
		} catch (ErrorFault exception) {
			String error = exception.getMessage();
			if (error == null)
				error = exception.toString();
			error = "could not get tracks - " + error;
			trackRows += error;
		}

		trackArea.setText(trackRows);
	}

	/**
	 * Get field value based on field name and the value.
	 * 
	 * @param field
	 *            name from database
	 * @param value
	 *            that the field value should be equal to
	 * @return Array of TrackDetail objects
	 * @throws ErrorFault
	 *             when an exception occurs
	 */
	private TrackDetail[] getField(String field, String value) throws ErrorFault {
		// Switch on the field name
		// Can easily add new fields in switch case block
		switch (field) {

		// Composer field
		case "composer":
			ComposerName composerName = new ComposerName();

			try {
				composerName.setComposerName(value);
				tracks = musicServiceStub.getByComposer(composerName);
			} catch (ErrorFault exception) {
				throw exception;
			} catch (RemoteException exception) {
				throw (new ErrorFault(exception.getMessage(), exception));
			}

			return tracks.getTracks().getTrackDetail();

		// Disc field
		case "disc":
			DiscNumber discNumber = new DiscNumber();

			try {
				discNumber.setDiscNumber(Integer.parseInt(value));
				tracks = musicServiceStub.getByDisc(discNumber);
			} catch (ErrorFault exception) {
				throw exception;
			} catch (NumberFormatException ex) {
				throw (new ErrorFault("DiscNumber must not be empty and must be an integer greater than or equal to 0!",
						ex));
			} catch (RemoteException exception) {
				throw (new ErrorFault(exception.getMessage(), exception));
			}

			return tracks.getTracks().getTrackDetail();
		default:
			// Returns null if the field name is not in the switch case
			// statement
			return null;

		}
	}

}
