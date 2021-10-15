package keuangan;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dirgahayu.DlgCariReg;
import fungsi.ColorTable;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import widget.Button;
import widget.ComboBox;
import widget.InternalFrame;
import widget.Label;
import widget.ScrollPane;
import widget.Table;
import widget.Tanggal;
import widget.TextArea;
import widget.TextBox;
import widget.panelisi;

public class DlgKasirRalanNew extends javax.swing.JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			DlgKasirRalanNew dialog = new DlgKasirRalanNew(new javax.swing.JFrame(), true);
			dialog.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent e) {
					System.exit(0);
				}
			});
			dialog.setVisible(true);
		});
	}

	private InternalFrame internalFrame1;
	private panelisi panelGlassBottom;
	private panelisi panelGlassTop;
	private JTabbedPane TabKasir;

	private Label jLabelNoRawat;
	private TextBox tNoRw;
	private TextBox tNoRM;
	private TextBox tPasien;
	private Label jLabelTanggal;
	private Tanggal DTPTgl;
	private ScrollPane ScrollTabs;
	private Table tbBilling;
	private panelisi panelPermintaan;
	private Label jLabelNoRM;
	private TextBox tDokter;
	private TextBox tpoli;
	private TextBox tPenjab;
	private DefaultTableModel tabelModelBilling;
	
	/**
	 * Create the dialog.
	 * 
	 * @param b
	 * @param jFrame
	 */
	private int i;
	private int tabelRowTindakan = 0;
	private Button BtnKeluar;
	private TextArea tAlamatPasien;
	private double JmlBayar;
	private panelisi panelDatBilling;
	private double totalBayar;
	private ComboBox comboBox;
	private panelisi panelDataTableBilling;
	private JCheckBox cbxTindakan;
	private JCheckBox cbxObat;
	private AbstractButton chckbxRadiologi;
	private JCheckBox chckbxLaboratorium;
	private JRadioButton rdbtnCash;
	private JRadioButton rdbtnPiutang;
	private ButtonGroup btnGrp;
	private TextBox tKd_pj;
	private ColorTable renderer;
	private double margin = 0;
	private Button MnTambahan;
	private JPopupMenu jPopupMenu1;
	private InternalFrame internalFrame4;
	private JDialog WindowTambahanBiaya;
	private ScrollPane scrollPane1;
	private Table tbTambahan;
	private panelisi panelisi1;
	private TextBox norawat;
	private Button BtnTambah;
	private Button BtnSimpan3;
	private Button BtnHapus;
	private Button BtnKeluar1;
	private DefaultTableModel tabModeTambahan;

	public DlgKasirRalanNew(java.awt.Frame parent, boolean modal) {
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
				"::[ Billing/Pembayaran Ralan Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11),
				new java.awt.Color(50, 50, 50))); // NOI18N
		internalFrame1.setName("internalFrame1");
		internalFrame1.setMinimumSize(new Dimension(800, 600));
		internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));
		panelGlassTop = new widget.panelisi();
		panelGlassBottom = new widget.panelisi();
		panelGlassBottom.setPreferredSize(new java.awt.Dimension(55, 55));
		panelGlassBottom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));
//		End Initial compoent
		panelGlassTop.setPreferredSize(new java.awt.Dimension(800, 210));
//		Start Initial compenent panelTop

		internalFrame1.add(panelGlassTop, java.awt.BorderLayout.PAGE_START);
//		End component panel Top
//		Start initial Tab
		TabKasir = new javax.swing.JTabbedPane();
		TabKasir.setBackground(new java.awt.Color(255, 255, 253));
		TabKasir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
		TabKasir.setForeground(new java.awt.Color(50, 50, 50));
		TabKasir.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
		TabKasir.setName("TabKasir");
		panelDatBilling = new panelisi();
		panelDatBilling.setLayout(new java.awt.BorderLayout(1, 2));

//		Right Click
		jPopupMenu1 = new javax.swing.JPopupMenu();
		jPopupMenu1.setName("jPopupMenu1");

		MnTambahan = new widget.Button();
		MnTambahan.setVisible(akses.gettambahan_biaya());
		MnTambahan.setBackground(new java.awt.Color(255, 255, 254));
		MnTambahan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
		MnTambahan.setForeground(new java.awt.Color(50, 50, 50));
		MnTambahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
		MnTambahan.setText("Tambahan Biaya");
		MnTambahan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		MnTambahan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		MnTambahan.setName("MnTambahan"); // NOI18N
		MnTambahan.setPreferredSize(new java.awt.Dimension(250, 25));
		MnTambahan.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				MnTambahanActionPerformed(evt);
			}
		});
		jPopupMenu1.add(MnTambahan);

		panelDataTableBilling = new panelisi();
		panelDataTableBilling.setPreferredSize(new java.awt.Dimension(800, 350));

		ScrollTabs = new widget.ScrollPane();
		ScrollTabs.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollTabs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
		ScrollTabs.setName("ScrollTabs");
		ScrollTabs.setPreferredSize(new java.awt.Dimension(800, 300));
		ScrollTabs.setOpaque(true);
		ScrollTabs.setComponentPopupMenu(jPopupMenu1);

		TabKasir.addTab("Data Tagihan", panelDatBilling);
		panelDataTableBilling.setLayout(new GridLayout(0, 1, 0, 10));

		tbBilling = new widget.Table();
		tbBilling.setToolTipText("");
		tbBilling.setName("tbBilling");
		tbBilling.setComponentPopupMenu(jPopupMenu1);
//		panelDataTableBilling.add(tbBilling);
		panelDataTableBilling.add(ScrollTabs);
		panelDatBilling.add(panelDataTableBilling, java.awt.BorderLayout.PAGE_START);
		tabelModelBilling = new DefaultTableModel(null, new Object[] { "Deskripsi", "Jumlah", "Jumlah Biaya", "Dijamin",
				"Potongan", "Jumlah di Bayar", "Status Bayar", "", "" }) {
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				boolean a = false;
				if ((colIndex > 2) && (colIndex < 5)) {
					a = true;
				}
				return a;
			}

			Class[] types = new Class[] { java.lang.Object.class, java.lang.Double.class, java.lang.Object.class,
					java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		tbBilling.setModel(tabelModelBilling);
		tbBilling.setPreferredScrollableViewportSize(new Dimension(800, 300));
		tbBilling.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		for (i = 0; i < tabelModelBilling.getColumnCount(); i++) {
			TableColumn column = tbBilling.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(180);

			} else if (i == 7) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else if (i == 8) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else {
				column.setPreferredWidth(60);
				column.setMinWidth(100);
				column.setMaxWidth(100);
			}
		}
		renderer = new ColorTable();
		tbBilling.setDefaultRenderer(Object.class, renderer);
		tbBilling.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			@Override
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				countPropertyBilling(evt);
			}
		});
		tbBilling.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				countBilling(evt);
			}
		});
		tbBilling.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				countBillingKeyPressed(evt);
			}
		});
		ScrollTabs.setViewportView(tbBilling);

		panelDataTotalBilling = new panelisi();
		panelDataTotalBilling.setLayout(null);
		panelDataTotalBilling.setPreferredSize(new java.awt.Dimension(800, 100));
		panelDataTableBilling.add(panelDataTotalBilling);

		lblTotalTagihan = new JLabel("Total Tagihan");
		lblTotalTagihan.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalTagihan.setBounds(10, 27, 96, 21);
		panelDataTotalBilling.add(lblTotalTagihan);

		lblTotalDijamin = new JLabel("Total Dijamin");
		lblTotalDijamin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalDijamin.setBounds(332, 26, 96, 21);
		panelDataTotalBilling.add(lblTotalDijamin);

		lblTotalTagihan_1 = new JLabel("Total Discount");
		lblTotalTagihan_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalTagihan_1.setBounds(657, 25, 96, 21);
		panelDataTotalBilling.add(lblTotalTagihan_1);

		lblTotalTagihan_2 = new JLabel("Total Dibayar");
		lblTotalTagihan_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalTagihan_2.setBounds(10, 66, 96, 21);
		panelDataTotalBilling.add(lblTotalTagihan_2);

		lblTotalTagihan_3 = new JLabel("Kembali");
		lblTotalTagihan_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalTagihan_3.setBounds(332, 67, 96, 21);
		panelDataTotalBilling.add(lblTotalTagihan_3);

		tTagihan = new TextBox();
		tTagihan.setBounds(116, 26, 198, 23);
		tTagihan.setFont(new Font("Tahoma", Font.BOLD, 14));
		tTagihan.setEditable(false);
		tTagihan.setText("0.0");
		panelDataTotalBilling.add(tTagihan);

		tInsurance = new TextBox();
		tInsurance.setBounds(438, 25, 198, 23);
		tInsurance.setFont(new Font("Tahoma", Font.BOLD, 14));
		tInsurance.setEditable(false);
		panelDataTotalBilling.add(tInsurance);

		tDiscount = new TextBox();
		tDiscount.setBounds(763, 24, 198, 23);
		tDiscount.setFont(new Font("Tahoma", Font.BOLD, 14));
		tDiscount.setEditable(false);
		panelDataTotalBilling.add(tDiscount);

		tPayment = new TextBox();
		tPayment.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					getCountPayement();
				}
			}
		});
		tPayment.setBounds(116, 65, 198, 23);
		tPayment.setFont(new Font("Tahoma", Font.BOLD, 14));
		tPayment.setText("0.0");

		panelDataTotalBilling.add(tPayment);

		tPayback = new TextBox();
		tPayback.setBounds(438, 66, 198, 23);
		tPayback.setFont(new Font("Tahoma", Font.BOLD, 14));
		tPayback.setEditable(false);
		tPayback.setText("0.0");
		panelDataTotalBilling.add(tPayback);

