package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import land.House;
import daoju.Prop;
import daoju.PropFactory;
import user.Player;
import user.SetPlayers;


public class UseDaoju {
	private JFrame use;
	private JButton sure;
	private JButton cancel;
	private JComboBox select;
	private JComboBox position;
	private Player player;
	private List<House> houses;
	private PlayGame game;
	public UseDaoju(Player player,List<House> houses,PlayGame game){
		this.player=player;
		this.houses=houses;
		this.game=game;
		init();
	}
	public void init(){
		use=new JFrame("ʹ�õ���");
		use.setSize(400, 300);
		use.setLocation(500, 120);
//		setWindowFrame.setLayout(null);
		Icon icon=new ImageIcon("picture/startBg.jpg","hah");
		JLabel bg=new JLabel(icon, JLabel.CENTER);
		//����
		
		List<String> name=new ArrayList<String>();//���ӵ�еĵ���
		List<String> propName=new ArrayList<String>(player.getProps().keySet());
		System.out.println(player.getName());
		for(int i=0;i<propName.size();i++){
			int number=player.getProps().get(propName.get(i));
			if(number>0){
			name.add(propName.get(i)+" x"+number+"");
			}
		}
		select =new JComboBox(new Vector(name));
		select.setBorder(BorderFactory.createTitledBorder("ѡ�����"));
		select.setBounds(150, 40, 100, 50);
		
		Integer[] money={10,9,8,7,6,5,4,3,2,1,-1,-2,-3,-4,-5,-6,-7,-8,-9,-10};
		position =new JComboBox(money);
		position.setBorder(BorderFactory.createTitledBorder("ѡ�����λ��"));
		position.setBounds(150, 100, 100, 50);
		
		sure=new JButton("ȷ��ʹ��");
		sure.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ʹ�õ���	
				String useDaoju=(String)select.getSelectedItem();
				Prop useProp=PropFactory.getProp(useDaoju);
				int location=(int) position.getSelectedItem();
				useProp.affect(player, houses, location);
				
				game.getReminder().append(player.getName()+"ʹ����"+useProp.getName()+"����!!��\r\n");
				use.setVisible(false);
			}
		});
		sure.setBounds(110, 160, 90, 50);
		cancel=new JButton("ȡ��");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				use.setVisible(false);
			}
		});
		cancel.setBounds(210, 160, 90, 50);
		use.add(cancel);
		use.add(select);
		use.add(position);
		use.add(sure);
		use.add(bg);
		use.setVisible(true);
	}
}

