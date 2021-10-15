package dirgahayu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import kepegawaian.DlgCariDokter;
import keuangan.DlgKasirLuar;
import widget.Button;
import widget.ComboBox;
import widget.InternalFrame;
import widget.RoundJTextField;
import widget.ScrollPane;
import widget.Table;
import widget.Tanggal;
import widget.TextBox;
import widget.panelisi;

public class DlgRegRadiologi extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private sekuel Sequel = new sekuel();
	private validasi Valid = new validasi();
	private Connection koneksi = koneksiDB.condb();
	private InternalFrame internalFrame1;
	private JPanel FormInput;
	private JTextField txtNama;
	private JTextField txtTempatLahir;
	private JTextField txtUmur;
	private Tanggal tanggal;
	private ComboBox cmbJk;
	private JLabel lblGolDar;
	private ComboBox cmbGoldar;
	private JLabel lblkelurahan;
	private JLabel lblKecamatan;
	private JLabel lblKabupaten;
	private JLabel lblProvinsi;
	private JTextField txtxKelurahan;
	private JTextField txtKecamatan;
	private JTextField txtKabupaten;
	private JTextField txtProvinsi;
	private JLabel lblKewargenegran;
	private JTextPane textPaneAlamat;
	private JTabbedPane TabWo;
	private InternalFrame internalFrameTabWo;
	private ScrollPane Scroll1;
	private JPanel jPanelTindakan;
	private Table tbPemeriksaan;
	private DefaultTableModel tabModelTidakanRadiologi, tabModelRegRadiologi;
	private JPanel jPanelPemeriksaan;
	private panelisi panelSearchPemeriksaan;
	private TextBox TCariPeriksa;
	private Button btnAllPeriksa;
	private InternalFrame internalFrameTabReg;
	private ScrollPane ScrollReg;
	private JPanel jPanelSearchRegister;
	private Table tbRegRadiologi;
	private JLabel labelKeywordReg;
	private TextBox TCariRegis;
	private Button btnAllRegis;
	private Tanggal DTPCari1;
	private JLabel lblSampai;
	private Tanggal DTPCari2;
	private boolean[] pilih;
	private String[] kode, nama;
	private int jml = 0, i = 0, index = 0, jmlparsial = 0;
	private JLabel lblNoReg;
	private JTextField txtNoReg;
	private String kd_kel, kd_kec, kd_kab, kd_prov, kd_dokter;
	private Button btnKeluar;
	private ComboBox cmbWn;
	private ComboBox pekerjaan;
	private ComboBox CMbPnd;
	private JLabel lblPendidikan;
	private JLabel lblKodePos;
	private RoundJTextField txtNotlp;
	private RoundJTextField txtKodePos;
	private String Tgl_regis;
	private Button btnHapusReg;
	private boolean isEdit = false;
	private Button btnCetak;
	private JLabel lblDokter;
	private Button btnDokter;
	private RoundJTextField txtxDokter;
	private JPanel panelRegistarsi;
	private Button btnSimpanRegistarsi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			DlgRegRadiologi dialog = new DlgRegRadiologi(new javax.swing.JFrame(), true);
			dialog.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent e) {
					System.exit(0);
				}
			});
			dialog.setVisible(true);
		});
	}

	/**
	 * Create the dialog.
	 */
	public DlgRegRadiologi(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		setUndecorated(true);
//		setResizable(false);
		initComponents();

		this.setLocation(8, 1);
		setSize(425, 21);
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		internalFrame1 = new widget.InternalFrame();
		internalFrame1.setMinimumSize(new Dimension(360, 480));
		internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)),
				"::[ Input Pendaftaran Radiologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11),
				new java.awt.Color(50, 50, 50)));
		internalFrame1.setName("internalFrame1");
		internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

		FormInput = new javax.swing.JPanel();
		FormInput.setName("FormInput");
		FormInput.setOpaque(false);
		FormInput.setPreferredSize(new java.awt.Dimension(720, 260));
		internalFrame1.add(FormInput, BorderLayout.PAGE_START);
		FormInput.setLayout(null);

		JLabel lblNama = new JLabel("Nama ");
		lblNama.setHorizontalAlignment(SwingConstants.LEFT);
		lblNama.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNama.setBounds(10, 14, 68, 14);
		FormInput.add(lblNama);

		txtNama = new RoundJTextField(5);
		txtNama.setBounds(114, 12, 306, 20);
		FormInput.add(txtNama);
		txtNama.setColumns(10);

		JLabel lbltmpLahir = new JLabel("Tempat Lahir");
		lbltmpLahir.setHorizontalAlignment(SwingConstants.LEFT);
		lbltmpLahir.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lbltmpLahir.setBounds(10, 44, 94, 14);
		FormInput.add(lbltmpLahir);

		JLabel lblTanggalLahir = new JLabel("Tanggal Lahir");
		lblTanggalLahir.setHorizontalAlignment(SwingConstants.LEFT);
		lblTanggalLahir.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblTanggalLahir.setBounds(10, 69, 105, 14);
		FormInput.add(lblTanggalLahir);

		JLabel lblUmur = new JLabel("Umur");
		lblUmur.setHorizontalAlignment(SwingConstants.LEFT);
		lblUmur.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblUmur.setBounds(223, 69, 68, 14);
		FormInput.add(lblUmur);

		JLabel lblJk = new JLabel("Jenis Kelamin");
		lblJk.setHorizontalAlignment(SwingConstants.LEFT);
		lblJk.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblJk.setBounds(10, 103, 94, 14);
		FormInput.add(lblJk);

		txtTempatLahir = new RoundJTextField(5);
		txtTempatLahir.setColumns(10);
		txtTempatLahir.setBounds(114, 38, 214, 20);
		FormInput.add(txtTempatLahir);

		txtUmur = new RoundJTextField(5);
		txtUmur.setEditable(false);
		txtUmur.setColumns(10);
		txtUmur.setBounds(315, 67, 105, 20);
		FormInput.add(txtUmur);

		tanggal = new Tanggal();
		tanggal.setForeground(new java.awt.Color(50, 70, 50));
		tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-06-2020" }));
		tanggal.setDisplayFormat("dd-MM-yyyy");
		tanggal.setName("Tanggal");
		tanggal.setOpaque(false);
		tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TanggalKeyPressed(evt);
			}
		});
		tanggal.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				tanggalLahirItemStateChanged(evt);
			}
		});

		tanggal.setBounds(114, 66, 90, 23);
		FormInput.add(tanggal);

		cmbJk = new ComboBox();
		cmbJk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LAKI-LAKI", "PEREMPUAN" }));
		cmbJk.setBounds(114, 100, 94, 23);
		FormInput.add(cmbJk);

		lblGolDar = new JLabel("Golongan Darah");
		lblGolDar.setHorizontalAlignment(SwingConstants.LEFT);
		lblGolDar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblGolDar.setBounds(223, 104, 94, 14);
		FormInput.add(lblGolDar);

		cmbGoldar = new ComboBox();
		cmbGoldar.setBounds(326, 100, 94, 23);
		cmbGoldar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "AB", "O" }));
		FormInput.add(cmbGoldar);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Alamat",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 134, 410, 53);
		FormInput.add(panel);
		panel.setLayout(null);

		textPaneAlamat = new JTextPane();
		textPaneAlamat.setBounds(6, 16, 394, 31);
		panel.add(textPaneAlamat);

		pekerjaan = new widget.ComboBox();
		Sequel.addItemCombo("Select nm_pekerjaan from pekerjaan order by nm_pekerjaan ASC", pekerjaan);
		pekerjaan.setName("Pekerjaan");
		FormInput.add(pekerjaan);
		pekerjaan.setBounds(114, 198, 112, 23);

		lblkelurahan = new JLabel("Kelurahan");
		lblkelurahan.setHorizontalAlignment(SwingConstants.LEFT);
		lblkelurahan.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblkelurahan.setBounds(458, 129, 68, 14);
		FormInput.add(lblkelurahan);

		lblKecamatan = new JLabel("Kecamatan");
		lblKecamatan.setHorizontalAlignment(SwingConstants.LEFT);
		lblKecamatan.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblKecamatan.setBounds(458, 158, 68, 14);
		FormInput.add(lblKecamatan);

		lblKabupaten = new JLabel("Kabupaten");
		lblKabupaten.setHorizontalAlignment(SwingConstants.LEFT);
		lblKabupaten.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblKabupaten.setBounds(458, 193, 68, 14);
		FormInput.add(lblKabupaten);

		lblProvinsi = new JLabel("Provinsi");
		lblProvinsi.setHorizontalAlignment(SwingConstants.LEFT);
		lblProvinsi.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblProvinsi.setBounds(458, 228, 68, 14);
		FormInput.add(lblProvinsi);

		txtxKelurahan = new RoundJTextField(5);
		txtxKelurahan.setColumns(10);
		txtxKelurahan.setEditable(false);
		txtxKelurahan.setBounds(566, 124, 140, 20);
		FormInput.add(txtxKelurahan);

		txtKecamatan = new RoundJTextField(5);
		txtKecamatan.setEditable(false);
		txtKecamatan.setColumns(10);
		txtKecamatan.setBounds(566, 153, 140, 20);
		FormInput.add(txtKecamatan);

		txtKabupaten = new RoundJTextField(5);
		txtKabupaten.setColumns(10);
		txtKabupaten.setEditable(false);
		txtKabupaten.setBounds(566, 187, 140, 20);
		FormInput.add(txtKabupaten);

		txtProvinsi = new RoundJTextField(5);
		txtProvinsi.setColumns(10);
		txtProvinsi.setEditable(false);
		txtProvinsi.setBounds(566, 223, 140, 20);
		FormInput.add(txtProvinsi);

		lblKewargenegran = new JLabel("Warganegara");
		lblKewargenegran.setHorizontalAlignment(SwingConstants.LEFT);
		lblKewargenegran.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblKewargenegran.setBounds(236, 232, 83, 14);
		FormInput.add(lblKewargenegran);

		Button btnKel = new Button();
		btnKel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DlgKelurahan kel = new DlgKelurahan(null, false);
				kel.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
				kel.setLocationRelativeTo(internalFrame1);
				kel.setVisible(true);

				kel.addWindowListener(new WindowListener() {
					@Override
					public void windowOpened(WindowEvent e) {
					}

					@Override
					public void windowClosing(WindowEvent e) {
					}

					@Override
					public void windowClosed(WindowEvent e) {
						txtxKelurahan.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 0).toString());
						kd_kel = kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 1).toString();
					}

					@Override
					public void windowIconified(WindowEvent e) {
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
					}

					@Override
					public void windowActivated(WindowEvent e) {
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
					}
				});

			}
		});
		btnKel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
		btnKel.setMnemonic('1');
		btnKel.setBounds(714, 124, 28, 23);
		FormInput.add(btnKel);

		Button btnKel_1 = new Button();
		btnKel_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DlgKecamatan kec = new DlgKecamatan(null, false);
				kec.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
				kec.setLocationRelativeTo(internalFrame1);
				kec.setVisible(true);
				kec.addWindowListener(new WindowListener() {
					@Override
					public void windowOpened(WindowEvent e) {
					}

					@Override
					public void windowClosing(WindowEvent e) {
					}

					@Override
					public void windowClosed(WindowEvent e) {
						txtKecamatan.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 0).toString());
						kd_kec = kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 1).toString();
					}

					@Override
					public void windowIconified(WindowEvent e) {
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
					}

					@Override
					public void windowActivated(WindowEvent e) {
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
					}
				});
			}
		});
		btnKel_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
		btnKel_1.setMnemonic('1');
		btnKel_1.setBounds(716, 153, 28, 23);
		FormInput.add(btnKel_1);

		Button btnKel_2 = new Button();
		btnKel_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DlgKabupaten kab = new DlgKabupaten(null, false);
				kab.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
				kab.setLocationRelativeTo(internalFrame1);
				kab.setVisible(true);
				kab.addWindowListener(new WindowListener() {
					@Override
					public void windowOpened(WindowEvent e) {
					}

					@Override
					public void windowClosing(WindowEvent e) {
					}

					@Override
					public void windowClosed(WindowEvent e) {
						txtKabupaten.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 0).toString());
						kd_kab = kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 1).toString();
					}

					@Override
					public void windowIconified(WindowEvent e) {
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
					}

					@Override
					public void windowActivated(WindowEvent e) {
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
					}
				});
			}
		});
		btnKel_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
		btnKel_2.setMnemonic('1');
		btnKel_2.setBounds(714, 189, 28, 23);
		FormInput.add(btnKel_2);

		Button btnKel_3 = new Button();
		btnKel_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DlgPropinsi prop = new DlgPropinsi(null, false);
				prop.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
				prop.setLocationRelativeTo(internalFrame1);
				prop.setVisible(true);
				prop.addWindowListener(new WindowListener() {
					@Override
					public void windowOpened(WindowEvent e) {
					}

					@Override
					public void windowClosing(WindowEvent e) {
					}

					@Override
					public void windowClosed(WindowEvent e) {
						txtProvinsi.setText(prop.getTable().getValueAt(prop.getTable().getSelectedRow(), 0).toString());
						kd_prov = prop.getTable().getValueAt(prop.getTable().getSelectedRow(), 1).toString();
					}

					@Override
					public void windowIconified(WindowEvent e) {
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
					}

					@Override
					public void windowActivated(WindowEvent e) {
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
					}
				});
			}
		});
		btnKel_3.setMnemonic('1');
		btnKel_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
		btnKel_3.setBounds(714, 223, 28, 23);
		FormInput.add(btnKel_3);

		lblNoReg = new JLabel("No Registrasi");
		lblNoReg.setHorizontalAlignment(SwingConstants.LEFT);
		lblNoReg.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNoReg.setBounds(458, 15, 83, 14);
		FormInput.add(lblNoReg);

		txtNoReg = new RoundJTextField(5);
		txtNoReg.setColumns(10);
		txtNoReg.setEnabled(false);
		txtNoReg.setBounds(566, 12, 158, 20);
		FormInput.add(txtNoReg);

		CMbPnd = new widget.ComboBox();
		CMbPnd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "TS", "TK", "SD", "SMP", "SMA",
				"SLTA/SEDERAJAT", "D1", "D2", "D3", "D4", "S1", "S2", "S3" }));
		CMbPnd.setName("CMbPnd"); // NOI18N
		CMbPnd.setBounds(566, 85, 70, 23);
		FormInput.add(CMbPnd);

		cmbWn = new ComboBox();
		cmbWn.setBounds(326, 229, 94, 23);
		cmbWn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "WNI", "WNA" }));
		FormInput.add(cmbWn);

		JLabel lblpekerjaan = new JLabel("Pekerjaan");
		lblpekerjaan.setHorizontalAlignment(SwingConstants.LEFT);
		lblpekerjaan.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblpekerjaan.setBounds(10, 202, 94, 14);
		FormInput.add(lblpekerjaan);

		JLabel lblNoTelphone = new JLabel("No Telphone");
		lblNoTelphone.setHorizontalAlignment(SwingConstants.LEFT);
		lblNoTelphone.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNoTelphone.setBounds(236, 201, 83, 14);
		FormInput.add(lblNoTelphone);

		txtNotlp = new RoundJTextField(5);
		txtNotlp.setBounds(319, 198, 112, 20);
		FormInput.add(txtNotlp);

		lblPendidikan = new JLabel("Pendidikan");
		lblPendidikan.setHorizontalAlignment(SwingConstants.LEFT);
		lblPendidikan.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblPendidikan.setBounds(458, 89, 68, 14);
		FormInput.add(lblPendidikan);

		lblKodePos = new JLabel("Kode Pos");
		lblKodePos.setHorizontalAlignment(SwingConstants.LEFT);
		lblKodePos.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblKodePos.setBounds(10, 234, 83, 14);
		FormInput.add(lblKodePos);

		txtKodePos = new RoundJTextField(5);
		txtKodePos.setBounds(114, 231, 112, 20);
		FormInput.add(txtKodePos);

		lblDokter = new JLabel("Dokter");
		lblDokter.setHorizontalAlignment(SwingConstants.LEFT);
		lblDokter.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblDokter.setBounds(458, 47, 68, 14);
		FormInput.add(lblDokter);

		txtxDokter = new RoundJTextField(5);
		txtxDokter.setEditable(false);
		txtxDokter.setBounds(566, 44, 193, 20);
		FormInput.add(txtxDokter);

		btnDokter = new Button();
		btnDokter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DlgCariDokter dokter = new DlgCariDokter(null, false);
				dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
				dokter.setLocationRelativeTo(internalFrame1);
				dokter.setVisible(true);

				dokter.addWindowListener(new WindowListener() {
					@Override
					public void windowOpened(WindowEvent e) {
					}

					@Override
					public void windowClosing(WindowEvent e) {
					}

					@Override
					public void windowClosed(WindowEvent e) {
						txtxDokter.setText(
								dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
						kd_dokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
					}

					@Override
					public void windowIconified(WindowEvent e) {
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
					}

					@Override
					public void windowActivated(WindowEvent e) {
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
					}
				});
			}
		});
		btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
		btnDokter.setMnemonic('1');
		btnDokter.setBounds(769, 44, 28, 23);
		FormInput.add(btnDokter);

		TabWo = new javax.swing.JTabbedPane();
		TabWo.setBackground(new java.awt.Color(255, 255, 254));
		TabWo.setForeground(new java.awt.Color(50, 50, 50));
		TabWo.setFont(new java.awt.Font("Tahoma", 0, 11));
		TabWo.setName("TabRawat");
