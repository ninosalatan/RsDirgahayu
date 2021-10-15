package dirgahayu;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import inventory.DlgCariObat;
import inventory.DlgCopyResep;
import inventory.DlgPemberianObat;
import inventory.DlgPeresepanDokter;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPegawai;
import kepegawaian.DlgCariPetugas;
import keuangan.DlgJnsPerawatanRalan;
import keuangan.Jurnal;
import laporan.DlgBerkasRawat;
import laporan.PanelDiagnosa;
import permintaan.DlgPermintaanLaboratorium;
import permintaan.DlgPermintaanRadiologi;
import rekammedis.MasterImunisasi;
import rekammedis.PanelRiwayat;
import rekammedis.RMDataResumePasien;
import rekammedis.RMPenilaianAwalKeperawatanBayiAnak;
import rekammedis.RMPenilaianAwalKeperawatanGigi;
import rekammedis.RMPenilaianAwalKeperawatanKebidanan;
import rekammedis.RMPenilaianAwalKeperawatanRalan;
import rekammedis.RMTriaseIGD;
import surat.SuratKontrol;
import widget.Button;
import widget.CekBox;
import widget.ComboBox;
import widget.InternalFrame;
import widget.Label;
import widget.PanelBiasa;
import widget.ScrollPane;
import widget.Table;
import widget.Tanggal;
import widget.TextArea;
import widget.TextBox;
import widget.panelisi;

public class DlgRawatInapNew extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7809543101565948345L;
	private final JPanel contentPanel = new JPanel();
	private widget.Table tbRawatDrPr;
	private DefaultTableModel tabModeDrPr;
	private DefaultTableModel tabModePemeriksaan;
	private Table tbPemeriksaan;
	private DefaultTableModel tableModePemeriksaanVaksin;
	private JTable tbPemeriksaanVaksin;
	private DefaultTableModel tabModeObstetri;
	private JTable tbPemeriksaanObstetri;
	private int i;
	private DefaultTableModel tabModeGinekologi;
	private JTable tbPemeriksaanGinekologi;
	private DefaultTableModel TabModeTindakan;
	private JTable tbTindakan;
	private DefaultTableModel TabModeCatatan;
	private JTable tbCatatan;
	private InternalFrame internalFrameMain;
	private JPanel jPanel3;
	private panelisi panelGlass8;
	private Button BtnSimpan;
	private Button BtnBatal;
	private Button BtnHapus;
	private Button BtnEdit;
	private Button BtnKeluar;
	private Label LCount;
	private Label jLabel10;
	private Button BtnAll;
	private Button BtnPrint;
	private InternalFrame internalFrameTindakaDrPetgs;
	private panelisi panelGlassTindakanDokter;
	private PanelBiasa PanelAccor;
	private JTabbedPane tabTindakanDokterPetugas;
	private Button BtnSeekDokter;
	private TextBox TDokter;
	private TextBox KdDok;
	private TextBox TPerawat;
	private Button BtnSeekPetugas;
	private TextBox kdpetugas;
	private JTabbedPane TabMain;
	private ScrollPane Scroll9;
	private ScrollPane Scroll10;
	private PanelBiasa FormInput;
	private TextBox TNoRw;
	private TextBox TNoRM;
	private TextBox TPasien;
	private Tanggal DTPTgl;
	private ComboBox cmbJam;
	private ComboBox cmbMnt;
	private ComboBox cmbDtk;
	private CekBox ChkJln;
	private CekBox ChkAccor;
	private PanelBiasa FormMenu;
	private Button BtnResepObat;
	private Button BtnCopyResep;
	private Button BtnInputObat;
	private Button BtnObatBhp;
	private Button BtnBerkasDigital;
	private Button BtnPermintaanLab;
	private Button BtnPermintaanRad;
	private Button BtnSKDP;
	private Button BtnKamar;
	private Button BtnRujukInternal;
	private Button BtnRujukKeluar;
	private Button BtnResume;
	private Button BtnTriaseIGD;
	private Button BtnCatatan;
	private Button BtnAwalKeperawatan;
	private panelisi panelGlass9;
	private Label jLabel19;
	private Tanggal DTPCari1;
	private Tanggal DTPCari2;
	private TextBox TCariPasien;
	private Button btnPasien;
	private TextBox TCari;
	private Button BtnCari;
	private Button BtnTambahTindakan;
	private Button BtnAwalKeperawatanGigi;
	private Button BtnPeniliaianAwalKeperawatanKebidanan;
	private Button BtnAwalKeperawatanBayiAnak;
	private InternalFrame internalFrame5;
	private ScrollPane ScrollVaksin;
	private ScrollPane Scroll3;
	private JPanel PanelInput;
	private JPanel PanelInputVaksin;
	private CekBox ChkInput;
	private panelisi panelGlass12;
	private ComboBox cmbImun;
	private TextArea TKeluhan;
	private ScrollPane scrollPane1;
	private ScrollPane scrollPane2;
	private TextArea TPemeriksaan;
	private TextBox TSuhu;
	private TextBox TTensi;
	private TextBox TTinggi;
	private TextBox TRespirasi;
	private TextBox TBerat;
	private TextBox TNadi;
	private TextBox TGCS;
	private TextBox TAlergi;
	private TextBox Jabatan;
	private ScrollPane scrollPane3;
	private TextArea TPenilaian;
	private ScrollPane scrollPane6;
	private TextArea TindakLanjut;
	private TextArea TInstruksi;
	private ScrollPane scrollPane7;
	private ComboBox cmbKesadaran;
	private TextBox KdPeg;
	private TextBox TPegawai;
	private Button BtnSeekPegawai;
	private InternalFrame internalFramePemeriksaan;
	private CekBox ChkInputVaksn;
	private PanelBiasa panelGlassVaksin;
	private TextBox kdptgsVasksin;
	private TextBox TPegawaiVk;
	private Button BtnSeekPetugasVaksin;
	private ComboBox cmbImunVaksin;
	private TextArea TxtareaSubject;
	private TextArea TxtareaObject;
	private TextBox TSuhuVaksin;
	private TextBox TtensiVaksn;
	private TextBox TtinggiVaksn;
	private TextBox TRespirasiVaksn;
	private TextBox TKgVaksn;
	private TextBox TNadiVaksn;
	private TextBox TGCSVaksin;
	private TextBox TAlegiVaksn;
	private TextBox TlinkardadaVksn;
	private TextBox Tkdvksin;
	private TextBox Tvksin;
	private Button BtnAddVaksin;
	private InternalFrame internalFrame6;
	private ScrollPane Scroll4;
	private JPanel PanelInput1;
	private CekBox ChkInput1;
	private panelisi panelGlass13;
	private TextBox TTinggi_uteri;
	private TextBox TLetak;
	private TextBox TKualitas_dtk;
	private ComboBox cmbPanggul;
	private TextBox TTebal;
	private TextBox TDenyut;
	private TextBox TDenominator;
	private TextBox TKualitas_mnt;
	private ComboBox cmbFeto;
	private ComboBox cmbJanin;
	private ComboBox cmbKetuban;
	private TextBox TPortio;
	private TextBox TVulva;
	private ComboBox cmbKontraksi;
	private ComboBox cmbAlbus;
	private ComboBox cmbFluksus;
	private TextBox TPembukaan;
	private TextBox TPenurunan;
	private ComboBox cmbDalam;
	private ComboBox cmbArah;
	private InternalFrame internalFrame7;
	private ScrollPane Scroll5;
	private JPanel PanelInput2;
	private CekBox ChkInput2;
	private panelisi panelGlass14;
	private TextBox TInspeksiVulva;
	private TextBox TAdnexaKanan;
	private ComboBox cmbMobilitas;
	private TextBox TInspekuloGine;
	private TextBox TPortioInspekulo;
	private TextBox TCavumUteri;
	private ComboBox cmbFluorGine;
	private TextBox TInspeksi;
	private ComboBox cmbFluxusGine;
	private TextBox TVulvaInspekulo;
	private ComboBox cmbNyeriTekan;
	private TextBox TPortioDalam;
	private TextBox TBentuk;
	private TextBox TSondage;
	private TextBox TAdnexaKiri;
	private TextBox TCavumDouglas;
	private TextBox TUkuran;
	private PanelRiwayat panelResume1;
	private PanelDiagnosa panelDiagnosa1;
	private InternalFrame internalFrame8;
	private JPanel PanelInput3;
	private panelisi panelGlass15;
	private ScrollPane scrollPane4;
	private TextBox KdDok3;
	private Button BtnSeekDokter3;
	private TextArea Catatan;
	private CekBox ChkInput3;
	private TextBox TDokter3;
	private ScrollPane Scroll11;

	private sekuel Sequel = new sekuel();
	private validasi Valid = new validasi();
	private Connection koneksi = koneksiDB.condb();
	private DlgPasien pasien = new DlgPasien(null, false);
	private DlgCariDokter dokter = new DlgCariDokter(null, false);
	public DlgCariPetugas petugas = new DlgCariPetugas(null, false);
	public DlgCariPegawai pegawai = new DlgCariPegawai(null, false);
	private MasterImunisasi vaksin = new MasterImunisasi(null, false);
	private PreparedStatement ps, ps2, ps3, ps4, ps5, ps6, pstindakan, psset_tarif, psrekening;
	private int jmlparsial = 0, jml = 0, index = 0;
	private final Properties prop = new Properties();
	private boolean[] pilih;
	private String[] kode, nama, kategori, jnsBayar;
	private double[] totaltnd, bagianrs, bhp, jmdokter, jmperawat, kso, menejemen;
	private boolean sukses = false;
	private ResultSet rstindakan, rsset_tarif, rsrekening;
	private double ttljmdokter = 0, ttljmperawat = 0, ttlkso = 0, ttljasasarana = 0, ttlbhp = 0, ttlmenejemen = 0,
			ttlpendapatan = 0;
	private String aktifkanparsial = "no", kode_poli, kd_pj = "", poli_ralan = "No", cara_bayar_ralan = "No",
			Suspen_Piutang_Tindakan_Ranap = "", Tindakan_Ranap = "", Beban_Jasa_Medik_Dokter_Tindakan_Ranap = "",
			Utang_Jasa_Medik_Dokter_Tindakan_Ranap = "", Beban_Jasa_Medik_Paramedis_Tindakan_Ranap = "",
			Utang_Jasa_Medik_Paramedis_Tindakan_Ranap = "", Beban_KSO_Tindakan_Ranap = "",
			Utang_KSO_Tindakan_Ranap = "", Beban_Jasa_Sarana_Tindakan_Ranap = "", Utang_Jasa_Sarana_Tindakan_Ranap = "",
			HPP_BHP_Tindakan_Ranap = "", Persediaan_BHP_Tindakan_Ranap = "", Beban_Jasa_Menejemen_Tindakan_Ranap = "",
			Utang_Jasa_Menejemen_Tindakan_Ranap = "";
	private TextBox TPoli;
	private TextBox tLkrKepala;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			DlgRawatInapNew dialog = new DlgRawatInapNew(new javax.swing.JFrame(), true);
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
	public DlgRawatInapNew(java.awt.Frame parent, boolean modal) {

//		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setLayout(new FlowLayout());
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		getContentPane().add(contentPanel, BorderLayout.CENTER);
		super(parent, modal);
		initComponents();
		

		this.setLocation(8, 1);
		setSize(885, 674);
		

		try {
			psrekening = koneksi.prepareStatement("select * from set_akun_ranap");
			try {
				rsrekening = psrekening.executeQuery();
				while (rsrekening.next()) {
					Suspen_Piutang_Tindakan_Ranap = rsrekening.getString("Suspen_Piutang_Tindakan_Ranap");
					Tindakan_Ranap = rsrekening.getString("Tindakan_Ranap");
					Beban_Jasa_Medik_Dokter_Tindakan_Ranap = rsrekening
							.getString("Beban_Jasa_Medik_Dokter_Tindakan_Ranap");
					Utang_Jasa_Medik_Dokter_Tindakan_Ranap = rsrekening
							.getString("Utang_Jasa_Medik_Dokter_Tindakan_Ranap");
					Beban_Jasa_Medik_Paramedis_Tindakan_Ranap = rsrekening
							.getString("Beban_Jasa_Medik_Paramedis_Tindakan_Ranap");
					Utang_Jasa_Medik_Paramedis_Tindakan_Ranap = rsrekening
							.getString("Utang_Jasa_Medik_Paramedis_Tindakan_Ranap");
					Beban_KSO_Tindakan_Ranap = rsrekening.getString("Beban_KSO_Tindakan_Ranap");
					Utang_KSO_Tindakan_Ranap = rsrekening.getString("Utang_KSO_Tindakan_Ranap");
					Beban_Jasa_Sarana_Tindakan_Ranap = rsrekening.getString("Beban_Jasa_Sarana_Tindakan_Ranap");
					Utang_Jasa_Sarana_Tindakan_Ranap = rsrekening.getString("Utang_Jasa_Sarana_Tindakan_Ranap");
					Beban_Jasa_Menejemen_Tindakan_Ranap = rsrekening.getString("Beban_Jasa_Menejemen_Tindakan_Ranap");
					Utang_Jasa_Menejemen_Tindakan_Ranap = rsrekening.getString("Utang_Jasa_Menejemen_Tindakan_Ranap");
					HPP_BHP_Tindakan_Ranap = rsrekening.getString("HPP_BHP_Tindakan_Ranap");
					Persediaan_BHP_Tindakan_Ranap = rsrekening.getString("Persediaan_BHP_Tindakan_Ranap");
				}
			} catch (Exception e) {
				System.out.println("Notif Rekening : " + e);
			} finally {
				if (rsrekening != null) {
					rsrekening.close();
				}
				if (psrekening != null) {
					psrekening.close();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			psset_tarif = koneksi.prepareStatement("select * from set_tarif");
			try {
				rsset_tarif = psset_tarif.executeQuery();
				if (rsset_tarif.next()) {
					poli_ralan = rsset_tarif.getString("poli_ralan");
					cara_bayar_ralan = rsset_tarif.getString("cara_bayar_ralan");
				} else {
					poli_ralan = "Yes";
					cara_bayar_ralan = "Yes";
				}
			} catch (Exception e) {
				System.out.println("Notifikasi : " + e);
			} finally {
				if (rsset_tarif != null) {
					rsset_tarif.close();
				}
				if (psset_tarif != null) {
					psset_tarif.close();
				}
			}
		} catch (Exception e) {
			System.out.println("Notifikasi : " + e);
		}
		pasien.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
				if (akses.getform().equals("DlgRawatJalan")) {
					if (pasien.getTable().getSelectedRow() != -1) {
						TCariPasien.setText(
								pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
					}
					if (pasien.getTable2().getSelectedRow() != -1) {
						TCariPasien.setText(
								pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(), 1).toString());
					}
					if (pasien.getTable3().getSelectedRow() != -1) {
						TCariPasien.setText(
								pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(), 1).toString());
					}
					TCariPasien.requestFocus();
				}
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

		pasien.getTable().addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (akses.getform().equals("DlgRawatJalan")) {
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						pasien.dispose();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		pasien.getTable2().addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (akses.getform().equals("DlgRawatJalan")) {
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						pasien.dispose();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		pasien.getTable3().addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (akses.getform().equals("DlgRawatJalan")) {
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						pasien.dispose();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		dokter.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
				if (akses.getform().equals("DlgRawatJalan")) {
					if (dokter.getTable().getSelectedRow() != -1) {
						if (TabMain.getSelectedIndex() == 0) {
							KdDok.setText(
									dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
							TDokter.setText(
									dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
							KdDok.requestFocus();
						}  else if (TabMain.getSelectedIndex() == 6) {
							KdDok3.setText(
									dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
							TDokter3.setText(
									dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
							KdDok3.requestFocus();
						}
					}
				}
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

		petugas.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
				if (akses.getform().equals("DlgRawatJalan")) {
					if (petugas.getTable().getSelectedRow() != -1) {
						if (TabMain.getSelectedIndex() == 0) {
							kdpetugas.setText(
									petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
							TPerawat.setText(
									petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
							kdpetugas.requestFocus();
						}  else if (TabMain.getSelectedIndex() == 2) {
							kdptgsVasksin.setText(
									petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
							TPegawaiVk.setText(
									petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
							kdptgsVasksin.requestFocus();

						}
					}

				}
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

		pegawai.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
				if (akses.getform().equals("DlgRawatJalan")) {
					if (pegawai.getTable().getSelectedRow() != -1) {
						KdPeg.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
						TPegawai.setText(
								pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
						Jabatan.setText(
								pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 3).toString());
						KdPeg.requestFocus();
					}
				}
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

		panelDiagnosa1.TabRawat.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LCount.setText(panelDiagnosa1.getRecord() + "");
			}

			@Override
			public void mousePressed(MouseEvent e) {
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
		});

		panelDiagnosa1.tbDiagnosaPasien.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (panelDiagnosa1.tbDiagnosaPasien.getSelectedRow() != -1) {
					TNoRw.setText(panelDiagnosa1.tbDiagnosaPasien
							.getValueAt(panelDiagnosa1.tbDiagnosaPasien.getSelectedRow(), 2).toString());
					TNoRM.setText(panelDiagnosa1.tbDiagnosaPasien
							.getValueAt(panelDiagnosa1.tbDiagnosaPasien.getSelectedRow(), 3).toString());
					TPasien.setText(panelDiagnosa1.tbDiagnosaPasien
							.getValueAt(panelDiagnosa1.tbDiagnosaPasien.getSelectedRow(), 4).toString());
				}
			}
		});

		panelDiagnosa1.tbDiagnosaPasien.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (panelDiagnosa1.tbDiagnosaPasien.getSelectedRow() != -1) {
					TNoRw.setText(panelDiagnosa1.tbDiagnosaPasien
							.getValueAt(panelDiagnosa1.tbDiagnosaPasien.getSelectedRow(), 2).toString());
					TNoRM.setText(panelDiagnosa1.tbDiagnosaPasien
							.getValueAt(panelDiagnosa1.tbDiagnosaPasien.getSelectedRow(), 3).toString());
					TPasien.setText(panelDiagnosa1.tbDiagnosaPasien
							.getValueAt(panelDiagnosa1.tbDiagnosaPasien.getSelectedRow(), 4).toString());
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
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
		});

		panelDiagnosa1.tbTindakanPasien.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (panelDiagnosa1.tbTindakanPasien.getSelectedRow() != -1) {
					TNoRw.setText(panelDiagnosa1.tbTindakanPasien
							.getValueAt(panelDiagnosa1.tbTindakanPasien.getSelectedRow(), 2).toString());
					TNoRM.setText(panelDiagnosa1.tbTindakanPasien
							.getValueAt(panelDiagnosa1.tbTindakanPasien.getSelectedRow(), 3).toString());
					TPasien.setText(panelDiagnosa1.tbTindakanPasien
							.getValueAt(panelDiagnosa1.tbTindakanPasien.getSelectedRow(), 4).toString());
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
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
		});

		panelDiagnosa1.tbTindakanPasien.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (panelDiagnosa1.tbTindakanPasien.getSelectedRow() != -1) {
					TNoRw.setText(panelDiagnosa1.tbTindakanPasien
							.getValueAt(panelDiagnosa1.tbTindakanPasien.getSelectedRow(), 2).toString());
					TNoRM.setText(panelDiagnosa1.tbTindakanPasien
							.getValueAt(panelDiagnosa1.tbTindakanPasien.getSelectedRow(), 3).toString());
					TPasien.setText(panelDiagnosa1.tbTindakanPasien
							.getValueAt(panelDiagnosa1.tbTindakanPasien.getSelectedRow(), 4).toString());
				}
			}
		});

		vaksin.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
				if (vaksin.getTable().getSelectedRow() != -1) {
					Tkdvksin.setText(vaksin.getTable().getValueAt(vaksin.getTable().getSelectedRow(), 0).toString());
					Tvksin.setText(vaksin.getTable().getValueAt(vaksin.getTable().getSelectedRow(), 1).toString());
					tampilPemeriksaanVaksin();
				}
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

		vaksin.getTable().addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					vaksin.dispose();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});


	}

	private void initComponents() {
		// TODO Auto-generated method stub
		tbRawatDrPr = new widget.Table();
		tabModeDrPr = new DefaultTableModel(null,
				new Object[] { "P", "No.Rawat", "No.R.M.", "Nama Pasien", "Perawatan/Tindakan", "Kode Dokter",
						"Dokter Yg Menangani", "NIP", "Petugas Yg Menangani", "Tgl.Rawat", "Jam Rawat", "Biaya", "Kode",
						"Tarif Dokter", "Tarif Petugas", "KSO", "Jasa Sarana", "BHP", "Menejemen" }) {
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				boolean a = false;
				if (colIndex == 0) {
					a = true;
				}
				return a;
			}

			Class[] types = new Class[] { java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		tbRawatDrPr.setModel(tabModeDrPr);
		tbRawatDrPr.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tbRawatDrPr.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < tabModeDrPr.getColumnCount(); i++) {
			TableColumn column = tbRawatDrPr.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(20);
			} else if (i == 1) {
				column.setPreferredWidth(105);
			} else if (i == 2) {
				column.setPreferredWidth(70);
			} else if (i == 3) {
				column.setPreferredWidth(180);
			} else if (i == 4) {
				column.setPreferredWidth(180);
			} else if (i == 5) {
				column.setPreferredWidth(90);
			} else if (i == 6) {
				column.setPreferredWidth(180);
			} else if (i == 7) {
				column.setPreferredWidth(90);
			} else if (i == 8) {
				column.setPreferredWidth(180);
			} else if (i == 9) {
				column.setPreferredWidth(80);
			} else if (i == 10) {
				column.setPreferredWidth(75);
			} else if (i == 11) {
				column.setPreferredWidth(90);
			} else if (i == 12) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else if (i == 13) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else if (i == 14) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else if (i == 15) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else if (i == 16) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else if (i == 17) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else if (i == 18) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			}
		}
		tbRawatDrPr.setDefaultRenderer(Object.class, new WarnaTable());
		tbPemeriksaan = new widget.Table();
		tabModePemeriksaan = new DefaultTableModel(null,
				new Object[] { "P", "No.Rawat", "No.R.M.", "Nama Pasien", "Tgl.Rawat", "Jam", "Suhu(C)", "Tensi",
						"Nadi(/menit)", "Respirasi(/menit)", "Tinggi(Cm)", "Berat(Kg)", "GCS(E,V,M)", "Kesadaran",
						"Subjek", "Objek", "Alergi", "Imun Ke", "Plan", "Asesmen", "Instruksi", "NIP",
						"Dokter/Paramedis", "Profesi/Jabatan" }) {
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				boolean a = false;
				if (colIndex == 0) {
					a = true;
				}
				return a;
			}

			Class[] types = new Class[] { java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		tbPemeriksaan.setModel(tabModePemeriksaan);
		tbPemeriksaan.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tbPemeriksaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < 24; i++) {
			TableColumn column = tbPemeriksaan.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(20);
			} else if (i == 1) {
				column.setPreferredWidth(105);
			} else if (i == 2) {
				column.setPreferredWidth(70);
			} else if (i == 3) {
				column.setPreferredWidth(150);
			} else if (i == 4) {
				column.setPreferredWidth(65);
			} else if (i == 5) {
				column.setPreferredWidth(50);
			} else if (i == 6) {
				column.setPreferredWidth(45);
			} else if (i == 7) {
				column.setPreferredWidth(60);
			} else if (i == 8) {
				column.setPreferredWidth(73);
			} else if (i == 9) {
				column.setPreferredWidth(90);
			} else if (i == 10) {
				column.setPreferredWidth(63);
			} else if (i == 11) {
				column.setPreferredWidth(57);
			} else if (i == 12) {
				column.setPreferredWidth(64);
			} else if (i == 13) {
				column.setPreferredWidth(90);
			} else if (i == 14) {
				column.setPreferredWidth(180);
			} else if (i == 15) {
				column.setPreferredWidth(180);
			} else if (i == 16) {
				column.setPreferredWidth(130);
			} else if (i == 17) {
				column.setPreferredWidth(50);
			} else if (i == 18) {
				column.setPreferredWidth(180);
			} else if (i == 19) {
				column.setPreferredWidth(180);
			} else if (i == 20) {
				column.setPreferredWidth(180);
			} else if (i == 21) {
				column.setPreferredWidth(80);
			} else if (i == 22) {
				column.setPreferredWidth(150);
			} else if (i == 23) {
				column.setPreferredWidth(100);
			}
		}
		tbPemeriksaan.setDefaultRenderer(Object.class, new WarnaTable());

		tableModePemeriksaanVaksin = new DefaultTableModel(null,
				new Object[] { "P", "No.Rawat", "Jenis Vaksin", "Imun Ke", "Tgl.Rawat", "Jam", "Suhu(C)", "Tensi",
						"Nadi(/menit)", "Respirasi(/menit)", "Tinggi(Cm)", "Berat(Kg)", "GCS(E,V,M)", "Subjek", "Objek",
						"Alergi", "Linkar dada", "Petugas" }) {
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				boolean a = false;
				if (colIndex == 0) {
					a = true;
				}
				return a;
			}

			Class[] types = new Class[] { java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		tbPemeriksaanVaksin = new widget.Table();
		tbPemeriksaanVaksin.setModel(tableModePemeriksaanVaksin);
		tbPemeriksaanVaksin.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tbPemeriksaanVaksin.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < tableModePemeriksaanVaksin.getColumnCount(); i++) {
			TableColumn column = tbPemeriksaanVaksin.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(20);
			} else if (i == 1) {
				column.setPreferredWidth(120);
			} else {
				column.setPreferredWidth(70);
			}
		}
		tbPemeriksaanVaksin.setDefaultRenderer(Object.class, new WarnaTable());
		tbPemeriksaanObstetri=new widget.Table();
		tabModeObstetri = new DefaultTableModel(null,
				new Object[] { "P", "No.Rawat", "No.R.M", "Nama Pasien", "Tgl.Rawat", "Jam Rawat", "Tinggi Fundus",
						"Janin", "Letak", "Panggul", "Denyut", "Kontraksi", "Kualitas Mnt", "Kualitas Detik", "Fluksus",
						"Albus", "Vulva", "Portio", "Dalam", "Tebal", "Arah", "Pembukaan", "Penurunan", "Denominator",
						"Ketuban", "Feto" }) {
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				boolean a = false;
				if (colIndex == 0) {
					a = true;
				}
				return a;
			}

			Class[] types = new Class[] { java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};

		tbPemeriksaanObstetri.setModel(tabModeObstetri);
		tbPemeriksaanObstetri.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tbPemeriksaanObstetri.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (i = 0; i < 26; i++) {
			TableColumn column = tbPemeriksaanObstetri.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(20);
			} else if (i == 1) {
				column.setPreferredWidth(105);
			} else if (i == 2) {
				column.setPreferredWidth(70);
			} else if (i == 3) {
				column.setPreferredWidth(180);
			} else if (i == 4) {
				column.setPreferredWidth(80);
			} else if (i == 5) {
				column.setPreferredWidth(70);
			} else if (i == 6) {
				column.setPreferredWidth(80);
			} else if (i == 7) {
				column.setPreferredWidth(60);
			} else if (i == 8) {
				column.setPreferredWidth(60);
			} else if (i == 9) {
				column.setPreferredWidth(60);
			} else if (i == 10) {
				column.setPreferredWidth(60);
			} else if (i == 11) {
				column.setPreferredWidth(60);
			} else if (i == 12) {
				column.setPreferredWidth(70);
			} else if (i == 13) {
				column.setPreferredWidth(80);
			} else if (i == 14) {
				column.setPreferredWidth(50);
			} else if (i == 15) {
				column.setPreferredWidth(40);
			} else if (i == 16) {
				column.setPreferredWidth(170);
			} else if (i == 17) {
				column.setPreferredWidth(170);
			} else if (i == 18) {
				column.setPreferredWidth(60);
			} else if (i == 19) {
				column.setPreferredWidth(50);
			} else if (i == 20) {
				column.setPreferredWidth(60);
			} else if (i == 21) {
				column.setPreferredWidth(170);
			} else if (i == 22) {
				column.setPreferredWidth(170);
			} else if (i == 23) {
				column.setPreferredWidth(170);
			} else if (i == 24) {
				column.setPreferredWidth(50);
			} else if (i == 25) {
				column.setPreferredWidth(70);
			}
		}
		tbPemeriksaanObstetri.setDefaultRenderer(Object.class, new WarnaTable());

		tabModeGinekologi = new DefaultTableModel(null,
				new Object[] { "P", "No.Rawat", "No.R.M", "Nama Pasien", "Tgl.Rawat", "Jam Rawat", "Inpeksi",
						"Inspeksi Vulva/Uretra/Vagina", "Inspekulo", "Fluxus", "Fluor Albus", "Inspekulo Vulva/Vagina",
						"Inspekulo Portio", "Inspekulo Sondage", "Pemeriksaan Dalam Portio", "Pemeriksaan Dalam Bentuk",
						"Pemeriksaan Dalam Cavum Uteri", "Mobilitas", "Ukuran Cavum Uteri", "Nyeri Tekan",
						"Pemeriksaan Dalam Adnexa Kanan", "Pemeriksaan Dalam Adnexa Kiri",
						"Pemeriksaan Dalam Cavum Douglas" }) {
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				boolean a = false;
				if (colIndex == 0) {
					a = true;
				}
				return a;
			}

			Class[] types = new Class[] { java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class

			};

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		tbPemeriksaanGinekologi= new widget.Table();
		tbPemeriksaanGinekologi.setModel(tabModeGinekologi);
		tbPemeriksaanGinekologi.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tbPemeriksaanGinekologi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (i = 0; i < 23; i++) {
			TableColumn column = tbPemeriksaanGinekologi.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(20);
			} else if (i == 1) {
				column.setPreferredWidth(105);
			} else if (i == 2) {
				column.setPreferredWidth(70);
			} else if (i == 3) {
				column.setPreferredWidth(180);
			} else if (i == 4) {
				column.setPreferredWidth(80);
			} else if (i == 5) {
				column.setPreferredWidth(70);
			} else if (i == 6) {
				column.setPreferredWidth(200);
			} else if (i == 7) {
				column.setPreferredWidth(200);
			} else if (i == 8) {
				column.setPreferredWidth(200);
			} else if (i == 9) {
				column.setPreferredWidth(42);
			} else if (i == 10) {
				column.setPreferredWidth(62);
			} else if (i == 11) {
				column.setPreferredWidth(200);
			} else if (i == 12) {
				column.setPreferredWidth(200);
			} else if (i == 13) {
				column.setPreferredWidth(200);
			} else if (i == 14) {
				column.setPreferredWidth(200);
			} else if (i == 15) {
				column.setPreferredWidth(200);
			} else if (i == 16) {
				column.setPreferredWidth(200);
			} else if (i == 17) {
				column.setPreferredWidth(50);
			} else if (i == 18) {
				column.setPreferredWidth(200);
			} else if (i == 19) {
				column.setPreferredWidth(67);
			} else if (i == 20) {
				column.setPreferredWidth(200);
			} else if (i == 21) {
				column.setPreferredWidth(200);
			} else if (i == 22) {
				column.setPreferredWidth(200);
			}
		}
		tbPemeriksaanGinekologi.setDefaultRenderer(Object.class, new WarnaTable());
		TabModeTindakan = new DefaultTableModel(null, new Object[] { "P", "Kode", "Nama Perawatan", "Poli",
				"Tarif/Biaya", "Bagian RS", "BHP", "JM Dokter", "JM Perawat", "KSO", "Menejemen", "Jenis Bayar" }) {
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				boolean a = false;
				if (colIndex == 0) {
					a = true;
				}
				return a;
			}

			Class[] types = new Class[] { java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
					java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
					java.lang.Object.class };

			/*
			 * Class[] types = new Class[] { java.lang.Boolean.class,
			 * java.lang.Object.class, java.lang.Object.class, java.lang.Object.class };
			 */
			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		tbTindakan = new widget.Table();
		tbTindakan.setModel(TabModeTindakan);
		tbTindakan.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tbTindakan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (i = 0; i < TabModeTindakan.getColumnCount(); i++) {
			TableColumn column = tbTindakan.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(20);
			} else if (i == 1) {
				column.setPreferredWidth(90);
			} else if (i == 2) {
				column.setPreferredWidth(420);
			} else if (i == 3) {
				column.setPreferredWidth(150);
			} else if (i == 5) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else if (i == 6) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else if (i == 7) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else if (i == 8) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else if (i == 9) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else if (i == 10) {
				column.setMinWidth(0);
				column.setMaxWidth(0);
			} else if (i == 11) {
				column.setPreferredWidth(90);
			} else {
				column.setPreferredWidth(90);
			}
		}
		tbTindakan.setDefaultRenderer(Object.class, new WarnaTable());
		tbCatatan = new widget.Table();
		TabModeCatatan = new DefaultTableModel(null, new Object[] { "P", "No.Rawat", "No.R.M.", "Nama Pasien",
				"Tanggal", "Jam", "Kode Dokter", "Nama Dokter", "Catatan" }) {
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				boolean a = false;
				if (colIndex == 0) {
					a = true;
				}
				return a;
			}

			Class[] types = new Class[] { java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};
		tbCatatan.setModel(TabModeCatatan);
		tbCatatan.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tbCatatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (i = 0; i < 9; i++) {
			TableColumn column = tbCatatan.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(20);
			} else if (i == 1) {
				column.setPreferredWidth(105);
			} else if (i == 2) {
				column.setPreferredWidth(70);
			} else if (i == 3) {
				column.setPreferredWidth(180);
			} else if (i == 4) {
				column.setPreferredWidth(80);
			} else if (i == 5) {
				column.setPreferredWidth(75);
			} else if (i == 6) {
				column.setPreferredWidth(80);
			} else if (i == 7) {
				column.setPreferredWidth(150);
			} else if (i == 8) {
				column.setPreferredWidth(700);
			}
		}
		tbCatatan.setDefaultRenderer(Object.class, new WarnaTable());

