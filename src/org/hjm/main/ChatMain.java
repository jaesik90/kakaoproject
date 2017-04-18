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
	JPanel p_north;//북쪽에 붙여질 패널
	JPanel p_center; //가운데 붙여질 패널
	JPanel p_south;////남쪽에 붙여질 패널
	BubbleModel mModel = new BubbleModel();//테이블 모델
	
	//북쪽 영역
	JButton bt_profil;//북쪽 패널 서쪽의 프로필 사진
	JLabel la_user; //북쪽 패널 센터의 채팅방의 대화상대
	JButton bt_list, bt_totalview, bt_serch;//북쪽 패널 남쪽의 게시판, 모아보기, 검색기능
	JButton bt_option; //북쪽 패널의 동쪽에 설정버튼
	
	//센터영역
	JScrollPane scroll;//가운데 붙여질 스크롤
	JTable table; //가운데 들어갈 테이블

	public ChatMain() {
		initGUI();
	}
	
	public void initGUI() {
		p_north = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		
		//북쪽영역의 패널
		bt_profil = new JButton("이미지");
		la_user = new JLabel("유저");
		bt_list = new JButton("게시판");
		bt_totalview = new JButton("모아보기");
		bt_serch = new JButton("검색");
		bt_option = new JButton("설정");
		
		p_north.setLayout(new MigLayout());
		p_north.setBackground(Color.RED);
		p_north.setPreferredSize(new Dimension(500, 60));
		p_north.add(bt_profil, "span 2 2"); //유저 프로필
		p_north.add(la_user, "span 3,wrap"); //대화방 유저목록
		p_north.add(bt_list); //게시판
		p_north.add(bt_totalview); //모아보기
		p_north.add(bt_serch); //검색
		p_north.add(bt_option,"gapleft 90"); //설정	
		add(p_north, BorderLayout.NORTH);
		
		//센터영역
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
		
		//남쪽영역
		JButton bt_send = new JButton("전송");
		JComboBox cmb = new JComboBox(new String[]{"나", "상대방"});
		JTextPane area = new JTextPane();
		
		
		p_south.setLayout(new MigLayout());
		p_south.add(cmb, "hmax pref");
		p_south.add(area, "hmin 50px,growx,pushx");
		p_south.add(bt_send, "growy,pushy");
		p_center.add(p_south, BorderLayout.SOUTH);
		
		setTitle("채팅프로젝트");
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
