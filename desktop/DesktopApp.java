import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class DesktopApp extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	JButton login = new JButton("login");
	JButton register = new JButton("register");
	JButton about = new JButton("about");
	JButton help = new JButton("help");
	JTextField searchField = new JTextField("Search for playlists, songs, and more!");
	JLabel user = new JLabel("Username:");
	JTextField username = new JTextField(25);
	JLabel pass = new JLabel("Password:");
	JTextField password = new JTextField(25);
	JLabel confirm = new JLabel("Confirm Password:");
	JTextField confirmPassword = new JTextField(25);
	JButton search = new JButton("Search!");
	JCheckBox albums = new JCheckBox("albums");
	JCheckBox artists = new JCheckBox("artists");
	JCheckBox playlists = new JCheckBox("playlists");
	JCheckBox songs = new JCheckBox("songs");
	JCheckBox members = new JCheckBox("members");
	JButton loginsubmit = new JButton("Login");
	JButton registersubmit = new JButton("Register");
	
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
	    
	    login.setBounds(590,40,90,20);
	    login.setFont(font);
	    login.setForeground(color1);
	    login.setBackground(getContentPane().getBackground());
	    login.setBorderPainted(false);
	    login.addActionListener(this);
	    login.setContentAreaFilled(false);
	    getContentPane().add(login);
	    
	    register.setBounds(660,40,100,20);
	    register.setFont(font);
	    register.setForeground(color1);
	    register.setBackground(getContentPane().getBackground());
	    register.setBorderPainted(false);
	    register.addActionListener(this);
	    register.setContentAreaFilled(false);
	    getContentPane().add(register);
	    
	    about.setBounds(745,40,85,20);
	    about.setFont(font);
	    about.setForeground(color1);
	    about.setBackground(getContentPane().getBackground());
	    about.setBorderPainted(false);
	    about.setContentAreaFilled(false);
	    getContentPane().add(about);
	    
	    help.setBounds(810,40,90,20);
	    help.setFont(font);
	    help.setForeground(color1);
	    help.setBackground(getContentPane().getBackground());
	    help.setBorderPainted(false);
	    help.setContentAreaFilled(false);
	    getContentPane().add(help);
	    
	    searchField.setBorder(BorderFactory.createLineBorder(Color.black));
	    searchField.setBounds(200,130,500,20);
	    getContentPane().add(searchField);
	    
	    search.setBounds(720,130,80,20);
	    getContentPane().add(search);
	    
	    albums.setBounds(250,160,90,20);
	    albums.setFont(font);
	    albums.setForeground(color);
	    albums.setBackground(getContentPane().getBackground());
	    getContentPane().add(albums);
	    
	    artists.setBounds(350,160,90,20);
	    artists.setFont(font);
	    artists.setForeground(color);
	    artists.setBackground(getContentPane().getBackground());
	    getContentPane().add(artists);
	    
	    playlists.setBounds(440,160,100,20);
	    playlists.setFont(font);
	    playlists.setForeground(color);
	    playlists.setBackground(getContentPane().getBackground());
	    getContentPane().add(playlists);
	    
	    songs.setBounds(550,160,80,20);
	    songs.setFont(font);
	    songs.setForeground(color);
	    songs.setBackground(getContentPane().getBackground());
	    getContentPane().add(songs);
	    
	    members.setBounds(630,160,110,20);
	    members.setFont(font);
	    members.setForeground(color);
	    members.setBackground(getContentPane().getBackground());
	    getContentPane().add(members);
	    
	    user.setBounds(350,300,90,20);
	    username.setBounds(450,300,150,20);
	    
	    pass.setBounds(350,340,90,20);
	    password.setBounds(450,340,150,20);
	    
	    confirm.setBounds(320,380,110,20);
	    confirmPassword.setBounds(450,380,150,20);
	    
	    loginsubmit.setBounds(420,440,90,20);
	    registersubmit.setBounds(420,440,90,20);
	    
	    /*JLabel display = new JLabel();
	    display.setText("Welcome to MyFaves, where you can create your own playlists from scratch and search for them!\n\n");
	    getContentPane().add(display);*/
	    
	    repaint();
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton button = (JButton) arg0.getSource();
		if(button == login)
		{
			getContentPane().add(user);
			getContentPane().add(username);
			getContentPane().add(pass);
			getContentPane().add(password);
			getContentPane().add(loginsubmit);
			getContentPane().remove(confirm);
			getContentPane().remove(confirmPassword);
			getContentPane().remove(registersubmit);
			repaint();
		}
		if(button == register)
		{
			getContentPane().add(user);
			getContentPane().add(username);
			getContentPane().add(pass);
			getContentPane().add(password);
			getContentPane().add(confirm);
			getContentPane().add(confirmPassword);
			getContentPane().add(registersubmit);
			getContentPane().remove(loginsubmit);
			repaint();
		}
	}
}
