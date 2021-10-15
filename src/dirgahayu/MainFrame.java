package dirgahayu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu.Separator;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;

import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import keuangan.DlgBilingRalan;
import keuangan.DlgBilingRanap;
import permintaan.DlgCariPermintaanLab;
import permintaan.DlgCariPermintaanLabPA;
import permintaan.DlgCariPermintaanRadiologi;
import rekammedis.RMSKriningRawatJalan;
import setting.WindowInputPassword;
import usu.widget.glass.PanelGlass;
import widget.Button;
import widget.ButtonBig;
import widget.CekBox;
import widget.ComboBox;
import widget.InternalFrame;
import widget.Label;
import widget.MenuBar;
import widget.PasswordBox;
import widget.ScrollPane;
import widget.Tanggal;
import widget.TextBox;
import widget.panelGlass;
import widget.panelisi;

public class MainFrame extends JFrame {

	private static MainFrame myInstance;
	private final Connection koneksi = koneksiDB.condb();
	private final sekuel Sequel = new sekuel();
	private final validasi Valid = new validasi();
	private PreparedStatement ps;
	private ResultSet rs;
	private final Properties prop = new Properties();
	private JLabel jLabel8;
	private JLabel jLabel11;
	private PanelGlass PanelWall;
	private JPanel PanelUtama;
	private ScrollPane scrollPane1;
	private PanelGlass panelJudul;
	private InternalFrame internalFrameMenu;
	private ButtonBig BtnLog;
	private ButtonBig BtnClose;
	private JDialog DlgLogin;
	private InternalFrame internalFrame2;
	private InternalFrame internalFrame3;
	private PanelGlass panelGlass1;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private PasswordBox edAdmin;
	private PasswordBox edPwd;
	private JLabel jLabel6;
	private Button BtnLogin;
	private Button BtnCancel;
	private MenuBar MenuBar;
	private JMenu jMenu1;
	private JMenuItem MnLogin;
	private JMenuItem MnGantiPassword;
	private Separator jSeparator14;
	private JMenuItem MenuKeluar;
	private JLabel lblStts;
	private JSeparator jSeparator1;
	private JLabel lblUser;
	private JSeparator jSeparator2;
	private JLabel lblTgl;
	private JSeparator jSeparator3;
	private JLabel LblIP;
	private JSeparator jSeparator6;
	private JLabel jLabel7;
	private InternalFrame internalFrame4;
	private String coder_nik = "", pilihpage = "", judulform = "",
			tampilkantni = Sequel.cariIsi("select tampilkan_tni_polri from set_tni_polri"),
			AKTIFKANTRACKSQL = koneksiDB.AKTIFKANTRACKSQL();
	private Tanggal tanggal;
	private PanelGlass FlayMenu;
	private JDialog DlgHome;
	private ComboBox cmbMenu;
	private TextBox TCari;
	private CekBox ChkInput;
	private ScrollPane scrollPane2;
	private panelGlass Panelmenu;
	private panelisi panelisi2;
	private Label label36;
	private Button button1;
	private Label label35;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		super();
		initCommponent();
		setResizable(false);
		setIconImage(new ImageIcon(super.getClass().getResource("/picture/RSD.png")).getImage());
		this.setSize(screen.width, screen.height);

		edAdmin.setDocument(new batasInput((byte) 100).getKata(edAdmin));
		edPwd.setDocument(new batasInput((byte) 100).getKata(edPwd));
		DlgLogin.setSize(299, 180);
		DlgLogin.setVisible(false);
		DlgLogin.setLocationRelativeTo(null);

