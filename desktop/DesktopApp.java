import java.awt.*;

import javax.swing.*;


public class DesktopApp extends JFrame{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DesktopApp desktop = new DesktopApp();
	}

	public DesktopApp()
    {
		super("Music App");
		setSize(1000,1000);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		// Get the size of the screen
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    // Determine the new location of the window
	    int w = getSize().width;
	    int h = getSize().height;
	    int x = (dim.width-w)/2;
	    int y = (dim.height-h)/2;
	    // Move the window
	    setLocation(x, y);
	    
	    getContentPane().setBackground(Color.gray);
	    Font font = new Font("Trebuchet MS", Font.BOLD, 18);
	    Color color = new Color(0xA6,0x28,0x11);//red
	    Color color1 = new Color(0x00,0x33,0x99);//blue
	    
	    JLabel logo = new JLabel();
	    logo.setIcon(new ImageIcon(getClass().getResource("flat logo.png")));
	    logo.setBounds(80,0,270,100);
	    getContentPane().add(logo);
	    
	    
	    
	    JButton login = new JButton("login");
	    login.setBounds(590,40,90,20);
	    login.setFont(font);
	    login.setForeground(color1);
	    login.setBackground(getContentPane().getBackground());
	    login.setBorderPainted(false);
	    getContentPane().add(login);
	    
	    JButton register = new JButton("register");
	    register.setBounds(660,40,100,20);
	    register.setFont(font);
	    register.setForeground(color1);
	    register.setBackground(getContentPane().getBackground());
	    register.setBorderPainted(false);
	    getContentPane().add(register);
	    
	    JButton about = new JButton("about");
	    about.setBounds(745,40,85,20);
	    about.setFont(font);
	    about.setForeground(color1);
	    about.setBackground(getContentPane().getBackground());
	    about.setBorderPainted(false);
	    getContentPane().add(about);
	    
	    JButton help = new JButton("help");
	    help.setBounds(810,40,90,20);
	    help.setFont(font);
	    help.setForeground(color1);
	    help.setBackground(getContentPane().getBackground());
	    help.setBorderPainted(false);
	    getContentPane().add(help);
	    
	    JTextField searchField = new JTextField("Search for playlists, songs, and more!");
	    searchField.setBorder(BorderFactory.createLineBorder(Color.black));
	    searchField.setBounds(200,130,500,20);
	    getContentPane().add(searchField);
	    
	    JButton search = new JButton("Search!");
	    search.setBounds(720,130,80,20);
	    getContentPane().add(search);
	    
	    JCheckBox albums = new JCheckBox("albums");
	    albums.setBounds(250,160,90,20);
	    albums.setFont(font);
	    albums.setForeground(color);
	    albums.setBackground(getContentPane().getBackground());
	    getContentPane().add(albums);
	    
	    JCheckBox artists = new JCheckBox("artists");
	    artists.setBounds(350,160,90,20);
	    artists.setFont(font);
	    artists.setForeground(color);
	    artists.setBackground(getContentPane().getBackground());
	    getContentPane().add(artists);
	    
	    JCheckBox playlists = new JCheckBox("playlists");
	    playlists.setBounds(440,160,100,20);
	    playlists.setFont(font);
	    playlists.setForeground(color);
	    playlists.setBackground(getContentPane().getBackground());
	    getContentPane().add(playlists);
	    
	    JCheckBox songs = new JCheckBox("songs");
	    songs.setBounds(550,160,80,20);
	    songs.setFont(font);
	    songs.setForeground(color);
	    songs.setBackground(getContentPane().getBackground());
	    getContentPane().add(songs);
	    
	    JCheckBox members = new JCheckBox("members");
	    members.setBounds(630,160,110,20);
	    members.setFont(font);
	    members.setForeground(color);
	    members.setBackground(getContentPane().getBackground());
	    getContentPane().add(members);
	    
	    /*JTextField display = new JTextField();
	    display.setText("Welcome to MyFaves, where you can create your own playlists from scratch and search for them!\n\n");
	    getContentPane().add(display);*/
	    
	    repaint();
    }
}
