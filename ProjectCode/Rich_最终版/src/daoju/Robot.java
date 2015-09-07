package daoju;

import java.util.List;

import javax.swing.ImageIcon;



import land.GetHousePicturel;
import land.House;
import user.Player;

public class Robot implements Prop{
	private int price=30;
	private String name="��������";
	@Override
	public void affect(Player player, List<House> houses, int position) {
		// TODO �Զ����ɵķ������
		if(player.getProps().get(this.getName())>0){//�Ƿ��л�������
			position=10;
			for(int i=1;i<=10;i++){
				House clearHouse=houses.get(player.getPosition()+i);
				if(clearHouse.isBomb()){
					clearHouse.setBomb(false);
					clearHouse.setIcon(new ImageIcon(GetHousePicturel.getPicture().get(clearHouse.getLever())));
				}
				if(clearHouse.isFence()){
					clearHouse.setFence(false);
					clearHouse.setIcon(new ImageIcon(GetHousePicturel.getPicture().get(clearHouse.getLever())));
				}			
			}
			player.getProps().put(this.getName(), (player.getProps().get(this)-1));//��������-1	
			
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