		lblTgl.setText(tanggal.getSelectedItem().toString());
		try {
			prop.loadFromXML(new FileInputStream("setting/database.xml"));
		} catch (Exception e) {
			System.out.println("Notif Setting : " + e);
		}

		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			LblIP.setText("" + inetAddress.getHostAddress());
		} catch (Exception e) {
			System.out.println("Notif IP : " + e);
		}
		
		addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
			public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            @Override
			public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
			public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            @Override
			public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

	}

	protected void formWindowOpened(WindowEvent evt) {
		// TODO Auto-generated method stub
		 setToolbar();
	}

	protected void formWindowClosed(WindowEvent evt) {
		// TODO Auto-generated method stub
		 DlgHome.dispose();
	        System.exit(0);
	}

	protected void formWindowStateChanged(WindowEvent evt) {
		// TODO Auto-generated method stub
//		if(this.getState()==1){
//	        isTutup();
//	    }
	}

	protected void formComponentMoved(ComponentEvent evt) {
		// TODO Auto-generated method stub
//		 Window[] wins = Window.getWindows();
//	        for (Window win : wins) {
//	            if (win instanceof JDialog) {
//	                win.setLocationRelativeTo(PanelUtama);
//	                win.toFront();
//	            }
//	        }	        
//	        setToolbar();
	}

	private void initCommponent() {
		// TODO Auto-generated method stub
		tanggal = new widget.Tanggal();

//		 IsWall
		jLabel8 = new javax.swing.JLabel();
		jLabel8.setBounds(0, 0, 1350, 148);
		jLabel11 = new javax.swing.JLabel();
		jLabel11.setBounds(96, 118, 717, 30);
		jLabel11.setAlignmentX(Component.CENTER_ALIGNMENT);
		PanelWall = new usu.widget.glass.PanelGlass();
		PanelWall.setOpaqueGradient(true);
//		 end Wall
//		Pane Login
		DlgLogin = new javax.swing.JDialog();
		internalFrame2 = new widget.InternalFrame();
		internalFrame3 = new widget.InternalFrame();
		panelGlass1 = new usu.widget.glass.PanelGlass();
		jLabel4 = new JLabel();
		jLabel5 = new JLabel();
		edAdmin = new widget.PasswordBox();
		edPwd = new widget.PasswordBox();
		jLabel6 = new JLabel();
		BtnLogin = new widget.Button();
		BtnCancel = new widget.Button();

		DlgLogin.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		DlgLogin.setName("DlgLogin"); // NOI18N
		DlgLogin.setUndecorated(true);
		DlgLogin.setResizable(false);

		internalFrame2.setBorder(null);
		internalFrame2.setName("internalFrame2"); // NOI18N
		internalFrame2.setWarnaAtas(new java.awt.Color(249, 0, 124));
		internalFrame2.setWarnaBawah(new java.awt.Color(232, 0, 107));
		internalFrame2.setLayout(null);

		internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(
				new javax.swing.border.LineBorder(new java.awt.Color(100, 125, 90), 1, true),
				":: Silahkan Anda Login ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 11),
				new java.awt.Color(50, 50, 50))); // NOI18N
		internalFrame3.setName("internalFrame3"); // NOI18N
		internalFrame3.setRequestFocusEnabled(false);
		internalFrame3.setWarnaAtas(new java.awt.Color(255, 206, 205));
		internalFrame3.setWarnaBawah(new java.awt.Color(255, 244, 243));
		internalFrame3.setLayout(null);

		panelGlass1.setBackground(java.awt.Color.red);
		panelGlass1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(185, 185, 110)));
		panelGlass1.setOpaqueImage(false);
		panelGlass1.setRound(false);
		panelGlass1.setWarna(new java.awt.Color(255, 255, 190));
		panelGlass1.setLayout(null);

		jLabel4.setForeground(new java.awt.Color(50, 50, 50));
		jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel4.setText("ID Admin :");
		jLabel4.setName("jLabel4"); // NOI18N
		panelGlass1.add(jLabel4);
		jLabel4.setBounds(2, 12, 75, 23);

		jLabel5.setForeground(new java.awt.Color(50, 50, 50));
		jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel5.setText("Password :");
		jLabel5.setName("jLabel5"); // NOI18N
		panelGlass1.add(jLabel5);
		jLabel5.setBounds(2, 40, 75, 23);

		edAdmin.setForeground(new java.awt.Color(50, 50, 50));
		edAdmin.setToolTipText("Silahkan masukkan ID Admin");
		edAdmin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		edAdmin.setName("edAdmin"); // NOI18N
		edAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				edAdminKeyPressed(evt);
			}
		});
		panelGlass1.add(edAdmin);
		edAdmin.setBounds(80, 12, 193, 23);

		edPwd.setForeground(new java.awt.Color(50, 50, 50));
		edPwd.setToolTipText("Silahkan masukkan password");
		edPwd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		edPwd.setName("edPwd"); // NOI18N
		edPwd.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				edPwdKeyPressed(evt);
			}
		});
		panelGlass1.add(edPwd);
		edPwd.setBounds(80, 40, 193, 23);

		internalFrame3.add(panelGlass1);
		panelGlass1.setBounds(-1, 25, 342, 76);

		jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/LaST (Cobalt) Lock n Gear.png"))); // NOI18N
		jLabel6.setName("jLabel6"); // NOI18N
		internalFrame3.add(jLabel6);
		jLabel6.setBounds(105, 5, 135, 145);

		BtnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/lock.png"))); // NOI18N
		BtnLogin.setMnemonic('Z');
		BtnLogin.setText("Log-in");
		BtnLogin.setToolTipText("Alt+Z");
		BtnLogin.setGlassColor(new java.awt.Color(235, 255, 245));
		BtnLogin.setName("BtnLogin"); // NOI18N
		BtnLogin.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnLoginActionPerformed(evt);
			}
		});
		internalFrame3.add(BtnLogin);
		BtnLogin.setBounds(12, 110, 105, 32);

		BtnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
		BtnCancel.setMnemonic('Y');
		BtnCancel.setText("Batal");
		BtnCancel.setToolTipText("Alt+Y");
		BtnCancel.setGlassColor(new java.awt.Color(235, 255, 245));
		BtnCancel.setName("BtnCancel"); // NOI18N
		BtnCancel.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnCancelActionPerformed(evt);
			}
		});
		internalFrame3.add(BtnCancel);
		BtnCancel.setBounds(177, 110, 105, 32);

		internalFrame2.add(internalFrame3);
		internalFrame3.setBounds(2, 12, 295, 155);

		DlgLogin.getContentPane().add(internalFrame2, java.awt.BorderLayout.CENTER);
//		endlogin

		PanelUtama = new javax.swing.JPanel();
		scrollPane1 = new widget.ScrollPane();
		panelJudul = new usu.widget.glass.PanelGlass();
		internalFrameMenu = new widget.InternalFrame();
		BtnLog = new widget.ButtonBig();
		BtnClose = new widget.ButtonBig();
		panelJudul.setOpaqueImage(false);

