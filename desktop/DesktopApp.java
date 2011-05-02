import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;


public class DesktopApp extends JFrame implements ActionListener{
	
	JButton login = new JButton("login");
	JLabel displayUser = new JLabel();
	JButton register = new JButton("register");
	JButton logout = new JButton("logout");
	JButton about = new JButton("about");
	JButton help = new JButton("help");
	JButton addsongbottom = new JButton("Add Selected to Playlist");
    JButton addsongtop = new JButton("Add Selected to Playlist");
	JTextField searchField = new JTextField("Search for playlists, songs, and more!");
	JLabel user = new JLabel("Username:");
	JTextField username = new JTextField(25);
	JLabel pass = new JLabel("Password:");
	JPasswordField password = new JPasswordField(25);
	JLabel confirm = new JLabel("Confirm Password:");
	JPasswordField confirmPassword = new JPasswordField(25);
	JButton search = new JButton("Search!");
	JRadioButton albums = new JRadioButton("albums");
	JRadioButton artists = new JRadioButton("artists");
	JRadioButton playlists = new JRadioButton("playlists");
	JRadioButton songs = new JRadioButton("songs");
	JRadioButton members = new JRadioButton("members");
	JButton loginsubmit = new JButton("Login");
	JButton registersubmit = new JButton("Register");
	JTable table = new JTable(new MyTableModel());
	JScrollPane scrollPane = new JScrollPane(table);
	ArrayList<Song> songlist = new ArrayList<Song>();
	ArrayList<Artist> artistlist = new ArrayList<Artist>();
	ArrayList<Album> albumlist = new ArrayList<Album>();
	ArrayList<Playlist> playlistlist = new ArrayList<Playlist>();
	myfavsCC myfavs;
	ArrayList<JCheckBox> booleanValues = new ArrayList<JCheckBox>();
	JButton addSong = new JButton("Add new song!");
	JLabel createSong = new JLabel("Enter new Song");
	JTextField inputText = new JTextField(25);
	JLabel createArtist = new JLabel("Enter new Artist");
	JLabel createAlbum = new JLabel("Enter new Album");
	JLabel createTrack = new JLabel("Enter new Track #");
	JLabel pickfrom = new JLabel("Pick from:");
	JTextField songInputText = new JTextField(25);
	JLabel or = new JLabel("or");
	JComboBox list = new JComboBox();
	JButton nextButton = new JButton("Next");
	JButton nextButton2 = new JButton("Next");
	JButton finalizeButton = new JButton("Finish");
	int artist_id;
	String songName;
	int album_id;
	int trackNum;
	ButtonGroup group = new ButtonGroup();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DesktopApp desktop = new DesktopApp();
	}

	public DesktopApp()
    {	
		super("myfaves");
		setSize(1000,700);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		try
		{
		myfavs = new myfavsCC();
		}catch(Exception e)
		{
			System.out.println("Failed to make myfavsCC.");
		}
		// Get the size of the screen
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    // Determine the new location of the window
	    int w = getSize().width;
	    int h = getSize().height;
	    int x = (dim.width-w)/2;
	    int y = (dim.height-h)/2;
	    // Move the window
	    setLocation(x, y);
	    
	    table.setPreferredScrollableViewportSize(new Dimension(500, 70));
	    table.setFillsViewportHeight(true);
	    
	    scrollPane.setBounds(200,220,600,300); //200 from top, 600 long, 300 tall  
	    
	    Font font = new Font("Trebuchet MS", Font.BOLD, 14);
	    Color color = new Color(0xA6,0x28,0x11);//red
	    Color color1 = new Color(0x00,0x33,0x99);//blue
	    Color color2 = new Color(0x95, 0x29, 0x0d);//orange
	    
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
	    
	    displayUser.setBounds(590,40,90,20);
	    displayUser.setFont(font);
	    displayUser.setForeground(color1);
	    displayUser.setBackground(getContentPane().getBackground());
	    
	    register.setBounds(660,40,100,20);
	    register.setFont(font);
	    register.setForeground(color1);
	    register.setBackground(getContentPane().getBackground());
	    register.setBorderPainted(false);
	    register.addActionListener(this);
	    register.setContentAreaFilled(false);
	    getContentPane().add(register);
	    
	    logout.setBounds(660,40,100,20);
	    logout.setFont(font);
	    logout.setForeground(color1);
	    logout.setBackground(getContentPane().getBackground());
	    logout.setBorderPainted(false);
	    logout.addActionListener(this);
	    logout.setContentAreaFilled(false);
	    
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
	    searchField.setBounds(200,130,480,20);
	    getContentPane().add(searchField);
	    
	    search.setForeground(Color.white);
        search.setBackground(color2);
	    search.setBounds(700,130,100,20);
	    search.addActionListener(this);
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
	    loginsubmit.addActionListener(this);
	    
	    registersubmit.setBounds(420,440,90,20);
	    registersubmit.addActionListener(this);
	    
	    addsongbottom.setBounds(200,530,220,20);
        addsongbottom.addActionListener(this);
        
        addsongtop.setBounds(200,190,220,20);
        addsongtop.addActionListener(this);
        
        addSong.setBounds(390,600,220,30);
        addSong.addActionListener(this);
        
        pickfrom.setBounds(320,300,120,20);
        list.setBounds(450,300,120,20);
        or.setBounds(450,340,50,20);
        
        createArtist.setBounds(330,380,120,20);
        createAlbum.setBounds(330,380,120,20);
        createTrack.setBounds(330,380,120,20);
        createSong.setBounds(330,300,120,20);
        
        songInputText.setBounds(450,300,120,20);
        inputText.setBounds(450,380,120,20);
        
        nextButton.setBounds(410,450,90,20);
        nextButton.addActionListener(this);
        
        nextButton2.setBounds(410,450,90,20);
        nextButton2.addActionListener(this);
        
        finalizeButton.setBounds(410,450,90,20);
        finalizeButton.addActionListener(this);
        
        group.add(artists);
        group.add(songs);
        group.add(members);
        group.add(playlists);
        group.add(albums);
    	
	    repaint();
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton button = (JButton) arg0.getSource();
		clearDisplay();
		if(button == login)
		{
			getContentPane().add(user);
			getContentPane().add(username);
			getContentPane().add(pass);
			getContentPane().add(password);
			getContentPane().add(loginsubmit);
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
		}
		if(button == search)
		{
			table.getColumnModel().getColumn(0).setPreferredWidth(10);
		    table.getColumnModel().getColumn(1).setPreferredWidth(30);
		    table.getColumnModel().getColumn(2).setPreferredWidth(1000);
		    table.getColumnModel().getColumn(3).setPreferredWidth(1000);
		    table.getColumnModel().getColumn(4).setPreferredWidth(1000);
		    table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
			getContentPane().add(scrollPane);
			getContentPane().add(addsongtop);
			getContentPane().add(addsongbottom);
			songlist.clear();
			albumlist.clear();
			artistlist.clear();
			playlistlist.clear();
			((MyTableModel)table.getModel()).removeAllRows();
			String query = searchField.getText();
			try {
				songlist.addAll(myfavs.readSongs());
				albumlist.addAll(myfavs.readAlbums());
				artistlist.addAll(myfavs.readArtists());
				//playlistlist.addAll(myfavs.readPlaylists());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(members.isSelected())
			{
				getContentPane().remove(scrollPane);
				//JTable memberTable = new JTable(myfavs.readMembers());
				//memberTable.add(comp)
			}
			else if(playlists.isSelected())
			{
				getContentPane().remove(scrollPane);
			}
			if(artists.isSelected())
			{
				for(int i = 0; i < songlist.size(); i++)
				{
					if(!query.equalsIgnoreCase(songlist.get(i).artist_name))
					{
						songlist.remove(i);
						i--;
					}
				}
			}
			if(songs.isSelected())
			{
				for(int i = 0; i < songlist.size(); i++)
				{
					if(!query.equalsIgnoreCase(songlist.get(i).name))
					{
						songlist.remove(i);
						i--;
					}
				}
			}
			if(albums.isSelected())
			{
				for(int i = 0; i < songlist.size(); i++)
				{
					if(!query.equalsIgnoreCase(songlist.get(i).album_name))
					{
						songlist.remove(i);
						i--;
					}
				}
			}
			for(int i = 0;i<songlist.size();i++)
			{
				booleanValues.add(new JCheckBox());
			}
		}
		if(button == loginsubmit)
		{
			//put code here to login to server
			try {
				if(myfavs.login(username.getText(), password.getText()))
				{
					getContentPane().remove(login);
					getContentPane().remove(register);
					getContentPane().add(displayUser);
					displayUser.setText(username.getText());
					getContentPane().add(logout);
					getContentPane().add(addSong);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Invalid username/password");
					getContentPane().add(user);
					getContentPane().add(username);
					getContentPane().add(pass);
					getContentPane().add(password);
					getContentPane().add(loginsubmit);
					password.setText("");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(button == registersubmit)
		{
			//put code here to register to server
			if(password.getText().equals(confirmPassword.getText()))
			{
				try {
					if(myfavs.createUser(username.getText(), password.getText()))
					{
						JOptionPane.showMessageDialog(null,"Welcome "+username.getText()+", you will shortly be redirected to the home page.");
						getContentPane().remove(login);
						getContentPane().remove(register);
						getContentPane().add(displayUser);
						displayUser.setText(username.getText());
						getContentPane().add(logout);
						getContentPane().add(addSong);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Not a valid username/password.");
						getContentPane().add(user);
						getContentPane().add(username);
						getContentPane().add(pass);
						getContentPane().add(password);
						getContentPane().add(confirm);
						getContentPane().add(confirmPassword);
						getContentPane().add(registersubmit);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Passwords need to match.");
				getContentPane().add(user);
				getContentPane().add(username);
				getContentPane().add(pass);
				getContentPane().add(password);
				getContentPane().add(confirm);
				getContentPane().add(confirmPassword);
				getContentPane().add(registersubmit);
				password.setText("");
				confirmPassword.setText("");
			}
		}
		if(button == addsongtop || button == addsongbottom)
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for(int i = 0;i<songlist.size();i++)
			{
				if(booleanValues.get(i).isSelected())
				{
					temp.add(songlist.get(i).song_id);
				}
			}
			int [] strArray = new int[temp.size()];
			for(int i = 0;i<temp.size();i++)
				strArray[i] = temp.get(i);
			String playlist_name = JOptionPane.showInputDialog(null,"Enter the name of this playlist.");
			try {
				myfavs.createPlaylist(playlist_name, strArray);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(button == logout)
		{
			try {
				myfavs.logout();
				getContentPane().add(login);
				getContentPane().add(register);
				getContentPane().remove(displayUser);
				getContentPane().remove(logout);
				getContentPane().remove(addSong);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(button == addSong)
		{
			pickfrom.setText("Pick from albums:");
			songlist.clear();
			list.removeAllItems();
			try {
				songlist.addAll(myfavs.readSongs());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i = 0; i < songlist.size();i++)
			{
				list.addItem(songlist.get(i).album_name);
			}
			getContentPane().add(pickfrom);
			getContentPane().add(list);
			getContentPane().add(or);
			getContentPane().add(createArtist);
			getContentPane().add(inputText);
			getContentPane().add(nextButton);
		}
		if(button == nextButton)
		{
			if(!inputText.getText().equals(""))
			{
				try {
					artist_id = myfavs.createArtist(inputText.getText()).artist_id;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				artist_id = songlist.get(list.getSelectedIndex()).artist_id;
			}
			inputText.setText("");
			pickfrom.setText("Pick from artists:");
			list.removeAllItems();
			for(int i = 0; i < songlist.size();i++)
			{
				list.addItem(songlist.get(i).artist_name);
			}
			
			getContentPane().add(pickfrom);
			getContentPane().add(list);
			getContentPane().add(or);
			getContentPane().add(createAlbum);
			getContentPane().add(inputText);
			getContentPane().add(nextButton2);
		}
		if(button == nextButton2)
		{
			if(!inputText.getText().equals(""))
			{
				try {
					album_id = myfavs.createAlbum(inputText.getText(), artist_id).album_id;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				album_id = songlist.get(list.getSelectedIndex()).album_id;
			}
			inputText.setText("");
			getContentPane().add(songInputText);
			getContentPane().add(createTrack);
			getContentPane().add(createSong);
			getContentPane().add(inputText);
			getContentPane().add(finalizeButton);
		}
		if(button == finalizeButton)
		{
			try {
				songlist.add(myfavs.createSong(songInputText.getText(), album_id, artist_id, Integer.parseInt(inputText.getText())));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		repaint();
	}
	public void clearDisplay()
	{
		getContentPane().remove(user);
		getContentPane().remove(username);
		getContentPane().remove(pass);
		getContentPane().remove(password);
		getContentPane().remove(loginsubmit);
		getContentPane().remove(confirm);
		getContentPane().remove(confirmPassword);
		getContentPane().remove(registersubmit);
		getContentPane().remove(scrollPane);
		getContentPane().remove(addsongtop);
		getContentPane().remove(addsongbottom);
		getContentPane().remove(pickfrom);
		getContentPane().remove(list);
		getContentPane().remove(or);
		getContentPane().remove(createArtist);
		getContentPane().remove(createAlbum);
		getContentPane().remove(createSong);
		getContentPane().remove(createTrack);
		getContentPane().remove(nextButton);
		getContentPane().remove(nextButton2);
		getContentPane().remove(finalizeButton);
		getContentPane().remove(inputText);
		getContentPane().remove(songInputText);
	}
	class MyItemListener implements ItemListener  
	{  
		@Override
		public void itemStateChanged(ItemEvent e) {  
			Object source = e.getSource();  
			if (source instanceof AbstractButton == false) return;  
			boolean checked = e.getStateChange() == ItemEvent.SELECTED;  
			for(int x = 0, y = table.getRowCount(); x < y; x++)  
			{  
				table.setValueAt(new Boolean(checked),x,0);  
			}  
	    }
	}
	class MyTableModel extends AbstractTableModel {
		Object [] columnNames = {"","#","Song","Artist","Album"};

        public int getColumnCount() {
            return columnNames.length;
        }

		public int getRowCount() {
            return songlist.size();
        }

        public String getColumnName(int col) {
            return (String) columnNames[col];
        }

        public Object getValueAt(int row, int col) {
        	switch(col){
    		case 0:
    			return (Object) booleanValues.get(row).isSelected();
    		case 1:
    			return (Object) songlist.get(row).track_number;
    		case 2:
    			return (Object) songlist.get(row).name;
    		case 3:
    			return (Object) songlist.get(row).artist_name;
    		case 4:
    			return (Object) songlist.get(row).album_name;
    		default:
    			return null;
        	}
        }
        
        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col == 0) {
                return true;
            } else {
                return false;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
        	switch(col){
        		case 0:
        			booleanValues.get(row).setSelected((Boolean) value);
        			break;
        		case 1:
        			songlist.get(row).track_number = (Integer) value;
        			break;
        		case 2:
        			songlist.get(row).name = (String) value;
        			break;
        		case 3:
        			songlist.get(row).artist_name = (String) value;
        			break;
        		case 4:
        			songlist.get(row).album_name = (String) value;
        			break;
        	}
            fireTableCellUpdated(row, col);
        }
        
        public void removeAllRows()
        {
          for(int i=getRowCount();i>0;i--)
            removeRow(i-1);
        }

        public void removeRow(int row)
        {
            songlist.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }

	
	class CheckBoxHeader extends JCheckBox  implements TableCellRenderer, MouseListener { 
		protected CheckBoxHeader rendererComponent;  
		protected int column;  
		protected boolean mousePressed = false;  
		public CheckBoxHeader(ItemListener itemListener) {  
			rendererComponent = this;  
			rendererComponent.addItemListener(itemListener);  
		}  
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{  
			if (table != null)
			{  
				JTableHeader header = table.getTableHeader();  
				if (header != null)
				{  
					rendererComponent.setForeground(header.getForeground());  
					rendererComponent.setBackground(header.getBackground());  
					rendererComponent.setFont(header.getFont());  
					header.addMouseListener(rendererComponent);  
				}  
			}  
			setColumn(column);
			setBorder(UIManager.getBorder("TableHeader.cellBorder"));  
			return rendererComponent;  
		}  
		protected void setColumn(int column) {  
			this.column = column;  
		}  
		public int getColumn() {  
			return column;  
		}  
		protected void handleClickEvent(MouseEvent e) {  
			if (mousePressed) {  
				mousePressed=false;  
				JTableHeader header = (JTableHeader)(e.getSource());  
				JTable tableView = header.getTable();  
				TableColumnModel columnModel = tableView.getColumnModel();  
				int viewColumn = columnModel.getColumnIndexAtX(e.getX());  
				int column = tableView.convertColumnIndexToModel(viewColumn);  
				if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {  
					doClick();  
				}  
			}  
		}  
		@Override
		public void mouseClicked(MouseEvent e) {  
			handleClickEvent(e);  
			((JTableHeader)e.getSource()).repaint();  
		}  
		@Override
		public void mousePressed(MouseEvent e) {  
			mousePressed = true;  
		}  
		@Override
		public void mouseReleased(MouseEvent e) {  
		} 
		@Override
		public void mouseEntered(MouseEvent e) {  
		}  
		@Override
		public void mouseExited(MouseEvent e) {  
		}
	} 
}