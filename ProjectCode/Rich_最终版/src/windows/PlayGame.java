package windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import user.Player;
import user.SetPlayers;
import land.Guidepost;
import land.House;
import listener.BgmListener;
import listener.DiceListener;
import listener.SellHouseListener;
import listener.UsePropListener;

public class PlayGame {
	private List<House> mapHouses=null;//��ͼ�ϵķ���
	private List<Guidepost> host;//����������
	private List<Player> players;//���
	private List<JTextField> playerName;//�������ǩ
	private List<JLabel> playerMoney;//��ҵ�ǰǮ��
	private List<JLabel> playerTicket;//��ҵĵㄻ��
	private JLabel round;//��ǰ�غϱ�־
	private JTextArea reminder;//��ʾ��
	private JButton useProp;//ʹ�õ���
	private JButton sellHouse;//������
	private JFrame gameWindow;
	private JButton bgm;
	public PlayGame() {
		round=new JLabel();
		players=SetPlayers.getPlayers();
		init();
	}
	public void init(){
		initLists();
		getPlayersInfo();
		
		gameWindow=new JFrame("����");
		gameWindow.setLayout(null);
		gameWindow.setBounds(300, 40, 1000, 650);
		JLabel dice=new JLabel();//���ӵ���
		dice.setBounds(420, 320, 70, 40);
		gameWindow.add(dice);
		JButton doDice=new JButton();
		doDice.setText("����");
		doDice.setBounds(410, 370, 70, 40);
		bgm =new JButton("BGM");
		bgm.setBounds(10, 30, 60, 30);
		bgm.addActionListener(new BgmListener());
		gameWindow.add(bgm);
		useProp=new JButton("(Q)����");
		useProp.setBounds(600, 170, 80, 40);
		useProp.addActionListener(new UsePropListener(this));
		gameWindow.add(useProp);
		sellHouse=new JButton("����");
		sellHouse.setBounds(700, 170, 80, 40);
		sellHouse.addActionListener(new SellHouseListener(this));
		gameWindow.add(sellHouse);
		gameWindow.add(doDice);		
		round.setText("��ǰ�غϣ�"+players.get(0).getName());
		round.setBounds(390, 285, 200, 30);
		gameWindow.add(round);
		reminder=new JTextArea();
		reminder.setEditable(false);
		JScrollPane scroll=new JScrollPane(reminder);	
		scroll.setBounds(325, 170, 250, 110);
		//gameWindow.add(reminder);
		gameWindow.add(scroll);
		showInfo();
		start();
		
		gameWindow.setVisible(true);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
			doDice.addActionListener(new DiceListener(dice,this));
		
	}
	public void showInfo()
	{
		for(Guidepost post:host){
			gameWindow.add(post);
		}
		for(House house:mapHouses){
			gameWindow.add(house);
		}
		for(JTextField label:playerName){
			gameWindow.add(label);
		}
		for(JLabel label:playerMoney){
			gameWindow.add(label);
		}
		for(JLabel label:playerTicket){
			gameWindow.add(label);
		}
	}
	
	
	public void initLists(){//��ʼ�����Ӻ�·��
		mapHouses=new ArrayList<House>();
		host=new ArrayList<Guidepost>();
		for(int i=0;i<=10;i++){//��
			Guidepost p=new Guidepost();
			p.setBackground(Color.white);
			p.setBounds(85+i*75, 70, 70, 30);
			host.add(p);
			House house=new House(i, 200);//
			house.setBackground(Color.green);
			house.setBounds(85+i*75, 100, 70, 40);
			if(i==0){
				house.setStart(true);
			}
			if(i==1){
				house.setBackground(Color.cyan);
				house.setText("S20");
				house.setTicket(true);
				house.setTicket(20);
			}
			if(i==6){
				house.setBackground(Color.cyan);
				house.setText("S60");
				house.setTicket(true);
				house.setTicket(60);
			}
			if(i==10){
				house.setDaoju(true);
				house.setIcon(new ImageIcon("picture/douju.jpg"));
			}
			mapHouses.add(house);

		}
		for(int i=11;i<19;i++){//��
			Guidepost p=new Guidepost();
			p.setBackground(Color.white);
			p.setBounds(905,145+(i-11)*45, 70, 40);
			host.add(p);
			House house=new House(i, 500);
			house.setBackground(Color.green);
			house.setBounds(835, 145+(i-11)*45, 70, 40);
			if(i==11){
				house.setBackground(Color.cyan);
				house.setText("S60");
				house.setTicket(true);
				house.setTicket(60);
			}
			if(i==18){
				house.setBackground(Color.cyan);
				house.setText("S80");
				house.setTicket(true);
				house.setTicket(80);
			}
			
			mapHouses.add(house);
			
		}
		for(int i=19;i<30;i++){//��
			Guidepost p=new Guidepost();
			p.setBackground(Color.white);
			p.setBounds(835-(i-19)*75, 545, 70, 30);
			host.add(p);
			House house=new House(i, 300);
			house.setBackground(Color.green);
			house.setBounds(835-(i-19)*75, 505, 70, 40);
			if(i==19){
				house.setPrison(true);
				house.setIcon(new ImageIcon("picture/jianyu.jpg"));
			}
			if(i==24){
				house.setBackground(Color.cyan);
				house.setText("S40");
				house.setTicket(true);
				house.setTicket(40);
			}
			if(i==28){
				house.setBackground(Color.cyan);
				house.setText("S20");
				house.setTicket(true);
				house.setTicket(20);
			}
			if(i==29){
				house.setHospital(true);
				house.setIcon(new ImageIcon("picture/yiyuan.jpg"));
			}
			mapHouses.add(house);
		}
		for(int i=30;i<38;i++)//��
		{
			Guidepost p=new Guidepost();
			p.setBackground(Color.white);
			p.setBounds(15, 460-(i-30)*45, 70, 40);
			host.add(p);
			House house=new House(i, 400); 
			house.setBackground(Color.green);
			house.setBounds(85, 460-(i-30)*45, 70, 40);
			if(i==33){
				house.setBackground(Color.cyan);
				house.setText("S40");
				house.setTicket(true);
				house.setTicket(40);
			}
			if(i==37){
				house.setBackground(Color.cyan);
				house.setText("S60");
				house.setTicket(true);
				house.setTicket(60);
			}
			mapHouses.add(house);
		}
	}
	public void getPlayersInfo(){//���������ʾ��ʼ��
		playerName=new ArrayList<JTextField>();
		playerMoney=new ArrayList<JLabel>();
		playerTicket=new ArrayList<JLabel>();
		for(int i=0;i<players.size();i++){
			JTextField name=new JTextField(players.get(i).getName()+":");
			name.setEditable(false);
			name.setBackground(SetPlayers.getColor().get(players.get(i).getName()));
			name.setBounds(85+i*220, 30, 60, 30);
			playerName.add(name);
			JLabel money=new JLabel("�ʽ�"+players.get(i).getMoney());
			money.setBounds(145+i*220, 30, 80, 30);
			playerMoney.add(money);
			JLabel ticket=new JLabel("�ㄻ"+players.get(i).getTicket());
			ticket.setBounds(225+i*220, 30, 100, 30);
			playerTicket.add(ticket);
		}	
	}
	public void flushPlayeInfo(Player player){//ˢ����ҵ�������ʾ
		playerMoney.get(playerNumber(player)).setText("�ʽ�"+player.getMoney()+"");
		playerTicket.get(playerNumber(player)).setText("�ㄻ"+player.getTicket()+"");
		
	}
	public void deletePlayer(Player player)
	{
		playerMoney.get(playerNumber(player)).setText("������Ʋ�");
		playerTicket.get(playerNumber(player)).setText("");
		playerMoney.remove(playerNumber(player));
		playerTicket.remove(playerNumber(player));
		
	}
	public int playerNumber(Player player){
		for(int i=0;i<players.size();i++){
			if(player.getName().equals(players.get(i).getName())){
				return i;
			}
		}
		return -1;
	}
	public void start(){
		mapHouses.get(0).setText("���");
		for(Player player:players){
			player.setPosition(0);
			host.get(0).setText(host.get(0).getText()+SetPlayers.getNames().get(player.getName()));;
		}
	}
	public List<House> getMapHouses() {
		return mapHouses;
	}
	public void setMapHouses(List<House> mapHouses) {
		this.mapHouses = mapHouses;
	}
	public List<Guidepost> getHost() {
		return host;
	}
	public void setHost(List<Guidepost> host) {
		this.host = host;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<JTextField> getPlayerName() {
		return playerName;
	}
	public void setPlayerName(List<JTextField> playerName) {
		this.playerName = playerName;
	}
	public List<JLabel> getPlayerMoney() {
		return playerMoney;
	}
	public void setPlayerMoney(List<JLabel> playerMoney) {
		this.playerMoney = playerMoney;
	}
	public JLabel getRound() {
		return round;
	}
	public void setRound(JLabel round) {
		this.round = round;
	}
	public JTextArea getReminder() {
		return reminder;
	}
	public void setReminder(JTextArea reminder) {
		this.reminder = reminder;
	}
	public List<JLabel> getPlayerTicket() {
		return playerTicket;
	}
	public void setPlayerTicket(List<JLabel> playerTicket) {
		this.playerTicket = playerTicket;
	}
	public JButton getUseProp() {
		return useProp;
	}
	public void setUseProp(JButton useProp) {
		this.useProp = useProp;
	}
	public JFrame getGameWindow() {
		return gameWindow;
	}
	public void setGameWindow(JFrame gameWindow) {
		this.gameWindow = gameWindow;
	}
	
	
}