//		bootom
		internalFrame4 = new widget.InternalFrame();
		lblStts = new javax.swing.JLabel();
		jSeparator1 = new javax.swing.JSeparator();
		lblUser = new javax.swing.JLabel();
		jSeparator2 = new javax.swing.JSeparator();
		lblTgl = new javax.swing.JLabel();
		jSeparator3 = new javax.swing.JSeparator();
		LblIP = new javax.swing.JLabel();
		jSeparator6 = new javax.swing.JSeparator();
		jLabel7 = new javax.swing.JLabel();

		internalFrame4.setBackground(new java.awt.Color(235, 215, 195));
		internalFrame4.setBorder(null);
		internalFrame4.setName("internalFrame4"); // NOI18N
		internalFrame4.setPreferredSize(new java.awt.Dimension(330, 25));
		internalFrame4.setWarnaAtas(new java.awt.Color(51, 255, 51));
		internalFrame4.setWarnaBawah(new java.awt.Color(153, 255, 102));
		internalFrame4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 1));

		lblStts.setForeground(new java.awt.Color(50, 50, 50));
		lblStts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblStts.setText("Status Admin :");
		lblStts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		lblStts.setName("lblStts"); // NOI18N
		lblStts.setPreferredSize(new java.awt.Dimension(100, 23));
		internalFrame4.add(lblStts);

		jSeparator1.setBackground(new java.awt.Color(235, 0, 107));
		jSeparator1.setForeground(new java.awt.Color(235, 0, 107));
		jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(90, 130, 80)));
		jSeparator1.setName("jSeparator1"); // NOI18N
		jSeparator1.setOpaque(true);
		jSeparator1.setPreferredSize(new java.awt.Dimension(1, 21));
		internalFrame4.add(jSeparator1);

		lblUser.setForeground(new java.awt.Color(50, 50, 50));
		lblUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblUser.setText("Log Out");
		lblUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		lblUser.setName("lblUser"); // NOI18N
		lblUser.setPreferredSize(new java.awt.Dimension(170, 23));
		internalFrame4.add(lblUser);

		jSeparator2.setBackground(new java.awt.Color(235, 0, 107));
		jSeparator2.setForeground(new java.awt.Color(235, 0, 107));
		jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(90, 130, 80)));
		jSeparator2.setName("jSeparator2"); // NOI18N
		jSeparator2.setOpaque(true);
		jSeparator2.setPreferredSize(new java.awt.Dimension(1, 21));
		internalFrame4.add(jSeparator2);

		lblTgl.setForeground(new java.awt.Color(50, 50, 50));
		lblTgl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblTgl.setText("Tanggal");
		lblTgl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		lblTgl.setName("lblTgl"); // NOI18N
		lblTgl.setPreferredSize(new java.awt.Dimension(100, 23));
		internalFrame4.add(lblTgl);

		jSeparator3.setBackground(new java.awt.Color(235, 0, 107));
		jSeparator3.setForeground(new java.awt.Color(235, 0, 107));
		jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(90, 130, 80)));
		jSeparator3.setName("jSeparator3"); // NOI18N
		jSeparator3.setOpaque(true);
		jSeparator3.setPreferredSize(new java.awt.Dimension(1, 21));
		internalFrame4.add(jSeparator3);

		LblIP.setForeground(new java.awt.Color(50, 50, 50));
		LblIP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		LblIP.setText("Alamat IP");
		LblIP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		LblIP.setName("LblIP"); // NOI18N
		LblIP.setPreferredSize(new java.awt.Dimension(120, 23));
		internalFrame4.add(LblIP);

		jSeparator6.setBackground(new java.awt.Color(235, 0, 107));
		jSeparator6.setForeground(new java.awt.Color(235, 0, 107));
		jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(90, 130, 80)));
		jSeparator6.setName("jSeparator6"); // NOI18N
		jSeparator6.setOpaque(true);
		jSeparator6.setPreferredSize(new java.awt.Dimension(1, 21));
		internalFrame4.add(jSeparator6);

		jLabel7.setForeground(new java.awt.Color(50, 50, 50));
		jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/file-edit-16x16.png"))); // NOI18N
		jLabel7.setText(" Didesain & dibuat oleh Khanza.Soft Media");
		jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		jLabel7.setIconTextGap(3);
		jLabel7.setName("jLabel7"); // NOI18N
		jLabel7.setPreferredSize(new java.awt.Dimension(287, 23));
		internalFrame4.add(jLabel7);

		getContentPane().add(internalFrame4, java.awt.BorderLayout.PAGE_END);
//        end bottom
		PanelUtama.setName("PanelUtama");
		PanelUtama.setOpaque(false);
		PanelUtama.setLayout(new java.awt.BorderLayout());
		scrollPane1.setBorder(null);
		scrollPane1.setName("scrollPane1");
		PanelWall.setBackground(new java.awt.Color(29, 29, 29));
		PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/wallpaper.jpg")));
		PanelWall.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
		PanelWall.setPreferredSize(new Dimension(170, 150));
		PanelWall.setRound(false);
		PanelWall.setWarna(new java.awt.Color(110, 110, 110));
		PanelWall.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			@Override
			public void mouseMoved(java.awt.event.MouseEvent evt) {
				PanelWallMouseMoved(evt);
			}
		});
		PanelWall.setLayout(new java.awt.BorderLayout());
		panelJudul.setBackground(new Color(0, 0, 0));
		panelJudul.setPreferredSize(new java.awt.Dimension(200, 170));
		panelJudul.setRound(false);
		panelJudul.setLayout(null);

		jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		jLabel11.setForeground(new java.awt.Color(50, 50, 50));
		jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		jLabel11.setText("Your Businis Solution");
		jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jLabel11.setName("jLabel11"); // NOI18N
		jLabel11.setPreferredSize(new java.awt.Dimension(430, 30));
		panelJudul.add(jLabel11);

		jLabel8.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
		jLabel8.setForeground(new java.awt.Color(50, 50, 50));
		jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/RSD.png"))); // NOI18N
		// jLabel8.setText("RS Dirgahayu, Hospital Management System");
		jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		jLabel8.setName("jLabel8"); // NOI18N
		panelJudul.add(jLabel8);
		jLabel8.getAccessibleContext().setAccessibleName("RS Dirgahayu, Hospital Management System");

		PanelWall.add(panelJudul, BorderLayout.SOUTH);
//		FlyMenu
		FlayMenu = new usu.widget.glass.PanelGlass();
		FlayMenu.setBackground(new java.awt.Color(255, 255, 255));
		FlayMenu.setOpaqueImage(false);
		FlayMenu.setPreferredSize(new java.awt.Dimension(200, 110));
		FlayMenu.setRound(false);
		FlayMenu.setLayout(new java.awt.GridLayout(1, 0, 4, 5));
		FlayMenu.setVisible(false);
		PanelWall.add(FlayMenu, java.awt.BorderLayout.PAGE_START);

		scrollPane1.setViewportView(PanelWall);

		PanelUtama.add(scrollPane1, java.awt.BorderLayout.CENTER);

		getContentPane().add(PanelUtama, java.awt.BorderLayout.CENTER);