//		TextFiled
		internalFrameMain = new widget.InternalFrame();
		jPanel3 = new javax.swing.JPanel();
		panelGlass8 = new widget.panelisi();
		panelGlass9 = new widget.panelisi();
		jLabel19 = new widget.Label();
		DTPCari1 = new widget.Tanggal();
		Label jLabel21 = new widget.Label();
		DTPCari2 = new widget.Tanggal();
		Label jLabel24 = new widget.Label();
		TCariPasien = new widget.TextBox();
		btnPasien = new widget.Button();
		JSeparator jSeparator5 = new javax.swing.JSeparator();
		Label jLabel6 = new widget.Label();
		TCari = new widget.TextBox();
		BtnCari = new widget.Button();
		BtnTambahTindakan = new widget.Button();
		BtnSimpan = new widget.Button();
		BtnBatal = new widget.Button();
		BtnHapus = new widget.Button();
		BtnEdit = new widget.Button();
		BtnPrint = new widget.Button();
		BtnAll = new widget.Button();
		jLabel10 = new widget.Label();
		LCount = new widget.Label();
		BtnKeluar = new widget.Button();
		TabMain = new javax.swing.JTabbedPane();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);

		internalFrameMain.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)),
				"::[ Perawatan/Tindakan Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11),
				new java.awt.Color(50, 50, 50)));
		internalFrameMain.setName("internalFrameMain");
		internalFrameMain.setLayout(new java.awt.BorderLayout(1, 1));

		jPanel3.setName("jPanel3");
		jPanel3.setOpaque(false);
		jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
		jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

		panelGlass8.setName("panelGlass8");
		panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
		panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

		BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png")));
		BtnSimpan.setMnemonic('S');
		BtnSimpan.setText("Simpan");
		BtnSimpan.setToolTipText("Alt+S");
		BtnSimpan.setName("BtnSimpan");
		BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
		BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnSimpanActionPerformed(evt);
			}
		});
		BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnSimpanKeyPressed(evt);
			}
		});
		panelGlass8.add(BtnSimpan);

		BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png")));
		BtnBatal.setMnemonic('B');
		BtnBatal.setText("Baru");
		BtnBatal.setToolTipText("Alt+B");
		BtnBatal.setName("BtnBatal");
		BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
		BtnBatal.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnBatalActionPerformed(evt);
			}
		});
		BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnBatalKeyPressed(evt);
			}
		});
		panelGlass8.add(BtnBatal);

		BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png")));
		BtnHapus.setMnemonic('H');
		BtnHapus.setText("Hapus");
		BtnHapus.setToolTipText("Alt+H");
		BtnHapus.setName("BtnHapus");
		BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
		BtnHapus.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnHapusActionPerformed(evt);
			}
		});
		BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnHapusKeyPressed(evt);
			}
		});
		panelGlass8.add(BtnHapus);

		BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png")));
		BtnEdit.setMnemonic('G');
		BtnEdit.setText("Ganti");
		BtnEdit.setToolTipText("Alt+G");
		BtnEdit.setName("BtnEdit");
		BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
		BtnEdit.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnEditActionPerformed(evt);
			}
		});
		BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnEditKeyPressed(evt);
			}
		});
		panelGlass8.add(BtnEdit);

		BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png")));
		BtnPrint.setMnemonic('T');
		BtnPrint.setText("Cetak");
		BtnPrint.setToolTipText("Alt+T");
		BtnPrint.setName("BtnPrint");
		BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
		BtnPrint.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnPrintActionPerformed(evt);
			}
		});
		BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnPrintKeyPressed(evt);
			}
		});
		panelGlass8.add(BtnPrint);

		BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png")));
		BtnAll.setMnemonic('M');
		BtnAll.setText("Semua");
		BtnAll.setToolTipText("Alt+M");
		BtnAll.setName("BtnAll");
		BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
		BtnAll.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnAllActionPerformed(evt);
			}
		});
		BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnAllKeyPressed(evt);
			}
		});
		panelGlass8.add(BtnAll);

		jLabel10.setText("Record :");
		jLabel10.setName("jLabel10");
		jLabel10.setPreferredSize(new java.awt.Dimension(95, 30));
		panelGlass8.add(jLabel10);

		LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		LCount.setText("0");
		LCount.setName("LCount");
		LCount.setPreferredSize(new java.awt.Dimension(87, 30));
		panelGlass8.add(LCount);

		BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png")));
		BtnKeluar.setMnemonic('K');
		BtnKeluar.setText("Keluar");
		BtnKeluar.setToolTipText("Alt+K");
		BtnKeluar.setName("BtnKeluar");
		BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
		BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnKeluarActionPerformed(evt);
			}
		});
		BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnKeluarKeyPressed(evt);
			}
		});
		panelGlass8.add(BtnKeluar);

		jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

		panelGlass9.setName("panelGlass9");
		panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
		panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

		jLabel19.setText("Tgl.Rawat :");
		jLabel19.setName("jLabel19");
		jLabel19.setPreferredSize(new java.awt.Dimension(64, 23));
		panelGlass9.add(jLabel19);

		DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
		DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-03-2021" }));
		DTPCari1.setDisplayFormat("dd-MM-yyyy");
		DTPCari1.setName("DTPCari1");
		DTPCari1.setOpaque(false);
		DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
		panelGlass9.add(DTPCari1);

		jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel21.setText("s.d.");
		jLabel21.setName("jLabel21");
		jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
		panelGlass9.add(jLabel21);

		DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
		DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-03-2021" }));
		DTPCari2.setDisplayFormat("dd-MM-yyyy");
		DTPCari2.setName("DTPCari2");
		DTPCari2.setOpaque(false);
		DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
		panelGlass9.add(DTPCari2);

		jLabel24.setText("No.RM :");
		jLabel24.setName("jLabel24");
		jLabel24.setPreferredSize(new java.awt.Dimension(55, 23));
		panelGlass9.add(jLabel24);

		TCariPasien.setName("TCariPasien");
		TCariPasien.setPreferredSize(new java.awt.Dimension(140, 23));
		panelGlass9.add(TCariPasien);

		btnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
		btnPasien.setMnemonic('6');
		btnPasien.setToolTipText("Alt+6");
		btnPasien.setName("btnPasien");
		btnPasien.setPreferredSize(new java.awt.Dimension(28, 23));
		btnPasien.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPasienActionPerformed(evt);
			}
		});
		btnPasien.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				btnPasienKeyPressed(evt);
			}
		});
		panelGlass9.add(btnPasien);

		jSeparator5.setBackground(new java.awt.Color(220, 225, 215));
		jSeparator5.setForeground(new java.awt.Color(220, 225, 215));
		jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jSeparator5.setName("jSeparator5");
		jSeparator5.setOpaque(true);
		jSeparator5.setPreferredSize(new java.awt.Dimension(1, 23));
		panelGlass9.add(jSeparator5);

		jLabel6.setText("Key Word :");
		jLabel6.setName("jLabel6");
		jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
		panelGlass9.add(jLabel6);

		TCari.setName("TCari");
		TCari.setPreferredSize(new java.awt.Dimension(240, 23));
		TCari.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TCariKeyPressed(evt);
			}
		});
		panelGlass9.add(TCari);

		BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png")));
		BtnCari.setMnemonic('6');
		BtnCari.setToolTipText("Alt+6");
		BtnCari.setName("BtnCari");
		BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
		BtnCari.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnCariActionPerformed(evt);
			}
		});
		BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnCariKeyPressed(evt);
			}
		});
		panelGlass9.add(BtnCari);

		BtnTambahTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png")));
		BtnTambahTindakan.setMnemonic('3');
		BtnTambahTindakan.setToolTipText("Alt+3");
		BtnTambahTindakan.setName("BtnTambahTindakan");
		BtnTambahTindakan.setPreferredSize(new java.awt.Dimension(28, 23));
		BtnTambahTindakan.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnTambahTindakanActionPerformed(evt);
			}
		});
		panelGlass9.add(BtnTambahTindakan);

		jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

		internalFrameMain.add(jPanel3, java.awt.BorderLayout.PAGE_END);

		TabMain.setBackground(new java.awt.Color(255, 255, 253));
		TabMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
		TabMain.setForeground(new java.awt.Color(50, 50, 50));
		TabMain.setFont(new java.awt.Font("Tahoma", 0, 11));
		TabMain.setName("TabMain");
		TabMain.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				TabMainMouseClicked(evt);
			}
		});

		internalFrameTindakaDrPetgs = new widget.InternalFrame();
		internalFrameTindakaDrPetgs.setBorder(null);
		internalFrameTindakaDrPetgs.setName("internalFrameTindakaDrPetgs");
		internalFrameTindakaDrPetgs.setPreferredSize(new java.awt.Dimension(45, 75));
		internalFrameTindakaDrPetgs.setLayout(new java.awt.BorderLayout(1, 1));

		panelGlassTindakanDokter = new widget.panelisi();
		panelGlassTindakanDokter.setBorder(null);
		panelGlassTindakanDokter.setName("panelGlassTindakanDokter");
		panelGlassTindakanDokter.setPreferredSize(new java.awt.Dimension(44, 74));
		panelGlassTindakanDokter.setLayout(null);

		Label jlabelPetugas = new widget.Label();
		kdpetugas = new widget.TextBox();
		BtnSeekPetugas = new widget.Button();
		TPerawat = new widget.TextBox();
		Label jLabelDokter = new widget.Label();
		KdDok = new widget.TextBox();
		TDokter = new widget.TextBox();
		BtnSeekDokter = new widget.Button();

		jlabelPetugas.setText("Petugas :");
		jlabelPetugas.setName("jlabelPetugas");
		panelGlassTindakanDokter.add(jlabelPetugas);
		jlabelPetugas.setBounds(0, 40, 65, 23);

		kdpetugas.setHighlighter(null);
		kdpetugas.setName("kdpetugas");
		kdpetugas.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				kdpetugasKeyPressed(evt);
			}
		});
		panelGlassTindakanDokter.add(kdpetugas);
		kdpetugas.setBounds(68, 40, 130, 23);

		BtnSeekPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
		BtnSeekPetugas.setMnemonic('5');
		BtnSeekPetugas.setToolTipText("ALt+5");
		BtnSeekPetugas.setName("BtnSeekPetugas");
		BtnSeekPetugas.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnSeekPetugasActionPerformed(evt);
			}
		});
		panelGlassTindakanDokter.add(BtnSeekPetugas);
		BtnSeekPetugas.setBounds(749, 40, 28, 23);

		TPerawat.setEditable(false);
		TPerawat.setBackground(new java.awt.Color(202, 202, 202));
		TPerawat.setHighlighter(null);
		TPerawat.setName("TPerawat");
		panelGlassTindakanDokter.add(TPerawat);
		TPerawat.setBounds(200, 40, 546, 23);

		jLabelDokter.setText("Dokter :");
		jLabelDokter.setName("jLabelDokter");
		panelGlassTindakanDokter.add(jLabelDokter);
		jLabelDokter.setBounds(0, 10, 65, 23);
		KdDok.setHighlighter(null);
		KdDok.setName("KdDok");
		KdDok.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				KdDokKeyPressed(evt);
			}
		});
		panelGlassTindakanDokter.add(KdDok);
		KdDok.setBounds(68, 10, 130, 23);

		TDokter.setHighlighter(null);
		TDokter.setName("TDokter");
		TDokter.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TDokterKeyPressed(evt);
			}
		});
		panelGlassTindakanDokter.add(TDokter);
		TDokter.setBounds(68, 10, 130, 23);

		TDokter.setEditable(false);
		TDokter.setHighlighter(null);
		TDokter.setName("TDokter");
		panelGlassTindakanDokter.add(TDokter);
		TDokter.setBounds(200, 10, 546, 23);

		BtnSeekDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
		BtnSeekDokter.setMnemonic('4');
		BtnSeekDokter.setToolTipText("ALt+4");
		BtnSeekDokter.setName("BtnSeekDokter");
		BtnSeekDokter.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnSeekDokterActionPerformed(evt);
			}
		});
		panelGlassTindakanDokter.add(BtnSeekDokter);
		BtnSeekDokter.setBounds(749, 10, 28, 23);
		internalFrameTindakaDrPetgs.add(panelGlassTindakanDokter, java.awt.BorderLayout.PAGE_START);
		tabTindakanDokterPetugas = new javax.swing.JTabbedPane();
		tabTindakanDokterPetugas.setBackground(new java.awt.Color(255, 255, 253));
		tabTindakanDokterPetugas.setForeground(new java.awt.Color(50, 50, 50));
		tabTindakanDokterPetugas.setFont(new java.awt.Font("Tahoma", 0, 11));
		tabTindakanDokterPetugas.setName("tabTindakanDokterPetugas");
		tabTindakanDokterPetugas.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tabTindakanDokterPetugasMouseClicked(evt);
			}
		});
		Scroll9 = new widget.ScrollPane();
		Scroll10 = new widget.ScrollPane();
		Scroll9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
		Scroll9.setName("Scroll9");
		Scroll9.setOpaque(true);
		tbTindakan.setAutoCreateRowSorter(true);
		tbTindakan.setToolTipText("");
		tbTindakan.setName("tbTindakan");
		tbTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				tbTindakanKeyPressed(evt);
			}
		});
		Scroll9.setViewportView(tbTindakan);
		tabTindakanDokterPetugas.addTab("Daftar Tindakan/Tagihan", Scroll9);
		Scroll10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
		Scroll10.setName("Scroll10");
		Scroll10.setOpaque(true);

		tbRawatDrPr.setAutoCreateRowSorter(true);
		tbRawatDrPr.setName("tbRawatDrPr");
		tbRawatDrPr.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tbRawatDrPrMouseClicked(evt);
			}
		});
		tbRawatDrPr.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tbRawatDrPrKeyReleased(evt);
			}
		});
		Scroll10.setViewportView(tbRawatDrPr);

		tabTindakanDokterPetugas.addTab("Tindakan Dilakukan", Scroll10);

		internalFrameTindakaDrPetgs.add(tabTindakanDokterPetugas, java.awt.BorderLayout.CENTER);

		TabMain.addTab("Penanganan Dokter & Petugas", internalFrameTindakaDrPetgs);

		internalFrame5 = new widget.InternalFrame();
		Scroll3 = new widget.ScrollPane();
		ScrollVaksin = new widget.ScrollPane();
		
		PanelInput = new javax.swing.JPanel();
		PanelInputVaksin = new javax.swing.JPanel();
		ChkInput = new widget.CekBox();
		panelGlass12 = new widget.panelisi();
		Label jLabel8 = new widget.Label();
		Label jLabel7 = new widget.Label();
		Label jLabel4 = new widget.Label();
		Label jLabel16 = new widget.Label();
		Label jLabel18 = new widget.Label();
		cmbImun = new widget.ComboBox();
		Label jLabel25 = new widget.Label();
		Label jLabel17 = new widget.Label();
		Label jLabel9 = new widget.Label();
		Label jLabel15 = new widget.Label();
		Label jLabel20 = new widget.Label();
		Label jLabel22 = new widget.Label();
		scrollPane1 = new widget.ScrollPane();
		TKeluhan = new widget.TextArea();
		Label jLabel28 = new widget.Label();
		Label jLabel26 = new widget.Label();
		scrollPane2 = new widget.ScrollPane();
		TPemeriksaan = new widget.TextArea();
		TSuhu = new widget.TextBox();
		TTensi = new widget.TextBox();
		TTinggi = new widget.TextBox();
		TRespirasi = new widget.TextBox();
		TBerat = new widget.TextBox();
		TNadi = new widget.TextBox();
		TGCS = new widget.TextBox();
		TAlergi = new widget.TextBox();
		scrollPane3 = new widget.ScrollPane();
		TPenilaian = new widget.TextArea();
		scrollPane6 = new widget.ScrollPane();
		TindakLanjut = new widget.TextArea();
		Label jLabel29 = new widget.Label();
		cmbKesadaran = new widget.ComboBox();
		Label jLabel37 = new widget.Label();
		KdPeg = new widget.TextBox();
		TPegawai = new widget.TextBox();
		BtnSeekPegawai = new widget.Button();
		Jabatan = new widget.TextBox();
		Label jLabel41 = new widget.Label();
		Label jLabel53 = new widget.Label();
		scrollPane7 = new widget.ScrollPane();
		TInstruksi = new widget.TextArea();

		internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
		internalFrame5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		internalFrame5.setName("internalFrame5");
		internalFrame5.setPreferredSize(new java.awt.Dimension(45, 75));
		internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

		Scroll3.setName("Scroll3");
		Scroll3.setOpaque(true);

		tbPemeriksaan.setAutoCreateRowSorter(true);
		tbPemeriksaan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
		tbPemeriksaan.setName("tbPemeriksaan");
		tbPemeriksaan.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tbPemeriksaanMouseClicked(evt);
			}
		});
		tbPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tbPemeriksaanKeyReleased(evt);
			}
		});
		Scroll3.setViewportView(tbPemeriksaan);

		internalFrame5.add(Scroll3, java.awt.BorderLayout.CENTER);

		PanelInput.setName("PanelInput");
		PanelInput.setOpaque(false);
		PanelInput.setPreferredSize(new java.awt.Dimension(192, 243));
		PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

		ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png")));
		ChkInput.setMnemonic('I');
		ChkInput.setText(".: Input Data");
		ChkInput.setToolTipText("Alt+I");
		ChkInput.setBorderPainted(true);
		ChkInput.setBorderPaintedFlat(true);
		ChkInput.setFocusable(false);
		ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		ChkInput.setName("ChkInput");
		ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
		ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png")));
		ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png")));
		ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png")));
		ChkInput.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ChkInputActionPerformed(evt);
			}
		});
		PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

		panelGlass12.setName("panelGlass12");
		panelGlass12.setPreferredSize(new java.awt.Dimension(44, 134));
		panelGlass12.setLayout(null);

		jLabel8.setText("Subjek :");
		jLabel8.setName("jLabel8");
		panelGlass12.add(jLabel8);
		jLabel8.setBounds(0, 70, 70, 23);

		jLabel7.setText("Suhu (°C) :");
		jLabel7.setName("jLabel7");
		panelGlass12.add(jLabel7);
		jLabel7.setBounds(0, 160, 70, 23);

		jLabel4.setText("Tensi :");
		jLabel4.setName("jLabel4");
		panelGlass12.add(jLabel4);
		jLabel4.setBounds(0, 190, 70, 23);

		jLabel16.setText("Berat(Kg) :");
		jLabel16.setName("jLabel16");
		panelGlass12.add(jLabel16);
		jLabel16.setBounds(294, 160, 79, 23);

		jLabel18.setText("Nadi(/menit) :");
		jLabel18.setName("jLabel18");
		panelGlass12.add(jLabel18);
		jLabel18.setBounds(294, 190, 79, 23);

		cmbImun.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13" }));
		cmbImun.setName("cmbImun");
		cmbImun.setPreferredSize(new java.awt.Dimension(62, 28));
		cmbImun.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbImunKeyPressed(evt);
			}
		});
		panelGlass12.add(cmbImun);
		cmbImun.setBounds(543, 40, 65, 23);

		jLabel25.setText("Imun Ke :");
		jLabel25.setName("jLabel25");
		panelGlass12.add(jLabel25);
		jLabel25.setBounds(450, 40, 90, 23);

		jLabel17.setText("Tinggi Badan(Cm) :");
		jLabel17.setName("jLabel17");
		panelGlass12.add(jLabel17);
		jLabel17.setBounds(134, 160, 100, 23);

		jLabel9.setText("Objek :");
		jLabel9.setName("jLabel9");
		panelGlass12.add(jLabel9);
		jLabel9.setBounds(0, 115, 70, 23);

		jLabel15.setText("Alergi :");
		jLabel15.setName("jLabel15");
		panelGlass12.add(jLabel15);
		jLabel15.setBounds(624, 40, 80, 23);

		jLabel20.setText("Respirasi(/menit) :");
		jLabel20.setName("jLabel20");
		panelGlass12.add(jLabel20);
		jLabel20.setBounds(134, 190, 100, 23);

		jLabel22.setText("GCS(E,V,M) :");
		jLabel22.setName("jLabel22");
		panelGlass12.add(jLabel22);
		jLabel22.setBounds(450, 10, 90, 23);

		scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		scrollPane1.setName("scrollPane1");

		TKeluhan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		TKeluhan.setColumns(20);
		TKeluhan.setRows(5);
		TKeluhan.setName("TKeluhan");
		TKeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TKeluhanKeyPressed(evt);
			}
		});
		scrollPane1.setViewportView(TKeluhan);

		panelGlass12.add(scrollPane1);
		scrollPane1.setBounds(73, 70, 360, 38);

		jLabel28.setText("Asesmen :");
		jLabel28.setName("jLabel28");
		panelGlass12.add(jLabel28);
		jLabel28.setBounds(450, 70, 90, 23);

		jLabel26.setText("Plan :");
		jLabel26.setName("jLabel26");
		panelGlass12.add(jLabel26);
		jLabel26.setBounds(450, 119, 90, 23);

		scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		scrollPane2.setName("scrollPane2");

		TPemeriksaan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		TPemeriksaan.setColumns(20);
		TPemeriksaan.setRows(5);
		TPemeriksaan.setName("TPemeriksaan");
		TPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TPemeriksaanKeyPressed(evt);
			}
		});
		scrollPane2.setViewportView(TPemeriksaan);

		panelGlass12.add(scrollPane2);
		scrollPane2.setBounds(73, 115, 360, 38);

		TSuhu.setFocusTraversalPolicyProvider(true);
		TSuhu.setName("TSuhu");
		TSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TSuhuKeyPressed(evt);
			}
		});
		panelGlass12.add(TSuhu);
		TSuhu.setBounds(73, 160, 55, 23);

		TTensi.setHighlighter(null);
		TTensi.setName("TTensi");
		TTensi.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TTensiKeyPressed(evt);
			}
		});
		panelGlass12.add(TTensi);
		TTensi.setBounds(73, 190, 55, 23);

		TTinggi.setFocusTraversalPolicyProvider(true);
		TTinggi.setName("TTinggi");
		TTinggi.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TTinggiKeyPressed(evt);
			}
		});
		panelGlass12.add(TTinggi);
		TTinggi.setBounds(237, 160, 55, 23);

		TRespirasi.setHighlighter(null);
		TRespirasi.setName("TRespirasi");
		TRespirasi.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TRespirasiKeyPressed(evt);
			}
		});
		panelGlass12.add(TRespirasi);
		TRespirasi.setBounds(237, 190, 55, 23);

		TBerat.setHighlighter(null);
		TBerat.setName("TBerat");
		TBerat.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TBeratKeyPressed(evt);
			}
		});
		panelGlass12.add(TBerat);
		TBerat.setBounds(378, 160, 55, 23);

		TNadi.setFocusTraversalPolicyProvider(true);
		TNadi.setName("TNadi");
		TNadi.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TNadiActionPerformed(evt);
			}
		});
		TNadi.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TNadiKeyPressed(evt);
			}
		});
		panelGlass12.add(TNadi);
		TNadi.setBounds(378, 190, 55, 23);

		TGCS.setFocusTraversalPolicyProvider(true);
		TGCS.setName("TGCS");
		TGCS.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TGCSKeyPressed(evt);
			}
		});
		panelGlass12.add(TGCS);
		TGCS.setBounds(543, 10, 65, 23);

		TAlergi.setHighlighter(null);
		TAlergi.setName("TAlergi");
		TAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TAlergiKeyPressed(evt);
			}
		});
		panelGlass12.add(TAlergi);
		TAlergi.setBounds(707, 40, 196, 23);

		scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		scrollPane3.setName("scrollPane3");

		TPenilaian.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		TPenilaian.setColumns(20);
		TPenilaian.setRows(5);
		TPenilaian.setName("TPenilaian");
		TPenilaian.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TPenilaianKeyPressed(evt);
			}
		});
		scrollPane3.setViewportView(TPenilaian);

		panelGlass12.add(scrollPane3);
		scrollPane3.setBounds(543, 70, 360, 42);

		scrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		scrollPane6.setName("scrollPane6");

		TindakLanjut.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		TindakLanjut.setColumns(20);
		TindakLanjut.setRows(5);
		TindakLanjut.setName("TindakLanjut");
		TindakLanjut.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TindakLanjutKeyPressed(evt);
			}
		});
		scrollPane6.setViewportView(TindakLanjut);

		panelGlass12.add(scrollPane6);
		scrollPane6.setBounds(543, 119, 360, 43);

		jLabel29.setText("Kesadaran :");
		jLabel29.setName("jLabel29");
		panelGlass12.add(jLabel29);
		jLabel29.setBounds(624, 10, 80, 23);

		cmbKesadaran.setModel(
				new javax.swing.DefaultComboBoxModel(new String[] { "Compos Mentis", "Somnolence", "Sopor", "Coma" }));
		cmbKesadaran.setName("cmbKesadaran");
		cmbKesadaran.setPreferredSize(new java.awt.Dimension(62, 28));
		cmbKesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbKesadaranKeyPressed(evt);
			}
		});
		panelGlass12.add(cmbKesadaran);
		cmbKesadaran.setBounds(707, 10, 196, 23);

		jLabel37.setText("Dilakukan :");
		jLabel37.setName("jLabel37");
		panelGlass12.add(jLabel37);
		jLabel37.setBounds(0, 10, 70, 23);

		KdPeg.setHighlighter(null);
		KdPeg.setName("KdPeg");
		KdPeg.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				KdPegKeyPressed(evt);
			}
		});
		panelGlass12.add(KdPeg);
		KdPeg.setBounds(73, 10, 115, 23);

		TPegawai.setEditable(false);
		TPegawai.setHighlighter(null);
		TPegawai.setName("TPegawai");
		panelGlass12.add(TPegawai);
		TPegawai.setBounds(190, 10, 212, 23);

		BtnSeekPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
		BtnSeekPegawai.setMnemonic('4');
		BtnSeekPegawai.setToolTipText("ALt+4");
		BtnSeekPegawai.setName("BtnSeekPegawai");
		BtnSeekPegawai.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnSeekPegawaiActionPerformed(evt);
			}
		});
		panelGlass12.add(BtnSeekPegawai);
		BtnSeekPegawai.setBounds(405, 10, 28, 23);

		Jabatan.setEditable(false);
		Jabatan.setHighlighter(null);
		Jabatan.setName("Jabatan");
		panelGlass12.add(Jabatan);
		Jabatan.setBounds(193, 40, 240, 23);

		jLabel41.setText("Profesi / Jabatan / Departemen :");
		jLabel41.setName("jLabel41");
		panelGlass12.add(jLabel41);
		jLabel41.setBounds(0, 40, 190, 23);

		jLabel53.setText("Instruksi :");
		jLabel53.setName("jLabel53");
		panelGlass12.add(jLabel53);
		jLabel53.setBounds(450, 169, 90, 23);

		scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		scrollPane7.setName("scrollPane7");

		TInstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		TInstruksi.setColumns(20);
		TInstruksi.setRows(5);
		TInstruksi.setName("TInstruksi");
		TInstruksi.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TInstruksiKeyPressed(evt);
			}
		});
		scrollPane7.setViewportView(TInstruksi);

		panelGlass12.add(scrollPane7);
		scrollPane7.setBounds(543, 169, 360, 43);

		PanelInput.add(panelGlass12, java.awt.BorderLayout.CENTER);

		internalFrame5.add(PanelInput, java.awt.BorderLayout.PAGE_START);
		// System.out.println("KodePoli:"+kode_poli.trim());
		TabMain.addTab("Pemeriksaan", internalFrame5);
