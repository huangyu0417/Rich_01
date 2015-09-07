package daoju;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import land.GetHousePicturel;
import land.House;
import user.Player;

public class Bomb implements Prop{
	//ը������
	private int price=50;
	private String name="ը��";
	@Override
	public void affect(Player player, List<House> houses, int position) {
		// TODO �Զ����ɵķ������
		if(player.getProps().get(this.getName())>0){//�Ƿ���ը��
		int distance=player.getPosition()+position;
		if(distance<0){
			distance+=38;
		}
		if(distance>37){
			distance-=38;
		}
		System.out.println("----"+position);
		House house=houses.get(distance);//��ȡĿ�귿��
		if(house.isTicket()||house.isPrison()||house.isHospital()||house.isDaoju()||house.isStart()||house.isFence()){
			String[] options={"ȷ��"};
			JOptionPane.showOptionDialog(null, "Ŀ��ضβ��ܷ���ը��,������ʹ��!", "Warning", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
		}
		else{//����ը��
			house.setBomb(true);
			house.setIcon(new ImageIcon(GetHousePicturel.getBomb().get(house.getLever())));//ͼƬ
			player.getProps().put(this.getName(), (player.getProps().get(this.getName())-1));			
		}	
		}
	}
	public int getPrice() {
		return price;
	}
	@Override
	public String getName() {
		// TODO �Զ����ɵķ������
		return name;
	}
	
	
}
