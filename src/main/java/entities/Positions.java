package entities;

import javax.persistence.*;

@Entity
@Table(name = "positions")
public class Positions {


	@Id
	@Column(name = "position_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int positionId;
	@Column(name = "position")
	private String position;

	public Positions() {
		
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
