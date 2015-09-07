package land;

import javax.swing.JButton;


import user.Player;

public class House extends JButton{
	private Player belongTo=null;//������
	private int lever=-1;//���Ӽ���
	private int position;//λ��
	private int price;//�����۸�
	private boolean isTicket=false;//�Ƿ����ṩ�ㄻ�ض�
	private int ticket;//�ضεĵㄻ
	private boolean isDaoju=false;//�Ƿ��ǵ�����
	private boolean isPrison=false;//����
	private boolean isHospital=false;//ҽԺ
	private boolean isBomb=false;//�Ƿ���ը��
	private boolean isStart=false;//�Ƿ������
	private boolean isFence=false;//�Ƿ���դ��
	public boolean isHospital() {
		return isHospital;
	}
	public void setHospital(boolean isHospital) {
		this.isHospital = isHospital;
	}
	
	public boolean isBomb() {
		return isBomb;
	}
	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}
	public boolean isPrison() {
		return isPrison;
	}
	public void setPrison(boolean isPrison) {
		this.isPrison = isPrison;
	}
	public House(int position,int price){
		this.position=position;
		this.price=price;
	}
	public Player getBelongTo() {
		return belongTo;
	}
	public void setBelongTo(Player belongTo) {
		this.belongTo = belongTo;
	}
	public int getLever() {
		return lever;
	}
	public void setLever(int lever) {
		this.lever = lever;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isTicket() {
		return isTicket;
	}
	public void setTicket(boolean isTicket) {
		this.isTicket = isTicket;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	public boolean isDaoju() {
		return isDaoju;
	}
	public void setDaoju(boolean isDaoju) {
		this.isDaoju = isDaoju;
	}
	public boolean isStart() {
		return isStart;
	}
	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	public boolean isFence() {
		return isFence;
	}
	public void setFence(boolean ifFence) {
		this.isFence = ifFence;
	}
	
	
}
