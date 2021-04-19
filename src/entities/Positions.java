package entities;

public class Positions {

	private int positionId;
	private String position;

	public Positions() {
		
	}
	
	public Positions(int positionId, String position) {
		this.positionId = positionId;
		this.position = position;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	

}
