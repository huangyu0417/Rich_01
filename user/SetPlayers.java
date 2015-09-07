package user;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;

public class SetPlayers {
	private int number;//����
	private int startMoney;//Ǯ��
	private static List<Player> players;
	private static Map<String, String> names;
	private static Map<String, Color> color;
	public SetPlayers(int number,int startMoney){
		this.number=number;
		this.startMoney=startMoney;
		init();
		setNames();
		setColor();
		System.out.println(players.size());
	}
	
	public static List<Player> getPlayers(){//ȡ��������ҵļ���
		return players;
	}
	public void init(){//��ʼ����Ϸ���
		players=new ArrayList<Player>();
		switch (number) {
		case 2:
			players.add(new Player("Ǯ����(Q)", startMoney));
			players.add(new Player("������(A)", startMoney));
			
			break;
		case 3:
			players.add(new Player("Ǯ����(Q)", startMoney));
			players.add(new Player("������(A)", startMoney));
			players.add(new Player("��С��(S)", startMoney));
			break;
		case 4:
			players.add(new Player("Ǯ����(Q)", startMoney));
			players.add(new Player("������(A)", startMoney));
			players.add(new Player("��С��(S)", startMoney));
			players.add(new Player("�𱴱�(J)", startMoney));
			break;
		default:
			throw new RuntimeException("��Ϸ��������!");
		}
	}
	public void setNames(){
		names=new HashMap<String,String>();
		names.put("Ǯ����(Q)", "Q");
		names.put("������(A)", "A");
		names.put("��С��(S)", "S");
		names.put("�𱴱�(J)","J");
	}
	public static Map<String, String> getNames(){
		return names;
	}
	public void setColor(){
		color=new HashMap<String,Color>();
		color.put("Ǯ����(Q)", Color.red);
		color.put("������(A)", Color.gray);
		color.put("��С��(S)", Color.pink);
		color.put("�𱴱�(J)",Color.yellow);
	}
	public static Map<String, Color> getColor(){
		return color;
	}
}