//       End Tabele data billing 
		panelPermintaan = new widget.panelisi();
		panelPermintaan.setBorder(null);
		panelPermintaan.setName("panelPermintaan");
		panelPermintaan.setPreferredSize(new java.awt.Dimension(100, 137));
		panelPermintaan.setLayout(new java.awt.GridLayout(3, 0));

//		TabKasir.addTab("Status Permintaan", panelPermintaan);
//        end Permintaan
		internalFrame1.add(TabKasir, java.awt.BorderLayout.CENTER);
		panelGlassTop.setLayout(null);

//		end inital Tab

		jLabelNoRawat = new Label();
		jLabelNoRawat.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelNoRawat.setBounds(10, 11, 78, 23);
		jLabelNoRawat.setText("No Registrasi");
		panelGlassTop.add(jLabelNoRawat);

		tNoRw = new TextBox();
		tNoRw.setBounds(131, 10, 230, 23);
		tNoRw.setEditable(false);
		tNoRw.setHighlighter(null);
		tNoRw.setName("TNoRw");
		tNoRw.setPreferredSize(new java.awt.Dimension(150, 23));
		panelGlassTop.add(tNoRw);

		jLabelNoRM = new Label();
		jLabelNoRM.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelNoRM.setBounds(10, 45, 78, 23);
		jLabelNoRM.setText("No Rekam Medik");
		panelGlassTop.add(jLabelNoRM);

		tNoRM = new TextBox();
		tNoRM.setBounds(131, 44, 230, 23);
		tNoRM.setEditable(false);
		tNoRM.setHighlighter(null);
		tNoRM.setName("TNoRM");
		tNoRM.setPreferredSize(new java.awt.Dimension(100, 23));
		panelGlassTop.add(tNoRM);

		Label jLabelPasien = new Label();
		jLabelPasien.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelPasien.setBounds(10, 79, 78, 23);
		jLabelPasien.setText("Nama Pasien");
		panelGlassTop.add(jLabelPasien);

		tPasien = new TextBox();
		tPasien.setBounds(131, 78, 230, 23);
		tPasien.setEditable(false);
		tPasien.setHighlighter(null);
		tPasien.setName("TPasien");
		tPasien.setPreferredSize(new java.awt.Dimension(210, 23));
		panelGlassTop.add(tPasien);

		jLabelTanggal = new Label();
		jLabelTanggal.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelTanggal.setBounds(421, 113, 41, 23);
		jLabelTanggal.setText("Tanggal ");
		panelGlassTop.add(jLabelTanggal);

		DTPTgl = new Tanggal();
		DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-09-2020" }));
		DTPTgl.setDisplayFormat("dd-MM-yyyy");
		DTPTgl.setBounds(542, 113, 139, 23);
		DTPTgl.setName("DTPTgl");
		DTPTgl.setEditable(false);
		panelGlassTop.add(DTPTgl);

		Label jLabelDokter = new Label();
		jLabelDokter.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelDokter.setBounds(421, 11, 78, 23);
		jLabelDokter.setText("Nama Dokter");
		panelGlassTop.add(jLabelDokter);

		tDokter = new TextBox();
		tDokter.setBounds(542, 11, 198, 23);
		tDokter.setEditable(false);
		tDokter.setHighlighter(null);
		panelGlassTop.add(tDokter);

		Label jLabelPoli = new Label();
		jLabelPoli.setText("Ruang Rawat");
		jLabelPoli.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelPoli.setBounds(421, 44, 78, 23);
		panelGlassTop.add(jLabelPoli);

		tpoli = new TextBox();
		tpoli.setBounds(542, 44, 198, 23);
		tpoli.setEditable(false);
		tpoli.setHighlighter(null);
		panelGlassTop.add(tpoli);

		Label jLabelpenjab = new Label();
		jLabelpenjab.setText("Penjamin");
		jLabelpenjab.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelpenjab.setBounds(421, 79, 78, 23);
		panelGlassTop.add(jLabelpenjab);

		tPenjab = new TextBox();
		tPenjab.setEditable(false);
		tPenjab.setHighlighter(null);
		tPenjab.setBounds(640, 78, 139, 23);
		panelGlassTop.add(tPenjab);

		Label jLabelAlamatPasien = new Label();
		jLabelAlamatPasien.setText("Alamat Pasien");
		jLabelAlamatPasien.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelAlamatPasien.setBounds(10, 113, 78, 23);
		panelGlassTop.add(jLabelAlamatPasien);

		tAlamatPasien = new TextArea();
		tAlamatPasien.setBounds(131, 112, 230, 44);
		tAlamatPasien.setColumns(20);
		tAlamatPasien.setRows(5);
		tAlamatPasien.setEditable(false);
		panelGlassTop.add(tAlamatPasien);

		Button BtnReg = new Button();
		BtnReg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DlgCariReg reg = new DlgCariReg(null, false);
				sekuel Sequel = new sekuel();
				akses.setform("DlgKasirRalanNew");
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
						if (akses.getform().equals("DlgKasirRalanNew")) {
							if (reg.getTable().getSelectedRow() != -1) {
								if (Sequel.cariRegistrasi(
										reg.getTable().getValueAt(reg.getTable().getSelectedRow(), 1).toString()) > 0) {
									JOptionPane.showMessageDialog(rootPane,
											"Data billing sudah terverifikasi.\nSilahkan hubungi bagian kasir/keuangan ..!!");
									// TCari.requestFocus();
								} else {
									tNoRw.setText(
											reg.getTable().getValueAt(reg.getTable().getSelectedRow(), 1).toString());
//			                            tNoRM.setText(reg.getTable().getValueAt(reg.getTable().getSelectedRow(),6).toString());
//			                            tPasien.setText(reg.getTable().getValueAt(reg.getTable().getSelectedRow(),7).toString());
									SetNoRm(tNoRw.getText());
									if (Sequel.cariInteger(
											"select count(pasien.no_rkm_medis) from pasien inner join reg_periksa inner join kamar_inap "
													+ "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat "
													+ "where kamar_inap.stts_pulang='-' and pasien.no_rkm_medis=?",
											reg.getTable().getValueAt(reg.getTable().getSelectedRow(), 6)
													.toString()) > 0) {
										JOptionPane.showMessageDialog(null,
												"Pasien sedang dalam masa perawatan di kamar inap..!!");
//										tNoRw.setText("");
//										tNoRM.setText("");
//										tPasien.setText("");
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
						if (akses.getform().equals("DlgKasirRalanNew")) {
							reg.dispose();
						}
					}

					@Override
					public void keyReleased(KeyEvent e) {
					}
				});
			}
		});
		BtnReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
		BtnReg.setMnemonic('4');
		BtnReg.setBounds(371, 11, 28, 23);
		panelGlassTop.add(BtnReg);

		Label jLabelCaraBayar = new Label();
		jLabelCaraBayar.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelCaraBayar.setBounds(758, 11, 64, 23);
		jLabelCaraBayar.setText("Cara Bayar");
		panelGlassTop.add(jLabelCaraBayar);

		btnGrp = new ButtonGroup();

		rdbtnCash = new JRadioButton("Cash");
		rdbtnCash.setBounds(879, 7, 71, 23);
		panelGlassTop.add(rdbtnCash);

		rdbtnPiutang = new JRadioButton("Piutang");
		rdbtnPiutang.setBounds(968, 7, 78, 23);
		panelGlassTop.add(rdbtnPiutang);
		btnGrp.add(rdbtnCash);
		btnGrp.add(rdbtnPiutang);

		Label jLabelAkunPenerimaan = new Label();
		jLabelAkunPenerimaan.setText("Penerimaan");
		jLabelAkunPenerimaan.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelAkunPenerimaan.setBounds(758, 44, 64, 23);
		panelGlassTop.add(jLabelAkunPenerimaan);

		comboBox = new widget.ComboBox();
		comboBox.setEditable(false);
		comboBox.enable(false);
		comboBox.setBounds(879, 45, 167, 22);
		comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jenis Penerimaan" }));
		panelGlassTop.add(comboBox);

		cbxTindakan = new JCheckBox("Tindakan/Pemeriksaan");
		cbxTindakan.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {// checkbox has been selected
					getDataTindakan();
					CountPayBill();
					CountInsurance();
					CountDiscount();
				} else {// checkbox has been deselected
					tabelModelBilling.setRowCount(0);
					if (cbxObat.isSelected()) {
						getDataObat();
					}
					if (chckbxRadiologi.isSelected()) {
						getDataRadiologi();
					}
					if (chckbxLaboratorium.isSelected()) {
						getDataLaborat();
					}
					CountPayBill();
					CountInsurance();
					CountDiscount();
				}

			}
		});

		cbxTindakan.setSelected(true);
		cbxTindakan.setBounds(216, 163, 158, 23);
		panelGlassTop.add(cbxTindakan);

		cbxObat = new JCheckBox("Obat/BHP");
		cbxObat.setSelected(true);
		cbxObat.setBounds(399, 163, 88, 23);
		cbxObat.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {// checkbox has been selected
					getDataObat();
					CountPayBill();
					CountInsurance();
					CountDiscount();
				} else {// checkbox has been deselected
					tabelModelBilling.setRowCount(0);
					if (cbxTindakan.isSelected()) {
						getDataTindakan();
					}
					if (chckbxRadiologi.isSelected()) {
						getDataRadiologi();
					}
					if (chckbxLaboratorium.isSelected()) {
						getDataLaborat();
					}
					CountPayBill();
					CountInsurance();
					CountDiscount();
				}

			}
		});
		panelGlassTop.add(cbxObat);

		chckbxRadiologi = new JCheckBox("Radiologi");
		chckbxRadiologi.setSelected(true);
		chckbxRadiologi.setBounds(504, 163, 93, 23);
		chckbxRadiologi.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {// checkbox has been selected
					getDataRadiologi();
					CountPayBill();
					CountInsurance();
					CountDiscount();
				} else {// checkbox has been deselected
					tabelModelBilling.setRowCount(0);
					if (cbxTindakan.isSelected()) {
						getDataTindakan();
					}
					if (cbxObat.isSelected()) {
						getDataObat();
					}
					if (chckbxLaboratorium.isSelected()) {
						getDataLaborat();
					}
					CountPayBill();
					CountInsurance();
					CountDiscount();
				}

			}
		});
		panelGlassTop.add(chckbxRadiologi);

		chckbxLaboratorium = new JCheckBox("Laboratorium");
		chckbxLaboratorium.setSelected(true);
		chckbxLaboratorium.setBounds(617, 163, 109, 23);
		chckbxLaboratorium.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {// checkbox has been selected
					getDataLaborat();
					CountPayBill();
					CountInsurance();
					CountDiscount();
				} else {// checkbox has been deselected
					tabelModelBilling.setRowCount(0);
					if (cbxTindakan.isSelected()) {
						getDataTindakan();
					}
					if (cbxObat.isSelected()) {
						getDataObat();
					}
					if (chckbxRadiologi.isSelected()) {
						getDataRadiologi();
					}
					CountPayBill();
					CountInsurance();
					CountDiscount();
				}

			}
		});
		panelGlassTop.add(chckbxLaboratorium);

		tKd_pj = new TextBox();
		tKd_pj.setBounds(542, 78, 88, 23);
		panelGlassTop.add(tKd_pj);

		rdbtnCash.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jenis Penerimaan" }));
				comboBox.enable(true);
				sekuel Sequel = new sekuel();
				Sequel.addItemCombo("Select nama_bayar from akun_bayar", comboBox);
			}

		});
		rdbtnPiutang.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jenis Penerimaan" }));
				comboBox.enable(true);
				sekuel Sequel = new sekuel();
				Sequel.addItemCombo("Select nama_bayar from akun_piutang", comboBox);
			}

		});

		internalFrame1.add(panelGlassBottom, java.awt.BorderLayout.PAGE_END);

		BtnKeluar = new Button();
		BtnKeluar.setMnemonic('K');
		BtnKeluar.setText("Keluar");
		BtnKeluar.setHorizontalTextPosition(SwingConstants.RIGHT);
		BtnKeluar.setHorizontalAlignment(SwingConstants.LEFT);
		BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
		BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png")));
		BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnKeluarActionPerformed(evt);
			}
		});

		BtnSimpan = new Button();
		BtnSimpan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Payement();
			}
		});
		BtnSimpan.setMnemonic('S');
		BtnSimpan.setText("Simpan");
		BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png")));
		BtnSimpan.setHorizontalTextPosition(SwingConstants.RIGHT);
		BtnSimpan.setHorizontalAlignment(SwingConstants.LEFT);
		BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
		panelGlassBottom.add(BtnSimpan);

		BtnNota = new Button();
		BtnNota.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notaPembayaran(null);
			}
		});
		BtnNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Agenda-1-16x16.png")));
		BtnNota.setMnemonic('N');
		BtnNota.setText(" Nota");
		BtnNota.setPreferredSize(new java.awt.Dimension(100, 30));
		BtnNota.setHorizontalTextPosition(SwingConstants.RIGHT);
		BtnNota.setHorizontalAlignment(SwingConstants.LEFT);
		panelGlassBottom.add(BtnNota);
		BtnNota.setPreferredSize(new java.awt.Dimension(100, 30));
		panelGlassBottom.add(BtnKeluar);
		
		btnTransferTag = new Button();
		btnTransferTag.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tranferBill(null);
			}
		});
		btnTransferTag.setMnemonic('K');
		btnTransferTag.setText("Transfer Tagihan");
		btnTransferTag.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnTransferTag.setHorizontalAlignment(SwingConstants.LEFT);
		btnTransferTag.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelGlassBottom.add(btnTransferTag);
		getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

		pack();
	}

	protected void notaPembayaran(Object object) {
		// TODO Auto-generated method stub
		sekuel Sequel = new sekuel();
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
		Date date = new Date();
		String dates=dateFormat.format(date);
		Map<String, Object> param = new HashMap<>();
		param.put("namars", akses.getnamars());
		param.put("alamatrs", akses.getalamatrs());
		param.put("kotars", akses.getkabupatenrs());
		param.put("propinsirs", akses.getpropinsirs());
		param.put("kontakrs", akses.getkontakrs());
		param.put("emailrs", akses.getemailrs());
		param.put("logo", Sequel.cariGambar("select logo from setting"));
		param.put("no_rm",Sequel.cariIsi("Select no_rkm_medis from reg_periksa where no_rawat='"+tNoRM.getText()+"'"));
		param.put("nm_pasien", Sequel.cariIsi("Select nm_pasien from reg_periksa inner join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis  where no_rawat='"+tNoRw.getText()+"'"));
		param.put("tanggal", dates);		
		param.put("nm_petugas", akses.getnamauser());
		param.put("no_rawat",tNoRw.getText());
		Valid.MyReportqry("notaRalan.jasper", "report", "::[ Billing Ralan ]::",
				"Select no_rawat,tgl_jurnal,deskripsi,jml,nominal,djamin,potongan,total_nominal from new_jurnal where no_rawat='"+tNoRw.getText()+"'",
				param);
		
	}

	protected void tranferBill(ActionEvent evt) {
		// TODO Auto-generated method stub
		int dialogButton = JOptionPane.showConfirmDialog (null, "Apakah anda akan Melakukan Tranfer tagihan ?","WARNING",JOptionPane.YES_NO_OPTION);
		if (dialogButton == JOptionPane.YES_OPTION) {
			sekuel Sequel = new sekuel();
			Sequel.mengedit("reg_periksa","no_rawat='"+tNoRw.getText()+"'","status_bayar='Transfer'");
			JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi..!!");
		}
		
	}

	protected void MnTambahanActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		sekuel Sequel = new sekuel();
		if (Sequel.cariRegistrasi(tNoRw.getText()) > 0) {
			JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi..!!");
		} else {
			createExtendsBill();
			norawat.setText(tNoRw.getText());
			tampilTambahan(tNoRw.getText());
			WindowTambahanBiaya.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
			WindowTambahanBiaya.setLocationRelativeTo(internalFrame1);
			WindowTambahanBiaya.setAlwaysOnTop(false);
			WindowTambahanBiaya.setVisible(true);
		}
	}

	private void createExtendsBill() {
		WindowTambahanBiaya = new javax.swing.JDialog();
		WindowTambahanBiaya.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
		WindowTambahanBiaya.setLocationRelativeTo(internalFrame1);
		WindowTambahanBiaya.setAlwaysOnTop(false);
		WindowTambahanBiaya.setVisible(true);
		internalFrame4 = new widget.InternalFrame();
		internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Tambah Biaya ]::",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
		internalFrame4.setName("internalFrame4");
		internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));
		internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Tambah Biaya ]::",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
		internalFrame4.setName("internalFrame4");
		internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));
		scrollPane1 = new widget.ScrollPane();
		tbTambahan = new widget.Table();
		panelisi1 = new widget.panelisi();
		Label label15 = new widget.Label();
		norawat = new widget.TextBox();
		BtnTambah = new widget.Button();
		BtnSimpan3 = new widget.Button();
		BtnHapus = new widget.Button();
		BtnKeluar1 = new widget.Button();
		scrollPane1.setName("scrollPane1");
		scrollPane1.setOpaque(true);

		// tambahan biaya
		tabModeTambahan = new DefaultTableModel(null, new Object[] { "Tambahan Biaya", "Besar Biaya" }) {
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return true;
			}

			Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		tbTambahan.setModel(tabModeTambahan);

		tbTambahan.setPreferredScrollableViewportSize(new Dimension(800, 800));
		tbTambahan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (i = 0; i < 2; i++) {
			TableColumn column = tbTambahan.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(300);
			} else if (i == 1) {
				column.setPreferredWidth(150);
			}
		}
		tbTambahan.setDefaultRenderer(Object.class, new WarnaTable());

