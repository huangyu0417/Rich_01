package windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import land.GetHousePicturel;
import land.House;
import user.Player;

public class SellHouse
{
	private JFrame use;
	private JButton sure;
	private JButton cancel;
	private JComboBox position;
	private Player player;
	private PlayGame game;
	public SellHouse(Player player,PlayGame game){
		this.player=player;
		this.game=game;
		init();
	}
	public void init(){
		use=new JFrame("���۷���");
		use.setSize(400, 300);
		use.setLocation(500, 120);
		Icon icon=new ImageIcon("picture/startBg.jpg","hah");
		JLabel bg=new JLabel(icon, JLabel.CENTER);
		//����
		
		List<House> houses=player.getMyHouses();//���ӵ�еķ���
		List<Integer> positions=new ArrayList<Integer>();
		for(int i=0;i<houses.size();i++)
		{
			positions.add(houses.get(i).getPosition());
		}
		
		
		
		Object[] positionArrays=(Object[]) positions.toArray();
		position =new JComboBox(positionArrays);
		position.setBorder(BorderFactory.createTitledBorder("ѡ����۵ķ�����λ��"));
		position.setBounds(150, 100, 100, 50);
		
		sure=new JButton("ȷ������");
		sure.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//���۷���
				int location=(int) position.getSelectedItem();
				House house=game.getMapHouses().get(location);
				player.getMyHouses().remove(house);
				int getmoney=(house.getLever()+1)*house.getPrice()*2;
				player.setMoney(player.getMoney()+getmoney);
				game.flushPlayeInfo(player);
				house.setLever(-1);
				house.setBelongTo(null);
				house.setIcon(new ImageIcon(GetHousePicturel.getPicture().get(house.getLever())));
				game.getHost().get(house.getPosition()).setBackground(Color.WHITE);//��������ɫΪ��ɫ
				
				
				game.getReminder().append(player.getName()+"������һ�׷���!!��\r\n");
				use.setVisible(false);
			}
		});
		sure.setBounds(110, 160, 90, 50);
		cancel=new JButton("ȡ��");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				use.setVisible(false);
			}
		});
		cancel.setBounds(210, 160, 90, 50);
		use.add(cancel);
		use.add(position);
		use.add(sure);
		use.add(bg);
		use.setVisible(true);
	}
}
