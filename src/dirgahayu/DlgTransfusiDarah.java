package dirgahayu;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import fungsi.WarnaTable2;
import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPetugas;
import widget.Button;
import widget.ComboBox;
import widget.InternalFrame;
import widget.Label;
import widget.ScrollPane;
import widget.Table;
import widget.Tanggal;
import widget.TextBox;
import widget.panelisi;

public class DlgTransfusiDarah extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private InternalFrame internalFrame1;
	private panelisi panelGlassTop;
	private TextBox tNoRw;
	private TextBox tNoRM;
	private TextBox tPasien;
	private Tanggal DTPTgl;
	private TextBox tDokter;
	private TextBox tpoli;
	private TextBox tPenjab;
	private Button btnPetugas;
	private panelisi panelGlassMiddle;
	private JTabbedPane TabRawat;
	private ScrollPane Scroll1;
	private ScrollPane Scroll2;
	private Table tblInputDarah;
	private DefaultTableModel tabModelDarah;
	private TextBox tKd_pj;
	private TextBox tPetugas;
	private TextBox tJK;
	private ComboBox cmbGolRefuse;
	private Button BtnSimpan;
	private Button BtnBaru;
	private Button BtnHapus;
	private Button BtnAll;
	private ComboBox cmbGolDarah;
	private Button BtnKeluar;
	private Button BtnKeluar_1;
	private panelisi panelGlassMiddleTop;
	private panelisi panelGlassMiddleBottom;
	private Table tblDaftarDarah;
	private DefaultTableModel tabModelDaftarDarah;
	private TextBox tAsalLabu;
	private TextBox tNoPermintaan;
	private TextBox tUmur;
	public String jnsRawat;
	public boolean isEdit=false;
	private TextBox tkd_dokter;
	private Button btnDokter;
	private Button btnRawat;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgTransfusiDarah dialog = new DlgTransfusiDarah(new javax.swing.JFrame(), true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgTransfusiDarah(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initComponents();
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		internalFrame1 = new widget.InternalFrame();
		internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)),
				"::[ Transfusi Darah ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11),
				new java.awt.Color(50, 50, 50)));
		internalFrame1.setName("internalFrame1");
		internalFrame1.setMinimumSize(new Dimension(800, 600));
		internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

		panelGlassTop = new widget.panelisi();
		panelGlassTop.setPreferredSize(new java.awt.Dimension(800, 210));
		panelGlassTop.setLayout(null);

		Label jLabelNoRawat = new Label();
		jLabelNoRawat.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelNoRawat.setBounds(10, 19, 47, 14);
		jLabelNoRawat.setText("No Rawat");
		panelGlassTop.add(jLabelNoRawat);

		tNoRw = new TextBox();
		tNoRw.setBounds(99, 14, 140, 23);
		tNoRw.setEditable(false);
		tNoRw.setHighlighter(null);
		tNoRw.setName("TNoRw");
		tNoRw.setPreferredSize(new java.awt.Dimension(150, 23));
		panelGlassTop.add(tNoRw);

		JLabel jLabelNoRM = new Label();
		jLabelNoRM.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelNoRM.setBounds(470, 18, 78, 14);
		jLabelNoRM.setText("No Rekam Medik");
		panelGlassTop.add(jLabelNoRM);

		tNoRM = new TextBox();
		tNoRM.setBounds(560, 14, 111, 23);
		tNoRM.setEditable(false);
		tNoRM.setHighlighter(null);
		tNoRM.setName("TNoRM");
		tNoRM.setPreferredSize(new java.awt.Dimension(100, 23));
		panelGlassTop.add(tNoRM);

		Label jLabelPasien = new Label();
		jLabelPasien.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelPasien.setBounds(10, 45, 61, 14);
		jLabelPasien.setText("Nama Pasien");
		panelGlassTop.add(jLabelPasien);

		tPasien = new TextBox();
		tPasien.setEditable(false);
		tPasien.setBounds(99, 45, 185, 23);
		tPasien.setEditable(false);
		tPasien.setHighlighter(null);
		tPasien.setName("TPasien");
		tPasien.setPreferredSize(new java.awt.Dimension(210, 23));
		panelGlassTop.add(tPasien);

		Label jLabelTanggal = new Label();
		jLabelTanggal.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelTanggal.setBounds(10, 150, 41, 14);
		jLabelTanggal.setText("Tanggal ");
		panelGlassTop.add(jLabelTanggal);

		DTPTgl = new Tanggal();
		DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-09-2020" }));
		DTPTgl.setDisplayFormat("dd-MM-yyyy");
		DTPTgl.setBounds(99, 150, 90, 20);
		DTPTgl.setName("DTPTgl");
		DTPTgl.setEditable(false);
		panelGlassTop.add(DTPTgl);

		Label jLabelDokter = new Label();
		jLabelDokter.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelDokter.setBounds(10, 115, 62, 14);
		jLabelDokter.setText("Nama Dokter");
		panelGlassTop.add(jLabelDokter);

		tDokter = new TextBox();
		tDokter.setBounds(180, 115, 210, 24);
		tDokter.setEditable(false);
		tDokter.setHighlighter(null);
		panelGlassTop.add(tDokter);

		Label jLabelPoli = new Label();
		jLabelPoli.setText("Ruang Rawat");
		jLabelPoli.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelPoli.setBounds(470, 45, 65, 14);
		panelGlassTop.add(jLabelPoli);

		tpoli = new TextBox();
		tpoli.setBounds(560, 45, 120, 23);
		tpoli.setEditable(false);
		tpoli.setHighlighter(null);
		panelGlassTop.add(tpoli);

		Label jLabelpenjab = new Label();
		jLabelpenjab.setText("Penjamin");
		jLabelpenjab.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelpenjab.setBounds(470, 85, 43, 14);
		panelGlassTop.add(jLabelpenjab);

		tPenjab = new TextBox();
		tPenjab.setEditable(false);
		tPenjab.setHighlighter(null);
		tPenjab.setBounds(656, 77, 121, 23);
		panelGlassTop.add(tPenjab);
		internalFrame1.add(panelGlassTop, BorderLayout.NORTH);

		Label jLabelGolonganDarah = new Label();
		jLabelGolonganDarah.setText("Golongan Darah ");
		jLabelGolonganDarah.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelGolonganDarah.setBounds(10, 85, 87, 14);
		panelGlassTop.add(jLabelGolonganDarah);

		cmbGolDarah = new ComboBox();

		cmbGolDarah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "AB", "O" }));
		cmbGolDarah.setBounds(99, 80, 103, 22);
		panelGlassTop.add(cmbGolDarah);

		Label jLabelAsalLabu = new Label();
		jLabelAsalLabu.setText("Asal Labu");
		jLabelAsalLabu.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelAsalLabu.setBounds(470, 115, 65, 14);
		panelGlassTop.add(jLabelAsalLabu);

		Label jLabelResusDarah = new Label();
		jLabelResusDarah.setText("Resus Darah");
		jLabelResusDarah.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelResusDarah.setBounds(226, 85, 72, 14);
		panelGlassTop.add(jLabelResusDarah);

		cmbGolRefuse = new ComboBox();
		cmbGolRefuse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cmbGolRefuse.setBounds(312, 80, 78, 22);
		panelGlassTop.add(cmbGolRefuse);

		Label jLabelJk = new Label();
		jLabelJk.setText("JK");
		jLabelJk.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelJk.setBounds(290, 19, 28, 14);
		panelGlassTop.add(jLabelJk);

		tJK = new TextBox();
		tJK.setEditable(false);
		tJK.setBounds(323, 14, 78, 23);
		panelGlassTop.add(tJK);

		tAsalLabu = new TextBox();
		tAsalLabu.setBounds(560, 109, 121, 23);
		panelGlassTop.add(tAsalLabu);

		Label jLabelNomorPer = new Label();
		jLabelNomorPer.setText("No. Permintaan");
		jLabelNomorPer.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelNomorPer.setBounds(470, 148, 78, 14);
		panelGlassTop.add(jLabelNomorPer);

		tNoPermintaan = new TextBox();
		tNoPermintaan.setBounds(560, 140, 149, 23);
		panelGlassTop.add(tNoPermintaan);

		Label jLabelPetugas = new Label();
		jLabelPetugas.setText("Petugas");
		jLabelPetugas.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelPetugas.setBounds(468, 175, 78, 14);
		panelGlassTop.add(jLabelPetugas);

		tPetugas = new TextBox();
		tPetugas.setBounds(557, 170, 200, 24);
		panelGlassTop.add(tPetugas);

		btnPetugas = new Button();
		btnPetugas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DlgCariPetugas petugas = new DlgCariPetugas(null, false);
				sekuel Sequel = new sekuel();
				akses.setform("DlgTransfusiDarah");
				petugas.emptTeks();
				petugas.isCek();
				petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
				petugas.setLocationRelativeTo(internalFrame1);
				petugas.setVisible(true);	
				
				petugas.addWindowListener(new WindowListener() {

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						if (akses.getform().equals("DlgTransfusiDarah")) {
							if (petugas.getTable().getSelectedRow() != -1) {
								tPetugas.setText(
										petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());								
							}

						}
					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
			}
		});
		btnPetugas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
		btnPetugas.setMnemonic('4');
		btnPetugas.setBounds(762, 170, 28, 23);
		panelGlassTop.add(btnPetugas);

		tKd_pj = new TextBox();
		tKd_pj.setEditable(false);
		tKd_pj.setBounds(560, 77, 88, 23);
		panelGlassTop.add(tKd_pj);

		Label jlLabelumur = new Label();
		jlLabelumur.setText("Umur");
		jlLabelumur.setHorizontalAlignment(SwingConstants.LEFT);
		jlLabelumur.setBounds(289, 45, 28, 14);
		panelGlassTop.add(jlLabelumur);

		tUmur = new TextBox();
		tUmur.setEditable(false);
		tUmur.setBounds(323, 45, 103, 23);
		panelGlassTop.add(tUmur);
		
		tkd_dokter = new TextBox();
		tkd_dokter.setBounds(99, 115, 78, 24);
		panelGlassTop.add(tkd_dokter);
		
		btnDokter = new Button();
		btnDokter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DlgCariDokter dokter = new DlgCariDokter(null, false);
				akses.setform("DlgTransfusiDarah");
				dokter.emptTeks();
				dokter.isCek();
				dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
				dokter.setLocationRelativeTo(internalFrame1);
				dokter.setVisible(true);
				dokter.addWindowListener(new WindowListener() {

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						if (akses.getform().equals("DlgTransfusiDarah")) {
							if (dokter.getTable().getSelectedRow() != -1) {									
								tkd_dokter.setText(
											dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
								tDokter.setText(
											dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());																
							}
						}
					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
			}
		});
		btnDokter.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
		btnDokter.setMnemonic('4');
		btnDokter.setBounds(390, 115, 28, 23);
		panelGlassTop.add(btnDokter);
		
	    btnRawat = new Button();
	    btnRawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
	    btnRawat.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRawat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DlgCariReg reg = new DlgCariReg(null, false);
				sekuel Sequel = new sekuel();
				akses.setform("DlgTransfusiDarah");
				reg.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
				reg.setLocationRelativeTo(internalFrame1);
				reg.setVisible(true);
				reg.addWindowListener(new WindowListener() {

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						if (akses.getform().equals("DlgTransfusiDarah")) {
							if (reg.getTable().getSelectedRow() != -1) {
								if (Sequel.cariRegistrasi(
										reg.getTable().getValueAt(reg.getTable().getSelectedRow(), 1).toString()) > 0) {
									JOptionPane.showMessageDialog(rootPane,
											"Data billing sudah terverifikasi.\nSilahkan hubungi bagian kasir/keuangan ..!!");
								} else {
									tNoRw.setText(
											reg.getTable().getValueAt(reg.getTable().getSelectedRow(), 1).toString());
									SetNoRm(tNoRw.getText(),"Ralan");
									if (Sequel.cariInteger(
											"select count(pasien.no_rkm_medis) from pasien inner join reg_periksa inner join kamar_inap "
													+ "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat "
													+ "where kamar_inap.stts_pulang='-' and pasien.no_rkm_medis=?",
											reg.getTable().getValueAt(reg.getTable().getSelectedRow(), 6)
													.toString()) > 0) {
										JOptionPane.showMessageDialog(null,
												"Pasien sedang dalam masa perawatan di kamar inap..!!");
										tNoRw.setText("");
										tNoRM.setText("");
										tPasien.setText("");
										tNoRM.requestFocus();
									}
								}
							}
						}
					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub

					}
					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
					}
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
					}
					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
					}
				});
				reg.getTable().addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent e) {
					}
					@Override
					public void keyPressed(KeyEvent e) {
						if (akses.getform().equals("DlgTransfusiDarah")) {
							reg.dispose();
						}
					}
					@Override
					public void keyReleased(KeyEvent e) {
					}
				});
			}
		});
		btnRawat.setMnemonic('4');
		btnRawat.setBounds(245, 15, 28, 23);
		panelGlassTop.add(btnRawat);

		TabRawat = new javax.swing.JTabbedPane();
		TabRawat.setBackground(new java.awt.Color(255, 255, 253));
		TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
		TabRawat.setForeground(new java.awt.Color(50, 50, 50));
		TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
		TabRawat.setName("TabRawat"); // NOI18N
		TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				TabRawatMouseClicked(evt);
			}
		});

		Scroll1 = new widget.ScrollPane();
		Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
		Scroll1.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
		Scroll1.setName("Scroll1");
		Scroll1.setOpaque(true);

		// Table Input
		tblInputDarah = new widget.Table();
		tabModelDarah = new DefaultTableModel(null,
				new String[] { "", "Kode Jenis Darah", "Nomor Kantong", "Jumlah", "Jenis Darah", "Tarif" }) {
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				boolean a = false;
				if ((colIndex == 0 || colIndex == 2 || colIndex == 3)) {
					a = true;
				}
				return a;
			}

			Class[] types = new Class[] { java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Integer.class, java.lang.Object.class, java.lang.Double.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		tblInputDarah.setModel(tabModelDarah);
		tblInputDarah.setPreferredScrollableViewportSize(new Dimension(500, 300));
		tblInputDarah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < tabModelDarah.getColumnCount(); i++) {
			TableColumn column = tblInputDarah.getColumnModel().getColumn(i);
			if (i == 1) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else if (i == 0) {
				column.setMaxWidth(30);
			}
			else if (i == 5) {
				DefaultTableCellRenderer RightRenderer = new DefaultTableCellRenderer();
				RightRenderer.setHorizontalAlignment(JLabel.RIGHT);
				column.setCellRenderer(RightRenderer);
			}
		}
		WarnaTable2 warna = new WarnaTable2();
		warna.kolom = 1;
		tblInputDarah.setDefaultRenderer(Object.class, warna);
		tblInputDarah.setPreferredScrollableViewportSize(new Dimension(800, 300));
		tblInputDarah.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		Scroll1.setViewportView(tblInputDarah);
		TabRawat.addTab("Input Labu Darah", Scroll1);

		Scroll2 = new widget.ScrollPane();
		Scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
		Scroll2.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
		Scroll2.setName("Scroll1");
		Scroll2.setOpaque(true);
		TabRawat.addTab("Daftar Labu Darah", Scroll2);
		internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);