//		End Pemeriksaan
		internalFramePemeriksaan = new widget.InternalFrame();
		internalFramePemeriksaan.setBackground(new java.awt.Color(235, 255, 235));
		internalFramePemeriksaan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		internalFramePemeriksaan.setName("internalFramePemeriksaan");
		internalFramePemeriksaan.setLayout(new java.awt.BorderLayout(1, 1));

		ScrollVaksin.setName("ScrollVaksin");
		ScrollVaksin.setOpaque(true);

		PanelInputVaksin.setOpaque(false);
		PanelInputVaksin.setPreferredSize(new java.awt.Dimension(192, 243));
		PanelInputVaksin.setLayout(new java.awt.BorderLayout(1, 1));
		internalFramePemeriksaan.add(PanelInputVaksin, java.awt.BorderLayout.PAGE_START);

		tbPemeriksaanVaksin.setAutoCreateRowSorter(true);
		tbPemeriksaanVaksin.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
		tbPemeriksaanVaksin.setName("tbPemeriksaanVaksin");
		tbPemeriksaanVaksin.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tbPemeriksaanVaksinMouseClicked(evt);
			}
		});
		tbPemeriksaanVaksin.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tbPemeriksaanVaksinKeyReleased(evt);
			}
		});

		ScrollVaksin.setViewportView(tbPemeriksaanVaksin);

		internalFramePemeriksaan.add(ScrollVaksin, java.awt.BorderLayout.CENTER);
		ChkInputVaksn = new widget.CekBox();
		ChkInputVaksn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png")));
		ChkInputVaksn.setMnemonic('V');
		ChkInputVaksn.setText(".: Input Data Vaksin");
		ChkInputVaksn.setToolTipText("Alt+V");
		ChkInputVaksn.setBorderPainted(true);
		ChkInputVaksn.setBorderPaintedFlat(true);
		ChkInputVaksn.setFocusable(false);
		ChkInputVaksn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		ChkInputVaksn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		ChkInputVaksn.setName("ChkInputVaksn");
		ChkInputVaksn.setPreferredSize(new java.awt.Dimension(192, 20));
		ChkInputVaksn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png")));
		ChkInputVaksn.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png")));
		ChkInputVaksn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png")));
		ChkInputVaksn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ChkInputVaksinActionPerformed(evt);
			}
		});
		PanelInputVaksin.add(ChkInputVaksn, java.awt.BorderLayout.PAGE_END);

		panelGlassVaksin = new widget.PanelBiasa();
		panelGlassVaksin.setName("panelGlassVak sin");
		panelGlassVaksin.setPreferredSize(new java.awt.Dimension(44, 130));
		panelGlassVaksin.setLayout(null);

		Label jLabelvaksin = new widget.Label();
		jLabelvaksin.setText("Petugas :");
		jLabelvaksin.setName("jLabelvaksin");
		panelGlassVaksin.add(jLabelvaksin);
		jLabelvaksin.setBounds(0, 10, 70, 23);

		kdptgsVasksin = new widget.TextBox();
		kdptgsVasksin.setHighlighter(null);
		kdptgsVasksin.setName("kdptgsVasksin");

		panelGlassVaksin.add(kdptgsVasksin);
		kdptgsVasksin.setBounds(72, 10, 130, 23);

		TPegawaiVk = new widget.TextBox();
		TPegawaiVk.setEditable(false);
		TPegawaiVk.setHighlighter(null);
		TPegawaiVk.setName("TPegawaiVk");
		panelGlassVaksin.add(TPegawaiVk);
		TPegawaiVk.setBounds(205, 10, 210, 23);

		BtnSeekPetugasVaksin = new widget.Button();
		BtnSeekPetugasVaksin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png")));
		BtnSeekPetugasVaksin.setMnemonic('p');
		BtnSeekPetugasVaksin.setToolTipText("ALt+4");
		BtnSeekPetugasVaksin.setName("BtnSeekPetugasVaksin");
		BtnSeekPetugasVaksin.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnSeekPetugas2ActionPerformed(evt);
			}
		});
		panelGlassVaksin.add(BtnSeekPetugasVaksin);
		BtnSeekPetugasVaksin.setBounds(415, 10, 25, 23);

		Label jLabelSubVaksin = new widget.Label();
		jLabelSubVaksin.setText("Subjek :");
		jLabelSubVaksin.setName("jLabelSubVaksin");
		panelGlassVaksin.add(jLabelSubVaksin);
		jLabelSubVaksin.setBounds(0, 56, 70, 23);

		Label jLabelSuhuVaksin = new widget.Label();
		jLabelSuhuVaksin.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelSuhuVaksin.setText("Suhu (°C) :");
		jLabelSuhuVaksin.setName("jLabelSuhuVaksin");
		panelGlassVaksin.add(jLabelSuhuVaksin);
		jLabelSuhuVaksin.setBounds(472, 90, 70, 23);

		Label JLabelTnsiVaksi = new widget.Label();
		JLabelTnsiVaksi.setHorizontalAlignment(SwingConstants.LEFT);
		JLabelTnsiVaksi.setText("Tensi :");
		JLabelTnsiVaksi.setName("JLabelTnsiVaksi");
		panelGlassVaksin.add(JLabelTnsiVaksi);
		JLabelTnsiVaksi.setBounds(472, 153, 70, 23);

		Label JLabelKgVaksi = new widget.Label();
		JLabelKgVaksi.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelKgVaksi.setText("Berat(Kg) :");
		JLabelKgVaksi.setName("JLabelKgVaksi");
		panelGlassVaksin.add(JLabelKgVaksi);
		JLabelKgVaksi.setBounds(450, 125, 79, 23);

		Label JLabelNadiVaksi = new widget.Label();
		JLabelNadiVaksi.setText("Nadi(/menit) :");
		JLabelNadiVaksi.setName("JLabelNadiVaksi");
		panelGlassVaksin.add(JLabelNadiVaksi);
		JLabelNadiVaksi.setBounds(766, 153, 79, 23);

		cmbImunVaksin = new widget.ComboBox();
		cmbImunVaksin.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13" }));
		cmbImunVaksin.setName("cmbImunVaksin");
		cmbImunVaksin.setPreferredSize(new java.awt.Dimension(62, 28));
		cmbImunVaksin.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbImunKeyPressed(evt);
			}
		});
		panelGlassVaksin.add(cmbImunVaksin);
		cmbImunVaksin.setBounds(815, 10, 65, 23);

		Label JLabelImunVaksi = new widget.Label();
		JLabelImunVaksi.setHorizontalAlignment(SwingConstants.LEFT);
		JLabelImunVaksi.setText("Imun Ke :");
		JLabelImunVaksi.setName("JLabelImunVaksi");
		panelGlassVaksin.add(JLabelImunVaksi);
		JLabelImunVaksi.setBounds(739, 10, 66, 23);

		Label JLabelTinggiVaksi = new widget.Label();
		JLabelTinggiVaksi.setText("Tinggi Badan(Cm) :");
		JLabelTinggiVaksi.setName("JLabelTinggiVaksi");
		panelGlassVaksin.add(JLabelTinggiVaksi);
		JLabelTinggiVaksi.setBounds(606, 90, 100, 23);

		Label JLabelObjectVaksi = new widget.Label();
		JLabelObjectVaksi.setText("Objek :");
		JLabelObjectVaksi.setName("JLabelObjectVaksi");
		panelGlassVaksin.add(JLabelObjectVaksi);
		JLabelObjectVaksi.setBounds(0, 141, 70, 23);

		Label JLabelalergiVaksi = new widget.Label();
		JLabelalergiVaksi.setHorizontalAlignment(SwingConstants.LEFT);
		JLabelalergiVaksi.setText("Alergi :");
		JLabelalergiVaksi.setName("JLabelalergiVaksi");
		panelGlassVaksin.add(JLabelalergiVaksi);
		JLabelalergiVaksi.setBounds(472, 44, 68, 23);

		Label JLabelRespirasiVaksi = new widget.Label();
		JLabelRespirasiVaksi.setText("Respirasi(/menit) :");
		JLabelRespirasiVaksi.setName("JLabelRespirasiVaksi");
		panelGlassVaksin.add(JLabelRespirasiVaksi);
		JLabelRespirasiVaksi.setBounds(606, 153, 100, 23);

		Label JLabelGCSVaksi = new widget.Label();
		JLabelGCSVaksi.setText("GCS(E,V,M) :");
		JLabelGCSVaksi.setName("JLabelGCSVaksi");
		panelGlassVaksin.add(JLabelGCSVaksi);
		JLabelGCSVaksi.setBounds(450, 10, 90, 23);

		PanelInputVaksin.add(panelGlassVaksin, java.awt.BorderLayout.CENTER);

		ScrollPane scrollPaneSubVaskn = new ScrollPane();
		scrollPaneSubVaskn.setBounds(72, 44, 360, 62);
		scrollPaneSubVaskn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		panelGlassVaksin.add(scrollPaneSubVaskn);
		TxtareaSubject = new TextArea();
		TxtareaSubject.setBounds(72, 44, 360, 72);
		scrollPaneSubVaskn.setViewportView(TxtareaSubject);

		TxtareaObject = new TextArea();
		TxtareaObject.setBounds(72, 100, 360, 72);

		ScrollPane scrollPaneObjkVaskn = new ScrollPane();
		scrollPaneObjkVaskn.setBounds(72, 123, 360, 62);
		scrollPaneObjkVaskn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		panelGlassVaksin.add(scrollPaneObjkVaskn);
		scrollPaneObjkVaskn.setViewportView(TxtareaObject);

		TSuhuVaksin = new TextBox();
		TSuhuVaksin.setBounds(544, 89, 55, 23);
		panelGlassVaksin.add(TSuhuVaksin);

		TtensiVaksn = new TextBox();
		TtensiVaksn.setBounds(544, 152, 55, 23);
		panelGlassVaksin.add(TtensiVaksn);

		TtinggiVaksn = new TextBox();
		TtinggiVaksn.setBounds(716, 89, 55, 23);
		panelGlassVaksin.add(TtinggiVaksn);

		TRespirasiVaksn = new TextBox();
		TRespirasiVaksn.setBounds(716, 152, 55, 23);
		panelGlassVaksin.add(TRespirasiVaksn);

		TKgVaksn = new TextBox();
		TKgVaksn.setBounds(544, 119, 55, 23);
		panelGlassVaksin.add(TKgVaksn);

		TNadiVaksn = new TextBox();
		TNadiVaksn.setBounds(861, 152, 55, 23);
		panelGlassVaksin.add(TNadiVaksn);

		TGCSVaksin = new TextBox();
		TGCSVaksin.setBounds(543, 9, 186, 23);
		panelGlassVaksin.add(TGCSVaksin);

		TAlegiVaksn = new TextBox();
		TAlegiVaksn.setBounds(543, 43, 186, 23);
		panelGlassVaksin.add(TAlegiVaksn);

		Label JLabelLingkarDD = new Label();
		JLabelLingkarDD.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelLingkarDD.setText("Lingkar Dada :");
		JLabelLingkarDD.setBounds(616, 124, 70, 23);
		panelGlassVaksin.add(JLabelLingkarDD);

		TlinkardadaVksn = new TextBox();
		TlinkardadaVksn.setBounds(716, 119, 55, 23);
		panelGlassVaksin.add(TlinkardadaVksn);

		Label JLabelDiagnosaVksn = new Label();
		JLabelDiagnosaVksn.setHorizontalAlignment(SwingConstants.LEFT);
		JLabelDiagnosaVksn.setText("Diagnosa :");
		JLabelDiagnosaVksn.setBounds(461, 188, 68, 23);
		panelGlassVaksin.add(JLabelDiagnosaVksn);

		Tkdvksin = new TextBox();
		Tvksin = new TextBox();
		Tkdvksin.setBounds(543, 187, 100, 23);
		panelGlassVaksin.add(Tkdvksin);

		Tvksin = new TextBox();
		Tvksin.setBounds(648, 187, 201, 23);
		panelGlassVaksin.add(Tvksin);

		BtnAddVaksin = new widget.Button();
		BtnAddVaksin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png")));
//        BtnAddVaksin.setMnemonic('3');
//        BtnAddVaksin.setToolTipText("Alt+3");
		BtnAddVaksin.setName("BtnAddVaksin");
		BtnAddVaksin.setPreferredSize(new java.awt.Dimension(28, 23));
		BtnAddVaksin.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				MasterImunisasi vaksin = new MasterImunisasi(null, false);
				vaksin.isCek();
				vaksin.setSize(internalFrameMain.getWidth() - 20, internalFrameMain.getHeight() - 20);
				vaksin.setLocationRelativeTo(internalFrameMain);
				vaksin.setVisible(true);
				
				vaksin.addWindowListener(new WindowListener() {

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
						if (vaksin.getTable().getSelectedRow() != -1) {
							Tkdvksin.setText(vaksin.getTable().getValueAt(vaksin.getTable().getSelectedRow(), 0).toString());
							Tvksin.setText(vaksin.getTable().getValueAt(vaksin.getTable().getSelectedRow(), 1).toString());
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
				
				vaksin.getTable().addKeyListener(new KeyListener() {

					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						if (e.getKeyCode() == KeyEvent.VK_SPACE) {
							vaksin.dispose();
						}
					}

					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
			}
		});
		panelGlassVaksin.add(BtnAddVaksin);
		BtnAddVaksin.setBounds(852, 188, 28, 23);
		
		Label JLabelLingkarKpl = new Label();
		JLabelLingkarKpl.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelLingkarKpl.setBounds(781, 119, 70, 23);
		JLabelLingkarKpl.setText("Lingkar Kepala");
		panelGlassVaksin.add(JLabelLingkarKpl);
		
		tLkrKepala = new TextBox();
		tLkrKepala.setBounds(861, 119, 55, 23);
		panelGlassVaksin.add(tLkrKepala);

		TabMain.addTab("Pemeriksaan Vaksin", internalFramePemeriksaan);
//		End Vaksin
		internalFrame6 = new widget.InternalFrame();
		Scroll4 = new widget.ScrollPane();
		tbPemeriksaanObstetri = new widget.Table();
		PanelInput1 = new javax.swing.JPanel();
		ChkInput1 = new widget.CekBox();
		panelGlass13 = new widget.panelisi();
		Label jLabel27 = new widget.Label();
		TTinggi_uteri = new widget.TextBox();
		Label jLabel30 = new widget.Label();
		Label jLabel31 = new widget.Label();
		TLetak = new widget.TextBox();
		Label jLabel32 = new widget.Label();
		TKualitas_dtk = new widget.TextBox();
		Label jLabel33 = new widget.Label();
		cmbPanggul = new widget.ComboBox();
		Label jLabel34 = new widget.Label();
		TTebal = new widget.TextBox();
		TDenyut = new widget.TextBox();
		Label jLabel36 = new widget.Label();
		TDenominator = new widget.TextBox();
		Label jLabel38 = new widget.Label();
		Label jLabel39 = new widget.Label();
		TKualitas_mnt = new widget.TextBox();
		Label jLabel40 = new widget.Label();
		cmbFeto = new widget.ComboBox();
		Label jLabel42 = new widget.Label();
		cmbJanin = new widget.ComboBox();
		cmbKetuban = new widget.ComboBox();
		TPortio = new widget.TextBox();
		Label jLabel43 = new widget.Label();
		TVulva = new widget.TextBox();
		cmbKontraksi = new widget.ComboBox();
		cmbAlbus = new widget.ComboBox();
		Label jLabel45 = new widget.Label();
		Label jLabel46 = new widget.Label();
		Label jLabel47 = new widget.Label();
		Label jLabel44 = new widget.Label();
		cmbFluksus = new widget.ComboBox();
		Label jLabel48 = new widget.Label();
		cmbDalam = new widget.ComboBox();
		Label jLabel49 = new widget.Label();
		TPembukaan = new widget.TextBox();
		TPenurunan = new widget.TextBox();
		Label jLabel50 = new widget.Label();
		Label jLabel51 = new widget.Label();
		cmbArah = new widget.ComboBox();
		Label jLabel52 = new widget.Label();

		internalFrame6.setBackground(new java.awt.Color(235, 255, 235));
		internalFrame6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		internalFrame6.setName("internalFrame6");
		internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

		Scroll4.setName("Scroll4");
		Scroll4.setOpaque(true);

		tbPemeriksaanObstetri.setAutoCreateRowSorter(true);
		tbPemeriksaanObstetri.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
		tbPemeriksaanObstetri.setName("tbPemeriksaanObstetri");
		tbPemeriksaanObstetri.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tbPemeriksaanObstetriMouseClicked(evt);
			}
		});
		tbPemeriksaanObstetri.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tbPemeriksaanObstetriKeyReleased(evt);
			}
		});
		Scroll4.setViewportView(tbPemeriksaanObstetri);

		internalFrame6.add(Scroll4, java.awt.BorderLayout.CENTER);

		PanelInput1.setName("PanelInput1");
		PanelInput1.setOpaque(false);
		PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

		ChkInput1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png")));
		ChkInput1.setMnemonic('I');
		ChkInput1.setText(".: Input Data");
		ChkInput1.setToolTipText("Alt+I");
		ChkInput1.setBorderPainted(true);
		ChkInput1.setBorderPaintedFlat(true);
		ChkInput1.setFocusable(false);
		ChkInput1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		ChkInput1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		ChkInput1.setName("ChkInput1");
		ChkInput1.setPreferredSize(new java.awt.Dimension(192, 20));
		ChkInput1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png")));
		ChkInput1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png")));
		ChkInput1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png")));
		ChkInput1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ChkInput1ActionPerformed(evt);
			}
		});
		PanelInput1.add(ChkInput1, java.awt.BorderLayout.PAGE_END);

		panelGlass13.setName("panelGlass13");
		panelGlass13.setPreferredSize(new java.awt.Dimension(44, 134));
		panelGlass13.setLayout(null);

		jLabel27.setText("Tinggi Fundus Uteri (Cm) :");
		jLabel27.setName("jLabel27");
		panelGlass13.add(jLabel27);
		jLabel27.setBounds(0, 10, 135, 23);

		TTinggi_uteri.setHighlighter(null);
		TTinggi_uteri.setName("TTinggi_uteri");
		TTinggi_uteri.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TTinggi_uteriKeyPressed(evt);
			}
		});
		panelGlass13.add(TTinggi_uteri);
		TTinggi_uteri.setBounds(138, 10, 50, 23);

		jLabel30.setText("Janin :");
		jLabel30.setName("jLabel30");
		panelGlass13.add(jLabel30);
		jLabel30.setBounds(194, 10, 45, 23);

		jLabel31.setText("Letak :");
		jLabel31.setName("jLabel31");
		panelGlass13.add(jLabel31);
		jLabel31.setBounds(375, 10, 40, 23);

		TLetak.setHighlighter(null);
		TLetak.setName("TLetak");
		TLetak.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TLetakKeyPressed(evt);
			}
		});
		panelGlass13.add(TLetak);
		TLetak.setBounds(418, 10, 50, 23);

		jLabel32.setText("Bagian Bawah Panggul :");
		jLabel32.setName("jLabel32");
		panelGlass13.add(jLabel32);
		jLabel32.setBounds(486, 10, 130, 23);

		TKualitas_dtk.setFocusTraversalPolicyProvider(true);
		TKualitas_dtk.setName("TKualitas_dtk");
		TKualitas_dtk.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TKualitas_dtkKeyPressed(evt);
			}
		});
		panelGlass13.add(TKualitas_dtk);
		TKualitas_dtk.setBounds(402, 40, 50, 23);

		jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		jLabel33.setText("detik");
		jLabel33.setName("jLabel33");
		panelGlass13.add(jLabel33);
		jLabel33.setBounds(455, 40, 30, 23);

		cmbPanggul.setModel(
				new javax.swing.DefaultComboBoxModel(new String[] { "-", "5/5", "4/5", "3/5", "2/5", "1/5" }));
		cmbPanggul.setName("cmbPanggul");
		cmbPanggul.setPreferredSize(new java.awt.Dimension(55, 28));
		cmbPanggul.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbPanggulKeyPressed(evt);
			}
		});
		panelGlass13.add(cmbPanggul);
		cmbPanggul.setBounds(619, 10, 62, 23);

		jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel34.setText("/10 menit/");
		jLabel34.setName("jLabel34");
		panelGlass13.add(jLabel34);
		jLabel34.setBounds(343, 40, 58, 23);

		TTebal.setHighlighter(null);
		TTebal.setName("TTebal");
		TTebal.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TTebalKeyPressed(evt);
			}
		});
		panelGlass13.add(TTebal);
		TTebal.setBounds(709, 70, 50, 23);

		TDenyut.setHighlighter(null);
		TDenyut.setName("TDenyut");
		TDenyut.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TDenyutKeyPressed(evt);
			}
		});
		panelGlass13.add(TDenyut);
		TDenyut.setBounds(876, 10, 50, 23);

		jLabel36.setText("Denyut Jantung Fetus (x/mnt) :");
		jLabel36.setName("jLabel36");
		panelGlass13.add(jLabel36);
		jLabel36.setBounds(693, 10, 170, 23);

		TDenominator.setHighlighter(null);
		TDenominator.setName("TDenominator");
		TDenominator.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TDenominatorKeyPressed(evt);
			}
		});
		panelGlass13.add(TDenominator);
		TDenominator.setBounds(548, 100, 125, 23);

		jLabel38.setText("Penurunan :");
		jLabel38.setName("jLabel38");
		panelGlass13.add(jLabel38);
		jLabel38.setBounds(267, 100, 70, 23);

		jLabel39.setText("Imbang Feto-Pelvik :");
		jLabel39.setName("jLabel39");
		panelGlass13.add(jLabel39);
		jLabel39.setBounds(673, 100, 110, 23);

		TKualitas_mnt.setHighlighter(null);
		TKualitas_mnt.setName("TKualitas_mnt");
		TKualitas_mnt.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TKualitas_mntKeyPressed(evt);
			}
		});
		panelGlass13.add(TKualitas_mnt);
		TKualitas_mnt.setBounds(293, 40, 50, 23);

		jLabel40.setText("Portio Inspekulo :");
		jLabel40.setName("jLabel40");
		panelGlass13.add(jLabel40);
		jLabel40.setBounds(272, 70, 90, 23);

		cmbFeto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Susp.CPD-FPD", "CPD-FPD" }));
		cmbFeto.setName("cmbFeto");
		cmbFeto.setPreferredSize(new java.awt.Dimension(55, 28));
		cmbFeto.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbFetoKeyPressed(evt);
			}
		});
		panelGlass13.add(cmbFeto);
		cmbFeto.setBounds(786, 100, 140, 23);

		jLabel42.setText("Denominator :");
		jLabel42.setName("jLabel42");
		jLabel42.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass13.add(jLabel42);
		jLabel42.setBounds(470, 100, 75, 23);

		cmbJanin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tunggal", "Gemelli" }));
		cmbJanin.setName("cmbJanin");
		cmbJanin.setPreferredSize(new java.awt.Dimension(55, 28));
		cmbJanin.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbJaninKeyPressed(evt);
			}
		});
		panelGlass13.add(cmbJanin);
		cmbJanin.setBounds(242, 10, 115, 23);

		cmbKetuban.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "+" }));
		cmbKetuban.setName("cmbKetuban");
		cmbKetuban.setPreferredSize(new java.awt.Dimension(55, 28));
		cmbKetuban.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbKetubanKeyPressed(evt);
			}
		});
		panelGlass13.add(cmbKetuban);
		cmbKetuban.setBounds(864, 40, 62, 23);

		TPortio.setFocusTraversalPolicyProvider(true);
		TPortio.setName("TPortio");
		TPortio.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TPortioKeyPressed(evt);
			}
		});
		panelGlass13.add(TPortio);
		TPortio.setBounds(365, 70, 125, 23);

		jLabel43.setText("Kualitas (x/mnt) : ");
		jLabel43.setName("jLabel43");
		panelGlass13.add(jLabel43);
		jLabel43.setBounds(193, 40, 100, 23);

		TVulva.setHighlighter(null);
		TVulva.setName("TVulva");
		TVulva.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TVulvaKeyPressed(evt);
			}
		});
		panelGlass13.add(TVulva);
		TVulva.setBounds(138, 70, 125, 23);

		cmbKontraksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cmbKontraksi.setName("cmbKontraksi");
		cmbKontraksi.setPreferredSize(new java.awt.Dimension(55, 28));
		cmbKontraksi.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbKontraksiKeyPressed(evt);
			}
		});
		panelGlass13.add(cmbKontraksi);
		cmbKontraksi.setBounds(138, 40, 62, 23);

		cmbAlbus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cmbAlbus.setName("cmbAlbus");
		cmbAlbus.setPreferredSize(new java.awt.Dimension(55, 28));
		cmbAlbus.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbAlbusKeyPressed(evt);
			}
		});
		panelGlass13.add(cmbAlbus);
		cmbAlbus.setBounds(698, 40, 62, 23);

		jLabel45.setText("Kontraksi :");
		jLabel45.setName("jLabel45");
		panelGlass13.add(jLabel45);
		jLabel45.setBounds(0, 40, 135, 23);

		jLabel46.setText("Fluor Albus :");
		jLabel46.setName("jLabel46");
		panelGlass13.add(jLabel46);
		jLabel46.setBounds(623, 40, 72, 23);

		jLabel47.setText("Vulva/Vagina :");
		jLabel47.setName("jLabel47");
		panelGlass13.add(jLabel47);
		jLabel47.setBounds(0, 70, 135, 23);

		jLabel44.setText("Fluksus :");
		jLabel44.setName("jLabel44");
		panelGlass13.add(jLabel44);
		jLabel44.setBounds(488, 40, 58, 23);

		cmbFluksus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cmbFluksus.setName("cmbFluksus");
		cmbFluksus.setPreferredSize(new java.awt.Dimension(55, 28));
		cmbFluksus.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbFluksusKeyPressed(evt);
			}
		});
		panelGlass13.add(cmbFluksus);
		cmbFluksus.setBounds(549, 40, 62, 23);

		jLabel48.setText("Dalam :");
		jLabel48.setName("jLabel48");
		panelGlass13.add(jLabel48);
		jLabel48.setBounds(500, 70, 47, 23);

		cmbDalam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kenyal", "Lunak" }));
		cmbDalam.setName("cmbDalam");
		cmbDalam.setPreferredSize(new java.awt.Dimension(55, 28));
		cmbDalam.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbDalamKeyPressed(evt);
			}
		});
		panelGlass13.add(cmbDalam);
		cmbDalam.setBounds(550, 70, 95, 23);

		jLabel49.setText("Pembukaan :");
		jLabel49.setName("jLabel49");
		panelGlass13.add(jLabel49);
		jLabel49.setBounds(0, 100, 135, 23);

		TPembukaan.setHighlighter(null);
		TPembukaan.setName("TPembukaan");
		TPembukaan.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TPembukaanKeyPressed(evt);
			}
		});
		panelGlass13.add(TPembukaan);
		TPembukaan.setBounds(138, 100, 125, 23);

		TPenurunan.setHighlighter(null);
		TPenurunan.setName("TPenurunan");
		TPenurunan.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TPenurunanKeyPressed(evt);
			}
		});
		panelGlass13.add(TPenurunan);
		TPenurunan.setBounds(340, 100, 125, 23);

		jLabel50.setText("Tebal/cm :");
		jLabel50.setName("jLabel50");
		panelGlass13.add(jLabel50);
		jLabel50.setBounds(646, 70, 60, 23);

		jLabel51.setText("Selaput Ketuban :");
		jLabel51.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
		jLabel51.setName("jLabel51");
		panelGlass13.add(jLabel51);
		jLabel51.setBounds(771, 40, 90, 23);

		cmbArah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Depan", "Axial", "Belakang" }));
		cmbArah.setName("cmbArah");
		cmbArah.setPreferredSize(new java.awt.Dimension(55, 28));
		cmbArah.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbArahKeyPressed(evt);
			}
		});
		panelGlass13.add(cmbArah);
		cmbArah.setBounds(806, 70, 120, 23);

		jLabel52.setText("Arah :");
		jLabel52.setName("jLabel52");
		panelGlass13.add(jLabel52);
		jLabel52.setBounds(763, 70, 40, 23);

		PanelInput1.add(panelGlass13, java.awt.BorderLayout.CENTER);

		internalFrame6.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

		TabMain.addTab("Pemeriksaan Obstetri", internalFrame6);
//		End Obstetri
		internalFrame7 = new widget.InternalFrame();
		internalFrame7.setBackground(new java.awt.Color(235, 255, 235));
		internalFrame7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		internalFrame7.setName("internalFrame7");
		internalFrame7.setLayout(new java.awt.BorderLayout(1, 1));
		Scroll5 = new widget.ScrollPane();
		Scroll5.setName("Scroll5");
		Scroll5.setOpaque(true);
		Scroll5.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				Scroll5KeyPressed(evt);
			}
		});
		tbPemeriksaanGinekologi.setAutoCreateRowSorter(true);
		tbPemeriksaanGinekologi.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
		tbPemeriksaanGinekologi.setName("tbPemeriksaanGinekologi");
		tbPemeriksaanGinekologi.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tbPemeriksaanGinekologiMouseClicked(evt);
			}
		});
		tbPemeriksaanGinekologi.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tbPemeriksaanGinekologiKeyReleased(evt);
			}
		});
		Scroll5.setViewportView(tbPemeriksaanGinekologi);

		internalFrame7.add(Scroll5, java.awt.BorderLayout.CENTER);
		PanelInput2 = new javax.swing.JPanel();
		PanelInput2.setName("PanelInput2");
		PanelInput2.setOpaque(false);
		PanelInput2.setPreferredSize(new java.awt.Dimension(192, 245));
		PanelInput2.setLayout(new java.awt.BorderLayout(1, 1));
		ChkInput2 = new widget.CekBox();

		ChkInput2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png")));
		ChkInput2.setMnemonic('I');
		ChkInput2.setText(".: Input Data");
		ChkInput2.setToolTipText("Alt+I");
		ChkInput2.setBorderPainted(true);
		ChkInput2.setBorderPaintedFlat(true);
		ChkInput2.setFocusable(false);
		ChkInput2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		ChkInput2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		ChkInput2.setName("ChkInput2");
		ChkInput2.setPreferredSize(new java.awt.Dimension(192, 20));
		ChkInput2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png")));
		ChkInput2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png")));
		ChkInput2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png")));
		ChkInput2.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ChkInput2ActionPerformed(evt);
			}
		});
		PanelInput2.add(ChkInput2, java.awt.BorderLayout.PAGE_END);
		panelGlass14 = new widget.panelisi();
		panelGlass14.setName("panelGlass14");
		panelGlass14.setPreferredSize(new java.awt.Dimension(44, 134));
		panelGlass14.setLayout(null);

		TInspeksiVulva = new widget.TextBox();
		TAdnexaKanan = new widget.TextBox();
		Label jLabel57 = new widget.Label();
		cmbMobilitas = new widget.ComboBox();
		Label jLabel60 = new widget.Label();
		TInspekuloGine = new widget.TextBox();
		Label jLabel62 = new widget.Label();
		Label jLabel64 = new widget.Label();
		Label jLabel67 = new widget.Label();
		TPortioInspekulo = new widget.TextBox();
		TCavumUteri = new widget.TextBox();
		cmbFluorGine = new widget.ComboBox();
		TInspeksi = new widget.TextBox();
		cmbFluxusGine = new widget.ComboBox();
		Label jLabel71 = new widget.Label();
		Label jLabel72 = new widget.Label();
		Label jLabel73 = new widget.Label();
		Label jLabel74 = new widget.Label();
		Label jLabel35 = new widget.Label();

		jLabel35.setText("Inspeksi :");
		jLabel35.setName("jLabel35");
		panelGlass14.add(jLabel35);
		jLabel35.setBounds(0, 10, 70, 23);

		TInspeksiVulva.setHighlighter(null);
		TInspeksiVulva.setName("TInspeksiVulva");
		TInspeksiVulva.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TInspeksiVulvaKeyPressed(evt);
			}
		});
		panelGlass14.add(TInspeksiVulva);
		TInspeksiVulva.setBounds(118, 40, 223, 23);

		TAdnexaKanan.setHighlighter(null);
		TAdnexaKanan.setName("TAdnexaKanan");
		TAdnexaKanan.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TAdnexaKananKeyPressed(evt);
			}
		});
		panelGlass14.add(TAdnexaKanan);
		TAdnexaKanan.setBounds(510, 120, 355, 23);

		jLabel57.setText("Fluor Albus :");
		jLabel57.setName("jLabel57");
		panelGlass14.add(jLabel57);
		jLabel57.setBounds(206, 100, 70, 23);

		cmbMobilitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cmbMobilitas.setName("cmbMobilitas");
		cmbMobilitas.setPreferredSize(new java.awt.Dimension(55, 28));
		cmbMobilitas.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbMobilitasKeyPressed(evt);
			}
		});
		panelGlass14.add(cmbMobilitas);
		cmbMobilitas.setBounds(803, 60, 62, 23);

		jLabel60.setText("Sondage :");
		jLabel60.setName("jLabel60");
		jLabel60.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass14.add(jLabel60);
		jLabel60.setBounds(20, 190, 95, 23);

		TInspekuloGine.setHighlighter(null);
		TInspekuloGine.setName("TInspekuloGine");
		TInspekuloGine.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TInspekuloGineKeyPressed(evt);
			}
		});
		panelGlass14.add(TInspekuloGine);
		TInspekuloGine.setBounds(73, 70, 268, 23);

		jLabel62.setText("Vulva/Uretra/Vagina :");
		jLabel62.setName("jLabel62");
		panelGlass14.add(jLabel62);
		jLabel62.setBounds(0, 40, 115, 23);

		jLabel64.setText("Inspekulo :");
		jLabel64.setName("jLabel64");
		panelGlass14.add(jLabel64);
		jLabel64.setBounds(0, 70, 70, 23);

		jLabel67.setText("Fluxus :");
		jLabel67.setName("jLabel67");
		panelGlass14.add(jLabel67);
		jLabel67.setBounds(0, 100, 115, 23);

		TPortioInspekulo.setHighlighter(null);
		TPortioInspekulo.setName("TPortioInspekulo");
		TPortioInspekulo.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TPortioInspekuloKeyPressed(evt);
			}
		});
		panelGlass14.add(TPortioInspekulo);
		TPortioInspekulo.setBounds(118, 160, 223, 23);

		TCavumUteri.setHighlighter(null);
		TCavumUteri.setName("TCavumUteri");
		TCavumUteri.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TCavumUteriKeyPressed(evt);
			}
		});
		panelGlass14.add(TCavumUteri);
		TCavumUteri.setBounds(468, 60, 272, 23);

		cmbFluorGine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cmbFluorGine.setName("cmbFluorGine");
		cmbFluorGine.setPreferredSize(new java.awt.Dimension(55, 28));
		cmbFluorGine.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbFluorGineKeyPressed(evt);
			}
		});
		panelGlass14.add(cmbFluorGine);
		cmbFluorGine.setBounds(279, 100, 62, 23);

		TInspeksi.setHighlighter(null);
		TInspeksi.setName("TInspeksi");
		TInspeksi.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TInspeksiKeyPressed(evt);
			}
		});
		panelGlass14.add(TInspeksi);
		TInspeksi.setBounds(73, 10, 268, 23);

		cmbFluxusGine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cmbFluxusGine.setName("cmbFluxusGine");
		cmbFluxusGine.setPreferredSize(new java.awt.Dimension(55, 28));
		cmbFluxusGine.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbFluxusGineKeyPressed(evt);
			}
		});
		panelGlass14.add(cmbFluxusGine);
		cmbFluxusGine.setBounds(118, 100, 62, 23);

		jLabel71.setText("Adnexa/Parametrium :");
		jLabel71.setName("jLabel71");
		jLabel71.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass14.add(jLabel71);
		jLabel71.setBounds(340, 120, 125, 23);

		jLabel72.setText("Portio :");
		jLabel72.setName("jLabel72");
		jLabel72.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass14.add(jLabel72);
		jLabel72.setBounds(20, 160, 95, 23);

		jLabel73.setText("Vulva/Vagina :");
		jLabel73.setName("jLabel73");
		jLabel73.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass14.add(jLabel73);
		jLabel73.setBounds(20, 130, 95, 23);

		jLabel74.setText("Pemeriksaan Dalam :");
		jLabel74.setName("jLabel74");
		jLabel74.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass14.add(jLabel74);
		jLabel74.setBounds(340, 10, 110, 23);

		Label jLabel75 = new widget.Label();
		TVulvaInspekulo = new widget.TextBox();
		Label jLabel76 = new widget.Label();
		Label jLabel77 = new widget.Label();
		TPortioDalam = new widget.TextBox();
		TBentuk = new widget.TextBox();
		Label jLabel78 = new widget.Label();
		cmbNyeriTekan = new widget.ComboBox();
		TSondage = new widget.TextBox();
		Label jLabel79 = new widget.Label();
		Label jLabel80 = new widget.Label();
		TAdnexaKiri = new widget.TextBox();
		Label jLabel81 = new widget.Label();
		TCavumDouglas = new widget.TextBox();
		TUkuran = new widget.TextBox();
		Label jLabel82 = new widget.Label();
		Label jLabel83 = new widget.Label();

		jLabel75.setText("Kanan :");
		jLabel75.setName("jLabel75");
		jLabel75.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass14.add(jLabel75);
		jLabel75.setBounds(437, 120, 70, 23);

		TVulvaInspekulo.setHighlighter(null);
		TVulvaInspekulo.setName("TVulvaInspekulo");
		TVulvaInspekulo.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TVulvaInspekuloKeyPressed(evt);
			}
		});
		panelGlass14.add(TVulvaInspekulo);
		TVulvaInspekulo.setBounds(118, 130, 223, 23);

		jLabel76.setText(", Bentuk :");
		jLabel76.setName("jLabel76");
		jLabel76.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass14.add(jLabel76);
		jLabel76.setBounds(640, 30, 50, 23);

		jLabel77.setText(", Mobilitas :");
		jLabel77.setName("jLabel77");
		jLabel77.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass14.add(jLabel77);
		jLabel77.setBounds(740, 60, 60, 23);

		TPortioDalam.setHighlighter(null);
		TPortioDalam.setName("TPortioDalam");
		TPortioDalam.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TPortioDalamKeyPressed(evt);
			}
		});
		panelGlass14.add(TPortioDalam);
		TPortioDalam.setBounds(468, 30, 173, 23);

		TBentuk.setHighlighter(null);
		TBentuk.setName("TBentuk");
		TBentuk.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TBentukKeyPressed(evt);
			}
		});
		panelGlass14.add(TBentuk);
		TBentuk.setBounds(693, 30, 173, 23);

		jLabel78.setText("Ukuran :");
		jLabel78.setName("jLabel78");
		jLabel78.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass14.add(jLabel78);
		jLabel78.setBounds(437, 90, 70, 23);

		cmbNyeriTekan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cmbNyeriTekan.setName("cmbNyeriTekan");
		cmbNyeriTekan.setPreferredSize(new java.awt.Dimension(55, 28));
		cmbNyeriTekan.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbNyeriTekanKeyPressed(evt);
			}
		});
		panelGlass14.add(cmbNyeriTekan);
		cmbNyeriTekan.setBounds(803, 90, 62, 23);

		TSondage.setHighlighter(null);
		TSondage.setName("TSondage");
		TSondage.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TSondageKeyPressed(evt);
			}
		});
		panelGlass14.add(TSondage);
		TSondage.setBounds(118, 190, 223, 23);

		jLabel79.setText("Cavum Uteri :");
		jLabel79.setName("jLabel79");
		jLabel79.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass14.add(jLabel79);
		jLabel79.setBounds(340, 60, 125, 23);

		jLabel80.setText("Kiri :");
		jLabel80.setName("jLabel80");
		jLabel80.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass14.add(jLabel80);
		jLabel80.setBounds(437, 150, 70, 23);

		TAdnexaKiri.setHighlighter(null);
		TAdnexaKiri.setName("TAdnexaKiri");
		TAdnexaKiri.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TAdnexaKiriKeyPressed(evt);
			}
		});
		panelGlass14.add(TAdnexaKiri);
		TAdnexaKiri.setBounds(510, 150, 355, 23);

		jLabel81.setText("Cavum Douglas :");
		jLabel81.setName("jLabel81");
		jLabel81.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass14.add(jLabel81);
		jLabel81.setBounds(340, 180, 125, 23);

		TCavumDouglas.setHighlighter(null);
		TCavumDouglas.setName("TCavumDouglas");
		TCavumDouglas.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TCavumDouglasKeyPressed(evt);
			}
		});
		panelGlass14.add(TCavumDouglas);
		TCavumDouglas.setBounds(468, 180, 397, 23);

		TUkuran.setHighlighter(null);
		TUkuran.setName("TUkuran");
		TUkuran.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TUkuranKeyPressed(evt);
			}
		});
		panelGlass14.add(TUkuran);
		TUkuran.setBounds(510, 90, 217, 23);

		jLabel82.setText(", Nyeri Tekan :");
		jLabel82.setName("jLabel82");
		jLabel82.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass14.add(jLabel82);
		jLabel82.setBounds(724, 90, 76, 23);

		jLabel83.setText("Portio :");
		jLabel83.setName("jLabel83");
		jLabel83.setPreferredSize(new java.awt.Dimension(63, 14));
		panelGlass14.add(jLabel83);
		jLabel83.setBounds(340, 30, 125, 23);

		PanelInput2.add(panelGlass14, java.awt.BorderLayout.CENTER);

		internalFrame7.add(PanelInput2, java.awt.BorderLayout.PAGE_START);

		TabMain.addTab("Pemeriksaan Ginekologi", internalFrame7);