//     tbTambahan.setModel(new javax.swing.table.DefaultTableModel(
//         new Object [][] {
//             {},
//             {},
//             {},
//             {}
//         },
//         new String [] {
//
//         }
//     ));
		tbTambahan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
		tbTambahan.setName("tbTambahan"); // NOI18N
		scrollPane1.setViewportView(tbTambahan);

		internalFrame4.add(scrollPane1, java.awt.BorderLayout.CENTER);

		panelisi1.setName("panelisi1"); // NOI18N
		panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
		panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

		label15.setText("No.Rawat :");
		label15.setName("label15"); // NOI18N
		label15.setPreferredSize(new java.awt.Dimension(60, 23));
		panelisi1.add(label15);

		norawat.setEditable(false);
		norawat.setName("norawat"); // NOI18N
		norawat.setPreferredSize(new java.awt.Dimension(150, 23));
		norawat.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				norawatKeyPressed(evt);
			}
		});
		panelisi1.add(norawat);

		BtnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/add-file-16x16.png"))); // NOI18N
		BtnTambah.setMnemonic('T');
		BtnTambah.setText("Tambah");
		BtnTambah.setToolTipText("Alt+T");
		BtnTambah.setName("BtnTambah"); // NOI18N
		BtnTambah.setPreferredSize(new java.awt.Dimension(100, 30));
		BtnTambah.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnTambahActionPerformed(evt);
			}
		});
		panelisi1.add(BtnTambah);

		BtnSimpan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
		BtnSimpan3.setMnemonic('S');
		BtnSimpan3.setText("Simpan");
		BtnSimpan3.setToolTipText("Alt+S");
		BtnSimpan3.setName("BtnSimpan3"); // NOI18N
		BtnSimpan3.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnSimpan3ActionPerformed(evt);
			}
		});
		panelisi1.add(BtnSimpan3);

		BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
		BtnHapus.setMnemonic('H');
		BtnHapus.setText("Hapus");
		BtnHapus.setToolTipText("Alt+H");
		BtnHapus.setName("BtnHapus"); // NOI18N
		BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
		BtnHapus.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnHapusActionPerformed(evt);
			}
		});
		panelisi1.add(BtnHapus);

		BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
		BtnKeluar1.setMnemonic('U');
		BtnKeluar1.setText("Tutup");
		BtnKeluar1.setToolTipText("Alt+U");
		BtnKeluar1.setName("BtnKeluar1"); // NOI18N
		BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
		BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnKeluar1ActionPerformed(evt);
			}
		});
		panelisi1.add(BtnKeluar1);

		internalFrame4.add(panelisi1, java.awt.BorderLayout.PAGE_END);
		WindowTambahanBiaya.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);
	}

	public void tampilTambahan(String NoRawat) {
		norawat.setText(NoRawat);
		Valid.tabelKosong(tabModeTambahan);
		ResultSet rstambahan = null;
		PreparedStatement pstambahan;
		Connection koneksi = koneksiDB.condb();
		try {
			pstambahan = koneksi
					.prepareStatement("select nama_biaya, besar_biaya from tambahan_biaya_ralan where no_rawat=?");
			try {
				pstambahan.setString(1, norawat.getText());
				rstambahan = pstambahan.executeQuery();
				while (rstambahan.next()) {
					tabModeTambahan.addRow(new Object[] { rstambahan.getString(1), rstambahan.getString(2) });
				}
			} catch (Exception e) {
				System.out.println("Notifikasi : " + e);
			} finally {
				if (rstambahan != null) {
					rstambahan.close();
				}
				if (pstambahan != null) {
					pstambahan.close();
				}
			}
		} catch (Exception e) {
			System.out.println("Notifikasi : " + e);
		}
	}

	protected void BtnKeluar1ActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		WindowTambahanBiaya.dispose();
	}

	protected void BtnHapusActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		sekuel Sequel = new sekuel();
		Sequel.queryu("delete from tambahan_biaya_ralan where no_rawat='" + norawat.getText() + "' and nama_biaya='"
				+ tbTambahan.getValueAt(tbTambahan.getSelectedRow(), 0).toString() + "'");