//		panelGlassMiddle.add(TabRawat);
		tblDaftarDarah = new widget.Table();
		tblDaftarDarah.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tblDaftarDarahMouseClicked(e);
			}
		});
		tabModelDaftarDarah = new DefaultTableModel(null,
				new String[] { "", "No Permintaan", "Nama Pasien","Ruang Rawat","Penjamin","Total Bayar","Status Bayar","kode" }) {
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				boolean a = false;
				if(colIndex == 0 ) {
					a=true;
				}
				return a;
			}
			Class[] types = new Class[] { java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
					 java.lang.Object.class,java.lang.Object.class,java.lang.Object.class, java.lang.Object.class,
					 java.lang.Object.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
			
		};
		tblDaftarDarah.setModel(tabModelDaftarDarah);

		tblDaftarDarah.setPreferredScrollableViewportSize(new Dimension(800, 400));
		tblDaftarDarah.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		for (int i = 0; i < tabModelDaftarDarah.getColumnCount(); i++) {
			TableColumn column = tblDaftarDarah.getColumnModel().getColumn(i);
			if (i == 1||i==7) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} 
		}
		WarnaTable2 warna2 = new WarnaTable2();
		warna.kolom = 1;
		tblDaftarDarah.setDefaultRenderer(Object.class, warna2);

		Scroll2.setViewportView(tblDaftarDarah);

		panelGlassMiddle = new widget.panelisi();
		panelGlassMiddle.setPreferredSize(new java.awt.Dimension(800, 90));
		panelGlassMiddle.setLayout(null);
		internalFrame1.add(panelGlassMiddle, java.awt.BorderLayout.SOUTH);

		panelGlassMiddleTop = new widget.panelisi();
		panelGlassMiddleTop.setSize(800, 50);
		panelGlassMiddleTop.setLocation(1, 1);
		panelGlassMiddleTop.setPreferredSize(new java.awt.Dimension(800, 90));
		panelGlassMiddleBottom = new widget.panelisi();

		panelGlassMiddle.add(panelGlassMiddleTop, java.awt.BorderLayout.SOUTH);
		panelGlassMiddle.add(panelGlassMiddleBottom, java.awt.BorderLayout.NORTH);
		panelGlassMiddleTop.setLayout(null);

		BtnSimpan = new Button();
		BtnSimpan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isEdit==false) {
				BtnSimpanActionPerformed(e);
				}else {
				  BtnUpdateActionPerformed(e);
				}
			}
		});
		BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png")));
		BtnSimpan.setMnemonic('S');
		BtnSimpan.setText("Simpan");
		BtnSimpan.setBounds(1, 10, 100, 30);
		panelGlassMiddleTop.add(BtnSimpan);

		BtnBaru = new Button();
		BtnBaru.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BtnNewActionPerformed(e);
			}
		});
		BtnBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png")));
		BtnBaru.setMnemonic('B');
		BtnBaru.setText("Baru");
		BtnBaru.setToolTipText("Alt+B");
		BtnBaru.setName("BtnBatal");
		BtnBaru.setBounds(111, 10, 100, 30);
		panelGlassMiddleTop.add(BtnBaru);

		BtnHapus = new Button();
		BtnHapus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BtnDeleteActionPerformed(e);
			}
		});
		BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png")));
		BtnHapus.setMnemonic('H');
		BtnHapus.setText("Hapus");
		BtnHapus.setToolTipText("Alt+H");
		BtnHapus.setName("BtnHapus");
		BtnHapus.setBounds(221, 10, 100, 30);
		panelGlassMiddleTop.add(BtnHapus);

		BtnAll = new Button();
		BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png")));
		BtnAll.setMnemonic('M');
		BtnAll.setText("Semua");
		BtnAll.setToolTipText("Alt+M");
		BtnAll.setName("BtnAll");
		BtnAll.setBounds(486, 36, 100, 30);
		// panelGlassMiddle.add(BtnAll);

		BtnKeluar = new widget.Button();
		BtnKeluar = new Button();
		BtnKeluar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				petugas.dispose();
