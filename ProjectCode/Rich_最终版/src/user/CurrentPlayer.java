package user;

import java.util.List;

public class CurrentPlayer
{
	private List<Player> allPlayers;
	private static int which = 0;// ��ǰ�ڼ������

	public CurrentPlayer(List<Player> players)
	{
		allPlayers = players;
	}

	public Player getCurrentPlayer()
	{// ���ص�ǰ���
		if (which >= allPlayers.size())
			which = 0;
		return allPlayers.get(which++);

	}

	public static int getPlayerLocation()
	{
		return which;
	}
	public void setWhich(int which)
	{
		this.which=which;
	}
}
