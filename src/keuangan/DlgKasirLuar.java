package keuangan;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dirgahayu.DlgKasirRalan;
import fungsi.ColorTable;
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

public class DlgKasirLuar extends javax.swing.JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			DlgKasirLuar dialog = new DlgKasirLuar(new javax.swing.JFrame(), true);
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
	private TextBox tPasien;
	private Label jLabelTanggal;
	private Tanggal DTPTgl;
	private ScrollPane ScrollTabs;
	private Table tbBilling;
	private panelisi panelPermintaan;
	private TextBox tDokter;
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
	private AbstractButton chckbxRadiologi;
	private JCheckBox chckbxLaboratorium;
	private JRadioButton rdbtnCash;
	private JRadioButton rdbtnPiutang;
	private ButtonGroup btnGrp;
	private TextBox tKd_pj;
	private ColorTable renderer;
	private double margin=0;
	public DlgKasirLuar(java.awt.Frame parent, boolean modal) {
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

		panelDataTableBilling = new panelisi();
		panelDataTableBilling.setPreferredSize(new java.awt.Dimension(800, 350));

		ScrollTabs = new widget.ScrollPane();
		ScrollTabs.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollTabs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
		ScrollTabs.setName("ScrollTabs");
		ScrollTabs.setPreferredSize(new java.awt.Dimension(800, 300));
		ScrollTabs.setOpaque(true);

		TabKasir.addTab("Data Tagihan", panelDatBilling);
		panelDataTableBilling.setLayout(new GridLayout(0, 1, 0, 10));

		tbBilling = new widget.Table();
		tbBilling.setToolTipText("");
		tbBilling.setName("tbBilling");
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
		renderer=new ColorTable();
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

		Label jLabelPasien = new Label();
		jLabelPasien.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelPasien.setBounds(10, 46, 78, 23);
		jLabelPasien.setText("Nama Pasien");
		panelGlassTop.add(jLabelPasien);

		tPasien = new TextBox();
		tPasien.setBounds(131, 45, 230, 23);
		tPasien.setEditable(false);
		tPasien.setHighlighter(null);
		tPasien.setName("TPasien");
		tPasien.setPreferredSize(new java.awt.Dimension(210, 23));
		panelGlassTop.add(tPasien);

		jLabelTanggal = new Label();
		jLabelTanggal.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelTanggal.setBounds(421, 46, 41, 23);
		jLabelTanggal.setText("Tanggal ");
		panelGlassTop.add(jLabelTanggal);

		DTPTgl = new Tanggal();
		DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-09-2020" }));
		DTPTgl.setDisplayFormat("dd-MM-yyyy");
		DTPTgl.setBounds(542, 46, 139, 23);
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

		Label jLabelpenjab = new Label();
		jLabelpenjab.setText("Penjamin");
		jLabelpenjab.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelpenjab.setBounds(421, 79, 78, 23);
		panelGlassTop.add(jLabelpenjab);

		tPenjab = new TextBox();
		tPenjab.setEditable(false);
		tPenjab.setHighlighter(null);
		tPenjab.setText("UMUM");
		tPenjab.setBounds(640, 78, 139, 23);
		panelGlassTop.add(tPenjab);

		Label jLabelAlamatPasien = new Label();
		jLabelAlamatPasien.setText("Alamat Pasien");
		jLabelAlamatPasien.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelAlamatPasien.setBounds(10, 80, 78, 23);
		panelGlassTop.add(jLabelAlamatPasien);

		tAlamatPasien = new TextArea();
		tAlamatPasien.setBounds(131, 79, 230, 44);
		tAlamatPasien.setColumns(20);
		tAlamatPasien.setRows(5);
		tAlamatPasien.setEditable(false);
		panelGlassTop.add(tAlamatPasien);

		Button BtnReg = new Button();
		BtnReg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DlgKasirRalan ralan = new DlgKasirRalan(null, false);

			}
		});
		BtnReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
		BtnReg.setMnemonic('4');
		BtnReg.setBounds(371, 11, 28, 23);
		panelGlassTop.add(BtnReg);

		Label jLabelCaraBayar = new Label();
		jLabelCaraBayar.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelCaraBayar.setBounds(421, 117, 64, 23);
		jLabelCaraBayar.setText("Cara Bayar");
		panelGlassTop.add(jLabelCaraBayar);

		btnGrp = new ButtonGroup();

		rdbtnCash = new JRadioButton("Cash");
		rdbtnCash.setBounds(542, 113, 71, 23);
		panelGlassTop.add(rdbtnCash);

		rdbtnPiutang = new JRadioButton("Piutang");
		rdbtnPiutang.setBounds(631, 113, 78, 23);
		panelGlassTop.add(rdbtnPiutang);
		btnGrp.add(rdbtnCash);
		btnGrp.add(rdbtnPiutang);

		Label jLabelAkunPenerimaan = new Label();
		jLabelAkunPenerimaan.setText("Penerimaan");
		jLabelAkunPenerimaan.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelAkunPenerimaan.setBounds(421, 155, 64, 23);
		panelGlassTop.add(jLabelAkunPenerimaan);

		comboBox = new widget.ComboBox();
		comboBox.setEditable(false);
		comboBox.enable(false);
		comboBox.setBounds(542, 156, 167, 22);
		comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jenis Penerimaan" }));
		panelGlassTop.add(comboBox);

		chckbxRadiologi = new JCheckBox("Radiologi");
		chckbxRadiologi.setSelected(true);
		chckbxRadiologi.setBounds(48, 143, 93, 23);
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
		chckbxLaboratorium.setBounds(162, 143, 109, 23);
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
		tKd_pj.setText("A00");
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
		BtnNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Agenda-1-16x16.png")));
		BtnNota.setMnemonic('N');
		BtnNota.setText(" Nota");
		BtnNota.setPreferredSize(new java.awt.Dimension(100, 30));
		BtnNota.setHorizontalTextPosition(SwingConstants.RIGHT);
		BtnNota.setHorizontalAlignment(SwingConstants.LEFT);
		panelGlassBottom.add(BtnNota);

		BtnView = new Button();
		BtnView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png")));
		BtnView.setMnemonic('L');
		BtnView.setText(" Lihat");
		BtnView.setHorizontalTextPosition(SwingConstants.RIGHT);
		BtnView.setHorizontalAlignment(SwingConstants.LEFT);
		BtnNota.setPreferredSize(new java.awt.Dimension(100, 30));
		panelGlassBottom.add(BtnView);
		panelGlassBottom.add(BtnKeluar);
		getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

		pack();
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
			String field="";
			boolean succes=false;
			if (rdbtnCash.isSelected() == true || rdbtnPiutang.isSelected() == true) {
				if (!comboBox.getSelectedItem().toString().equals("Pilih Jenis Penerimaan")) {
					for (int i = 0; i < tbBilling.getRowCount(); i++) {
						if (tbBilling.getValueAt(i, 2) != null) {
							//System.out.println("pk"+tbBilling.getValueAt(i, 7)+"-Table:"+tbBilling.getValueAt(i, 8));
							table=tbBilling.getValueAt(i, 8).toString();
							if(tbBilling.getValueAt(i, 8).toString().equals("detail_reg_radiologi")) {
								condition=" no_reg='"+tNoRw.getText()+"'";
								field="status_bayar='Sudah Lunas'";
							}else if(tbBilling.getValueAt(i, 8).toString().equals("detail_reg_lab")) {
								condition="no_reg='"+tbBilling.getValueAt(i, 7).toString()+"'";
								field="status_bayar='Lunas'";
							}
							if(Sequel.mengedittf(table,condition,field)==true) {
								succes=true;
							}else {
								succes=false;
								
							}
						}
					}
					
					if(succes==true) {
						Sequel.mengedit("reg_noRm","no_reg='"+tNoRw.getText()+"'","status_bayar='Lunas'");
						Sequel.Commit();
						JOptionPane.showMessageDialog(rootPane, "Pembayaran Berasil disimpan");
						getloadDataTableBilling();
					}else {
						Sequel.RollBack();
					}
					Sequel.AutoComitTrue();
					
				}else {
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
			}else {
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
	private Button BtnView;
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

	

	public void getDataRadiologi() {
		sekuel Sequel = new sekuel();
		if (Sequel.cariInteger("Select count(no_reg) from detail_reg_radiologi where no_reg=?",
				tNoRw.getText()) > 0) {
			tabelModelBilling.addRow(new Object[] { "Tindakan Penujang Radiologi" });
			Connection koneksi = koneksiDB.condb();
			ResultSet rs;
			String query = "Select idDetailRad,nm_perawatan,total_bayar,status_bayar from detail_reg_radiologi "
					+ "inner join jns_perawatan_radiologi on jns_perawatan_radiologi.kd_jenis_prw=detail_reg_radiologi.kd_perawatan "
					+ "where no_reg='" + tNoRw.getText() + "'";
			String StatusBayar="Belum Lunas";
			try {
				rs = koneksi.prepareStatement(query).executeQuery();
				while (rs != null && rs.next()) {
					double hargaRadiologi = Math.round(rs.getDouble("total_bayar"));
					double JmlBayarRadiologi = hargaRadiologi;
					if(rs.getString("status_bayar").toString().equals("Sudah Lunas")) {
						StatusBayar="Sudah Lunas";
					}
					tabelModelBilling.addRow(new Object[] { rs.getString("nm_perawatan"), 1, hargaRadiologi, 0, 0,
							JmlBayarRadiologi,StatusBayar, rs.getString("idDetailRad"),
							"detail_reg_radiologi" });
					tabelRowRadiologit++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			String StatusBayar="Belum Lunas";
			try {
				rs = koneksi.prepareStatement(query).executeQuery();
				while (rs != null && rs.next()) {
					double hargaLaborat = Math.round(rs.getDouble("total_byr"));
					double JmlBayarLaborat = hargaLaborat;
					if(rs.getString("stts_bayar").toString().equals("Sudah")) {
						StatusBayar="Sudah Lunas";
					}
					tabelModelBilling.addRow(new Object[] { rs.getString("nm_perawatan"), 1, hargaLaborat, 0, 0,
							JmlBayarLaborat, StatusBayar, rs.getString("noorder"),
							"permintaan_pemeriksaan_lab" });
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
			String StatusBayar="Belum Lunas";
			try {
				rs = koneksi.prepareStatement(query).executeQuery();
				while (rs != null && rs.next()) {
					double hargaLaborat = Math.round(rs.getDouble("total_byr"));
					double JmlBayarLaborat = hargaLaborat;
					if(rs.getString("stts_bayar").toString().equals("Sudah")) {
						StatusBayar="Sudah Lunas";
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
		String tgl_registrasi = Sequel.cariIsi("select tanggal_reg from reg_noRm where no_reg=?", noRawat);
		tNoRw.setText(noRawat);		
		tPasien.setText(Sequel.cariIsi(
				"select nama_pasien from reg_noRm where no_reg=?",
				noRawat));
		tAlamatPasien.setText(Sequel.cariIsi(
				"select concat(alamat,', ',nm_kel,', ',nm_kec,', ',nm_kab,', ',nm_prop) as alamat from reg_noRm "						
						+ "Inner join kelurahan on kelurahan.kd_kel=reg_noRm.kd_kel "
						+ "Inner join kecamatan on kecamatan.kd_kec=reg_noRm.kd_kec "
						+ "Inner Join kabupaten on kabupaten.kd_kab=reg_noRm.kd_kab "
						+ "Inner join propinsi on propinsi.kd_prop=reg_noRm.kd_prov where no_reg=?",
				noRawat));
		tDokter.setText(Sequel.cariIsi(
				"select nm_dokter from reg_noRm Inner join dokter on dokter.kd_dokter=reg_noRm.kd_dokter where no_reg=?",
				noRawat));				
		validasi Valid = new validasi();
		Valid.SetTgl(DTPTgl, tgl_registrasi);
margin=Sequel.cariIsiAngka("Select hargajual from set_harga_obat_ralan where kd_pj='"+tKd_pj.getText()+"'");
		getloadDataTableBilling();
		CountPayBill();
		CountInsurance();
		CountDiscount();
	}
	public void getloadDataTableBilling() {
		tabelModelBilling.setRowCount(0);
		
		if (chckbxRadiologi.isSelected()) {
			getDataRadiologi();
		}
		if (chckbxLaboratorium.isSelected()) {
			getDataLaborat();
		}
	}
}