//				dokter.dispose();
//				pasien.dispose();				
				dispose();
			}
		});
		BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png")));
		BtnKeluar.setMnemonic('M');
		BtnKeluar.setText("Keluar");
		BtnKeluar.setToolTipText("Alt+M");
		BtnKeluar.setName("BtnAll");
		BtnKeluar.setBounds(331, 10, 100, 30);
		panelGlassMiddleTop.add(BtnKeluar);
		getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);
		pack();

	}

	

	protected void BtnDeleteActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		sekuel Sequel = new sekuel();
		validasi Valid = new validasi();
		if (tabModelDaftarDarah.getRowCount() != 0) {	
			if (tblDaftarDarah.getSelectedRow() != -1) {				
					if (tblDaftarDarah.getValueAt(tblDaftarDarah.getSelectedRow(), 0).toString().equals("true")) {
						if (tblDaftarDarah.getValueAt(tblDaftarDarah.getSelectedRow(), 0).toString().equals("Lunas")) {
							JOptionPane.showMessageDialog(rootPane,
									"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");						
						}else {
							int jawab = JOptionPane.showConfirmDialog(null, "Apakah anda yakin menghapus data ini?",
									"Konfirmasi", JOptionPane.YES_NO_OPTION);
							if (jawab == JOptionPane.YES_OPTION) {
							Sequel.meghapus("tindakan_darah","nomor_permintaan",tNoPermintaan.getText());
							Sequel.meghapus("detail_tindakan_darah","nomor_permintaan",tNoPermintaan.getText());
							JOptionPane.showMessageDialog(rootPane,
									"Data Berhasil Dihapus");
							tampilDataPemakaianDarah();
							}
						}
					}				
			}			
		}else {
			
		}
	}

	protected void BtnUpdateActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		sekuel Sequel = new sekuel();
		validasi Valid = new validasi();
		Sequel.AutoComitFalse();
		boolean sukses = true;
		double total=0;
		if (tblDaftarDarah.getSelectedRow() != -1) {	
			Sequel.meghapus("detail_tindakan_darah","nomor_permintaan",tNoPermintaan.getText());
			for (int i = 0; i < tblInputDarah.getRowCount(); i++) {
				if (!tblInputDarah.getValueAt(i, 2).equals("") && !tblInputDarah.getValueAt(i, 3).equals("")) {
					total+=Double.parseDouble(tblInputDarah.getValueAt(i, 5).toString());
					if (Sequel.menyimpanNew("detail_tindakan_darah",
							"nomor_permintaan,kd_prw,nomo_kantong,jumlah,harga,tanggal_beri",
							"'" + tNoPermintaan.getText() + "','" + tblInputDarah.getValueAt(i, 1) + "','"
									+ tblInputDarah.getValueAt(i, 2) + "','" + tblInputDarah.getValueAt(i, 3) + "','"
									+ tblInputDarah.getValueAt(i, 5) + "','"
									+ Valid.SetTgl(DTPTgl.getSelectedItem().toString()) + "'",
							"Detail Tindakan transfusi Darah") == true) {
					} else {
						sukses = false;
					}
				}
			}
			if (sukses == true) {				
				Sequel.mengedit("tindakan_darah", "nomor_permintaan='"+tNoPermintaan.getText()+"'", "asal_labu='"+tAsalLabu.getText()+"',golongan_darah='"+cmbGolDarah.getSelectedItem().toString()+"',resus='"+cmbGolRefuse.getSelectedItem().toString()+"',total_biaya='"+String.valueOf(total)+"',kd_dokter='"+tkd_dokter.getText()+"',ruang='"+tpoli.getText()+"',kd_petugas='"+tpoli.getText()+"'");
				Sequel.Commit();
				JOptionPane.showMessageDialog(rootPane, " Berhasil diperbaharui");
				emptTeks();
				tampilDataPemakaianDarah();
			}else {
				Sequel.RollBack();
			}
			Sequel.AutoComitTrue();
		}
	}

	protected void BtnNewActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		emptTeks();
	}

	

	protected void BtnSimpanActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		sekuel Sequel = new sekuel();
		validasi Valid = new validasi();
		Sequel.AutoComitFalse();
		boolean sukses = true;
		double total=0;
		if (tNoRw.getText().equals("")||tPasien.getText().equals("")) {
			Valid.textKosong(tNoRw,"pasien");
		} else {
			for (int i = 0; i < tblInputDarah.getRowCount(); i++) {
				if (!tblInputDarah.getValueAt(i, 2).equals("") && !tblInputDarah.getValueAt(i, 3).equals("")) {
					total+=Double.parseDouble(tblInputDarah.getValueAt(i, 5).toString());
					if (Sequel.menyimpanNew("detail_tindakan_darah",
							"nomor_permintaan,kd_prw,nomo_kantong,jumlah,harga,tanggal_beri",
							"'" + tNoPermintaan.getText() + "','" + tblInputDarah.getValueAt(i, 1) + "','"
									+ tblInputDarah.getValueAt(i, 2) + "','" + tblInputDarah.getValueAt(i, 3) + "','"
									+ tblInputDarah.getValueAt(i, 5) + "','"
									+ Valid.SetTgl(DTPTgl.getSelectedItem().toString()) + "'",
							"Detail Tindakan transfusi Darah") == true) {
					} else {
						sukses = false;
					}
				}
			}
			if (sukses == true) {
				Sequel.menyimpanNew("tindakan_darah","nomor_permintaan,no_rm,no_rawat,asal_labu,golongan_darah,resus,total_biaya,status_perawatan,kd_dokter,ruang,kd_petugas","'"+tNoPermintaan.getText()+"','"+tNoRM.getText()+"','"+tNoRw.getText()+"','"+tAsalLabu.getText()+"','"+cmbGolDarah.getSelectedItem().toString()+"','"+cmbGolRefuse.getSelectedItem().toString()+"','"+String.valueOf(total)+"','"+jnsRawat+"','"+tkd_dokter.getText()+"','"+tpoli.getText()+"','"+tPetugas.getText()+"'","Tindakan Darah");
				Sequel.Commit();
				JOptionPane.showMessageDialog(rootPane, " Berhasil disimpan");
				emptTeks();
				tampilDataPemakaianDarah();
			}else {
				Sequel.RollBack();
			}
			Sequel.AutoComitTrue();
		}
	}

	protected void TabRawatMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		switch (TabRawat.getSelectedIndex()) {
			case 0:
				tampildataDarah();
				break;
			case 1:
				tampilDataPemakaianDarah();
				break;
		}
		
	}

	public void SetNoRm(String noRawat, String jnsRawat) {
		sekuel Sequel = new sekuel();
		String noRekamMedik = Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=?", noRawat);
		String tgl_registrasi = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", noRawat);

		tNoRw.setText(noRawat);
		tNoRM.setText(noRekamMedik);
		tPasien.setText(Sequel.cariIsi(
				"select nm_pasien from reg_periksa Inner join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where no_rawat=?",
				noRawat));
		tUmur.setText(Sequel.cariIsi("select umur from reg_periksa inner join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where no_rawat='"+noRawat+"'"));
		tDokter.setText(Sequel.cariIsi(
				"select nm_dokter from reg_periksa Inner join dokter on dokter.kd_dokter=reg_periksa.kd_dokter where no_rawat=?",
				noRawat));
		tkd_dokter.setText(Sequel.cariIsi(
				"select dokter.kd_dokter from reg_periksa Inner join dokter on dokter.kd_dokter=reg_periksa.kd_dokter where no_rawat=?",
				noRawat));
	
		tPenjab.setText(Sequel.cariIsi(
				"select png_jawab from reg_periksa Inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where no_rawat=?",
				noRawat));
		tKd_pj.setText(Sequel.cariIsi(
				"select reg_periksa.kd_pj from reg_periksa Inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where no_rawat=?",
				noRawat));
		validasi Valid = new validasi();
		Valid.SetTgl(DTPTgl, tgl_registrasi);
		if (jnsRawat.equals("Ralan")) {
			Sequel.cariIsi(
					"Select nm_poli from poliklinik inner join reg_periksa on reg_periksa.kd_poli=poliklinik.kd_poli where no_rawat='"
							+ noRawat + "'",
					tpoli);
		} else {
			Sequel.cariIsi(
					"Select nm_bangsal from bangsal inner join kamar on kamar.kd_bangsal=bangsal.kd_bangsal inner join kamar_inap on kamar_inap.kd_kamar=kamar.kd_kamar where no_rawat='"
							+ noRawat + "'",
					tpoli);
		}
		Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'", tPetugas);

		tJK.setText(Sequel.cariIsi("Select jk from pasien where no_rkm_medis=?", noRekamMedik));
		this.jnsRawat=jnsRawat;
		tampildataDarah();
	}
	private void emptTeks() {
		// TODO Auto-generated method stub
		tNoRw.setText("");
		tNoPermintaan.setText("");
		tAsalLabu.setText("");
		tNoRM.setText("");
		tUmur.setText("");
		tPasien.setText("");
		tDokter.setText("");
		tkd_dokter.setText("");
		tPenjab.setText("");
		tKd_pj.setText("");
		cmbGolDarah.setSelectedIndex(0);
		cmbGolRefuse.setSelectedIndex(0);
		isEdit=false;
		BtnSimpan.setText("Simpan");
		DTPTgl.setDate(new Date());
		for (int i = 0; i < tblInputDarah.getRowCount(); i++) {
			tblInputDarah.setValueAt("", i, 2);
			tblInputDarah.setValueAt("0", i, 3);
		}
	}
	private void tampildataDarah() {
		// TODO Auto-generated method stub
		validasi Valid = new validasi();
		Valid.tabelKosong(tabModelDarah);
		PreparedStatement ps;
		Connection koneksi = koneksiDB.condb();
		ResultSet rs;
		try {
			ps = koneksi.prepareStatement(
					"select jns_perawatan_utd.kd_jenis_prw,jns_perawatan_utd.nm_perawatan,jns_perawatan_utd.bagian_rs,jns_perawatan_utd.bhp,"
							+ "jns_perawatan_utd.tarif_perujuk,jns_perawatan_utd.tarif_tindakan_dokter,jns_perawatan_utd.tarif_tindakan_petugas,"
							+ "jns_perawatan_utd.kso,jns_perawatan_utd.manajemen,jns_perawatan_utd.total_byr,"
							+ "penjab.png_jawab from jns_perawatan_utd inner join penjab on penjab.kd_pj=jns_perawatan_utd.kd_pj "
							+ "where jns_perawatan_utd.status='1' and jns_perawatan_utd.kd_pj='" + tKd_pj.getText()
							+ "'  order by jns_perawatan_utd.kd_jenis_prw");
			rs = ps.executeQuery();
			while (rs.next()) {
				tabModelDarah.addRow(new Object[] { false, rs.getString("kd_jenis_prw"), "", "0",
						rs.getString("nm_perawatan"), rs.getDouble("total_byr") });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void tampilDataPemakaianDarah() {
		sekuel Sequel = new sekuel();
		validasi Valid = new validasi();
		Valid.tabelKosong(tabModelDaftarDarah);
		PreparedStatement ps;
		Connection koneksi = koneksiDB.condb();
		ResultSet rs;
		try {
			ps = koneksi.prepareStatement("select id,nomor_permintaan,no_rm,no_rawat,total_biaya,status_bayar from tindakan_darah where status_perawatan='"+jnsRawat+"'");
			rs = ps.executeQuery();
			while (rs.next()) {
				String namaPasien=Sequel.cariIsi("Select nm_pasien from pasien where no_rkm_medis='"+rs.getString("no_rm")+"'");
				String RuangRawat=Sequel.cariIsi(
						"select nm_poli from reg_periksa Inner join poliklinik on poliklinik.kd_poli=reg_periksa.kd_poli where no_rawat=?",
						rs.getString("no_rawat"));
				String Penjamin=Sequel.cariIsi(
						"select png_jawab from reg_periksa Inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where no_rawat=?",
						rs.getString("no_rawat"));
				tabModelDaftarDarah.addRow(new Object[] { false,rs.getString("nomor_permintaan"),namaPasien,RuangRawat,Penjamin,rs.getString("total_biaya"),rs.getString("status_bayar"),rs.getString("id") });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void tblDaftarDarahMouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		sekuel Sequel = new sekuel();
		validasi Valid = new validasi();
		if (tabModelDaftarDarah.getRowCount() != 0) {			
			if (tblDaftarDarah.getSelectedRow() != -1) {
				emptTeks();
				this.isEdit=true;
				BtnSimpan.setText("Ubah");
				String noPermintaan=tblDaftarDarah.getValueAt(tblDaftarDarah.getSelectedRow(), 1).toString();
				String noRawat=Sequel.cariIsi("Select no_rawat from tindakan_darah where nomor_permintaan='"+noPermintaan+"'");
				String kd_dokter=Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='"+noRawat+"'");
				tUmur.setText(Sequel.cariIsi("select umur from reg_periksa inner join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where no_rawat='"+noRawat+"'"));
				tNoPermintaan.setText(noPermintaan);
				tPasien.setText(tblDaftarDarah.getValueAt(tblDaftarDarah.getSelectedRow(), 2).toString());
				tNoRw.setText(noRawat);
				tNoRM.setText(Sequel.cariIsi("Select no_rm from tindakan_darah where nomor_permintaan='"+noPermintaan+"'"));
				tAsalLabu.setText(Sequel.cariIsi("Select asal_labu from tindakan_darah where nomor_permintaan='"+noPermintaan+"'"));
				tDokter.setText(Sequel.cariIsi("Select nm_dokter from dokter where kd_dokter='"+kd_dokter+"'"));
				tkd_dokter.setText(kd_dokter);
				cmbGolDarah.setSelectedItem(Sequel.cariIsi("Select golongan_darah from tindakan_darah where nomor_permintaan='"+noPermintaan+"'"));
				cmbGolRefuse.setSelectedItem(Sequel.cariIsi("Select resus from tindakan_darah where nomor_permintaan='"+noPermintaan+"'"));
				Valid.SetTgl(DTPTgl,Sequel.cariIsi("Select tanggal_beri from detail_tindakan_darah where nomor_permintaan='"+noPermintaan+"'"));
				tPenjab.setText(Sequel.cariIsi(
						"select png_jawab from reg_periksa Inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where no_rawat=?",
						noRawat));
				tKd_pj.setText(Sequel.cariIsi(
						"select reg_periksa.kd_pj from reg_periksa Inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where no_rawat=?",
						noRawat));
				
				PreparedStatement ps;
				Connection koneksi = koneksiDB.condb();
				ResultSet rs;
				for (int i = 0; i < tblInputDarah.getRowCount(); i++) {
					try {
						ps = koneksi.prepareStatement("select kd_prw,nomo_kantong,jumlah from detail_tindakan_darah where nomor_permintaan='"+noPermintaan+"'");
						rs = ps.executeQuery();
						while (rs.next()) {
							if(tblInputDarah.getValueAt(i, 1).equals(rs.getString("kd_prw"))) {
								tblInputDarah.setValueAt(rs.getString("nomo_kantong"), i, 2);
								tblInputDarah.setValueAt(rs.getString("jumlah"), i, 3);								
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}
				if(tblDaftarDarah.getValueAt(tblDaftarDarah.getSelectedRow(),0).toString().equals("true")) {
					tblDaftarDarah.setValueAt(true,tblDaftarDarah.getSelectedRow(), 0);
				}else {
				TabRawat.setSelectedIndex(0);
				}
			}
		}				
	}
}
