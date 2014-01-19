package 高橋健太.JPL.ch22.ex22_04;

import java.util.Observable;
import java.util.Observer;

import 高橋健太.JPL.ch22.ex22_04.AttributedImpl.STATE;

public class Eye implements Observer {

	private AttributedImpl watching;

	public Eye(AttributedImpl users) {
		watching = users;
		watching.addObserver(this);
	}

	@Override
	public void update(Observable users, Object whichState) {
		if(users != watching)
			throw new IllegalArgumentException();

		AttributedImpl.STATE state = ((AttributedImpl.AttributedState)whichState).state;

		if(state == STATE.ADD)
			System.out.println("ADD");
		else if(state == STATE.REMOVE)
			System.out.println("REMOVE");
	}

}