//		end Ginekologi
		panelResume1 = new rekammedis.PanelRiwayat();
		panelResume1.setBorder(null);
		panelResume1.setFont(new java.awt.Font("Tahoma", 0, 11));
		panelResume1.setName("panelResume1");
		TabMain.addTab("Riwayat Pasien", panelResume1);
//		End Resume
		panelDiagnosa1 = new laporan.PanelDiagnosa();
		panelDiagnosa1.setBorder(null);
		panelDiagnosa1.setName("panelDiagnosa1");
		TabMain.addTab("Diagnosa", panelDiagnosa1);
//		End Diagnosa
		internalFrame8 = new widget.InternalFrame();
		internalFrame8.setBackground(new java.awt.Color(235, 255, 235));
		internalFrame8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		internalFrame8.setName("internalFrame8");
		internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

		PanelInput3 = new javax.swing.JPanel();
		ChkInput3 = new widget.CekBox();
		panelGlass15 = new widget.panelisi();
		Label jLabel55 = new widget.Label();
		scrollPane4 = new widget.ScrollPane();
		Catatan = new widget.TextArea();
		Label jLabel11 = new widget.Label();
		KdDok3 = new widget.TextBox();
		TDokter3 = new widget.TextBox();
		BtnSeekDokter3 = new widget.Button();
		Scroll11 = new widget.ScrollPane();
		
		PanelInput3.setName("PanelInput3");
		PanelInput3.setOpaque(false);
		PanelInput3.setPreferredSize(new java.awt.Dimension(192, 140));
		PanelInput3.setLayout(new java.awt.BorderLayout(1, 1));
		ChkInput3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png")));
		ChkInput3.setMnemonic('I');
		ChkInput3.setText(".: Input Data");
		ChkInput3.setToolTipText("Alt+I");
		ChkInput3.setBorderPainted(true);
		ChkInput3.setBorderPaintedFlat(true);
		ChkInput3.setFocusable(false);
		ChkInput3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		ChkInput3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		ChkInput3.setName("ChkInput3"); // NOI18N
		ChkInput3.setPreferredSize(new java.awt.Dimension(192, 20));
		ChkInput3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
		ChkInput3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
		ChkInput3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
		ChkInput3.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ChkInput3ActionPerformed(evt);
			}

			private void ChkInput3ActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				isForm4();
			}
		});
		PanelInput3.add(ChkInput3, java.awt.BorderLayout.PAGE_END);

		panelGlass15.setName("panelGlass15"); // NOI18N
		panelGlass15.setPreferredSize(new java.awt.Dimension(44, 104));
		panelGlass15.setLayout(null);

		jLabel55.setText("Catatan :");
		jLabel55.setName("jLabel55"); // NOI18N
		panelGlass15.add(jLabel55);
		jLabel55.setBounds(0, 40, 60, 23);

		scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		scrollPane4.setName("scrollPane4"); // NOI18N

		Catatan.setBorder(null);
		Catatan.setColumns(20);
		Catatan.setRows(5);
		Catatan.setName("Catatan"); // NOI18N
		Catatan.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				CatatanKeyPressed(evt);
			}
		});
		scrollPane4.setViewportView(Catatan);

		panelGlass15.add(scrollPane4);
		scrollPane4.setBounds(64, 40, 713, 68);

		jLabel11.setText("Dokter :");
		jLabel11.setName("jLabel11"); // NOI18N
		panelGlass15.add(jLabel11);
		jLabel11.setBounds(0, 10, 60, 23);

		KdDok3.setHighlighter(null);
		KdDok3.setName("KdDok3"); // NOI18N
		KdDok3.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				KdDok3KeyPressed(evt);
			}
		});
		panelGlass15.add(KdDok3);
		KdDok3.setBounds(64, 10, 146, 23);

		TDokter3.setEditable(false);
		TDokter3.setHighlighter(null);
		TDokter3.setName("TDokter3"); // NOI18N
		panelGlass15.add(TDokter3);
		TDokter3.setBounds(212, 10, 534, 23);

		BtnSeekDokter3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
		BtnSeekDokter3.setMnemonic('4');
		BtnSeekDokter3.setToolTipText("ALt+4");
		BtnSeekDokter3.setName("BtnSeekDokter3"); // NOI18N
		BtnSeekDokter3.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnSeekDokter3ActionPerformed(evt);
			}
		});
		panelGlass15.add(BtnSeekDokter3);
		BtnSeekDokter3.setBounds(749, 10, 28, 23);

		PanelInput3.add(panelGlass15, java.awt.BorderLayout.CENTER);

		internalFrame8.add(PanelInput3, java.awt.BorderLayout.PAGE_START);

		Scroll11.setName("Scroll11"); // NOI18N
		Scroll11.setOpaque(true);

		tbCatatan.setAutoCreateRowSorter(true);
		tbCatatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
		tbCatatan.setName("tbCatatan"); // NOI18N
		tbCatatan.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tbCatatanMouseClicked(evt);
			}
		});
		tbCatatan.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tbCatatanKeyReleased(evt);
			}
		});
		Scroll11.setViewportView(tbCatatan);

		internalFrame8.add(Scroll11, java.awt.BorderLayout.CENTER);

		TabMain.addTab("Catatan Dokter", internalFrame8);
//		End Catatan

		internalFrameMain.add(TabMain, java.awt.BorderLayout.CENTER);
		FormInput = new widget.PanelBiasa();
		Label jLabel3 = new widget.Label();
		TNoRw = new widget.TextBox();
		TNoRM = new widget.TextBox();
		TPasien = new widget.TextBox();
		Label jLabel23 = new widget.Label();
		DTPTgl = new widget.Tanggal();
		cmbJam = new widget.ComboBox();
		cmbMnt = new widget.ComboBox();
		cmbDtk = new widget.ComboBox();
		ChkJln = new widget.CekBox();
		FormInput.setName("FormInput");
		FormInput.setPreferredSize(new java.awt.Dimension(260, 45));
		FormInput.setLayout(null);

		TPoli = new TextBox();
		TPoli.setBounds(595, 9, 141, 23);
		TPoli.setEnabled(false);
		FormInput.add(TPoli);

		jLabel3.setText("No.Rawat :");
		jLabel3.setName("jLabel3");
		FormInput.add(jLabel3);
		jLabel3.setBounds(0, 10, 70, 23);

		TNoRw.setHighlighter(null);
		TNoRw.setName("TNoRw");
		TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TNoRwKeyPressed(evt);
			}
		});
		FormInput.add(TNoRw);
		TNoRw.setBounds(74, 10, 125, 23);

		TNoRM.setEditable(false);
		TNoRM.setHighlighter(null);
		TNoRM.setName("TNoRM");
		FormInput.add(TNoRM);
		TNoRM.setBounds(201, 10, 80, 23);

		TPasien.setEditable(false);
		TPasien.setHighlighter(null);
		TPasien.setName("TPasien");
		FormInput.add(TPasien);
		TPasien.setBounds(283, 10, 270, 23);

		jLabel23.setText("Tanggal :");
		jLabel23.setName("jLabel23");
		FormInput.add(jLabel23);
		jLabel23.setBounds(730, 10, 60, 23);

		DTPTgl.setForeground(new java.awt.Color(50, 70, 50));
		DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-03-2021" }));
		DTPTgl.setDisplayFormat("dd-MM-yyyy");
		DTPTgl.setName("DTPTgl");
		DTPTgl.setOpaque(false);
		DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				DTPTglKeyPressed(evt);
			}
		});
		FormInput.add(DTPTgl);
		DTPTgl.setBounds(793, 10, 90, 23);

		cmbJam.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
						"15", "16", "17", "18", "19", "20", "21", "22", "23" }));
		cmbJam.setName("cmbJam");
		cmbJam.setPreferredSize(new java.awt.Dimension(62, 28));
		cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbJamKeyPressed(evt);
			}
		});
		FormInput.add(cmbJam);
		cmbJam.setBounds(887, 10, 62, 23);

		cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
						"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
						"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
						"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
		cmbMnt.setName("cmbMnt");
		cmbMnt.setPreferredSize(new java.awt.Dimension(62, 28));
		cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbMntKeyPressed(evt);
			}
		});
		FormInput.add(cmbMnt);
		cmbMnt.setBounds(952, 10, 62, 23);

		cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
						"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
						"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
						"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
		cmbDtk.setName("cmbDtk");
		cmbDtk.setPreferredSize(new java.awt.Dimension(62, 28));
		cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmbDtkKeyPressed(evt);
			}
		});
		FormInput.add(cmbDtk);
		cmbDtk.setBounds(1017, 10, 62, 23);

		ChkJln.setBorder(null);
		ChkJln.setSelected(true);
		ChkJln.setBorderPainted(true);
		ChkJln.setBorderPaintedFlat(true);
		ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11));
		ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		ChkJln.setName("ChkJln");
		ChkJln.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ChkJlnActionPerformed(evt);
			}
		});
		FormInput.add(ChkJln);
		ChkJln.setBounds(1082, 10, 23, 23);
		internalFrameMain.add(FormInput, java.awt.BorderLayout.PAGE_START);

		PanelAccor = new widget.PanelBiasa();
		ChkAccor = new widget.CekBox();
		FormMenu = new widget.PanelBiasa();
		BtnResepObat = new widget.Button();
		BtnCopyResep = new widget.Button();
		BtnInputObat = new widget.Button();
		BtnObatBhp = new widget.Button();
		BtnBerkasDigital = new widget.Button();
		BtnPermintaanLab = new widget.Button();
		BtnPermintaanRad = new widget.Button();
		BtnSKDP = new widget.Button();
		BtnKamar = new widget.Button();
		BtnRujukInternal = new widget.Button();
		BtnRujukKeluar = new widget.Button();
		BtnCatatan = new widget.Button();
		BtnTriaseIGD = new widget.Button();
		BtnResume = new widget.Button();
		PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
		PanelAccor.setName("PanelAccor");
		PanelAccor.setPreferredSize(new java.awt.Dimension(190, 43));
		PanelAccor.setLayout(new java.awt.BorderLayout());
		ChkAccor.setBackground(new java.awt.Color(255, 250, 248));
		ChkAccor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 255, 248)));
		ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png")));
		ChkAccor.setFocusable(false);
		ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		ChkAccor.setName("ChkAccor");
		ChkAccor.setPreferredSize(new java.awt.Dimension(15, 20));
		ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png")));
		ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png")));
		ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png")));
		ChkAccor.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ChkAccorActionPerformed(evt);
			}
		});
		PanelAccor.add(ChkAccor, java.awt.BorderLayout.EAST);

		FormMenu.setBackground(new java.awt.Color(255, 255, 255));
		FormMenu.setBorder(null);
		FormMenu.setName("FormMenu");
		FormMenu.setPreferredSize(new java.awt.Dimension(155, 43));
		FormMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 1));

		BtnResepObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnResepObat.setText("Input Resep");
		BtnResepObat.setFocusPainted(false);
		BtnResepObat.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnResepObat.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnResepObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnResepObat.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnResepObat.setName("BtnResepObat");
		BtnResepObat.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnResepObat.setRoundRect(false);
		BtnResepObat.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnResepObatActionPerformed(evt);
			}
		});
		BtnResepObat.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnResepObatKeyPressed(evt);
			}
		});
		FormMenu.add(BtnResepObat);

		BtnCopyResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnCopyResep.setText("Copy Resep");
		BtnCopyResep.setFocusPainted(false);
		BtnCopyResep.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnCopyResep.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnCopyResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnCopyResep.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnCopyResep.setName("BtnCopyResep");
		BtnCopyResep.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnCopyResep.setRoundRect(false);
		BtnCopyResep.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnCopyResepActionPerformed(evt);
			}
		});
		BtnCopyResep.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnCopyResepKeyPressed(evt);
			}
		});
		FormMenu.add(BtnCopyResep);

		BtnInputObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnInputObat.setText("Input Obat & BHP");
		BtnInputObat.setFocusPainted(false);
		BtnInputObat.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnInputObat.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnInputObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnInputObat.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnInputObat.setName("BtnInputObat");
		BtnInputObat.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnInputObat.setRoundRect(false);
		BtnInputObat.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnInputObatActionPerformed(evt);
			}
		});
		BtnInputObat.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnInputObatKeyPressed(evt);
			}
		});
		FormMenu.add(BtnInputObat);

		BtnObatBhp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnObatBhp.setText("Data Obat & BHP");
		BtnObatBhp.setFocusPainted(false);
		BtnObatBhp.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnObatBhp.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnObatBhp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnObatBhp.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnObatBhp.setName("BtnObatBhp");
		BtnObatBhp.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnObatBhp.setRoundRect(false);
		BtnObatBhp.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnObatBhpActionPerformed(evt);
			}
		});
		BtnObatBhp.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnObatBhpKeyPressed(evt);
			}
		});
		FormMenu.add(BtnObatBhp);

		BtnBerkasDigital.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnBerkasDigital.setText("Berkas Digital");
		BtnBerkasDigital.setFocusPainted(false);
		BtnBerkasDigital.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnBerkasDigital.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnBerkasDigital.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnBerkasDigital.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnBerkasDigital.setName("BtnBerkasDigital");
		BtnBerkasDigital.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnBerkasDigital.setRoundRect(false);
		BtnBerkasDigital.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnBerkasDigitalActionPerformed(evt);
			}
		});
		BtnBerkasDigital.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnBerkasDigitalKeyPressed(evt);
			}
		});
		FormMenu.add(BtnBerkasDigital);

		BtnPermintaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnPermintaanLab.setText("Permintaan Lab");
		BtnPermintaanLab.setFocusPainted(false);
		BtnPermintaanLab.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnPermintaanLab.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnPermintaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnPermintaanLab.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnPermintaanLab.setName("BtnPermintaanLab");
		BtnPermintaanLab.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnPermintaanLab.setRoundRect(false);
		BtnPermintaanLab.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnPermintaanLabActionPerformed(evt);
			}
		});
		BtnPermintaanLab.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnPermintaanLabKeyPressed(evt);
			}
		});
		FormMenu.add(BtnPermintaanLab);

		BtnPermintaanRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnPermintaanRad.setText("Permintaan Radiologi");
		BtnPermintaanRad.setFocusPainted(false);
		BtnPermintaanRad.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnPermintaanRad.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnPermintaanRad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnPermintaanRad.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnPermintaanRad.setName("BtnPermintaanRad");
		BtnPermintaanRad.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnPermintaanRad.setRoundRect(false);
		BtnPermintaanRad.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnPermintaanRadActionPerformed(evt);
			}
		});
		BtnPermintaanRad.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				BtnPermintaanRadKeyPressed(evt);
			}
		});
		FormMenu.add(BtnPermintaanRad);

		BtnSKDP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnSKDP.setText("Surat Kontrol");
		BtnSKDP.setFocusPainted(false);
		BtnSKDP.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnSKDP.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnSKDP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnSKDP.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnSKDP.setName("BtnSKDP");
		BtnSKDP.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnSKDP.setRoundRect(false);
		BtnSKDP.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnSKDPActionPerformed(evt);
			}
		});
		FormMenu.add(BtnSKDP);

		BtnKamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnKamar.setText("Kamar Inap");
		BtnKamar.setFocusPainted(false);
		BtnKamar.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnKamar.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnKamar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnKamar.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnKamar.setName("BtnKamar");
		BtnKamar.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnKamar.setRoundRect(false);
		BtnKamar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnKamarActionPerformed(evt);
			}
		});
		FormMenu.add(BtnKamar);

		BtnRujukInternal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnRujukInternal.setText("Rujuk Internal");
		BtnRujukInternal.setFocusPainted(false);
		BtnRujukInternal.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnRujukInternal.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnRujukInternal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnRujukInternal.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnRujukInternal.setName("BtnRujukInternal");
		BtnRujukInternal.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnRujukInternal.setRoundRect(false);
		BtnRujukInternal.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnRujukInternalActionPerformed(evt);
			}
		});
		FormMenu.add(BtnRujukInternal);

		BtnRujukKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnRujukKeluar.setText("Rujuk Keluar");
		BtnRujukKeluar.setFocusPainted(false);
		BtnRujukKeluar.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnRujukKeluar.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnRujukKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnRujukKeluar.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnRujukKeluar.setName("BtnRujukKeluar");
		BtnRujukKeluar.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnRujukKeluar.setRoundRect(false);
		BtnRujukKeluar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnRujukKeluarActionPerformed(evt);
			}
		});
		FormMenu.add(BtnRujukKeluar);

		BtnCatatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnCatatan.setText("Catatan Pasien");
		BtnCatatan.setFocusPainted(false);
		BtnCatatan.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnCatatan.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnCatatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnCatatan.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnCatatan.setName("BtnCatatan");
		BtnCatatan.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnCatatan.setRoundRect(false);
		BtnCatatan.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnCatatanActionPerformed(evt);
			}
		});
		FormMenu.add(BtnCatatan);

		BtnTriaseIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnTriaseIGD.setText("Triase IGD");
		BtnTriaseIGD.setFocusPainted(false);
		BtnTriaseIGD.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnTriaseIGD.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnTriaseIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnTriaseIGD.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnTriaseIGD.setName("BtnTriaseIGD");
		BtnTriaseIGD.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnTriaseIGD.setRoundRect(false);
		BtnTriaseIGD.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnTriaseIGDActionPerformed(evt);
			}
		});
		FormMenu.add(BtnTriaseIGD);

		BtnResume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnResume.setText("Resume Pasien");
		BtnResume.setFocusPainted(false);
		BtnResume.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnResume.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnResume.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnResume.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnResume.setName("BtnResume");
		BtnResume.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnResume.setRoundRect(false);
		BtnResume.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BtnResumeActionPerformed(evt);
			}
		});
		FormMenu.add(BtnResume);

		BtnAwalKeperawatan = new widget.Button();
		BtnAwalKeperawatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnAwalKeperawatan.setText("Nilai Awal Keperawatan");
		BtnAwalKeperawatan.setFocusPainted(false);
		BtnAwalKeperawatan.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnAwalKeperawatan.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnAwalKeperawatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnAwalKeperawatan.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnAwalKeperawatan.setName("BtnAwalKeperawatan");
		BtnAwalKeperawatan.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnAwalKeperawatan.setRoundRect(false);
		BtnAwalKeperawatan.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				RMPenilaianAwalKeperawatanRalan form = new RMPenilaianAwalKeperawatanRalan(null, false);
				form.isCek();
				form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
				form.emptTeks();
				form.setSize(internalFrameMain.getWidth() - 20, internalFrameMain.getHeight() - 20);
				form.setLocationRelativeTo(internalFrameMain);
				form.setVisible(true);

			}
		});
		FormMenu.add(BtnAwalKeperawatan);

		BtnAwalKeperawatanGigi = new widget.Button();
		BtnAwalKeperawatanGigi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnAwalKeperawatanGigi.setText("Gigi & Mulut");
		BtnAwalKeperawatanGigi.setFocusPainted(false);
		BtnAwalKeperawatanGigi.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnAwalKeperawatanGigi.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnAwalKeperawatanGigi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnAwalKeperawatanGigi.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnAwalKeperawatanGigi.setName("BtnAwalKeperawatanGigi");
		BtnAwalKeperawatanGigi.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnAwalKeperawatanGigi.setRoundRect(false);
		BtnAwalKeperawatanGigi.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				RMPenilaianAwalKeperawatanGigi form = new RMPenilaianAwalKeperawatanGigi(null, false);
				form.isCek();
				form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
				form.emptTeks();
				form.setSize(internalFrameMain.getWidth() - 20, internalFrameMain.getHeight() - 20);
				form.setLocationRelativeTo(internalFrameMain);
				form.setVisible(true);

			}
		});
		FormMenu.add(BtnAwalKeperawatanGigi);

		BtnPeniliaianAwalKeperawatanKebidanan = new widget.Button();
		BtnPeniliaianAwalKeperawatanKebidanan
				.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnPeniliaianAwalKeperawatanKebidanan.setText("Kebidanan & Kandungan");
		BtnPeniliaianAwalKeperawatanKebidanan.setFocusPainted(false);
		BtnPeniliaianAwalKeperawatanKebidanan.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnPeniliaianAwalKeperawatanKebidanan.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnPeniliaianAwalKeperawatanKebidanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnPeniliaianAwalKeperawatanKebidanan.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnPeniliaianAwalKeperawatanKebidanan.setName("BtnPeniliaianAwalKeperawatanKebidanan");
		BtnPeniliaianAwalKeperawatanKebidanan.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnPeniliaianAwalKeperawatanKebidanan.setRoundRect(false);
		BtnPeniliaianAwalKeperawatanKebidanan.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				RMPenilaianAwalKeperawatanKebidanan form = new RMPenilaianAwalKeperawatanKebidanan(null, false);
				form.isCek();
				form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
				form.emptTeks();
				form.setSize(internalFrameMain.getWidth() - 20, internalFrameMain.getHeight() - 20);
				form.setLocationRelativeTo(internalFrameMain);
				form.setVisible(true);

			}
		});
		FormMenu.add(BtnPeniliaianAwalKeperawatanKebidanan);

		BtnAwalKeperawatanBayiAnak = new widget.Button();
		BtnAwalKeperawatanBayiAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png")));
		BtnAwalKeperawatanBayiAnak.setText("Keperawatan Bayi/Anak");
		BtnAwalKeperawatanBayiAnak.setFocusPainted(false);
		BtnAwalKeperawatanBayiAnak.setFont(new java.awt.Font("Tahoma", 0, 11));
		BtnAwalKeperawatanBayiAnak.setGlassColor(new java.awt.Color(255, 255, 255));
		BtnAwalKeperawatanBayiAnak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		BtnAwalKeperawatanBayiAnak.setMargin(new java.awt.Insets(1, 1, 1, 1));
		BtnAwalKeperawatanBayiAnak.setName("BtnAwalKeperawatanBayiAnak");
		BtnAwalKeperawatanBayiAnak.setPreferredSize(new java.awt.Dimension(145, 23));
		BtnAwalKeperawatanBayiAnak.setRoundRect(false);
		BtnAwalKeperawatanBayiAnak.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				RMPenilaianAwalKeperawatanBayiAnak form = new RMPenilaianAwalKeperawatanBayiAnak(null, false);
				form.isCek();
				form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
				// form.emptTeks();
				form.setSize(internalFrameMain.getWidth() - 20, internalFrameMain.getHeight() - 20);
				form.setLocationRelativeTo(internalFrameMain);
				form.setVisible(true);

			}
		});
		FormMenu.add(BtnAwalKeperawatanBayiAnak);

		PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);
		internalFrameMain.add(PanelAccor, java.awt.BorderLayout.WEST);

		getContentPane().add(internalFrameMain, java.awt.BorderLayout.CENTER);
		pack();
	}

	protected void isForm4() {
		// TODO Auto-generated method stub
		if (ChkInput3.isSelected() == true) {
			ChkInput3.setVisible(false);
			PanelInput3.setPreferredSize(new Dimension(WIDTH, 140));
			panelGlass15.setVisible(true);
			ChkInput3.setVisible(true);
		} else if (ChkInput3.isSelected() == false) {
			ChkInput3.setVisible(false);
			PanelInput3.setPreferredSize(new Dimension(WIDTH, 20));
			panelGlass15.setVisible(false);
			ChkInput3.setVisible(true);
		}
	}

	protected void tbCatatanMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		if (TabModeCatatan.getRowCount() != 0) {
			try {
				getDataCatatan();
			} catch (java.lang.NullPointerException e) {
			}
		}
	}

	private void getDataCatatan() {
		// TODO Auto-generated method stub
		if (tbCatatan.getSelectedRow() != -1) {
			TNoRw.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 1).toString());
			TNoRM.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 2).toString());
			TPasien.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 3).toString());
			KdDok3.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 6).toString());
			TDokter3.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 7).toString());
			Catatan.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 8).toString());
			cmbJam.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 5).toString().substring(0, 2));
			cmbMnt.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 5).toString().substring(3, 5));
			cmbDtk.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 5).toString().substring(6, 8));
			Valid.SetTgl(DTPTgl, tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 4).toString());
		}
	}

	protected void CatatanKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, KdDok3, BtnSimpan);
	}

	protected void BtnSeekDokter3ActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		akses.setform("DlgRawatJalan");
		dokter.emptTeks();
		dokter.isCek();
		dokter.setSize(internalFrameMain.getWidth() - 20, internalFrameMain.getHeight() - 20);
		dokter.setLocationRelativeTo(internalFrameMain);
		dokter.setVisible(true);
	}

	protected void KdDok3KeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
			Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter3, KdDok3.getText());
		} else if (evt.getKeyCode() == KeyEvent.VK_UP) {
			BtnSeekDokter3ActionPerformed(null);
		} else {
			Valid.pindah(evt, TNoRw, BtnSeekDokter3);
		}
	}

	protected void tbCatatanKeyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (TabModeCatatan.getRowCount() != 0) {
			if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP)
					|| (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
				try {
					getDataCatatan();
				} catch (java.lang.NullPointerException e) {
				}
			}

		}
	}

	protected void TSondageKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TPortioInspekulo, TPortioDalam);
	}

	protected void cmbNyeriTekanKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TUkuran, TAdnexaKanan);
	}

	protected void TCavumDouglasKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TAdnexaKiri, BtnSimpan);
	}

	protected void TUkuranKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbMobilitas, cmbNyeriTekan);
	}

	protected void TAdnexaKiriKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TAdnexaKanan, TCavumDouglas);
	}

	protected void TBentukKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TPortioDalam, TCavumUteri);
	}

	protected void TVulvaInspekuloKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbFluorGine, TPortioInspekulo);
	}

	protected void TPortioDalamKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TSondage, TBentuk);
	}

	protected void TInspeksiKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TNoRw, TInspeksiVulva);
	}

	protected void cmbMobilitasKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TCavumUteri, TUkuran);
	}

	protected void TInspekuloGineKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TInspeksiVulva, cmbFluxusGine);
	}

	protected void cmbFluxusGineKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TInspekuloGine, cmbFluorGine);
	}

	protected void cmbFluorGineKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbFluxusGine, TVulvaInspekulo);
	}

	protected void TAdnexaKananKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbNyeriTekan, TAdnexaKiri);
	}

	protected void TInspeksiVulvaKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TInspeksi, TInspekuloGine);
	}

	protected void TCavumUteriKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TBentuk, cmbMobilitas);
	}

	protected void TPortioInspekuloKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TVulvaInspekulo, TSondage);
	}

	protected void ChkInput2ActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		isForm3();
	}

	private void isForm3() {
		// TODO Auto-generated method stub
		if (ChkInput2.isSelected() == true) {
			ChkInput2.setVisible(false);
			PanelInput2.setPreferredSize(new Dimension(WIDTH, 246));
			panelGlass14.setVisible(true);
			ChkInput2.setVisible(true);
		} else if (ChkInput2.isSelected() == false) {
			ChkInput2.setVisible(false);
			PanelInput2.setPreferredSize(new Dimension(WIDTH, 20));
			panelGlass14.setVisible(false);
			ChkInput2.setVisible(true);
		}
	}

	protected void tbPemeriksaanGinekologiMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		if (tabModeGinekologi.getRowCount() != 0) {
			try {
				getDataPemeriksaanGinekologi();

			} catch (java.lang.NullPointerException e) {

			}
		}
	}

	private void getDataPemeriksaanGinekologi() {
		// TODO Auto-generated method stub
		if (tbPemeriksaanGinekologi.getSelectedRow() != -1) {
			TNoRw.setText(tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 1).toString());
			TNoRM.setText(tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 2).toString());
			TPasien.setText(tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 3).toString());
			Valid.SetTgl(DTPTgl,
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 4).toString());
			cmbJam.setSelectedItem(tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 5)
					.toString().substring(0, 2));
			cmbMnt.setSelectedItem(tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 5)
					.toString().substring(3, 5));
			cmbDtk.setSelectedItem(tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 5)
					.toString().substring(6, 8));
			TInspeksi.setText(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 6).toString());
			TInspeksiVulva.setText(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 7).toString());
			TInspekuloGine.setText(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 8).toString());
			cmbFluxusGine.setSelectedItem(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 9).toString());
			cmbFluorGine.setSelectedItem(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 10).toString());
			TVulvaInspekulo.setText(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 11).toString());
			TPortioInspekulo.setText(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 12).toString());
			TSondage.setText(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 13).toString());
			TPortioDalam.setText(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 14).toString());
			TBentuk.setText(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 15).toString());
			TCavumUteri.setText(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 16).toString());
			cmbMobilitas.setSelectedItem(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 17).toString());
			TUkuran.setText(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 18).toString());
			cmbNyeriTekan.setSelectedItem(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 19).toString());
			TAdnexaKanan.setText(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 20).toString());
			TAdnexaKiri.setText(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 21).toString());
			TCavumDouglas.setText(
					tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 22).toString());
		}
	}

	protected void tbPemeriksaanGinekologiKeyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (tabModeGinekologi.getRowCount() != 0) {
			if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP)
					|| (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
				try {
					getDataPemeriksaanGinekologi();
				} catch (java.lang.NullPointerException e) {
				}
			}
		}
	}

	protected void Scroll5KeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub

	}

	protected void cmbArahKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TTebal, TPembukaan);
	}

	protected void TPortioKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TVulva, cmbDalam);
	}

	protected void TPenurunanKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TPembukaan, TDenominator);
	}

	protected void cmbDalamKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TPortio, TTebal);
	}

	private void cmbFluksusKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_cmbFluksusKeyPressed
		Valid.pindah(evt, TKualitas_dtk, cmbAlbus);
	}

	protected void TVulvaKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbKetuban, TPortio);
	}

	protected void cmbKontraksiKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TDenyut, TKualitas_mnt);
	}

	protected void cmbAlbusKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbFluksus, cmbKetuban);
	}

	protected void cmbKetubanKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbAlbus, TVulva);
	}

	protected void cmbFetoKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TDenominator, BtnSimpan);
	}

	protected void TDenominatorKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TPenurunan, cmbFeto);
	}

	protected void TKualitas_mntKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbKontraksi, TKualitas_dtk);
	}

	protected void TPembukaanKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbArah, TPenurunan);
	}

	protected void cmbJaninKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TTinggi_uteri, TLetak);
	}

	protected void TDenyutKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbPanggul, cmbKontraksi);
	}

	protected void TTebalKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbDalam, cmbArah);
	}

	protected void cmbPanggulKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TLetak, TDenyut);
	}

	protected void TLetakKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbJanin, cmbPanggul);
	}

	protected void TKualitas_dtkKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TKualitas_mnt, cmbFluksus);
	}

	protected void ChkInput1ActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		isForm2();
	}

	private void isForm2() {
		// TODO Auto-generated method stub
		if (ChkInput1.isSelected() == true) {
			ChkInput1.setVisible(false);
			PanelInput1.setPreferredSize(new Dimension(WIDTH, 156));
			panelGlass13.setVisible(true);
			ChkInput1.setVisible(true);
		} else if (ChkInput1.isSelected() == false) {
			ChkInput1.setVisible(false);
			PanelInput1.setPreferredSize(new Dimension(WIDTH, 20));
			panelGlass13.setVisible(false);
			ChkInput1.setVisible(true);
		}
	}

	protected void TTinggi_uteriKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TNoRw, cmbJanin);
	}

	protected void tbPemeriksaanObstetriKeyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (tabModeObstetri.getRowCount() != 0) {
			if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP)
					|| (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
				try {
					getDataPemeriksaanObstetri();
				} catch (java.lang.NullPointerException e) {
				}
			}
		}
	}

	private void getDataPemeriksaanObstetri() {
		// TODO Auto-generated method stub
		if (tbPemeriksaanObstetri.getSelectedRow() != -1) {
			TNoRw.setText(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 1).toString());
			TNoRM.setText(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 2).toString());
			TPasien.setText(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 3).toString());
			Valid.SetTgl(DTPTgl,
					tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 4).toString());
			cmbJam.setSelectedItem(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 5)
					.toString().substring(0, 2));
			cmbMnt.setSelectedItem(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 5)
					.toString().substring(3, 5));
			cmbDtk.setSelectedItem(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 5)
					.toString().substring(6, 8));
			TTinggi_uteri
					.setText(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 6).toString());
			cmbJanin.setSelectedItem(
					tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 7).toString());
			TLetak.setText(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 8).toString());
			cmbPanggul.setSelectedItem(
					tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 9).toString());
			TDenyut.setText(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 10).toString());
			cmbKontraksi.setSelectedItem(
					tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 11).toString());
			TKualitas_mnt
					.setText(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 12).toString());
			TKualitas_dtk
					.setText(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 13).toString());
			cmbFluksus.setSelectedItem(
					tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 14).toString());
			cmbAlbus.setSelectedItem(
					tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 15).toString());
			TVulva.setText(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 16).toString());
			TPortio.setText(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 17).toString());
			cmbDalam.setSelectedItem(
					tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 18).toString());
			TTebal.setText(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 19).toString());
			cmbArah.setSelectedItem(
					tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 20).toString());
			TPembukaan.setText(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 21).toString());
			TPenurunan.setText(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 22).toString());
			TDenominator
					.setText(tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 23).toString());
			cmbKetuban.setSelectedItem(
					tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 24).toString());
			cmbFeto.setSelectedItem(
					tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 25).toString());
		}
	}

	protected void tbPemeriksaanObstetriMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		if (tabModeObstetri.getRowCount() != 0) {
			try {
				getDataPemeriksaanObstetri();

			} catch (java.lang.NullPointerException e) {

			}
		}
	}

	protected void BtnSeekPetugas2ActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		akses.setform("DlgRawatJalan");
		petugas.emptTeks();
		petugas.isCek();
		petugas.setSize(internalFrameMain.getWidth() - 20, internalFrameMain.getHeight() - 20);
		petugas.setLocationRelativeTo(internalFrameMain);
		petugas.setVisible(true);
	}

	protected void ChkInputVaksinActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (ChkInputVaksn.isSelected() == true) {
			ChkInputVaksn.setVisible(false);
			PanelInputVaksin.setPreferredSize(new Dimension(WIDTH, 243));
			panelGlassVaksin.setVisible(true);
			ChkInputVaksn.setVisible(true);
		} else if (ChkInputVaksn.isSelected() == false) {
			ChkInputVaksn.setVisible(false);
			PanelInputVaksin.setPreferredSize(new Dimension(WIDTH, 20));
			panelGlassVaksin.setVisible(false);
			ChkInputVaksn.setVisible(true);
		}
	}

	protected void tbPemeriksaanVaksinKeyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (tableModePemeriksaanVaksin.getRowCount() != 0) {
			if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP)
					|| (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
				try {
					getDataPemeriksaanvaksin();
				} catch (java.lang.NullPointerException e) {
				}
			}

		}
	}

	private void getDataPemeriksaanvaksin() {
		// TODO Auto-generated method stub
		if (tbPemeriksaanVaksin.getSelectedRow() != -1) {
//			TNoRw.setText(tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 1).toString());
//			TNoRM.setText(tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 2).toString());
			Valid.SetTgl(DTPTgl, tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 4).toString());
			cmbJam.setSelectedItem(
					tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 5).toString().substring(0, 2));
			cmbMnt.setSelectedItem(
					tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 5).toString().substring(3, 5));
			cmbDtk.setSelectedItem(
					tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 5).toString().substring(6, 8));
