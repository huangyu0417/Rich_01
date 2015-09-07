package listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import land.GetHousePicturel;
import land.House;
import random.Dice;
import user.CurrentPlayer;
import user.Player;
import user.SetPlayers;
import windows.DaojuHouse;
import windows.PlayGame;
import windows.Victory;

public class DiceListener implements ActionListener{
	private PlayGame game;
	private JLabel dice;
	private int point;//���ӵĵ���
	private CurrentPlayer currentPlayer;
	private House toHouse;//����ķ���
	private Player current;//��ǰ���
	public DiceListener(JLabel dice,PlayGame game){
		this.dice=dice;
		this.game=game;
		currentPlayer=new CurrentPlayer(game.getPlayers());
		current=currentPlayer.getCurrentPlayer();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(game.getReminder().getLineCount()>=6){
			game.getReminder().setText("");
		}
		
		point =Dice.getNum(dice);
		
		//������λ�õ�����
		game.getHost().get(current.getPosition()).setText(new StringBuffer(game.getHost().get(current.getPosition()).getText()).delete(0, 1).toString());
		//��ǰλ��
		
		System.out.println(current.getPosition()+"---------");
		int location=current.getPosition()+point;
		System.out.println(location+"+++====");
		if(location>37){
			boolean flag=false;
			for(int i=current.getPosition()+1;i<=37;i++){//37֮ǰ·��
				if(flag){
					break;
				}
				House nextHouse=game.getMapHouses().get(i);
				if(nextHouse.isFence()){//ǰ���·����·��
					location=i;
					current.setPosition(location);//�������Ͷ�����Ӻ��λ��
					nextHouse.setIcon(new ImageIcon(GetHousePicturel.getPicture().get(nextHouse.getLever())));
					nextHouse.setFence(false);
					game.getReminder().append(current.getName()+"�ȵ���·��\r\n");
					flag=true;//�ҵ���·��
					
				}
				
			}
			if(!flag){
				location-=38;
				for(int i=0;i<=location;i++){//0�ͺ����·��
					if(flag){
						break;
					}
					House nextHouse=game.getMapHouses().get(i);
					if(nextHouse.isFence()){//ǰ���·����·��
						location=i;//������location
						current.setPosition(location);//�������Ͷ�����Ӻ��λ��
						nextHouse.setIcon(new ImageIcon(GetHousePicturel.getPicture().get(nextHouse.getLever())));
						nextHouse.setFence(false);
						game.getReminder().append(current.getName()+"�ȵ���·��\r\n");
						flag=true;//�ҵ���·��
						break;
					}
				}
			}
		}
		else{
			boolean flag=false;
			for(int i=current.getPosition()+1;i<=location;i++){//0�ͺ����·��
				if(flag){
					break;
				}
				House nextHouse=game.getMapHouses().get(i);
				if(nextHouse.isFence()){//ǰ���·����·��
					location=i;//������location
					current.setPosition(location);//�������Ͷ�����Ӻ��λ��
					nextHouse.setIcon(new ImageIcon(GetHousePicturel.getPicture().get(nextHouse.getLever())));
					nextHouse.setFence(false);
					game.getReminder().append(current.getName()+"�ȵ���·��\r\n");
					flag=true;//�ҵ���·��
					break;
				}
			}
			
			
		}
		
		
		//�жϵ�ǰλ�÷��ӵ�����
		House current_house=game.getMapHouses().get(location);
		current.setPosition(location);
		//�Ǽ���
		if(current_house.isPrison())
		{
			current.setStayRound(3);
			current.setToPrison(true);//�ؽ�����
			game.getReminder().append(current.getName()+"�ڼ���ͣ������\r\n");
			game.getHost().get(current.getPosition()).setText(game.getHost().get(current.getPosition()).getText()+SetPlayers.getNames().get(current.getName()));
			
			
		}
		//������ը������
		else if(current_house.isBomb())
		{
			current.setStayRound(2);
			current.setPosition(29);
			current.setToHospotal(true);//ס��ҽԺ
			current_house.setBomb(false);
			game.getReminder().append(current.getName()+"�ȵ�ը����סԺ2��\r\n");
			current_house.setIcon(new ImageIcon(GetHousePicturel.getPicture().get(current_house.getLever())));//�ı�ͼƬ
			game.getHost().get(current.getPosition()).setText(game.getHost().get(current.getPosition()).getText()+SetPlayers.getNames().get(current.getName()));
		}
		//�ǵ�����
		else if (current_house.isDaoju()) {
			new DaojuHouse(current,game);
			game.getHost().get(current.getPosition()).setText(game.getHost().get(current.getPosition()).getText()+SetPlayers.getNames().get(current.getName()));
		}
		//�����ṩ��ȯ�Ŀ��
		else if(current_house.isTicket())
		{
			current.setTicket(current_house.getTicket()+current.getTicket());
			game.getReminder().append(current.getName()+"�����"+current_house.getTicket()+"�ㄻ\r\n");
			game.flushPlayeInfo(current);
			game.getHost().get(current.getPosition()).setText(game.getHost().get(current.getPosition()).getText()+SetPlayers.getNames().get(current.getName()));
		}
		else if(current_house.isHospital()){//ҽԺ
			game.getHost().get(current.getPosition()).setText(game.getHost().get(current.getPosition()).getText()+SetPlayers.getNames().get(current.getName()));
		}
		else if(current_house.isStart()){//	���			
			game.getHost().get(current.getPosition()).setText(game.getHost().get(current.getPosition()).getText()+SetPlayers.getNames().get(current.getName()));
		}
		//��ͨ���ݣ�
		else{
			game.getHost().get(current.getPosition()).setText(game.getHost().get(current.getPosition()).getText()+SetPlayers.getNames().get(current.getName()));
			if(current_house.getBelongTo()==null){//�յ�				
			int select=JOptionPane.showConfirmDialog(null, "�Ƿ񻨷�"+current_house.getPrice()+"������", "���ز�", JOptionPane.YES_NO_OPTION);
				//0:�ǣ�1:��
			if(select==0){//����˵�
				if(current.getMoney()<current_house.getPrice()){//�ʽ𲻹�
					String[] options={"ȷ��"};
					JOptionPane.showOptionDialog(null, "����ʽ���!", "Warning", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
							null, options, options[0]);
				}
				else{//����ɹ� 0��
					current.setMoney(current.getMoney()-current_house.getPrice());//����
					current.getMyHouses().add(current_house);
					current_house.setLever(current_house.getLever()+1);
					current_house.setIcon(new ImageIcon(GetHousePicturel.getPicture().get(current_house.getLever())));//���õصļ���ͼƬ
					
					current_house.setBelongTo(current);
					game.getHost().get(current_house.getPosition()).setBackground(SetPlayers.getColor().get(current.getName()));
					game.flushPlayeInfo(current);
					game.getReminder().append(current.getName()+"�ɹ�����һ��յأ�����"+current_house.getPrice()+"\r\n");
				}
			}
			}
			else{//������������
				if(current_house.getBelongTo().equals(current)){//�����Լ��ĵ���
					if(current_house.getLever()<3)//������󼶱�Ϊ3
					{
						int select=JOptionPane.showConfirmDialog(null, "�Ƿ񻨷�"+current_house.getPrice()+"��������", "���ز�", JOptionPane.YES_NO_OPTION);
						if(select==0){//�����˵�
							if(current.getMoney()<current_house.getPrice()){//�ʽ𲻹�
								String[] options={"ȷ��"};
								JOptionPane.showOptionDialog(null, "����ʽ���!", "Warning", 
										JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
										null, options, options[0]);
							}
							else{//�����ɹ� 
								current.setMoney(current.getMoney()-(current_house.getPrice()));//����
								current_house.setLever(current_house.getLever()+1);//����
								current_house.setIcon(new ImageIcon(GetHousePicturel.getPicture().get(current_house.getLever())));//���õصļ���ͼƬ
								game.getHost().get(current_house.getPosition()).setBackground(SetPlayers.getColor().get(current.getName()));
								game.flushPlayeInfo(current);//ˢ�²Ƹ���
								game.getReminder().append(current.getName()+"�ɹ��������ӣ�����"+current_house.getPrice()+"\r\n");//����
							}
						}
					}
					else{
						game.getReminder().append(current.getName()+"�ķ����Ѵﶥ��!!!������������\r\n");
					}
				}
				else{//���ڱ��˵ĵ�����
					if(current_house.getBelongTo().getStayRound()==0)//���������û���ؽ���������ס��ҽԺ
					{
					int pay=(current_house.getLever()+1)*current_house.getPrice()/2;
					if(current.getMoney()<pay)//��Ǯ�����˵��������˳���Ϸ
					{
						String[] options={"ȷ��"};
						JOptionPane.showOptionDialog(null, "���ź���"+current.getName()+"����ʽ���!�˳���Ϸ", "Warning", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
								null, options, options[0]);
						for(House house:current.getMyHouses())
						{
							//�ѵ�ǰ����ҵ����з������óɲ������κ���ҵĿյ�״̬
							house.setLever(-1);
							house.setBelongTo(null);
							house.setIcon(new ImageIcon(GetHousePicturel.getPicture().get(house.getLever())));
							game.getHost().get(house.getPosition()).setBackground(Color.WHITE);//��������ɫΪ��ɫ
							
						}
						StringBuffer sb=new StringBuffer(game.getHost().get(current.getPosition()).getText());
						game.getHost().get(current.getPosition()).setText(sb.delete(sb.length()-1, sb.length()).toString());
						game.deletePlayer(current);
						int which=currentPlayer.getPlayerLocation();
						game.getPlayers().remove(current);
						//game.getPlayerMoney().remove(which);
						//game.getPlayerTicket().remove(which);
						System.out.println(game.getPlayers().size()+"haahhahh");
						currentPlayer=new CurrentPlayer(game.getPlayers());//�������õ�ǰ�������
						currentPlayer.setWhich(which-1);
				
					//	current=currentPlayer.getCurrentPlayer();
						if(game.getPlayers().size()<2)
						{
							new Victory(game.getPlayers().get(0),game);
						
						}
						
						
						
					}
					else//��Ǯ������Ǯ�����͸���
					{
					current.setMoney(current.getMoney()-pay);//������Է�
					current_house.getBelongTo().setMoney(current_house.getBelongTo().getMoney()+pay);//��Ǯ
					game.getReminder().append(current.getName()+"��"+current_house.getBelongTo().getName()+"֧����·��"+pay+"\r\n");//֪ͨ
					game.flushPlayeInfo(current);//ˢ����ʾ��
					System.out.println(current.getName()+"������");
					game.flushPlayeInfo(current_house.getBelongTo());
					System.out.println(current_house.getBelongTo().getName()+"�տ���");
					}
					}
					else{//������ס����������ҽԺ
						game.getReminder().append(current.getName()+"������,�������˲��ڼ�,�����·��\r\n");
					}
							
				}
				
			}
			
			
			
			
			
		}		
		current=currentPlayer.getCurrentPlayer();//��ǰ���
		while(true){
		if(current.getStayRound()>0){
			ok:
			if(current.isToHospotal()){
				
				current.setStayRound(current.getStayRound()-1);
				
				if(current.getStayRound()==0){
					current.setToHospotal(false);
					game.getReminder().append("���"+current.getName()+"��Ժ��\r\n");
					break ok;
				}
				game.getReminder().append("���"+current.getName()+"��ը��סԺ��ʣ"+current.getStayRound()+"�غ�\r\n");
				
			}
		out:
		if(current.isToPrison()){
			
			current.setStayRound(current.getStayRound()-1);
			
			if(current.getStayRound()==0){
				current.setToPrison(false);
				game.getReminder().append("���"+current.getName()+"������\r\n");
				break out;
			}
			game.getReminder().append("���"+current.getName()+"���ؽ�����,ʣ���ͷŻغϣ�"+current.getStayRound()+"��\r\n");
			
		}
			current=currentPlayer.getCurrentPlayer();//���ͣ������һ�����
			
		}
		
			
			
			if(current.getStayRound()==0)
				break;
			
		}
		game.getRound().setText("��ǰ�غϣ�"+current.getName());
		game.getUseProp().setText("("+SetPlayers.getNames().get(current.getName())+")����");
		System.out.println("which"+CurrentPlayer.getPlayerLocation());
		}
		
	}
	