//	    tabModeTambahan.removeRow(tbTambahan.getSelectedRow());
	}

	protected void BtnSimpan3ActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		sekuel Sequel = new sekuel();
		if (norawat.getText().trim().equals("") || (tbTambahan.getRowCount() <= 0)) {
			Valid.textKosong(norawat, "Data");
		} else {
			for (i = 0; i < tbTambahan.getRowCount(); i++) {
				if (Valid.SetAngka(tbTambahan.getValueAt(i, 1).toString()) > 0) {
					Sequel.menyimpan("tambahan_biaya_ralan",
							"'" + norawat.getText() + "','" + tbTambahan.getValueAt(i, 0).toString() + "','"
									+ tbTambahan.getValueAt(i, 1).toString() + "','Belum Lunas'",
							"Tambahan Biaya");
				}
			}
			WindowTambahanBiaya.dispose();
			SetNoRm(norawat.getText());
		}
	}

	protected void BtnTambahActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		tabModeTambahan.addRow(new Object[] { "", "" });
	}

	private validasi Valid = new validasi();

	protected void norawatKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, BtnKeluar, BtnNota);
	}

	protected void getCountPayement() {
		// TODO Auto-generated method stub
		double kembali = Double.parseDouble(tPayment.getText().toString())
				- Double.parseDouble(tTagihan.getText().toString());
		tPayback.setText(String.valueOf(kembali));
		Payement();
	}

	protected void Payement() {
		// TODO Auto-generated method stub
		sekuel Sequel = new sekuel();
		validasi Valid = new validasi();
		Sequel.AutoComitFalse();
		String noBill = tNoRw.getText();
		boolean sukses = false;
		double countPayment = Double.parseDouble(tPayment.getText());
		double countPayBack = Double.parseDouble(tPayback.getText());
		double countBill = Double.parseDouble(tTagihan.getText());
		if (countBill > countPayment) {
			JOptionPane.showMessageDialog(rootPane, "Pembayaran pasien masih kurang..!");
		} else {
			String table;
			String condition = "";
			String field = "";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
			Date date = new Date();
			String tanggal=dateFormat.format(date);
			boolean succes = false;
			
			if (rdbtnCash.isSelected() == true || rdbtnPiutang.isSelected() == true) {
				if (!comboBox.getSelectedItem().toString().equals("Pilih Jenis Penerimaan")) {
					for (int i = 0; i < tbBilling.getRowCount(); i++) {
						String deskripsi="";
						if (tbBilling.getValueAt(i, 2) != null) {
							// System.out.println("pk"+tbBilling.getValueAt(i,
							// 7)+"-Table:"+tbBilling.getValueAt(i, 8));
							table = tbBilling.getValueAt(i, 8).toString();
							if (tbBilling.getValueAt(i, 8).toString().equals("tindakan_rajal")) {
								condition = "kd_jenis_prw='" + tbBilling.getValueAt(i, 7) + "' and no_rawat='"
										+ tNoRw.getText() + "'";
								field = "stts_bayar='Sudah'";
								deskripsi="Tindakan Pemeriksaan Rawat Jalan Pasien ";
							} else if (tbBilling.getValueAt(i, 8).toString().equals("resep_obat")) {
								condition = "no_resep='" + tbBilling.getValueAt(i, 7).toString() + "'";
								field = "status_bayar='Sudah Lunas'";
								deskripsi="Pemberian Obat/Resep Pasien ";
							} else if (tbBilling.getValueAt(i, 8).toString().equals("permintaan_pemeriksaan_radiologi")
									|| tbBilling.getValueAt(i, 8).toString().equals("permintaan_pemeriksaan_lab")
									|| tbBilling.getValueAt(i, 8).toString().equals("permintaan_pemeriksaan_labpa")) {
								condition = "noorder='" + tbBilling.getValueAt(i, 7).toString() + "'";
								field = "stts_bayar='Sudah'";
								deskripsi="Pemeriksaan Penunjang Pasien";
							} else if (tbBilling.getValueAt(i, 8).toString().equals("bhp_lab")) {
								condition = "no_order='" + tbBilling.getValueAt(i, 7).toString() + "'";
								field = "stts_bayar='Lunas'";
							} else if(tbBilling.getValueAt(i, 8).toString().equals("tambahan_biaya_ralan")) {
								condition = "no_rawat='" + tbBilling.getValueAt(i, 7).toString() + "'";
								field = "status_bayar='Sudah Lunas'";
								deskripsi="Tambahan Biaya "+tbBilling.getValueAt(i, 0).toString();
							}else if(tbBilling.getValueAt(i, 8).toString().equals("reg_periksa")) {
								condition = "no_rawat='" + tbBilling.getValueAt(i, 7).toString() + "'";
								field = "biaya_reg='0'";
								deskripsi="Biaya Pendaftaran Pasien Baru";
							}
							if (Sequel.mengedittf(table, condition, field) == true) {
								succes = true;
							} else {
								succes = false;

							}
							String nojur=Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_jurnal,6),signed)),0) from new_jurnal where tgl_jurnal='"+tanggal+"' ","JR"+tanggal.replaceAll("-",""),6);
							succes=Sequel.menyimpanNew("new_jurnal","no_jurnal,no_rawat,tgl_jurnal,deskripsi,jml,nominal,djamin,potongan,total_nominal,source_table,jenis",
									"'"+nojur+"','"+tNoRw.getText()+"','"+tanggal+"','Beban "+deskripsi+"','"+tbBilling.getValueAt(i, 1).toString()+"','"+tbBilling.getValueAt(i, 2).toString()+"',"
											+ "'"+tbBilling.getValueAt(i, 3).toString()+"','"+tbBilling.getValueAt(i, 4).toString()+"','"+tbBilling.getValueAt(i, 5).toString()+"','"+tbBilling.getValueAt(i, 8).toString()+"','U'", "Beban");
						}						
												
					}
					String nojurP=Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_jurnal,6),signed)),0) from new_jurnal where tgl_jurnal='"+tanggal+"' ","JR"+tanggal.replaceAll("-",""),6);
					
					if (succes == true) {
						Sequel.mengedit("reg_periksa", "no_rawat='" + tNoRw.getText() + "'",
								"status_bayar='Sudah Bayar'");
						Sequel.menyimpanNew("new_jurnal","no_rawat,no_jurnal,tgl_jurnal,deskripsi,nominal,total_nominal,jenis",
								"'"+tNoRw.getText()+"','"+nojurP+"','"+tanggal+"','Penerimaan Pembayaran Kasir Ralan "+comboBox.getSelectedItem().toString()+"','"+tTagihan.getText()+"','"+tPayment.getText()+"','P'","Penerimaan");
						Sequel.Commit();
						JOptionPane.showMessageDialog(rootPane, "Pembayaran Berasil disimpan");
						getloadDataTableBilling();
					} else {
						Sequel.RollBack();
					}
					Sequel.AutoComitTrue();

				} else {
					JOptionPane.showMessageDialog(rootPane, "Silahkan pilih jenis posting cara bayar..");
				}