//		TabWo.addMouseListener(new java.awt.event.MouseAdapter() {
//			@Override
//			public void mouseClicked(java.awt.event.MouseEvent evt) {
//				TabRawatMouseClicked(evt);
//			}
//		});
		internalFrameTabWo = new widget.InternalFrame();
		internalFrameTabWo.setBackground(new java.awt.Color(254, 254, 254));
		internalFrameTabWo.setBorder(null);
		internalFrameTabWo.setName("internalFrameTabWo");
		internalFrameTabWo.setLayout(new java.awt.BorderLayout(1, 1));

		TabWo.addTab("Tindakan Radiologi", internalFrameTabWo);
		Scroll1 = new widget.ScrollPane();
		Scroll1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		Scroll1.setName("Scroll1");
		Scroll1.setOpaque(true);

		jPanelTindakan = new javax.swing.JPanel();
//		jPanelTindakan.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)), " Pemeriksaan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); 
		jPanelTindakan.setName("jPanelTindakan");
		jPanelTindakan.setOpaque(false);
//		jPanelTindakan.setPreferredSize(new java.awt.Dimension(360, 102));
		jPanelPemeriksaan = new javax.swing.JPanel();
		jPanelPemeriksaan.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)), " Pemeriksaan ",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50)));
		jPanelPemeriksaan.setName("jPanelTindakan");
		jPanelPemeriksaan.setOpaque(false);
