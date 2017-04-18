package org.hjm.main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;

import org.hjm.entity.Chat;
import org.hjm.model.BubbleModel;
import org.hjm.renderer.BubbleRenderer;

import com.oracle.jrockit.jfr.EventToken;


public class ChatMain extends JFrame {
	JPanel p_north;//���ʿ� �ٿ��� �г�
	JPanel p_center; //��� �ٿ��� �г�
	JPanel p_south;////���ʿ� �ٿ��� �г�
	BubbleModel mModel = new BubbleModel();//���̺� ��
	
	//���� ����
	JButton bt_profil;//���� �г� ������ ������ ����
	JLabel la_user; //���� �г� ������ ä�ù��� ��ȭ���
	JButton bt_list, bt_totalview, bt_serch;//���� �г� ������ �Խ���, ��ƺ���, �˻����
	JButton bt_option; //���� �г��� ���ʿ� ������ư
	
	//���Ϳ���
	JScrollPane scroll;//��� �ٿ��� ��ũ��
	JTable table; //��� �� ���̺�

	public ChatMain() {
		initGUI();
	}
	
	public void initGUI() {
		p_north = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		
		//���ʿ����� �г�
		bt_profil = new JButton("�̹���");
		la_user = new JLabel("����");
		bt_list = new JButton("�Խ���");
		bt_totalview = new JButton("��ƺ���");
		bt_serch = new JButton("�˻�");
		bt_option = new JButton("����");
		
		p_north.setLayout(new MigLayout());
		p_north.setBackground(Color.RED);
		p_north.setPreferredSize(new Dimension(500, 60));
		p_north.add(bt_profil, "span 2 2"); //���� ������
		p_north.add(la_user, "span 3,wrap"); //��ȭ�� �������
		p_north.add(bt_list); //�Խ���
		p_north.add(bt_totalview); //��ƺ���
		p_north.add(bt_serch); //�˻�
		p_north.add(bt_option,"gapleft 90"); //����	
		add(p_north, BorderLayout.NORTH);
		
		//���Ϳ���
		scroll = new JScrollPane();
		table = new JTable();
		
		table.setTableHeader(null);
		table.setModel(mModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(260);
		table.getColumnModel().getColumn(0).setCellRenderer(new BubbleRenderer());
		scroll.setViewportView(table);
		table.setBackground(Color.white);
		table.setOpaque(true);
		table.setShowHorizontalLines(false);
		scroll.getViewport().setBackground(Color.WHITE);
		
		p_center.setLayout(new BorderLayout());
		p_center.add(scroll, BorderLayout.CENTER);
		add(p_center);
		
		//���ʿ���
		JButton bt_send = new JButton("����");
		JComboBox cmb = new JComboBox(new String[]{"��", "����"});
		JTextPane area = new JTextPane();
		
		
		p_south.setLayout(new MigLayout());
		p_south.add(cmb, "hmax pref");
		p_south.add(area, "hmin 50px,growx,pushx");
		p_south.add(bt_send, "growy,pushy");
		p_center.add(p_south, BorderLayout.SOUTH);
		
		setTitle("ä��������Ʈ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 550);
		setLocationRelativeTo(null);
		
		bt_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sMsg = area.getText().trim();

				String sSend = (String)cmb.getSelectedItem();
				String sTime =  getTime();
				Chat chat = new Chat();
				chat.setSender(sSend);
				chat.setTime(sTime);
				chat.setMsg(sMsg);
				mModel.addRow(chat);
				area.setText("");
			}
		});
		
		area.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				int key= e.getKeyCode();
				if(key==KeyEvent.VK_ENTER){
					String sMsg = area.getText().trim();

					String sSend = (String)cmb.getSelectedItem();
					String sTime =  getTime();
					Chat chat = new Chat();
					chat.setSender(sSend);
					chat.setTime(sTime);
					chat.setMsg(sMsg);
					mModel.addRow(chat);
					area.setText("");
				}
			}
		});
		
		
	}
	
	static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		return sdf.format(new Date());
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatMain frame = new ChatMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