//		start midle menu
		internalFrameMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		internalFrameMenu.setName("internalFrameMenu"); // NOI18N
		internalFrameMenu.setPreferredSize(new java.awt.Dimension(40, 42));
		internalFrameMenu.setVerifyInputWhenFocusTarget(false);
		internalFrameMenu.setWarnaAtas(javax.swing.UIManager.getDefaults().getColor("Button.light"));
		internalFrameMenu.setWarnaBawah(javax.swing.UIManager.getDefaults().getColor("Button.light"));

//		Dynamic Menu

		BtnLog.setBackground(new java.awt.Color(255, 255, 255));
		BtnLog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/login2.png"))); // NOI18N
		BtnLog.setMnemonic('L');
		BtnLog.setText("Log In");
		BtnLog.setToolTipText("Alt+L");
		BtnLog.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnLog.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		BtnLog.setIconTextGap(2);
		BtnLog.setMargin(new java.awt.Insets(0, 0, 0, 0));
		BtnLog.setName("BtnLog"); // NOI18N
		BtnLog.setPreferredSize(new java.awt.Dimension(79, 38));
		BtnLog.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
		BtnLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnLog.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnLogActionPerformed(evt);
			}
		});
		internalFrameMenu.setLayout(new BoxLayout(internalFrameMenu, BoxLayout.X_AXIS));

		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBorder(new EmptyBorder(0, 0, 0, 0));
		menuBar.setBackground(SystemColor.controlLtHighlight);
		internalFrameMenu.add(menuBar);
		menuBar.setVisible(false);

		defaultBar = new JMenuBar();
		defaultBar.setBorderPainted(false);
		defaultBar.setBackground(Color.WHITE);
		internalFrameMenu.add(defaultBar);

		jMenu4 = new JMenu();
		jMenu4.setToolTipText("Alt+G");
		jMenu4.setText("Tentang Program");
		jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/63.png")));
		jMenu4.setName("jMenu4");
		jMenu4.setMnemonic('G');
		jMenu4.setHorizontalAlignment(SwingConstants.LEFT);
		jMenu4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jMenu4.setBorder(null);
		defaultBar.add(jMenu4);

		jMenu4.addMenuListener(new javax.swing.event.MenuListener() {
			@Override
			public void menuCanceled(javax.swing.event.MenuEvent evt) {
			}

			@Override
			public void menuDeselected(javax.swing.event.MenuEvent evt) {
			}

			@Override
			public void menuSelected(javax.swing.event.MenuEvent evt) {
				jMenu4MenuSelected(evt);
			}
		});
		jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jMenu4MouseClicked(evt);
			}
		});
		internalFrameMenu.add(BtnLog);

		BtnClose.setBackground(new java.awt.Color(255, 255, 255));
		BtnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Delete.png"))); // NOI18N
		BtnClose.setMnemonic('U');
		BtnClose.setText("Keluar");
		BtnClose.setToolTipText("Alt+U");
		BtnClose.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnClose.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		BtnClose.setIconTextGap(2);
		BtnClose.setMargin(new java.awt.Insets(0, 0, 0, 0));
		BtnClose.setName("BtnClose"); // NOI18N
		BtnClose.setPreferredSize(new java.awt.Dimension(80, 38));
		BtnClose.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
		BtnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnClose.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnCloseActionPerformed(evt);
			}
		});
		internalFrameMenu.add(BtnClose);

		getContentPane().add(internalFrameMenu, BorderLayout.NORTH);

