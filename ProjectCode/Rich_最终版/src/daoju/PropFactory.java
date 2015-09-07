package daoju;

public class PropFactory {

	public static Prop getProp(String propName){
		if("".equals(propName.trim())){
			return null;
		}
		else if(propName.contains("ը��")){
			return new Bomb();
		}
		else if(propName.contains("·��")){
			return new Fence();
		}
		else if(propName.contains("��������")){
			return new Robot();
		}
		else{
			return null;
		}
	}
}