//			TPasien.setText(tbPemeriksaanVaksin.getValueAt(tbPemeriksaan.getSelectedRow(), 3).toString());
			TSuhuVaksin.setText(tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 6).toString());
			TtensiVaksn.setText(tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 7).toString());
			TNadiVaksn.setText(tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 9).toString());
			TRespirasiVaksn.setText(tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 8).toString());
			TtinggiVaksn.setText(tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 10).toString());
			TKgVaksn.setText(tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 11).toString());
			TGCSVaksin.setText(tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 12).toString());
			TxtareaSubject.setText(tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 13).toString());
			TxtareaObject.setText(tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 14).toString());
			TAlegiVaksn.setText(tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 15).toString());
			cmbImunVaksin.setSelectedItem(
					tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 3).toString());
			TlinkardadaVksn
					.setText(tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 16).toString());
			String namaVaksin = tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 2).toString();
			String namaPetugas = tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 17).toString();
			Tkdvksin.setText(Sequel
					.cariIsi("select kode_imunisasi from master_imunisasi where nama_imunisasi='" + namaVaksin + "'"));
			Tvksin.setText(namaVaksin);
			kdptgsVasksin.setText(Sequel.cariIsi("select nip from petugas where nama='" + namaPetugas + "'"));
			TPegawaiVk.setText(namaPetugas);
		}
	}

	protected void tbPemeriksaanVaksinMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		if (tableModePemeriksaanVaksin.getRowCount() != 0) {
			try {
				getDataPemeriksaanvaksin();
			} catch (java.lang.NullPointerException e) {
			}

		}
	}

	protected void TInstruksiKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah2(evt, TindakLanjut, BtnSimpan);
	}

	protected void BtnSeekPegawaiActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		akses.setform("DlgRawatJalan");
		pegawai.emptTeks();
		pegawai.setSize(internalFrameMain.getWidth() - 20, internalFrameMain.getHeight() - 20);
		pegawai.setLocationRelativeTo(internalFrameMain);
		pegawai.setVisible(true);
	}

	protected void cmbKesadaranKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TGCS, cmbImun);
	}

	protected void KdPegKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
			Sequel.cariIsi("select nama from pegawai where nik=?", TPegawai, KdPeg.getText());
		} else if (evt.getKeyCode() == KeyEvent.VK_UP) {
			BtnSeekPegawaiActionPerformed(null);
		} else {
			Valid.pindah(evt, TNoRw, TKeluhan);
		}
	}

	protected void TindakLanjutKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah2(evt, TPenilaian, TInstruksi);
	}

	protected void TPenilaianKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah2(evt, TAlergi, TindakLanjut);
	}

	protected void TAlergiKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbImun, TPenilaian);
	}

	protected void TGCSKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TNadi, cmbKesadaran);
	}

	protected void TNadiKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TRespirasi, TGCS);
	}

	protected void TTensiKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TBerat, TRespirasi);
	}

	protected void TTinggiKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TSuhu, TBerat);
	}

	protected void TNadiActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	protected void TBeratKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TTinggi, TTensi);
	}

	protected void TRespirasiKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TTensi, TNadi);
	}

	protected void TPemeriksaanKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah2(evt, TKeluhan, TSuhu);
	}

	protected void TSuhuKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TPemeriksaan, TTinggi);
	}

	protected void TKeluhanKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah2(evt, KdPeg, TPemeriksaan);
	}

	protected void cmbImunKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbKesadaran, TAlergi);
	}

	protected void ChkInputActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		isForm();
	}

	private void isForm() {
		// TODO Auto-generated method stub
		if (ChkInput.isSelected() == true) {
			ChkInput.setVisible(false);
			PanelInput.setPreferredSize(new Dimension(WIDTH, 243));
			panelGlass12.setVisible(true);
			ChkInput.setVisible(true);
		} else if (ChkInput.isSelected() == false) {
			ChkInput.setVisible(false);
			PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
			panelGlass12.setVisible(false);
			ChkInput.setVisible(true);
		}
	}

	protected void tbPemeriksaanKeyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (tabModePemeriksaan.getRowCount() != 0) {
			if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP)
					|| (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
				try {
					getDataPemeriksaan();
				} catch (java.lang.NullPointerException e) {
				}
			}

		}
	}

	private void getDataPemeriksaan() {
		// TODO Auto-generated method stub
		if (tbPemeriksaan.getSelectedRow() != -1) {
			TNoRw.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 1).toString());
			TNoRM.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 2).toString());
			TPasien.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 3).toString());
			TSuhu.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 6).toString());
			TTensi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 7).toString());
			TNadi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 8).toString());
			TRespirasi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 9).toString());
			TTinggi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 10).toString());
			TBerat.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 11).toString());
			TGCS.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 12).toString());
			cmbKesadaran.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 13).toString());
			TKeluhan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 14).toString());
			TPemeriksaan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 15).toString());
			TAlergi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 16).toString());
			cmbImun.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 17).toString());
			TindakLanjut.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 18).toString());
			TPenilaian.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 19).toString());
			TInstruksi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 20).toString());
			KdPeg.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 21).toString());
			TPegawai.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 22).toString());
			Jabatan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 23).toString());
			cmbJam.setSelectedItem(
					tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 5).toString().substring(0, 2));
			cmbMnt.setSelectedItem(
					tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 5).toString().substring(3, 5));
			cmbDtk.setSelectedItem(
					tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 5).toString().substring(6, 8));
			Valid.SetTgl(DTPTgl, tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 4).toString());
		}
	}

	protected void tbPemeriksaanMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		if (tabModePemeriksaan.getRowCount() != 0) {
			try {
				getDataPemeriksaan();
			} catch (java.lang.NullPointerException e) {
			}

		}
	}

	protected void KdDokKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
			Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter, KdDok.getText());
		} else if (evt.getKeyCode() == KeyEvent.VK_UP) {
			BtnSeekDokterActionPerformed(null);
		} else {
			Valid.pindah(evt, TNoRw, BtnSeekDokter);
		}
	}

	protected void BtnCariKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
			BtnCariActionPerformed(null);
		} else {
			Valid.pindah(evt, TCari, BtnAll);
		}
	}

	protected void BtnTambahTindakanActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		DlgJnsPerawatanRalan perawatan = new DlgJnsPerawatanRalan(null, false);
		perawatan.emptTeks();
		perawatan.isCek();
		perawatan.setSize(internalFrameMain.getWidth(), internalFrameMain.getHeight());
		perawatan.setLocationRelativeTo(internalFrameMain);
		perawatan.setAlwaysOnTop(false);
		perawatan.setVisible(true);
		this.setCursor(Cursor.getDefaultCursor());
	}

	protected void BtnCariActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		TampilkanData();
	}

	private void TampilkanData() {
		// TODO Auto-generated method stub
		switch (TabMain.getSelectedIndex()) {
		case 0:
			tampilkanPenangananDokterPetugas();
			break;
		case 1:
			tampilPemeriksaan();
			break;
		case 2:
			tampilPemeriksaanVaksin();
			break;
		case 3:
			tampilPemeriksaanObstetri();
			break;
		case 4:
			tampilPemeriksaanGinekologi();
			break;
		case 5:
			if (akses.getresume_pasien() == true) {
				panelResume1.setRM(TNoRM.getText(), Valid.SetTgl(DTPCari1.getSelectedItem() + ""),
						Valid.SetTgl(DTPCari2.getSelectedItem() + ""), false);
				panelResume1.pilihTab();
			}
			LCount.setText("0");
			break;
		case 6:
			if (akses.getdiagnosa_pasien() == true) {
				panelDiagnosa1.setRM(TNoRw.getText(), TNoRM.getText(), Valid.SetTgl(DTPCari1.getSelectedItem() + ""),
						Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "Ralan", TCari.getText().trim());
				panelDiagnosa1.pilihTab();
				LCount.setText(panelDiagnosa1.getRecord() + "");
			}
			break;
		case 7:
			if (akses.getcatatan_perawatan() == true) {
				tampilCatatan();
			}
			break;
		default:
			break;
		}
	}

	private void tampilCatatan() {
		// TODO Auto-generated method stub
		Valid.tabelKosong(TabModeCatatan);
		ResultSet rs = null;
		try {
			ps4 = koneksi
					.prepareStatement("select catatan_perawatan.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
							+ "catatan_perawatan.tanggal,catatan_perawatan.jam,catatan_perawatan.kd_dokter,dokter.nm_dokter,"
							+ "catatan_perawatan.catatan from pasien inner join reg_periksa inner join catatan_perawatan inner join dokter "
							+ "on catatan_perawatan.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
							+ "and catatan_perawatan.kd_dokter=dokter.kd_dokter where  "
							+ "catatan_perawatan.tanggal between ? and ? and reg_periksa.no_rkm_medis like ? and catatan_perawatan.no_rawat like ? or "
							+ "catatan_perawatan.tanggal between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
							+ "catatan_perawatan.tanggal between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or  "
							+ "catatan_perawatan.tanggal between ? and ? and reg_periksa.no_rkm_medis like ? and catatan_perawatan.catatan like ? or "
							+ "catatan_perawatan.tanggal between ? and ? and reg_periksa.no_rkm_medis like ? and catatan_perawatan.kd_dokter like ? or "
							+ "catatan_perawatan.tanggal between ? and ? and reg_periksa.no_rkm_medis like ? and dokter.nm_dokter like ? "
							+ "order by catatan_perawatan.no_rawat desc");
			try {
				ps4.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps4.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps4.setString(3, "%" + TCariPasien.getText() + "%");
				ps4.setString(4, "%" + TCari.getText().trim() + "%");
				ps4.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps4.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps4.setString(7, "%" + TCariPasien.getText() + "%");
				ps4.setString(8, "%" + TCari.getText().trim() + "%");
				ps4.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps4.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps4.setString(11, "%" + TCariPasien.getText() + "%");
				ps4.setString(12, "%" + TCari.getText().trim() + "%");
				ps4.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps4.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps4.setString(15, "%" + TCariPasien.getText() + "%");
				ps4.setString(16, "%" + TCari.getText().trim() + "%");
				ps4.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps4.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps4.setString(19, "%" + TCariPasien.getText() + "%");
				ps4.setString(20, "%" + TCari.getText().trim() + "%");
				ps4.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps4.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps4.setString(23, "%" + TCariPasien.getText() + "%");
				ps4.setString(24, "%" + TCari.getText().trim() + "%");
				rs = ps4.executeQuery();
				while (rs.next()) {
					TabModeCatatan.addRow(new Object[] { false, rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8) });
				}
			} catch (Exception e) {
				System.out.println("Notifikasi Catatan : " + e);
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (ps4 != null) {
					ps4.close();
				}
			}
		} catch (Exception e) {
			System.out.println("Notifikasi : " + e);
		}
		LCount.setText("" + TabModeCatatan.getRowCount());
	}

	private void tampilPemeriksaanGinekologi() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Valid.tabelKosong(tabModeGinekologi);
		try {
			ps6 = koneksi.prepareStatement(
					"select pemeriksaan_ginekologi_ralan.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
							+ "pemeriksaan_ginekologi_ralan.tgl_perawatan,pemeriksaan_ginekologi_ralan.jam_rawat,pemeriksaan_ginekologi_ralan.inspeksi,pemeriksaan_ginekologi_ralan.inspeksi_vulva,pemeriksaan_ginekologi_ralan.inspekulo_gine, "
							+ "pemeriksaan_ginekologi_ralan.fluxus_gine,pemeriksaan_ginekologi_ralan.fluor_gine,pemeriksaan_ginekologi_ralan.vulva_inspekulo, "
							+ "pemeriksaan_ginekologi_ralan.portio_inspekulo,pemeriksaan_ginekologi_ralan.sondage,pemeriksaan_ginekologi_ralan.portio_dalam,pemeriksaan_ginekologi_ralan.bentuk, "
							+ "pemeriksaan_ginekologi_ralan.cavum_uteri,pemeriksaan_ginekologi_ralan.mobilitas,pemeriksaan_ginekologi_ralan.ukuran, pemeriksaan_ginekologi_ralan.nyeri_tekan, pemeriksaan_ginekologi_ralan.adnexa_kanan, pemeriksaan_ginekologi_ralan.adnexa_kiri,"
							+ "pemeriksaan_ginekologi_ralan.cavum_douglas "
							+ "from pasien inner join reg_periksa inner join pemeriksaan_ginekologi_ralan "
							+ "on pemeriksaan_ginekologi_ralan.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where  "
							+ "pemeriksaan_ginekologi_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ginekologi_ralan.no_rawat like ? or "
							+ "pemeriksaan_ginekologi_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
							+ "pemeriksaan_ginekologi_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or  "
							+ "pemeriksaan_ginekologi_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ginekologi_ralan.inspeksi like ? or "
							+ "pemeriksaan_ginekologi_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ginekologi_ralan.inspeksi_vulva like ? or "
							+ "pemeriksaan_ginekologi_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ginekologi_ralan.inspekulo_gine like ? "
							+ "order by pemeriksaan_ginekologi_ralan.no_rawat desc");
			try {
				ps6.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps6.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps6.setString(3, "%" + TCariPasien.getText() + "%");
				ps6.setString(4, "%" + TCari.getText().trim() + "%");
				ps6.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps6.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps6.setString(7, "%" + TCariPasien.getText() + "%");
				ps6.setString(8, "%" + TCari.getText().trim() + "%");
				ps6.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps6.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps6.setString(11, "%" + TCariPasien.getText() + "%");
				ps6.setString(12, "%" + TCari.getText().trim() + "%");
				ps6.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps6.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps6.setString(15, "%" + TCariPasien.getText() + "%");
				ps6.setString(16, "%" + TCari.getText().trim() + "%");
				ps6.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps6.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps6.setString(19, "%" + TCariPasien.getText() + "%");
				ps6.setString(20, "%" + TCari.getText().trim() + "%");
				ps6.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps6.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps6.setString(23, "%" + TCariPasien.getText() + "%");
				ps6.setString(24, "%" + TCari.getText().trim() + "%");

				rs = ps6.executeQuery();
				while (rs.next()) {
					tabModeGinekologi.addRow(new Object[] { false, rs.getString("no_rawat"),
							rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_perawatan"),
							rs.getString("jam_rawat"), rs.getString("inspeksi"), rs.getString("inspeksi_vulva"),
							rs.getString("inspekulo_gine"), rs.getString("fluxus_gine"), rs.getString("fluor_gine"),
							rs.getString("vulva_inspekulo"), rs.getString("portio_inspekulo"), rs.getString("sondage"),
							rs.getString("portio_dalam"), rs.getString("bentuk"), rs.getString("cavum_uteri"),
							rs.getString("mobilitas"), rs.getString("ukuran"), rs.getString("nyeri_tekan"),
							rs.getString("adnexa_kanan"), rs.getString("adnexa_kiri"), rs.getString("cavum_douglas") });
				}
			} catch (Exception e) {
				System.out.println("Notifikasi : " + e);
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (ps5 != null) {
					ps5.close();
				}
			}
		} catch (Exception e) {
			System.out.println("Notifikasi :" + e);
		}
		LCount.setText("" + tabModeGinekologi.getRowCount());
	}

	private void tampilPemeriksaanObstetri() {
		// TODO Auto-generated method stub
		Valid.tabelKosong(tabModeObstetri);
		ResultSet rs = null;
		try {
			ps5 = koneksi.prepareStatement(
					"select pemeriksaan_obstetri_ralan.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
							+ "pemeriksaan_obstetri_ralan.tgl_perawatan,pemeriksaan_obstetri_ralan.jam_rawat,pemeriksaan_obstetri_ralan.tinggi_uteri,pemeriksaan_obstetri_ralan.janin,pemeriksaan_obstetri_ralan.letak, "
							+ "pemeriksaan_obstetri_ralan.panggul,pemeriksaan_obstetri_ralan.denyut,pemeriksaan_obstetri_ralan.kontraksi, "
							+ "pemeriksaan_obstetri_ralan.kualitas_mnt,pemeriksaan_obstetri_ralan.kualitas_dtk,pemeriksaan_obstetri_ralan.fluksus,pemeriksaan_obstetri_ralan.albus, "
							+ "pemeriksaan_obstetri_ralan.vulva,pemeriksaan_obstetri_ralan.portio,pemeriksaan_obstetri_ralan.dalam, pemeriksaan_obstetri_ralan.tebal, pemeriksaan_obstetri_ralan.arah, pemeriksaan_obstetri_ralan.pembukaan,"
							+ "pemeriksaan_obstetri_ralan.penurunan, pemeriksaan_obstetri_ralan.denominator, pemeriksaan_obstetri_ralan.ketuban, pemeriksaan_obstetri_ralan.feto "
							+ "from pasien inner join reg_periksa inner join pemeriksaan_obstetri_ralan "
							+ "on pemeriksaan_obstetri_ralan.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where  "
							+ "pemeriksaan_obstetri_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_obstetri_ralan.no_rawat like ? or "
							+ "pemeriksaan_obstetri_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
							+ "pemeriksaan_obstetri_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or  "
							+ "pemeriksaan_obstetri_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_obstetri_ralan.tinggi_uteri like ? or "
							+ "pemeriksaan_obstetri_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_obstetri_ralan.janin like ? or "
							+ "pemeriksaan_obstetri_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_obstetri_ralan.letak like ? "
							+ "order by pemeriksaan_obstetri_ralan.no_rawat desc");
			try {
				ps5.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps5.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps5.setString(3, "%" + TCariPasien.getText() + "%");
				ps5.setString(4, "%" + TCari.getText().trim() + "%");
				ps5.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps5.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps5.setString(7, "%" + TCariPasien.getText() + "%");
				ps5.setString(8, "%" + TCari.getText().trim() + "%");
				ps5.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps5.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps5.setString(11, "%" + TCariPasien.getText() + "%");
				ps5.setString(12, "%" + TCari.getText().trim() + "%");
				ps5.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps5.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps5.setString(15, "%" + TCariPasien.getText() + "%");
				ps5.setString(16, "%" + TCari.getText().trim() + "%");
				ps5.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps5.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps5.setString(19, "%" + TCariPasien.getText() + "%");
				ps5.setString(20, "%" + TCari.getText().trim() + "%");
				ps5.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps5.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps5.setString(23, "%" + TCariPasien.getText() + "%");
				ps5.setString(24, "%" + TCari.getText().trim() + "%");
				rs = ps5.executeQuery();
				while (rs.next()) {
					tabModeObstetri.addRow(new Object[] { false, rs.getString("no_rawat"), rs.getString("no_rkm_medis"),
							rs.getString("nm_pasien"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"),
							rs.getString("tinggi_uteri"), rs.getString("janin"), rs.getString("letak"),
							rs.getString("panggul"), rs.getString("denyut"), rs.getString("kontraksi"),
							rs.getString("kualitas_mnt"), rs.getString("kualitas_dtk"), rs.getString("fluksus"),
							rs.getString("albus"), rs.getString("vulva"), rs.getString("portio"), rs.getString("dalam"),
							rs.getString("tebal"), rs.getString("arah"), rs.getString("pembukaan"),
							rs.getString("penurunan"), rs.getString("denominator"), rs.getString("ketuban"),
							rs.getString("feto") });
				}
			} catch (Exception e) {
				System.out.println("Notifikasi : " + e);
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (ps5 != null) {
					ps5.close();
				}
			}
		} catch (Exception e) {
			System.out.println("Notifikasi :" + e);
		}
		LCount.setText("" + tabModeObstetri.getRowCount());
	}

	private void tampilPemeriksaanVaksin() {
		// TODO Auto-generated method stub
		Valid.tabelKosong(tableModePemeriksaanVaksin);
		ResultSet rs = null;
		String query = "";
		try {
			if (Tkdvksin.getText().equals("")) {
				query = "Select *,nama_imunisasi,nama from pemeriksaan_vaksin Inner join master_imunisasi on master_imunisasi.kode_imunisasi=kd_vaksin "
						+ "Inner join petugas on petugas.nip=pemeriksaan_vaksin.nip where no_rawat='" + TNoRw.getText()
						+ "'";
			} else {
				query = "Select *,nama_imunisasi,nama from pemeriksaan_vaksin Inner join master_imunisasi on master_imunisasi.kode_imunisasi=kd_vaksin "
						+ "Inner join petugas on petugas.nip=pemeriksaan_vaksin.nip where kd_vaksin='"
						+ Tkdvksin.getText() + "'";
			}
			try {
				rs = koneksi.prepareStatement(query).executeQuery();
				while (rs.next()) {
					tableModePemeriksaanVaksin.addRow(new Object[] { false, rs.getString(1),
							rs.getString("nama_imunisasi"), rs.getString(5), rs.getString(2), rs.getString(3),
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
							rs.getString(11), rs.getString(12), rs.getString(15), rs.getString(16), rs.getString(14),
							rs.getString(13), rs.getString("nama") });
				}
			} catch (Exception e) {
				System.out.println("Notifikasi : " + e);
			} finally {
				if (rs != null) {
					rs.close();
				}
			}

		} catch (Exception e) {
			System.out.println("Notifikasi : " + e);
		}
		LCount.setText("" + tableModePemeriksaanVaksin.getRowCount());
	}

	private void tampilPemeriksaan() {
		// TODO Auto-generated method stub
		Valid.tabelKosong(tabModePemeriksaan);
		ResultSet rs = null;
		try {
			ps4 = koneksi
					.prepareStatement("select pemeriksaan_ralan.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
							+ "pemeriksaan_ralan.tgl_perawatan,pemeriksaan_ralan.jam_rawat,pemeriksaan_ralan.suhu_tubuh,pemeriksaan_ralan.tensi, "
							+ "pemeriksaan_ralan.nadi,pemeriksaan_ralan.respirasi,pemeriksaan_ralan.tinggi, "
							+ "pemeriksaan_ralan.berat,pemeriksaan_ralan.gcs,pemeriksaan_ralan.kesadaran,pemeriksaan_ralan.keluhan, "
							+ "pemeriksaan_ralan.pemeriksaan,pemeriksaan_ralan.alergi,pemeriksaan_ralan.imun_ke,pemeriksaan_ralan.rtl,"
							+ "pemeriksaan_ralan.penilaian,pemeriksaan_ralan.instruksi,pemeriksaan_ralan.nip,pegawai.nama,pegawai.jbtn "
							+ "from pasien inner join reg_periksa on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
							+ "inner join pemeriksaan_ralan on pemeriksaan_ralan.no_rawat=reg_periksa.no_rawat "
							+ "inner join pegawai on pemeriksaan_ralan.nip=pegawai.nik where  "
							+ "pemeriksaan_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and "
							+ "(pemeriksaan_ralan.no_rawat like ? or reg_periksa.no_rkm_medis like ? or pasien.nm_pasien like ? or "
							+ "pemeriksaan_ralan.alergi like ? or pemeriksaan_ralan.keluhan like ? or pemeriksaan_ralan.penilaian like ? or "
							+ "pemeriksaan_ralan.pemeriksaan like ? or pegawai.nama like ?) "
							+ "order by pemeriksaan_ralan.no_rawat desc");
			try {
				ps4.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
				ps4.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
				ps4.setString(3, "%" + TCariPasien.getText() + "%");
				ps4.setString(4, "%" + TCari.getText().trim() + "%");
				ps4.setString(5, "%" + TCari.getText().trim() + "%");
				ps4.setString(6, "%" + TCari.getText().trim() + "%");
				ps4.setString(7, "%" + TCari.getText().trim() + "%");
				ps4.setString(8, "%" + TCari.getText().trim() + "%");
				ps4.setString(9, "%" + TCari.getText().trim() + "%");
				ps4.setString(10, "%" + TCari.getText().trim() + "%");
				ps4.setString(11, "%" + TCari.getText().trim() + "%");
				rs = ps4.executeQuery();
				while (rs.next()) {
					tabModePemeriksaan.addRow(new Object[] { false, rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
							rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
							rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18),
							rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23) });
				}
			} catch (Exception e) {
				System.out.println("Notifikasi : " + e);
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (ps4 != null) {
					ps4.close();
				}
			}
		} catch (Exception e) {
			System.out.println("Notifikasi : " + e);
		}
		LCount.setText("" + tabModePemeriksaan.getRowCount());
	}

	private void tampilkanPenangananDokterPetugas() {
		// TODO Auto-generated method stub
		if (tabTindakanDokterPetugas.getSelectedIndex() == 0) {
			tampilTindakanDrPr();
		} else if (tabTindakanDokterPetugas.getSelectedIndex() == 1) {
			tampilDrPr();
		}
	}

	private void tampilTindakanDrPr() {
		// TODO Auto-generated method stub
		ResultSet rstindakan = null;
		try {
			jml = 0;
			for (i = 0; i < TabModeTindakan.getRowCount(); i++) {
				if (TabModeTindakan.getValueAt(i, 0).toString().equals("true")) {
					jml++;
				}
			}

			pilih = null;
			pilih = new boolean[jml];
			kode = null;
			kode = new String[jml];
			nama = null;
			nama = new String[jml];
			kategori = null;
			kategori = new String[jml];
			totaltnd = null;
			totaltnd = new double[jml];
			bagianrs = null;
			bagianrs = new double[jml];
			bhp = null;
			bhp = new double[jml];
			jmdokter = null;
			jmdokter = new double[jml];
			jmperawat = null;
			jmperawat = new double[jml];
			kso = null;
			kso = new double[jml];
			menejemen = null;
			menejemen = new double[jml];
			jnsBayar = null;
			jnsBayar = new String[jml];
			index = 0;
			for (i = 0; i < TabModeTindakan.getRowCount(); i++) {
				if (TabModeTindakan.getValueAt(i, 0).toString().equals("true")) {
					pilih[index] = true;
					kode[index] = TabModeTindakan.getValueAt(i, 1).toString();
					nama[index] = TabModeTindakan.getValueAt(i, 2).toString();
					kategori[index] = TabModeTindakan.getValueAt(i, 3).toString();
					totaltnd[index] = Double.parseDouble(TabModeTindakan.getValueAt(i, 4).toString());
					bagianrs[index] = Double.parseDouble(TabModeTindakan.getValueAt(i, 5).toString());
					bhp[index] = Double.parseDouble(TabModeTindakan.getValueAt(i, 6).toString());
					jmdokter[index] = Double.parseDouble(TabModeTindakan.getValueAt(i, 7).toString());
					jmperawat[index] = Double.parseDouble(TabModeTindakan.getValueAt(i, 8).toString());
					kso[index] = Double.parseDouble(TabModeTindakan.getValueAt(i, 9).toString());
					menejemen[index] = Double.parseDouble(TabModeTindakan.getValueAt(i, 10).toString());
					jnsBayar[index] = TabModeTindakan.getValueAt(i, 1).toString();
					index++;
				}
			}
			Valid.tabelKosong(TabModeTindakan);

			for (i = 0; i < jml; i++) {
				TabModeTindakan.addRow(new Object[] { pilih[i], kode[i], nama[i], kategori[i], totaltnd[i], bagianrs[i],
						bhp[i], jmdokter[i], jmperawat[i], kso[i], menejemen[i], jnsBayar[i] });
			}

			if (poli_ralan.equals("Yes") && cara_bayar_ralan.equals("Yes")) {
				pstindakan = koneksi.prepareStatement(
						"select jns_perawatan.kd_jenis_prw,jns_perawatan.nm_perawatan,kategori_perawatan.nm_kategori,"
								+ "jns_perawatan.total_byrdr,jns_perawatan.total_byrpr,jns_perawatan.total_byrdrpr,jns_perawatan.bhp,jns_perawatan.material,"
								+ "jns_perawatan.tarif_tindakandr,jns_perawatan.tarif_tindakanpr,jns_perawatan.kso,jns_perawatan.menejemen,png_jawab,nm_poli from jns_perawatan Inner join poliklinik on poliklinik.kd_poli=jns_perawatan.kd_poli inner join kategori_perawatan "
								+ "on jns_perawatan.kd_kategori=kategori_perawatan.kd_kategori Inner join penjab on penjab.kd_pj=jns_perawatan.kd_pj INNER join reg_periksa on reg_periksa.kd_pj=penjab.kd_pj and no_rawat='"
								+ TNoRw.getText() + "' "
								+ "where jns_perawatan.total_byrdrpr>0 and jns_perawatan.status='1' and (jns_perawatan.kd_pj=? or jns_perawatan.kd_pj='-') and (jns_perawatan.kd_poli=? or jns_perawatan.kd_poli='-') and jns_perawatan.kd_jenis_prw like ? or "
								+ "jns_perawatan.total_byrdrpr>0 and jns_perawatan.status='1' and (jns_perawatan.kd_pj=? or jns_perawatan.kd_pj='-') and (jns_perawatan.kd_poli=? or jns_perawatan.kd_poli='-') and jns_perawatan.nm_perawatan like ? or "
								+ "jns_perawatan.total_byrdrpr>0 and jns_perawatan.status='1' and (jns_perawatan.kd_pj=? or jns_perawatan.kd_pj='-') and (jns_perawatan.kd_poli=? or jns_perawatan.kd_poli='-') and kategori_perawatan.nm_kategori like ? order by jns_perawatan.nm_perawatan ");
			} else if (poli_ralan.equals("No") && cara_bayar_ralan.equals("Yes")) {
				pstindakan = koneksi.prepareStatement(
						"select jns_perawatan.kd_jenis_prw,jns_perawatan.nm_perawatan,kategori_perawatan.nm_kategori,"
								+ "jns_perawatan.total_byrdr,jns_perawatan.total_byrpr,jns_perawatan.total_byrdrpr,jns_perawatan.bhp,jns_perawatan.material,"
								+ "jns_perawatan.tarif_tindakandr,jns_perawatan.tarif_tindakanpr,jns_perawatan.kso,jns_perawatan.menejemen,png_jawab,nm_poli from jns_perawatan Inner join poliklinik on poliklinik.kd_poli=jns_perawatan.kd_poli inner join kategori_perawatan "
								+ "on jns_perawatan.kd_kategori=kategori_perawatan.kd_kategori Inner join penjab on penjab.kd_pj=jns_perawatan.kd_pj INNER join reg_periksa on reg_periksa.kd_pj=penjab.kd_pj and no_rawat='"
								+ TNoRw.getText() + "' "
								+ "where jns_perawatan.total_byrdrpr>0 and jns_perawatan.status='1' and (jns_perawatan.kd_pj=? or jns_perawatan.kd_pj='-') and jns_perawatan.kd_jenis_prw like ? or "
								+ "jns_perawatan.total_byrdrpr>0 and jns_perawatan.status='1' and (jns_perawatan.kd_pj=? or jns_perawatan.kd_pj='-') and jns_perawatan.nm_perawatan like ? or "
								+ "jns_perawatan.total_byrdrpr>0 and jns_perawatan.status='1' and (jns_perawatan.kd_pj=? or jns_perawatan.kd_pj='-') and kategori_perawatan.nm_kategori like ? order by jns_perawatan.nm_perawatan ");
			} else if (poli_ralan.equals("Yes") && cara_bayar_ralan.equals("No")) {
				pstindakan = koneksi.prepareStatement(
						"select jns_perawatan.kd_jenis_prw,jns_perawatan.nm_perawatan,nm_poli,kategori_perawatan.nm_kategori,"
								+ "jns_perawatan.total_byrdr,jns_perawatan.total_byrpr,jns_perawatan.total_byrdrpr,jns_perawatan.bhp,jns_perawatan.material,"
								+ "jns_perawatan.tarif_tindakandr,jns_perawatan.tarif_tindakanpr,jns_perawatan.kso,jns_perawatan.menejemen,png_jawab from jns_perawatan inner join kategori_perawatan "
								+ "on jns_perawatan.kd_kategori=kategori_perawatan.kd_kategori Inner join penjab on penjab.kd_pj=jns_perawatan.kd_pj  Inner join poliklinik on poliklinik.kd_poli=jns_perawatan.kd_poli INNER join reg_periksa on reg_periksa.kd_pj=penjab.kd_pj and no_rawat='"
								+ TNoRw.getText() + "' "
								+ "where jns_perawatan.total_byrdrpr>0 and jns_perawatan.status='1' and (jns_perawatan.kd_poli=? or jns_perawatan.kd_poli='-') and jns_perawatan.kd_jenis_prw like ? or "
								+ "jns_perawatan.total_byrdrpr>0 and jns_perawatan.status='1' and (jns_perawatan.kd_poli=? or jns_perawatan.kd_poli='-') and jns_perawatan.nm_perawatan like ? or "
								+ "jns_perawatan.total_byrdrpr>0 and jns_perawatan.status='1' and (jns_perawatan.kd_poli=? or jns_perawatan.kd_poli='-') and kategori_perawatan.nm_kategori like ? order by jns_perawatan.nm_perawatan ");
			} else if (poli_ralan.equals("No") && cara_bayar_ralan.equals("No")) {
				pstindakan = koneksi.prepareStatement(
						"select jns_perawatan.kd_jenis_prw,jns_perawatan.nm_perawatan,kategori_perawatan.nm_kategori,"
								+ "jns_perawatan.total_byrdr,jns_perawatan.total_byrpr,jns_perawatan.total_byrdrpr,jns_perawatan.bhp,jns_perawatan.material,"
								+ "jns_perawatan.tarif_tindakandr,jns_perawatan.tarif_tindakanpr,jns_perawatan.kso,jns_perawatan.menejemen,png_jawab,nm_poli from jns_perawatan Inner join poliklinik on poliklinik.kd_poli=jns_perawatan.kd_poli inner join kategori_perawatan "
								+ "on jns_perawatan.kd_kategori=kategori_perawatan.kd_kategori Inner join penjab on penjab.kd_pj=jns_perawatan.kd_pj INNER join reg_periksa on reg_periksa.kd_pj=penjab.kd_pj and no_rawat='"
								+ TNoRw.getText() + "' "
								+ "where jns_perawatan.total_byrdrpr>0 and jns_perawatan.status='1' and (jns_perawatan.kd_jenis_prw like ? or "
								+ "jns_perawatan.total_byrdrpr>0) and jns_perawatan.status='1' and (jns_perawatan.nm_perawatan like ? or "
								+ "jns_perawatan.total_byrdrpr>0) and jns_perawatan.status='1' and kategori_perawatan.nm_kategori like ? and (jns_perawatan.kd_poli=? or jns_perawatan.kd_poli='-') order by jns_perawatan.nm_perawatan ");
			}

			try {
				if (poli_ralan.equals("Yes") && cara_bayar_ralan.equals("Yes")) {
					pstindakan.setString(1, kd_pj.trim());
					pstindakan.setString(2, kode_poli.trim());
					pstindakan.setString(3, "%" + TCari.getText().trim() + "%");
					pstindakan.setString(4, kd_pj.trim());
					pstindakan.setString(5, kode_poli.trim());
					pstindakan.setString(6, "%" + TCari.getText().trim() + "%");
					pstindakan.setString(7, kd_pj.trim());
					pstindakan.setString(8, kode_poli.trim());
					pstindakan.setString(9, "%" + TCari.getText().trim() + "%");
					rstindakan = pstindakan.executeQuery();
				} else if (poli_ralan.equals("No") && cara_bayar_ralan.equals("Yes")) {
					pstindakan.setString(1, kd_pj.trim());
					pstindakan.setString(2, "%" + TCari.getText().trim() + "%");
					pstindakan.setString(3, kd_pj.trim());
					pstindakan.setString(4, "%" + TCari.getText().trim() + "%");
					pstindakan.setString(5, kd_pj.trim());
					pstindakan.setString(6, "%" + TCari.getText().trim() + "%");
					rstindakan = pstindakan.executeQuery();
				} else if (poli_ralan.equals("Yes") && cara_bayar_ralan.equals("No")) {
					pstindakan.setString(1, kode_poli.trim());
					pstindakan.setString(2, "%" + TCari.getText().trim() + "%");
					pstindakan.setString(3, kode_poli.trim());
					pstindakan.setString(4, "%" + TCari.getText().trim() + "%");
					pstindakan.setString(5, kode_poli.trim());
					pstindakan.setString(6, "%" + TCari.getText().trim() + "%");
					rstindakan = pstindakan.executeQuery();
				} else if (poli_ralan.equals("No") && cara_bayar_ralan.equals("No")) {
					pstindakan.setString(1, "%" + TCari.getText().trim() + "%");
					pstindakan.setString(2, "%" + TCari.getText().trim() + "%");
					pstindakan.setString(3, "%" + TCari.getText().trim() + "%");
					pstindakan.setString(4, kode_poli.trim());
					rstindakan = pstindakan.executeQuery();
				}
//				System.out.println(poli_ralan+"-"+cara_bayar_ralan);
				while (rstindakan.next()) {
					TabModeTindakan.addRow(new Object[] { false, rstindakan.getString(1), rstindakan.getString(2),
							rstindakan.getString("nm_poli"), rstindakan.getDouble("total_byrdrpr"),
							rstindakan.getDouble("material"), rstindakan.getDouble("bhp"),
							rstindakan.getDouble("tarif_tindakandr"), rstindakan.getDouble("tarif_tindakanpr"),
							rstindakan.getDouble("kso"), rstindakan.getDouble("menejemen"),
							rstindakan.getString("png_jawab") });
				}
			} catch (Exception e) {
				System.out.println("Notifikasi : " + e);
			} finally {
				if (rstindakan != null) {
					rstindakan.close();
				}
				if (pstindakan != null) {
					pstindakan.close();
				}
			}
		} catch (Exception e) {
			System.out.println("Notifikasi : " + e);
		}
		LCount.setText("" + TabModeTindakan.getRowCount());
	}

	private void tampilDrPr() {
		// TODO Auto-generated method stub
		Valid.tabelKosong(tabModeDrPr);
		ResultSet rs = null;
		try {
			ps3 = koneksi.prepareStatement("Select tindakan_rajal.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,concat(tindakan_rajal.kd_jenis_prw,' ',jns_perawatan.nm_perawatan),\r\n"
					+ " tindakan_rajal.kd_dokter,dokter.nm_dokter,tindakan_rajal.nip,petugas.nama,tindakan_rajal.tgl_perawatan,tindakan_rajal.jam_rawat,tindakan_rajal.biaya_rawat,tindakan_rajal.kd_jenis_prw, tindakan_rajal.tarif_tindakandr,\r\n"
					+ " tindakan_rajal.tarif_tindakanpr,tindakan_rajal.kso,tindakan_rajal.material,tindakan_rajal.bhp,tindakan_rajal.menejemen\r\n"
					+ " from tindakan_rajal\r\n"
					+ " Inner join jns_perawatan on jns_perawatan.kd_jenis_prw=tindakan_rajal.kd_jenis_prw\r\n"
					+ " Inner Join dokter on dokter.kd_dokter=tindakan_rajal.kd_dokter\r\n"
					+ " Inner Join reg_periksa on reg_periksa.no_rawat=tindakan_rajal.no_rawat\r\n"
					+ " Inner Join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis\r\n"
					+ " left join petugas on tindakan_rajal.nip=petugas.nip\r\n"
					+ " Where tindakan_rajal.no_rawat='"+TNoRw.getText()+"' order by kd_jenis_prw DESC");
			try {
				
//				 System.out.print(ps3);
				rs = ps3.executeQuery();
				while (rs.next()) {
					tabModeDrPr.addRow(new Object[] { false, rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
							rs.getString(9), rs.getString(10), rs.getDouble(11), rs.getString("kd_jenis_prw"),
							rs.getString("tarif_tindakandr"), rs.getString("tarif_tindakanpr"), rs.getString("kso"),
							rs.getString("material"), rs.getString("bhp"), rs.getString("menejemen") });
				}
			} catch (Exception e) {
				System.out.println("Notifikasi : " + e);
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (ps3 != null) {
					ps3.close();
				}
			}
		} catch (Exception e) {
			System.out.println("Notifikasi : " + e);
		}
		LCount.setText("" + tabModeDrPr.getRowCount());
	}

	protected void TCariKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			TampilkanData();
		} else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
			BtnCari.requestFocus();
		} else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
			BtnKeluar.requestFocus();
		} else if (evt.getKeyCode() == KeyEvent.VK_UP) {
			switch (TabMain.getSelectedIndex()) {
			case 0:
				if (tabTindakanDokterPetugas.getSelectedIndex() == 0) {
					tbTindakan.requestFocus();
				} else if (tabTindakanDokterPetugas.getSelectedIndex() == 1) {
					tabTindakanDokterPetugas.requestFocus();
				}
				break;
			case 1:
				tbPemeriksaan.requestFocus();
				break;
			case 2:

				break;
			case 3:
				tbPemeriksaanObstetri.requestFocus();
				break;
			case 4:
				tbPemeriksaanGinekologi.requestFocus();
				break;
			default:
				break;
			}
		}
	}

	protected void btnPasienKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, TCariPasien, DTPCari1);
	}

	protected void btnPasienActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		akses.setform("DlgRawatJalan");
		pasien.emptTeks();
		pasien.isCek();
		pasien.setSize(internalFrameMain.getWidth() - 20, internalFrameMain.getHeight() - 20);
		pasien.setLocationRelativeTo(internalFrameMain);
		pasien.setVisible(rootPaneCheckingEnabled);
	}

	protected void BtnCatatanActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TNoRw.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
			TCari.requestFocus();
		} else {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			DlgCatatan catatan = new DlgCatatan(null, true);
			catatan.setNoRm(TNoRM.getText());
			catatan.setSize(720, 330);
			catatan.setLocationRelativeTo(internalFrameMain);
			catatan.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
		}
	}

	protected void BtnTriaseIGDActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TPasien.getText().trim().equals("") || TNoRw.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
			TCari.requestFocus();
		} else {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			RMTriaseIGD form = new RMTriaseIGD(null, false);
			form.isCek();
			form.setNoRm(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
			form.setSize(internalFrameMain.getWidth() - 20, internalFrameMain.getHeight() - 20);
			form.setLocationRelativeTo(internalFrameMain);
			form.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
		}
	}

	protected void BtnRujukKeluarActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TNoRw.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
			TCari.requestFocus();
		} else {
			if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
				JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
			} else {
				if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
					JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi ..!!");
				} else {
					this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					DlgRujuk dlgrjk = new DlgRujuk(null, false);
					dlgrjk.setSize(internalFrameMain.getWidth(), internalFrameMain.getHeight());
					dlgrjk.setLocationRelativeTo(internalFrameMain);
					dlgrjk.emptTeks();
					dlgrjk.isCek();
					dlgrjk.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
					dlgrjk.tampil();
					dlgrjk.setVisible(true);
					this.setCursor(Cursor.getDefaultCursor());
				}
			}
		}
	}

	protected void BtnKamarActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TNoRw.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
			TCari.requestFocus();
		} else {
			if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
				JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
			} else {
				if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
					JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi ..!!");
				} else {
					inputKamar();
				}
			}
		}
	}

	private void inputKamar() {
		// TODO Auto-generated method stub
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		akses.setstatus(true);
		DlgKamarInap dlgki = new DlgKamarInap(null, false);
		dlgki.setSize(internalFrameMain.getWidth(), internalFrameMain.getHeight());
		dlgki.setLocationRelativeTo(internalFrameMain);
		dlgki.emptTeks();
		dlgki.isCek();
		dlgki.setNoRm(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
		dlgki.setVisible(true);
		this.setCursor(Cursor.getDefaultCursor());
	}

	protected void BtnRujukInternalActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TNoRw.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
			TCari.requestFocus();
		} else {
			if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
				JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
			} else {
				if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
					JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi ..!!");
				} else {
					this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					DlgRujukanPoliInternal dlgrjk = new DlgRujukanPoliInternal(null, false);
					dlgrjk.setLocationRelativeTo(internalFrameMain);
					dlgrjk.isCek();
					dlgrjk.setNoRm(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), this.getWidth() + 20,
							this.getHeight() + 20);
					dlgrjk.setVisible(true);
					this.setCursor(Cursor.getDefaultCursor());
				}
			}
		}
	}

	protected void BtnSKDPActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TNoRw.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
			TCari.requestFocus();
		} else {
			if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
				JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
			} else {
				SuratKontrol form = new SuratKontrol(null, false);
				form.isCek();
				form.setSize(internalFrameMain.getWidth(), internalFrameMain.getHeight());
				form.setLocationRelativeTo(internalFrameMain);
				form.emptTeks();
				form.setNoRm(TNoRM.getText(), TPasien.getText(), kode_poli,
						Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", kode_poli), KdDok.getText(),
						TDokter.getText());
				form.setVisible(true);
			}
		}
	}

	protected void BtnPermintaanRadKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub

	}

	protected void BtnPermintaanRadActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TPasien.getText().trim().equals("") || TNoRw.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
			TCari.requestFocus();
		} else {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			DlgPermintaanRadiologi dlgro = new DlgPermintaanRadiologi(null, false);
			dlgro.setSize(internalFrameMain.getWidth(), internalFrameMain.getHeight());
			dlgro.setLocationRelativeTo(internalFrameMain);
			dlgro.emptTeks();
			dlgro.isCek();
			dlgro.setNoRm(TNoRw.getText(), "Ralan");
			dlgro.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
		}
	}

	protected void BtnPermintaanLabKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub

	}

	protected void BtnPermintaanLabActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TPasien.getText().trim().equals("") || TNoRw.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
			TCari.requestFocus();
		} else {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			DlgPermintaanLaboratorium dlgro = new DlgPermintaanLaboratorium(null, false);
			dlgro.setSize(internalFrameMain.getWidth(), internalFrameMain.getHeight());
			dlgro.setLocationRelativeTo(internalFrameMain);
			dlgro.emptTeks();
			dlgro.isCek();
			dlgro.setNoRm(TNoRw.getText(), "Ralan");
			dlgro.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
		}
	}

	protected void BtnBerkasDigitalKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub

	}

	protected void BtnBerkasDigitalActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TNoRw.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
			TCari.requestFocus();
		} else {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			DlgBerkasRawat berkas = new DlgBerkasRawat(null, true);
			berkas.setJudul("::[ Berkas Digital Perawatan ]::", "berkasrawat/pages");
			try {
				berkas.loadURL("http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + prop.getProperty("PORTWEB") + "/"
						+ prop.getProperty("HYBRIDWEB") + "/"
						+ "berkasrawat/login2.php?act=login&usere=admin&passwordte=akusayangsamakamu&no_rawat="
						+ TNoRw.getText());
			} catch (Exception ex) {
				System.out.println("Notifikasi : " + ex);
			}

			berkas.setSize(internalFrameMain.getWidth(), internalFrameMain.getHeight());
			berkas.setLocationRelativeTo(internalFrameMain);
			berkas.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
		}
	}

	protected void BtnObatBhpKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub

	}

	protected void BtnObatBhpActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TNoRw.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
			TCari.requestFocus();
		} else {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			DlgPemberianObat dlgrwinap = new DlgPemberianObat(null, false);
			dlgrwinap.setSize(internalFrameMain.getWidth(), internalFrameMain.getHeight());
			dlgrwinap.setLocationRelativeTo(internalFrameMain);
			dlgrwinap.isCek();
			dlgrwinap.setNoRm2(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate(), "ralan");
			dlgrwinap.tampilPO();
			dlgrwinap.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
		}
	}

	protected void BtnCopyResepKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub

	}

	protected void BtnCopyResepActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TNoRw.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
			TCari.requestFocus();
		} else {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			DlgCopyResep daftar = new DlgCopyResep(null, false);
			daftar.isCek();
			daftar.setRM(TNoRw.getText(), TNoRM.getText(), KdDok.getText(), kd_pj, "ralan");
			daftar.tampil();
			daftar.setSize(internalFrameMain.getWidth(), internalFrameMain.getHeight());
			daftar.setLocationRelativeTo(internalFrameMain);
			daftar.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
		}
	}

	protected void BtnInputObatKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub

	}

	protected void BtnInputObatActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TNoRw.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
			TCari.requestFocus();
		} else {
			if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
				JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
			} else {
				jmlparsial = 0;
				if (aktifkanparsial.equals("yes")) {
					jmlparsial = Sequel.cariInteger("select count(kd_pj) from set_input_parsial where kd_pj=?", kd_pj);
				}
				if (jmlparsial > 0) {
					inputObat();
				} else {
					if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
						JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi ..!!");
					} else {
						inputObat();
					}
				}
			}
		}
	}

	private void inputObat() {
		// TODO Auto-generated method stub
		DlgCariObat dlgobt = new DlgCariObat(null, false);
		dlgobt.setNoRm(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""),
				cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
		dlgobt.isCek();
		dlgobt.setDokter(KdDok.getText(), TDokter.getText());
		dlgobt.tampilobat();
		dlgobt.setSize(internalFrameMain.getWidth(), internalFrameMain.getHeight());
		dlgobt.setLocationRelativeTo(internalFrameMain);
		dlgobt.setVisible(true);
	}

	protected void BtnResepObatKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub

	}

	protected void BtnResepObatActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TNoRw.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
			TCari.requestFocus();
		} else {
			if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
				JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
			} else {
				jmlparsial = 0;
				if (aktifkanparsial.equals("yes")) {
					jmlparsial = Sequel.cariInteger("select count(kd_pj) from set_input_parsial where kd_pj=?", kd_pj);
				}
				if (jmlparsial > 0) {
					inputResep();
				} else {
					if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
						JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi ..!!");
					} else {
						inputResep();
					}
				}
			}
		}
	}

	private void inputResep() {
		// TODO Auto-generated method stub
		DlgPeresepanDokter resep = new DlgPeresepanDokter(null, false);
		resep.setSize(internalFrameMain.getWidth(), internalFrameMain.getHeight());
		resep.setLocationRelativeTo(internalFrameMain);
		resep.setNoRm(TNoRw.getText(), DTPTgl.getDate(), cmbJam.getSelectedItem().toString(),
				cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), KdDok.getText(),
				TDokter.getText(), "ralan");
		resep.isCek();
		resep.tampilobat();
		resep.setVisible(true);
	}

	protected void ChkAccorActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		isMenu();
	}

	private void isMenu() {
		// TODO Auto-generated method stub
		if (ChkAccor.isSelected() == true) {
			ChkAccor.setVisible(false);
			PanelAccor.setPreferredSize(new Dimension(190, HEIGHT));
			FormMenu.setVisible(true);
			ChkAccor.setVisible(true);
		} else if (ChkAccor.isSelected() == false) {
			ChkAccor.setVisible(false);
			PanelAccor.setPreferredSize(new Dimension(15, HEIGHT));
			FormMenu.setVisible(false);
			ChkAccor.setVisible(true);
		}
	}

	protected void BtnResumeActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TNoRw.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
			TCari.requestFocus();
		} else {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			RMDataResumePasien resume = new RMDataResumePasien(null, false);
			resume.isCek();
			resume.setSize(internalFrameMain.getWidth() - 20, internalFrameMain.getHeight() - 20);
			resume.setLocationRelativeTo(internalFrameMain);
			resume.setNoRm(TNoRw.getText(), DTPCari2.getDate());
			resume.tampil();
			resume.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
		}
	}

	protected void ChkJlnActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	protected void cmbMntKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbJam, cmbDtk);
	}

	protected void cmbDtkKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, cmbMnt, TCari);
	}

	protected void cmbJamKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, DTPTgl, cmbMnt);
	}

	protected void DTPTglKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		Valid.pindah(evt, BtnSeekDokter, cmbJam);
	}

	protected void TNoRwKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
			isRawat();
			isPsien();
			kd_pj = Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", TNoRw.getText());
			kode_poli = Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat=?", TNoRw.getText());
		} else {
			if (TabMain.getSelectedIndex() == 0) {
				Valid.pindah(evt, DTPTgl, KdDok);
			} else if (TabMain.getSelectedIndex() == 3) {
				Valid.pindah(evt, DTPTgl, KdPeg);
			} else if (TabMain.getSelectedIndex() == 5) {
				Valid.pindah(evt, DTPTgl, TTinggi_uteri);
			} else if (TabMain.getSelectedIndex() == 6) {
				Valid.pindah(evt, DTPTgl, TInspeksi);
			} else if (TabMain.getSelectedIndex() == 9) {
				Valid.pindah(evt, DTPTgl, KdDok3);
			}
		}
	}

	private void isPsien() {
		// TODO Auto-generated method stub
		Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());
	}

	private void isRawat() {
		// TODO Auto-generated method stub
		Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, TNoRw.getText());
		TCariPasien.setText(TNoRM.getText());
	}

	protected void tbRawatDrPrKeyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (tabModeDrPr.getRowCount() != 0) {
			if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP)
					|| (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
				try {
					getDataDrPr();
				} catch (java.lang.NullPointerException e) {
				}
			}
		}
	}

	private void getDataDrPr() {
		// TODO Auto-generated method stub
		if (tbRawatDrPr.getSelectedRow() != -1) {
			TNoRw.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 1).toString());
			TNoRM.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 2).toString());
			TPasien.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 3).toString());
			KdDok.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 5).toString());
			TDokter.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 6).toString());
			kdpetugas.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 7).toString());
			TPerawat.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 8).toString());
			cmbJam.setSelectedItem(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 10).toString().substring(0, 2));
			cmbMnt.setSelectedItem(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 10).toString().substring(3, 5));
			cmbDtk.setSelectedItem(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 10).toString().substring(6, 8));
			Valid.SetTgl(DTPTgl, tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 9).toString());
		}
	}

	protected void tbRawatDrPrMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		if (tabModeDrPr.getRowCount() != 0) {
			try {
				getDataDrPr();
			} catch (java.lang.NullPointerException e) {
			}
		}
	}

	protected void tbTindakanKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (tbTindakan.getRowCount() != 0) {
			if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
				try {
					i = tbTindakan.getSelectedColumn();
					if (i == 1) {
						if (tbTindakan.getSelectedRow() > -1) {
							tbTindakan.setValueAt(true, tbTindakan.getSelectedRow(), 0);
						}
						TCari.setText("");
						TCari.requestFocus();
					}
				} catch (java.lang.NullPointerException e) {
				}
			} else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
				TCari.setText("");
				TCari.requestFocus();
			}
		}
	}

	protected void tabTindakanDokterPetugasMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		if (tabTindakanDokterPetugas.getSelectedIndex() == 0) {
			TCari.setText("");
		} else if (tabTindakanDokterPetugas.getSelectedIndex() == 1) {
			TCari.setText("");
			TCariPasien.setText(TNoRM.getText());
		}
		TCari.requestFocus();
		tampilkanPenangananDokterPetugas();
	}

	protected void kdpetugasKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
			Sequel.cariIsi("select nama from petugas where nip=?", TPerawat, kdpetugas.getText());
		} else if (evt.getKeyCode() == KeyEvent.VK_UP) {
			BtnSeekPetugasActionPerformed(null);
		} else {
			Valid.pindah(evt, TNoRw, BtnSeekPetugas);
		}
	}

	protected void BtnSeekPetugasActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		akses.setform("DlgRawatJalan");
		petugas.emptTeks();
		petugas.isCek();
		petugas.setSize(internalFrameMain.getWidth() - 20, internalFrameMain.getHeight() - 20);
		petugas.setLocationRelativeTo(internalFrameMain);
		petugas.setVisible(true);
	}

	protected void TDokterKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
			Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter, KdDok.getText());
		} else if (evt.getKeyCode() == KeyEvent.VK_UP) {
			BtnSeekDokterActionPerformed(null);
		} else {
			Valid.pindah(evt, TNoRw, BtnSeekDokter);
		}
	}

	protected void BtnSeekDokterActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		akses.setform("DlgRawatJalan");
		dokter.emptTeks();
		dokter.isCek();
		dokter.setSize(internalFrameMain.getWidth() - 20, internalFrameMain.getHeight() - 20);
		dokter.setLocationRelativeTo(internalFrameMain);
		dokter.setVisible(true);
	}

	protected void TabMainMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		switch (TabMain.getSelectedIndex()) {
		case 0:
			BtnSimpan.setEnabled(akses.gettindakan_ralan());
			BtnHapus.setEnabled(akses.gettindakan_ralan());
			BtnEdit.setEnabled(akses.gettindakan_ralan());
			BtnPrint.setEnabled(akses.gettindakan_ralan());
			BtnTambahTindakan.setVisible(true);
			TCari.setPreferredSize(new Dimension(207, 23));
			tabTindakanDokterPetugasMouseClicked(null);
			break;
		case 1:
			BtnSimpan.setEnabled(akses.gettindakan_ralan());
			BtnHapus.setEnabled(akses.gettindakan_ralan());
			BtnEdit.setEnabled(akses.gettindakan_ralan());
			BtnPrint.setEnabled(akses.gettindakan_ralan());
			BtnTambahTindakan.setVisible(false);
			TCari.setPreferredSize(new Dimension(240, 23));
			TCariPasien.setText(TNoRM.getText());
			tampilPemeriksaan();
			break;
		case 2:

			break;
		case 3:
			BtnSimpan.setEnabled(akses.gettindakan_ralan());
			BtnHapus.setEnabled(akses.gettindakan_ralan());
			BtnEdit.setEnabled(akses.gettindakan_ralan());
			BtnPrint.setEnabled(akses.gettindakan_ralan());
			BtnTambahTindakan.setVisible(false);
			TCari.setPreferredSize(new Dimension(240, 23));
			TCariPasien.setText(TNoRM.getText());
			tampilPemeriksaanObstetri();
			break;
		case 4:
			BtnSimpan.setEnabled(akses.gettindakan_ralan());
			BtnHapus.setEnabled(akses.gettindakan_ralan());
			BtnEdit.setEnabled(akses.gettindakan_ralan());
			BtnPrint.setEnabled(akses.gettindakan_ralan());
			BtnTambahTindakan.setVisible(false);
			TCari.setPreferredSize(new Dimension(240, 23));
			TCariPasien.setText(TNoRM.getText());
			tampilPemeriksaanGinekologi();
			break;
		case 5:
			BtnTambahTindakan.setVisible(false);
			TCari.setPreferredSize(new Dimension(240, 23));
			TCariPasien.setText(TNoRM.getText());
			if (akses.getresume_pasien() == true) {
				panelResume1.setRM(TNoRM.getText(), Valid.SetTgl(DTPCari1.getSelectedItem() + ""),
						Valid.SetTgl(DTPCari2.getSelectedItem() + ""), false);
				panelResume1.pilihTab();
			}
			LCount.setText("0");
			break;
		case 6:
			BtnSimpan.setEnabled(akses.getdiagnosa_pasien());
			BtnHapus.setEnabled(akses.getdiagnosa_pasien());
			BtnEdit.setEnabled(akses.getdiagnosa_pasien());
			BtnPrint.setEnabled(akses.getdiagnosa_pasien());
			BtnTambahTindakan.setVisible(false);
			TCari.setPreferredSize(new Dimension(240, 23));
			TCariPasien.setText(TNoRM.getText());
			if (akses.getdiagnosa_pasien() == true) {
				panelDiagnosa1.setRM(TNoRw.getText(), TNoRM.getText(), Valid.SetTgl(DTPCari1.getSelectedItem() + ""),
						Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "Ralan", TCari.getText().trim());
				panelDiagnosa1.pilihTab();
				LCount.setText(panelDiagnosa1.getRecord() + "");
			}
			break;
		case 7:
			BtnSimpan.setEnabled(akses.getcatatan_perawatan());
			BtnHapus.setEnabled(akses.getcatatan_perawatan());
			BtnEdit.setEnabled(akses.getcatatan_perawatan());
			BtnPrint.setEnabled(akses.getcatatan_perawatan());
			BtnTambahTindakan.setVisible(false);
			TCari.setPreferredSize(new Dimension(240, 23));
			TCariPasien.setText(TNoRM.getText());
			if (akses.getcatatan_perawatan() == true) {
				tampilCatatan();
			}
			break;
		default:
			break;
		}
	}

	protected void BtnAllKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
			BtnAllActionPerformed(null);
		} else {
			Valid.pindah(evt, BtnPrint, BtnKeluar);
		}
	}

	protected void BtnAllActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		TCari.setText("");
		TCariPasien.setText("");
		TampilkanData();
	}

	protected void BtnPrintKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
			BtnPrintActionPerformed(null);
		} else {
			Valid.pindah(evt, BtnHapus, BtnAll);
		}
	}

	protected void BtnPrintActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if (!TCari.getText().trim().equals("")) {
			BtnCariActionPerformed(evt);
		}
		switch (TabMain.getSelectedIndex()) {
		case 0:
			if (tabModeDrPr.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null,
						"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
				BtnBatal.requestFocus();
			} else if (tabModeDrPr.getRowCount() != 0) {
				Map<String, Object> param = new HashMap<>();
				param.put("namars", akses.getnamars());
				param.put("alamatrs", akses.getalamatrs());
				param.put("kotars", akses.getkabupatenrs());
				param.put("propinsirs", akses.getpropinsirs());
				param.put("kontakrs", akses.getkontakrs());
				param.put("emailrs", akses.getemailrs());
				param.put("logo", Sequel.cariGambar("select logo from setting"));
				String pas = " and reg_periksa.no_rkm_medis like '%" + TCariPasien.getText() + "%' ";

				String tgl = " tindakan_rajal.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "")
						+ "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " + pas;
				Valid.MyReportqry("rptJalanDrPr.jasper", "report", "::[ Data Rawat Jalan Yang Ditangani Dokter ]::",
						"select tindakan_rajal.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
								+ "jns_perawatan.nm_perawatan,tindakan_rajal.kd_dokter,dokter.nm_dokter,tindakan_rajal.nip,petugas.nama,"
								+ "tindakan_rajal.tgl_perawatan,tindakan_rajal.jam_rawat,tindakan_rajal.biaya_rawat "
								+ "from pasien inner join reg_periksa inner join jns_perawatan inner join "
								+ "dokter inner join tindakan_rajal inner join "
								+ "petugas on tindakan_rajal.no_rawat=reg_periksa.no_rawat "
								+ "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
								+ "and tindakan_rajal.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
								+ "and tindakan_rajal.kd_dokter=dokter.kd_dokter "
								+ "and tindakan_rajal.nip=petugas.nip " + "where " + tgl
								+ " and tindakan_rajal.no_rawat like '%" + TCari.getText().trim() + "%' or " + tgl
								+ "and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or " + tgl
								+ "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or " + tgl
								+ "and jns_perawatan.nm_perawatan like '%" + TCari.getText().trim() + "%' or " + tgl
								+ "and tindakan_rajal.kd_dokter like '%" + TCari.getText().trim() + "%' or " + tgl
								+ "and dokter.nm_dokter like '%" + TCari.getText().trim() + "%' or " + tgl
								+ "and tindakan_rajal.nip like '%" + TCari.getText().trim() + "%' or " + tgl
								+ "and petugas.nama like '%" + TCari.getText().trim() + "%' or " + tgl
								+ "and tgl_perawatan like '%" + TCari.getText().trim() + "%' "
								+ " order by tindakan_rajal.no_rawat desc",
						param);
			}
			break;
		case 1:
			if (tabModePemeriksaan.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null,
						"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
				BtnBatal.requestFocus();
			} else if (tabModePemeriksaan.getRowCount() != 0) {
				Map<String, Object> param = new HashMap<>();
				param.put("namars", akses.getnamars());
				param.put("alamatrs", akses.getalamatrs());
				param.put("kotars", akses.getkabupatenrs());
				param.put("propinsirs", akses.getpropinsirs());
				param.put("kontakrs", akses.getkontakrs());
				param.put("emailrs", akses.getemailrs());
				param.put("logo", Sequel.cariGambar("select logo from setting"));
				String pas = " and reg_periksa.no_rkm_medis like '%" + TCariPasien.getText() + "%' ";

				String tgl = " pemeriksaan_ralan.tgl_perawatan between '"
						+ Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '"
						+ Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " + pas;
				Valid.MyReportqry("rptJalanPemeriksaan.jasper", "report", "::[ Data Pemeriksaan Rawat Jalan ]::",
						"select pemeriksaan_ralan.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
								+ "pemeriksaan_ralan.tgl_perawatan,pemeriksaan_ralan.jam_rawat,pemeriksaan_ralan.suhu_tubuh,pemeriksaan_ralan.tensi, "
								+ "pemeriksaan_ralan.nadi,pemeriksaan_ralan.respirasi,pemeriksaan_ralan.tinggi, "
								+ "pemeriksaan_ralan.berat,pemeriksaan_ralan.gcs,pemeriksaan_ralan.kesadaran,pemeriksaan_ralan.keluhan, "
								+ "pemeriksaan_ralan.pemeriksaan,pemeriksaan_ralan.alergi,pemeriksaan_ralan.imun_ke,"
								+ "pemeriksaan_ralan.rtl,pemeriksaan_ralan.penilaian,pemeriksaan_ralan.instruksi,pemeriksaan_ralan.nip,pegawai.nama "
								+ "from pasien inner join reg_periksa on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
								+ "inner join pemeriksaan_ralan on pemeriksaan_ralan.no_rawat=reg_periksa.no_rawat "
								+ "inner join pegawai on pemeriksaan_ralan.nip=pegawai.nik where  " + tgl
								+ "and (pemeriksaan_ralan.no_rawat like '%" + TCari.getText().trim()
								+ "%' or reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
								+ "pasien.nm_pasien like '%" + TCari.getText().trim()
								+ "%' or pemeriksaan_ralan.alergi like '%" + TCari.getText().trim() + "%' or "
								+ "pemeriksaan_ralan.keluhan like '%" + TCari.getText().trim()
								+ "%' or pemeriksaan_ralan.penilaian like '%" + TCari.getText().trim() + "%' or "
								+ "pemeriksaan_ralan.pemeriksaan like '%" + TCari.getText().trim()
								+ "%' or pegawai.nama like '%" + TCari.getText().trim() + "%') "
								+ "order by pemeriksaan_ralan.no_rawat desc",
						param);
			}
			break;
		case 2:

			break;
		case 3:
			if (tabModeObstetri.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null,
						"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
				BtnBatal.requestFocus();
			} else if (tabModeObstetri.getRowCount() != 0) {
				Map<String, Object> param = new HashMap<>();
				param.put("namars", akses.getnamars());
				param.put("alamatrs", akses.getalamatrs());
				param.put("kotars", akses.getkabupatenrs());
				param.put("propinsirs", akses.getpropinsirs());
				param.put("kontakrs", akses.getkontakrs());
				param.put("emailrs", akses.getemailrs());
				param.put("logo", Sequel.cariGambar("select logo from setting"));
				String pas = " and reg_periksa.no_rkm_medis like '%" + TCariPasien.getText() + "%' ";

				String tgl = " pemeriksaan_obstetri_ralan.tgl_perawatan between '"
						+ Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '"
						+ Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " + pas;
				Valid.MyReportqry("rptJalanObstetri.jasper", "report", "::[ Data Pemeriksaan Obstetri Rawat Jalan ]::",
						"select pemeriksaan_obstetri_ralan.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
								+ "pemeriksaan_obstetri_ralan.tgl_perawatan,pemeriksaan_obstetri_ralan.jam_rawat,pemeriksaan_obstetri_ralan.tinggi_uteri,pemeriksaan_obstetri_ralan.janin,pemeriksaan_obstetri_ralan.letak, "
								+ "pemeriksaan_obstetri_ralan.panggul,pemeriksaan_obstetri_ralan.denyut,pemeriksaan_obstetri_ralan.kontraksi, "
								+ "pemeriksaan_obstetri_ralan.kualitas_mnt,pemeriksaan_obstetri_ralan.kualitas_dtk,pemeriksaan_obstetri_ralan.fluksus,pemeriksaan_obstetri_ralan.albus, "
								+ "pemeriksaan_obstetri_ralan.vulva,pemeriksaan_obstetri_ralan.portio,pemeriksaan_obstetri_ralan.dalam, pemeriksaan_obstetri_ralan.tebal, pemeriksaan_obstetri_ralan.arah, pemeriksaan_obstetri_ralan.pembukaan,"
								+ "pemeriksaan_obstetri_ralan.penurunan, pemeriksaan_obstetri_ralan.denominator, pemeriksaan_obstetri_ralan.ketuban, pemeriksaan_obstetri_ralan.feto "
								+ "from pasien inner join reg_periksa inner join pemeriksaan_obstetri_ralan "
								+ "on pemeriksaan_obstetri_ralan.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where  "
								+ tgl + "and pemeriksaan_obstetri_ralan.no_rawat like '%" + TCari.getText().trim()
								+ "%' or " + tgl + "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or  "
								+ tgl + "and pemeriksaan_obstetri_ralan.tinggi_uteri like '%" + TCari.getText().trim()
								+ "%' or " + tgl + "and pemeriksaan_obstetri_ralan.janin like '%"
								+ TCari.getText().trim() + "%' or " + tgl
								+ "and pemeriksaan_obstetri_ralan.letak like '%" + TCari.getText().trim() + "%' "
								+ "order by pemeriksaan_obstetri_ralan.no_rawat desc",
						param);
			}
			break;
		case 4:
			if (tabModeGinekologi.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null,
						"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
				BtnBatal.requestFocus();
			} else if (tabModeGinekologi.getRowCount() != 0) {
				Map<String, Object> param = new HashMap<>();
				param.put("namars", akses.getnamars());
				param.put("alamatrs", akses.getalamatrs());
				param.put("kotars", akses.getkabupatenrs());
				param.put("propinsirs", akses.getpropinsirs());
				param.put("kontakrs", akses.getkontakrs());
				param.put("emailrs", akses.getemailrs());
				param.put("logo", Sequel.cariGambar("select logo from setting"));
				String pas = " and reg_periksa.no_rkm_medis like '%" + TCariPasien.getText() + "%' ";

				String tgl = " pemeriksaan_ginekologi_ralan.tgl_perawatan between '"
						+ Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '"
						+ Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " + pas;
				Valid.MyReportqry("rptJalanGinekologi.jasper", "report",
						"::[ Data Pemeriksaan Ginekologi Rawat Jalan ]::",
						"select pemeriksaan_ginekologi_ralan.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
								+ "pemeriksaan_ginekologi_ralan.tgl_perawatan,pemeriksaan_ginekologi_ralan.jam_rawat,pemeriksaan_ginekologi_ralan.inspeksi,pemeriksaan_ginekologi_ralan.inspeksi_vulva,pemeriksaan_ginekologi_ralan.inspekulo_gine, "
								+ "pemeriksaan_ginekologi_ralan.fluxus_gine,pemeriksaan_ginekologi_ralan.fluor_gine,pemeriksaan_ginekologi_ralan.vulva_inspekulo, "
								+ "pemeriksaan_ginekologi_ralan.portio_inspekulo,pemeriksaan_ginekologi_ralan.sondage,pemeriksaan_ginekologi_ralan.portio_dalam,pemeriksaan_ginekologi_ralan.bentuk, "
								+ "pemeriksaan_ginekologi_ralan.cavum_uteri,pemeriksaan_ginekologi_ralan.mobilitas,pemeriksaan_ginekologi_ralan.ukuran, pemeriksaan_ginekologi_ralan.nyeri_tekan, pemeriksaan_ginekologi_ralan.adnexa_kanan, pemeriksaan_ginekologi_ralan.adnexa_kiri,"
								+ "pemeriksaan_ginekologi_ralan.cavum_douglas "
								+ "from pasien inner join reg_periksa inner join pemeriksaan_ginekologi_ralan "
								+ "on pemeriksaan_ginekologi_ralan.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where  "
								+ tgl + "and pemeriksaan_ginekologi_ralan.no_rawat like '%" + TCari.getText().trim()
								+ "%' or " + tgl + "and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim()
								+ "%' or " + tgl + "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or  "
								+ tgl + "and pemeriksaan_ginekologi_ralan.inspeksi like '%" + TCari.getText().trim()
								+ "%' or " + tgl + "and pemeriksaan_ginekologi_ralan.inspeksi_vulva like '%"
								+ TCari.getText().trim() + "%' or " + tgl
								+ "and pemeriksaan_ginekologi_ralan.inspekulo_gine like '%" + TCari.getText().trim()
								+ "%' " + "order by pemeriksaan_ginekologi_ralan.no_rawat desc",
						param);
			}
			break;
		case 5:
			if (akses.getresume_pasien() == true) {
				panelResume1.laporan();
			}
			break;
		case 6:
			if (akses.getdiagnosa_pasien() == true) {
				panelDiagnosa1.cetak();
			}
			break;
		case 7:
			if (TabModeCatatan.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null,
						"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
				BtnBatal.requestFocus();
			} else if (TabModeCatatan.getRowCount() != 0) {
				Map<String, Object> param = new HashMap<>();
				param.put("namars", akses.getnamars());
				param.put("alamatrs", akses.getalamatrs());
				param.put("kotars", akses.getkabupatenrs());
				param.put("propinsirs", akses.getpropinsirs());
				param.put("kontakrs", akses.getkontakrs());
				param.put("emailrs", akses.getemailrs());
				param.put("logo", Sequel.cariGambar("select logo from setting"));
				String pas = " and reg_periksa.no_rkm_medis like '%" + TCariPasien.getText() + "%' ";

				String tgl = " catatan_perawatan.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "")
						+ "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " + pas;
				Valid.MyReportqry("rptCatatanDokter.jasper", "report", "::[ Data Catatan Dokter ]::",
						"select catatan_perawatan.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
								+ "catatan_perawatan.tanggal,catatan_perawatan.jam,catatan_perawatan.kd_dokter,dokter.nm_dokter,"
								+ "catatan_perawatan.catatan from pasien inner join reg_periksa inner join catatan_perawatan inner join dokter "
								+ "on catatan_perawatan.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
								+ "and catatan_perawatan.kd_dokter=dokter.kd_dokter where  " + tgl
								+ " and catatan_perawatan.no_rawat like '%" + TCari.getText().trim() + "%' or " + tgl
								+ " and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or " + tgl
								+ " and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or  " + tgl
								+ " and catatan_perawatan.catatan like '%" + TCari.getText().trim() + "%' or " + tgl
								+ " and catatan_perawatan.kd_dokter like '%" + TCari.getText().trim() + "%' or " + tgl
								+ " and dokter.nm_dokter like '%" + TCari.getText().trim() + "%' "
								+ "order by catatan_perawatan.no_rawat desc",
						param);
			}
			break;
		default:
			break;
		}

		this.setCursor(Cursor.getDefaultCursor());
	}

	protected void BtnEditActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
			Valid.textKosong(TNoRw, "No.Rawat");
		} else {
			switch (TabMain.getSelectedIndex()) {
			case 1:
				if ((!TKeluhan.getText().trim().equals("")) || (!TPemeriksaan.getText().trim().equals(""))
						|| (!TSuhu.getText().trim().equals("")) || (!TTensi.getText().trim().equals(""))
						|| (!TAlergi.getText().trim().equals("")) || (!TTinggi.getText().trim().equals(""))
						|| (!TBerat.getText().trim().equals("")) || (!TRespirasi.getText().trim().equals(""))
						|| (!TNadi.getText().trim().equals("")) || (!TGCS.getText().trim().equals(""))
						|| (!TindakLanjut.getText().trim().equals("")) || (!TPenilaian.getText().trim().equals(""))
						|| (!KdPeg.getText().trim().equals("")) || (!TPegawai.getText().trim().equals(""))
						|| (!TInstruksi.getText().trim().equals(""))) {
					if (tbPemeriksaan.getSelectedRow() > -1) {
						if (Sequel.mengedittf("pemeriksaan_ralan", "no_rawat='"
								+ tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 1) + "' and tgl_perawatan='"
								+ tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 4) + "' and jam_rawat='"
								+ tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 5) + "'",
								"no_rawat='" + TNoRw.getText() + "',suhu_tubuh='" + TSuhu.getText() + "',tensi='"
										+ TTensi.getText() + "'," + "keluhan='" + TKeluhan.getText() + "',pemeriksaan='"
										+ TPemeriksaan.getText() + "'," + "nadi='" + TNadi.getText() + "',respirasi='"
										+ TRespirasi.getText() + "'," + "tinggi='" + TTinggi.getText() + "',berat='"
										+ TBerat.getText() + "'," + "gcs='" + TGCS.getText() + "',kesadaran='"
										+ cmbKesadaran.getSelectedItem() + "'," + "alergi='" + TAlergi.getText()
										+ "',imun_ke='" + cmbImun.getSelectedItem() + "'," + "tgl_perawatan='"
										+ Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "'," + "jam_rawat='"
										+ cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":"
										+ cmbDtk.getSelectedItem() + "'," + "rtl='" + TindakLanjut.getText()
										+ "',penilaian='" + TPenilaian.getText() + "'," + "instruksi='"
										+ TInstruksi.getText() + "',nip='" + KdPeg.getText() + "'") == true) {
							TSuhu.setText("");
							TTensi.setText("");
							TNadi.setText("");
							TRespirasi.setText("");
							TTinggi.setText("");
							TBerat.setText("");
							TGCS.setText("");
							TKeluhan.setText("");
							TPemeriksaan.setText("");
							TAlergi.setText("");
							cmbImun.setSelectedIndex(0);
							TindakLanjut.setText("");
							TPenilaian.setText("");
							TInstruksi.setText("");
							tampilPemeriksaan();
						}
					} else {
						JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
						TCari.requestFocus();
					}
				}
				break;
			case 2:
				if (!TSuhuVaksin.getText().equals("") || !TtensiVaksn.getText().equals("")
						|| !TNadiVaksn.getText().equals("") || !TRespirasiVaksn.getText().equals("")
						|| !TtinggiVaksn.getText().equals("") || !TKgVaksn.getText().equals("")
						|| !TGCSVaksin.getText().equals("") || !TxtareaSubject.getText().equals("")
						|| !TxtareaObject.getText().equals("") || !TAlegiVaksn.getText().equals("")
						|| !TlinkardadaVksn.getText().equals("")
						|| !cmbImunVaksin.getSelectedItem().toString().equals("-") || !Tkdvksin.getText().equals("")
						|| !Tvksin.getText().equals("") || !kdptgsVasksin.getText().equals("")
						|| !TPegawaiVk.getText().equals("")) {
					if (tbPemeriksaanVaksin.getSelectedRow() > -1) {
						if (Sequel.mengedittf(
								"pemeriksaan_vaksin",
								"no_rawat='"
										+ TNoRw.getText() + "' and tgl_perawatan='"
										+ tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 4)
										+ "' and jam_rawat='"
										+ tbPemeriksaanVaksin.getValueAt(tbPemeriksaanVaksin.getSelectedRow(), 5) + "'",
								"kd_vaksin='" + Tkdvksin.getText() + "',imun_ke='"
										+ cmbImunVaksin.getSelectedItem().toString() + "',suhu_tubuh='"
										+ TSuhuVaksin.getText() + "',tensi='" + TtensiVaksn.getText() + "'," + "nadi='"
										+ TNadiVaksn.getText() + "',respirasi='" + TRespirasiVaksn.getText()
										+ "',tinggi='" + TtinggiVaksn.getText().equals("") + "'," + "berat='"
										+ TKgVaksn.getText() + "',gcs='" + TGCSVaksin.getText() + "',lingkar_dada='"
										+ TlinkardadaVksn.getText() + "',alergi='" + TAlegiVaksn.getText().equals("")
										+ "'," + "subject='" + TxtareaSubject.getText() + "',object='"
										+ TxtareaObject.getText() + "',nip='" + kdptgsVasksin.getText()
										+ "'") == true) {
							TxtareaSubject.setText("");
							TxtareaObject.setText("");
							TSuhuVaksin.setText("");
							TtensiVaksn.setText("");
							TtinggiVaksn.setText("");
							TRespirasiVaksn.setText("");
							TKgVaksn.setText("");
							TNadiVaksn.setText("");
							TGCSVaksin.setText("");
							TAlegiVaksn.setText("");
							TlinkardadaVksn.setText("");
							Tkdvksin.setText("");
							Tvksin.setText("");
							tLkrKepala.setText("");
							tampilPemeriksaanVaksin();
						}
					} else {
						JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
						TCari.requestFocus();
					}
				}
				break;
			case 3:
				if ((!TTinggi_uteri.getText().trim().equals("")) || (!TLetak.getText().trim().equals(""))
						|| (!TDenyut.getText().trim().equals("")) || (!TKualitas_mnt.getText().trim().equals(""))
						|| (!TKualitas_dtk.getText().trim().equals("")) || (!TVulva.getText().trim().equals(""))
						|| (!TPortio.getText().trim().equals("")) || (!TTebal.getText().trim().equals(""))
						|| (!TPembukaan.getText().trim().equals("")) || (!TPenurunan.getText().trim().equals(""))
						|| (!TDenominator.getText().trim().equals(""))) {
					if (tbPemeriksaanObstetri.getSelectedRow() > -1) {
						if (Sequel.mengedittf("pemeriksaan_obstetri_ralan", "no_rawat='"
								+ tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 1)
								+ "' and tgl_perawatan='"
								+ tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 4)
								+ "' and jam_rawat='"
								+ tbPemeriksaanObstetri.getValueAt(tbPemeriksaanObstetri.getSelectedRow(), 5) + "'",
								"no_rawat='" + TNoRw.getText() + "', tgl_perawatan='"
										+ Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "', " + "jam_rawat='"
										+ cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":"
										+ cmbDtk.getSelectedItem() + "', " + "tinggi_uteri='" + TTinggi_uteri.getText()
										+ "', janin='" + cmbJanin.getSelectedItem() + "', letak='" + TLetak.getText()
										+ "', " + "panggul='" + cmbPanggul.getSelectedItem() + "', denyut='"
										+ TDenyut.getText() + "', kontraksi='" + cmbKontraksi.getSelectedItem() + "', "
										+ "kualitas_mnt='" + TKualitas_mnt.getText() + "', kualitas_dtk='"
										+ TKualitas_dtk.getText() + "', " + "fluksus='" + cmbFluksus.getSelectedItem()
										+ "', albus='" + cmbAlbus.getSelectedItem() + "', vulva='" + TVulva.getText()
										+ "'," + "portio='" + TPortio.getText() + "', dalam='"
										+ cmbDalam.getSelectedItem() + "', tebal='" + TTebal.getText() + "', "
										+ "arah='" + cmbArah.getSelectedItem() + "', pembukaan='" + TPembukaan.getText()
										+ "', penurunan='" + TPenurunan.getText() + "', " + "denominator='"
										+ TDenominator.getText() + "', ketuban='" + cmbKetuban.getSelectedItem()
										+ "', feto='" + cmbFeto.getSelectedItem() + "'") == true) {
							TTinggi_uteri.setText("");
							cmbJanin.setSelectedIndex(0);
							TLetak.setText("");
							cmbPanggul.setSelectedIndex(0);
							TDenyut.setText("");
							cmbKontraksi.setSelectedIndex(0);
							TKualitas_mnt.setText("");
							TKualitas_dtk.setText("");
							cmbFluksus.setSelectedIndex(0);
							cmbAlbus.setSelectedIndex(0);
							TVulva.setText("");
							TPortio.setText("");
							cmbDalam.setSelectedIndex(0);
							TTebal.setText("");
							cmbArah.setSelectedIndex(0);
							TPembukaan.setText("");
							TPenurunan.setText("");
							TDenominator.setText("");
							cmbKetuban.setSelectedIndex(0);
							cmbFeto.getSelectedItem().toString();
							tampilPemeriksaanObstetri();
						}
					} else {
						JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
						TCari.requestFocus();
					}
				}
				break;
			case 4:
				if ((!TInspeksi.getText().trim().equals("")) || (!TInspeksiVulva.getText().trim().equals(""))
						|| (!TInspekuloGine.getText().trim().equals(""))
						|| (!TVulvaInspekulo.getText().trim().equals(""))
						|| (!TPortioInspekulo.getText().trim().equals("")) || (!TSondage.getText().trim().equals(""))
						|| (!TPortioDalam.getText().trim().equals("")) || (!TBentuk.getText().trim().equals(""))
						|| (!TCavumUteri.getText().trim().equals("")) || (!TUkuran.getText().trim().equals(""))
						|| (!TAdnexaKanan.getText().trim().equals("")) || (!TAdnexaKiri.getText().trim().equals(""))
						|| (!TCavumDouglas.getText().trim().equals(""))) {
					if (tbPemeriksaanGinekologi.getSelectedRow() > -1) {
						if (Sequel.mengedittf("pemeriksaan_ginekologi_ralan", "no_rawat='"
								+ tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 1)
								+ "' and tgl_perawatan='"
								+ tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 4)
								+ "' and jam_rawat='"
								+ tbPemeriksaanGinekologi.getValueAt(tbPemeriksaanGinekologi.getSelectedRow(), 5) + "'",
								"no_rawat='" + TNoRw.getText() + "', tgl_perawatan='"
										+ Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "', " + "jam_rawat='"
										+ cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":"
										+ cmbDtk.getSelectedItem() + "', " + "inspeksi='" + TInspeksi.getText()
										+ "', inspeksi_vulva='" + TInspeksiVulva.getText() + "', inspekulo_gine='"
										+ TInspekuloGine.getText() + "', " + "fluxus_gine='"
										+ cmbFluxusGine.getSelectedItem() + "', fluor_gine='"
										+ cmbFluorGine.getSelectedItem() + "', " + "vulva_inspekulo='"
										+ TVulvaInspekulo.getText() + "', portio_inspekulo='"
										+ TPortioInspekulo.getText() + "', sondage='" + TSondage.getText() + "', "
										+ "portio_dalam='" + TPortioDalam.getText() + "', bentuk='" + TBentuk.getText()
										+ "', cavum_uteri='" + TCavumUteri.getText() + "', " + "mobilitas='"
										+ cmbMobilitas.getSelectedItem() + "', ukuran='" + TUkuran.getText()
										+ "', nyeri_tekan='" + cmbNyeriTekan.getSelectedItem() + "'," + "adnexa_kanan='"
										+ TAdnexaKanan.getText() + "', adnexa_kiri='" + TAdnexaKiri.getText()
										+ "', cavum_douglas='" + TCavumDouglas.getText() + "'") == true) {
							TInspeksi.setText("");
							TInspeksiVulva.setText("");
							TInspekuloGine.setText("");
							cmbFluxusGine.setSelectedIndex(0);
							cmbFluorGine.setSelectedIndex(0);
							TVulvaInspekulo.setText("");
							TPortioInspekulo.setText("");
							TSondage.setText("");
							TPortioDalam.setText("");
							TBentuk.setText("");
							TCavumUteri.setText("");
							cmbMobilitas.setSelectedIndex(0);
							TUkuran.setText("");
							cmbNyeriTekan.setSelectedIndex(0);
							TAdnexaKanan.setText("");
							TAdnexaKiri.setText("");
							TCavumDouglas.getText();
							tampilPemeriksaanGinekologi();
						}
					} else {
						JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
						TCari.requestFocus();
					}
				}
				break;
			case 5:
				if (!Catatan.getText().trim().equals("")) {
					if (tbCatatan.getSelectedRow() > -1) {
						if (Sequel.mengedittf("catatan_perawatan",
								"no_rawat='" + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 1) + "' and tanggal='"
										+ tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 4) + "' and jam='"
										+ tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 5) + "' and kd_dokter='"
										+ tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 6) + "'",
								"no_rawat='" + TNoRw.getText() + "',catatan='" + Catatan.getText() + "',"
										+ "kd_dokter='" + KdDok3.getText() + "',tanggal='"
										+ Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "'," + "jam='"
										+ cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":"
										+ cmbDtk.getSelectedItem() + "'") == true) {
							Catatan.setText("");
							tampilCatatan();
						}
					} else {
						JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
						TCari.requestFocus();
					}
				}
				break;
			default:
				break;
			}
		}
	}

	protected void BtnEditKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
			BtnEditActionPerformed(null);
		} else {
			Valid.pindah(evt, BtnHapus, BtnPrint);
		}
	}

	protected void BtnHapusKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
			BtnHapusActionPerformed(null);
		} else {
			Valid.pindah(evt, BtnBatal, BtnPrint);
		}
	}

	protected void BtnHapusActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Jurnal jur = new Jurnal();
		switch (TabMain.getSelectedIndex()) {
		case 0:
			if (tabModeDrPr.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
				TNoRw.requestFocus();
			} else {
				Sequel.AutoComitFalse();
				sukses = true;
				ttljmdokter = 0;
				ttljmperawat = 0;
				ttlkso = 0;
				ttlpendapatan = 0;
				ttljasasarana = 0;
				ttlbhp = 0;
				ttlmenejemen = 0;
				for (i = 0; i < tbRawatDrPr.getRowCount(); i++) {
					if (tbRawatDrPr.getValueAt(i, 0).toString().equals("true")) {
						if (Sequel.cariRegistrasi(tbRawatDrPr.getValueAt(i, 1).toString()) > 0) {
							JOptionPane.showMessageDialog(rootPane,
									"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
							TCari.requestFocus();
						} else {
							if (Sequel.queryutf("delete from tindakan_rajal where no_rawat='"
									+ tbRawatDrPr.getValueAt(i, 1).toString() + "' and kd_jenis_prw='"
									+ tbRawatDrPr.getValueAt(i, 12) + "' and kd_dokter='"
									+ tbRawatDrPr.getValueAt(i, 5).toString() + "' and nip='"
									+ tbRawatDrPr.getValueAt(i, 7).toString() + "' and tgl_perawatan='"
									+ tbRawatDrPr.getValueAt(i, 9).toString() + "' and jam_rawat='"
									+ tbRawatDrPr.getValueAt(i, 10).toString() + "' ") == true) {
								ttljmdokter = ttljmdokter
										+ Double.parseDouble(tbRawatDrPr.getValueAt(i, 13).toString());
								ttljmperawat = ttljmperawat
										+ Double.parseDouble(tbRawatDrPr.getValueAt(i, 14).toString());
								ttlkso = ttlkso + Double.parseDouble(tbRawatDrPr.getValueAt(i, 15).toString());
								ttlpendapatan = ttlpendapatan
										+ Double.parseDouble(tbRawatDrPr.getValueAt(i, 11).toString());
								ttljasasarana = ttljasasarana
										+ Double.parseDouble(tbRawatDrPr.getValueAt(i, 16).toString());
								ttlbhp = ttlbhp + Double.parseDouble(tbRawatDrPr.getValueAt(i, 17).toString());
								ttlmenejemen = ttlmenejemen
										+ Double.parseDouble(tbRawatDrPr.getValueAt(i, 18).toString());
							} else {
								sukses = false;
							}
						}
					}
				}

				if (sukses == true) {
					Sequel.queryu("delete from tampjurnal");
					if (ttlpendapatan > 0) {
						Sequel.menyimpan("tampjurnal",
								"'" + Suspen_Piutang_Tindakan_Ranap + "','Suspen Piutang Tindakan Ralan','0','"
										+ ttlpendapatan + "'",
								"kredit=kredit+'" + (ttlpendapatan) + "'",
								"kd_rek='" + Suspen_Piutang_Tindakan_Ranap + "'");
						Sequel.menyimpan("tampjurnal",
								"'" + Tindakan_Ranap + "','Pendapatan Tindakan Rawat Jalan','" + ttlpendapatan
										+ "','0'",
								"debet=debet+'" + (ttlpendapatan) + "'", "kd_rek='" + Tindakan_Ranap + "'");
					}
					if (ttljmdokter > 0) {
						Sequel.menyimpan("tampjurnal",
								"'" + Beban_Jasa_Medik_Dokter_Tindakan_Ranap
										+ "','Beban Jasa Medik Dokter Tindakan Ralan','0','" + ttljmdokter + "'",
								"kredit=kredit+'" + (ttljmdokter) + "'",
								"kd_rek='" + Beban_Jasa_Medik_Dokter_Tindakan_Ranap + "'");
						Sequel.menyimpan("tampjurnal",
								"'" + Utang_Jasa_Medik_Dokter_Tindakan_Ranap
										+ "','Utang Jasa Medik Dokter Tindakan Ralan','" + ttljmdokter + "','0'",
								"debet=debet+'" + (ttljmdokter) + "'",
								"kd_rek='" + Utang_Jasa_Medik_Dokter_Tindakan_Ranap + "'");
					}
					if (ttljmperawat > 0) {
						Sequel.menyimpan("tampjurnal",
								"'" + Beban_Jasa_Medik_Paramedis_Tindakan_Ranap
										+ "','Beban Jasa Medik Paramedis Tindakan Ralan','0','" + ttljmperawat + "'",
								"kredit=kredit+'" + (ttljmperawat) + "'",
								"kd_rek='" + Beban_Jasa_Medik_Paramedis_Tindakan_Ranap + "'");
						Sequel.menyimpan("tampjurnal",
								"'" + Utang_Jasa_Medik_Paramedis_Tindakan_Ranap
										+ "','Utang Jasa Medik Paramedis Tindakan Ralan','" + ttljmperawat + "','0'",
								"debet=debet+'" + (ttljmperawat) + "'",
								"kd_rek='" + Utang_Jasa_Medik_Paramedis_Tindakan_Ranap + "'");
					}
					if (ttlkso > 0) {
						Sequel.menyimpan("tampjurnal",
								"'" + Beban_KSO_Tindakan_Ranap + "','Beban KSO Tindakan Ralan','0','" + ttlkso + "'",
								"kredit=kredit+'" + (ttlkso) + "'", "kd_rek='" + Beban_KSO_Tindakan_Ranap + "'");
						Sequel.menyimpan("tampjurnal",
								"'" + Utang_KSO_Tindakan_Ranap + "','Utang KSO Tindakan Ralan','" + ttlkso + "','0'",
								"debet=debet+'" + (ttlkso) + "'", "kd_rek='" + Utang_KSO_Tindakan_Ranap + "'");
					}
					if (ttlmenejemen > 0) {
						Sequel.menyimpan("tampjurnal",
								"'" + Beban_Jasa_Menejemen_Tindakan_Ranap
										+ "','Beban Jasa Menejemen Tindakan Ralan','0','" + ttlmenejemen + "'",
								"kredit=kredit+'" + (ttlmenejemen) + "'",
								"kd_rek='" + Beban_Jasa_Menejemen_Tindakan_Ranap + "'");
						Sequel.menyimpan("tampjurnal",
								"'" + Utang_Jasa_Menejemen_Tindakan_Ranap + "','Utang Jasa Menejemen Tindakan Ralan','"
										+ ttlmenejemen + "','0'",
								"debet=debet+'" + (ttlmenejemen) + "'",
								"kd_rek='" + Utang_Jasa_Menejemen_Tindakan_Ranap + "'");
					}
					if (ttljasasarana > 0) {
						Sequel.menyimpan("tampjurnal",
								"'" + Beban_Jasa_Sarana_Tindakan_Ranap + "','Beban Jasa Sarana Tindakan Ralan','0','"
										+ ttljasasarana + "'",
								"kredit=kredit+'" + (ttljasasarana) + "'",
								"kd_rek='" + Beban_Jasa_Sarana_Tindakan_Ranap + "'");
						Sequel.menyimpan("tampjurnal",
								"'" + Utang_Jasa_Sarana_Tindakan_Ranap + "','Utang Jasa Sarana Tindakan Ralan','"
										+ ttljasasarana + "','0'",
								"debet=debet+'" + (ttljasasarana) + "'",
								"kd_rek='" + Utang_Jasa_Sarana_Tindakan_Ranap + "'");
					}
					if (ttlbhp > 0) {
						Sequel.menyimpan("tampjurnal",
								"'" + HPP_BHP_Tindakan_Ranap + "','HPP BHP Tindakan Ralan','0','" + ttlbhp + "'",
								"kredit=kredit+'" + (ttlbhp) + "'", "kd_rek='" + HPP_BHP_Tindakan_Ranap + "'");
						Sequel.menyimpan("tampjurnal",
								"'" + Persediaan_BHP_Tindakan_Ranap + "','Persediaan BHP Tindakan Ralan','" + ttlbhp
										+ "','0'",
								"debet=debet+'" + (ttlbhp) + "'", "kd_rek='" + Persediaan_BHP_Tindakan_Ranap + "'");
					}
					sukses = jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U",
							"PEMBATALAN TINDAKAN RAWAT JALAN PASIEN OLEH " + akses.getkode());
				}

				if (sukses == true) {
					Sequel.Commit();
					tampilDrPr();
				} else {
					sukses = false;
					JOptionPane.showMessageDialog(null,
							"Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
					Sequel.RollBack();
				}
				Sequel.AutoComitTrue();
			}
			break;
		case 1:
			if (tabModePemeriksaan.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
				TNoRw.requestFocus();
			} else {
				for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
					if (tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
						Sequel.queryu("delete from pemeriksaan_ralan where no_rawat='"
								+ tbPemeriksaan.getValueAt(i, 1).toString() + "' and tgl_perawatan='"
								+ tbPemeriksaan.getValueAt(i, 4).toString() + "' and jam_rawat='"
								+ tbPemeriksaan.getValueAt(i, 5).toString() + "' ");
					}
				}
				tampilPemeriksaan();
			}
			break;
		case 2:
			if (tableModePemeriksaanVaksin.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
				TNoRw.requestFocus();
			} else {
				for (i = 0; i < tableModePemeriksaanVaksin.getRowCount(); i++) {
					if (tbPemeriksaanVaksin.getValueAt(i, 0).toString().equals("true")) {
						int jawab = JOptionPane.showConfirmDialog(null, "Apakah anda yakin menghapus data ini?",
								"Konfirmasi", JOptionPane.YES_NO_OPTION);
						if (jawab == JOptionPane.YES_OPTION) {
							Sequel.queryu("delete from pemeriksaan_vaksin where no_rawat='"
									+ tbPemeriksaanVaksin.getValueAt(i, 1).toString() + "'");
						}
					}
				}
				tampilPemeriksaanVaksin();
			}
			break;
		case 3:
			if (tabModeObstetri.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
				TNoRw.requestFocus();
			} else {
				for (i = 0; i < tbPemeriksaanObstetri.getRowCount(); i++) {
					if (tbPemeriksaanObstetri.getValueAt(i, 0).toString().equals("true")) {
						Sequel.queryu("delete from pemeriksaan_obstetri_ralan where no_rawat='"
								+ tbPemeriksaanObstetri.getValueAt(i, 1).toString() + "' and tgl_perawatan='"
								+ tbPemeriksaanObstetri.getValueAt(i, 4).toString() + "' and jam_rawat='"
								+ tbPemeriksaanObstetri.getValueAt(i, 5).toString() + "' ");
					}
				}
				tampilPemeriksaanObstetri();
			}
			break;
		case 4:
			if (tabModeGinekologi.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!");
				TNoRw.requestFocus();
			} else {
				for (i = 0; i < tbPemeriksaanGinekologi.getRowCount(); i++) {
					if (tbPemeriksaanGinekologi.getValueAt(i, 0).toString().equals("true")) {
						Sequel.queryu("delete from pemeriksaan_ginekologi_ralan where no_rawat='"
								+ tbPemeriksaanGinekologi.getValueAt(i, 1).toString() + "' and tgl_perawatan='"
								+ tbPemeriksaanGinekologi.getValueAt(i, 4).toString() + "' and jam_rawat='"
								+ tbPemeriksaanGinekologi.getValueAt(i, 5).toString() + "' ");
					}
				}
				tampilPemeriksaanGinekologi();
			}
			break;
		case 6:
			panelDiagnosa1.setRM(TNoRw.getText(), TNoRM.getText(), Valid.SetTgl(DTPCari1.getSelectedItem() + ""),
					Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "Ralan", TCari.getText().trim());
			panelDiagnosa1.hapus();
			LCount.setText(panelDiagnosa1.getRecord() + "");
			break;
		case 7:
			if (TabModeCatatan.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
				TNoRw.requestFocus();
			} else {
				for (i = 0; i < tbCatatan.getRowCount(); i++) {
					if (tbCatatan.getValueAt(i, 0).toString().equals("true")) {
						Sequel.queryu(
								"delete from catatan_perawatan where no_rawat='" + tbCatatan.getValueAt(i, 1).toString()
										+ "' and tanggal='" + tbCatatan.getValueAt(i, 4).toString() + "' and jam='"
										+ tbCatatan.getValueAt(i, 5).toString() + "' and kd_dokter='"
										+ tbCatatan.getValueAt(i, 6).toString() + "' ");
					}
				}
				tampilCatatan();
			}
			break;
		default:
			break;
		}

		BtnBatalActionPerformed(evt);
	}

	protected void BtnBatalKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
			BtnBatalActionPerformed(null);
		} else {
			Valid.pindah(evt, BtnSimpan, BtnHapus);
		}
	}

	protected void BtnBatalActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		ChkInput.setSelected(true);
		ChkInput1.setSelected(true);
		ChkInput2.setSelected(true);
		ChkInput3.setSelected(true);
		isForm();
		isForm2();
		isForm3();
		isForm4();
		TSuhu.setText("");
		TTensi.setText("");
		TKeluhan.setText("");
		TInstruksi.setText("");
		TPemeriksaan.setText("");
		TPenilaian.setText("");
		TAlergi.setText("");
		TBerat.setText("");
		TTinggi.setText("");
		TNadi.setText("");
		TRespirasi.setText("");
		TGCS.setText("");
		TindakLanjut.setText("");
		TTinggi_uteri.setText("");
		TLetak.setText("");
		TDenyut.setText("");
		TVulva.setText("");
		TPortio.setText("");
		TTebal.setText("");
		TPembukaan.setText("");
		TPenurunan.setText("");
		TDenominator.setText("");
		TKualitas_mnt.setText("");
		TKualitas_dtk.setText("");
		TInspeksi.setText("");
		TInspeksiVulva.setText("");
		TInspekuloGine.setText("");
		TVulvaInspekulo.setText("");
		TPortioInspekulo.setText("");
		TSondage.setText("");
		TPortioDalam.setText("");
		TBentuk.setText("");
		TCavumUteri.setText("");
		TUkuran.setText("");
		TAdnexaKanan.setText("");
		TAdnexaKiri.setText("");
		TCavumDouglas.setText("");
		Catatan.setText("");
		cmbKesadaran.setSelectedIndex(0);
		cmbImunVaksin.setSelectedIndex(0);
		TxtareaSubject.setText("");
		TxtareaObject.setText("");
		TSuhuVaksin.setText("");
		TtensiVaksn.setText("");
		TtinggiVaksn.setText("");
		TRespirasiVaksn.setText("");
		TKgVaksn.setText("");
		TNadiVaksn.setText("");
		TGCSVaksin.setText("");
		TAlegiVaksn.setText("");
		TlinkardadaVksn.setText("");
		Tkdvksin.setText("");
		Tvksin.setText("");
		tLkrKepala.setText("");
		TNoRw.requestFocus();
	}

	protected void BtnSimpanActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
			Valid.textKosong(TNoRw, "No.Rawat");
		} else {
			switch (TabMain.getSelectedIndex()) {
			case 0:
				if (KdDok.getText().trim().equals("") || TDokter.getText().trim().equals("")) {
					Valid.textKosong(KdDok, "Dokter");
				}else {
					try {
						jmlparsial = 0;
						if (aktifkanparsial.equals("yes")) {
							jmlparsial = Sequel.cariInteger("select count(kd_pj) from set_input_parsial where kd_pj=?",
									Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", TNoRw.getText()));
						}
						if (jmlparsial > 0) {
//							SimpanPenangananDokterPetugas();
							AddDoDoctorNurse();
						} else {
							if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
								JOptionPane.showMessageDialog(rootPane,
										"Data billing sudah terverifikasi.\nSilahkan hubungi bagian kasir/keuangan ..!!");
								TCari.requestFocus();
							} else {
//								SimpanPenangananDokterPetugas();
								AddDoDoctorNurse();
							}
						}
					} catch (Exception e) {
					}
				}
				break;
			case 1:
				if ((!TKeluhan.getText().trim().equals("")) || (!TPemeriksaan.getText().trim().equals(""))
						|| (!TSuhu.getText().trim().equals("")) || (!TTensi.getText().trim().equals(""))
						|| (!TAlergi.getText().trim().equals("")) || (!TTinggi.getText().trim().equals(""))
						|| (!TBerat.getText().trim().equals("")) || (!TRespirasi.getText().trim().equals(""))
						|| (!TNadi.getText().trim().equals("")) || (!TGCS.getText().trim().equals(""))
						|| (!TindakLanjut.getText().trim().equals("")) || (!TPenilaian.getText().trim().equals(""))
						|| (!TInstruksi.getText().trim().equals(""))) {
					if (KdPeg.getText().trim().equals("") || TPegawai.getText().trim().equals("")) {
						Valid.textKosong(KdPeg, "Dokter/Paramedis masih kosong...!!");
					} else {
						if (Sequel.menyimpantf("pemeriksaan_ralan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 19,
								new String[] { TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""),
										cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":"
												+ cmbDtk.getSelectedItem(),
										TSuhu.getText(), TTensi.getText(), TNadi.getText(), TRespirasi.getText(),
										TTinggi.getText(), TBerat.getText(), TGCS.getText(),
										cmbKesadaran.getSelectedItem().toString(), TKeluhan.getText(),
										TPemeriksaan.getText(), TAlergi.getText(), cmbImun.getSelectedItem().toString(),
										TindakLanjut.getText(), TPenilaian.getText(), TInstruksi.getText(),
										KdPeg.getText() }) == true) {
							TSuhu.setText("");
							TTensi.setText("");
							TNadi.setText("");
							TRespirasi.setText("");
							TTinggi.setText("");
							TBerat.setText("");
							TGCS.setText("");
							TKeluhan.setText("");
							TPemeriksaan.setText("");
							TAlergi.setText("");
							cmbImun.setSelectedIndex(0);
							TindakLanjut.setText("");
							TPenilaian.setText("");
							TInstruksi.setText("");
							cmbKesadaran.setSelectedIndex(0);
							tampilPemeriksaan();
						}
					}
				}
				break;
			case 2:
				if (kdptgsVasksin.getText().trim().equals("")) {
					Valid.textKosong(KdPeg, "Petugas masih kosong...!!");
				} else {
					if (Sequel.menyimpantf("pemeriksaan_vaksin", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 18,
							new String[] { TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""),
									cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":"
											+ cmbDtk.getSelectedItem(),
									Tkdvksin.getText(), cmbImunVaksin.getSelectedItem().toString(),
									TSuhuVaksin.getText(), TtensiVaksn.getText(), TNadiVaksn.getText(),
									TRespirasiVaksn.getText(), TtinggiVaksn.getText(), TKgVaksn.getText(),
									TGCSVaksin.getText(), TlinkardadaVksn.getText(), TAlegiVaksn.getText(),
									TxtareaSubject.getText(), TxtareaObject.getText(),
									kdptgsVasksin.getText(),tLkrKepala.getText() }) == true) {

						TSuhuVaksin.setText("");
						TtensiVaksn.setText("");
						TNadiVaksn.setText("");
						TRespirasiVaksn.setText("");
						TtinggiVaksn.setText("");
						TKgVaksn.setText("");
						TGCSVaksin.setText("");
						TxtareaSubject.setText("");
						TxtareaObject.setText("");
						TAlegiVaksn.setText("");
						cmbImunVaksin.setSelectedIndex(0);
						TlinkardadaVksn.setText("");
						Tkdvksin.setText("");
						Tvksin.setText("");
						tampilPemeriksaanVaksin();
					}
				}
				break;
			case 3:
				if ((!TTinggi_uteri.getText().trim().equals("")) || (!TLetak.getText().trim().equals(""))
						|| (!TDenyut.getText().trim().equals("")) || (!TKualitas_mnt.getText().trim().equals(""))
						|| (!TKualitas_dtk.getText().trim().equals("")) || (!TVulva.getText().trim().equals(""))
						|| (!TPortio.getText().trim().equals("")) || (!TTebal.getText().trim().equals(""))
						|| (!TPembukaan.getText().trim().equals("")) || (!TPenurunan.getText().trim().equals(""))
						|| (!TDenominator.getText().trim().equals(""))) {
					if (Sequel.menyimpantf("pemeriksaan_obstetri_ralan",
							"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 23,
							new String[] { TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""),
									cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":"
											+ cmbDtk.getSelectedItem(),
									TTinggi_uteri.getText(), cmbJanin.getSelectedItem().toString(), TLetak.getText(),
									cmbPanggul.getSelectedItem().toString(), TDenyut.getText(),
									cmbKontraksi.getSelectedItem().toString(), TKualitas_mnt.getText(),
									TKualitas_dtk.getText(), cmbFluksus.getSelectedItem().toString(),
									cmbAlbus.getSelectedItem().toString(), TVulva.getText(), TPortio.getText(),
									cmbDalam.getSelectedItem().toString(), TTebal.getText(),
									cmbArah.getSelectedItem().toString(), TPembukaan.getText(), TPenurunan.getText(),
									TDenominator.getText(), cmbKetuban.getSelectedItem().toString(),
									cmbFeto.getSelectedItem().toString() }) == true) {
						TTinggi_uteri.setText("");
						cmbJanin.setSelectedIndex(0);
						TLetak.setText("");
						cmbPanggul.setSelectedIndex(0);
						TDenyut.setText("");
						cmbKontraksi.setSelectedIndex(0);
						TKualitas_mnt.setText("");
						TKualitas_dtk.setText("");
						cmbFluksus.setSelectedIndex(0);
						cmbAlbus.setSelectedIndex(0);
						TVulva.setText("");
						TPortio.setText("");
						cmbDalam.setSelectedIndex(0);
						TTebal.setText("");
						cmbArah.setSelectedIndex(0);
						TPembukaan.setText("");
						TPenurunan.setText("");
						TDenominator.setText("");
						cmbKetuban.setSelectedIndex(0);
						cmbFeto.getSelectedItem().toString();
						tampilPemeriksaanObstetri();
					}
				}
				break;
			case 4:
				if ((!TInspeksi.getText().trim().equals("")) || (!TInspeksiVulva.getText().trim().equals(""))
						|| (!TInspekuloGine.getText().trim().equals("")) || (!TUkuran.getText().trim().equals(""))
						|| (!TPortioInspekulo.getText().trim().equals("")) || (!TSondage.getText().trim().equals(""))
						|| (!TPortioDalam.getText().trim().equals("")) || (!TBentuk.getText().trim().equals(""))
						|| (!TCavumUteri.getText().trim().equals("")) || (!TUkuran.getText().trim().equals(""))
						|| (!TAdnexaKanan.getText().trim().equals("")) || (!TAdnexaKiri.getText().trim().equals(""))
						|| (!TCavumDouglas.getText().trim().equals(""))) {
					if (Sequel.menyimpantf("pemeriksaan_ginekologi_ralan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",
							"Data", 20,
							new String[] { TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""),
									cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":"
											+ cmbDtk.getSelectedItem(),
									TInspeksi.getText(), TInspeksiVulva.getText(), TInspekuloGine.getText(),
									cmbFluxusGine.getSelectedItem().toString(),
									cmbFluorGine.getSelectedItem().toString(), TVulvaInspekulo.getText(),
									TPortioInspekulo.getText(), TSondage.getText(), TPortioDalam.getText(),
									TBentuk.getText(), TCavumUteri.getText(), cmbMobilitas.getSelectedItem().toString(),
									TUkuran.getText(), cmbNyeriTekan.getSelectedItem().toString(),
									TAdnexaKanan.getText(), TAdnexaKiri.getText(), TCavumDouglas.getText() }) == true) {
						TInspeksi.setText("");
						TInspeksiVulva.setText("");
						TInspekuloGine.setText("");
						cmbFluxusGine.setSelectedIndex(0);
						cmbFluorGine.setSelectedIndex(0);
						TVulvaInspekulo.setText("");
						TPortioInspekulo.setText("");
						TSondage.setText("");
						TPortioDalam.setText("");
						TBentuk.setText("");
						TCavumUteri.setText("");
						cmbMobilitas.setSelectedIndex(0);
						TUkuran.setText("");
						cmbNyeriTekan.setSelectedIndex(0);
						TAdnexaKanan.setText("");
						TAdnexaKiri.setText("");
						TCavumDouglas.getText();
						tampilPemeriksaanGinekologi();
					}
				}
				break;
			case 6:
				if (akses.getdiagnosa_pasien() == true) {
					panelDiagnosa1.setRM(TNoRw.getText(), TNoRM.getText(),
							Valid.SetTgl(DTPCari1.getSelectedItem() + ""),
							Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "Ralan", TCari.getText().trim());
					panelDiagnosa1.simpan();
				}
				break;
			case 7:
				if ((!KdDok3.getText().trim().equals("")) && (!TDokter3.getText().trim().equals(""))
						&& (!Catatan.getText().trim().equals(""))) {
					if (Sequel.menyimpantf("catatan_perawatan", "?,?,?,?,?", "Data", 5,
							new String[] { Valid.SetTgl(DTPTgl.getSelectedItem() + ""),
									cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":"
											+ cmbDtk.getSelectedItem(),
									TNoRw.getText(), KdDok3.getText(), Catatan.getText() }) == true) {
						Catatan.setText("");
						tampilCatatan();
					}
				}
				break;
			default:
				break;
			}
		}
	}

	private void AddDoDoctorNurse() {
		// TODO Auto-generated method stub
		try {
			ChkJln.setSelected(false);
			Sequel.AutoComitFalse();
			sukses = true;
			ttljmdokter = 0;
			ttljmperawat = 0;
			ttlkso = 0;
			ttlpendapatan = 0;
			ttljasasarana = 0;
			ttlbhp = 0;
			ttlmenejemen = 0;
			for (i = 0; i < tbTindakan.getRowCount(); i++) {
				if (tbTindakan.getValueAt(i, 0).toString().equals("true")) {
					if (Sequel.menyimpantf("tindakan_rajal", "?,?,?,?,?,?,?,?,?,?,?,?,?,'Belum'", "Tindakan Ralan", 13,
							new String[] { TNoRw.getText(), tbTindakan.getValueAt(i, 1).toString(), KdDok.getText(),
									kdpetugas.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""),
									cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":"
											+ cmbDtk.getSelectedItem(),
									tbTindakan.getValueAt(i, 5).toString(), tbTindakan.getValueAt(i, 6).toString(),
									tbTindakan.getValueAt(i, 7).toString(), tbTindakan.getValueAt(i, 8).toString(),
									tbTindakan.getValueAt(i, 9).toString(), tbTindakan.getValueAt(i, 10).toString(),
									tbTindakan.getValueAt(i, 4).toString() }) == true) {
						ttljmdokter = ttljmdokter + Double.parseDouble(tbTindakan.getValueAt(i, 7).toString());
						ttljmperawat = ttljmperawat + Double.parseDouble(tbTindakan.getValueAt(i, 8).toString());
						ttlkso = ttlkso + Double.parseDouble(tbTindakan.getValueAt(i, 9).toString());
						ttlpendapatan = ttlpendapatan + Double.parseDouble(tbTindakan.getValueAt(i, 4).toString());
						ttljasasarana = ttljasasarana + Double.parseDouble(tbTindakan.getValueAt(i, 5).toString());
						ttlbhp = ttlbhp + Double.parseDouble(tbTindakan.getValueAt(i, 6).toString());
						ttlmenejemen = ttlmenejemen + Double.parseDouble(tbTindakan.getValueAt(i, 10).toString());
					} else {
						sukses = false;
					}
				}
			}
			if (sukses == true) {
				Sequel.Commit();
				JOptionPane.showMessageDialog(null, "Simpan Tindakan Berhasil");
				for (i = 0; i < tbTindakan.getRowCount(); i++) {
					tbTindakan.setValueAt(false, i, 0);
				}
//				jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U",
//						"TINDAKAN RAWAT JALAN PASIEN " + TNoRM.getText() + " " + TPasien.getText() + ", DIPOSTING OLEH "
//								+ akses.getkode());
			} else {
				sukses = false;
				JOptionPane.showMessageDialog(null,
						"Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
				Sequel.RollBack();
			}

			Sequel.AutoComitTrue();
			ChkJln.setSelected(true);

		} catch (Exception e) {
			System.out.println("Notif : " + e);
		}
	}

	protected void BtnSimpanKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
			BtnSimpanActionPerformed(null);
		} else {
			if (TabMain.getSelectedIndex() == 0) {
				Valid.pindah(evt, BtnSeekDokter, BtnBatal);
			} else if (TabMain.getSelectedIndex() == 1) {
				Valid.pindah(evt, BtnSeekPetugas, BtnBatal);
			} else if (TabMain.getSelectedIndex() == 3) {
				Valid.pindah(evt, TInstruksi, BtnBatal);
			} else if (TabMain.getSelectedIndex() == 5) {
				Valid.pindah(evt, cmbFeto, BtnBatal);
			} else if (TabMain.getSelectedIndex() == 6) {
				Valid.pindah(evt, TCavumDouglas, BtnBatal);
			} else if (TabMain.getSelectedIndex() == 9) {
				Valid.pindah(evt, Catatan, BtnBatal);
			}
		}
	}

	protected void BtnKeluarKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub

		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			BtnKeluarActionPerformed(null);
		} else {
			Valid.pindah(evt, BtnPrint, TCari);
		}
	}

	protected void BtnKeluarActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		petugas.dispose();
		dokter.dispose();
		pasien.dispose();
		Sequel.mengedit("reg_periksa", "no_rawat=?", "stts=?", 2, new String[] { "Sudah", TNoRw.getText() });
		dispose();
	}

	public void emptTeks() {
		BtnBatalActionPerformed(null);
		TabMain.setSelectedIndex(1);
	}

	public void isCek() {
		BtnSimpan.setEnabled(akses.gettindakan_ralan());
		BtnHapus.setEnabled(akses.gettindakan_ralan());
		BtnEdit.setEnabled(akses.gettindakan_ralan());
		BtnPrint.setEnabled(akses.gettindakan_ralan());
		BtnResepObat.setEnabled(akses.getresep_dokter());
		BtnCopyResep.setEnabled(akses.getresep_dokter());
		BtnObatBhp.setEnabled(akses.getberi_obat());
		BtnInputObat.setEnabled(akses.getberi_obat());
		BtnPermintaanLab.setEnabled(akses.getpermintaan_lab());
		BtnBerkasDigital.setEnabled(akses.getberkas_digital_perawatan());
		BtnPermintaanRad.setEnabled(akses.getpermintaan_radiologi());
		BtnTambahTindakan.setEnabled(akses.gettarif_ralan());
		BtnKamar.setEnabled(akses.getkamar_inap());
		BtnRujukInternal.setEnabled(akses.getrujukan_poli_internal());
		BtnRujukKeluar.setEnabled(akses.getrujukan_keluar());
		BtnSKDP.setEnabled(akses.getskdp_bpjs());
		BtnCatatan.setEnabled(akses.getcatatan_pasien());
		BtnTriaseIGD.setEnabled(akses.getdata_triase_igd());
		BtnResume.setEnabled(akses.getdata_resume_pasien());
		TCari.setPreferredSize(new Dimension(207, 23));
	}

	public void SetPoli(String KodePoli) {
		// System.out.print(KodePoli);
		this.kode_poli = KodePoli;
		TPoli.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", KodePoli));
	}

	public void setNoRm(String norwt, Date tgl1, Date tgl2) {
		TNoRw.setText(norwt);
		TCari.setText("");
		DTPCari1.setDate(tgl1);
		DTPCari2.setDate(tgl2);
		isRawat();
		isPsien();
		KdDok.setText(Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?", norwt));
		Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter, KdDok.getText());

		KdDok3.setText(KdDok.getText());
		TDokter.setText(TDokter.getText());
		TDokter3.setText(TDokter.getText());
		KdPeg.setText(KdDok.getText());
		TPegawai.setText(TDokter.getText());
		
		Sequel.cariIsi("select jbtn from pegawai where nik=?", Jabatan, KdPeg.getText());
		ChkInput.setSelected(true);
		isForm();
		ChkInput1.setSelected(true);
		isForm2();
		ChkInput2.setSelected(true);
		isForm3();
		ChkInput3.setSelected(true);
		isForm4();
		TabMainMouseClicked(null);
	}
	public void setNoRm(String norwt, Date tgl1, Date tgl2, String kodedokter, String namadokter) {
		TNoRw.setText(norwt);
		DTPCari1.setDate(tgl1);
		DTPCari2.setDate(tgl2);
		isRawat();
		isPsien();
		ChkInput.setSelected(true);
		isForm();
		ChkInput1.setSelected(true);
		isForm2();
		ChkInput2.setSelected(true);
		isForm3();
		ChkInput3.setSelected(true);
		isForm4();
		KdDok.setText(kodedokter);
		
		KdDok3.setText(kodedokter);
		KdPeg.setText(kodedokter);
		TDokter.setText(namadokter);
		
		TDokter3.setText(namadokter);
		TPegawai.setText(namadokter);
		Sequel.cariIsi("select jbtn from pegawai where nik=?", Jabatan, KdPeg.getText());
	}

	public void SetPj(String KodePj) {
		this.kd_pj = KodePj;
	}
}
