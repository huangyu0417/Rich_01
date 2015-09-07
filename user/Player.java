package user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import daoju.Prop;
import land.House;

public class Player {
	private String name;//�������
	private int money;//�ֽ�
	private int position;//����·��λ��
	private List<House> myHouses;//����
	private int houseProperty;//����
	private Map<String,Integer> props;//����
	private int stayRound=0;	//��ҽԺ��������ͣ���غ���
	private int ticket;//�ㄻ
	private boolean isToPrison=false;//�Ƿ��ڼ���
	private boolean isToHospotal=false;//�Ƿ�ը��ҽԺ
	public Player(String name,int money){
		this.name=name;
		this.money=money;
		ticket=50;
		position=0;
		myHouses=new ArrayList<House>();
		props=new HashMap<String, Integer>();
		props.put("ը��", 0);
		props.put("·��", 0);
		props.put("��������", 0);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO �Զ����ɵķ������
		Player user=(Player)obj;
		return this.getName().equals(user.getName());
	}
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getHouseProperty() {
		return houseProperty;
	}
	public void setHouseProperty(int houseProperty) {
		this.houseProperty = houseProperty;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	public List<House> getMyHouses() {
		return myHouses;
	}

	public void setMyHouses(List<House> myHouses) {
		this.myHouses = myHouses;
	}

	public Map<String, Integer> getProps() {
		return props;
	}

	public void setProps(Map<String, Integer> props) {
		this.props =  props;
	}

	public int getStayRound() {
		return stayRound;
	}

	public void setStayRound(int stayRound) {
		this.stayRound = stayRound;
	}

	public boolean isToPrison() {
		return isToPrison;
	}

	public void setToPrison(boolean isToPrison) {
		this.isToPrison = isToPrison;
	}

	public boolean isToHospotal() {
		return isToHospotal;
	}
	public void setToHospotal(boolean isToHospotal) {
		this.isToHospotal = isToHospotal;
	}
	
}