//		jPanelPemeriksaan.setPreferredSize(new java.awt.Dimension(360, 102));

		panelSearchPemeriksaan = new widget.panelisi();
		panelSearchPemeriksaan.setBorder(null);
		panelSearchPemeriksaan.setName("panelSearchPemeriksaan"); // NOI18N
		panelSearchPemeriksaan.setPreferredSize(new java.awt.Dimension(100, 43));
		panelSearchPemeriksaan.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

		JLabel label10 = new JLabel();
		label10.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		label10.setBounds(new Rectangle(5, 0, 0, 0));
		label10.setText("Key Word :");
		label10.setName("label10"); // NOI18N
		label10.setPreferredSize(new java.awt.Dimension(68, 23));
		panelSearchPemeriksaan.add(label10);

		TCariPeriksa = new widget.TextBox();
		TCariPeriksa.setToolTipText("Alt+C");
		TCariPeriksa.setName("TCariPeriksa"); // NOI18N
		TCariPeriksa.setPreferredSize(new java.awt.Dimension(160, 23));

		TCariPeriksa.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TCariPeriksaActionPerformed(evt);
			}
		});
		TCariPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TCariPeriksaKeyPressed(evt);
			}
		});

		jPanelPemeriksaan.setLayout(new java.awt.BorderLayout(1, 1));
		panelSearchPemeriksaan.add(TCariPeriksa);

		jPanelPemeriksaan.add(panelSearchPemeriksaan, java.awt.BorderLayout.PAGE_END);
		btnAllPeriksa = new widget.Button();
		btnAllPeriksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png")));
		btnAllPeriksa.setMnemonic('2');
		btnAllPeriksa.setToolTipText("Alt+2");
		btnAllPeriksa.setName("BtnAllPeriksa");
		btnAllPeriksa.setText("Cari");
		btnAllPeriksa.setPreferredSize(new java.awt.Dimension(70, 23));
		btnAllPeriksa.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnAllPeriksaActionPerformed(evt);
			}
		});
		btnAllPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
