package daoju;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import land.GetHousePicturel;
import land.House;
import user.Player;

public class Fence implements Prop {

	private int price=50;
	private String name="·��";
	@Override
	public void affect(Player player, List<House> houses, int position) {
		// TODO �Զ����ɵķ������
		if(player.getProps().get(this.getName())>0){//�Ƿ���դ��
			int distance=player.getPosition()+position;
			if(distance<0){
				distance+=38;
			}
			if(distance>37){
				distance-=38;
			}
			House house=houses.get(distance);//��ȡĿ�귿��
			if(house.isTicket()||house.isPrison()||house.isHospital()||house.isDaoju()||house.isStart()||house.isBomb()){
				String[] options={"ȷ��"};
				JOptionPane.showOptionDialog(null, "Ŀ��ضβ��ܷ���դ��,������ʹ��!", "Warning", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, options[0]);
			}
			else{//����ը��
				house.setFence(true);
				String image=GetHousePicturel.getFence().get(house.getLever());
				System.out.println(image+"-------"+house.getLever());
				house.setIcon(new ImageIcon(image));//ͼƬ
				player.getProps().put(this.getName(), (player.getProps().get(this.getName())-1));			
			}	
		}
	}
	@Override
	public int getPrice() {
		// TODO �Զ����ɵķ������
		return price;
	}
	@Override
	public String getName() {
		// TODO �Զ����ɵķ������
		return name;
	}

}