//			if(Sequel.menyimpantf2("newBilling","?,?,?,?,?,?,?,?,?,?,?","Akun bayar",11,new String[] {
//					 noBill,tNoRw.getText(),tNoRM.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),comboBox.getSelectedItem().toString()
//					,Double.toString(totalBayar),tPayment.getText(),Double.toString(totalInsurace),Double.toString(totalDiscount),
//					"Lunas",tKd_pj.getText()
//			})==true) {
//			Sequel.mengedit("reg_periksa","no_rawat='"+tNoRw.getText()+"'","status_bayar='Sudah Bayar'");
//			}
//			System.out.println("Pembayaran Kembali"+countPayBack+"Total Tagihan:"+totalBayar+"Total Dijamin:"+totalInsurace+"Total diskon:"+totalDiscount);
			} else {
				JOptionPane.showMessageDialog(rootPane, "Silahkan Pilih Jenis Cara Bayar");
			}
		}

	}

	private double dijamin;
	private double diskon;
	private double totalInsurace;
	private double totalDiscount;
	private Button BtnSimpan;
	private Button BtnNota;
	private int tabelRowObat;
	private panelisi panelDataTotalBilling;
	private JLabel lblTotalTagihan;
	private JLabel lblTotalDijamin;
	private JLabel lblTotalTagihan_1;
	private JLabel lblTotalTagihan_2;
	private JLabel lblTotalTagihan_3;
	private TextBox tTagihan;
	private TextBox tInsurance;
	private TextBox tDiscount;
	private TextBox tPayment;
	private TextBox tPayback;
	private int tabelRowRadiologit;
	private int tabelRowLaborat;
	private Button btnTransferTag;

	protected void countBillingKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (tbBilling.getRowCount() != 0) {
			if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
				i = tbBilling.getSelectedColumn();
				if (i == 3) {
					CountBill();

				} else if (i == 4) {
					CountBill();
				}

			}
		}
	}

	public void CountBill() {
		double harga = Double.parseDouble(tbBilling.getValueAt(tbBilling.getSelectedRow(), 2).toString());

		if (tbBilling.getValueAt(tbBilling.getSelectedRow(), 3) != null) {
			dijamin = Double.parseDouble(tbBilling.getValueAt(tbBilling.getSelectedRow(), 3).toString());
		} else {
			dijamin = 0;
			tbBilling.setValueAt("0.0", tbBilling.getSelectedRow(), 3);
		}
		diskon = Double.parseDouble(tbBilling.getValueAt(tbBilling.getSelectedRow(), 4).toString());
		double jmlharga = harga - dijamin - diskon;
		tbBilling.setValueAt(jmlharga, tbBilling.getSelectedRow(), 5);
		CountInsurance();
		CountDiscount();
//		CountPayBill();
		if (!tPayment.getText().equals("0.0")) {
			getCountPayement();
		}
	}

	public void CountPayBill() {
		totalBayar = 0;
		for (int i = 0; i < tbBilling.getRowCount(); i++) {
			if (tbBilling.getValueAt(i, 5) != null && !tbBilling.getValueAt(i, 6).toString().equals("Sudah Lunas")) {
				totalBayar += Double.parseDouble(tbBilling.getValueAt(i, 5).toString());
			}
		}
		double totalBayars = totalBayar - totalInsurace - totalDiscount;
		tTagihan.setText(String.valueOf(totalBayars));
	}

	public void CountInsurance() {
		totalInsurace = 0;
		for (int i = 0; i < tbBilling.getRowCount(); i++) {
//			
			if (tbBilling.getValueAt(i, 3) != null) {
				// System.out.println(tbBilling.getValueAt(i, 3).toString());
				totalInsurace += Double.parseDouble(tbBilling.getValueAt(i, 3).toString());
			}

		}

		double totalBayars = totalBayar - totalInsurace - totalDiscount;
		tTagihan.setText(String.valueOf(totalBayars));
		tInsurance.setText(String.valueOf(totalInsurace));

	}

	public void CountDiscount() {
		totalDiscount = 0;
		for (int i = 0; i < tbBilling.getRowCount(); i++) {
			if (tbBilling.getValueAt(i, 4) != null) {
				totalDiscount += Double.parseDouble(tbBilling.getValueAt(i, 4).toString());
			}
		}

		double totalBayars = totalBayar - totalInsurace - totalDiscount;
		tTagihan.setText(String.valueOf(totalBayars));
		tDiscount.setText(String.valueOf(totalDiscount));

	}

	protected void countBilling(KeyEvent evt) {
		// TODO Auto-generated method stub
		countBillingKeyPressed(evt);
	}

	protected void countPropertyBilling(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (this.isVisible() == true) {
			CountBill();
		}
	}

	protected void btnKeluarActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		dispose();
	}

	public void getDataTindakan() {
		sekuel Sequel = new sekuel();
		
		Connection koneksi = koneksiDB.condb();				 
		
		if (Sequel.cariInteger("select count(no_rkm_medis) from reg_periksa where no_rawat=? and stts_daftar='Baru'",
				tNoRw.getText())>0) {		
			tabelModelBilling.addRow(new Object[] { " Biaya Registrasi" });
			String sqlpsreg="select reg_periksa.tgl_registrasi,reg_periksa.no_rkm_medis,"+
	                "reg_periksa.kd_poli,reg_periksa.no_rawat,reg_periksa.biaya_reg,current_time() as jam,"+
	                "reg_periksa.umurdaftar,reg_periksa.sttsumur,status_bayar "+
	                "from reg_periksa where reg_periksa.no_rawat=? and stts_daftar='Baru'";			
			PreparedStatement psreg;
			try {
				 psreg = koneksi.prepareStatement(sqlpsreg);
				 psreg.setString(1,tNoRw.getText());
		         ResultSet rsreg = psreg.executeQuery();
		         if(rsreg.next()){
		         tabelModelBilling.addRow(new Object[] { "Registrasi", 1, rsreg.getDouble("biaya_reg"), 0, 0, rsreg.getDouble("biaya_reg"),
							rsreg.getString("status_bayar"), tNoRw.getText(), "reg_periksa" });
		         }
		         
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (Sequel.cariInteger("Select count(no_rawat) from tindakan_rajal where no_rawat=?", tNoRw.getText()) > 0) {
			tabelModelBilling.addRow(new Object[] { "Pemeriksaan Rawat Jalan" });
			
			ResultSet rs;
			double harga;
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			DefaultTableCellRenderer RightRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);

			RightRenderer.setHorizontalAlignment(JLabel.RIGHT);
//			if (tbBilling.getValueAt(0, 0).toString().equals("Pemeriksaan Rawat Jalan")) {
//				tbBilling.setRowHeight(0, 40);
//				for (i = 0; i < tabelModelBilling.getColumnCount(); i++) {
//					renderer.setColorForCell(0, i, Color.LIGHT_GRAY);
//				}
//			}
			tbBilling.setAutoscrolls(true);
			tbBilling.getColumnModel().getColumn(2).setCellRenderer(RightRenderer);
			tbBilling.getColumnModel().getColumn(3).setCellRenderer(RightRenderer);
			tbBilling.getColumnModel().getColumn(4).setCellRenderer(RightRenderer);
			tbBilling.getColumnModel().getColumn(5).setCellRenderer(RightRenderer);
			tbBilling.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
			String query = "Select no_rawat,nm_perawatan,jns_perawatan.material,jns_perawatan.tarif_tindakandr,kd_dokter,jns_perawatan.kd_jenis_prw,stts_bayar from tindakan_rajal "
					+ "Inner Join jns_perawatan on jns_perawatan.kd_jenis_prw=tindakan_rajal.kd_jenis_prw "
					+ "where no_rawat='" + tNoRw.getText() + "'";

			int j = 0;
			String StatusBayar = "Belum Bayar";
			try {
				rs = koneksi.prepareStatement(query).executeQuery();
				while (rs != null && rs.next()) {
					if (rs.getDouble("material") == 0) {
						harga = Math.round(rs.getDouble("tarif_tindakandr"));
					} else if (rs.getDouble("tarif_tindakandr") == 0) {
						harga = Math.round(rs.getDouble("material"));
					} else {
						harga = Math.round(rs.getDouble("tarif_tindakandr") + rs.getDouble("material"));
					}
					if (rs.getString("stts_bayar").toString().equals("Sudah")) {
						StatusBayar = "Sudah Lunas";
					}
					JmlBayar = harga;

					tabelModelBilling.addRow(new Object[] { rs.getString("nm_perawatan"), 1, harga, 0, 0, JmlBayar,
							StatusBayar, rs.getString("kd_jenis_prw"), "tindakan_rajal" });
					tabelRowTindakan++;
					j++;
				}
				// tTagihan.setText(String.valueOf(totalBayar));

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
//			System.out.println("Tidak Punya Tindakan:" + tNoRw.getText());
		}

	}

	public void getDataObat() {
		sekuel Sequel = new sekuel();
		if (Sequel.cariInteger(
				"Select count(no_rawat) from resep_obat Inner join resep_dokter on resep_dokter.no_resep=resep_obat.no_resep where no_rawat=? and resep_obat.jam_peresepan!=resep_obat.jam",
				tNoRw.getText()) > 0) {
			tabelModelBilling.addRow(new Object[] { " Obat/Barang Medis" });
			Connection koneksi = koneksiDB.condb();
			ResultSet rs;
			Double harga;
			validasi Valid = new validasi();
			String tanggal = Valid.SetTgl(DTPTgl.getSelectedItem() + "");
			String query = "select resep_obat.no_resep,databarang.kode_brng,databarang.nama_brng,resep_dokter.jml,databarang.kode_sat,databarang.dasar,"
					+ "if(resep_obat.jam_peresepan=resep_obat.jam,'Belum Terlayani','Sudah Terlayani') as status,resep_obat.status_bayar, \r\n"
					+ "poliklinik.nm_poli,resep_obat.status as status_asal,penjab.png_jawab \r\n"
					+ "from resep_obat  \r\n"
					+ "inner join reg_periksa on resep_obat.no_rawat=reg_periksa.no_rawat  \r\n"
					+ "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis  \r\n"
					+ "inner join dokter on resep_obat.kd_dokter=dokter.kd_dokter  \r\n"
					+ "inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli  \r\n"
					+ "inner join penjab on reg_periksa.kd_pj=penjab.kd_pj  \r\n"
					+ "inner join resep_dokter on resep_dokter.no_resep=resep_obat.no_resep\r\n"
					+ "inner join databarang on resep_dokter.kode_brng=databarang.kode_brng \r\n"
					+ "where resep_obat.status='ralan' and resep_obat.tgl_perawatan between '" + tanggal + "' and '"
					+ tanggal + "' " + "and  resep_obat.jam_peresepan!=resep_obat.jam\r\n"
					+ " and reg_periksa.no_rawat='" + tNoRw.getText() + "'"
					+ "order by resep_obat.tgl_perawatan desc,resep_obat.jam desc";
			// System.out.println(query);
			try {
				rs = koneksi.prepareStatement(query).executeQuery();
				while (rs != null && rs.next()) {
					double hargaObat = rs.getDouble("dasar") + Math.round(rs.getDouble("dasar") * margin / 100);
					double JmlBayarObat = hargaObat * rs.getDouble("jml");
					// totalBayar += JmlBayarObat;
					tabelModelBilling.addRow(new Object[] { rs.getString("nama_brng"), rs.getDouble("jml"), hargaObat,
							0, 0, JmlBayarObat, rs.getString("status_bayar"), rs.getString("no_resep"), "resep_obat" });
					tabelRowObat++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getDataObatRacik();
	}

	public void getDataObatRacik() {
		sekuel Sequel = new sekuel();
		if (Sequel.cariInteger("Select count(no_rawat) from obat_racikan where no_rawat=? ", tNoRw.getText()) > 0) {
			tabelModelBilling.addRow(new Object[] { " Obat Racikan" });
			Connection koneksi = koneksiDB.condb();
			ResultSet rs, rs1;
			Double harga;
			validasi Valid = new validasi();
			String tanggal = Valid.SetTgl(DTPTgl.getSelectedItem() + "");
			String query = "select nama_racik,nm_racik,jml_dr,resep_obat.jam,status_bayar,no_resep from obat_racikan "
					+ "Inner join resep_obat on obat_racikan.no_rawat=resep_obat.no_rawat and resep_obat.tgl_perawatan=obat_racikan.tgl_perawatan and resep_obat.jam=obat_racikan.jam "
					+ "Inner join metode_racik on metode_racik.kd_racik=obat_racikan.kd_racik "
					+ "where resep_obat.no_rawat='" + tNoRw.getText() + "'" + "group by resep_obat.jam ";
			// System.out.println(query);
			try {
				rs = koneksi.prepareStatement(query).executeQuery();
				while (rs != null && rs.next()) {
//					double hargaObat = rs.getDouble("dasar") * rs.getDouble("jml");
//					double JmlBayarObat = hargaObat;
					// totalBayar += JmlBayarObat;
					tabelModelBilling.addRow(
							new Object[] { " " + rs.getString("nama_racik") + " Jenis " + rs.getString("nm_racik"),
									rs.getDouble("jml_dr") });
					rs1 = koneksi
							.prepareStatement("select nama_brng,biaya_obat,jml,total from detail_pemberian_obat \r\n"
									+ "inner join databarang on databarang.kode_brng=detail_pemberian_obat.kode_brng\r\n"
									+ "where no_rawat='" + tNoRw.getText() + "' and detail_pemberian_obat.jam='"
									+ rs.getString("jam") + "'")
							.executeQuery();
					while (rs1 != null && rs1.next()) {
						tabelModelBilling.addRow(new Object[] { "  " + rs1.getString("nama_brng"), rs1.getDouble("jml"),
								rs1.getDouble("biaya_obat"), 0, 0, rs1.getDouble("total"), rs.getString("status_bayar"),
								rs.getString("no_resep"), "resep_obat" });
						tabelRowObat++;
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void getDataRadiologi() {
		sekuel Sequel = new sekuel();
		if (Sequel.cariInteger("Select count(no_rawat) from permintaan_radiologi where no_rawat=?",
				tNoRw.getText()) > 0) {
			tabelModelBilling.addRow(new Object[] { " Tindakan Penujang Radiologi" });
			Connection koneksi = koneksiDB.condb();
			ResultSet rs;

			String query = "SELECT ppr.noorder,nm_perawatan,total_byr,stts_bayar FROM permintaan_radiologi\r\n"
					+ "Inner join permintaan_pemeriksaan_radiologi as ppr on ppr.noorder=permintaan_radiologi.noorder\r\n"
					+ "Inner join jns_perawatan_radiologi as jns_rad on  jns_rad.kd_jenis_prw=ppr.kd_jenis_prw\r\n"
					+ "where no_rawat='" + tNoRw.getText() + "'";
			String StatusBayar = "Belum Lunas";
			try {
				rs = koneksi.prepareStatement(query).executeQuery();
				while (rs != null && rs.next()) {
					double hargaRadiologi = Math.round(rs.getDouble("total_byr"));
					double JmlBayarRadiologi = hargaRadiologi;
					if (rs.getString("stts_bayar").toString().equals("Sudah")) {
						StatusBayar = "Sudah Lunas";
					}
					tabelModelBilling.addRow(
							new Object[] { rs.getString("nm_perawatan"), 1, hargaRadiologi, 0, 0, JmlBayarRadiologi,
									StatusBayar, rs.getString("noorder"), "permintaan_pemeriksaan_radiologi" });
					tabelRowRadiologit++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void getBHPLab(String noOrder) {
		Connection koneksi = koneksiDB.condb();
		ResultSet rs;

		try {
			String StatusBayar = "Belum Lunas";
			rs = koneksi.prepareStatement(
					"Select bhp_lab.*,nama_brng from bhp_lab INNER JOIN databarang on databarang.kode_brng=bhp_lab.kode_brng where no_order='"
							+ noOrder + "'")
					.executeQuery();
			// System.out.println("Select bhp_lab.*,nama_brng from bhp_lab INNER JOIN
			// databarang on databarang.kdjns=bhp_lab.kode_brng where
			// no_order='"+noOrder+"'");
			while (rs != null && rs.next()) {
				double hargaLaborat = Math.round(rs.getDouble("harga"));
				double JmlBayarLaborat = hargaLaborat * rs.getDouble("jumlah");
				if (rs.getString("stts_bayar").toString().equals("Lunas")) {
					StatusBayar = "Sudah Lunas";
				}
				tabelModelBilling.addRow(new Object[] { rs.getString("nama_brng"), rs.getDouble("jumlah"), hargaLaborat,
						0, 0, JmlBayarLaborat, StatusBayar, rs.getString("no_order"), "bhp_lab" });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getDataLaborat() {
		// TODO Auto-generated method stub
		sekuel Sequel = new sekuel();
		if (Sequel.cariInteger("Select count(no_rawat) from permintaan_lab where no_rawat=?", tNoRw.getText()) > 0) {
			tabelModelBilling.addRow(new Object[] { "Tindakan Penujang Laboratorium" });
			Connection koneksi = koneksiDB.condb();
			ResultSet rs;

			String query = "Select permintaan_pemeriksaan_lab.noorder,tgl_permintaan,nm_perawatan,total_byr,stts_bayar from permintaan_lab "
					+ "inner join permintaan_pemeriksaan_lab on permintaan_lab.noorder=permintaan_pemeriksaan_lab.noorder "
					+ "inner join jns_perawatan_lab on jns_perawatan_lab.kd_jenis_prw=permintaan_pemeriksaan_lab.kd_jenis_prw "
					+ "where no_rawat='" + tNoRw.getText() + "'\r\n"
					+ "group by permintaan_lab.noorder order by nm_perawatan DESC";
			String StatusBayar = "Belum Lunas";
			try {
				rs = koneksi.prepareStatement(query).executeQuery();
				while (rs != null && rs.next()) {
					double hargaLaborat = Math.round(rs.getDouble("total_byr"));
					double JmlBayarLaborat = hargaLaborat;
					if (rs.getString("stts_bayar").toString().equals("Sudah")) {
						StatusBayar = "Sudah Lunas";
					}
					tabelModelBilling.addRow(new Object[] { rs.getString("nm_perawatan"), 1, hargaLaborat, 0, 0,
							JmlBayarLaborat, StatusBayar, rs.getString("noorder"), "permintaan_pemeriksaan_lab" });
					getBHPLab(rs.getString("noorder"));
					tabelRowLaborat++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		getDataLaboratPa();
	}

	private void getDataLaboratPa() {
		// TODO Auto-generated method stub
		sekuel Sequel = new sekuel();
		if (Sequel.cariInteger("Select count(no_rawat) from permintaan_labpa where no_rawat=?", tNoRw.getText()) > 0) {
			tabelModelBilling.addRow(new Object[] { "Tindakan Penujang Laboratorium PA" });
			Connection koneksi = koneksiDB.condb();
			ResultSet rs;

			String query = "Select permintaan_pemeriksaan_labpa.noorder,tgl_permintaan,nm_perawatan,total_byr,stts_bayar from permintaan_labpa "
					+ "inner join permintaan_pemeriksaan_labpa on permintaan_labpa.noorder=permintaan_pemeriksaan_labpa.noorder "
					+ "inner join jns_perawatan_lab on jns_perawatan_lab.kd_jenis_prw=permintaan_pemeriksaan_labpa.kd_jenis_prw "
					+ "where no_rawat='" + tNoRw.getText() + "'\r\n"
					+ "group by permintaan_labpa.noorder order by nm_perawatan DESC";
			String StatusBayar = "Belum Lunas";
			try {
				rs = koneksi.prepareStatement(query).executeQuery();
				while (rs != null && rs.next()) {
					double hargaLaborat = Math.round(rs.getDouble("total_byr"));
					double JmlBayarLaborat = hargaLaborat;
					if (rs.getString("stts_bayar").toString().equals("Sudah")) {
						StatusBayar = "Sudah Lunas";
					}
					tabelModelBilling.addRow(new Object[] { rs.getString("nm_perawatan"), 1, hargaLaborat, 0, 0,
							JmlBayarLaborat, rs.getString("stts_bayar"), rs.getString("noorder"),
							"permintaan_pemeriksaan_labpa" });
					tabelRowLaborat++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void SetNoRm(String noRawat) {
		sekuel Sequel = new sekuel();

		String noRekamMedik = Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=?", noRawat);
		String tgl_registrasi = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", noRawat);

		tNoRw.setText(noRawat);
		tNoRM.setText(noRekamMedik);
		tPasien.setText(Sequel.cariIsi(
				"select nm_pasien from reg_periksa Inner join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where no_rawat=?",
				noRawat));
		tAlamatPasien.setText(Sequel.cariIsi(
				"select concat(alamat,', ',nm_kel,', ',nm_kec,', ',nm_kab,', ',nm_prop) as alamat from reg_periksa "
						+ "Inner join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis\r\n"
						+ "Inner join kelurahan on kelurahan.kd_kel=pasien.kd_kel\r\n"
						+ "Inner join kecamatan on kecamatan.kd_kec=pasien.kd_kec\r\n"
						+ "Inner Join kabupaten on kabupaten.kd_kab=pasien.kd_kab\r\n"
						+ "Inner join propinsi on propinsi.kd_prop=pasien.kd_prop where no_rawat=?",
				noRawat));
		tDokter.setText(Sequel.cariIsi(
				"select nm_dokter from reg_periksa Inner join dokter on dokter.kd_dokter=reg_periksa.kd_dokter where no_rawat=?",
				noRawat));
		tpoli.setText(Sequel.cariIsi(
				"select nm_poli from reg_periksa Inner join poliklinik on poliklinik.kd_poli=reg_periksa.kd_poli where no_rawat=?",
				noRawat));
		tPenjab.setText(Sequel.cariIsi(
				"select png_jawab from reg_periksa Inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where no_rawat=?",
				noRawat));
		tKd_pj.setText(Sequel.cariIsi(
				"select reg_periksa.kd_pj from reg_periksa Inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where no_rawat=?",
				noRawat));
		validasi Valid = new validasi();
		Valid.SetTgl(DTPTgl, tgl_registrasi);
		margin = Sequel
				.cariIsiAngka("Select hargajual from set_harga_obat_ralan where kd_pj='" + tKd_pj.getText() + "'");
		getloadDataTableBilling();
		CountPayBill();
		CountInsurance();
		CountDiscount();
	}

	private void getDataExtends() {

		ResultSet rs = null;
		double harga;
		PreparedStatement pstambahan;
		Connection koneksi = koneksiDB.condb();
		sekuel Sequel = new sekuel();
		if (Sequel.cariInteger("Select count(no_rawat) from tambahan_biaya_ralan where no_rawat=?",
				tNoRw.getText()) > 0) {
			tabelModelBilling.addRow(new Object[] { " Biaya Lain-lain" });
			try {
				pstambahan = koneksi.prepareStatement(
						"select nama_biaya, besar_biaya,status_bayar from tambahan_biaya_ralan where no_rawat=?");
				try {
					pstambahan.setString(1, tNoRw.getText());
					rs = pstambahan.executeQuery();
					String StatusBayar = "Belum Bayar";

					while (rs.next()) {

						if (rs.getString("status_bayar").toString().equals("Sudah Lunas")) {
							StatusBayar = "Sudah Lunas";
						}

						tabelModelBilling.addRow(new Object[] { rs.getString("nama_biaya"), 1,
								rs.getDouble("besar_biaya"), 0, 0, rs.getDouble("besar_biaya"), StatusBayar,
								tNoRw.getText(), "tambahan_biaya_ralan" });
//						tabelRowTindakan++;
					}
				} catch (Exception e) {
					System.out.println("Notifikasi : " + e);
				} finally {
					if (rs != null) {
						rs.close();
					}
					if (pstambahan != null) {
						pstambahan.close();
					}
				}
			} catch (Exception e) {
				System.out.println("Notifikasi : " + e);
			}
		}
	}

	public void getloadDataTableBilling() {
		tabelModelBilling.setRowCount(0);
		if (cbxTindakan.isSelected()) {
			getDataTindakan();
		}
		if (cbxObat.isSelected()) {
			getDataObat();
		}
		if (chckbxRadiologi.isSelected()) {
			getDataRadiologi();
		}
		if (chckbxLaboratorium.isSelected()) {
			getDataLaborat();
		}
		getDataExtends();
	}
}