//                BtnAllPeriksaKeyPressed(evt);
			}
		});
		panelSearchPemeriksaan.add(btnAllPeriksa);

		tbPemeriksaan = new widget.Table();
		tbPemeriksaan.setName("tbPemeriksaan");
		jPanelPemeriksaan.add(Scroll1);

		tabModelTidakanRadiologi = new DefaultTableModel(null,
				new Object[] { "P", "Kode Periksa", "Nama Pemeriksaan", "Tarif" }) {
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				boolean a = false;
				if (colIndex == 0) {
					a = true;
				}
				return a;
			}

			Class[] types = new Class[] { java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Double.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		tbPemeriksaan.setModel(tabModelTidakanRadiologi);
		tbPemeriksaan.setPreferredScrollableViewportSize(new Dimension(680, 500));
		tbPemeriksaan.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		for (int i = 0; i < tabModelTidakanRadiologi.getColumnCount(); i++) {
			TableColumn column = tbPemeriksaan.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(20);
			} else if (i == 1) {
				column.setPreferredWidth(160);
			} else if (i == 2) {
				column.setPreferredWidth(480);
			} else {
				column.setPreferredWidth(100);
			}
		}
		tbPemeriksaan.setDefaultRenderer(Object.class, new WarnaTable());
		Scroll1.setViewportView(tbPemeriksaan);

		internalFrameTabWo.add(jPanelTindakan);
		jPanelTindakan.setLayout(new GridLayout(0, 1, 0, 0));
		jPanelTindakan.add(jPanelPemeriksaan);
		internalFrame1.add(TabWo, java.awt.BorderLayout.CENTER);

		panelRegistarsi = new javax.swing.JPanel();
		panelRegistarsi.setName("FormInput");
		panelRegistarsi.setOpaque(false);
		panelRegistarsi.setPreferredSize(new Dimension(720, 45));
		internalFrame1.add(panelRegistarsi, BorderLayout.PAGE_END);
		panelRegistarsi.setLayout(null);

		btnSimpanRegistarsi = new Button();
		btnSimpanRegistarsi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BtnSimpanActionPerformed(e);
			}
		});
		btnSimpanRegistarsi.setBounds(66, 11, 91, 29);
		btnSimpanRegistarsi.setMnemonic('S');
		btnSimpanRegistarsi.setName("SimpanReg");
		btnSimpanRegistarsi.setText("Simpan");
		btnSimpanRegistarsi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png")));
		panelRegistarsi.add(btnSimpanRegistarsi);

		btnKeluar = new widget.Button();
		btnKeluar.setBounds(468, 10, 91, 30);
		panelRegistarsi.add(btnKeluar);
		btnKeluar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window[] wins = Window.getWindows();
				for (Window win : wins) {
					if (win instanceof JDialog) {
						win.dispose();
					}
				}
				dispose();
			}
		});
		btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png")));
		btnKeluar.setText("Keluar");

		Button btnCelar = new Button();
		btnCelar.setBounds(167, 10, 108, 30);
		panelRegistarsi.add(btnCelar);
		btnCelar.setText("Bersihkan");
		btnCelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				emptyTeks();
				isEdit = false;
			}
		});
		btnCelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png")));

		btnHapusReg = new Button();
		btnHapusReg.setBounds(299, 10, 79, 30);
		panelRegistarsi.add(btnHapusReg);
		btnHapusReg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnHapusReg();
			}
		});
		btnHapusReg.setMnemonic('B');
		btnHapusReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png")));
		btnHapusReg.setText("Hapus");

		btnCetak = new Button();
		btnCetak.setBounds(388, 10, 79, 30);
		btnCetak.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BtnNotaJasperActionPerformed(e);
			}
		});
		btnCetak.setMnemonic('3');
		btnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png")));
		btnCetak.setText("Nota");
		panelRegistarsi.add(btnCetak);