//		DlgHome
		DlgHome = new javax.swing.JDialog();
		DlgHome.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		DlgHome.setAlwaysOnTop(true);
		DlgHome.setBackground(new java.awt.Color(255, 253, 253));
		DlgHome.setIconImage(null);
		DlgHome.setName("DlgHome"); // NOI18N
		DlgHome.setUndecorated(true);
		DlgHome.setResizable(false);
		
		cmbMenu = new widget.ComboBox();
        TCari = new widget.TextBox();
        ChkInput = new widget.CekBox();
        scrollPane2 = new widget.ScrollPane();
        Panelmenu = new widget.panelGlass();
        InternalFrame panelMenu = new widget.InternalFrame();
        label36 = new widget.Label();
        button1 = new widget.Button();
        label35 = new widget.Label();
        
        panelMenu.setBackground(new java.awt.Color(255, 253, 253));
        panelMenu.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(223, 233, 213)), "::[ Menu Utama ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        panelMenu.setName("panelMenu"); // NOI18N
        panelMenu.setPreferredSize(new java.awt.Dimension(2412, 3653));
        panelMenu.setWarnaAtas(new java.awt.Color(255, 252, 252));
        panelMenu.setWarnaBawah(new java.awt.Color(255, 252, 252));
        panelMenu.setLayout(new java.awt.BorderLayout(1, 1));
        panelisi2 = new widget.panelisi();

        panelisi2.setBackground(new java.awt.Color(255, 253, 253));
        panelisi2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 247, 225)));
        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(100, 40));
        panelisi2.setWarnaAtas(new java.awt.Color(255, 252, 252));
        panelisi2.setWarnaBawah(new java.awt.Color(255, 252, 252));
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 7));

        label36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(1, 23));
        panelisi2.add(label36);

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        button1.setGlassColor(new java.awt.Color(255, 255, 255));
        button1.setMinimumSize(new java.awt.Dimension(28, 23));
        button1.setName("button1"); // NOI18N
        button1.setPreferredSize(new java.awt.Dimension(25, 23));
        button1.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        panelisi2.add(button1);

        label35.setText("Tampilkan Menu :");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(105, 23));
        panelisi2.add(label35);
        
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(470, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi2.add(TCari);
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setSelected(true);
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setOpaque(false);
        ChkInput.setPreferredSize(new java.awt.Dimension(25, 23));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        panelisi2.add(ChkInput);

        panelMenu.add(panelisi2, java.awt.BorderLayout.PAGE_START);

        scrollPane2.setBackground(new java.awt.Color(255, 252, 252));
        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 247, 225)));
        scrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.setName("scrollPane2"); // NOI18N

        Panelmenu.setBackground(new java.awt.Color(255, 252, 252));
        Panelmenu.setBorder(null);
        Panelmenu.setMinimumSize(new java.awt.Dimension(1975, 2826));
        Panelmenu.setName("Panelmenu"); // NOI18N
        Panelmenu.setLayout(new java.awt.GridLayout(0, 12));
        scrollPane2.setViewportView(Panelmenu);
        panelMenu.add(scrollPane2, java.awt.BorderLayout.CENTER);
        DlgHome.getContentPane().add(panelMenu, java.awt.BorderLayout.CENTER);

	}

	protected void ChkInputActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}

	

	protected void button1ActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		DlgHome.dispose();
		this.setCursor(Cursor.getDefaultCursor());
	}

	protected void jMenu4MouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		DlgAbout About = new DlgAbout(this, true);
		About.setSize(PanelWall.getWidth(), PanelWall.getHeight());
		About.setLocationRelativeTo(PanelWall);
		About.setVisible(true);
	}

	protected void jMenu4MenuSelected(MenuEvent evt) {
		// TODO Auto-generated method stub
		DlgAbout About = new DlgAbout(this, true);
		About.setSize(PanelWall.getWidth(), PanelWall.getHeight());
		About.setLocationRelativeTo(PanelWall);
		About.setVisible(true);
	}

	protected void MnGantiPasswordBtnLogActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// isTutup();
		WindowInputPassword barcode = new WindowInputPassword(this, true);
		barcode.SetUserPass(lblUser.getText(), edPwd.getText());
		barcode.setLocationRelativeTo(PanelUtama);
		barcode.setVisible(true);
		// DlgHome.dispose();
		this.setCursor(Cursor.getDefaultCursor());
	}

	public JMenu[] menus;
	public JMenuItem[] menuItem;
	public Integer idMenu = 0;

	public int checkChild(Integer parent) {
		int count = 0;
		try {
			rs = koneksi.prepareStatement("Select count(idmenu) from menu where parent='" + parent + "'")
					.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;

	}

	Integer WidthButtonDynamin = 0;

	public void DynamicMenu(String parent, String pref) {
		profileMenu = new JMenu("Profile");
		profileMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		profileMenu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		profileMenu.setBorder(new EmptyBorder(0, 0, 0, 0));
		profileMenu.setBackground(SystemColor.text);
		profileMenu.setIcon(new ImageIcon(getClass().getResource("/picture/loginorg.png")));
		menuBar.add(profileMenu);
		MnGantiPassword = new JMenuItem("Ubah Password");
		MnGantiPassword.setBackground(UIManager.getColor("Button.highlight"));
		MnGantiPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		MnGantiPassword.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				MnGantiPasswordBtnLogActionPerformed(evt);
			}
		});
		profileMenu.add(MnGantiPassword);
		String queryCount;
		String query;
		if (!pref.equals("admin")) {
			queryCount = "Select count(menu.idmenu) from menu INNER join usermenu on usermenu.idmenu=menu.idmenu Inner join pegawai on pegawai.id=usermenu.idUser where parent='"
					+ parent + "' and nik='" + akses.getkode() + "'";
			query = "Select menu.* from menu INNER join usermenu on usermenu.idmenu=menu.idmenu Inner join pegawai on pegawai.id=usermenu.idUser "
					+ " where parent='" + parent + "' and nik='" + akses.getkode() + "' order by nm_menu DESC";
		} else {
			queryCount = "Select count(menu.idmenu) from menu where parent='" + parent + "'";
			query = "Select * from menu where parent='" + parent + "' order by nm_menu DESC";
		}
		Integer countRow = (int) Sequel.cariIsiAngka(queryCount);
		Integer i = 0;
		menus = new JMenu[countRow];
		try {
			ResultSet rs;
			rs = koneksi.prepareStatement(query).executeQuery();
			while (rs.next()) {
				idMenu = rs.getInt("idmenu");
				String nmMenu = rs.getString("nm_menu");
//				iCons
				String icons = rs.getString("icons");
				if (icons == null) {
					icons = "/48x48/checklist.png";
				}
				ImageIcon imageIcon = new ImageIcon(getClass().getResource(icons));
				Image image = imageIcon.getImage();
				Image newimg = image.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
				imageIcon = new ImageIcon(newimg);
//				end Icon
				String menuClass = rs.getString("menuClass");
				if (checkChild(idMenu) != 0) {
					menus[i] = new JMenu(nmMenu);
					menus[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					menus[i].setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
					menus[i].setBorder(new EmptyBorder(0, 0, 0, 0));
					menus[i].setBackground(SystemColor.text);
					menus[i].setIcon(imageIcon);
					menus[i].setName(nmMenu);
					sMenuItem(idMenu, i, checkChild(idMenu), pref);
					menuBar.add(menus[i]);
					WidthButtonDynamin += menus[i].getWidth();
				} else {
					menus[i] = new JMenu(nmMenu);
					menus[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					menus[i].setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
					menus[i].setBorder(new EmptyBorder(0, 0, 0, 0));
					menus[i].setBackground(SystemColor.text);
					menus[i].setIcon(imageIcon);
					menus[i].addMouseListener(new java.awt.event.MouseAdapter() {
						@Override
						public void mouseClicked(java.awt.event.MouseEvent evt) {
							btnDynamicMenuActionPerformed(evt, menuClass);
						}
					});
					menuBar.add(menus[i]);
					WidthButtonDynamin += menus[i].getWidth();
				}
				i++;
			}
			// internalFrameMenu.add(menuBar);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sMenuItem(Integer parent, Integer index, Integer RowMenu, String pref) {
		Integer j = 0;
		menuItem = new JMenuItem[RowMenu];
		String query;
		if (!pref.equals("admin")) {
			query = "Select menu.* from menu INNER join usermenu on usermenu.idmenu=menu.idmenu Inner join pegawai on pegawai.id=usermenu.idUser "
					+ " where parent='" + parent + "' and nik='" + akses.getkode() + "' order by nm_menu DESC";
		} else {
			query = "Select * from menu where parent='" + parent + "' order by nm_menu DESC";
		}
		try {
			ResultSet rs;
			rs = koneksi.prepareStatement(query).executeQuery();
			while (rs != null && rs.next()) {
				idMenu = rs.getInt("idmenu");
				String nmMenu = rs.getString("nm_menu");
//				iCons
				String icons = rs.getString("icons");
				if (icons == null) {
					icons = "/48x48/checklist.png";
				}
				ImageIcon imageIcon = new ImageIcon(getClass().getResource(icons));
				Image image = imageIcon.getImage();
				Image newimg = image.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
				imageIcon = new ImageIcon(newimg);
//				end Icon
				String menuClass = rs.getString("menuClass");
				if (checkChild(idMenu) != 0) {
					menus[j] = new JMenu(nmMenu);
					menus[j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					menus[j].setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
					menus[j].setBorder(new EmptyBorder(0, 0, 0, 0));
					menus[j].setBackground(SystemColor.text);
					menus[j].setIcon(imageIcon);
					menus[j].setName(nmMenu);
					sMenuItem(idMenu, j, checkChild(idMenu), pref);
					menus[index].add(menus[j]);
				} else {
					menuItem[j] = new JMenuItem(nmMenu);
					menuItem[j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					menuItem[j].setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
					menuItem[j].setBackground(SystemColor.text);
					menuItem[j].setIcon(imageIcon);
					menuItem[j].addActionListener(new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(java.awt.event.ActionEvent evt) {
							btnDynamicActionPerformed(evt, menuClass);
						}
					});
					menus[index].add(menuItem[j]);
				}

				j++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void btnDynamicMenuActionPerformed(MouseEvent me, String menuClass) {
		// TODO Auto-generated method stub
		// System.out.print(menuClass);
		paseClass(menuClass);
	}

	protected void btnDynamicActionPerformed(java.awt.event.ActionEvent evt, String menuClass) {
		// TODO Auto-generated method stub
		paseClass(menuClass);

	}

	private final DlgReg reg = new DlgReg(this, false);
	private final RMSKriningRawatJalan Skrining = new RMSKriningRawatJalan(this, false);
	private final DlgIGD Igd = new DlgIGD(this, false);
	private final DlgKasirRalan ralan = new DlgKasirRalan(this, false);
	private final DlgKamarInap ranap = new DlgKamarInap(this, false);
	private final DlgBilingRanap bilingRanap = new DlgBilingRanap(this, false);
	private final DlgBilingRalan bilingRalan = new DlgBilingRalan(this, false);
	private ButtonBig btnMenuTree;
	private ButtonBig btnMenuSetUser;

	public void paseClass(String menuClass) {
		switch (menuClass) {
		case "DlgReg":
			isTutup();
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			reg.emptTeks();
			reg.isCek();
			reg.setSize(PanelWall.getWidth(), PanelWall.getHeight());
			reg.setLocationRelativeTo(PanelWall);
			reg.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
			break;
		case "RMSKriningRawatJalan":
			isTutup();
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Skrining.emptTeks();
			Skrining.isCek();
			Skrining.setSize(PanelWall.getWidth(), PanelWall.getHeight());
			Skrining.setLocationRelativeTo(PanelWall);
			Skrining.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
			break;
		case "DlgIGD":
			isTutup();
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Igd.emptTeks();
			Igd.isCek();
			Igd.setSize(PanelWall.getWidth(), PanelWall.getHeight());
			Igd.setLocationRelativeTo(PanelWall);
			Igd.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
			break;
		case "DlgKasirRalan":
			isTutup();
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			ralan.isCek();
			ralan.setSize(PanelWall.getWidth(), PanelWall.getHeight());
			ralan.setLocationRelativeTo(PanelWall);
			ralan.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
			break;
		case "DlgKamarInap":
			isTutup();
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			ranap.isCek();
			ranap.setSize(PanelWall.getWidth(), PanelWall.getHeight());
			ranap.setLocationRelativeTo(PanelWall);
			ranap.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
			break;
		case "DlgCariPermintaanRadiologi":
			isTutup();
			DlgCariPermintaanRadiologi permintaanRadiologi = new DlgCariPermintaanRadiologi(this, false);
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			permintaanRadiologi.isCek();
			permintaanRadiologi.setSize(PanelWall.getWidth(), PanelWall.getHeight());
			permintaanRadiologi.setLocationRelativeTo(PanelWall);
			permintaanRadiologi.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
			break;
		case "DlgCariPeriksaRadiologi":
			isTutup();
			DlgCariPeriksaRadiologi periksaRadiologi = new DlgCariPeriksaRadiologi(this, false);
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			periksaRadiologi.isCek();
			periksaRadiologi.setSize(PanelWall.getWidth(), PanelWall.getHeight());
			periksaRadiologi.setLocationRelativeTo(PanelWall);
			periksaRadiologi.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
			break;
		case "DlgCariPermintaanLab":
			isTutup();
			DlgCariPermintaanLab permintaanLab = new DlgCariPermintaanLab(this, false);
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			permintaanLab.isCek();
			permintaanLab.setSize(PanelWall.getWidth(), PanelWall.getHeight());
			permintaanLab.setLocationRelativeTo(PanelWall);
			permintaanLab.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
			break;
		case "DlgCariPermintaanLabPA":
			isTutup();
			DlgCariPermintaanLabPA permintaanLabPa = new DlgCariPermintaanLabPA(this, false);
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			permintaanLabPa.isCek();
			permintaanLabPa.setSize(PanelWall.getWidth(), PanelWall.getHeight());
			permintaanLabPa.setLocationRelativeTo(PanelWall);
			permintaanLabPa.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
			break;
		case "DlgCariPeriksaLab":
			isTutup();
			DlgCariPeriksaLab periksaLab = new DlgCariPeriksaLab(this, false);
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			periksaLab.isCek();
			periksaLab.setSize(PanelWall.getWidth(), PanelWall.getHeight());
			periksaLab.setLocationRelativeTo(PanelWall);
			periksaLab.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
			break;
		case "DlgCariPeriksaLabPA":
			isTutup();
			DlgCariPeriksaLabPA periksaLabPa = new DlgCariPeriksaLabPA(this, false);
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			periksaLabPa.isCek();
			periksaLabPa.setSize(PanelWall.getWidth(), PanelWall.getHeight());
			periksaLabPa.setLocationRelativeTo(PanelWall);
			periksaLabPa.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
			break;
		case "DlgBilingRalan":
			isTutup();
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			bilingRalan.isCek();
			bilingRalan.setSize(PanelWall.getWidth(), PanelWall.getHeight());
			bilingRalan.setLocationRelativeTo(PanelWall);
			bilingRalan.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
			break;
		case "DlgBilingRanap":
			isTutup();
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			bilingRanap.isCek();
			bilingRanap.setSize(PanelWall.getWidth(), PanelWall.getHeight());
			bilingRanap.setLocationRelativeTo(PanelWall);
			bilingRanap.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
			break;
		case "menu":
			if (akses.getjml1() >= 1) {
				Panelmenu.removeAll();
				 this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
				isTutup();
				DlgHome.setSize(PanelUtama.getWidth()-30, PanelUtama.getHeight()-30);
			    DlgHome.setLocationRelativeTo(PanelUtama);
			    DlgHome.setVisible(true);			    
//				Icon\
				ImageIcon imageIcon = new ImageIcon(getClass().getResource("/48x48/data_management.png"));
				Image image = imageIcon.getImage();
				Image newimg = image.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
				imageIcon = new ImageIcon(newimg);
//				end Icon		    
				btnMenuSetUser = new widget.ButtonBig();
				btnMenuSetUser.setIcon(imageIcon);
				btnMenuSetUser.setText("Set Menu User");
				btnMenuSetUser.setIconTextGap(0);
				btnMenuSetUser.setName("btnMenuTree");
				btnMenuSetUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
				btnMenuSetUser.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnMenuSetUserActionPerform(evt);
					}

					private void btnMenuSetUserActionPerform(ActionEvent evt) {
						// TODO Auto-generated method stub

					}
				});
				Panelmenu.add(btnMenuSetUser);
				 if(menuawal==0){
					 isButtonMenu();
				     setLayout();
				     menuawal=1;
				    }else{
				    	isButtonMenu();
				    	setLayout();
				    }
				

			}else {
				JOptionPane.showMessageDialog(null, "Maaf, Anda Tidak Mempunyai Hak Akes");
			}
			this.setCursor(Cursor.getDefaultCursor());
			break;
		}
	}
	private void setLayout() {
        if(jmlmenu<=1){
            grid=1;
        }else if(jmlmenu<=4){
            grid=2;
        }else if(jmlmenu<=9){
            grid=3;
        }else if(jmlmenu<=16){
            grid=4;
        }else if(jmlmenu>16){
            grid=5;
        }
        
        if(jmlmenu<=20){
            tinggi=scrollPane2.getHeight()-5;
        }else if(jmlmenu<=25){
            tinggi=scrollPane2.getHeight()+(scrollPane2.getHeight()/4);
        }else if(jmlmenu>25){
            tinggi=1;
            for(i=25;i<jmlmenu;i++){
                if(i%5==0){
                    tinggi++;
                }
            }
            tinggi=scrollPane2.getHeight()+((scrollPane2.getHeight()/4)*tinggi);
        }
        
        Panelmenu.setLayout(new GridLayout(0,grid));
        Panelmenu.setPreferredSize(new Dimension(scrollPane2.getWidth()-10,tinggi));
        Panelmenu.revalidate();
        Panelmenu.repaint(); 
    }
	private int jmlmenu=0,grid=0,tinggi=0,i=0,menuawal=0;
	protected void TCariKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
			 if(!TCari.getText().equals("")){
				 Panelmenu.removeAll();
				 isButtonMenu();
				 setLayout();
	            }    
	        }
		
	}
	private ButtonBig[] dynamicButton;
	public void isButtonMenu() {
		jmlmenu=0;
		Integer i = 0;
		String query;
		String queryCount;
		if(!TCari.getText().equals("")) {
			query="SELECT * FROM btnmenu where btnName like '%"+TCari.getText()+"'";
			queryCount="SELECT count(btnName) from  btnMenu where btnName like '%"+TCari.getText()+"'";
		}else {
			query="SELECT * FROM btnmenu";
			queryCount="Select count(btnName) from btnmenu";
			}
		Integer CountBtnMenu=Sequel.cariInteger(queryCount);
		// System.out.print(CountBtnMenu);
		try {
			ResultSet rs;
			rs = koneksi.prepareStatement(query).executeQuery();
			dynamicButton=new ButtonBig[CountBtnMenu];
			while (rs != null && rs.next()) {
			dynamicButton[i] = new widget.ButtonBig();
//					iCons
				String icons = rs.getString("icons");
				if (icons == null) {
					icons = "/48x48/checklist.png";
				}
//					Icon\
				 	java.net.URL imgURL = getClass().getResource(icons);
				 	ImageIcon imageIcon ;
					if(imgURL==null) {
					 imageIcon = new ImageIcon(getClass().getResource("/48x48/checklist.png"));
					}else {
					 imageIcon = new ImageIcon(getClass().getResource(icons));
					}
					Image image = imageIcon.getImage();
					Image newimg = image.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
					imageIcon = new ImageIcon(newimg);

//					end Icon
					dynamicButton[i].setIcon(imageIcon);
					dynamicButton[i].setText(rs.getString("btnName"));
					dynamicButton[i].setIconTextGap(0);
					dynamicButton[i].setName("Btn" + i);
					dynamicButton[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Panelmenu.add(dynamicButton[i]);
				i++;
				jmlmenu++;
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	private void isTutup() {
		FlayMenu.setVisible(false);
		akses.setform("MainFrame");
		Window[] wins = Window.getWindows();
		for (Window win : wins) {
			if (win instanceof JDialog) {
				win.dispose();
			}
		}
	}

	protected void BtnCancelActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		edAdmin.setText("");
		edPwd.setText("");
		DlgLogin.dispose();
	}

	protected void BtnLoginActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (edAdmin.getText().trim().equals("")) {
			Valid.textKosong(edAdmin, "ID User");
		} else if (edPwd.getText().trim().equals("")) {
			Valid.textKosong(edPwd, "Password");
		} else {
			try {
				akses.setData(edAdmin.getText(), edPwd.getText());
				if (akses.getjml1() >= 1) {
					DlgLogin.dispose();
					BtnLog.setText("Log Out");
					lblStts.setText("Admin : ");
					lblUser.setText("Admin Utama");
					menuBar.setVisible(true);
					DynamicMenu("0", "admin");
					if (AKTIFKANTRACKSQL.equals("yes")) {
						Sequel.menyimpan("tracker", "'Admin Utama',current_date(),current_time()", "Login");
					}
				} else if (akses.getjml2() >= 1) {
					DlgLogin.dispose();
					BtnLog.setText("Log Out");
					menuBar.setVisible(true);
					lblStts.setText("Admin : ");
					lblUser.setText(akses.getkode());
					DynamicMenu("0", akses.getkode());
					if (AKTIFKANTRACKSQL.equals("yes")) {
						Sequel.menyimpan("tracker", "'" + edAdmin.getText() + "',current_date(),current_time()",
								"Login");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Maaf, Gagal login. ID User atau password ada yang salah ...!");
				}
			} catch (Exception e) {
				System.out.println("Notifikasi : " + e);
			}
		}
	}

	protected void BtnLogActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		// FlayMenu.setVisible(false);
		switch (BtnLog.getText().trim()) {
		case "Log Out":
			edAdmin.setText("");
			edPwd.setText("");
			BtnLog.setText("Log In");
			edAdmin.setText("");
			edPwd.setText("");
			BtnLog.setText("Log In");
			// MnLogin.setText("Log In");
			lblStts.setText("Status Admin : ");
			lblUser.setText("Log Out");
			// BtnMenu.setEnabled(false);
			isTutup();
			menuBar.setVisible(false);
			lblStts.setText("Status Admin : ");
			lblUser.setText("Log Out");
			menuBar.removeAll();
			break;
		case "Log In":
			DlgLogin.setVisible(true);
			edAdmin.requestFocus();
			break;
		}
	}

	protected void edPwdKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			BtnLoginActionPerformed(null);
		} else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
			edAdmin.requestFocus();
		} else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
			BtnLogin.requestFocus();
		}
	}

	protected void edAdminKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, BtnCancel, edPwd);
	}

	protected void BtnCloseActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		isTutup();
		int jawab = JOptionPane.showConfirmDialog(null, "Yakin anda mau keluar dari program ini ????", "Konfirmasi",
				JOptionPane.YES_NO_OPTION);
		if (jawab == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	protected void PanelWallMouseMoved(MouseEvent evt) {
		// TODO Auto-generated method stub
		setToolbar();
		Window[] wins = Window.getWindows();
		for (Window win : wins) {
			if (win instanceof JDialog) {
				win.setLocationRelativeTo(PanelUtama);
				win.toFront();
			}
		}
	}

	private void setToolbar() {
		if (internalFrameMenu.getWidth() < (WidthButtonDynamin + 8)) {
			internalFrameMenu.setSize(new Dimension(PanelUtama.getWidth(), 90));
		} else {
			internalFrameMenu.setSize(new Dimension(PanelUtama.getWidth(), 44));
		}
	}

	public void isWall() {
		try {
			ps = koneksi.prepareStatement(
					"select nama_instansi, alamat_instansi, kabupaten, propinsi, aktifkan, wallpaper,kontak,email,logo from setting");
			try {
				rs = ps.executeQuery();
				while (rs.next()) {
					jLabel8.setText(rs.getString(1));
					this.setTitle("SIM " + rs.getString("nama_instansi"));
					jLabel11.setText(rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4) + " ");
					akses.setnamars(rs.getString("nama_instansi"));
					akses.setalamatrs(rs.getString("alamat_instansi"));
					akses.setkabupatenrs(rs.getString("kabupaten"));
					akses.setpropinsirs(rs.getString("propinsi"));
					akses.setkontakrs(rs.getString("kontak"));
					akses.setemailrs(rs.getString("email"));
					if (rs.getString(5).equals("Yes")) {
						Blob blob = rs.getBlob(6);
						PanelWall
								.setBackgroundImage(new javax.swing.ImageIcon(blob.getBytes(1, (int) (blob.length()))));
						repaint();
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			}
		} catch (Exception e) {
			System.out.println("Notifikasi : Silahkan Set Aplikasi " + e);
		}
	}

	private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private JMenuBar menuBar;
	private JMenu profileMenu;
	private JMenuBar defaultBar;
	private JMenu jMenu4;

	public static MainFrame getInstance() {
		// TODO Auto-generated method stub
		if (myInstance == null)
			myInstance = new MainFrame();

		return myInstance;
	}

}
