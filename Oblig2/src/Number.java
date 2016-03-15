
public class Number {
	private int value;
	private boolean marked;
	
	Number(int value,boolean marked){
		this.value = value;
		this.marked = marked;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}
	
}