//		 Regiter
		internalFrameTabReg = new widget.InternalFrame();
		internalFrameTabReg.setBackground(new java.awt.Color(254, 254, 254));
		internalFrameTabReg.setBorder(null);
		internalFrameTabReg.setName("internalFrameTabReg");
		internalFrameTabReg.setLayout(new java.awt.BorderLayout(1, 1));

		TabWo.addTab("Registrasi Radiologi", internalFrameTabReg);
		jPanelSearchRegister = new javax.swing.JPanel();
		jPanelSearchRegister.setName("jPanelRegister");
		jPanelSearchRegister.setOpaque(false);
		jPanelSearchRegister.setPreferredSize(new Dimension(380, 50));
		internalFrameTabReg.add(jPanelSearchRegister, java.awt.BorderLayout.PAGE_END);
		jPanelSearchRegister.setLayout(null);

		labelKeywordReg = new JLabel();
		labelKeywordReg.setText("Key Word :");
		labelKeywordReg.setPreferredSize(new Dimension(68, 23));
		labelKeywordReg.setName("label10");
		labelKeywordReg.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		labelKeywordReg.setBounds(new Rectangle(10, 9, 68, 23));
		jPanelSearchRegister.add(labelKeywordReg);

		TCariRegis = new TextBox();
		TCariRegis.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					TampilRegistrasi();
				}
			}
		});
		TCariRegis.setBounds(76, 8, 147, 23);
		TCariRegis.setName("TCariPeriksa"); // NOI18N
		TCariRegis.setPreferredSize(new java.awt.Dimension(160, 23));
		jPanelSearchRegister.add(TCariRegis);

		DTPCari1 = new widget.Tanggal();
		DTPCari1.setBounds(283, 9, 88, 23);
		DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
		DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-03-2021" }));
		DTPCari1.setDisplayFormat("dd-MM-yyyy");
		DTPCari1.setName("DTPCari1");
		DTPCari1.setOpaque(false);
		DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
		jPanelSearchRegister.add(DTPCari1);

		btnAllRegis = new Button();
		btnAllRegis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TampilRegistrasi();
			}
		});
		btnAllRegis.setBounds(503, 5, 70, 30);
		btnAllRegis.setName("btnAllRegis");
		btnAllRegis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png")));
		btnAllRegis.setText("Cari");
		btnAllRegis.setPreferredSize(new java.awt.Dimension(70, 23));
		jPanelSearchRegister.add(btnAllRegis);

		JLabel lblTanggalCariReg = new JLabel();
		lblTanggalCariReg.setText("Tanggal :");
		lblTanggalCariReg.setPreferredSize(new Dimension(68, 23));
		lblTanggalCariReg.setName("label10");
		lblTanggalCariReg.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblTanggalCariReg.setBounds(new Rectangle(10, 9, 68, 23));
		lblTanggalCariReg.setBounds(233, 9, 57, 23);
		jPanelSearchRegister.add(lblTanggalCariReg);

		lblSampai = new JLabel();
		lblSampai.setText("s/d");
		lblSampai.setPreferredSize(new Dimension(68, 23));
		lblSampai.setName("label10");
		lblSampai.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblSampai.setBounds(new Rectangle(10, 9, 68, 23));
		lblSampai.setBounds(381, 9, 29, 23);
		jPanelSearchRegister.add(lblSampai);

		DTPCari2 = new Tanggal();
		DTPCari2.setBounds(405, 9, 88, 23);
		DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
		DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-03-2021" }));
		DTPCari2.setDisplayFormat("dd-MM-yyyy");
		DTPCari2.setName("DTPCari1");
		DTPCari2.setOpaque(false);
		DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
		jPanelSearchRegister.add(DTPCari2);

		ScrollReg = new widget.ScrollPane();
		ScrollReg.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		ScrollReg.setName("ScrollReg");
		ScrollReg.setOpaque(true);
		internalFrameTabReg.add(ScrollReg, java.awt.BorderLayout.PAGE_START);

		tbRegRadiologi = new widget.Table();
		tabModelRegRadiologi = new DefaultTableModel(null, new Object[] { "P", "No Periksa", "Nama Pasien" }) {
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				boolean a = false;
				if (colIndex == 0) {
					a = true;
				}
				return a;
			}

			Class[] types = new Class[] { java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Double.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		tbRegRadiologi.setModel(tabModelRegRadiologi);
		tbRegRadiologi.setPreferredScrollableViewportSize(new Dimension(800, 800));
		tbRegRadiologi.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		for (int i = 0; i < tabModelRegRadiologi.getColumnCount(); i++) {
			TableColumn column = tbRegRadiologi.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(90);
			} else if (i == 1) {
				column.setPreferredWidth(180);
			} else if (i == 2) {
				column.setPreferredWidth(380);
			} else {
				column.setPreferredWidth(120);
			}
		}

		tbRegRadiologi.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tabRegRadiologiMouseClicked(evt);
			}
		});
		tbRegRadiologi.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				tabRegRadiologiKeyPressed(evt);
			}
		});

		ScrollReg.setViewportView(tbRegRadiologi);
		getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);
		pack();
	}

	protected void BtnNotaJasperActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (TabWo.getSelectedIndex() == 1) {
			if (tbRegRadiologi.getSelectedRow() != -1) {
				this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				String noorder = tbRegRadiologi.getValueAt(tbRegRadiologi.getSelectedRow(), 1).toString().trim();
				String dates = Sequel.cariIsi("select tanggal_reg from reg_noRm where no_reg='" + noorder + "'");
				Map<String, Object> param = new HashMap<>();
				param.put("namars", akses.getnamars());
				param.put("alamatrs", akses.getalamatrs());
				param.put("kotars", akses.getkabupatenrs());
				param.put("propinsirs", akses.getpropinsirs());
				param.put("kontakrs", akses.getkontakrs());
				param.put("emailrs", akses.getemailrs());
				param.put("logo", Sequel.cariGambar("select logo from setting"));
				param.put("tanggal", dates);
				param.put("nm_dockterpr", "");
				param.put("nm_petugas", akses.getnamauser());
				param.put("nm_pasien",
						Sequel.cariIsi("select nama_pasien from reg_noRm where no_reg='" + noorder + "'"));
				param.put("nm_dockter", Sequel.cariIsi(
						"select nm_dokter from reg_noRm inner join dokter on dokter.kd_dokter=reg_noRm.kd_dokter where no_reg='"
								+ noorder + "'"));
				param.put("tgl_lahir",
						Sequel.cariString("select tgl_lahir from reg_noRm where no_reg='" + noorder + "'"));
				param.put("umur", Sequel.cariString("select umur from reg_noRm where no_reg='" + noorder + "'"));
				Valid.MyReportqry("notaRadiologiLuar.jasper", "report", "::[ Billing Radiologi & Pemeriksaan Luar]::",
						"Select ROW_NUMBER() OVER(PARTITION BY reg_noRm.no_reg) as no,jns_perawatan_radiologi.nm_perawatan,jns_perawatan_radiologi.total_byr "
								+ "nama_pasien,alamat,tgl_lahir,umur,jeni_kelamin as jk from reg_noRm "
								+ "inner join detail_reg_radiologi on detail_reg_radiologi.no_reg=reg_noRm.no_reg "
								+ "inner join jns_perawatan_radiologi on jns_perawatan_radiologi.kd_jenis_prw=detail_reg_radiologi.kd_perawatan "
								+ "where reg_noRm.no_reg='" + noorder + "' ",
						param);
				this.setCursor(Cursor.getDefaultCursor());
			} else {
				JOptionPane.showMessageDialog(null, "Silahkan Pilih Data terlebih dahulu");
			}
		}

	}

	protected void btnHapusReg() {
		// TODO Auto-generated method stub
		for (i = 0; i < tbRegRadiologi.getRowCount(); i++) {
			if (tbRegRadiologi.getValueAt(i, 0).toString().equals("true")) {
				if (Sequel.cariInteger("Select count(no_reg) as reg from  detail_reg_radiologi where no_reg='"
						+ tbRegRadiologi.getValueAt(tbRegRadiologi.getSelectedRow(), 1)
						+ "' and status_bayar='Sudah Lunas'") >= 0) {

					int jawab = JOptionPane.showConfirmDialog(null, "Yakin anda akan Menghapus Data ini ????",
							"Konfirmasi", JOptionPane.YES_NO_OPTION);
					if (jawab == JOptionPane.YES_OPTION) {
						Sequel.meghapus("detail_reg_radiologi", "no_reg",
								tbRegRadiologi.getValueAt(tbRegRadiologi.getSelectedRow(), 1).toString());
						Sequel.meghapus("reg_noRm", "no_reg",
								tbRegRadiologi.getValueAt(tbRegRadiologi.getSelectedRow(), 1).toString());
						emptyTeks();
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Maaf, Tidak boleh dihapus karena sudah ada tindakan yang sudah dibayar.\nSilahkan hubungi kasir...!!!!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data Registrasi...!!!!");

			}
		}
	}

	protected void tabRegRadiologiKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (tabModelRegRadiologi.getRowCount() != 0) {
			if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
				i = tbRegRadiologi.getSelectedColumn();
				 if (i == 1) {
					 if (akses.getkasir_ralan() == true) {
						 DlgKasirLuar kasr=new DlgKasirLuar(null,false);
						 kasr.SetNoRm(txtNoReg.getText());
						 kasr.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
						 kasr.setLocationRelativeTo(internalFrame1);
						 kasr.setVisible(true);
					}
				 }
			}
		}
	}

	

	protected void tabRegRadiologiMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		if (tabModelRegRadiologi.getRowCount() != 0) {
			try {
				getDataReg();
			} catch (java.lang.NullPointerException e) {
			}
		}
	}

	private void getDataReg() {
		// TODO Auto-generated method stub

		if (tbRegRadiologi.getSelectedRow() != -1) {
			String Noreg = tbRegRadiologi.getValueAt(tbRegRadiologi.getSelectedRow(), 1).toString();
			txtNoReg.setText(Noreg);
			txtNama.setText(Sequel.cariIsi("select nama_pasien from reg_noRm where no_reg='" + Noreg + "'"));
			txtTempatLahir.setText(Sequel.cariIsi("select tempat_lahir from reg_noRm where no_reg='" + Noreg + "'"));
			txtUmur.setText(Sequel.cariIsi("select umur from reg_noRm where no_reg='" + Noreg + "'"));
			cmbJk.setSelectedItem(Sequel.cariIsi("select jeni_kelamin from reg_noRm where no_reg='" + Noreg + "'"));
			cmbGoldar.setSelectedItem(
					Sequel.cariIsi("select golongan_darah from reg_noRm where no_reg='" + Noreg + "'"));
			textPaneAlamat.setText(Sequel.cariIsi("select alamat from reg_noRm where no_reg='" + Noreg + "'"));
			pekerjaan.setSelectedItem(Sequel.cariIsi("select pekerjaan from reg_noRm where no_reg='" + Noreg + "'"));
			txtNotlp.setText(Sequel.cariIsi("select no_ltp from reg_noRm where no_reg='" + Noreg + "'"));
			CMbPnd.setSelectedItem(Sequel.cariIsi("select pendidikan from reg_noRm where no_reg='" + Noreg + "'"));
			txtKodePos.setText(Sequel.cariIsi("select kode_pos from reg_noRm where no_reg='" + Noreg + "'"));
			kd_kel = Sequel.cariIsi("select kd_kel from reg_noRm where no_reg='" + Noreg + "'");
			txtxKelurahan.setText(Sequel.cariIsi("select nm_kel from kelurahan where kd_kel='" + kd_kel + "'"));
			kd_kec = Sequel.cariIsi("select kd_kec from reg_noRm where no_reg='" + Noreg + "'");
			txtKecamatan.setText(Sequel.cariIsi("select nm_kec from kecamatan where kd_kec='" + kd_kec + "'"));
			kd_kab = Sequel.cariIsi("select kd_kab from reg_noRm where no_reg='" + Noreg + "'");
			txtKabupaten.setText(Sequel.cariIsi("select nm_kab from kabupaten where kd_kab='" + kd_kab + "'"));
			kd_prov = Sequel.cariIsi("select kd_prov from reg_noRm where no_reg='" + Noreg + "'");
			txtProvinsi.setText(Sequel.cariIsi("select nm_prop from propinsi where kd_prop='" + kd_prov + "'"));
			cmbWn.setSelectedItem(Sequel.cariIsi("select kewarganegaraan from reg_noRm where no_reg='" + Noreg + "'"));
			
			Valid.SetTgl(tanggal, Sequel.cariIsi("select tgl_lahir from reg_noRm where no_reg='" + Noreg + "'"));
			kd_dokter = Sequel.cariIsi("select kd_dokter from reg_noRm where no_reg='" + Noreg + "'");
			txtxDokter.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kd_dokter + "'"));
			btnSimpanRegistarsi.setText("Ubah");
			isEdit = true;
			tampil();
		} else {
			JOptionPane.showMessageDialog(null, "Silahkan Pilih Data terlebih dahulu");
		}
	}

	public void emptyTeks() {
		txtNama.setText("");
		txtTempatLahir.setText("");
		txtUmur.setText("");
		textPaneAlamat.setText("");
		txtxKelurahan.setText("");
		txtKecamatan.setText("");
		txtKabupaten.setText("");
		txtProvinsi.setText("");
		txtNotlp.setText("");
		txtKodePos.setText("");
		cmbWn.setSelectedItem("WNI");
		txtKodePos.setText("");
		cmbJk.setSelectedIndex(0);
		cmbGoldar.setSelectedIndex(0);
		CMbPnd.setSelectedIndex(0);
		kd_prov = "";
		kd_kec = "";
		kd_kel = "";
		kd_kab = "";
		kd_dokter = "";
		txtxDokter.setText("");
		tanggal.setDate(new Date());
		autoNomor();
		tampil();
		TampilRegistrasi();
	}

	protected void tanggalLahirItemStateChanged(ItemEvent evt) {
		// TODO Auto-generated method stub
		Date lahir;
		lahir = tanggal.getDate();
		LocalDate today = LocalDate.now();
		LocalDate birthday;
		Period p;
		birthday = lahir.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		p = Period.between(birthday, today);
		txtUmur.setText(String.valueOf(p.getYears()) + " Th " + String.valueOf(p.getMonths()) + " Bln "
				+ String.valueOf(p.getDays()) + " Hr");

	}

	protected void TCariPeriksaKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			tampil();
		}
	}

	protected void TCariPeriksaActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		tampil();
	}

	protected void BtnAllPeriksaActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		tampil();
	}

	protected void TanggalKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			txtUmur.requestFocus();
		} else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
			txtTempatLahir.requestFocus();
		}
	}

	public void isReset() {
		jml = tbPemeriksaan.getRowCount();
		for (i = 0; i < jml; i++) {
			tbPemeriksaan.setValueAt(false, i, 0);
		}
		Valid.tabelKosong(tabModelTidakanRadiologi);
		tampil();
	}

	protected void BtnSimpanActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (txtNoReg.getText().trim().equals("")) {
			Valid.textKosong(txtNoReg, "No.Registrasi");
		} else if (txtNama.getText().trim().equals("")) {
			Valid.textKosong(txtNama, "Nama Pasien");
		} else if (txtUmur.getText().trim().equals("")) {
			Valid.textKosong(txtUmur, "Umur");
		} else if (txtxKelurahan.getText().trim().equals("")) {
			Valid.textKosong(txtxKelurahan, "Kelurahan");
		} else if (txtKecamatan.getText().trim().equals("")) {
			Valid.textKosong(txtKecamatan, "Kecamatan");
		} else if (txtKabupaten.getText().trim().equals("")) {
			Valid.textKosong(txtKabupaten, "Kabupaten");
		} else if (txtProvinsi.getText().trim().equals("")) {
			Valid.textKosong(txtProvinsi, "Provinsi");
		} else if (txtxDokter.getText().trim().equals("") && kd_dokter.equals("")) {
			Valid.textKosong(txtxDokter, "Dokter");
		} else {

			PreparedStatement ps, ps1, psUpdate;
			ResultSet rs;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			boolean Status = false;
			Tgl_regis = dateFormat.format(now);
			if (btnSimpanRegistarsi.getText().equals("Simpan")) {
				try {
					for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
						if (tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
							ps1 = koneksi.prepareStatement(
									"Insert into detail_reg_radiologi (no_reg,kd_perawatan,harga,total_bayar,status_bayar) VALUES ('"
											+ txtNoReg.getText() + "','" + tbPemeriksaan.getValueAt(i, 1).toString()
											+ "','" + tbPemeriksaan.getValueAt(i, 3).toString() + "','"
											+ tbPemeriksaan.getValueAt(i, 3).toString() + "','Belum Lunas')");
							ps1.executeUpdate();
							Status = true;
						}
					}

					if (Status == true) {
						ps = koneksi.prepareStatement("insert into reg_noRm (no_reg,\r\n" + "tanggal_reg,\r\n"
								+ "nama_pasien,\r\n" + "tempat_lahir,\r\n" + "tgl_lahir,\r\n" + "umur,\r\n"
								+ "jeni_kelamin,\r\n" + "golongan_darah,\r\n" + "pendidikan,\r\n" + "pekerjaan,\r\n"
								+ "alamat,\r\n" + "kd_kel,\r\n" + "kd_kec,\r\n" + "kd_kab,\r\n" + "kd_prov,\r\n"
								+ "no_ltp,\r\n" + "kode_pos,\r\n" + "kewarganegaraan,\r\n"
								+ "type_reg,kd_dokter,jam_sampel,tgl_sampel,jam_hasil,tgl_hasil) VALUES('"
								+ txtNoReg.getText() + "','" + Tgl_regis + "','" + txtNama.getText() + "','"
								+ txtTempatLahir.getText() + "','" + Valid.SetTgl(tanggal.getSelectedItem().toString())
								+ "','" + txtUmur.getText() + "','" + cmbJk.getSelectedItem().toString() + "'," + "'"
								+ cmbGoldar.getSelectedItem().toString() + "','" + CMbPnd.getSelectedItem().toString()
								+ "','" + pekerjaan.getSelectedItem().toString() + "','"
								+ textPaneAlamat.getText().toString() + "','" + kd_kel + "','" + kd_kec + "','" + kd_kab
								+ "'," + "'" + kd_prov + "','" + txtNotlp.getText() + "','" + txtKodePos.getText()
								+ "','" + cmbWn.getSelectedItem().toString() + "','1','" + kd_dokter
								+ "','00:00:00','0000-00-00','00:00:00','0000-00-00')");
//					System.out.print(ps);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Proses Simpan Berhasil");
						emptyTeks();
					} else {
						JOptionPane.showMessageDialog(null, "Silahkan Tindakan Terlebih dahulu");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				int Check = Sequel.cariInteger(
						"select count(no_reg) from reg_noRm where no_reg='" + txtNoReg.getText() + "' and status_bayar='Lunas'");
				if (Check == 1) {
					JOptionPane.showMessageDialog(null, "Data Sudah Tervalidasi tidak dapat di ubah");
				} else {
					int reply = JOptionPane.showConfirmDialog(rootPane, "Apakah Anda yakin akan mengubah data ini",
							"Konfirmasi", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						try {
							Sequel.meghapus("detail_reg_radiologi", "no_reg", txtNoReg.getText());
							for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
								if (tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
									psUpdate = koneksi.prepareStatement(
											"Insert into detail_reg_radiologi (no_reg,kd_perawatan,harga,total_bayar,status_bayar) VALUES ('"
													+ txtNoReg.getText() + "','"
													+ tbPemeriksaan.getValueAt(i, 1).toString() + "','"
													+ tbPemeriksaan.getValueAt(i, 3).toString() + "','"
													+ tbPemeriksaan.getValueAt(i, 3).toString() + "','Belum Lunas')");
									psUpdate.executeUpdate();
									Status = true;
								}
							}
							if (Status == true) {
								Sequel.mengedit("reg_noRm", "no_reg='" + txtNoReg.getText() + "'", "nama_pasien='"
										+ txtNama.getText() + "',tempat_lahir='" + txtTempatLahir.getText()
										+ "',tgl_lahir='" + Valid.SetTgl(tanggal.getSelectedItem().toString())
										+ "',umur='" + txtUmur.getText() + "'," + "jeni_kelamin='"
										+ cmbJk.getSelectedItem().toString() + "',golongan_darah='"
										+ cmbGoldar.getSelectedItem().toString() + "'," + "pendidikan='"
										+ CMbPnd.getSelectedItem().toString() + "',pekerjaan='"
										+ pekerjaan.getSelectedItem().toString() + "',alamat='"
										+ textPaneAlamat.getText().toString() + "'," + "kd_kel='" + kd_kel
										+ "',kd_kec='" + kd_kec + "',kd_kab='" + kd_kab + "',kd_prov='" + kd_prov
										+ "',no_ltp='" + txtNotlp.getText() + "',kode_pos='" + txtKodePos.getText()
										+ "',kewarganegaraan='" + cmbWn.getSelectedItem().toString() + "'");
								JOptionPane.showMessageDialog(null, "Proses Simpan Berhasil");
								emptyTeks();
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	private void autoNomor() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		// System.out.println(dateFormat.format(now));
		Valid.autoNomer3(
				"select ifnull(MAX(CONVERT(RIGHT(no_reg,4),signed)),0) from reg_noRm where tanggal_reg='"
						+ dateFormat.format(now) + "'",
				"RAD/" + dateFormat.format(now).replaceAll("-", "/") + "/", 6, txtNoReg);
	}

	private void TampilRegistrasi() {
		Valid.tabelKosong(tabModelRegRadiologi);
		PreparedStatement psReg;
		ResultSet rs = null;

		try {
			psReg = koneksi.prepareStatement(
					"Select no_reg,nama_pasien from reg_noRm where nama_pasien like '" + TCariRegis.getText()
							+ "%' and tanggal_reg between '" + Valid.SetTgl(DTPCari1.getSelectedItem().toString())
							+ "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem().toString()) + "' ");
			rs = psReg.executeQuery();

			while (rs.next()) {
				tabModelRegRadiologi
						.addRow(new Object[] { false, rs.getString("no_reg"), rs.getString("nama_pasien") });
			}

		} catch (Exception e) {
			System.out.println("Notifikasi 2 : " + e);
		}
	}

	private void tampil() {
		PreparedStatement pspemeriksaan;
		ResultSet rs = null;
		Valid.tabelKosong(tabModelTidakanRadiologi);
		try {
			jml = 0;
			for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
				if (tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
					jml++;
				}
			}

			pilih = null;
			pilih = new boolean[jml];
			kode = null;
			kode = new String[jml];
			nama = null;
			nama = new String[jml];

			index = 0;
			for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
				if (tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
					pilih[index] = true;
					kode[index] = tbPemeriksaan.getValueAt(i, 1).toString();
					nama[index] = tbPemeriksaan.getValueAt(i, 2).toString();
					index++;
				}
			}

			Valid.tabelKosong(tabModelTidakanRadiologi);
			for (i = 0; i < jml; i++) {
				tabModelTidakanRadiologi.addRow(new Object[] { pilih[i], kode[i], nama[i] });
			}
			String query = "";

			boolean ischeck = false;
			String condition = "";
			if (!TCariPeriksa.getText().trim().equals("")) {
				condition = " and jns_perawatan_radiologi.nm_perawatan like '%" + TCariPeriksa.getText().trim() + "%' ";
			}
			if (isEdit == true) {
				query = "select jns_perawatan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan,jns_perawatan_radiologi.total_byr,"
						+ "jns_perawatan_radiologi.bagian_rs,jns_perawatan_radiologi.bhp,jns_perawatan_radiologi.tarif_perujuk,"
						+ "jns_perawatan_radiologi.tarif_tindakan_dokter,jns_perawatan_radiologi.tarif_tindakan_petugas,"
						+ "jns_perawatan_radiologi.kso,jns_perawatan_radiologi.menejemen,penjab.png_jawab, IF(detail_reg_radiologi.no_reg IS NULL,0,1) as reg_tindakan "
						+ "from jns_perawatan_radiologi "
						+ "inner join penjab on penjab.kd_pj=jns_perawatan_radiologi.kd_pj "
						+ "left join detail_reg_radiologi on detail_reg_radiologi.kd_perawatan=jns_perawatan_radiologi.kd_jenis_prw and detail_reg_radiologi.no_reg='"
						+ txtNoReg.getText() + "' "
						+ "where jns_perawatan_radiologi.status='1' and jns_perawatan_radiologi.kelas='Rawat Jalan' and penjab.png_jawab='UMUM' "
						+ condition + " order by jns_perawatan_radiologi.kd_jenis_prw";
			} else {

				query = "select jns_perawatan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan,jns_perawatan_radiologi.total_byr,"
						+ "jns_perawatan_radiologi.bagian_rs,jns_perawatan_radiologi.bhp,jns_perawatan_radiologi.tarif_perujuk,"
						+ "jns_perawatan_radiologi.tarif_tindakan_dokter,jns_perawatan_radiologi.tarif_tindakan_petugas,"
						+ "jns_perawatan_radiologi.kso,jns_perawatan_radiologi.menejemen,penjab.png_jawab "
						+ "from jns_perawatan_radiologi "
						+ "inner join penjab on penjab.kd_pj=jns_perawatan_radiologi.kd_pj "
						+ "where jns_perawatan_radiologi.status='1' and jns_perawatan_radiologi.kelas='Rawat Jalan' and penjab.png_jawab='UMUM'"
						+ condition + " order by jns_perawatan_radiologi.kd_jenis_prw";
			}

			pspemeriksaan = koneksi.prepareStatement(query);
			try {
				rs = pspemeriksaan.executeQuery();
				while (rs != null && rs.next()) {
					if (isEdit == true) {
						if (rs.getInt("reg_tindakan") == 1) {
							ischeck = true;
						} else {
							ischeck = false;
						}
					}
					tabModelTidakanRadiologi.addRow(
							new Object[] { ischeck, rs.getString(1), rs.getString(2), rs.getDouble("total_byr") });
				}
			} catch (Exception e) {
				System.out.println("Notifikasi 1 : " + e);
			} finally {
				if (rs != null) {
					rs.close();
				}
				pspemeriksaan.close();
			}
		} catch (Exception e) {
			System.out.println("Notifikasi 2 : " + e);
		}
	}
}
