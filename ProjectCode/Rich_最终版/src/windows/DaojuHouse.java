package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import daoju.Prop;
import daoju.PropFactory;
import user.Player;
import user.SetPlayers;


public class DaojuHouse {
	private JFrame shop;
	private JButton sure;
	private JButton cancel;
	private JComboBox setDaoju;
	private Player player;
	private PlayGame game;
	public DaojuHouse(Player player,PlayGame game){
		this.player=player;
		this.game=game;
		init();
	}
	public void init(){
		shop=new JFrame("�����̳�");
		shop.setSize(400, 300);
		shop.setLocation(500, 120);
//		setWindowFrame.setLayout(null);
		Icon icon=new ImageIcon("picture/startBg.jpg","hah");
		JLabel bg=new JLabel(icon, JLabel.CENTER);
		//����
		String[] name={"ը��","·��","��������"};
		setDaoju =new JComboBox(name);
		setDaoju.setBorder(BorderFactory.createTitledBorder("ѡ�����"));
		setDaoju.setBounds(150, 40, 100, 50);
		sure=new JButton("ȷ������");
		sure.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// �������	
				String daoju=(String) setDaoju.getSelectedItem();
				Prop prop=PropFactory.getProp(daoju);
				System.out.println(prop.getName());
				if(player.getTicket()<prop.getPrice()){//�ㄻ����
					String[] options={"ȷ��"};
					JOptionPane.showOptionDialog(null, "��ĵㄻ����!", "Warning", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
							null, options, options[0]);
				}
				else{//����
					player.setTicket(player.getTicket()-prop.getPrice());//����
					game.flushPlayeInfo(player);
					player.getProps().put(prop.getName(), (player.getProps().get(prop.getName())+1));//���߼�һ	
					game.getReminder().append(player.getName()+"�ɹ�������"+prop.getName()+"����!\r\n");
				}
				shop.setVisible(false);
				
			}
		});
		sure.setBounds(110, 160, 90, 50);
		cancel=new JButton("ȡ��");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				shop.setVisible(false);
			}
		});
		cancel.setBounds(210, 160, 90, 50);
		shop.add(cancel);
		shop.add(setDaoju);
		shop.add(sure);
		shop.add(bg);
		shop.setVisible(true);
		
	}
}

